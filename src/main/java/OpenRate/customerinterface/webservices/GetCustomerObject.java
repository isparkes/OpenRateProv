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
 * $Log: GetCustomerObject.java,v $
 * Revision 1.9  2014-01-27 14:57:57  max
 * Add unit test initial version
 *
 * Revision 1.8  2012-10-20 20:41:30  ian
 * Update for JUnit and Ticket #650870
 *
 * Revision 1.7  2011-06-23 18:14:38  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.6  2010/06/30 22:18:58  ian
 * removed extraneous new lines
 *
 * Revision 1.5  2008/03/15 17:46:37  ian
 * 0.661 Allow manual management of transactions to permit grouped methods in a logical transaction
 *
 * Revision 1.4  2007/12/11 22:52:17  ian
 * 0.624 Remove Parse Exception
 *
 * Revision 1.3  2007/11/30 21:09:14  ian
 * 0.623 Update After test
 *
 * Revision 1.2  2007/11/13 11:08:45  ian
 * 0.623 getCustomer modified
 *
 * Revision 1.1  2007/10/08 14:46:34  ian
 * 0.621 Updated class names, added getProducts
 *
 * Revision 1.4  2007/10/01 21:49:48  ian
 * 0.621 Code formatting
 *
 * Revision 1.3  2007/09/24 22:32:06  ian
 * 0.620 Code Revision for readability
 *
 * Revision 1.2  2007/09/20 20:36:29  ian
 * 0.620 Code Review
 *
 * Revision 1.1  2007/09/17 09:20:38  ian
 * Add GetCustomer
 *
 * ====================================================================
 */
package OpenRate.customerinterface.webservices;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;

/**
 * This defines the GetCustomer method
 *
 * @author kurupavi
 */
public class GetCustomerObject implements GetCustomer
{
  /** Creates a new instance of GetCustomerObject */
  public GetCustomerObject(){}
  public EnquiryMethodReturnTypeObj getCustomer(Session ParentSession, String ClientMSN, long ClientID, String EffectiveDate)
  {
    boolean                    status                             = false;
    long                       tempAccountID                      = 0;
    int                        ErrorCode                          = 0;
    CustomerBusinessLogic      customerBusinessLogic              = new CustomerBusinessLogic();
    Date                       effectiveDate                      = new Date();
    EnquiryMethodReturnTypeObj getCustomerEnquiryMethodReturnType = new EnquiryMethodReturnTypeObj();
    AccountVersionFacade       accountVersionFacade;
    AccountVersion             accountVersion;
    long                       tempAcctVerID;
    SimpleDateFormat           sdfInput = new SimpleDateFormat("yyyyMMddHHmmss");
    // Transaction Management stuff
    Session ourSession        = ParentSession;
    boolean ParentTransaction = false;
    sdfInput.setLenient(false);
    AccountInfo tmpAccountInfo;
    if (((ClientMSN.trim()).length() == 0) && (ClientID <= 0))
    {
      ErrorCode = -100;
      status    = true;
    }
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
        ErrorCode = -5;
        status    = true;
      }
    }
    if (!status)
    {
      // Open a session and transaction
      if (ourSession == null)
      {
        ourSession = OpenRate.customerinterface.webservices.HibernateUtil.currentSession(false);
      }
      else
      {
        // mark that we are in a parent transaction
        ParentTransaction = true;
      }
      // locate the account
      if ((ClientID > 0) && (EffectiveDate != null) && (EffectiveDate.trim()).length() != 0)
      {
        accountVersionFacade = new AccountVersionFacade();
        tempAcctVerID        = accountVersionFacade.getAccountVer(ourSession, ClientID, (int) customerBusinessLogic.getUnixTime(EffectiveDate));
        if (tempAcctVerID == 0)
        {
          ErrorCode = -7;
          status    = true;
        }
      }
      else
      {
        tmpAccountInfo = customerBusinessLogic.locateAccount(ourSession, ClientID, ClientMSN, EffectiveDate);
        tempAcctVerID  = tmpAccountInfo.AccountVerID;
        if (tempAcctVerID == 0)
        {
          ErrorCode = -11;
          status    = true;
        }
      }
      if (!status)
      {
        accountVersionFacade = new AccountVersionFacade();
        accountVersion       = (AccountVersion) accountVersionFacade.GetAccountVer(ourSession, tempAcctVerID);
        tempAccountID        = accountVersion.getAccountID();
        getCustomerEnquiryMethodReturnType.setActivationDate(new Date((long) accountVersion.getStartDate() * 1000));
        getCustomerEnquiryMethodReturnType.setDeactivationDate(new Date((long) accountVersion.getEndDate() * 1000));
      }
    }
    // remove the session
    if (!ParentTransaction)
    {
      // Close down the session we opened
      OpenRate.customerinterface.webservices.HibernateUtil.closeSession();
    }
    getCustomerEnquiryMethodReturnType.setReturnCode(ErrorCode);
    getCustomerEnquiryMethodReturnType.setMessage(customerBusinessLogic.getMessage(ErrorCode));
    getCustomerEnquiryMethodReturnType.setClientID(tempAccountID);
    return getCustomerEnquiryMethodReturnType;
  }
}