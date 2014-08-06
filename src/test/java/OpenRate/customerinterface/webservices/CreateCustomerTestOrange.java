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
public class CreateCustomerTestOrange {
  
  public CreateCustomerTestOrange() {
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
   * Test of createCustomer method, of class CreateCustomerObject.
   */
  @Test
  public void CreateTestCustomerOrange()
  {
    int xx=0;
    long custId1;
    long custId2;
    String custMSN1 = "07012011";
    String custMSN2 = "07012012";
    
    Transaction tx;
    Session ourSession;

    System.out.println("Initialising...");
    MethodReturnTypeObj createCustomerMethodReturnTypeObj;
    CreateCustomerObject createCustomerObject = new CreateCustomerObject();

    MethodReturnTypeObj createAliasMethodReturnTypeObj;
    CreateAliasObject createAliasObject = new CreateAliasObject();

    MethodReturnTypeObj createProductMethodReturnTypeObj;
    CreateProductObject createProductObject = new CreateProductObject();

    MethodReturnTypeObj createERAMethodReturnTypeObj;
    CreateERAObject createERAObject = new CreateERAObject();

    System.out.println("Open Sessionu");
    ourSession = OpenRate.customerinterface.webservices.HibernateUtil.currentSession(false);

    System.out.println("Open Transaction Object");
    System.out.println("Remember that for manual transactions, you must be using the InnoDB engine!");
    tx = ourSession.beginTransaction();

    System.out.println("Create Customer 1");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(ourSession,custMSN1, "20070101000000","20071231235959","20070101000000");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");
    
    Assert.assertEquals(0, createCustomerMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createCustomerMethodReturnTypeObj.getMessage());
    custId1 = createCustomerMethodReturnTypeObj.getClientID();

    System.out.println("Add an alias to Customer 1, using CustID as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(ourSession,"",custId1,"222100000000001","SUB2","20070101000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createCustomerMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createCustomerMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createCustomerMethodReturnTypeObj.getClientID());
    
    System.out.println("Add an alias, using CustID as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(ourSession,"",custId1,"0033123400001","SUB1","20070101000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createAliasMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createAliasMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createAliasMethodReturnTypeObj.getClientID());
    
    System.out.println("Add an alias, using CustID as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(ourSession,"",custId1,"0033680000001","SUB2","20070101000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createAliasMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createAliasMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createAliasMethodReturnTypeObj.getClientID());
    
    System.out.println("Add an alias, using CustID as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(ourSession,"",custId1,"0033900000001","SUB3","20070101000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createAliasMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createAliasMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createAliasMethodReturnTypeObj.getClientID());
    
    System.out.println("Add a TEL product to Customer, using CustID as key");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(ourSession,"",custId1,"P.1","SUB1","TEL","20070101000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createProductMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createProductMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createProductMethodReturnTypeObj.getClientID());
    
    System.out.println("Add a MOB product to Customer, using CustID as key");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(ourSession,"",custId1,"P.2","SUB2","MOB","20070303000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createProductMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createProductMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createProductMethodReturnTypeObj.getClientID());
    
    System.out.println("Add an SMS product to Customer, using CustID as key");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(ourSession,"",custId1,"P.3","SUB2","SMS","20070313000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createProductMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createProductMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createProductMethodReturnTypeObj.getClientID());
    
    System.out.println("Add a MOB product to Customer, using CustID as key");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(ourSession,"",custId1,"P.4","SUB3","VOIP","20070313000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createProductMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createProductMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createProductMethodReturnTypeObj.getClientID());
    
    System.out.println("Add an ERA, using CustID as key");
    System.out.println("Expected result OK");

    createERAMethodReturnTypeObj = createERAObject.createERA(ourSession,"",custId1,"CUG_ID","CUG1234","0");

    System.out.println(" EXIT ERROR :"+createERAMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createERAMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createERAMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createERAMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createERAMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1, createERAMethodReturnTypeObj.getClientID());
    
    System.out.println("Create Customer 2");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(ourSession,custMSN2, "20070101000000","20071231235959","20070101000000");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    Assert.assertEquals(0, createCustomerMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createCustomerMethodReturnTypeObj.getMessage());
    custId2 = createCustomerMethodReturnTypeObj.getClientID();

    System.out.println("Add an ERA, using CustID as key");
    System.out.println("Expected result OK");

    createERAMethodReturnTypeObj = createERAObject.createERA(ourSession,"",custId2,"CUG_ID","CUG1234","0");

    System.out.println(" EXIT ERROR :"+createERAMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createERAMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createERAMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createERAMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createERAMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId2, createERAMethodReturnTypeObj.getClientID());
    
    System.out.println("Add an alias, using CustID as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(ourSession,"",custId2,"00331231233","SUB1","20070101000000","20071231235959","0");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createAliasMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createAliasMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId2, createAliasMethodReturnTypeObj.getClientID());
    
    System.out.println("Add an ERA, using CustID as key");
    System.out.println("Expected result OK");

    createERAMethodReturnTypeObj = createERAObject.createERA(ourSession,"",custId2,"CUG_Discount_CUG1234","D.CUG1","0");

    System.out.println(" EXIT ERROR :"+createERAMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createERAMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createERAMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0, createERAMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK", createERAMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId2, createERAMethodReturnTypeObj.getClientID());
    
    System.out.println("Committing transaction");
    tx.commit();
    System.out.println("Closing session");
    ourSession.close();
  }
}