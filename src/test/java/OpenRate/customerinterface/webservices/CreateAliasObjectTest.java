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
public class CreateAliasObjectTest {
  
  public CreateAliasObjectTest() {
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
  public void testCreateAliasObject()
  {
    int xx=0;
    long custId1;
    long custId2;
    long custId3;
    long custId4;
    String custMSN1 = "00322011";
    String custMSN2 = "00322012";
    String custMSN3 = "00322013";
    String custMSN4 = "00322014";

    System.out.println("Initialising...");
    MethodReturnTypeObj createCustomerMethodReturnTypeObj;
    CreateCustomerObject createCustomerObject = new CreateCustomerObject();

    MethodReturnTypeObj modifyCustomerMethodReturnTypeObj;
    ModifyCustomerObject modifyCustomerObject = new ModifyCustomerObject();

    MethodReturnTypeObj createAliasMethodReturnTypeObj;
    CreateAliasObject createAliasObject = new CreateAliasObject();
    
    System.out.println("========= Create Alias Tests =========\n");

    System.out.println("Test 3.0");
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
    
    System.out.println("Test 3.0");
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
    
    System.out.println("Test 3.0");
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
    
    System.out.println("Test 3.0");
    System.out.println("Modify Customer again, using MSN as key");
    System.out.println("Expected result OK");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN3,xx,"20060303090001","20070201000000");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    System.out.println("Test 3.0");
    System.out.println("Create Customer 4");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN4, "20060201090001","20080201090001","20060201090001");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    // We can't know the id of the customer at this point, so just get it
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getReturnCode(), 0);
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getMessage(), "OK");
    custId4 = createCustomerMethodReturnTypeObj.getClientID();
    
    System.out.println("Test 3.1");
    System.out.println("Add an alias to Customer 1, using CustID as key");
    System.out.println("Expected result OK");
    
    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId1,"222100000000001","SUB1","20080101000000","20081231120001","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 3.2");
    System.out.println("Add an alias to Customer 1, using MSN as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,custMSN1,xx,"222100000000002","SUB1","20080101000000","20081231120001","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 3.3");
    System.out.println("Create Customer 4");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN4, "20060201090001","20080201090001","20070101000000");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 3.4");
    System.out.println("Alias in past using MSN as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,custMSN4,xx,"222100000000005","SUB1","20080101000000","20081231120001","20070101000010");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), custId4);
    
    System.out.println("Test 3.5");
    System.out.println("Add an alias to Customer 4, using CustID as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId4,"222100000000006","SUB1","20080101000000","20081231120001","20070101000000");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), custId4);
    
    System.out.println("Test 3.6");
    System.out.println("Add an overlapping alias (overlap end date)");
    System.out.println("Expected result -6 Alias already assigned for date/time");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId1,"222100000000001","SUB2","20080301000000","20090131120001","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-6);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "Alias Objects matching with the given alias, there is a period of overlap");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.7");
    System.out.println("Add an overlapping alias (overlap start date)");
    System.out.println("Expected result -6 Alias already assigned for date/time");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId1,"222100000000001","SUB2","20071201000000","20080131120001","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-6);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "Alias Objects matching with the given alias, there is a period of overlap");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.8");
    System.out.println("Add an overlapping alias (completely contained)");
    System.out.println("Expected result -6 Alias already assigned for date/time");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId1,"222100000000001","SUB2","20080201000000","20080202120001","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-6);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "Alias Objects matching with the given alias, there is a period of overlap");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.9");
    System.out.println("Add an overlapping alias (engulfing)");
    System.out.println("Expected result -6 Alias already assigned for date/time");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId1,"222100000000001","SUB2","20070101000000","20091231120001","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-6);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "Alias Objects matching with the given alias, there is a period of overlap");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.10");
    System.out.println("Add an alias");
    System.out.println("Expected result -2 Activation Date Format Incorrect");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId2,"222100000000004","SUB2","NotADate","20091231120001","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-2);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "ActivationDate Date Format Incorrect");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.11");
    System.out.println("Add an alias");
    System.out.println("Expected result -3 Deactivation Date Format Incorrect");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId2,"222100000000004","SUB2","20070101000000","NotADate","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-3);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "DeactivationDate Date Format Incorrect");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.12");
    System.out.println("Add an alias, Cust ID as key");
    System.out.println("Expected result -4 Effective Date Too Old");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId2,"222100000000005","SUB2","20070101000000","20070101000000","20061231000000");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-4);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "An attempt was made to create an effective date that was before a previous committed effective date segment");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.13");
    System.out.println("Add an alias, MSN as key");
    System.out.println("Expected result -4 Effective Date Too Old");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,custMSN3,xx,"222100000000005","SUB2","20070101000000","20070101000000","20070131000000");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-4);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "An attempt was made to create an effective date that was before a previous committed effective date segment");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.14");
    System.out.println("Add an alias, CustID as key");
    System.out.println("Expected result -7 CustID not found");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",999100,"222100000000005","SUB2","20070101000000","20070101000000","20061231000000");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-7);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "An attempt was made to retrieve an account object, but the account object could not be found for the given account ID");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.15");
    System.out.println("Create Alias, using MSN as key");
    System.out.println("Expected result -11 MSN not found for date");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,custMSN3,xx,"222100000000005","SUB4","20070101000000","20070101000000","20010303090001");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-11);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "An attempt was made to modify an account object, but the account object could not be found for the given MSN and effective date");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.16");
    System.out.println("Add an alias, CustID as key");
    System.out.println("Expected result -17 SubID not found");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId2,"222100000000005","","20070101000000","20070101000000","20061231000000");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),-17);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "SubscriptionID string should not be null");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 3.17");
    System.out.println("Add an alias to Customer, Eff Date = 0");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,custMSN3,xx,"222100000000007","SUB1","20080101000000","20081231120001","0");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), custId3);
    
    System.out.println("Test 3.18");
    System.out.println("Add an existing alias to Customer 4 for a different validity, using CustID as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,"",custId4,"222100000000006","SUB1","20071001000000","20071031120001","");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), custId4);
  }    
}