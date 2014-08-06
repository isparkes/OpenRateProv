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
package OpenRate.customerinterface.webservices;

import java.util.Calendar;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ian
 */
public class PerformanceTestExtTxn {
  
  public PerformanceTestExtTxn() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test the performance without an enclosing transaction.
   */
  @Test
  public void performanceTestExternalSession()
  {
    int numberToPerform = 100;
    
    System.out.println( "Performance Test (ext tx) Initialising..." );
    MethodReturnTypeObj createCustomerMethodReturnTypeObj;
    CreateCustomerObject createCustomerObject = new CreateCustomerObject( );

    MethodReturnTypeObj createAliasMethodReturnTypeObj;
    CreateAliasObject createAliasObject = new CreateAliasObject( );

    MethodReturnTypeObj createProductMethodReturnTypeObj;
    CreateProductObject createProductObject = new CreateProductObject( );

    MethodReturnTypeObj createERAMethodReturnTypeObj;
    CreateERAObject createERAObject = new CreateERAObject( );

    long CustID;
    
  	// Session management variables
  	Session ourSession;
  	Transaction tx;
    
    // Get the session object to work on
    System.out.println("Opening Transaction");
    ourSession = OpenRate.customerinterface.webservices.HibernateUtil.currentSession(false);
    tx         = ourSession.beginTransaction();
    
    long startTime = Calendar.getInstance().getTimeInMillis();
    
    for( int index=0;index<=numberToPerform;index++ )
    {
      int alias = index;
      String aliasStr = "20000000"+ String.valueOf(alias);
      String clientMSN = "2000"+String.valueOf(index);
      createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(ourSession,clientMSN,"20070101000000","20081231235959","20070101000000");

      if ( createCustomerMethodReturnTypeObj.getClientID( ) == 0  )
      {
        Assert.fail();
      }

      CustID = createCustomerMethodReturnTypeObj.getClientID( );
      createProductMethodReturnTypeObj = createProductObject.createProduct(ourSession, "", CustID, "P.1", "SUB1", "TEL", "20070101000000", "20081231235959", "0" );
      if (createProductMethodReturnTypeObj.getReturnCode() != 0)
      {
        Assert.fail();
      }

      createAliasMethodReturnTypeObj = createAliasObject.createAlias(ourSession, "", CustID, aliasStr.trim( ), "SUB1", "20070101000000", "20081231235959", "0" );
      if (createAliasMethodReturnTypeObj.getReturnCode() != 0)
      {
        Assert.fail();
      }

      createERAMethodReturnTypeObj = createERAObject.createERA(ourSession, "", CustID, "CUG_ID"+alias, "CUG"+alias, "0" );
      if (createERAMethodReturnTypeObj.getReturnCode() != 0)
      {
        Assert.fail();
      }      
    }
    
    long endTime = Calendar.getInstance().getTimeInMillis();    
    
    System.out.println("Closing Transaction");
    ourSession.flush();
    OpenRate.customerinterface.webservices.HibernateUtil.closeSession();
          
    System.out.println( "Performance customer, product, alias, ERA (ext tx): " + numberToPerform + " accounts");
    System.out.println( "Performance customer, product, alias, ERA (ext tx): " + (endTime - startTime)/1000 + " seconds");

    // Check that the performance is acceptable
    if (numberToPerform/((endTime - startTime)/1000) < 10)
    {
      Assert.fail("Performance too low, less than 10 per second");
    }
  }  
}