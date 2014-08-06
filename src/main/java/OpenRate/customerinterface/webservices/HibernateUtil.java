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
 * $Log: HibernateUtil.java,v $
 * Revision 1.4  2014-01-27 14:57:58  max
 * Add unit test initial version
 *
 * Revision 1.3  2010-06-30 22:18:58  ian
 * removed extraneous new lines
 *
 * Revision 1.2  2008/03/15 17:46:38  ian
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

//~--- non-JDK imports --------------------------------------------------------

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

//~--- JDK imports ------------------------------------------------------------

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Properties;

/**
 * This class encapsulates the interaction with the Hibernate layer.
 *
 * @netbeans.hibernate.util
 */
public class HibernateUtil
{
  private static Log              log     = LogFactory.getLog(HibernateUtil.class);
  public static final ThreadLocal session = new ThreadLocal();
  private static SessionFactory   sessionFactory;

  /**
   * Return the current session factory
   *
   * @return
   */
  private static SessionFactory getSessionFactory(boolean useBeansList)
  {
    try
    {
      if (sessionFactory == null)
      {
        if (useBeansList)
        {
          // use the J2EE type beans list
          Configuration configuration = new Configuration();

          // load all beans
          InputStream    is     = HibernateUtil.class.getResourceAsStream("hibernateBeans.lst");
          BufferedReader reader = new BufferedReader(new InputStreamReader(is));
          String         line;

          while ((line = reader.readLine()) != null)
          {
            configuration.addResource(line);
          }

          Properties properties = new Properties();

          properties.load(HibernateUtil.class.getResourceAsStream("hibernate.properties"));
          configuration.setProperties(properties);
          sessionFactory = configuration.buildSessionFactory();
        }
        else
        {
          // Use the plain old session factory
          sessionFactory = new Configuration().configure().buildSessionFactory();
        }
      }
    }
    catch (Throwable ex)
    {
      log.error("Initial SessionFactory creation failed.", ex);

      throw new ExceptionInInitializerError(ex);
    }

    return sessionFactory;
  }

  public static Session currentSession(boolean useBeansList) throws HibernateException
  {
    Session s = (Session) session.get();

    if (s == null)
    {
      s = getSessionFactory(useBeansList).openSession();
      session.set(s);
    }

    return s;
  }

  public static void closeSession() throws HibernateException
  {
    Session s = (Session) session.get();

    session.set(null);

    if (s != null)
    {
      s.close();
    }
  }
}