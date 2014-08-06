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
 * $Log: CreateProdsObject.java,v $
 * Revision 1.7  2014-01-27 14:57:58  max
 * Add unit test initial version
 *
 * Revision 1.6  2011-06-23 18:14:38  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.5  2011/02/02 12:06:25  marco
 * added check on EffectiveDate to avoid NullPointerException whene the parameter is not specified
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

import java.text.SimpleDateFormat;

import java.util.Date;

/**
 *
 * @author sholappr
 */
public class CreateProdsObject implements CreateProduct
{
  /** Creates a new instance of CreateProdsObject */
  public CreateProdsObject(){}

  public MethodReturnTypeObj createProduct(Session ParentSession, String ClientMSN, long ClientID, String ProductName, String SubscriptionID, String ServiceID, String ActivationDate, String DeactivationDate, String EffectiveDate)
  {
    int                   latestAcctVerID;
    boolean               status                = false;
    boolean               EffectiveDateIs0      = false;
    int                   exitError             = 999;
    CustomerBusinessLogic customerBusinessLogic = new CustomerBusinessLogic();
    AccountVersion        accountVersion;
    MethodReturnTypeObj   createProductMethodReturnTypeObj = new MethodReturnTypeObj();
    long                  oldAccountID                     = 0;
    String                tempMSN;
    Date                  accVersionEffectiveDate, accVersionActivationDate, accVersionDeactivationDate;
    int                   accVersionUnixStartDate, accVersionUnixEndDate, accVersionUnixEffectiveDate;
    Date                  effectiveDate = new Date();
    AccountVersionFacade  accountVersionFacade;
    ProductFacade         productFacade = new ProductFacade();
    SimpleDateFormat      sdfInput      = new SimpleDateFormat("yyyyMMddHHmmss");

    // Transaction Management stuff
    Session     ourSession        = ParentSession;
    Transaction tx                = null;
    boolean     ParentTransaction = false;

    sdfInput.setLenient(false);

    AccountInfo tmpAccountInfo;
    int         effDateAcctVerID;
    int         newAcctVerID = 0;

    if ((ProductName == null) || (ProductName.trim()).length() == 0)
    {
      exitError = -19;
      status    = true;
    }

    if ((SubscriptionID == null) || (SubscriptionID.trim()).length() == 0)
    {
      exitError = -17;
      status    = true;
    }

    DateValidMethodReturnTypeObj dateValidMethodReturnTypeObj = customerBusinessLogic.dateValidation(ActivationDate, DeactivationDate, EffectiveDate);

    if (dateValidMethodReturnTypeObj.isActivationDateStatus())
    {
      exitError = -2;
      status    = true;
    }
    else
    {
      ActivationDate = dateValidMethodReturnTypeObj.getActivationDateString();
    }

    if (dateValidMethodReturnTypeObj.isDeactionDateStatus())
    {
      exitError = -3;
      status    = true;
    }
    else
    {
      DeactivationDate = dateValidMethodReturnTypeObj.getDeactivationDateString();
    }

    if (EffectiveDate == null)
    {
      EffectiveDate = "";
    }

    if ((EffectiveDate != null) && (EffectiveDate.matches("0+")))
    {
      EffectiveDateIs0             = true;
      EffectiveDate                = "";
      dateValidMethodReturnTypeObj = customerBusinessLogic.dateValidation(ActivationDate, DeactivationDate, EffectiveDate);
      effectiveDate = dateValidMethodReturnTypeObj.getEffectiveDate();
      EffectiveDate = dateValidMethodReturnTypeObj.getEffectiveDateString();
    }
    else
    {
      if (dateValidMethodReturnTypeObj.isEffectiveDateDateStatus())
      {
        exitError = -5;
        status    = true;
      }
      else
      {
        effectiveDate = dateValidMethodReturnTypeObj.getEffectiveDate();
        EffectiveDate = dateValidMethodReturnTypeObj.getEffectiveDateString();
      }
    }

    // perform the operation now that the inputs are validated
    if (!status)
    {
      // Open a session and transaction
      if (ourSession == null)
      {
        ourSession = OpenRate.customerinterface.webservices.HibernateUtil.currentSession(false);
        tx         = ourSession.beginTransaction();
      }
      else
      {
        // mark that we are in a parent transaction
        ParentTransaction = true;
      }

      tmpAccountInfo = customerBusinessLogic.locateAccount(ourSession, ClientID, ClientMSN, EffectiveDate);

      if (tmpAccountInfo.ErrorCode == 0)
      {
        oldAccountID = tmpAccountInfo.AccountID;
        tempMSN      = tmpAccountInfo.MSN;

        if (oldAccountID > 0)
        {
          // recover the newest version of the account
          accountVersionFacade        = new AccountVersionFacade();
          latestAcctVerID             = accountVersionFacade.GetMaxAccountVerID(ourSession, oldAccountID);
          accountVersion              = (AccountVersion) accountVersionFacade.GetAccountVer(ourSession, latestAcctVerID);
          accVersionUnixStartDate     = accountVersion.getStartDate();
          accVersionUnixEndDate       = accountVersion.getEndDate();
          accVersionUnixEffectiveDate = accountVersion.getEffectiveDate();
          accVersionActivationDate    = new Date((long) accVersionUnixStartDate * 1000);
          accVersionDeactivationDate  = new Date((long) accVersionUnixEndDate * 1000);
          accVersionEffectiveDate     = new Date((long) accVersionUnixEffectiveDate * 1000);

          if (effectiveDate.before(accVersionEffectiveDate) &&!status)
          {
            exitError = -4;
            status    = true;
          }

          if (!status)
          {
            String productTokens[] = ProductName.split("~");

            if (EffectiveDateIs0)
            {
              // add the new product to the existing acct ver
              productFacade.generate(ourSession, latestAcctVerID, productTokens[0], productTokens[1], (int) customerBusinessLogic.getUnixTime(ActivationDate), (int) customerBusinessLogic.getUnixTime(DeactivationDate), oldAccountID, SubscriptionID);
            }
            else
            {
              // create the new acct ver
              tmpAccountInfo = customerBusinessLogic.createNewAccountVersion(ourSession, tempMSN, sdfInput.format(accVersionActivationDate), sdfInput.format(accVersionDeactivationDate), EffectiveDate, oldAccountID, "Objectisfound");
              newAcctVerID = accountVersionFacade.GetMaxAccountVerID(ourSession, tmpAccountInfo.AccountID);

              // Copy over existing products
              customerBusinessLogic.CopyProducts(ourSession, latestAcctVerID, newAcctVerID);

              // Copy over existing ERAs
              customerBusinessLogic.CopyERAs(ourSession, latestAcctVerID, newAcctVerID);

              // finally add the new product
              productFacade.generate(ourSession, newAcctVerID, productTokens[0], productTokens[1], (int) customerBusinessLogic.getUnixTime(ActivationDate), (int) customerBusinessLogic.getUnixTime(DeactivationDate), oldAccountID, SubscriptionID);
            }

            exitError = 0;
          }
        }
      }
      else
      {
        // There was an error in locating the account
        exitError = tmpAccountInfo.ErrorCode;
      }

      // commit the transaction and close the session
      if (!ParentTransaction)
      {
        if (exitError == 0)
        {
          tx.commit();
        }
        else
        {
          tx.rollback();
          oldAccountID = 0;
        }

        // Close down the session we opened
        OpenRate.customerinterface.webservices.HibernateUtil.closeSession();
      }
      else
      {
        if (exitError != 0)
        {
          oldAccountID = 0;
        }
        else
        {
          // flush the session
          ourSession.flush();
        }
      }
    }

    createProductMethodReturnTypeObj.setReturnCode(exitError);
    createProductMethodReturnTypeObj.setMessage(customerBusinessLogic.getMessage(exitError));
    createProductMethodReturnTypeObj.setClientID(oldAccountID);

    return createProductMethodReturnTypeObj;
  }
}