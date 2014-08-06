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
 * $Log: AccountVersionFacade.java,v $
 * Revision 1.7  2014-01-27 14:57:56  max
 * Add unit test initial version
 *
 * Revision 1.6  2011-06-23 18:14:39  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.5  2008/08/31 22:30:32  ian
 * 0.682 Add ModT to tables
 *
 * Revision 1.4  2008/03/15 17:46:38  ian
 * 0.661 Allow manual management of transactions to permit grouped methods in a logical transaction
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
 * Revision 1.6  2007/10/01 21:49:48  ian
 * 0.621 Code formatting
 *
 * Revision 1.5  2007/09/24 22:32:06  ian
 * 0.620 Code Revision for readability
 *
 * Revision 1.4  2007/09/05 23:54:00  ian
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

import java.util.Calendar;
import org.hibernate.Session;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

/**
 *  @netbeans.hibernate.facade beanClass=com.AccountVersion
 */
public class AccountVersionFacade
{
  public Long saveAccountVersion(Session session, AccountVersion accountVersion)
  {
    accountVersion.setModT(Calendar.getInstance().getTimeInMillis());
    session.save(accountVersion);
    session.persist(accountVersion);

    return accountVersion.getAccountVerID();
  }

  public void updateAccountVersion(Session session, AccountVersion accountVersion, int EndDate)
  {
    accountVersion.setModT(Calendar.getInstance().getTimeInMillis());
    accountVersion.setEndDate(EndDate);
    session.saveOrUpdate(accountVersion);
    session.persist(accountVersion);
  }

  public Long generate(Session session, int EffectiveDate, int StartDate, int EndDate, long AccountID, String MSN)
  {
    long           newAcctVerID;
    AccountVersion accountVersion = new AccountVersion();

    accountVersion.setEffectiveDate(EffectiveDate);
    accountVersion.setStartDate(StartDate);
    accountVersion.setEndDate(EndDate);
    accountVersion.setAccountID(AccountID);
    accountVersion.setMSN(MSN);
    newAcctVerID = saveAccountVersion(session, accountVersion);

    return newAcctVerID;
  }

  public List GetAccountVerID(Session session, long accountVersionAccountID)
  {
    org.hibernate.Query query = session.createQuery(" select accountVersion " + " from  " + " AccountVersion as accountVersion " + " where  " + " accountVersion.accountID = ? ");

    query.setLong(0, accountVersionAccountID);

    return query.list();
  }

  public List GetHighestAccountVerID(Session session, int accountVersionAccountID)
  {
    org.hibernate.Query query = session.createQuery("select accountVersion from AccountVersion as accountVersion" + " where accountVersion.accountID = ?" + " and accountVersion.accountVerID =" + " (select max(accountVersion1.accountVerID) " + "  from AccountVersion as accountVersion1 " + "  where accountVersion1.accountID = ?" + " )");

    query.setInteger(0, accountVersionAccountID);
    query.setInteger(1, accountVersionAccountID);

    return query.list();
  }

  public List GetHighestVersionAccountVerID(Session session, int accountVersionAccountID)
  {
    org.hibernate.Query query = session.createQuery(" select accountVersion " + " from  " + " AccountVersion as accountVersion " + " where  accountVersion.accountVerID=(select max(accountVersion1.accountVerID) from AccountVersion as accountVersion1 where " + " accountVersion1.accountID = ? " + ") and accountVersion.accountID = ?");

    query.setInteger(0, accountVersionAccountID);
    query.setInteger(1, accountVersionAccountID);

    return query.list();
  }

  public int GetMaxAccountVerID(Session session, long AccountID)
  {
    org.hibernate.Query query;
    Number              result;

    query = session.createQuery(" select max(accountVerID) " + " from AccountVersion as accountVersion " + " where accountVersion.accountID=:acctID");
    query.setLong("acctID", AccountID);

    if (query.uniqueResult() == null)
    {
      return 0;
    }
    else
    {
      result = (Number) query.uniqueResult();

      return result.intValue();
    }
  }

  public List getAccountVer(Session session, String accountMSN, int accountVersionEffectiveDate)
  {
    org.hibernate.Query query = session.createQuery(" select accountVersion " + " from  " + " Account as account, AccountVersion as accountVersion " + " where  " + " account.accountVerID = accountVersion.accountVerID " + " and accountVersion.effectiveDate =( select max( accountVersion1.effectiveDate ) from Account as account1, AccountVersion as accountVersion1 where account1.accountVerID = accountVersion1.accountVerID " + " and account1.MSN = ? " + " and accountVersion1.effectiveDate <= ? " + ")" + " and account.MSN = ? ");

    query.setParameter(0, accountMSN);
    query.setInteger(1, accountVersionEffectiveDate);
    query.setParameter(2, accountMSN);

    return query.list();
  }

  /**
   * Get the version of the account indicated by the accountID that is valid for
   * the date given in accountVersionEffectiveDate
   */
  public long getAccountVer(Session session, long accountID, int accountVersionEffectiveDate)
  {
    long                result;
    int                 i;
    AccountVersion      tmpAccountVersion;
    org.hibernate.Query query = session.createQuery(" select accountVersion from AccountVersion as accountVersion " + " where accountVersion.accountID = ? order by accountVersion.effectiveDate desc");

    query.setParameter(0, accountID);

    // loop through the results
    result = 0;

    for (i = 0; i < query.list().size(); i++)
    {
      tmpAccountVersion = (AccountVersion) query.list().get(i);

      if (tmpAccountVersion.getEffectiveDate() <= accountVersionEffectiveDate)
      {
        result = tmpAccountVersion.getAccountVerID();
      }
    }

    return result;
  }

  public AccountVersion GetAccountVer(Session session, long AccountVerID)
  {
    org.hibernate.Query query;
    AccountVersion      accountVer;

    query = session.createQuery(" select accountVersion " + " from AccountVersion as accountVersion " + " where accountVersion.accountVerID=?");
    query.setLong(0, AccountVerID);

    if (query.list().size() == 1)
    {
      accountVer = (AccountVersion) query.list().get(0);

      return accountVer;
    }
    else
    {
      return null;
    }
  }
}
