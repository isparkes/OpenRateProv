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
 * $Log: ModifyAliasObject.java,v $
 * Revision 1.9  2014-01-27 14:57:57  max
 * Add unit test initial version
 *
 * Revision 1.8  2011-06-25 21:03:02  ian
 * Change ID to long for Sequel Server
 *
 * Revision 1.7  2011/06/23 18:14:39  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.6  2011/02/02 12:06:25  marco
 * added check on EffectiveDate to avoid NullPointerException whene the parameter is not specified
 *
 * Revision 1.5  2010/06/30 22:18:58  ian
 * removed extraneous new lines
 *
 * Revision 1.4  2008/06/08 21:33:24  ian
 * Corrected handling on null MSN
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
 * Revision 1.2  2007/10/01 21:49:46  ian
 * 0.621 Code formatting
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
 * This defines the ModifyAlias method
 *
 * @author kurupavi
 */
public class ModifyAliasObject implements ModifyAlias
{
  /** Creates a new instance of ModifyAliasObject */
  public ModifyAliasObject(){}

  public MethodReturnTypeObj modifyAlias(Session ParentSession, String ClientMSN, long ClientID, String AliasID, String DeactivationDate, String EffectiveDate)
  {
    Date                  OldActivationDate = null;
    boolean               overlap;
    boolean               status                = false;
    int                   exitError             = 999;
    String                ActivationDate        = null;
    CustomerBusinessLogic customerBusinessLogic = new CustomerBusinessLogic();
    List                  users                 = new ArrayList();
    Alias                 alias;
    Alias                 aliasToUpdate                  = null;
    long                  oldAccountID                   = 0;
    MethodReturnTypeObj   modifyAliasMethodReturnTypeObj = new MethodReturnTypeObj();
    Date                  deactionDate                   = new Date();
    Date                  aliasStartDate, aliasEndDate;
    AliasFacade           aliasFacade;
    int                   aliasUnixStartDate;
    int                   aliasUnixEndDate;
    SimpleDateFormat      sdfInput = new SimpleDateFormat("yyyyMMddHHmmss");

    // Transaction Management stuff
    Session     ourSession        = ParentSession;
    Transaction tx                = null;
    boolean     ParentTransaction = false;

    sdfInput.setLenient(false);

    AccountInfo                  tmpAccountInfo;
    int                          Index;
    long                         AliasToWorkOn                = 0;
    int                          effUnixDate                  = 0;
    int                          deactUnixDate                = 0;
    DateValidMethodReturnTypeObj dateValidMethodReturnTypeObj = customerBusinessLogic.dateValidation(ActivationDate, DeactivationDate, EffectiveDate);

    if (dateValidMethodReturnTypeObj.isDeactionDateStatus())
    {
      exitError = -3;
      status    = true;
    }
    else
    {
      DeactivationDate = dateValidMethodReturnTypeObj.getDeactivationDateString();
      deactionDate     = dateValidMethodReturnTypeObj.getDeactionDate();
      deactUnixDate    = customerBusinessLogic.getUnixTime(DeactivationDate);
    }

    if ((AliasID == null) || (AliasID.trim()).length() == 0)
    {
      exitError = -24;
      status    = true;
    }

    if ((EffectiveDate != null) && (EffectiveDate.matches("0+")))
    {
      EffectiveDate                = "";
      dateValidMethodReturnTypeObj = customerBusinessLogic.dateValidation(ActivationDate, DeactivationDate, EffectiveDate);
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
        EffectiveDate = dateValidMethodReturnTypeObj.getEffectiveDateString();
        effUnixDate   = customerBusinessLogic.getUnixTime(EffectiveDate);
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

      tmpAccountInfo = customerBusinessLogic.locateAccount(ourSession, ClientID, ClientMSN, EffectiveDate);

      // see if the accout was found
      if (tmpAccountInfo.ErrorCode == 0)
      {
        // account found
        oldAccountID = tmpAccountInfo.AccountID;
        aliasFacade  = new AliasFacade();
        users        = aliasFacade.getAlias(ourSession, AliasID);

        if (users.size() > 0)
        {
          // find the alias to work on
          for (Index = 0; Index < users.size(); Index++)
          {
            alias              = (Alias) users.get(Index);
            aliasUnixStartDate = alias.getStartDate();
            aliasUnixEndDate   = alias.getEndDate();

            if ((effUnixDate >= aliasUnixStartDate) & (effUnixDate < aliasUnixEndDate))
            {
              // we have found the alias to work on
              AliasToWorkOn     = alias.getID();
              OldActivationDate = new Date((long) aliasUnixStartDate * 1000);
              aliasToUpdate     = alias;

              break;
            }
          }

          // Check to see if we found anything
          if (AliasToWorkOn == 0)
          {
            // we did not find the Alias
            exitError = -18;
            status    = true;
          }
        }
        else
        {
          // we did not find the Alias
          exitError = -18;
          status    = true;
        }

        if (!status)
        {
          // we have found the one to work on, now see if there are any
          // overlaps caused by this, working on the same list
          // users = aliasFacade.getAlias(AliasID);
          if (users.size() > 0)
          {
            for (Index = 0; Index < users.size(); Index++)
            {
              alias = (Alias) users.get(Index);

              // we check all the aliases that are not us
              if (alias.getID() != AliasToWorkOn)
              {
                aliasUnixStartDate = alias.getStartDate();
                aliasUnixEndDate   = alias.getEndDate();
                aliasStartDate     = new Date((long) aliasUnixStartDate * 1000);
                aliasEndDate       = new Date((long) aliasUnixEndDate * 1000);
                overlap            = customerBusinessLogic.customerDateOverlap(OldActivationDate, deactionDate, aliasStartDate, aliasEndDate);

                if (overlap)
                {
                  exitError = -6;
                  status    = true;

                  break;
                }
              }
            }
          }

          if (!status)
          {
            // perform the update
            aliasFacade.updateAlias(ourSession, aliasToUpdate, deactUnixDate);
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

    modifyAliasMethodReturnTypeObj.setReturnCode(exitError);
    modifyAliasMethodReturnTypeObj.setMessage(customerBusinessLogic.getMessage(exitError));
    modifyAliasMethodReturnTypeObj.setClientID(oldAccountID);

    return modifyAliasMethodReturnTypeObj;
  }
}
