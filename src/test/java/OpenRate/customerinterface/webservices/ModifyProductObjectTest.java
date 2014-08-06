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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ian
 */
public class ModifyProductObjectTest {
  
  public ModifyProductObjectTest() {
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
  public void testModifyProductObject()
  {
    int xx=0;
    long custId1;
    long custId2;
    long custId3;
    long custId4;
    long custId5;
    String custMSN1 = "00522011";
    String custMSN2 = "00522012";
    String custMSN3 = "00522013";
    String custMSN4 = "00522014";
    String custMSN5 = "00522015";

    System.out.println("Initialising...");
    MethodReturnTypeObj createCustomerMethodReturnTypeObj;
    CreateCustomerObject createCustomerObject = new CreateCustomerObject();

    MethodReturnTypeObj modifyCustomerMethodReturnTypeObj;
    ModifyCustomerObject modifyCustomerObject = new ModifyCustomerObject();

    MethodReturnTypeObj createAliasMethodReturnTypeObj;
    CreateAliasObject createAliasObject = new CreateAliasObject();

    MethodReturnTypeObj createProductMethodReturnTypeObj;
    CreateProductObject createProductObject = new CreateProductObject();

    MethodReturnTypeObj modifyProductMethodReturnTypeObj;
    ModifyProductObject modifyProductObject = new ModifyProductObject();

    System.out.println("========= Modify Product Tests =========\n");

    System.out.println("Test 5.0");
    System.out.println("Create Customer 1");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN1, "20060201090001","20080201090001","");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    // We can't know the id of the customer at this point, so just get it
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getReturnCode(), 0);
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getMessage(), "OK");
    custId1 = createCustomerMethodReturnTypeObj.getClientID();
    
    System.out.println("Test 5.0");
    System.out.println("Create Customer 2");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN2, "20060201090001","20080201090001","");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    // We can't know the id of the customer at this point, so just get it
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getReturnCode(), 0);
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getMessage(), "OK");
    custId2 = createCustomerMethodReturnTypeObj.getClientID();
    
    System.out.println("Test 5.0");
    System.out.println("Create Customer 3");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN3, "20060201090001","20080201090001","20070101000000");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    // We can't know the id of the customer at this point, so just get it
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getReturnCode(), 0);
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getMessage(), "OK");
    custId3 = createCustomerMethodReturnTypeObj.getClientID();
    
    System.out.println("Test 5.0");
    System.out.println("Modify Customer again, using MSN as key");
    System.out.println("Expected result OK");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN3,xx,"20060303090001","20070201000000");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    System.out.println("Test 5.0");
    System.out.println("Add a product to Customer 1, using MSN as key");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(null,custMSN1,xx,"P.1","SUB1","TEL","20080101000000","20081231120001","");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 5.1");
    System.out.println("Modify a product, using MSN as key, no activation date");
    System.out.println("Expected result OK");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN1,xx,"P.1","SUB1","","20081231120022","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0,modifyProductMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK",modifyProductMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1,modifyProductMethodReturnTypeObj.getClientID());
    
    System.out.println("Test 5.2");
    System.out.println("Add a product to Customer 1, using CustID as key");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(null,"",custId1,"P.2","SUB2","TEL","20080101000000","20081231120001","");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 5.2");
    System.out.println("Modify a product, using CustID as key, no activation date");
    System.out.println("Expected result OK");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,"",custId1,"P.2","SUB2","","20081231120032","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 5.3");
    System.out.println("CustID as key, Activation Date format Incorrect");
    System.out.println("Expected result -2 Activation Date format Incorrect");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,"",custId1,"P.2","SUB2","NotADate","20081231120002","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-2);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "ActivationDate Date Format Incorrect");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.4");
    System.out.println("CustID as key, Dectivation Date format Incorrect");
    System.out.println("Expected result -3 Deactivation Date format Incorrect");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,"",custId1,"P.2","SUB2","20080101000000","NotADate","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-3);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "DeactivationDate Date Format Incorrect");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.5");
    System.out.println("MSN as key, Effective Date Too Old");
    System.out.println("Expected result -4 Effective Date Too Old");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN3,xx,"P.2","SUB2","20080101000000","20080201000000","20070131000000");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-4);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "An attempt was made to create an effective date that was before a previous committed effective date segment");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.6");
    System.out.println("CustID as key, Effective Date Too Old");
    System.out.println("Expected result -4 Effective Date Too Old");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,"",custId2,"P.2","SUB2","20080101000000","20080201000000","20061231000000");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-4);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "An attempt was made to create an effective date that was before a previous committed effective date segment");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.7");
    System.out.println("CustID as key, Effective Date format Incorrect");
    System.out.println("Expected result -5 Effective Date format Incorrect");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,"",custId1,"P.2","SUB2","20080101000000","20080201000000","NotADate");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-5);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "EffectiveDate Date Format Incorrect");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.8");
    System.out.println("CustID as key, CustID not found");
    System.out.println("Expected result -7 Cust ID Not Found");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,"",999100,"P.2","SUB2","20080101000000","20080201000000","20080201000000");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-7);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "An attempt was made to retrieve an account object, but the account object could not be found for the given account ID");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.9");
    System.out.println("Modify a product, product does not exist");
    System.out.println("Expected result -8, Product not Found");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN1,xx,"P.100","SUB1","","20081231120022","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-8);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "The given product name to migrate from could not be found on the account");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.10");
    System.out.println("Create Customer");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN5, "20060201090001","20080201090001","");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    Assert.assertEquals(createCustomerMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getMessage(), "OK");
    custId5 = createCustomerMethodReturnTypeObj.getClientID();
    
    System.out.println("Test 5.10");
    System.out.println("Create Product instance 1");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(null,custMSN5,xx,"P.1","SUB6","TEL","20080101000000","20081231120001","");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createProductMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createProductMethodReturnTypeObj.getClientID(), custId5);
    
    System.out.println("Test 5.10");
    System.out.println("Create Product instance 2");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(null,custMSN5,xx,"P.1","SUB6","TEL","20080201000000","20081231120001","");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createProductMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createProductMethodReturnTypeObj.getClientID(), custId5);
    
    System.out.println("Test 5.10");
    System.out.println("Modify a product, product does not exist");
    System.out.println("Expected result -9, Product not Found");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN5,xx,"P.1","SUB1","20080301000000","20081231120022","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-9);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "No product is found with the Request Product Name and the Request Activation Date");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.11");
    System.out.println("MSN as key, MSN not found for date");
    System.out.println("Expected result -11 MSN not found for date");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN3,xx,"P.2","SUB2","20080101000000","20080201000000","20010131000000");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-11);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "An attempt was made to modify an account object, but the account object could not be found for the given MSN and effective date");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.12");
    System.out.println("Modify a product, SubID not found");
    System.out.println("Expected result -17, SubID not found");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,"",custId1,"P.2","","20080101000000","20081231120032","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-17);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "SubscriptionID string should not be null");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.13");
    System.out.println("Modify a non-duplicate product using start date");
    System.out.println("Expected result OK");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN1,xx,"P.1","SUB1","20080101000000","20081231120042","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 5.14");
    System.out.println("Add a product to Customer, Effective Date = 0");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(null,custMSN1,xx,"P.3","SUB3","TEL","20080101000000","20081231120001","0");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 5.14");
    System.out.println("Modify a product, Effective Date = 0");
    System.out.println("Expected result OK");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN1,xx,"P.3","SUB3","20080101000000","20091231120022","0");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 5.15");
    System.out.println("Create Product instance 4 for modify product with subID and date test");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(null,custMSN5,xx,"P.1","SUB7","TEL","20080201000100","20081231120001","");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createProductMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    System.out.println("Test 5.15");
    System.out.println("Modify a product using SubscriberID (updates 2 instances)");
    System.out.println("Expected result OK");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN5,xx,"P.1","SUB7","","20080101000003","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), custId5);
    
    System.out.println("Test 5.16");
    System.out.println("Modify a product using SubscriberID, subscriber ID not found");
    System.out.println("Expected result -8, product not found");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN5,xx,"P.100","SUB7","","20080101000001","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-8);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "The given product name to migrate from could not be found on the account");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.17");
    System.out.println("Modify a product using SubscriberID, act date not found");
    System.out.println("Expected result -9, product not found");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN5,xx,"P.1","SUB7","20080102000000","20080101000009","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-9);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "No product is found with the Request Product Name and the Request Activation Date");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 5.18");
    System.out.println("Modify a product using SubscriberID, act date found");
    System.out.println("Expected result OK");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN5,xx,"P.1","SUB7","20080201000100","20080101000006","");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), custId5);
    
    System.out.println("Test 5.19");
    System.out.println("Modify a product, Effective Date = 0, no product found because of active date ");
    System.out.println("Expected result -9, no product found");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,custMSN1,xx,"P.3","SUB3","20080103000000","20091231120022","0");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),-9);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "No product is found with the Request Product Name and the Request Activation Date");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), 0);
  }    
}