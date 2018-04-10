package com.automation.drivers;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.ACP;
import com.automation.pageobjects.ApplicationMgmt;
import com.automation.pageobjects.ApplicationVersion;
import com.automation.pageobjects.AssetMgmt;
import com.automation.pageobjects.CustomerManagement;
import com.automation.pageobjects.Dashboard;
import com.automation.pageobjects.SmokeTest;
import com.automation.pageobjects.Users;
import com.hpe.base.Base;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_CriticalRegression extends Base {

	ApplicationVersion version = new ApplicationVersion();
	Dashboard dash = new Dashboard();
	Users user = new Users();
	CustomerManagement cust = new CustomerManagement();
	AssetMgmt asset = new AssetMgmt();
	ApplicationMgmt app = new ApplicationMgmt();
	ACP acp = new ACP();
	SmokeTest smoke = new SmokeTest();
	

	/*@BeforeSuite(groups = { "Smoke", "Critical_Regression" })
	public void start() throws Exception {
		setup();
	}
	
	@AfterSuite(groups = { "Smoke", "Critical_Regression" })
	public void clean() throws Exception {
		closeBrowser();
	}*/

	@AfterMethod(groups = { "Smoke", "Critical_Regression"})
	public void exit(ITestResult result) throws Exception {
		
		smoke.logout();
		//closeBrowser();
	}
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 29, groups = { "Critical_Regression"},dependsOnMethods = {"com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, description = "Verify the DSM/DAV Version , ALM Id = 88228", enabled = true)
	public void ValidateVersion() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Verifying DSM Version");
		version.ValidateApplicationVersion();
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 30, groups = { "Critical_Regression" },dependsOnMethods = {"com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, description = "Validating Users Count on Dashboard , ALM Id = 88229", enabled = true)
	public void Dashboard_ValidateUsersCount() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Users Count");
		dash.VerifyUsersCount();

	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 31, groups = { "Critical_Regression" },dependsOnMethods = {"com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, description = "Validating Apps Count on Dashboard , ALM Id = 88230", enabled = true)
	public void Dashboard_ValidateAppCount() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Users Count");
		dash.VerifyAppCount();

	}
	/**
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 32, groups = {
			"Critical_Regression" },dependsOnMethods = {"com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, description = "Validating Active Devices Count on Dashboard , ALM Id = 88231", enabled = true)
	public void Dashboard_ValidateActiveDevicesCount() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Users Count");
		dash.VerifyActiveCount();

	}
	/**
	 * @author Jeevan
	 * 
	 **/
	@Test(priority = 33, groups = { "Critical_Regression" },dependsOnMethods = {"com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, description = "Validating Sensors Count on Dashboard , ALM Id = 88232", enabled = true)
	public void Dashboard_ValidateSensorsCount() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Users Count");
		dash.VerifySensorsCount();

	}
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 34, groups = { "Critical_Regression" },dependsOnMethods = {"com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, description = "Validating Gateway Count on Dashboard , ALM Id = 88233", enabled = true)
	public void Dashboard_ValidateGatewayCount() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Gateway Count");
		dash.VerifyGatewayCount();
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 35, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = {
			"Critical_Regression" }, description = "Validating User Details on Dashboard , ALM Id = 88236", enabled = true)
	public void Dashboard_ValidateUserDetails() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name") , prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Validating User Details");
		version.ValidateUserDetails();
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 36, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Validating My Group Shortcut , ALM Id = 88237", enabled = true)
	public void Dashboard_ValidateMyGroupShortcut() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating User Details");
		dash.VerifyMyGroupShortCut();
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 37, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Validating Key Import Shortcut , ALM Id = 88238", enabled = true)
	public void Dashboard_ValidateKeyImportShortcut() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating User Details");
		dash.VerifyKeyImportShortcut();
	}

	/*
	 * @author Jeevan
	 * Decomissioned
	 */
	@Test(priority = 38, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Validating Display Config Shortcut , ALM Id = 88239", enabled = false)
	public void Dashboard_ValidateDisplayConfigShortcut() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating User Details");
		dash.VerifyDisplayConfigShortcut();
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 39, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Validating Create User Shortcut , ALM Id = 88240", enabled = true)
	public void Dashboard_ValidateCreateUserShortcut() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating User Details");
		dash.VerifyCreateUserShortcut();
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 40, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Validating Global Search , ALM Id = 88241", enabled = true)
	public void Dashboard_VerifyGlobalSearch() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Global Search List");
		dash.GlobalSearch_VerifyTheList();

		logger.log(LogStatus.INFO, "Validating Global Search for User");
		dash.GlobalSearch_VerifyFunctionality("User");

		logger.log(LogStatus.INFO, "Validating Global Search for Customer");
		dash.GlobalSearch_VerifyFunctionality("Customer");

		logger.log(LogStatus.INFO, "Validating Global Search for Device");
		dash.GlobalSearch_VerifyFunctionalityForUser("asset");
	}

	/*
	 * @author Jeevan
	 * Decomissioned
	 * 
	 */
	@Test(priority = 41, groups = { "Critical_Regression" }, description = "Validating Tenants on Dashboard , ALM Id = 88244", enabled = false)
	public void Dashboard_ValidateTenantCount() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Tenant Count");
		dash.VerifyTenantCount();

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 42, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Customer Logo , ALM Id =88245", enabled = true)
	public void Dashboard_VerifyCustomerNameLink() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Customer Name Link");
		dash.VerifyCustomerNameLink();

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 43, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Pagination for Users Screen , ALM Id = 88248", enabled = true)
	public void Users_VerifyPagination() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Validating Customer Name Link");
		user.Verify_Pagination_Users();

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 44, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Critical_Regression" }, description = "Verify Updating a User details , ALM Id = 88249", enabled = true)
	public void Users_VerifyUserUpdate() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Updating User");
		user.User_Update();

		logger.log(LogStatus.INFO, "Verifying if User Update is Success");
		user.Verify_Update("successfully");

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 45, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Critical_Regression" }, description = "Verify Updating a Group details , ALM Id = 88251", enabled = true)
	public void Users_VerifyGroupUpdate() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Updating Tenant");
		user.Update_Group();

		logger.log(LogStatus.INFO, "Verifying if Tenant Update is Success");
		user.Verify_Update("successfully");

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 46, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Critical_Regression" }, description = "Verify Tenant Search , ALM Id = 88250", enabled = true)
	public void Users_VerifyGroupSearch() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Search Tenant");
		user.Search_Group();

		logger.log(LogStatus.INFO, "Verifying if Tenant Search is Success");
		user.Verify_Search_Tenant();

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 47, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Critical_Regression" }, description = "Verify Creating a new Role , ALM Id = 88252", enabled = true)
	public void Users_Create_Role() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(/* "Test_User_Automation_5969" */prop.getProperty("new_user_name"),
				/* "Password@1" */prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create Role");
		user.Create_Role("Device Management");

		logger.log(LogStatus.INFO, "Verifying if Role Creation is Success");
		//user.Verify_Update("successfully");

	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 48,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Critical_Regression" }, description = "Verify Creating a new Theme , ALM Id = 88253", enabled = true)
	public void Users_Create_Theme() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(/* "Test_User_Automation_5969" */prop.getProperty("new_user_name"),
				/* "Password@1" */prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Create Role");
		user.Create_Theme();

		logger.log(LogStatus.INFO, "Verifying if Role Creation is Success");
		user.Verify_Theme("Success");

	}

	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 49, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" },description = "Verify Search by Customer List , ALM Id = 88257", enabled = true)
	public void CustMgnt_Search_Customer_List() throws Exception {

		/*login( "Test_User_Automation_2680",
				 "Password@1");*/
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"),
				  prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Display Customer List");
		cust.Search_Customer(false,false);

	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 50, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" } , description = "Verify Search by Customer Name , ALM Id =88258", enabled = true)
	public void CustMgnt_Search_Customer_Name() throws Exception {

		/*login( "Test_User_Automation_1096",
				 "Password@1");*/
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"),
				  prop.getProperty("password"));
		
		
		logger.log(LogStatus.INFO, "Search Customer Name");
		cust.Search_Customer(true,false);

	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 51, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" },description = "Verify Search by Customer Hyperlink , ALM Id = 88259", enabled = true)
	public void CustMgnt_Search_Customer_Hyperlink() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"),
				 prop.getProperty("password"));
		
		/*login( "Test_User_Automation_1096",
				 "Password@1");*/
		
		logger.log(LogStatus.INFO, "Search Customer Hyperlink");
		cust.Search_Customer(false,true);

	}
	
	
	/*
	 * @author Jeevan
	 * Decommissioned
	 */
	@Test(priority = 52, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Creating a new Routing Rule", enabled = false)
	public void AppMgmt_Create_Rule() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(/* "Test_User_Automation_5969" */prop.getProperty("new_user_name"),
				/* "Password@1" */prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		app.Add_RoutingRule();
		
		logger.log(LogStatus.INFO, "VVerify Role");
		app.Verify_Role();

	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 53, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Searcing a existing ACP , ALM Id = 88295", enabled = true)
	public void Acp_Search() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(/* "Test_User_Automation_5969" */prop.getProperty("new_user_name"),
				/* "Password@1" */prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Search ACP");
		acp.Search_ACP();
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 54, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Updating a ACP , ALM Id = 88296", enabled = true)
	public void Acp_Update() throws Exception {
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(/* "Test_User_Automation_5969" */prop.getProperty("new_user_name"),
				/* "Password@1" */prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Update ACP");
		acp.Update_ACP();
		
		logger.log(LogStatus.INFO, "Verify Update ACP");
		acp.Verify_Update();
		
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	// Execute Theme Test Cases at the End
	@Test(priority = 55, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Applying a new Theme , ALM Id = 88254", enabled = true)
	public void Users_Apply_Theme() throws Exception {
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"),
				  prop.getProperty("new_password"));
		
		/*login( "Test_User_Automation_4355",
				 "Password@1");*/

		logger.log(LogStatus.INFO, "Apply Theme");
		user.Apply_Theme();

		logger.log(LogStatus.INFO, "Verifying if Apply Theme is Success");
		user.Verify_Theme("Success");

	}

}
