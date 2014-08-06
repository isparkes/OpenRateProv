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
 * $Log: MigrateProductObject.java,v $
 * Revision 1.7  2014-01-27 14:57:59  max
 * Add unit test initial version
 *
 * Revision 1.6  2011-06-23 18:14:38  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.5  2011/02/02 12:06:25  marco
 * added check on EffectiveDate to avoid NullPointerException whene the parameter is not specified
 *
 * Revision 1.4  2010/06/30 22:18:58  ian
 * removed extraneous new lines
 *
 * Revision 1.3  2008/03/15 17:54:33  ian
 * 0.661 Allow manual management of transactions to permit grouped methods in a logical transaction
 *
 * Revision 1.2  2007/12/11 22:52:17  ian
 * 0.624 Remove Parse Exception
 *
 * Revision 1.1  2007/10/08 14:46:34  ian
 * 0.621 Updated class names, added getProducts
 *
 * Revision 1.7  2007/10/01 21:49:46  ian
 * 0.621 Code formatting
 *
 * Revision 1.6  2007/09/24 22:32:06  ian
 * 0.620 Code Revision for readability
 *
 * Revision 1.5  2007/09/20 20:36:29  ian
 * 0.620 Code Review
 *
 * Revision 1.4  2007/08/31 19:55:40  ian
 * Code tidy up, final defect fixes
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
 *
 * @author sholappr
 */
public class MigrateProductObject implements MigrateProduct
{
  /** Creates a new instance of MigrateProductObject */
  public MigrateProductObject(){}

  public MethodReturnTypeObj migrateProduct(Session ParentSession, String ClientMSN, long ClientID, String ProductNameOld, String ProductNameNew, String SubscriptionID, String ActivationDate, String MigrationDate, String DeactivationDate, String EffectiveDate)
  {
    boolean               status                = false;
    boolean               EffectiveDateIs0      = false;
    int                   exitError             = 999;
    CustomerBusinessLogic customerBusinessLogic = new CustomerBusinessLogic();
    List                  users                 = new ArrayList();
    AccountVersion        accountVersion;
    Product               product;
    Product               locatedProduct                   = new Product();
    MethodReturnTypeObj   modifyProductMethodReturnTypeObj = new MethodReturnTypeObj();
    long                  oldAccountID                     = 0;
    String                tempMSN;
    Date                  accVersionEffectiveDate;
    Date                  accVersionActivationDate;
    Date                  accVersionDeactivationDate;
    int                   accVersionUnixStartDate, accVersionUnixEndDate, accVersionUnixEffectiveDate;
    Date                  effectiveDate = new Date();
    Date                  migrationDate = new Date();
    AccountVersionFacade  accountVersionFacade;
    ProductFacade         productFacade = new ProductFacade();
    SimpleDateFormat      sdfInput      = new SimpleDateFormat("yyyyMMddHHmmss");

    // Transaction Management stuff
    Session     ourSession        = ParentSession;
    Transaction tx                = null;
    boolean     ParentTransaction = false;

    sdfInput.setLenient(false);

    AccountInfo tmpAccountInfo;
    long        effDateAcctVerID;
    long        latestAcctVerID;
    long        newAcctVerID = 0;

    if ((ProductNameOld == null) || (ProductNameOld.trim()).length() == 0)
    {
      exitError = -23;
      status    = true;
    }

    if ((ProductNameNew == null) || (ProductNameNew.trim()).length() == 0)
    {
      exitError = -13;
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

    if ((MigrationDate == null) || (MigrationDate.trim()).length() == 0)
    {
      migrationDate = new Date(System.currentTimeMillis());
      MigrationDate = sdfInput.format(migrationDate);
    }
    else
    {
      try
      {
        migrationDate = sdfInput.parse(MigrationDate);
      }
      catch (Exception ex)
      {
        exitError = -10;
        status    = true;
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

      // Locate the account to work on
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

          if (!status)
          {
            users = productFacade.getProducts(ourSession, latestAcctVerID, ProductNameOld);

            if (users.size() > 0)
            {
              // if we have multiple products, use the activation date to decide between them
              if (users.size() > 1)
              {
                users = productFacade.getProducts(ourSession, ProductNameOld, (int) customerBusinessLogic.getUnixTime(ActivationDate));

                if (users.isEmpty())
                {
                  // we could find the product name, but there were multiples and there
                  // was no product that matched the name and activation date
                  exitError = -9;
                  status    = true;
                }
              }
            }
            else
            {
              exitError = -8;
              status    = true;
            }

            // Perform the update
            if (!status)
            {
              locatedProduct = (Product) users.get(0);

              if (EffectiveDateIs0)
              {
                // Update the existing product
                productFacade.updateProduct(ourSession, locatedProduct, (int) customerBusinessLogic.getUnixTime(MigrationDate));

                // Create the new product
                productFacade.generate(ourSession, latestAcctVerID, ProductNameNew, locatedProduct.getService(), (int) customerBusinessLogic.getUnixTime(MigrationDate), (int) customerBusinessLogic.getUnixTime(DeactivationDate), oldAccountID, SubscriptionID);
              }
              else
              {
                // create the new account version and copy the products over
                tmpAccountInfo = customerBusinessLogic.createNewAccountVersion(ourSession, tempMSN, sdfInput.format(accVersionActivationDate), sdfInput.format(accVersionDeactivationDate), EffectiveDate, oldAccountID, "Objectisfound");
                newAcctVerID = tmpAccountInfo.AccountVerID;

                // get the products to copy
                users = productFacade.getProducts(ourSession, latestAcctVerID);

                // cycle through, copying
                if (users.size() > 0)
                {
                  for (int Index = 0; Index < users.size(); Index++)
                  {
                    product = (Product) users.get(Index);

                    // Copy all the products accross unaltered, unless ours is the target product
                    if ((product.getStartDate() == locatedProduct.getStartDate()) && (product.getProductID().trim().equalsIgnoreCase(locatedProduct.getProductID())))
                    {
                      // copy across, altering the date and inserting the migrated to product
                      productFacade.generate(ourSession, newAcctVerID, product.getProductID(), product.getService(), product.getStartDate(), (int) customerBusinessLogic.getUnixTime(MigrationDate), product.getAccountID(), product.getSubscriptionID());
                      productFacade.generate(ourSession, newAcctVerID, ProductNameNew, product.getService(), (int) customerBusinessLogic.getUnixTime(MigrationDate), (int) customerBusinessLogic.getUnixTime(DeactivationDate), oldAccountID, SubscriptionID);
                    }
                    else
                    {
                      // copy across, unaltered
                      productFacade.generate(ourSession, newAcctVerID, product.getProductID(), product.getService(), product.getStartDate(), product.getEndDate(), product.getAccountID(), product.getSubscriptionID());
                    }
                  }
                }

                // Copy over existing ERAs
                customerBusinessLogic.CopyERAs(ourSession, latestAcctVerID, newAcctVerID);
              }

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