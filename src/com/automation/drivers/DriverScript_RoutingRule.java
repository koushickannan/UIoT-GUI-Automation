/**
 * 
 */
package com.automation.drivers;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.RoleMgmt;
import com.automation.pageobjects.RoleMgmt_AppEntity;
import com.automation.pageobjects.Routing_Rules;
import com.automation.pageobjects.SmokeTest;
import com.automation.pageobjects.Users;
import com.hpe.base.Base;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author aloksu
 *
 */
@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_RoutingRule extends Base{
	
	Users user = new Users();
	RoleMgmt roleMgmt = new RoleMgmt();
	SmokeTest smoke = new SmokeTest();
	Routing_Rules rout = new Routing_Rules();
	RoleMgmt_AppEntity appEntity = new RoleMgmt_AppEntity();
	
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
	
	// Routing Rules as Admin User

	@Test(priority = 401, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
	       description = "Check Admin is able to Create a Role with all the permissions under Routing Role, ALM Id=96353", enabled = false)
    public void Admin_AllPermissionRole_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","1,2","Routing_Rules_Role");
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "1,2", "Routing_Rules_Role");
    }
	
	@Test(priority = 402, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to create Routing Rule Role with only Add Routing Rules permission", enabled = false)
    public void Admin_AddRouting_Role() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","1","Routing_Rules_Role");
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "1", "Routing_Rules_Role");
    }
	
	@Test(priority = 403, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to access Add Routing Rule page", enabled = false)
    public void Admin_AddRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Opening Add Rule page");
		rout.OpenAddRulepage_Routing();
	}
	
	@Test(priority = 404, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to create Routing Rule Role with only Manage Routing Rules permission", enabled = false)
    public void Admin_ManageRouting_Role() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","2","Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "2", "Routing_Rules_Role");
    }
	
	@Test(priority = 405, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to access Manage Routing Rule page", enabled = false)
    public void Admin_ManageRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));	
		logger.log(LogStatus.INFO, "Opening Manage Rule page");
		rout.OpenManageRulePage_Routing();
		
	}
	
	@Test(priority = 406, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to access Applied Rules page", enabled = false)
    public void Admin_AppliedRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));	
		logger.log(LogStatus.INFO, "Opening Applied Rule page");
		rout.OpenAppliedRulePage_Routing();	
	}
	
	@Test(priority = 407, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to Create Role having all the permissions of both Application Entity and Routing Rules", enabled = false)
    public void Admin_DuelRole_RoutingRule() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.newCreate_combinedRole("Application Entity Management", "1,2,3,4,5,6,7,8,9,10,11,12,13", "Routing Rules", "1,2", "Combined_Role");
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Combined_Role");
    }
	
	@Test(priority = 408, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Admin clicking on Cancel button will redirect to Dashboard page", enabled = false)
    public void Admin_CancelAddRule_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Redirecting to Dashboard");
		rout.AddRule_Cancel("Auto_Rule");
	}
	
	@Test(priority = 409, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin can create new rule from Add Rule page", enabled = true)
        public void Admin_AddNewRule_Routing() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		
	}
	
	@Test(priority = 410, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to assign routing role to the application", enabled = true)
    public void Admin_ApplyRule_toApp() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Creating new Application");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Applying Rule to the Application");
		rout.AddRuleto_Application();	
		
	}
	
	@Test(priority = 411, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Admin new Rule can be created having multiple attribute rows", enabled = false)
    public void Admin_AddNewRule_MultipleRows() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Adding Rule with multiple attributes");
		rout.multipleFields_AddRule("Auto_Rule","Temp","Value");
		
	}
	
	@Test(priority = 412, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to delete Unassigned rule", enabled = false)
    public void Admin_Deleting_AddedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Deleting Added Rule");
		rout.Delete_UnassignedRule();
	}
	
	@Test(priority = 413, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is not able to delete Assigned rule", enabled = true)
    public void Admin_Deleting_AssignedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");

		logger.log(LogStatus.INFO, "Adding New Application");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add Rule to Application");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Try to add Added Rule");
		rout.Delete_AssignedRule();
	}
	
	@Test(priority = 414, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin User able to open open Conditions popup in Applied Rules page", enabled = false)
    public void Admin_OpenConditionpopup_AppliedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Log in as Admin");
		
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AppliedRule_Conditionpopup();
		logger.log(LogStatus.INFO, "Open Conditions popup");
	}
	
	@Test(priority = 415, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Admin is able to search for Rule by providing Rule Name", enabled = true)
    public void Admin_SearchforAddedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Opening Manage Rules page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Able to delete Added Rule");
		rout.Search_AddedRule();
	}
	
	@Test(priority = 416, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Admin rule added to Application is displayed in Applied Rule page", enabled = false)
    public void Admin_AddedRule_InAppliedPage() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		
		logger.log(LogStatus.INFO, "Add Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Open Applied Rule page");
		rout.OpenAppliedRulePage_Routing();
		
		logger.log(LogStatus.INFO, "Able to search for the application");
		rout.Search_AddedRule();
		
	}
	
	@Test(priority=417,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Admin can delete searched unassociated Rule", enabled=false)
    public void Admin_DeleteSearched_Rule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for adde Rule");
		rout.Search_AddedRule();
		logger.log(LogStatus.INFO, "Able to delete Added Rule");
		rout.Delete_UnassignedRule();
	}
	
	@Test(priority=418,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Admin can associate multiple rules to the application", enabled=false)
    public void Admin_Application_AddMultipleRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Able to associate multiple rules");
		rout.AddMultipleRulet_App();
	}
	
	@Test(priority=419,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Admin can delete created Data Propogation", enabled=false)
    public void Admin_Delete_DataPropogation() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Able to delete Added Rule"); 
		rout.Delete_AddedRule();	 
	}
	
	@Test(priority=420,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Admin can delete added Rule in Data Propogation page", enabled=false)
    public void Admin_DeleteRule_InDataPropogationPage() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add multiple rule to App");
		rout.AddMultipleRulet_App();
		logger.log(LogStatus.INFO, "Able to delete Added Rule"); 
		rout.DeleteRule_InDataProp();
	}
	
	@Test(priority=421,groups={ "Smoke", "Critical_Regression" },dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check as Admin applied rule cannot be edited", enabled=false)
    public void Admin_EditAppliedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Able to edit Applied rule"); 
		rout.Editing_AppliedRule("Rule already applied, cannot edit !"); 
	}
	
	@Test(priority=422,groups={ "Smoke", "Critical_Regression" },dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Admin user can edit unassigned rule existing attributes", enabled=false)
    public void Admin_EditExistingAttri_UnAssignedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Not able to edit attribute's of UnAssigned Rule"); 
		rout.Editing_UnAppliedRule("Temp", "Value");	 
	}
	
	@Test(priority=423,groups={ "Smoke", "Critical_Regression" },dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Admin user can add new attributes while editing unassigned rule", enabled=false)
    public void Admin_AddNewAttri_UnAssignedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Opening Add Rules page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Not able to add new attribute to UnAssigned Rule"); 
		rout.AddAttribute_EditRule("Temp", "Value");	 
	}
	
	// Routing Rules as Customer User
	
	@Test(priority = 424, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
	    description = "Check as Customer User add Routing Rule Role with all the permissions", enabled = false)
    public void Customer_AllPerRole_RoutingRule() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","1,2","Routing_Rules_Role");
		logger.log(LogStatus.INFO, "Verify Success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "1,2", "Routing_Rules_Role");
    }
	
	@Test(priority = 425, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Customer User add Routing Rule Role with only Add Routing Rules permissions", enabled = false)
    public void Customer_AddRouting_Role() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","1","Routing_Rules_Role");
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "1", "Routing_Rules_Role");
    }
	
	@Test(priority = 426, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Customer User Add Routing Rules page displayed to User having only Add Rule permission", enabled = false)
    public void Customer_AddRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Log in as Customer User");
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Routing_Rules_Role");
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Opening Add Rule page");
	    rout.OpenAddRulepage_Routing();
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Assign default Role");
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 427, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Customer User able to create Routing Rule Role with only Manage Routing Rules permissions", enabled = false)
    public void Customer_ManageRouting_Role() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","2","Routing_Rules_Role");
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "2", "Routing_Rules_Role");
    }
	
	@Test(priority = 428, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Customer User Manage Rule page displayed to User having only Manage Routing Rule permission", enabled = false)
    public void Customer_ManageRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Routing_Rules_Role");
		logout();
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Manage Rule page opening");
	}
	
	@Test(priority = 429, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Customer User Applied Rules page displayed to User having only Manage Routing Rule permission", enabled = false)
    public void Customer_AppliedRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Applied Rule page");
		rout.OpenAppliedRulePage_Routing();
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Assign Default Role");
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 430, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Customer User able to create Role having all the permissions of Application Entity and Routing Rules", enabled = false)
    public void Customer_DuelRole_RoutingRule() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.newCreate_combinedRole("Application Entity Management", "1,2,3,4,5,6,7,8,9,10,11,12,13", "Routing Rules", "1,2", "Combined_Role");
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Combined_Role");
    }
	
	@Test(priority = 431, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check as Customer User clicking on Cancel button will redirect to Dashboard page", enabled = false)
    public void Customer_CancelAddRule_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Combined_Role");
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Redirecting to Dashboard");
		rout.AddRule_Cancel("Auto_Rule");
	}
	
	@Test(priority = 432, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Customer User is able to add new Rule from Add Rule page", enabled = false)
    public void Customer_AddNewRule_Routing() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Adding new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
	}
	
	@Test(priority = 433, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Customer User is able to assign role to the application", enabled = false)
    public void Customer_ApplyRule_toApp() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Creating new Application");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Apply Rule to App");
		rout.AddRuleto_Application();	
	}
	
	@Test(priority = 434, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "As Customer User check the options present in Condition dropdown", enabled = false)
    public void Customer_ConditionDropdown_Options() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Check options present under Conditions dropdown");
		rout.ConditionDropdown_Options();
    }
	
	@Test(priority = 435, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Customer User not able to create new Rule by entering existing Rule Name", enabled = false)
    public void Customer_EnteringDuplicateRule_Name() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		
		logger.log(LogStatus.INFO, "Add new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.DuplicateRuleName_Creation("Auto_Rule");
	}

	@Test(priority = 436, groups = {"Smoke", "Critical_Regression"}, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="As Customer User check Alert message displayed when trying to add new Attribute without filling default row", enabled = false)
    public void Customer_CheckMessage_WithoutEnteringAttributeValue() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Check Alert message displayed");
		rout.AlertMessage_NewAttribute("Please fill all the details of rule condition");
	}
	
	@Test(priority = 437, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "As Customer User check new row will be added when clicked on Add New Attribute", enabled = false)
    public void Customer_FillDetails_AddNewAttributeCol() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Adding new Attribute row");
		rout.AddNewAttribute_Row();
	}
	
	@Test(priority = 438, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Customer User is not able to delete default row", enabled = false)
    public void Customer_DeleteDefault_Attribute() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Check the Alert message displayed");
		rout.AlertMessage_DeleteAttribute("Rule should have atleast one Condition !");
	}
	
	@Test(priority = 439, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Customer User is able to delete added Attribute row", enabled = false)
    public void Customer_DeleteAdded_Attribute() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Not able to delete new Row");
		rout.addAttributeRow_Delete("Auto_Rule","Temp","Value");
	}
	
	@Test(priority = 440, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/}, 
		description = "As Customer User check new Rule can be created having multiple attribute rows", enabled = false)
    public void Customer_AddNewRule_MultipleRows() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.multipleFields_AddRule("Auto_Rule","Temp","Value");
	}
	
	@Test(priority = 441, groups = { "Smoke", "Critical_Regression" }, description = "Check Customer User is able to delete Unassigned rule", enabled = false)
    public void Customer_Deleting_AddedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Delete Added Rule");
		rout.Delete_UnassignedRule();	
	}
	
	@Test(priority = 442, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Customer User is not able to delete Assigned rule", enabled = false)
    public void Customer_Deleting_AssignedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		
		logger.log(LogStatus.INFO, "Apply new Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Delete assigned Rule");
		rout.Delete_AssignedRule();
		logger.log(LogStatus.INFO, "Able to delete Added Rule");
	}
	
	@Test(priority = 443, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
	    description = "Check Customer User is able to check of details in Applied Rules page", enabled = false)
    public void Customer_OpenConditionpopup_AppliedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Open Applied Rule page");
		rout.AppliedRule_Conditionpopup();
	}
	
	@Test(priority = 444, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Customer User is able to search for Rule by providing Rule Name", enabled = false)
    public void Customer_SearchforAddedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for Added Rule");
		rout.Search_AddedRule();
	}
	
	@Test(priority = 445, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Customer User is able to search by providing Customer Id", enabled = false)
    public void Customer_SearchforCust() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for Rule");
		rout.Search_ByCustName();
	}
	
	@Test(priority = 446, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "As Customer User check rule applied to Application is displayed in Applied Rule page", enabled = false)
    public void Customer_AddedRule_InAppliedPage() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		
		logger.log(LogStatus.INFO, "Add new Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Open Applied Rule page");
		rout.OpenAppliedRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for Added Rule");
		rout.Search_AddedRule();
	}
	
	@Test(priority=447,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Customer User is able to reset the searched result by clicking on Reset", enabled=false)
    public void Customer_SearchRule_ClickReset() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for Added Rule");
		rout.Search_AddedRule();
		logger.log(LogStatus.INFO, "Able to reset searched Result");
		driver.findElement(By.xpath("//*[@class='btn grommetux-button-cancel']")).click();	
	}
	
	@Test(priority=448,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
	    description="Check Customer User can delete searched unassociated Rule", enabled=false)
    public void Customer_DeleteSearched_Rule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for added Rule");
		rout.Search_AddedRule();
		logger.log(LogStatus.INFO, "Try deleting Rule");
		rout.Delete_UnassignedRule();
	}
	
	@Test(priority=449,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Customer user can associate multiple rules to the application", enabled=false)
    public void Customer_Application_AddMultipleRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add Multiple Rule to App");
		rout.AddMultipleRulet_App();
	}
	
	@Test(priority=450,groups={ "Smoke", "Critical_Regression" },dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Customer user can delete created Data Propogation", enabled=false)
    public void Customer_Delete_DataPropogation() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add new Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Try deleting added Rule");
		rout.Delete_AddedRule();  
	}
	
	@Test(priority=451,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Customer User can delete added Rule in Data Propogation page", enabled=false)
    public void Customer_DeleteRule_InDataPropogationPage() throws Exception {
		try{
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add multiple Rule to App");
		rout.AddMultipleRulet_App();
		logger.log(LogStatus.INFO, "Deleting added Rule");
		rout.DeleteRule_InDataProp();
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Assigning Default Role");
        user.editUserWithRole("defaultRole"); 
		}catch(Exception e){
			logout();
			logger.log(LogStatus.INFO, "Log in as Admin");
			login(prop.getProperty("username"), prop.getProperty("password"));
			logger.log(LogStatus.INFO, "Assigning Default Role");
	        user.editUserWithRole("defaultRole");
	        throw(e);
		}
	}
	
	// Routing Rules as Group User
	
	@Test(priority = 452, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is able to Create a Role with all the permissions under Routing Role, ALM ID=96353", enabled = false)
    public void Group_AllPerRole_RoutingRule() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","1,2","Routing_Rules_Role");
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "1,2", "Routing_Rules_Role");
    }
	
	@Test(priority = 453, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Create Routing Rule Role with only Add Routing Rules permissions", enabled = false)
    public void Group_AddRouting_Role() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));	
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","1","Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "1", "Routing_Rules_Role");
    }
	
	@Test(priority = 454, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Add Routing Rules page displayed to User having only Add Rule permission", enabled = false)
    public void Group_AddRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editGroupUserWithRole("Routing_Rules_Role");
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Creating new Application");
		rout.OpenAddRulepage_Routing();
		
		logout();
		logger.log(LogStatus.INFO, "Login as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Assigning default Role");
		user.editGroupUserWithRole("defaultRole");
	}
	
	@Test(priority = 455, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Create Routing Rule Role with only Manage Routing Rules permissions", enabled = false)
    public void Group_ManageRouting_Role() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));	
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("Routing Rules","2","Routing_Rules_Role");
		
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Routing_Rules_Role");
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Routing Rules", "2", "Routing_Rules_Role");
    }
	
	@Test(priority = 456, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Manage Rule page displayed to User having only Managa Routing Rule permission", enabled = false)
    public void Group_ManageRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editGroupUserWithRole("Routing_Rules_Role");
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		
	}
	
	@Test(priority = 457, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Applied Rules page displayed to User having only Manage Routing Rule permission", enabled = false)
    public void Group_AppliedRuleDisplayed_Routing() throws Exception {
		logger.assignCategory("Routing Rule");
       
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Creating new Application");
		rout.OpenAppliedRulePage_Routing();
		logout();
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Assigning Default Role");
		user.editGroupUserWithRole("defaultRole");
	}
	
	@Test(priority = 458, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is able to Create Role having all the permissions of both Application Entity and Routing Rules, ALM ID=96359", enabled = false)
    public void Group_DuelRole_RoutingRule() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.newCreate_combinedRole("Application Entity Management", "1,2,3,4,5,6,7,8,9,10,11,12,13", "Routing Rules", "1,2", "Combined_Role");
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("Combined_Role");
    }
	
	@Test(priority = 459, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check clicking on Cancel button will redirect to Dashboard page", enabled = false)
    public void Group_CancelAddRule_Routing() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editGroupUserWithRole("Combined_Role");
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Try Adding new Rule");
		rout.AddRule_Cancel("Auto_Rule");
		logger.log(LogStatus.INFO, "Redirecting to Dashboard");
	}
	
	@Test(priority = 460, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check new Rule can be created from Add Rule page", enabled = false)
    public void Group_AddNewRule_Routing() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temprature","Value");
	}
	
	@Test(priority = 461, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Assigning role to the application", enabled = false)
    public void Group_ApplyRule_toApp() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Creating new Application");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add Rule to App");
		rout.AddRuleto_Application();	
	}
	
	@Test(priority = 462, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check the options present in Condition dropdown", enabled = false)
    public void Group_ConditionDropdown_Options() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Creating new Application");
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Open Conditions dropdown");
		rout.ConditionDropdown_Options();
    }
	
	@Test(priority = 463, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check User not able to create new Rule by entering existing Rule Name", enabled = false)
    public void Group_EnteringDuplicateRule_Name() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		
		logger.log(LogStatus.INFO, "Add new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.DuplicateRuleName_Creation("Auto_Rule");
	}
	
	@Test(priority = 464, groups = {"Smoke", "Critical_Regression"}, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Alert message displayed when trying to add new Attribute without filling default row", enabled = false)
    public void Group_CheckMessage_WithoutEnteringAttributeValue() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Check the Alert message displayed");
		rout.AlertMessage_NewAttribute("Please fill all the details of rule condition");
	}
	
	@Test(priority = 465, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check new row added when clicked on Add New Attribute", enabled = false)
    public void Group_FillDetails_AddNewAttributeCol() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Add new Attribute row");
		rout.AddNewAttribute_Row();
	}
	
	@Test(priority = 466, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is not able to delete default row", enabled = false)
    public void Group_DeleteDefault_Attribute() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Check Alert message");
		rout.AlertMessage_DeleteAttribute("Rule should have atleast one Condition !");
	}
	
	@Test(priority = 467, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is able to delete added Attribute row", enabled = false )
    public void Group_DeleteAdded_Attribute() throws Exception {
		logger.assignCategory("Routing Rule");

		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		rout.OpenAddRulepage_Routing();
		rout.addAttributeRow_Delete("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Not able to delete new Row");
	}
	
	@Test(priority = 468, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check new Rule can be created having multiple attribute rows", enabled = false)
    public void Group_AddNewRule_MultipleRows() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.multipleFields_AddRule("Auto_Rule","Temp","Value");
	}
	
	@Test(priority = 469, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is able to delete Unassigned rule", enabled = false)
    public void Group_Deleting_AddedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Try deleting Rule");
		rout.Delete_UnassignedRule();
	}
	
	@Test(priority = 470, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is not able to delete Assigned rule", enabled = false)
    public void Group_Deleting_AssignedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		
		logger.log(LogStatus.INFO, "Apply Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Try deleting Rule");
		rout.Delete_AssignedRule();
	}
	
	@Test(priority = 471, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is able to check of details in Applied Rules page", enabled = false)
    public void Group_OpenConditionpopup_AppliedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Open Applied Rule page");
		rout.AppliedRule_Conditionpopup();	
	}
	
	@Test(priority = 472, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is able to search by providing Rule name", enabled = false)
    public void Group_SearchforAddedRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for added Rule");
		rout.Search_AddedRule();
	}
	
	@Test(priority = 473, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check Group User is able to search by providing Customer Id", enabled = false)
    public void Group_SearchforCust() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for Rule");
		rout.Search_ByCustName();
	}
	
	@Test(priority=474,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Group User is able to reset the searched result by clicking on Reset", enabled=false)
    public void Group_SearchRule_ClickReset() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for added Rule");
		rout.Search_AddedRule();
		logger.log(LogStatus.INFO, "Click to Reset");
		driver.findElement(By.xpath("//*[@class='btn grommetux-button-cancel']")).click();
	}
	
	@Test(priority=475,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Group User can delete searched unassociated Rule", enabled=false)
    public void Group_DeleteSearched_Rule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Open Manage Rule page");
		rout.OpenManageRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for added Rule");
		rout.Search_AddedRule();
		logger.log(LogStatus.INFO, "Try deleting Rule");
		rout.Delete_UnassignedRule();
	}
	
	@Test(priority=476,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Group user can associate multiple rules to the application", enabled=false)
    public void Group_Application_AddMultipleRule() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Try adding multiple Rule to App");
		rout.AddMultipleRulet_App();
	}
	
	@Test(priority=477,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Group user can delete created Data Propogation", enabled=false)
    public void Group_Delete_DataPropogation() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Apply new Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Try deleting added Rule");
		rout.Delete_AddedRule();
	}
	
	@Test(priority = 478, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description = "Check rule added to Application is displayed in Applied Rule page", enabled = false)
    public void Group_AddedRule_InAppliedPage() throws Exception {
		logger.assignCategory("Routing Rule");
		
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Open Add Rule page");
		rout.OpenAddRulepage_Routing();
		logger.log(LogStatus.INFO, "Creating new Rule");
		rout.AddRule_Routing("Auto_Rule","Temp","Value");
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		
		logger.log(LogStatus.INFO, "Apply new Rule to App");
		rout.AddRuleto_Application();
		logger.log(LogStatus.INFO, "Open Applied Rule page");
		rout.OpenAppliedRulePage_Routing();
		logger.log(LogStatus.INFO, "Search for Added Rule");
		rout.Search_AddedRule();
	}
	
	@Test(priority=479,groups={ "Smoke", "Critical_Regression" }, dependsOnMethods = { /*"com.automation.drivers.DriverScript_Smoke.TestCase_80533"*/},
		description="Check Group User can delete added Rule in Data Propogation page", enabled=false)
    public void Group_DeleteRule_InDataPropogationPage() throws Exception {
		try{
		logger.assignCategory("Routing Rule");
	
		logger.log(LogStatus.INFO, "Log in as Group User");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Add new App");
		appEntity.Add_Application("app_name","IoT","HTTP");
		logger.log(LogStatus.INFO, "Add multiple Rule to App");
		rout.AddMultipleRulet_App();
		logger.log(LogStatus.INFO, "Try deleting added App");
		rout.DeleteRule_InDataProp();

		logout();
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Assigning default Role");
        user.editGroupUserWithRole("defaultRole");
        
	}catch(Exception e){
		
		logout();
		logger.log(LogStatus.INFO, "Log in as Admin");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Assigning default Role");
        user.editGroupUserWithRole("defaultRole");
        throw(e);
		
		}
	}
	
}	




