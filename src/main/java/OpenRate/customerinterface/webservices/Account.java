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
 * $Log: Account.java,v $
 * Revision 1.6  2014-01-27 14:57:57  max
 * Add unit test initial version
 *
 * Revision 1.5  2011-06-23 18:14:38  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.4  2010/06/30 22:18:58  ian
 * removed extraneous new lines
 *
 * Revision 1.3  2008/08/31 22:30:32  ian
 * 0.682 Add ModT to tables
 *
 * Revision 1.2  2008/03/15 17:46:37  ian
 * 0.661 Allow manual management of transactions to permit grouped methods in a logical transaction
 *
 * Revision 1.1  2007/10/08 14:46:34  ian
 * 0.621 Updated class names, added getProducts
 *
 * Revision 1.4  2007/10/01 21:49:46  ian
 * 0.621 Code formatting
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

/**
 * This maps the logical account object
 *
 * @hibernate.class
 *     table="ACCOUNT
 */
public class Account
{
  // <editor-fold defaultstate="collapsed" desc=" Property:   int AccountID ">
  private long AccountID;

  // <editor-fold defaultstate="collapsed" desc=" Property:   int AccountVerID ">
  private long AccountVerID;

  // <editor-fold defaultstate="collapsed" desc=" PrimaryKey:   int ID ">
  private long ID;

  // <editor-fold defaultstate="collapsed" desc=" Property:   String MSN ">
  private String MSN;

  // <editor-fold defaultstate="collapsed" desc=" Property:   long ModT ">
  private long ModT;

  /**
   *    @hibernate.id
   *      generator-class="increment"
   *      column="ID"
   *      type="long"
   *    @hibernate.column
   *      name="ID"
   *      sql-type="int"
   *      not-null="true"
   */
  public long getID()
  {
    return ID;
  }

  public void setID(long ID)
  {
    this.ID = ID;
  }

  // </editor-fold>

  /**
   *   @hibernate.property
   */
  public long getAccountVerID()
  {
    return AccountVerID;
  }

  public void setAccountVerID(long AccountVerID)
  {
    this.AccountVerID = AccountVerID;
  }

  // </editor-fold>

  /**
   *   @hibernate.property
   */
  public long getAccountID()
  {
    return AccountID;
  }

  public void setAccountID(long AccountID)
  {
    this.AccountID = AccountID;
  }

  // </editor-fold>

  /**
   *   @hibernate.property
   */
  public String getMSN()
  {
    return MSN;
  }

  public void setMSN(String MSN)
  {
    this.MSN = MSN;
  }

  // </editor-fold>

  /**
   *   @hibernate.property
   */
  public long getModT()
  {
    return ModT;
  }

  public void setModT(long ModT)
  {
    this.ModT = ModT;
  }

  // </editor-fold>

}
