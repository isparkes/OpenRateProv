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
 * $Log: GetERAObject.java,v $
 * Revision 1.8  2014-01-27 14:57:58  max
 * Add unit test initial version
 *
 * Revision 1.7  2011-06-23 18:14:39  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.6  2010/07/09 14:45:51  ian
 * remove unnecessary throw
 *
 * Revision 1.5  2010/07/08 10:37:08  afzaal
 * removed parser exception
 *
 * Revision 1.4  2010/06/30 22:18:58  ian
 * removed extraneous new lines
 *
 * Revision 1.3  2008/06/08 21:33:24  ian
 * Corrected handling on null MSN
 *
 * Revision 1.2  2008/03/15 17:46:38  ian
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This defines the GetERA method
 *
 * @author sholappr
 */
public class GetERAObject implements GetERA
{
  /** Creates a new instance of GetERAObject */
  public GetERAObject(){}

  public EnquiryMethodReturnTypeObj GetERA(Session ParentSession, String ClientMSN, long ClientID, String EffectiveDate) 
  {
    boolean                    status        = false;
    int                        exitError     = 0;
    long                       tempAccountID = 0;
    List                       users         = new ArrayList();
    List                       getERAList    = new ArrayList();
    CustomerBusinessLogic      customerBusinessLogic         = new CustomerBusinessLogic();
    Date                       effectiveDate                 = new Date();
    EnquiryMethodReturnTypeObj GetERAEnquiryMethodReturnType = new EnquiryMethodReturnTypeObj();
    EnquiryERAObjectList       enquiryERAObjectList;
    ERAFacade                  eRAFacade;
    ERA                        eRA;
    long                       tempAcctVerID;
    SimpleDateFormat           sdfInput = new SimpleDateFormat("yyyyMMddHHmmss");

    // Transaction Management stuff
    Session     ourSession        = ParentSession;
    Transaction tx                = null;
    boolean     ParentTransaction = false;

    sdfInput.setLenient(false);

    AccountInfo tmpAccountInfo;

    if ((EffectiveDate == null) || (EffectiveDate.trim()).length() == 0)
    {
      effectiveDate = new Date(System.currentTimeMillis());
      EffectiveDate = sdfInput.format(effectiveDate);
    }
    else
    {
      try
      {
        effectiveDate = sdfInput.parse(EffectiveDate);
      }
      catch (Exception ex)
      {
        exitError = -5;
        status    = true;
      }
    }

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

      // locate the account
      tmpAccountInfo = customerBusinessLogic.locateAccount(ourSession, ClientID, ClientMSN, EffectiveDate);

      // see if the accout was found
      if (tmpAccountInfo.ErrorCode == 0)
      {
        tempAcctVerID  = tmpAccountInfo.AccountVerID;
        tempAccountID  = tmpAccountInfo.AccountID;

        eRAFacade = new ERAFacade();
        users     = eRAFacade.getERAs(ourSession, tempAcctVerID);

        if (users.size() > 0)
        {
          for (int Index = 0; Index < users.size(); Index++)
          {
            eRA                  = (ERA) users.get(Index);
            enquiryERAObjectList = new EnquiryERAObjectList();
            enquiryERAObjectList.setERAKey(eRA.getERAKey());
            enquiryERAObjectList.setERAValue(eRA.getERAValue());
            getERAList.add(enquiryERAObjectList);
          }

          GetERAEnquiryMethodReturnType.setObjectList(getERAList);
        }

        // all OK
        exitError = 0;
      }
      else
      {
        // There was an error in locating the account
        exitError = tmpAccountInfo.ErrorCode;
      }
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
        tempAccountID = 0;
      }
    }

    GetERAEnquiryMethodReturnType.setReturnCode(exitError);
    GetERAEnquiryMethodReturnType.setMessage(customerBusinessLogic.getMessage(exitError));
    GetERAEnquiryMethodReturnType.setClientID(tempAccountID);

    return GetERAEnquiryMethodReturnType;
  }
}