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
public class MigrateProductObjectTest {
  
  public MigrateProductObjectTest() {
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
  public void testMigrateProductObject()
  {
    int xx=0;
    long custId1;
    long custId3;
    long custId5;
    String custMSN1 = "00622011";
    String custMSN3 = "00622013";
    String custMSN5 = "00622015";

    System.out.println("Initialising...");
    MethodReturnTypeObj createCustomerMethodReturnTypeObj;
    CreateCustomerObject createCustomerObject = new CreateCustomerObject();

    MethodReturnTypeObj createProductMethodReturnTypeObj;
    CreateProductObject createProductObject = new CreateProductObject();
    
    MethodReturnTypeObj modifyCustomerMethodReturnTypeObj;
    ModifyCustomerObject modifyCustomerObject = new ModifyCustomerObject();

    MethodReturnTypeObj migrateProductMethodReturnTypeObj;
    MigrateProductObject migrateProductObject = new MigrateProductObject();
    
    System.out.println("========= Migrate Product Tests =========\n");

    System.out.println("Test 6.0");
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
        
    System.out.println("Test 6.0");
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
    
    System.out.println("Test 6.0");
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
    
    System.out.println("Test 6.1");
    System.out.println("Migrate a product, using MSN as key");
    System.out.println("Expected result OK");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,custMSN1,xx,"P.1","P.M","SUBM","20080101000000","20080801120000","20081231120022","");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 6.2");
    System.out.println("Migrate a product, using CustID as key");
    System.out.println("Expected result OK");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,"",custId1,"P.M","P.N","SUBN","20080101000000","20080901120000","","");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 6.3");
    System.out.println("CustID as key, Activation Date format Incorrect");
    System.out.println("Expected result -2 Activation Date format Incorrect");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,"",custId1,"P.M","P.N","SUB1","NotADate","20081231120022","20081231120022","");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-2);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "ActivationDate Date Format Incorrect");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.4");
    System.out.println("CustID as key, Dectivation Date format Incorrect");
    System.out.println("Expected result -3 Deactivation Date format Incorrect");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,"",custId1,"P.M","P.N","SUB1","20080101000000","20081231120022","NotADate","");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-3);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "DeactivationDate Date Format Incorrect");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.5");
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
    
    System.out.println("Test 4.0");
    System.out.println("Modify Customer again, using MSN as key");
    System.out.println("Expected result OK");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN3,xx,"20060303090001","20070201000000");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    System.out.println("Test 6.5");
    System.out.println("MSN as key, Effective Date Too Old");
    System.out.println("Expected result -4 Effective Date Too Old");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,custMSN3,xx,"P.1","P.M","SUB1","20080101000000","20081231120022","20081231120022","20070131000000");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-4);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "An attempt was made to create an effective date that was before a previous committed effective date segment");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.6");
    System.out.println("CustID as key, Effective Date Too Old");
    System.out.println("Expected result -4 Effective Date Too Old");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,custMSN3,xx,"P.1","P.M","SUB1","20080101000000","20081231120022","20081231120022","20070131000000");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-4);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "An attempt was made to create an effective date that was before a previous committed effective date segment");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.7");
    System.out.println("CustID as key, Effective Date format Incorrect");
    System.out.println("Expected result -5 Effective Date format Incorrect");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,custMSN3,xx,"P.1","P.M","SUB1","20080101000000","20081231120022","20081231120022","NotADate");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-5);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "EffectiveDate Date Format Incorrect");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.8");
    System.out.println("CustID as key, CustID not found");
    System.out.println("Expected result -7 Cust ID Not Found");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,"",999123,"P.1","P.M","SUB1","20080101000000","20081231120022","20081231120022","20070131000000");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(-7,migrateProductMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("An attempt was made to retrieve an account object, but the account object could not be found for the given account ID",migrateProductMethodReturnTypeObj.getMessage());
    Assert.assertEquals(0,migrateProductMethodReturnTypeObj.getClientID());
    
    System.out.println("Test 6.9");
    System.out.println("Migrate a product, product does not exist");
    System.out.println("Expected result -8, Product not Found");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,custMSN1,1,"P.100","P.M","SUB1","20080101000000","20081231120022","20081231120022","");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-8);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "The given product name to migrate from could not be found on the account");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.10");
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
    
    System.out.println("Test 6.10");
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
    
    System.out.println("Test 6.10");
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
    
    System.out.println("Test 6.10");
    System.out.println("Migrate a product, product does not exist");
    System.out.println("Expected result -9, Product not Found");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,custMSN5,xx,"P.1","P.M","SUB1","20080301000000","20081231120022","20081231120022","");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-9);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "No product is found with the Request Product Name and the Request Activation Date");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.11");
    System.out.println("MSN as key, MSN not found for date");
    System.out.println("Expected result -11 MSN not found for date");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,custMSN3,xx,"P.1","P.M","SUB1","20080301000000","20081231120022","20081231120022","20010131000000");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-11);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "An attempt was made to modify an account object, but the account object could not be found for the given MSN and effective date");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.12");
    System.out.println("Migrate a product, Migration Date Format Incorrect");
    System.out.println("Expected result -10, Migration Date Format Incorrect");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,"",custId1,"P.2","P.M","SUB1","20080301000000","NotADate","20081231120022","20010131000000");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-10);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "Migration Date format was Incorrect");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.13");
    System.out.println("Migrate a product, Migration To Product Not Found");
    System.out.println("Expected result -13, Migration To Product Not Found");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,"",custId1,"P.2","","SUB1","20080301000000","20081231120022","20081231120022","20010131000000");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-13);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "The product name to migrate to was not given");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.14");
    System.out.println("Migrate a product, SubID not found");
    System.out.println("Expected result -17, SubID not found");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,"",custId1,"P.2","P.M","","20080301000000","20081231120022","20081231120022","20010131000000");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),-17);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "SubscriptionID string should not be null");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 6.15");
    System.out.println("Migrate a product, Effective Date = 0");
    System.out.println("Expected result OK");

    migrateProductMethodReturnTypeObj = migrateProductObject.migrateProduct(null,"",custId1,"P.N","P.O","SUBO","20080301000000","20081231120022","20081231120022","0");

    System.out.println(" EXIT ERROR :"+migrateProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+migrateProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+migrateProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(migrateProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(migrateProductMethodReturnTypeObj.getClientID(), custId1);
  }    
}