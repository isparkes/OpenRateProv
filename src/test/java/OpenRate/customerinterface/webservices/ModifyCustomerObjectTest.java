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
public class ModifyCustomerObjectTest {
  
  public ModifyCustomerObjectTest() {
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
  public void testModifyCustomerObject() {
    int xx=0;
    String custMSN1 = "00222011";
    String custMSN2 = "00222012";
    long custId1;
    long custId2;

    System.out.println("Initialising...");
    MethodReturnTypeObj createCustomerMethodReturnTypeObj;
    CreateCustomerObject createCustomerObject = new CreateCustomerObject();

    MethodReturnTypeObj modifyCustomerMethodReturnTypeObj;
    ModifyCustomerObject modifyCustomerObject = new ModifyCustomerObject();

    System.out.println("========= Modify Customer Tests =========\n");

    System.out.println("Test 2.0");
    System.out.println("Create Customer 1");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN1, "20060201090001","20080201090001","20060201090001");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    // We can't know the id of the customer at this point, so just get it
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getReturnCode(), 0);
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getMessage(), "OK");
    custId1 = createCustomerMethodReturnTypeObj.getClientID();
    
    System.out.println("Test 2.0");
    System.out.println("Create Customer 2");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN2, "20060201090001","20080201090001","20060201090001");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    // We can't know the id of the customer at this point, so just get it
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getReturnCode(), 0);
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getMessage(), "OK");
    custId2 = createCustomerMethodReturnTypeObj.getClientID();
    
    System.out.println("Test 2.1");
    System.out.println("Modify Customer, using Cust ID as key for modification");
    System.out.println("Expected result OK");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,"",custId1,"20060203090001","20070101000000");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Test 2.2");
    System.out.println("Modify Customer again, using MSN as key");
    System.out.println("Expected result OK");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,xx,"20060303090001","20070201000000");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), custId2);
    
    System.out.println("Test 2.3");
    System.out.println("Modify Customer, using MSN as key");
    System.out.println("Expected result -3 Deactivation Date Format Incorrect");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,xx,"NotADate","");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),-3);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "DeactivationDate Date Format Incorrect");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 2.4");
    System.out.println("Modify Customer, using Cust ID as key");
    System.out.println("Expected result -4 Effective date too old");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,custId2,"20060303090001","20061231000000");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),-4);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "An attempt was made to create an effective date that was before a previous committed effective date segment");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 2.5");
    System.out.println("Modify Customer, using MSN as key");
    System.out.println("Expected result -4 Effective date too old");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,xx,"20060303090001","20070131000000");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),-4);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "An attempt was made to create an effective date that was before a previous committed effective date segment");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 2.6");
    System.out.println("Modify Customer, using Cust ID as key");
    System.out.println("Expected result -5 Effective Date Format Incorrect");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,3,"20060303090001","NotADate");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),-5);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "EffectiveDate Date Format Incorrect");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 2.7");
    System.out.println("Modify Customer, using MSN as key");
    System.out.println("Expected result -11 MSN not found for date");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,xx,"20060303090001","20010303090001");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),-11);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "An attempt was made to modify an account object, but the account object could not be found for the given MSN and effective date");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 2.8");
    System.out.println("Modify Customer, using CustID as key");
    System.out.println("Expected result -7 Cust ID not found");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,"",999100,"20060303090001","20010303090001");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),-7);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "An attempt was made to retrieve an account object, but the account object could not be found for the given account ID");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 2.9");
    System.out.println("Modify Customer 3, using MSN as key");
    System.out.println("Expected result -5  Effective Date Format Incorrect");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,xx,"20060303090001","NotADate");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),-5);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "EffectiveDate Date Format Incorrect");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 2.10");
    System.out.println("Modify Customer 3, using CustID as key, then using MSN as key");
    System.out.println("Expected result -7 CustID Not Found");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,999100,"20060303090001","20070202000000");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),-7);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "An attempt was made to retrieve an account object, but the account object could not be found for the given account ID");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), 0);
    
    System.out.println("Test 2.11");
    System.out.println("Modify Customer 3, Effective Date = 0");
    System.out.println("Expected result OK");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN2,xx,"20080303090001","0");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), custId2);
  }
}