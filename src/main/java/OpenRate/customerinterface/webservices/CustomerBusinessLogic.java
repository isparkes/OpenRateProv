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
 * $Log: CustomerBusinessLogic.java,v $
 * Revision 1.6  2014-01-27 14:57:57  max
 * Add unit test initial version
 *
 * Revision 1.5  2011-06-23 18:14:38  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.4  2008/03/15 17:46:38  ian
 * 0.661 Allow manual management of transactions to permit grouped methods in a logical transaction
 *
 * Revision 1.3  2007/12/11 22:52:17  ian
 * 0.624 Remove Parse Exception
 *
 * Revision 1.2  2007/11/30 21:09:14  ian
 * 0.623 Update After test
 *
 * Revision 1.1  2007/10/08 14:46:34  ian
 * 0.621 Updated class names, added getProducts
 *
 * Revision 1.7  2007/10/01 21:27:29  ian
 * 0.620 Modify Alias
 *
 * Revision 1.6  2007/09/24 22:32:06  ian
 * 0.620 Code Revision for readability
 *
 * Revision 1.5  2007/09/20 20:36:29  ian
 * 0.620 Code Review
 *
 * Revision 1.4  2007/09/05 23:53:59  ian
 * 0.619 Added MSN mapping to AccountVer WIP
 *
 * Revision 1.3  2007/08/30 20:55:06  ian
 * Header and minor bug fixes
 *
 * Revision 1.2  2007/08/29 16:32:37  ian
 * Added Logging, minor bug fixes
 *
 * ====================================================================
 */
package OpenRate.customerinterface.webservices;

//~--- non-JDK imports --------------------------------------------------------

import org.hibernate.Session;

//~--- JDK imports ------------------------------------------------------------

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sholappr
 */
public class CustomerBusinessLogic
{
  public static final long HIGH_DATE = 2147483647;
  public static final long LOW_DATE  = 0;
  SimpleDateFormat         sdfInput  = new SimpleDateFormat("yyyyMMddHHmmss");

  /** Creates a new instance of CustomerBusinessLogic */
  public CustomerBusinessLogic(){}

  public DateValidMethodReturnTypeObj dateValidation(String ActivationDate, String DeactivationDate, String EffectiveDate)
  {
    DateValidMethodReturnTypeObj dateValidMethodReturnTypeObj = new DateValidMethodReturnTypeObj();
    Date                         tempCurrentDate              = new Date(System.currentTimeMillis());
    Date                         tempHighDate                 = new Date(HIGH_DATE * 1000);

    sdfInput.setLenient(false);

    if ((ActivationDate == null) || (ActivationDate.trim()).length() == 0)
    {
      dateValidMethodReturnTypeObj.setActivationDate(tempCurrentDate);
      dateValidMethodReturnTypeObj.setActivationDateString(sdfInput.format(tempCurrentDate));
    }
    else
    {
      try
      {
        dateValidMethodReturnTypeObj.setActivationDate(sdfInput.parse(ActivationDate));
        dateValidMethodReturnTypeObj.setActivationDateString(ActivationDate);
      }
      catch (Exception ex)
      {
        dateValidMethodReturnTypeObj.setActivationDateStatus(true);
      }
    }

    if ((DeactivationDate == null) || (DeactivationDate.trim()).length() == 0)
    {
      dateValidMethodReturnTypeObj.setDeactionDate(tempHighDate);
      dateValidMethodReturnTypeObj.setDeactivationDateString(sdfInput.format(tempHighDate));
    }
    else
    {
      try
      {
        dateValidMethodReturnTypeObj.setDeactionDate(sdfInput.parse(DeactivationDate));
        dateValidMethodReturnTypeObj.setDeactivationDateString(DeactivationDate);
      }
      catch (Exception ex)
      {
        dateValidMethodReturnTypeObj.setDeactionDateStatus(true);
      }
    }

    if ((EffectiveDate == null) || (EffectiveDate.trim()).length() == 0)
    {
      dateValidMethodReturnTypeObj.setEffectiveDate(tempCurrentDate);
      dateValidMethodReturnTypeObj.setEffectiveDateString(sdfInput.format(tempCurrentDate));
    }
    else
    {
      try
      {
        dateValidMethodReturnTypeObj.setEffectiveDate(sdfInput.parse(EffectiveDate));
        dateValidMethodReturnTypeObj.setEffectiveDateString(EffectiveDate);
      }
      catch (Exception ex)
      {
        dateValidMethodReturnTypeObj.setEffectiveDateDateStatus(true);
      }
    }

    return dateValidMethodReturnTypeObj;
  }

  public boolean customerDateOverlap(Date TempActivationDate, Date TempDeactionDate, Date TempAccVersionStartDate, Date TempAccVersionEndDate)
  {
    boolean dateOverlaped       = false;
    Date    activationDate      = TempActivationDate;
    Date    deactionDate        = TempDeactionDate;
    Date    accVersionStartDate = TempAccVersionStartDate;
    Date    accVersionEndDate   = TempAccVersionEndDate;

    if ((accVersionStartDate.after(activationDate) || accVersionStartDate.equals(activationDate)) && (accVersionStartDate.before(deactionDate) || accVersionStartDate.equals(deactionDate)))
    {
      dateOverlaped = true;
    }

    if ((accVersionEndDate.after(activationDate) || accVersionEndDate.equals(activationDate)) && (accVersionEndDate.before(deactionDate) || accVersionEndDate.equals(deactionDate)))
    {
      dateOverlaped = true;
    }

    if ((activationDate.after(accVersionStartDate) || activationDate.equals(accVersionStartDate)) && (activationDate.before(accVersionEndDate) || activationDate.equals(accVersionEndDate)))
    {
      dateOverlaped = true;
    }

    if ((deactionDate.after(accVersionStartDate) || deactionDate.equals(accVersionStartDate)) && (deactionDate.before(accVersionStartDate) || deactionDate.equals(accVersionStartDate)))
    {
      dateOverlaped = true;
    }

    return dateOverlaped;
  }    // customerDateOverlap

  public int getUnixTime(String simpleDateFormat)
  {
    long tmpFromDate;

    try
    {
      tmpFromDate = sdfInput.parse(simpleDateFormat).getTime() / 1000;
    }
    catch (ParseException pe)
    {
      tmpFromDate = 0;
    }

    return (int) tmpFromDate;
  }    // getUnixTime

  /**
   * Create a new account version object and account object
   */
  public AccountInfo createNewAccountVersion(Session ParentSession, String ClientMSN, String ActivationDate, String DeactivationDate, String EffectiveDate, long AccountID, String object)
  {
    long                  tmpAccountID;
    long                  tempAccountVerID = 0;
    int                   tempEffDate;
    int                   tempActDate;
    int                   tempDeactDate;
    AccountInfo           tmpAccountVerInfo     = new AccountInfo();
    CustomerBusinessLogic customerBusinessLogic = new CustomerBusinessLogic();

    tempEffDate   = customerBusinessLogic.getUnixTime(EffectiveDate);
    tempActDate   = customerBusinessLogic.getUnixTime(ActivationDate);
    tempDeactDate = customerBusinessLogic.getUnixTime(DeactivationDate);

    AccountFacade        accountFacade        = new AccountFacade();
    AccountVersionFacade accountVersionFacade = new AccountVersionFacade();

    tempAccountVerID = accountVersionFacade.generate(ParentSession, tempEffDate, tempActDate, tempDeactDate, AccountID, ClientMSN);
    tmpAccountID                   = accountFacade.generate(ParentSession, tempAccountVerID, AccountID, ClientMSN);
    tmpAccountVerInfo.AccountID    = tmpAccountID;
    tmpAccountVerInfo.AccountVerID = tempAccountVerID;

    return tmpAccountVerInfo;
  }    // createNewAccountVersion

  /**
   * Copy over all of the Products from FromAccountVersion to ToAccountVersion
   */
  public void CopyProducts(Session ParentSession, long FromAccountVersion, long ToAccountVersion)
  {
    int           IndexStatus;
    Product       product;
    ProductFacade productFacade = new ProductFacade();
    List          ProductList   = new ArrayList();

    // Copy across the products onto the new acct ver
    ProductList = productFacade.getProducts(ParentSession, FromAccountVersion);

    if (ProductList.size() > 0)
    {
      for (IndexStatus = 0; IndexStatus < ProductList.size(); IndexStatus++)
      {
        product = (Product) ProductList.get(IndexStatus);
        productFacade.generate(ParentSession, ToAccountVersion, product.getProductID(), product.getService(), product.getStartDate(), product.getEndDate(), product.getAccountID(), product.getSubscriptionID());
      }
    }
  }

  /**
   * Copy over all of the ERAs from FromAccountVersion to ToAccountVersion
   */
  public void CopyERAs(Session ParentSession, long FromAccountVersion, long ToAccountVersion)
  {
    List      EraList = new ArrayList();
    ERA       eRA;
    int       Index;
    ERAFacade eRAFacade = new ERAFacade();

    EraList = eRAFacade.getERAs(ParentSession, FromAccountVersion);

    if (EraList.size() > 0)
    {
      for (Index = 0; Index < EraList.size(); Index++)
      {
        eRA = (ERA) EraList.get(Index);
        eRAFacade.generate(ParentSession, ToAccountVersion, eRA.getERAKey(), eRA.getERAValue(), eRA.getAccountID());
      }
    }
  }

  public AccountInfo locateAccount(Session ParentSession, long ClientID, String ClientMSN, String EffectiveDate)
  {
    AccountInfo   returnCode                  = new AccountInfo();
    List          users                       = new ArrayList();
    AccountFacade accountFacade               = new AccountFacade();
    int           accVersionUnixEffectiveDate = 0;
    Account       account;

    if (ClientID > 0)
    {
      users = accountFacade.getAccID(ParentSession, ClientID);

      if (  users.isEmpty())
      {
        returnCode.ErrorCode    = -7;
        returnCode.ErrorMessage = "An attempt was made to modify an account object,but the account object could not " + "be found for the given account ID";

        return returnCode;
      }
    }
    else
    {
      if ((ClientMSN != null) && (ClientMSN.trim()).length() != 0)
      {
        if ((EffectiveDate != null) && (EffectiveDate.trim()).length() != 0)
        {
          accVersionUnixEffectiveDate = getUnixTime(EffectiveDate);
          users                       = accountFacade.getAccID(ParentSession, ClientMSN, accVersionUnixEffectiveDate);

          if (users.isEmpty())
          {
            returnCode.ErrorCode    = -11;
            returnCode.ErrorMessage = "An attempt was made to modify an account object, but the account object could not " + "be found for the given account ID and effective date";

            return returnCode;
          }
        }
      }
      else
      {
        returnCode.ErrorCode = -22;

        return returnCode;
      }
    }

    // Now that we have located the client, fill the return information
    account                 = (Account) users.get(0);
    returnCode.AccountID    = account.getAccountID();
    returnCode.MSN          = account.getMSN();
    returnCode.AccountVerID = account.getAccountVerID();
    returnCode.ErrorCode    = 0;

    return returnCode;
  }

  public String getMessage(int errorCode)
  {
    switch (errorCode)
    {
    case 0 :
      return "OK";

    case -1 :
      return "Overlapping validity date period defined";

    case -2 :
      return "ActivationDate Date Format Incorrect";

    case -3 :
      return "DeactivationDate Date Format Incorrect";

    case -4 :
      return "An attempt was made to create an effective date that was before a previous committed effective date segment";

    case -5 :
      return "EffectiveDate Date Format Incorrect";

    case -6 :
      return "Alias Objects matching with the given alias, there is a period of overlap";

    case -7 :
      return "An attempt was made to retrieve an account object, but the account object could not be found for the given account ID";

    case -8 :
      return "The given product name to migrate from could not be found on the account";

    case -9 :
      return "No product is found with the Request Product Name and the Request Activation Date";

    case -10 :
      return "Migration Date format was Incorrect";

    case -11 :
      return "An attempt was made to modify an account object, but the account object could not be found for the given MSN and effective date";

    case -13 :
      return "The product name to migrate to was not given";

    case -14 :
      return "The given ERA key was not found";

    case -17 :
      return "SubscriptionID string should not be null";

    case -18 :
      return "Alias not found for given Alias Key/Date";

    case -19 :
      return "Product ID not given";

    case -20 :
      return "ProductName string should not be null";

    case -21 :
      return "ERAName Key Not Given";

    case -22 :
      return "Either ClientMSN or ClientID must be filled.";

    case -23 :
      return "Migration From Product Name not given";

    case -24 :
      return "Alias not given";

    default :
      return "Unknown Error";
    }
  }
}