/**
 * 
 */
package com.automation.drivers;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.RoleMgmt;
import com.automation.pageobjects.RoleMgmt_AppEntity;
import com.automation.pageobjects.Routing_Rules;
import com.automation.pageobjects.Security_Filter;
import com.automation.pageobjects.SmokeTest;
import com.hpe.base.Base;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author aloksu
 *
 */
@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_Security extends Base {

	SmokeTest smoke = new SmokeTest();
	Security_Filter filter = new Security_Filter();
	RoleMgmt_AppEntity app = new RoleMgmt_AppEntity();
	Routing_Rules routing = new Routing_Rules();
	RoleMgmt role = new RoleMgmt();
	
	@BeforeSuite(groups = { "Smoke", "Critical_Regression" })
	public void start() throws Exception {
		setup();
	}

	@AfterSuite(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void clean() throws Exception {
		closeBrowser();
	}
	
	@AfterMethod(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void exit(ITestResult result) throws Exception {
		logout();
		/*if(result.getStatus()==ITestResult.FAILURE){
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
		logout();
		}*/	
    }
	
	//Customer User
	
	@Test(priority = 1, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		  description = "Check 2nd Customer User is not allowed to view Users Details page created by 1st Customer User", enabled = false)
	      public void ViewUser_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as Admin");
			login(prop.getProperty("username"), prop.getProperty("password"));
			
			logger.log(LogStatus.INFO, "Create Customer");
			filter.CreateCustomerAsAdmin_Security();
			
			logger.log(LogStatus.INFO, "Create 1st Customer User");
			filter.FirstCustUser_Security();
			
			logger.log(LogStatus.INFO, "Create Customer");
			filter.CreateCustomerAsAdmin_Security();
			
			logger.log(LogStatus.INFO, "Create 2nd Customer User");
			filter.SecondCustUser_Security();
			logout();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating new Customer User");
			filter.CreateUser_Security();
			
			logger.log(LogStatus.INFO, "View created User");
			filter.ViewUser_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 2, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to edit User created by 1st Customer User", enabled = false)
	      public void EditUser_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Edit created User");
			filter.EditUser_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 3, groups = { "Smoke", "Critical_Regression" }, 
			  description = "Check 2nd Customer User is not allowed to view My Group Details page of 1st Customer User", enabled = false)
	      public void GroupDetails_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open My Group Details page");
			filter.GroupDetails_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 4, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to edit User Profile created by 1st Customer User", enabled = false)
	      public void EditUserProfile_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new User Profile");
			filter.CreateUserProfile_Security();
			
			logger.log(LogStatus.INFO, "Editing User Profile");
			filter.EditUserProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 5, groups = { "Smoke", "Critical_Regression" }, 
				description = "Delete User Profile created by 1st Customer User", enabled = false)
	      public void DeleteUserProfile_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Delete created User Profile");
			filter.DeleteUserProf_Security();
		}
		
		@Test(priority = 6, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to edit Role created by 1st Customer User", enabled = false)
	      public void EditRole_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Role");
			role.createNewRole("Application Entity Management", "1,2", "Application_Entity_Management_Role");
			role.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
			
			logger.log(LogStatus.INFO, "Try editing created role");
			role.editcreatedRole("Application_Entity_Management_Role");
			
			logger.log(LogStatus.INFO, "Retrieve url");
			filter.retrieveUrl_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 7, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to view QoS Policy created by 1st Customer User", enabled = false)
	      public void ViewAddedQoS_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New QoS Policy");
			filter.AddQoSPolicy_Security("QOS_CLASS:01", "AutoTemp");
			
			logger.log(LogStatus.INFO, "View created QoS Policy");
			filter.ViewQoSPolicy_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 8, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to edit QoS Policy created by 1st Customer User", enabled = false)
	    public void EditAddedQoS_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New QoS Policy");
			filter.AddQoSPolicy_Security("QOS_CLASS:01", "AutoTemp");
			
			logger.log(LogStatus.INFO, "Edit created QoS Policy");
			filter.EditQoSPolicy_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 9, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to view Application created by 1st Customer User", enabled = false)
	      public void ViewAddedApplication_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New Application");
			app.Add_Application("app_name","IoT","HTTP");
			
			logger.log(LogStatus.INFO, "View created Application");
			filter.ViewApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 10, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to edit Application created by 1st Customer User", enabled = false)
	    public void EditApplication_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Edit created Application");
			filter.EditApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 11, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to view Resource Group created by 1st Customer User", enabled = false)
	    public void ViewAppResourceGroup_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View Create Resource Group");
			filter.CreateResource_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 12, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to view Subscriptions created by 1st Customer User", enabled = false)
	    public void ViewAppSubscription_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View Subscriptions");
			filter.SubscriptionApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 13, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to edit Routing Rule added by 1st Customer User", enabled = false)
	    public void EditRoutingRule_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open Add Rule page");
			routing.OpenAddRulepage_Routing();
			
			logger.log(LogStatus.INFO, "Add new Rule");
			routing.AddRule_Routing("Auto_Rule","Temp","Value");
			
			logger.log(LogStatus.INFO, "Edit added rule");
			filter.EditRouting_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 14, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to view Customer added by 1st Customer User", enabled = false)
	    public void ViewCustomer_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Customer");
			filter.CreateChildCustomer_Security();
			
			logger.log(LogStatus.INFO, "Click on Customer Name");
			filter.ViewCustomer_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 15, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to edit Customer added by 1st Customer User", enabled = false)
	    public void EditCustomer_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Customer");
			filter.CreateChildCustomer_Security();
			
			logger.log(LogStatus.INFO, "Editing Customer");
			filter.EditCreatedCustomer_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 16, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to view Group added by 1st Customer User", enabled = false)
	    public void ViewGroup_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Group");
			filter.CreateChildGroup_Security();
			
			logger.log(LogStatus.INFO, "Click on Group Name to open View Page");
			filter.ViewGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 17, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to edit Group added by 1st Customer User", enabled = false)
	    public void EditGroup_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Group");
			filter.CreateChildGroup_Security();
			
			logger.log(LogStatus.INFO, "Try editing the added Group");
			filter.EditGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 18, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer user is not able to view Device Manufacturer Profile created by 1st Customer user", enabled = false)
	    public void ViewDevMgmtProfile_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Device Manufacturer Profile");
			filter.AddDevMfgProfile_Security("deviceprofile.xml");
			
			logger.log(LogStatus.INFO, "Open View Device Profile page");
			filter.ViewDevMfgProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 19, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer user is not able to edit Device Manufacturer Profile created by 1st Customer user", enabled = false)
	    public void EditDevMgmtProfile_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open View Device Profile page");
			filter.EditDevMfgProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 20, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer user is not able to view Asset created by 1st Customer user", enabled = true)
	    public void ViewAddedAsset_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Adding new Asset");
			filter.AddNewAsset_Security();

			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.ViewAddedAsset_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 21, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer user is not able to edit Asset created by 1st Customer user", enabled = false)
	    public void EditAddedAsset_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.EditAddedAsset_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 22, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer User is not allowed to view Asset of 1st Customer User under Asset KPI", enabled = false)
	    public void ViewAssetKPI_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.ViewAssetKPI_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 23, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer user is not able to view ACP created by 1st Customer user", enabled = false)
	    public void ViewACP_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new ACP");
			filter.AddACP_Security();
			
			logger.log(LogStatus.INFO, "View added ACP");
			filter.ViewAddedACP_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 24, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer user is not able to edit ACP created by 1st Customer user", enabled = false)
	    public void EditACP_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View added ACP");
			filter.EditAddedACP_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 25, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer user is not able to view Address Pool created by 1st Customer user", enabled = false)
	    public void ViewAddPool_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Address Pool");
			filter.AddAddressPool_Security();
			
			logger.log(LogStatus.INFO, "View added Address Pool");
			filter.ViewAddedPool_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}

		@Test(priority = 26, groups = { "Smoke", "Critical_Regression" }, 
				description = "Deleting Address Pool created by 1st Customer User", enabled = false)
	    public void DeleteAddPool_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Delete added Address Pool");
			filter.DeleteAddedPool_Security();
		}
		
		@Test(priority = 27, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Customer user is not able to edit Address Pool created by 1st Customer user", enabled = false)
	    public void EditAddPool_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Address Pool");
			filter.AddAddressPool_Security();
			
			logger.log(LogStatus.INFO, "View added Address Pool");
			filter.EditAddedPool_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("customer_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}

		@Test(priority = 28, groups = { "Smoke", "Critical_Regression" }, 
				description = "Deleting Address Pool created by 1st Customer User", enabled = false)
	    public void DeleteAddPoolforEdit_CustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Delete added Address Pool");
			filter.DeleteAddedPool_Security();
		}
		
		//Group User
		
		@Test(priority = 29, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to view Users created by 1st Group User", enabled = false)
	      public void ViewUser_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as Admin");
			login(prop.getProperty("username"), prop.getProperty("password"));
			
			logger.log(LogStatus.INFO, "Create Customer");
			filter.CreateCustomerAsAdmin_Security();
			
			logger.log(LogStatus.INFO, "Create Group");
			filter.CreateGroup_Security();
			
			logger.log(LogStatus.INFO, "Create 1st Group User");
			filter.FrstGroupUser_Security();
			
			logger.log(LogStatus.INFO, "Create Customer");
			filter.CreateCustomerAsAdmin_Security();
			
			logger.log(LogStatus.INFO, "Create Group");
			filter.CreateGroup_Security();
			
			logger.log(LogStatus.INFO, "Create 2nd Customer User");
			filter.SecondGroupUser_Security();
			logout();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating new Customer User");
			filter.CreateUser_Security();
			
			logger.log(LogStatus.INFO, "View created User");
			filter.ViewUser_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority =30, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to edit User created by 1st Group User", enabled = false)
	      public void EditUser_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Edit created User");
			filter.EditUser_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 31, groups = { "Smoke", "Critical_Regression" }, 
			  description = "Check 2nd Group User is not allowed to view My Group Details page of 1st Group User", enabled = false)
	      public void GroupDetails_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open My Group Details page");
			filter.GroupDetails_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 32, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to edit User Profile created by 1st Group User", enabled = false)
	      public void EditUserProfile_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new User Profile");
			filter.CreateUserProfile_Security();
			
			logger.log(LogStatus.INFO, "Editing User Profile");
			filter.EditUserProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 33, groups = { "Smoke", "Critical_Regression" }, 
				description = "Delete User Profile created by 1st Group User", enabled = false)
	      public void DeleteUserProfile_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Delete created User Profile");
			filter.DeleteUserProf_Security();
		}
		
		@Test(priority = 34, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to edit Role created by 1st Group User", enabled = false)
	      public void EditRole_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Role");
			role.createNewRole("Application Entity Management", "1,2", "Application_Entity_Management_Role");
			role.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
			
			logger.log(LogStatus.INFO, "Try editing created role");
			role.editcreatedRole("Application_Entity_Management_Role");
			
			logger.log(LogStatus.INFO, "Retrieve url");
			filter.retrieveUrl_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 35, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to view QoS Policy created by 1st Group User", enabled = false)
	      public void ViewAddedQoS_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New QoS Policy");
			filter.AddQoSPolicy_Security("QOS_CLASS:01", "AutoTemp");
			
			logger.log(LogStatus.INFO, "View created QoS Policy");
			filter.ViewQoSPolicy_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 36, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to edit QoS Policy created by 1st Group User", enabled = false)
	      public void EditAddedQoS_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New QoS Policy");
			filter.AddQoSPolicy_Security("QOS_CLASS:01", "AutoTemp");
			
			logger.log(LogStatus.INFO, "Edit created QoS Policy");
			filter.EditQoSPolicy_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 37, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to view Application created by 1st Group User", enabled = false)
	      public void ViewAddedApplication_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New Application");
			app.Add_Application("app_name","IoT","HTTP");
			
			logger.log(LogStatus.INFO, "View created Application");
			filter.ViewApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 38, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to edit Application created by 1st Group User", enabled = false)
	    public void EditApplication_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Edit created Application");
			filter.EditApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 39, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to view Resource Group created by 1st Customer User", enabled = false)
	    public void ViewAppResourceGroup_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View Create Resource Group");
			filter.CreateResource_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 40, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to view Subscriptions created by 1st Group User", enabled = false)
	    public void ViewAppSubscription_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View Subscriptions");
			filter.SubscriptionApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 41, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to edit Routing Rule added by 1st Group User", enabled = false)
	    public void EditRoutingRule_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open Add Rule page");
			routing.OpenAddRulepage_Routing();
			
			logger.log(LogStatus.INFO, "Add new Rule");
			routing.AddRule_Routing("Auto_Rule","Temp","Value");
			
			logger.log(LogStatus.INFO, "Edit added rule");
			filter.EditRouting_Security();
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		/*
		@Test(priority = 14, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to view Customer added by 1st Group User", enabled = false)
	    public void ViewCustomer_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Customer");
			filter.CreateChildCustomer_Security();
			
			logger.log(LogStatus.INFO, "Click on Customer Name");
			filter.ViewCustomer_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 15, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to edit Customer added by 1st Group User", enabled = false)
	    public void EditCustomer_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Customer");
			filter.CreateChildCustomer_Security();
			
			logger.log(LogStatus.INFO, "Editing Customer");
			filter.EditCreatedCustomer_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		*/
		@Test(priority = 42, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to view Group added by 1st Group User", enabled = false)
	    public void ViewGroup_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Group");
			filter.CreateChildGroup_Security();
			
			logger.log(LogStatus.INFO, "Click on Group Name to open View Page");
			filter.ViewGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 43, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group User is not allowed to edit Group added by 1st Group User", enabled = false)
	    public void EditGroup_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Group");
			filter.CreateChildGroup_Security();
			
			logger.log(LogStatus.INFO, "Try editing the added Group");
			filter.EditGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 44, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to view Device Manufacturer Profile created by 1st Group user", enabled = false)
	    public void ViewDevMgmtProfile_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Device Manufacturer Profile");
			filter.AddDevMfgProfile_Security("deviceprofile.xml");
			
			logger.log(LogStatus.INFO, "Open View Device Profile page");
			filter.ViewDevMfgProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 45, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to edit Device Manufacturer Profile created by 1st Group user", enabled = false)
	    public void EditDevMgmtProfile_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open View Device Profile page");
			filter.EditDevMfgProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 46, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to view Asset created by 1st Group user", enabled = false)
	    public void ViewAddedAsset_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Adding new Asset");
			filter.AddAssetwithGroup_Security();

			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.ViewAddedAssetGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 47, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to edit Asset created by 1st Group user", enabled = false)
	    public void EditAddedAsset_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.EditAddedAssetGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 48, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to view Asset KPI created by 1st Group user", enabled = false)
	    public void ViewAssetKPI_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.ViewAssetKPIGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 49, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to view ACP created by 1st Group user", enabled = false)
	    public void ViewACP_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new ACP");
			filter.AddACP_Security();
			
			logger.log(LogStatus.INFO, "View added ACP");
			filter.ViewAddedACP_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 50, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to edit ACP created by 1st Group user", enabled = false)
	    public void EditACP_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View added ACP");
			filter.EditAddedACP_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 51, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to view Address Pool created by 1st Group user", enabled = false)
	    public void ViewAddPool_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Address Pool");
			filter.AddAddressPool_Security();
			
			logger.log(LogStatus.INFO, "View added Address Pool");
			filter.ViewAddedPool_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}

		@Test(priority = 52, groups = { "Smoke", "Critical_Regression" }, 
				description = "Deleting Address Pool created by 1st Group User", enabled = false)
	    public void DeleteAddPool_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Delete added Address Pool");
			filter.DeleteAddedPool_Security();
		}
		
		@Test(priority = 53, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check 2nd Group user is not able to edit Address Pool created by 1st Group user", enabled = false)
	    public void EditAddPool_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Address Pool");
			filter.AddAddressPool_Security();
			
			logger.log(LogStatus.INFO, "View added Address Pool");
			filter.EditAddedPool_Security();
			
			logger.log(LogStatus.INFO, "Log in as GroupUser2");
			login(securityProp.getProperty("group_User2"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}

		@Test(priority = 54, groups = { "Smoke", "Critical_Regression" }, 
				description = "Deleting Address Pool created by 1st Group User", enabled = false)
	    public void DeleteAddPoolforEdit_GroupUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as GroupUser1");
			login(securityProp.getProperty("group_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Delete added Address Pool");
			filter.DeleteAddedPool_Security();
		}
		
		//Parent-Child Customer User
		
		@Test(priority = 55, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to view Users created by Parent Customer User", enabled = false)
	      public void ViewUser_ChildCustomerUser() throws Exception { 
		
			logger.log(LogStatus.INFO, "Log in as 1st Customer User");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create child Customer");
			filter.CreateChildCustomer_Security();
			
			logger.log(LogStatus.INFO, "Create child Customer User");
			filter.ChildCustUser_Security();
			
			logger.log(LogStatus.INFO, "Create child Customer");
			filter.CreateChildCustomer_Security();
			
			logger.log(LogStatus.INFO, "Creating new Customer User");
			filter.CreateUser_Security();
			
			logger.log(LogStatus.INFO, "View created User");
			filter.ViewUser_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 56, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to edit User created by Parent Customer User", enabled = false)
	      public void EditUser_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Edit created User");
			filter.EditUser_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 57, groups = { "Smoke", "Critical_Regression" }, 
			  description = "Check Child Customer User is not allowed to view My Group Details page of Parent Customer User", enabled = false)
	      public void GroupDetails_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open My Group Details page");
			filter.GroupDetails_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 58, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to edit User Profile created by Parent Customer User", enabled = false)
	      public void EditUserProfile_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new User Profile");
			filter.CreateUserProfile_Security();
			
			logger.log(LogStatus.INFO, "Editing User Profile");
			filter.EditUserProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 59, groups = { "Smoke", "Critical_Regression" }, 
				description = "Delete User Profile created by 1st Customer User", enabled = false)
	      public void DeleteUserProfile_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Delete created User Profile");
			filter.DeleteUserProf_Security();
		}
		
		@Test(priority = 60, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to edit Role created by Parent Customer User", enabled = false)
	      public void EditRole_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Role");
			role.createNewRole("Application Entity Management", "1,2", "Application_Entity_Management_Role");
			role.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
			
			logger.log(LogStatus.INFO, "Try editing created role");
			role.editcreatedRole("Application_Entity_Management_Role");
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Retrieve url");
			filter.retrieveUrl_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 61, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to view QoS Policy created by Parent Customer User", enabled = false)
	      public void ViewAddedQoS_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New QoS Policy");
			filter.AddQoSPolicy_Security("QOS_CLASS:01", "AutoTemp");
			
			logger.log(LogStatus.INFO, "View created QoS Policy");
			filter.ViewQoSPolicy_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 62, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to edit QoS Policy created by Parent Customer User", enabled = false)
	    public void EditAddedQoS_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New QoS Policy");
			filter.AddQoSPolicy_Security("QOS_CLASS:01", "AutoTemp");
			
			logger.log(LogStatus.INFO, "Edit created QoS Policy");
			filter.EditQoSPolicy_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 63, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to view Application created by Parent Customer User", enabled = false)
	      public void ViewAddedApplication_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Creating New Application");
			app.Add_Application("app_name","IoT","HTTP");
			
			logger.log(LogStatus.INFO, "View created Application");
			filter.ViewApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 64, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to edit Application created by Parent Customer User", enabled = false)
	    public void EditApplication_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Edit created Application");
			filter.EditApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 65, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to view Resource Group created by Parent Customer User", enabled = false)
	    public void ViewAppResourceGroup_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View Create Resource Group");
			filter.CreateResource_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 66, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to view Subscriptions created by Parent Customer User", enabled = false)
	    public void ViewAppSubscription_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View Subscriptions");
			filter.SubscriptionApplication_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 67, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to edit Routing Rule added by Parent Customer User", enabled = false)
	    public void EditRoutingRule_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open Add Rule page");
			routing.OpenAddRulepage_Routing();
			
			logger.log(LogStatus.INFO, "Add new Rule");
			routing.AddRule_Routing("Auto_Rule","Temp","Value");
			
			logger.log(LogStatus.INFO, "Edit added rule");
			filter.EditRouting_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 68, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to view Customer added by Parent Customer User", enabled = false)
	    public void ViewCustomer_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Customer");
			filter.CreateChildCustomer_Security();
			
			logger.log(LogStatus.INFO, "Click on Customer Name");
			filter.ViewCustomer_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 69, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to edit Customer added by Parent Customer User", enabled = false)
	    public void EditCustomer_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Customer");
			filter.CreateChildCustomer_Security();
			
			logger.log(LogStatus.INFO, "Editing Customer");
			filter.EditCreatedCustomer_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 70, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to view Group added by Parent Customer User", enabled = false)
	    public void ViewGroup_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Group");
			filter.CreateChildGroup_Security();
			
			logger.log(LogStatus.INFO, "Click on Group Name to open View Page");
			filter.ViewGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 71, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer User is not allowed to edit Group added by Parent Customer User", enabled = false)
	    public void EditGroup_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Create new Group");
			filter.CreateChildGroup_Security();
			
			logger.log(LogStatus.INFO, "Try editing the added Group");
			filter.EditGroup_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();	
		}
		
		@Test(priority = 72, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to view Device Manufacturer Profile created by Parent Customer user", enabled = false)
	    public void ViewDevMgmtProfile_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Device Manufacturer Profile");
			filter.AddDevMfgProfile_Security("deviceprofile.xml");
			
			logger.log(LogStatus.INFO, "Open View Device Profile page");
			filter.ViewDevMfgProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 73, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to edit Device Manufacturer Profile created by Parent Customer user", enabled = false)
	    public void EditDevMgmtProfile_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Open View Device Profile page");
			filter.EditDevMfgProfile_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 74, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to view Asset created by Parent Customer user", enabled = false)
	    public void ViewAddedAsset_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Adding new Asset");
			filter.AddNewAsset_Security();

			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.ViewAddedAsset_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 75, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to edit Asset created by Parent Customer user", enabled = false)
	    public void EditAddedAsset_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.EditAddedAsset_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 76, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to view Asset KPI created by Parent Customer user", enabled = false)
	    public void ViewAssetKPI_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Click on Asset to view details");
			filter.ViewAssetKPI_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 77, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to view ACP created by Parent Customer user", enabled = false)
	    public void ViewACP_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new ACP");
			filter.AddACP_Security();
			
			logger.log(LogStatus.INFO, "View added ACP");
			filter.ViewAddedACP_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 78, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to edit ACP created by Parent Customer user", enabled = false)
	    public void EditACP_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "View added ACP");
			filter.EditAddedACP_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 79, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to view Address Pool created by Parent Customer user", enabled = false)
	    public void ViewAddPool_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Address Pool");
			filter.AddAddressPool_Security();
			
			logger.log(LogStatus.INFO, "View added Address Pool");
			filter.ViewAddedPool_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}

		@Test(priority = 80, groups = { "Smoke", "Critical_Regression" }, 
				description = "Deleting Address Pool created by 1st Customer User", enabled = false)
	    public void DeleteAddPool_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Delete added Address Pool");
			filter.DeleteAddedPool_Security();
		}
		
		@Test(priority = 81, groups = { "Smoke", "Critical_Regression" }, 
				description = "Check Child Customer user is not able to edit Address Pool created by Parent Customer user", enabled = false)
	    public void EditAddPool_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			
			logger.log(LogStatus.INFO, "Add new Address Pool");
			filter.AddAddressPool_Security();
			
			logger.log(LogStatus.INFO, "View added Address Pool");
			filter.EditAddedPool_Security();
			
			logger.log(LogStatus.INFO, "Log in as Child User");
			login(securityProp.getProperty("child_CustomerUser"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Enter the Url");
			filter.UrlVerification_Security();
		}
		
		@Test(priority = 82, groups = { "Smoke", "Critical_Regression" }, 
				description = "Deleting Address Pool created by 1st Customer User", enabled = false)
	    public void DeletePool_ChildCustomerUser() throws Exception { 
			
			logger.log(LogStatus.INFO, "Log in as CustomerUser1");
			login(securityProp.getProperty("customer_User1"), securityProp.getProperty("password_security"));
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "Delete added Address Pool");
			filter.DeleteAddedPool_Security();
		}
	}