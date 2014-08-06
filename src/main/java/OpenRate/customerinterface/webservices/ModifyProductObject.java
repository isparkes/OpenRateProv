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
* $Log: ModifyProductObject.java,v $
* Revision 1.9  2014-01-27 14:57:56  max
* Add unit test initial version
*
* Revision 1.8  2012-10-20 20:41:30  ian
* Update for JUnit and Ticket #650870
*
* Revision 1.7  2011-06-23 18:14:39  ian
* Change ID to long for compatibility with Sequel Server
*
* Revision 1.6  2011/02/02 12:06:25  marco
* added check on EffectiveDate to avoid NullPointerException whene the parameter is not specified
*
* Revision 1.5  2010/06/30 22:18:58  ian
* removed extraneous new lines
*
* Revision 1.4  2008/03/15 17:59:05  ian
* 0.661 Allow manual management of transactions to permit grouped methods in a logical transaction
*
* Revision 1.3  2007/12/11 22:52:17  ian
* 0.624 Remove Parse Exception
*
* Revision 1.2  2007/10/08 16:00:26  ian
* 0.621 ModifyProduct new algorithm
*
* Revision 1.1  2007/10/08 14:46:34  ian
* 0.621 Updated class names, added getProducts
*
* Revision 1.8  2007/10/01 21:49:46  ian
* 0.621 Code formatting
*
* Revision 1.7  2007/09/24 22:32:06  ian
* 0.620 Code Revision for readability
*
* Revision 1.6  2007/09/20 20:36:29  ian
* 0.620 Code Review
*
* Revision 1.5  2007/08/31 19:55:40  ian
* Code tidy up, final defect fixes
*
* Revision 1.4  2007/08/31 00:02:48  ian
* Effective Date 0
* ====================================================================
*/
package OpenRate.customerinterface.webservices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * This defines the ModifyProduct method
 *
 * @author sholappr
 */
public class ModifyProductObject implements ModifyProduct
{
  /** Creates a new instance of ModifyProductObject */
  public ModifyProductObject(){}

  public MethodReturnTypeObj modifyProduct(Session ParentSession, String ClientMSN, long ClientID, String ProductName, String SubscriptionID, String ActivationDate, String DeactivationDate, String EffectiveDate)
  {
    int                   productModifyCount;
    int                   latestAcctVerID;
    boolean               status                = false;
    boolean               EffectiveDateIs0      = false;
    int                   exitError             = 999;
    CustomerBusinessLogic customerBusinessLogic = new CustomerBusinessLogic();
    List                  users                 = new ArrayList();
    AccountVersion        accountVersion;
    Product               product;
    MethodReturnTypeObj   modifyProductMethodReturnTypeObj = new MethodReturnTypeObj();
    long                  oldAccountID                     = 0;
    String                tempMSN;
    Date                  accVersionEffectiveDate, accVersionActivationDate, accVersionDeactivationDate;
    int                   accVersionUnixStartDate, accVersionUnixEndDate, accVersionUnixEffectiveDate;
    Date                  effectiveDate = new Date();
    AccountVersionFacade  accountVersionFacade;
    ProductFacade         productFacade = new ProductFacade();
    SimpleDateFormat      sdfInput      = new SimpleDateFormat("yyyyMMddHHmmss");
    int                   Index;
    boolean               useActivationDate;

    // Transaction Management stuff
    Session     ourSession        = ParentSession;
    Transaction tx                = null;
    boolean     ParentTransaction = false;

    sdfInput.setLenient(false);

    AccountInfo tmpAccountInfo;
    long        effDateAcctVerID;
    long        newAcctVerID;

    // see if we are using the activation date
    useActivationDate = (ActivationDate.length() > 0);

    // check the parameters
    if ((ProductName == null) || (ProductName.trim()).length() == 0)
    {
      exitError = -20;
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

      // locate the account
      tmpAccountInfo = customerBusinessLogic.locateAccount(ourSession, ClientID, ClientMSN, EffectiveDate);

      if (tmpAccountInfo.ErrorCode == 0)
      {
        oldAccountID     = tmpAccountInfo.AccountID;
        tempMSN          = tmpAccountInfo.MSN;
        effDateAcctVerID = tmpAccountInfo.AccountVerID;

        if (effDateAcctVerID > 0)
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

          // get the number of products to be modified
          if (!status)
          {
            productModifyCount = 0;
            users              = productFacade.getProducts(ourSession, latestAcctVerID);

            for (Index = 0; Index < users.size(); Index++)
            {
              product = (Product) users.get(Index);

              if ((product.getSubscriptionID().equals(SubscriptionID)) && (product.getProductID().equals(ProductName)))
              {
                if (useActivationDate)
                {
                  if (product.getStartDate() == (int) customerBusinessLogic.getUnixTime(ActivationDate))
                  {
                    // count the products that match the activation date
                    productModifyCount++;
                  }
                }
                else
                {
                  // count the products that match just the subscription ID
                  productModifyCount++;
                }
              }
            }

            if (productModifyCount == 0)
            {
              // we did not find any products matching the parameters, exit with an error
              if (useActivationDate)
              {
                exitError = -9;
                status    = true;
              }
              else
              {
                exitError = -8;
                status    = true;
              }
            }
          }

          if (!status)
          {
            if (EffectiveDateIs0)
            {
              // update the existing version
              for (Index = 0; Index < users.size(); Index++)
              {
                product = (Product) users.get(Index);

                if ((product.getSubscriptionID().equals(SubscriptionID)) && (product.getProductID().equals(ProductName)))
                {
                  if (useActivationDate)
                  {
                    if (product.getStartDate() == (int) customerBusinessLogic.getUnixTime(ActivationDate))
                    {
                      // modify
                      productFacade.updateProduct(ourSession, product, (int) customerBusinessLogic.getUnixTime(DeactivationDate));
                    }
                    else
                    {
                      // don't modify
                    }
                  }
                  else
                  {
                    // modify
                    productFacade.updateProduct(ourSession, product, (int) customerBusinessLogic.getUnixTime(DeactivationDate));
                  }
                }
              }

              // everything done OK
              exitError = 0;
            }
            else
            {
              // copy accross, modifying
              // create new acct ver
              tmpAccountInfo = customerBusinessLogic.createNewAccountVersion(ourSession, tempMSN, sdfInput.format(accVersionActivationDate), sdfInput.format(accVersionDeactivationDate), EffectiveDate, oldAccountID, "Objectisfound");
              newAcctVerID = tmpAccountInfo.AccountVerID;

              for (Index = 0; Index < users.size(); Index++)
              {
                product = (Product) users.get(Index);

                if ((product.getSubscriptionID().equals(SubscriptionID)) && (product.getProductID().equals(ProductName)))
                {
                  if (useActivationDate)
                  {
                    if (product.getStartDate() == (int) customerBusinessLogic.getUnixTime(ActivationDate))
                    {
                      // copy across, altering the date
                      productFacade.generate(ourSession, newAcctVerID, product.getProductID(), product.getService(), product.getStartDate(), (int) customerBusinessLogic.getUnixTime(DeactivationDate), product.getAccountID(), product.getSubscriptionID());
                    }
                    else
                    {
                      // copy across, unaltered
                      productFacade.generate(ourSession, newAcctVerID, product.getProductID(), product.getService(), product.getStartDate(), product.getEndDate(), product.getAccountID(), product.getSubscriptionID());
                    }
                  }
                  else
                  {
                    // modify
                    productFacade.generate(ourSession, newAcctVerID, product.getProductID(), product.getService(), product.getStartDate(), (int) customerBusinessLogic.getUnixTime(DeactivationDate), product.getAccountID(), product.getSubscriptionID());
                  }
                }
                else
                {
                  // copy across, unaltered
                  productFacade.generate(ourSession, newAcctVerID, product.getProductID(), product.getService(), product.getStartDate(), product.getEndDate(), product.getAccountID(), product.getSubscriptionID());
                }
              }

              // Copy over the ERAs
              customerBusinessLogic.CopyERAs(ourSession, latestAcctVerID, newAcctVerID);
              exitError = 0;
            }
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

    modifyProductMethodReturnTypeObj.setReturnCode(exitError);
    modifyProductMethodReturnTypeObj.setMessage(customerBusinessLogic.getMessage(exitError));
    modifyProductMethodReturnTypeObj.setClientID(oldAccountID);

    return modifyProductMethodReturnTypeObj;
  }
}