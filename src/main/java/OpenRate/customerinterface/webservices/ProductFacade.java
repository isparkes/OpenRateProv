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
 * $Log: ProductFacade.java,v $
 * Revision 1.7  2014-01-27 14:57:58  max
 * Add unit test initial version
 *
 * Revision 1.6  2012-10-20 20:41:30  ian
 * Update for JUnit and Ticket #650870
 *
 * Revision 1.5  2011-06-23 18:14:38  ian
 * Change ID to long for compatibility with Sequel Server
 *
 * Revision 1.4  2010/06/30 22:18:58  ian
 * removed extraneous new lines
 *
 * Revision 1.3  2008/08/31 22:30:50  ian
 * 0.682 Add ModT to tables
 *
 * Revision 1.2  2008/03/15 17:59:05  ian
 * 0.661 Allow manual management of transactions to permit grouped methods in a logical transaction
 *
 * Revision 1.1  2007/10/08 14:46:34  ian
 * 0.621 Updated class names, added getProducts
 *
 * Revision 1.4  2007/09/24 22:32:06  ian
 * 0.620 Code Revision for readability
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

import java.util.Calendar;
import org.hibernate.Session;

/**
 * This defines the mapping for the Product object
 */
public class ProductFacade
{
  public void saveProduct(Session session, Product product)
  {
    product.setModT(Calendar.getInstance().getTimeInMillis());
    session.save(product);
    session.persist(product);
  }

  public void updateProduct(Session session, Product product, int endDate)
  {
    product.setEndDate(endDate);
    product.setModT(Calendar.getInstance().getTimeInMillis());
    session.saveOrUpdate(product);
    session.persist(product);
  }

  public void generate(Session session, long AccountVerID, String ProductID, String Service, int StartDate, int EndDate, long AccountID, String SubscriptionID)
  {
    Product productTable = new Product();

    productTable.setAccountVerID(AccountVerID);
    productTable.setProductID(ProductID);
    productTable.setService(Service);
    productTable.setStartDate(StartDate);
    productTable.setEndDate(EndDate);
    productTable.setAccountID(AccountID);
    productTable.setSubscriptionID(SubscriptionID);
    saveProduct(session, productTable);
  }

  public java.util.List GetAccountVerID(Session session, int productAccountID)
  {
    org.hibernate.Query query = session.createQuery(" select product " + " from  " + " Product as product " + "  where  " + " product.accountID = ? ");

    query.setInteger(0, productAccountID);

    return query.list();
  }

  public java.util.List getProducts(Session session, long productAccountVerID)
  {
    org.hibernate.Query query = session.createQuery(" select product " + " from  " + " Product as product " + "  where  " + " product.accountVerID = ? ");

    query.setLong(0, productAccountVerID);

    return query.list();
  }

  public java.util.List getProducts(Session session, long productAccountVerID, java.lang.String productProductID)
  {
    org.hibernate.Query query = session.createQuery(" select product " + " from  " + " Product as product " + "  where  " + " product.productID = ? " + " and product.accountVerID = ? ");

    query.setParameter(0, productProductID);
    query.setLong(1, productAccountVerID);

    return query.list();
  }

  public java.util.List getProducts(Session session, String productProductID, int productStartDate)
  {
    org.hibernate.Query query = session.createQuery(" select product " + " from  " + " Product as product " + "  where  " + " product.productID = ? " + "  and product.startDate = ? ");

    query.setParameter(0, productProductID);
    query.setInteger(1, productStartDate);

    return query.list();
  }

  public java.util.List getProducts(Session session, String productProductID, int productStartDate, java.lang.String productSubscriptionID)
  {
    org.hibernate.Query query = session.createQuery(" select product " + " from  " + " Product as product " + "  where  " + " product.productID = ? " + "  and product.startDate = ? " + "  and product.subscriptionID = ? ");

    query.setParameter(0, productProductID);
    query.setInteger(1, productStartDate);
    query.setParameter(2, productSubscriptionID);

    return query.list();
  }
}