/* ====================================================================
 * Limited Evaluation License:
 *
 * The exclusive owner of this work is The Openrate Project.
 * This work, including all associated documents and components
 * is Copyright The Openrate Project 2006-2014.
 *
 * The following restrictions apply unless they are expressly relaxed in a
 * contractual agreement between the license holder or one of its officially
 * assigned agents and you or your organisation:
 *
 * 1) This work may not be disclosed, either in full or in part, in any form
 *    electronic or physical, to any third party. This includes both in the
 *    form of source code and compiled modules.
 * 2) This work contains trade secrets in the form of architecture, algorithms
 *    methods and technologies. These trade secrets may not be disclosed to
 *    third parties in any form, either directly or in summary or paraphrased
 *    form, nor may these trade secrets be used to construct products of a
 *    similar or competing nature either by you or third parties.
 * 3) This work may not be included in full or in part in any application.
 * 4) You may not remove or alter any proprietary legends or notices contained
 *    in or on this work.
 * 5) This software may not be reverse-engineered or otherwise decompiled, if
 *    you received this work in a compiled form.
 * 6) This work is licensed, not sold. Possession of this software does not
 *    imply or grant any right to you.
 * 7) You agree to disclose any changes to this work to the copyright holder
 *    and that the copyright holder may include any such changes at its own
 *    discretion into the work
 * 8) You agree not to derive other works from the trade secrets in this work,
 *    and that any such derivation may make you liable to pay damages to the
 *    copyright holder
 * 9) You agree to use this software exclusively for evaluation purposes, and
 *    that you shall not use this software to derive commercial profit or
 *    support your business or personal activities.
 *
 * This software is provided "as is" and any expressed or impled warranties,
 * including, but not limited to, the impled warranties of merchantability
 * and fitness for a particular purpose are disclaimed. In no event shall
 * The Openrate Project or its officially assigned agents be liable to any
 * direct, indirect, incidental, special, exemplary, or consequential damages
 * (including but not limited to, procurement of substitute goods or services;
 * Loss of use, data, or profits; or any business interruption) however caused
 * and on theory of liability, whether in contract, strict liability, or tort
 * (including negligence or otherwise) arising in any way out of the use of
 * this software, even if advised of the possibility of such damage.
 * This software contains portions by The Apache Software Foundation, Robert
 * Half International.
 * ====================================================================
 */
/* ========================== VERSION HISTORY =========================
 * $Log: CreateProductsObject.java,v $
 * Revision 1.7  2014-01-27 14:57:57  max
 * Add unit test initial version
 *
 * Revision 1.6  2011-06-23 18:14:39  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.5  2010/07/09 14:45:51  ian
 * remove unnecessary throw
 *
 * Revision 1.4  2010/07/08 10:37:08  afzaal
 * removed parser exception
 *
 * Revision 1.3  2010/06/30 22:18:58  ian
 * removed extraneous new lines
 *
 * Revision 1.2  2008/03/15 17:46:37  ian
 * 0.661 Allow manual management of transactions to permit grouped methods in a logical transaction
 *
 * ====================================================================
 */
package OpenRate.customerinterface.webservices;

//~--- non-JDK imports --------------------------------------------------------

import org.hibernate.Session;
import org.hibernate.Transaction;

//~--- JDK imports ------------------------------------------------------------

/**
 *
 * @author sholappr
 */
public class CreateProductsObject implements CreateProducts
{
  /** Creates a new instance of CreateProductsObject */
  public CreateProductsObject(){}

  public MethodReturnTypeObj createProducts(Session ParentSession, String ClientMSN, long ClientID, String[] ProductName, String SubscriptionID, String ServiceID, String ActivationDate, String DeactivationDate, String EffectiveDate) 
  {
    // Transaction Management stuff
    Session               ourSession                        = ParentSession;
    Transaction           tx                                = null;
    boolean               ParentTransaction                 = false;
    boolean               transactionValidation             = false;
    String                tmpEffectiveDate                  = null;
    MethodReturnTypeObj   createProductsMethodReturnTypeObj = new MethodReturnTypeObj();
    MethodReturnTypeObj[] methodReturnTypeObj               = new MethodReturnTypeObj[ProductName.length];

    // Open a session and transaction
    if (ourSession == null)
    {
      ourSession = OpenRate.customerinterface.webservices.HibernateUtil.currentSession(false);
      tx         = ourSession.beginTransaction();

      // mark that we are in a parent transaction
      ParentTransaction = true;
    }

    for (int indexStatus = 0; indexStatus < ProductName.length; indexStatus++)
    {
      /*
       * if (indexStatus ==0)
       */
      {
        if ((EffectiveDate != null) &&!EffectiveDate.equals(""))
        {
          tmpEffectiveDate = EffectiveDate.trim();
        }
        else
        {
          tmpEffectiveDate = "0";
        }
      }

      /*
       * else
       * {
       * tmpEffectiveDate = "0";
       * }
       */
      methodReturnTypeObj[indexStatus] = new CreateProdsObject().createProduct(ParentSession, ClientMSN, ClientID, ProductName[indexStatus], SubscriptionID, ServiceID, ActivationDate, DeactivationDate, tmpEffectiveDate);

      if (methodReturnTypeObj[indexStatus].ClientID == 0)
      {
        transactionValidation = true;
        createProductsMethodReturnTypeObj.setReturnCode(methodReturnTypeObj[indexStatus].getReturnCode());
        createProductsMethodReturnTypeObj.setMessage(methodReturnTypeObj[indexStatus].getMessage());
        createProductsMethodReturnTypeObj.setClientID(methodReturnTypeObj[indexStatus].getClientID());

        break;
      }
    }

    if (!ParentTransaction)
    {
      // commit the transaction and close the session
      if (!transactionValidation)
      {
        createProductsMethodReturnTypeObj.setReturnCode(methodReturnTypeObj[0].getReturnCode());
        createProductsMethodReturnTypeObj.setMessage(methodReturnTypeObj[0].getMessage());
        createProductsMethodReturnTypeObj.setClientID(methodReturnTypeObj[0].getClientID());
        tx.commit();
      }
      else
      {
        tx.rollback();

        // oldAccountID = 0;
      }
    }
    else
    {
      if (!transactionValidation)
      {
        // oldAccountID = 0;
      }
      else
      {
        // flush the session
        ourSession.flush();
      }
    }

    OpenRate.customerinterface.webservices.HibernateUtil.closeSession();

    return createProductsMethodReturnTypeObj;
  }
}