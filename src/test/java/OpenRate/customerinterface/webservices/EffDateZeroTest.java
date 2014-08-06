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
public class EffDateZeroTest {
  
  public EffDateZeroTest() {
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
  public void testEffDate0() {
    int xx=0;
    long custId1;
    String custMSN1 = "10345011";

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

    MethodReturnTypeObj createERAMethodReturnTypeObj;
    CreateERAObject createERAObject = new CreateERAObject();

    MethodReturnTypeObj deleteERAMethodReturnTypeObj;
    DeleteERAObject deleteERAObject = new DeleteERAObject();

    MethodReturnTypeObj modifyERAMethodReturnTypeObj;
    ModifyERAObject modifyERAObject = new ModifyERAObject();

    System.out.println("========= EffDate0 Test =========\n");

    System.out.println("Create Customer");
    System.out.println("Expected result OK");

    createCustomerMethodReturnTypeObj = createCustomerObject.createCustomer(null,custMSN1, "20060201090001","20080201090001","");

    System.out.println(" EXIT ERROR :"+createCustomerMethodReturnTypeObj.getReturnCode());
    System.out.println(" MESSAGE    :"+createCustomerMethodReturnTypeObj.getMessage());
    System.out.println(" CUST ID    :"+createCustomerMethodReturnTypeObj.getClientID());
    System.out.println("-----------\n");

    // We can't know the id of the customer at this point, so just get it
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createCustomerMethodReturnTypeObj.getMessage(), "OK");    
    custId1 = createCustomerMethodReturnTypeObj.getClientID();
    
    System.out.println("Modify Customer, using MSN as key for modification");
    System.out.println("Expected result OK");

    modifyCustomerMethodReturnTypeObj = modifyCustomerObject.modifyCustomer(null,custMSN1,xx,"20060203090001","0");

    System.out.println(" EXIT ERROR :"+modifyCustomerMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyCustomerMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyCustomerMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyCustomerMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Add an alias, using MSN as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,custMSN1,xx,"222100980000020","SUB20","20080101000000","20081231120001","0");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(0,createAliasMethodReturnTypeObj.getReturnCode());
    Assert.assertEquals("OK",createAliasMethodReturnTypeObj.getMessage());
    Assert.assertEquals(custId1,createAliasMethodReturnTypeObj.getClientID());
    
    System.out.println("Add an alias, using MSN as key");
    System.out.println("Expected result OK");

    createAliasMethodReturnTypeObj = createAliasObject.createAlias(null,custMSN1,xx,"222100980000021","SUB20","20080101000000","20081231120001","0");

    System.out.println(" EXIT ERROR :"+createAliasMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createAliasMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createAliasMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createAliasMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createAliasMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createAliasMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Add a product to Customer, using MSN as key");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(null,custMSN1,xx,"P.20","SUB20","TEL","20080101000000","20081231120001","0");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Add a product to Customer, using CustID as key");
    System.out.println("Expected result OK");

    createProductMethodReturnTypeObj = createProductObject.createProduct(null,"",custId1,"P.21","SUB20","TEL","20080101000000","20081231120001","0");

    System.out.println(" EXIT ERROR :"+createProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Modify a product, using CustID as key");
    System.out.println("Expected result OK");

    modifyProductMethodReturnTypeObj = modifyProductObject.modifyProduct(null,"",custId1,"P.21","SUB20","20080101000000","20081231120032","0");

    System.out.println(" EXIT ERROR :"+modifyProductMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyProductMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyProductMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyProductMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyProductMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Add an ERA, using CustID as key");
    System.out.println("Expected result OK");

    createERAMethodReturnTypeObj = createERAObject.createERA(null,"",custId1,"ERAKey20","ERAValue20","0");

    System.out.println(" EXIT ERROR :"+createERAMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createERAMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createERAMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createERAMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createERAMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createERAMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Add an ERA, using MSN as key");
    System.out.println("Expected result OK");

    createERAMethodReturnTypeObj = createERAObject.createERA(null,custMSN1,xx,"ERAKey21","ERAValue21","0");

    System.out.println(" EXIT ERROR :"+createERAMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+createERAMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+createERAMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(createERAMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(createERAMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(createERAMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Modify an ERA, using CustID as key");
    System.out.println("Expected result OK");

    modifyERAMethodReturnTypeObj = modifyERAObject.modifyERA(null,"",custId1,"ERAKey20","ERAValue20New","0");

    System.out.println(" EXIT ERROR :"+modifyERAMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+modifyERAMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+modifyERAMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");

    Assert.assertEquals(modifyERAMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(modifyERAMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(modifyERAMethodReturnTypeObj.getClientID(), custId1);
    
    System.out.println("Delete an ERA, using MSN as key");
    System.out.println("Expected result OK");

    deleteERAMethodReturnTypeObj = deleteERAObject.deleteERA(null,custMSN1,xx,"ERAKey21","0");

    System.out.println(" EXIT ERROR :"+deleteERAMethodReturnTypeObj.getReturnCode()+"");
    System.out.println(" MESSAGE :"+deleteERAMethodReturnTypeObj.getMessage()+"");
    System.out.println(" CUST ID :"+deleteERAMethodReturnTypeObj.getClientID()+"");
    System.out.println("-----------\n");
    
    Assert.assertEquals(deleteERAMethodReturnTypeObj.getReturnCode(),0);
    Assert.assertEquals(deleteERAMethodReturnTypeObj.getMessage(), "OK");
    Assert.assertEquals(deleteERAMethodReturnTypeObj.getClientID(), custId1);    
  }
}