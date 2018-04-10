package com.automation.drivers;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.CustomerManagement;
import com.automation.pageobjects.DefaultResources;
import com.automation.pageobjects.SmokeTest;
import com.hpe.base.Base;
import com.hpe.iot.api.base.Base_API;
import com.hpe.iot.api.utilities.JDBCUtils;
import com.hpe.iot.api.utilities.Tunneling;
import com.relevantcodes.extentreports.LogStatus;
@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_Smoke extends Base {

	SmokeTest smoke = new SmokeTest();
	DefaultResources def = new DefaultResources();
	CustomerManagement cust = new CustomerManagement();

	@BeforeSuite(groups = { "Smoke", "Critical_Regression"})
	public void start() throws Exception {
		setup();
		
		
		if(def.checkIfAcpAndAppExists()){
			
			login(prop.getProperty("username"), prop.getProperty("password"));
			def.CreateACP();
			def.Verify("Success");
			def.AddNewApplciation();
			def.Verify("Success");
			logout();
		}
	}
	
	@BeforeTest(groups = { "Smoke", "Critical_Regression"})
	public void connection_setup() throws IOException {
		try{
		if (Boolean.valueOf(Base_API.prop_api.getProperty("dbValidation"))) {
			
			Tunneling.dbserverTunnel();
			JDBCUtils.Connect();

		}

		}catch(Exception e){
			
			System.out.println("Tunneling and DB Setup Fail");
		}
		
	}
	
	
	
	@AfterSuite(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void clean() throws Exception {
		closeBrowser();
	}

	@AfterMethod(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void exit(ITestResult result) throws Exception {
		
		smoke.logout();
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 1, groups = { "Smoke","Critical_Regression" }, description = "Veirfy if Login with Iot Admin User is Successfull , ALM Id = 80520", enabled = true)
	public void TestCase_80520() throws Exception {

		prop.setProperty("tc_id", "80520");
		logger.assignCategory("Smoke");

		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "User Logged in");

	}
	
	@Test(priority = 2, groups = { "Smoke","Critical_Regression" }, description = "Verify Creating a New Customer , ALM id = 88256", enabled = true)
	public void CustMgmt_Create_Customer() throws Exception {
		// Test_User_Automation_1096
		
		/*login( "Test_User_Automation_1096",
		 "Password@1");*/
		logger.assignCategory("Smoke");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"),
				 prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Create Customer");
		cust.Create_Customer();

		logger.log(LogStatus.INFO, "Verifying if Customer Creation is Success");
		cust.Verify_Customer();

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 3, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = {
	"CustMgmt_Create_Customer" },description = "Verify Creating a Group under Customer , ALM Id = 80522", enabled = true)
	public void TestCase_80522() throws Exception {

		prop.setProperty("tc_id", "80522");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Entering New User Values");
		smoke.EnterGroupValues();

		logger.log(LogStatus.INFO, "Verifying New User");
		smoke.VerifyTenant();

	}
	


	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 4, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = {"CustMgmt_Create_Customer",
			"TestCase_80522" }, description = "Verify Creating a new user under customer->group , ALM Id = 80521", enabled = true)
	public void TestCase_80521() throws Exception {

		prop.setProperty("tc_id", "80521");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Creating New Group User ");
		smoke.EnterUserValues(false);

		logger.log(LogStatus.INFO, "Verifying New Group User User");
		smoke.VerifyNewUser();
		
		logger.log(LogStatus.INFO, "Creating New Customer User ");
		smoke.EnterUserValues(true);

		logger.log(LogStatus.INFO, "Verifying New Customer User User");
		smoke.VerifyNewUser();

	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 5, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = {
			"TestCase_80521" }, description = "Verify New User's Admin access , ALM Id = 80523", enabled = true)
	public void TestCase_80523() throws Exception {

		prop.setProperty("tc_id", "80523");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verifying DSM Admin Role");
		smoke.VerifyDSMAdminRole();

	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 6, groups = { "Smoke","Critical_Regression"}, dependsOnMethods = {
			"TestCase_80521" }, description = "Verify Container Upload , ALM Id = 80525", enabled = true)
	public void TestCase_80525() throws Exception {

		prop.setProperty("tc_id", "80525");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Uploading a XML file to Container");
		smoke.ContainerUpload("container.xml");

		logger.log(LogStatus.INFO, "Verify Container Upload");
		smoke.Verify("Success!");

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 7, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = {
			"TestCase_80521" }, description = "Verify Device Profile Upload , ALM Id = 80526", enabled = true)
	public void TestCase_80526() throws Exception {

		prop.setProperty("tc_id", "80526");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Uploading Device Profile");
		smoke.DeviceMgmtUpload("deviceprofile.xml",false);

		logger.log(LogStatus.INFO, "Verify Device Profile Upload");
		smoke.Verify("Success!");

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 8, groups = { "Smoke" ,"Critical_Regression"}, dependsOnMethods = { "TestCase_80521",
			"TestCase_80526" }, description = "Verify Create New Asset , ALM Id = 80527", enabled = true)
	public void TestCase_80527() throws Exception {

		prop.setProperty("tc_id", "80527");
		logger.assignCategory("Smoke");

		//obj.login("Test_User_Automation_6777", "Password@1");
		
		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Entering Asset Details");
		smoke.CreateNewAsset(true,true);

		logger.log(LogStatus.INFO, "Verifying if Asset is Added Successful");
		smoke.Verify("Success!");
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 9, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = { "TestCase_80521",
			"TestCase_80526" }, description = "Verify create new asset with Auto Provision , ALM Id = 80528", enabled = true)
	public void TestCase_80528() throws Exception {

		prop.setProperty("tc_id", "80528");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Enabling Auto Provision");
		smoke.CreateNewAsset(true,false);
		
		logger.log(LogStatus.INFO, "Verifying Asset is created  Successful");
		smoke.Verify("Success!");
		
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 10, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = {
			"TestCase_80521","TestCase_80527" }, description = "Verify Create New ACP , ALM Id = 80530", enabled = true)
	public void TestCase_80530() throws Exception {

		prop.setProperty("tc_id", "80530");
		logger.assignCategory("Smoke");

		//obj.login("Test_User_Automation_5517", "Password@1");
		
		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Manage Asset to get Nodelink");
		smoke.ManageAsset();

		logger.log(LogStatus.INFO, "Creating New ACP");
		smoke.CreateACP();

		logger.log(LogStatus.INFO, "Verifying if New ACP Created");
		smoke.Verify("Success!");

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 11, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = { "TestCase_80521",
			"TestCase_80530" }, description = "Verify Update ACP , ALM Id = 80531", enabled = true)
	public void TestCase_80531() throws Exception {

		prop.setProperty("tc_id", "80531");
		logger.assignCategory("Smoke");
		
		//obj.login("Test_User_Automation_5517", "Password@1");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Editing New ACP");
		smoke.EditACPRule();

		logger.log(LogStatus.INFO, "Verifying if  ACP is Updated");
		smoke.Verify("Success!");

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 12, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = {
			"TestCase_80521","TestCase_80530" }, description = "Verify Create New Application , ALM Id = 80532", enabled = true)
	public void TestCase_80532() throws Exception {

		prop.setProperty("tc_id", "80532");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Creating New Application");
		smoke.AddNewApplciation();

		logger.log(LogStatus.INFO, "Verifying if  New App is Created");
		smoke.Verify("Success!");
		
		logger.log(LogStatus.INFO, "Manage Application to get");
		smoke.Manage_App();
		

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 13, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = { "TestCase_80521",
			"TestCase_80526","TestCase_80530" }, description = "Verify Adding a policy to New Application , ALM Id = 80533", enabled = true)
	public void TestCase_80533() throws Exception {

		prop.setProperty("tc_id", "80533");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Editing New Application");
		smoke.EditNewApp();

		logger.log(LogStatus.INFO, "Verifying if  New Apolicy is Added");
		smoke.Verify("Success!");
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 14, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = { "TestCase_80521",
			"TestCase_80526" }, description = "Create New SLA", enabled = false)
	public void TestCase_80536() throws Exception {

		prop.setProperty("tc_id", "80536");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Creating New SLA");
		smoke.CreateSLA();
		

		logger.log(LogStatus.INFO, "Verifying if  New SLA is Added");
		smoke.Verify("SLA Added successfully! ");
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 15, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = { "TestCase_80521",
			"TestCase_80536" }, description = "Create New Scheduler", enabled = false)
	public void TestCase_80535() throws Exception {

		prop.setProperty("tc_id", "80535");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Creating New Scheduler");
		smoke.CreateNewScheduler();
		;

		logger.log(LogStatus.INFO, "Verifying if  New Scheduler is Added");
		smoke.Verify("Scheduler Added successfully!");
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 16, groups = { "Smoke","Critical_Regression" }, dependsOnMethods = { "TestCase_80521",
			"TestCase_80536" }, description = "Create New Display Config", enabled = false)
	public void TestCase_80537() throws Exception {

		prop.setProperty("tc_id", "80537");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Creating New Display Config");
		smoke.CreateDisplayConfig();

		logger.log(LogStatus.INFO, "Verifying if  New Display Config is Added");
		smoke.Verify("Success!");
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 17, groups = { "Smoke","Critical_Regression" },dependsOnMethods = {"CustMgmt_Create_Customer","TestCase_80521","TestCase_80533"}, description = "Verify Adding a Device Controller , ALM Id = 80538", enabled = true)
	public void TestCase_80538() throws Exception {

		prop.setProperty("tc_id", "80538");
		logger.assignCategory("Smoke");

		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Creating New Device Controller");
		smoke.CreateDeviceController();

		logger.log(LogStatus.INFO, "Verifying if  New Controller is Created");
		smoke.VerifyDeviceController("Success!");
	}
}
