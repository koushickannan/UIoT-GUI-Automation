package com.automation.drivers;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.ApplicationMgmt;
import com.automation.pageobjects.RoleMgmt;
import com.automation.pageobjects.RoleMgmtTopology;
import com.automation.pageobjects.RoleMgmt_AppEntity;
import com.automation.pageobjects.RoleMgmt_DCCPCM;
import com.automation.pageobjects.RoleMgmt_DeviceMgmt;
import com.automation.pageobjects.RoleMgmt_UserMgmt;
import com.automation.pageobjects.RoleMgmt_UserProfile;
import com.automation.pageobjects.SmokeTest;
import com.automation.pageobjects.Users;
import com.hpe.base.Base;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_RoleMgmt extends Base {

	RoleMgmt roleMgmt = new RoleMgmt();
	RoleMgmt_UserMgmt userMag = new RoleMgmt_UserMgmt();
	Users user = new Users();
	RoleMgmtTopology roleTop = new RoleMgmtTopology();
	RoleMgmt_UserProfile rr = new RoleMgmt_UserProfile();
	RoleMgmt_DCCPCM rolemgmtdc = new RoleMgmt_DCCPCM();
	SmokeTest smoke = new SmokeTest();
	RoleMgmt_AppEntity am = new RoleMgmt_AppEntity();
	ApplicationMgmt aem = new ApplicationMgmt();
	RoleMgmt_DeviceMgmt deviceMag = new RoleMgmt_DeviceMgmt();

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
		if(result.getStatus()==ITestResult.FAILURE){
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
		logout();
		}
		
	}

    @Test(priority = 200,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-01-P-Verifying View Permission  for Topology Management", enabled = true)
    public void CreateViewPermission_Topology() throws Exception {
    		logger.assignCategory("Role Management");

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           roleMgmt.createNewRole("Topology Management", "1", "Topology_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Topology_Management_Role");

           roleMgmt.verifyRolePermissions("Topology Management", "1", "Topology_Management_Role");

    }

    @Test(priority = 201,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-02-P-Creating User assigning the Role created and Checking the assigined permissions functionality to the user", enabled = true)
    public void VerifyViewPermission_Topology() throws Exception {
		logger.assignCategory("Role Management");

           String Expectedresult = "Asset Topology";

           login(prop.getProperty("username"), prop.getProperty("password"));

           user.editUserWithRole("Topology_Management_Role");

           logout();

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           // login("deletetop","new_password@1");

           String Actualresult = roleTop.CheckVibilityofComponent();

           _assert.contains(Actualresult, Expectedresult, "Verifying Topology Component Alone");
           
           logout();
           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("defaultRole");

    }

    @Test(priority = 202,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-03-P-Deleting Role which is having users associated", enabled = true)
    public void DeleteRoleWithUser_Topology() throws Exception {
		logger.assignCategory("Role Management");

           login(prop.getProperty("username"), prop.getProperty("password"));
           
           roleMgmt.createNewRole("Topology Management", "1", "Topology_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Topology_Management_Role");

           roleMgmt.verifyRolePermissions("Topology Management", "1", "Topology_Management_Role");
           
           roleMgmt.tempUser("Topology_Management_Role");

           roleTop.RemoveRoleVerify_withUser("Topology_Management_Role");
           
           logout();
           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("defaultRole");

    }

    @Test(priority = 203,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-04-P-Deleting Role which is not having users associated to it", enabled = true)
    public void DeleteRoleWithoutUser_Topology() throws Exception {
		logger.assignCategory("Role Management");

           login(prop.getProperty("username"), prop.getProperty("password"));
           
           roleMgmt.createNewRole("Topology Management", "1", "Topology_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Topology_Management_Role");

           roleMgmt.verifyRolePermissions("Topology Management", "1", "Topology_Management_Role");

           roleTop.RemoveRoleVerify_withoutUser("Topology_Management_Role");

    }

    @Test(priority = 204,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-05-P-Verifying select all permisssions without expanding the tree", enabled = true)
    public void SelectAllRoleWithoutExpandingTree_Topology() throws Exception {
		logger.assignCategory("Role Management");

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           roleMgmt.SelectAllRole("Topology Management", "Topology_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Topology_Management_Role");

           roleMgmt.verifyRolePermissions("Topology Management", "1", "Topology_Management_Role");

    }

    @Test(priority = 205,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-06-P-Verifying select all permisssions without expanding the tree", enabled = true)
    public void ViewTopologyPermissionAfter_SelectAll_Topology() throws Exception {
		logger.assignCategory("Role Management");

           String Expectedresult = "Asset Topology";

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           roleMgmt.SelectAllRole("Topology Management", "Topology_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Topology_Management_Role");

           roleMgmt.verifyRolePermissions("Topology Management", "1", "Topology_Management_Role");
           
           logout();
           
           login(prop.getProperty("username"), prop.getProperty("password"));
           
           user.editUserWithRole("Topology_Management_Role");

           logout();

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           //login("deletetop", "new_password@1");

           String Actualresult = roleTop.CheckVibilityofComponent();

           _assert.contains(Actualresult, Expectedresult, "Verifying Topology Component Alone");
           
           logout();
           
           login(prop.getProperty("username"), prop.getProperty("password"));
           
           user.editUserWithRole("defaultRole");

    }



    @Test(priority = 206,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-07-P-Verifying select all permisssions by expanding the permission tree ", enabled = true)
    public void SelectAllRoleWithExpandingTree_Topology() throws Exception {
		logger.assignCategory("Role Management");

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           roleMgmt.SelectAllRole_WithTree("Topology Management", "Topology_Management_Role");

           Thread.sleep(2000);

           roleMgmt.verifyCreateRoleSuccessMsg("Topology_Management_Role");

           roleMgmt.verifyRolePermissions("Topology Management", "1", "Topology_Management_Role");

    }
    @Test(priority = 207,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-01-P-Verifying Add ACP Permission  for Access Control Policy Management", enabled = true)
    public void CreateAddACPRole_ACP() throws Exception {
		logger.assignCategory("Role Management");

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           //login("simma","Password@1");
           
           roleMgmt.createNewRole("Access Control Policy Management", "1", "Access_Control_Policy_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Access_Control_Policy_Management_Role");

           roleMgmt.verifyRolePermissions("Access Control Policy Management", "1", "Access_Control_Policy_Management_Role");

    }


    @Test(priority = 208, groups = { "Smoke", "Critical_Regression" }, description = "TC-02-P-Creating User assigning the Add ACP Role created and Checking the ACP Module Visible functionality to the user", enabled = true, dependsOnMethods= {"CreateAddACPRole_ACP","com.automation.drivers.DriverScript_Smoke.TestCase_80533"})
    public void VerifyACPPermission_ACP() throws Exception {
		logger.assignCategory("Role Management");

           String Expectedresult = "Access Control Policy";

           login(prop.getProperty("username"), prop.getProperty("password"));
           
           user.editUserWithRole("Access_Control_Policy_Management_Role");

           logout();

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           // login("deletetop","new_password@1");

           String Actualresult = roleTop.CheckVibilityofComponent();

           _assert.contains(Actualresult, Expectedresult, "Verifying Topology Component Alone");
           
           logout();
           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("defaultRole");

    }

    @Test(priority = 209, groups = { "Smoke", "Critical_Regression" }, description = "TC-02-P-Creating User assigning the Add ACP Role created and Checking the assigined permissions Add ACP functionality to the user", enabled = true,dependsOnMethods= {"CreateAddACPRole_ACP","com.automation.drivers.DriverScript_Smoke.TestCase_80533"})
    public void VerifyAddACPPermission_ACP() throws Exception {
		logger.assignCategory("Role Management");

           boolean result = true;

           String Expectedresult = "Access Control Policy";

           login(prop.getProperty("username"), prop.getProperty("password"));
           
           user.editUserWithRole("Access_Control_Policy_Management_Role");

           logout();

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           //login("simma","Password@1");

           String Actualresult = roleTop.CheckVibilityofComponent();

           if (Actualresult.equals(Expectedresult)){

                  roleTop.CreateACP_RoleManagement();

                  result=true;

           }

           _assert.equals(result, "Verifying Add ACP Permission");
           
           logout();
           
           login(prop.getProperty("username"), prop.getProperty("password"));
           
           user.editUserWithRole("defaultRole");

    }
    
    
    @Test(priority = 210,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-03-P-Verifying ASSIGN_ACP  Permission  for Access Control Policy Management.", enabled = true)
    public void CreateAssignACPRole_ACP() throws Exception {
		logger.assignCategory("Role Management");

           //login("AutoCustUser9095", prop.getProperty("new_password"));
           
           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           //login("simma","Password@1");
           
           roleMgmt.createNewRole("Access Control Policy Management", "1,2", "Access_Control_Policy_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Access_Control_Policy_Management_Role");

           roleMgmt.verifyRolePermissions("Access Control Policy Management", "1,2", "Access_Control_Policy_Management_Role");

    }
    
    
    @Test(priority = 211, groups = { "Smoke", "Critical_Regression" }, description = "TC-04-P-Creating User assigning the ASSIGN_ACP Role created and Checking the assigined permissions ASSIGN_ACP functionality to the user", enabled = true,dependsOnMethods= {"CreateAssignACPRole_ACP","com.automation.drivers.DriverScript_Smoke.TestCase_80533"})
    public void VerifyAssignACPPermission_ACP() throws Exception {
		logger.assignCategory("Role Management");

           boolean result = true;

           String Expectedresult = "Access Control Policy";

           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("Access_Control_Policy_Management_Role");

           logout();

           //login("AutoCustUser9095", prop.getProperty("new_password"));
           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           //login("simma","Password@1");

           String Actualresult = roleTop.CheckVibilityofComponent();

           if (Actualresult.equals(Expectedresult)){

                  roleTop.CreateACP_RoleManagement_Options("12");

                  result=true;
           }
           _assert.equals(result, "Verifying Add ACP Permission");
           logout();
           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("defaultRole");

    }


    @Test(priority = 212,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-05-P-Verifying SEARCH_ACP  Permission  for Access Control Policy Management.", enabled = true)
    public void CreateSearchACPRole_ACP() throws Exception {
		logger.assignCategory("Role Management");

           //login("AutoCustUser9095", prop.getProperty("new_password"));
           
           //login("simma","Password@1");
           
           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           roleMgmt.createNewRole("Access Control Policy Management", "3", "Access_Control_Policy_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Access_Control_Policy_Management_Role");

           roleMgmt.verifyRolePermissions("Access Control Policy Management", "3", "Access_Control_Policy_Management_Role");
    }
    
    
    @Test(priority = 213, groups = { "Smoke", "Critical_Regression" }, description = "TC-06-P-Creating User assigning the SEARCH_ACP Role created and Checking the assigined permissions SEARCH_ACP functionality to the user", enabled = true,dependsOnMethods= {"CreateSearchACPRole_ACP","com.automation.drivers.DriverScript_Smoke.TestCase_80533"})
    public void VerifySearchACPPermission_ACP() throws Exception {
		logger.assignCategory("Role Management");

           boolean result = true;

           String Expectedresult = "Access Control Policy";

           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("Access_Control_Policy_Management_Role");

           logout();

           //login("AutoCustUser9095", prop.getProperty("new_password"));
           //login("deletetop","new_password@1");
           
           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           //login("simma","Password@1");

           String Actualresult = roleTop.CheckVibilityofComponent();

           if (Actualresult.equals(Expectedresult)){

                  roleTop.Search_ACP_Rule_Alone();

                  result=true;
           }
           _assert.equals(result, "Verifying Add ACP Permission");
           logout();
           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("defaultRole");

    }
    
    
    @Test(priority = 214,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-12-P-Deleting Role which is having users associated-ACP", enabled = true)
    public void DeleteRoleWithUser_ACP() throws Exception {
		logger.assignCategory("Role Management");

           login(prop.getProperty("username"), prop.getProperty("password"));
           
           roleMgmt.createNewRole("Access Control Policy Management", "1", "Access_Control_Policy_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Access_Control_Policy_Management_Role");

           roleMgmt.verifyRolePermissions("Access Control Policy Management", "1", "Access_Control_Policy_Management_Role");
           
           roleMgmt.tempUser("Access_Control_Policy_Management_Role");

           roleTop.RemoveRoleVerify_withUser("Access_Control_Policy_Management_Role");
           
           logout();
           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("defaultRole");


    }
    
    
    @Test(priority = 215,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-13-P-Deleting Role which is not having users associated to it", enabled = true)
    public void DeleteRoleWithoutUser_ACP() throws Exception {

		logger.assignCategory("Role Management");

    	   login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           //login("simma","Password@1");
           
           roleMgmt.createNewRole("Access Control Policy Management", "1", "Access_Control_Policy_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Access_Control_Policy_Management_Role");

           roleMgmt.verifyRolePermissions("Access Control Policy Management", "1", "Access_Control_Policy_Management_Role");

           roleTop.RemoveRoleVerify_withoutUser("Access_Control_Policy_Management_Role");

    }
    
    
    @Test(priority = 216,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-07-P-Verifying select all permisssions without expanding the tree", enabled = true)
    public void SelectAll_Permissions_WithoutTreeExpand_ACP() throws Exception {
		logger.assignCategory("Role Management");
           
           boolean result= true;

           String Expectedresult = "Access Control Policy";

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           
           roleMgmt.SelectAllRole("Access Control Policy", "Access_Control_Policy_Management_Role");

           roleMgmt.verifyCreateRoleSuccessMsg("Access_Control_Policy_Management_Role");

           roleMgmt.verifyRolePermissions("Access Control Policy Management", "1,2,3", "Access_Control_Policy_Management_Role");

           logout();
           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("Access_Control_Policy_Management_Role");

           logout();

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           //login("deletetop", "new_password@1");
           
           String Actualresult = roleTop.CheckVibilityofComponent();

           if (Actualresult.equals(Expectedresult)){
                  

                  roleTop.Search_ACP_Rule_Alone();
                  
                  roleTop.Add_ACP_Rule_Alone();

                  result=true;
           }
           _assert.equals(result, "Verifying Add ACP and Search ACP visibility");
           
           logout();
           login(prop.getProperty("username"), prop.getProperty("password"));
           user.editUserWithRole("defaultRole");

    }

    
    @Test(priority = 217,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, description = "TC-08-P-Verifying select all permisssions by expanding the permission tree  ", enabled = true)
    public void SelectAllPermisssionsWithTreeExpand_ACP() throws Exception {
		logger.assignCategory("Role Management");

           login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
           roleMgmt.SelectAllRole_WithTree("Access Control Policy", "Access_Control_Policy_Management_Role");

           Thread.sleep(2000);

           roleMgmt.verifyCreateRoleSuccessMsg("Access_Control_Policy_Management_Role");

           roleMgmt.verifyRolePermissions("Access Control Policy Management", "1,2,3", "Access_Control_Policy_Management_Role");

    }


	@Test(priority = 300,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-01-P-Verify create DC permission for Device Controller Management, ALM_ID : 96227", enabled = true)
	public void CreateDCRoleandVerify() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Device Controller Management", "1", "Device_Controller_Management_Role");

		roleMgmt.verifyCreateRoleSuccessMsg("Device_Controller_Management_Role");

		roleMgmt.verifyRolePermissions("Device Controller Management", "1", "Device_Controller_Management_Role");

	}

	@Test(priority = 301,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-02-P-Create User assigning the Role created and check the assigned permissions functionality to the user, ALM_ID : 96228", enabled = true)
	public void VerfiyCreateDCRoleFunctionality() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Device_Controller_Management_Role");

		logout();

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.checkTileDisplayed(2);

		rolemgmtdc.CreateDeviceController();
		rolemgmtdc.VerifyDC("Success");
		
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}

	@Test(priority = 302,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-03-P-Verifying select all permisssions without expanding the tree, ALM_ID : 96229", enabled = true)
	public void SelectAllRolePerWithoutExpanding_DC() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.SelectAllRole("Device Controller Management", "Device_Controller_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Device_Controller_Management_Role");
		roleMgmt.verifyRolePermissions("Device Controller Management", "1", "Device_Controller_Management_Role");

	}

	@Test(priority = 303,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-04-P-Verifying select all permisssions by expanding the permission tree, ALM_ID : 96230 ", enabled = true)
	public void SelectAllPermissionwithExpanding_DC() throws Exception {

		logger.assignCategory("Role Management");


		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));roleMgmt.createNewRole("Device Controller Management", "1", "Device_Controller_Management_Role");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Device Controller Management", "1", "Device_Controller_Management_Role");

		roleMgmt.verifyCreateRoleSuccessMsg("Device_Controller_Management_Role");
		roleMgmt.verifyRolePermissions("Device Controller Management", "1", "Device_Controller_Management_Role");

	}

	@Test(priority = 304,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-05-P-Deleting Role which is not having users associated to it, ALM_ID : 96231 ", enabled = true)
	public void DeleteUserWithoutRole_DC() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Device Controller Management", "1", "Device_Controller_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Device_Controller_Management_Role");
		roleMgmt.verifyRolePermissions("Device Controller Management", "1", "Device_Controller_Management_Role");

		rolemgmtdc.DeleteRole("Device_Controller_Management_Role");
		roleMgmt.verifyDeleteRoleSuccessMsg("Device_Controller_Management_Role");

	}

	@Test(priority = 305,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-06-P-Deleting Role which is having users associated to it, ALM_ID : 96232", enabled = true)
	public void DeleteUserWithRole_DC() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));

		roleMgmt.createNewRole("Device Controller Management", "1", "Device_Controller_Management_Role");

		roleMgmt.verifyCreateRoleSuccessMsg("Device_Controller_Management_Role");

		roleMgmt.verifyRolePermissions("Device Controller Management", "1", "Device_Controller_Management_Role");

		roleMgmt.tempUser("Device_Controller_Management_Role");

		roleTop.RemoveRoleVerify_withUser("Device_Controller_Management_Role");

		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}

	@Test(priority = 306,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-01-P-Verify Add Container profile for container profile management role, ALM_ID : 96266", enabled = true)
	public void AddCPRoleandVerify() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Container Profile Management", "1", "Container_Profile_Management_Role");

		roleMgmt.verifyCreateRoleSuccessMsg("Container_Profile_Management_Role");

		roleMgmt.verifyRolePermissions("Container Profile Management", "1", "Container_Profile_Management_Role");

	}

	@Test(priority = 307,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-02-P-Creating User assigning the Add Container profile Role created and check the assigned permissions functionality to the user-(CR:34833), ALM_ID : 96267", enabled = true)
	public void VerfiyAddCPRoleFunctionality() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Container_Profile_Management_Role");

		logout();

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.checkTileDisplayed(2);

		smoke.ContainerUpload("httpcp.xml");
		smoke.Verify("Success!");
		
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}

	@Test(priority = 308,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-03-P-Verify search Container profile for container profile management role, ALM_ID : 96268", enabled = true)
	public void SearchCPRoleandVerify() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Container Profile Management", "2", "Container_Profile_Management_Role");

		roleMgmt.verifyCreateRoleSuccessMsg("Container_Profile_Management_Role");

		roleMgmt.verifyRolePermissions("Container Profile Management", "2", "Container_Profile_Management_Role");

	}

	@Test(priority = 309,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-04-P-Creating User assigning the search container Role created and check the assigned permission functionality to the user-(CR:34836), ALM_ID : 96269", enabled = true)
	public void VerfiySearchCPRoleFunctionality() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Container_Profile_Management_Role");

		logout();

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.checkTileDisplayed(2);

		rolemgmtdc.SearchContainerProfile("Auto-Http");
		
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}

	@Test(priority = 310,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-05-P-Verifying select all permisssions without expanding the tree, ALM_ID : 96270", enabled = true)
	public void SelectAllRolePerWithoutExpanding_CP() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.SelectAllRole("Container Profile Management", "Container_Profile_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Container_Profile_Management_Role");
		roleMgmt.verifyRolePermissions("Container Profile Management", "1,2", "Container_Profile_Management_Role");

	}

	@Test(priority = 311,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-06-P-Verifying select all permisssions by expanding the permission tree, ALM_ID : 96271 ", enabled = true)
	public void SelectAllPermissionwithExpanding_CP() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Container Profile Management", "1,2", "Container_Profile_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Container_Profile_Management_Role");
		roleMgmt.verifyRolePermissions("Container Profile Management", "1,2", "Container_Profile_Management_Role");

	}

	@Test(priority = 312,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-09-P-Deleting Role which is not having users associated to it , ALM_ID : 96274", enabled = true)
	public void DeleteUserWithoutRole_CP() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Container Profile Management", "1,2", "Container_Profile_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Container_Profile_Management_Role");
		roleMgmt.verifyRolePermissions("Container Profile Management", "1,2", "Container_Profile_Management_Role");

		rolemgmtdc.DeleteRole("Container_Profile_Management_Role");
		roleMgmt.verifyDeleteRoleSuccessMsg("Container_Profile_Management_Role");

	}

	@Test(priority = 313,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-10-P-Deleting Role which is having users associated to it, ALM_ID : 96275", enabled = true)
	public void DeleteUserWithRole_CP() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));

		roleMgmt.createNewRole("Container Profile Management", "1", "Container_Profile_Management_Role");

		roleMgmt.verifyCreateRoleSuccessMsg("Container_Profile_Management_Role");

		roleMgmt.verifyRolePermissions("Container Profile Management", "1", "Container_Profile_Management_Role");

		roleMgmt.tempUser("Container_Profile_Management_Role");

		roleTop.RemoveRoleVerify_withUser("Container_Profile_Management_Role");

		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}
	
	@Test(priority = 314,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
			"Critical_Regression" }, description = "TC-01-P-Verify create group for User Management role, ALM_ID : 96039", enabled = true)
	public void CreateGroupRoleandVerify() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management", "3,4", "User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "3,4", "User_Management_Role");

	}

	@Test(priority = 315,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
			"Critical_Regression" }, description = "TC-02-P-Creating User assigning the create group Role created and check the assigned permission functionality to the user, ALM_ID : 96040", enabled = true)
	public void VerifyCreateGroupRoleFunctionality() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		
		user.editUserWithRole("User_Management_Role");

		logout();

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		roleMgmt.checkTileDisplayed(2);
		smoke.EnterGroupValues();
		smoke.Verify("Success");
		
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		user.editUserWithRole("defaultRole");

	}

	@Test(priority = 316,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
			"Critical_Regression" }, description = "TC-03-P-Verify view group for User Management role, ALM_ID : 96041", enabled = true)
	public void ViewGroupRoleandVerify() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management", "3,4", "User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "3,4", "User_Management_Role");

	}

	@Test(priority = 317,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
			"Critical_Regression" }, description = "TC-04-P-Creating User assigning the view group Role created and check assigned permission functionality to the user, ALM_ID : 96042", enabled = true)
	public void VerifyViewGroupRoleFunctionality() throws Exception {
		logger.assignCategory("Role Management");

		 login(prop.getProperty("username"), prop.getProperty("password")); 		
		user.editUserWithRole("User_Management_Role");

		logout();

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.checkTileDisplayed(2);
		smoke.EnterGroupValues();
		rolemgmtdc.SearchGroup();
		
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}
	
	@Test(priority = 318,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-17-P-Verifying select all permisssions without expanding the tree, ALM_ID : 96262", enabled = true)
	public void SelectAllRolePerWithoutExpanding_Cust() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.SelectAllRole("Customer Management", "Customer_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Customer_Management_Role");
		roleMgmt.verifyRolePermissions("Customer Management", "1,2,3,4", "Customer_Management_Role");

	}

	@Test(priority = 319,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-18-P-Verifying select all permisssions by expanding the permission tree, ALM_ID : 96263 ", enabled = true)
	public void SelectAllPermissionwithExpanding_Cust() throws Exception {

		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Customer Management", "1,2,3,4", "Customer_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Customer_Management_Role");
		roleMgmt.verifyRolePermissions("Customer Management", "1,2,3,4", "Customer_Management_Role");

	}
	
	@Test(priority = 320,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
	"Critical_Regression" }, description = "TC-05-P-Verify update group for User Management role, ALM_ID : 96043", enabled = true)
	public void UpdateGroupRoleandVerify() throws Exception {
		logger.assignCategory("Role Management");


		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management", "3,4,5", "User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "3,4,5", "User_Management_Role");

	}
	
	@Test(priority = 321,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",
			"Critical_Regression" }, description = "TC-06-P-Creating User assigning the update group Role created and check assigned permission functionality to the user, ALM_ID : 96044", enabled = true)
	public void VerifyUpdateGroupRoleFunctionality() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("User_Management_Role");

		logout();

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.checkTileDisplayed(2);
		smoke.EnterGroupValues();
		rolemgmtdc.UpdateGroup();
		smoke.Verify("Success");

		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}
	
// Part of Customer Management hence it is duplicated 
	@Test(priority = 322,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",

	"Critical_Regression" }, description = "TC-07-P-Verify delete group for User Management role, ALM_ID : 96045", enabled = false)

	public void DeleteGroupRoleandVerify() throws Exception {
		logger.assignCategory("Role Management");


		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management", "3,4,6", "User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "3,4,6", "User_Management_Role");

	}

	// Part of Customer Management hence it is duplicated 

	@Test(priority = 323,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke",

			"Critical_Regression" }, description = "TC-08-P-Creating User assigning the delete group Role created and check assigned permission functionality to the user, ALM_ID : 96046", enabled = false)

	public void VerifyDeleteGroupRoleFunctionality() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("User_Management_Role");

		logout();

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.checkTileDisplayed(2);
		smoke.EnterGroupValues();
		rolemgmtdc.DeleteGroup();
		smoke.Verify("Success");

		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}
	
	@Test(priority = 324,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "Verify Add User profile role", enabled = true)
	public void AddUserProfileRole() throws Exception{
		logger.assignCategory("Role Management");

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create New User Profile Role");
		roleMgmt.createNewRole("User Profile Module", "1,4", "User_Profile_Module_Role");

		logger.log(LogStatus.INFO, "Verify Role Creation Success Message");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Profile_Module_Role");

		logger.log(LogStatus.INFO, "Verify Permissions for Created Role");
		roleMgmt.verifyRolePermissions("User Profile Module", "1,4","User_Profile_Module_Role");
		
	}
	
	@Test(priority = 325,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "Assign Add User Profile role to user and verify", enabled = true)
	public void AssignAddUserProfileRoleAndVerify() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.log(LogStatus.INFO, "Log in as DSM Admin User");
		login(prop.getProperty("username"), prop.getProperty("password"));	
		
		logger.log(LogStatus.INFO, "Edit Customer User with Role");
		user.editUserWithRole("User_Profile_Module_Role");

		logger.log(LogStatus.INFO, "Logout");
		logout();

		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Create New User Profile");
		rr.createUserProfile(roleProp.getProperty("User_Profile_Module_Role"));
		logger.log(LogStatus.INFO, "Verify New User Profile Creation Success Message");
		smoke.Verify("Success!");
		
		logger.log(LogStatus.INFO, "Logout");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
		
	}
	
	@Test(priority = 326,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "Verify Edit User Profile role", enabled = true)
	public void EditUserProfileRole() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));	
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("User Profile Module","2,4","User_Profile_Module_Role");
		
		logger.log(LogStatus.INFO, "Verify Success");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Profile_Module_Role");
		

	}
	
	@Test(priority = 327,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "Assign Edit User Profile role to user and verify", enabled = true)
	public void AssignEditUserProfileRoleAndVerify() throws Exception{
		logger.assignCategory("Role Management");

		logger.log(LogStatus.INFO, "Log in as Admin User");
		login(prop.getProperty("username"), prop.getProperty("password"));		
		
		logger.log(LogStatus.INFO, "Edit User with Custom Role");
		user.editUserWithRole("User_Profile_Module_Role");
		
		logger.log(LogStatus.INFO, "Logout");
		logout();
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "View User Profile");
		rr.viewUserProfile();
		logger.log(LogStatus.INFO, "Edit User Profile");
		rr.editUserProfile();
		
		logger.log(LogStatus.INFO, "Verify Success Message");
		smoke.Verify("Success!");
		
		logger.log(LogStatus.INFO, "Logout");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 328,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "Verify View User profile role", enabled = true)
	public void ViewUserProfileRole() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.log(LogStatus.INFO, "Log in as Customer User");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create New Role");
		roleMgmt.createNewRole("User Profile Module","4","User_Profile_Module_Role");
		
		logger.log(LogStatus.INFO, "Verify Success Message");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Profile_Module_Role");
		
		logger.log(LogStatus.INFO, "Verify Role Permissions");
		roleMgmt.verifyRolePermissions("User Profile Module", "4","User_Profile_Module_Role");
		
	}
	
	@Test(priority = 329,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "Assign View User Profile role to user and verify", enabled = true)
	public void AssignViewUserProfileRoleAndVerify() throws Exception{
		
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));		
		user.editUserWithRole("User_Profile_Module_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));		
		roleMgmt.checkTileDisplayed(2);
		
		rr.viewUserProfile();
		
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
		
	}
	
	@Test(priority = 330,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "Verify Delete User profile role", enabled = true)
	public void DeleteUserProfileRole() throws Exception{
		logger.assignCategory("Role Management");
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));		
		roleMgmt.createNewRole("User Profile Module","3,4","User_Profile_Module_Role");
		
		roleMgmt.verifyCreateRoleSuccessMsg("User_Profile_Module_Role");
		
		roleMgmt.verifyRolePermissions("User Profile Module", "3,4","User_Profile_Module_Role");
		
	}
	
	@Test(priority = 331,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "Assign Delete User Profile role to user and verify", enabled = true)
	public void AssignDeleteUserProfileRoleAndVerify() throws Exception{
		logger.assignCategory("Role Management");
		
		login(prop.getProperty("username"), prop.getProperty("password"));	
		user.editUserWithRole("User_Profile_Module_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));		
		roleMgmt.checkTileDisplayed(2);
		
		rr.viewUserProfile();
		
		rr.deleteUserProfile();
		
		smoke.Verify("Success");
		
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
		
	}
	
	@Test(priority = 332,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, 
	        description = "TC-003-P-Check role creation for Application Entity Management without expanding the tree, ALM Id = 96109", enabled = true)
	
    public void SelectAllRolePerWithoutExpanding_AE() throws Exception {
		logger.assignCategory("Role Management");

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
        login(prop.getProperty("username"), prop.getProperty("password"));
        
        logger.log(LogStatus.INFO, "Check arrow mark");
        roleMgmt.SelectAllRole("Application Entity Management", "Application_Entity_Management_Role");
        
        roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
        
        roleMgmt.verifyRolePermissions("Application Entity Management", "1,2", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 333,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, 
			description = "TC-007-P-Check role creation for Application Entity Management after expanding the tree, ALM Id = 96113", enabled = true)
	public void ApplicationEntity_allPermissions() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "1,2,3,4,5,6,7,8,9,10,11,12,13", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "1,2,3,4,5,6,7,8,9,10,11,12,13", "Application_Entity_Management_Role");
		
	}
	
	@Test(priority = 334,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
            description = "TC-010-P-Create and verify Role creation with Create Application permission, ALM Id = 96116", enabled = true)
	public void AppEntity_CreateApp() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "1,2", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "1,2", "Application_Entity_Management_Role");
	
	}
	
	@Test(priority = 335,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-011-P-Check User assigned with Create Application role is able to create an Application, ALM Id = 96117 ", enabled = true)
	public void AppEntity_CreateAppUse() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Application_Entity_Management_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Creating new Application");
		am.Add_Application("app_name","IoT","HTTP");
		
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
		
	}
	
	@Test(priority = 336,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-012-P-Create and verify Role creation with Search Application permission, ALM Id=96118", enabled = true)
	public void AppEntity_SearchApp() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "1,2", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "1,2", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 337,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-013-P-Check User assigned with Search Application role is able to search for an Application, ALM Id=96119", enabled = true)
	public void AppEntity_SearchAppUse() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Application_Entity_Management_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Search for Application");
		am.Search_Application("app_name_521");	
		
        logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 338,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-014-P-Create and verify Role creation with Update Application permission, ALM Id-96120", enabled = true)
	public void AppEntity_UpdateApp() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "1,2,3", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "1,2,3", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 339,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-015-P-Check User assigned with Update Application role is able to update application, ALM Id=96121", enabled = true)
	public void AppEntity_UpdateAppUse() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Application_Entity_Management_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Edit Application");  
		am.Update_Application();
		
        logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 340,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-016-P-Create and verify Role creation with Delete Application permission, ALM Id=96122", enabled = true)
	public void AppEntity_DeleteApp() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "2,4", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "2,4", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 341,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-017-P-Check User assigned with Delete Application role is able to delete application, ALM Id=96123", enabled = true)
	public void AppEntity_DeleteAppUse() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Application_Entity_Management_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Delete Application");  
		am.Delete_Application();
		
        logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 342,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-018-P-Create and verify Role creation with View Application permission, ALM Id=96124", enabled = true)
	public void AppEntity_ViewApp() throws Exception{
		logger.assignCategory("Role Management");
		
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "2,5", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "2,5", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 343,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-019-P-Check User assigned with Update Application role is able to update application, ALM Id=96125", enabled = true)
	public void AppEntity_ViewAppUse() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Application_Entity_Management_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "View Application");  
		am.View_Application("View Application");
        
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 344,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-020-P-Create and verify Role creation with Associate New Rule permission, ALM Id=96126", enabled = true)
	public void AppEntity_NewRule() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "6", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "6", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 345,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-021-P-Create and verify Role creation with Create Data Propogation permission, ALM Id=96127", enabled = true)
	public void AppEntity_DataProp() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "2,7", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "2,7", "Application_Entity_Management_Role");
	}	
	
	@Test(priority = 346,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
			description = "TC-022-P-Check User assigned with Data Propogation role is able to create Kafka Topic, ALM Id=96128", enabled = true)
	public void AppEntity_DataPropUse() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Application_Entity_Management_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Data Propagation");  
		am.Data_Propagation("New01","Test01","STRING");
	 
        logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 347,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
	        description = "TC-023-P-Create and verify Role creation with Add Subscription permission, ALM Id=96129", enabled = true)
	public void AppEntity_AddSubs() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "2,8", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "2,8", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 348,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
	        description = "TC-024-P-Check User assigned with Add Subscription role is able to add a subscription, 96130", enabled = true)
	
	public void AppEntity_AddSubsUse() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Edit User with created Role");
		user.editUserWithRole("Application_Entity_Management_Role");
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Data Propagation");  
		aem.addSubscriptionToApplication(assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("MQ_asset_resourceID"), "commands");
		Thread.sleep(3000);
		smoke.Verify("Success");
        
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
	}
	
	@Test(priority = 349,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
	        description = "TC-025-P-Create and verify Role creation with Delete Subscription permission, ALM Id=96131", enabled = true)
	public void AppEntity_DeleteSubs() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "2,8,9", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "2,8,9", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 350,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
	           description = "TC-027-P-Create and verify Role creation with Create Resource Group permission, ALM Id=96133", enabled = true)
	public void AppEntity_CreateResource_Group() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "10", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "10", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 351,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
	           description = "TC-029-P-Create and verify Role creation with View Resource Group permission, ALM Id=96135", enabled = true)
	public void AppEntity_VIewResource_Group() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "11", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "11", "Application_Entity_Management_Role");
		
	}
	
	@Test(priority = 352,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
	           description = "TC-031-P-Create and verify Role creation with Update Resource Group permission, ALM Id=96137", enabled = true)
	public void AppEntity_UpdateResource_Group() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "11,12", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "11,12", "Application_Entity_Management_Role");
	}
	
	@Test(priority = 353,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, 
	        description = "TC-033-P-Create and verify Role creation with Delete Resource Group permission, ALM Id=96139", enabled = true)
	public void AppEntity_DeleteResource_Group() throws Exception{
		
		logger.assignCategory("Role Management");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Role");
		roleMgmt.createNewRole("Application Entity Management", "11,13", "Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify Role creation success message");
		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
		
		logger.log(LogStatus.INFO, "Verify created Role permissions");
		roleMgmt.verifyRolePermissions("Application Entity Management", "11,13", "Application_Entity_Management_Role");
		
	}
	
	@Test(priority = 354, groups = { "Smoke", "Critical_Regression" }, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},
			description = "TC-035-P-Deleting Role which is having users associated, ALM Id=96142", enabled = true)
	public void DeleteRoleWithUser_AE() throws Exception {
		logger.assignCategory("Role Management");

				login(prop.getProperty("username"), prop.getProperty("password"));
				roleMgmt.createNewRole("Application Entity Management", "1,2", "Application_Entity_Management_Role");
				roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");
				roleMgmt.verifyRolePermissions("Application Entity Management", "1,2", "Application_Entity_Management_Role");

				roleMgmt.tempUser("Application_Entity_Management_Role");
				roleTop.RemoveRoleVerify_withUser("Application_Entity_Management_Role");
				logout();
				
				login(prop.getProperty("username"), prop.getProperty("password"));
				user.editUserWithRole("defaultRole");
	 }
	
	@Test(priority = 355,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke", "Critical_Regression" }, 
			description = "TC-036-P-Deleting Role which is not having users associated to it, ALM Id=96141", enabled = true)
	public void DeleteRoleWithoutUser_AE() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Application Entity Management", "1,2", "Application_Entity_Management_Role");

		roleMgmt.verifyCreateRoleSuccessMsg("Application_Entity_Management_Role");

		roleMgmt.verifyRolePermissions("Application Entity Management", "1,2", "Application_Entity_Management_Role");

		roleTop.RemoveRoleVerify_withoutUser("Application_Entity_Management_Role");

	}


	@Test(priority = 356,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, 
			description = "96081: TC-03-P-Verifying  Search accert  permission for device Mangement.", enabled = true)
	public void CreateSearchAssetPermission_DeviceManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Device Management","2","Device_ManagementRole");
		roleMgmt.verifyCreateRoleSuccessMsg("Device_ManagementRole");
		roleMgmt.verifyRolePermissions("Device Management", "2", "Device_ManagementRole");
		}

	@Test(priority = 357,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, 
			description = "96082: TC-04-P-Creating User with search Asset Role  and checking the assigined permission functionality  for that user . ", enabled = true)
	public void VerifysearchAssetPermission_DeviceManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Device_ManagementRole");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		deviceMag.searchDeviceVerification("NewAsset");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}


	@Test(priority = 357,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, 
			description = "96087: TC-09-P-Verifying  Update asset  permission for device Mangement.", enabled = true)
	public void CreateupdateDevicePermission_DeviceManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Device Management","2,5","Device_ManagementRole");
		roleMgmt.verifyCreateRoleSuccessMsg("Device_ManagementRole");
		roleMgmt.verifyRolePermissions("Device Management", "2,5", "Device_ManagementRole");
		}

	@Test(priority = 358,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" },
			description = "96088 : TC-10-P-Creating User with update asset role  and checking the assigined permission functionality  for that user . ", enabled = true)
	public void VerifyupdateAssetPermission_DeviceManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Device_ManagementRole");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		deviceMag.editAssetVerification("Edit Device", "NewAsset");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}
	

//.. Group Management now it is part of user management
	
	/*Now it is part of customer management Hence changed  method from "CreateGroupPermission_UserManagement" to "CreateGroupPermission_CustomerManagement"*/
	
	@Test(priority = 359,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96039: TC-05-P-Verifying   create group  permission for user Mangement.", enabled = true)
	public void CreateGroupPermission_CustomerManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Customer Management","1,2","Customer_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Customer_Management_Role");
		roleMgmt.verifyRolePermissions("Customer Management", "1,2", "Customer_Management_Role");
		}
	/*Now it is part of customer management Hence changed  method from "VerifyViewGroupPermission_UserManagement" to "VerifyViewGroupPermission_CustomerManagement"*/

	@Test(priority = 360,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96040: TC-06-P-Creating User with create group role  and checking the assigined permission functionality  for  that user . ", enabled = true)
	public void VerifyGroupPermission_CustomerManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Customer_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		userMag.CreateGrop_roleMag();
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}

	/*Now it is part of customer management Hence changed  method from "CreateviewGroupPermission_UserManagement" to "CreateviewGroupPermission_CustomerManagement"*/
	
	@Test(priority = 370,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96041: TC-07-P-Verifying   view group  permission for user Mangement.", enabled = true)
	public void CreateviewGroupPermission_CustomerManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Customer Management","2","Customer_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Customer_Management_Role");
		roleMgmt.verifyRolePermissions("Customer Management", "2", "Customer_Management_Role");
		}
	/*Now it is part of customer management Hence changed  method from "VerifyViewGroupPermission_UserManagement" to "VerifyViewGroupPermission_CustomerManagement"*/

	@Test(priority = 371,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96042: TC-08-P-Creating User with  view group role  and checking the assigined permission functionality  for that user . ", enabled = true)
	public void VerifyViewGroupPermission_CustomerManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Customer_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		userMag.ViewGroupVerification("Group Details");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}


	/*Now it is part of customer management Hence changed  method from "CreateupdateGroupPermission_UserMangement" to "CreateupdateGroupPermission_CustomerManagement"*/

	@Test(priority = 372,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96043: TC-09-P-Verifying   update group  permission for user Mangement.", enabled = true)
	public void CreateupdateGroupPermission_CustomerManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Customer Management","2,3","Customer_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Customer_Management_Role");
		roleMgmt.verifyRolePermissions("Customer Management","2,3", "Customer_Management_Role");
		}


	/*Now it is part of customer management Hence changed  method from "VerifyupdateGroupPermission_UserManagement" to "VerifyupdateGroupPermission_CustomerManagement"*/


	@Test(priority = 373,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96044: TC-10-P-Creating User with  update  group role  and checking the assigined permission functionality  for that user  . ", enabled = true)
	public void VerifyupdateGroupPermission_CustomerManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Customer_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		userMag.updateGroupVerification("Update Customer/Group");
		smoke.Verify("Success");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}

	/*Now it is part of customer management Hence changed  method from "DeleteGroupPermission_UserManagement" to "DeleteGroupPermission_CustomerManagement"*/

	@Test(priority = 374,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96045: TC-11-P-Verifying   Delete group  permission for user Mangement.", enabled = true)
	public void DeleteGroupPermission_CustomerManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("Customer Management","2,4","Customer_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("Customer_Management_Role");
		roleMgmt.verifyRolePermissions("Customer Management", "2,4", "Customer_Management_Role");
		}


	/*Now it is part of customer management Hence changed  method from "VerifydeleteGroupPermission_UserManagement" to "VerifydeleteGroupPermissionn_CustomerManagement"*/


	@Test(priority = 375,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96046: TC-12-P-Creating User with delete group role and checking the assigined permission functionality  for that user . ", enabled = true)
	public void VerifydeleteGroupPermissionn_CustomerManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("Customer_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		userMag.deleteGroupVerification();
		smoke.Verify("Success");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}

	//.. UserManagement
	@Test(priority = 376,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96022 : TC-01-P-Verifying  create user  permission for user Mangement.", enabled = true)
	public void CreateUserPermission_UserManagement() throws Exception {
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management","1,2","User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "1,2", "User_Management_Role");
		
	}
	@Test(priority = 377,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96036 : TC-02-P-Creating User with create user role and checking the assigined permission functionality  for that user . ", enabled = true)
	public void VerifyCreateUserPermission_Usermanagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("User_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		userMag.Createuser_underiot_Customer();
		smoke.Verify("Success");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}
	
	@Test(priority = 378,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96037 : TC-03-P-Create view user  permission for user Mangement.", enabled = true)
	public void CreateviewPermission_UserManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management","2","User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "2", "User_Management_Role");
		}

	@Test(priority = 379,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96038: TC-04-P-Creating User with view user role  and checking the assigined permission functionality  for that user . ", enabled = true)
	public void VerifyViewPermission_UserManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("User_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		userMag.ViewUserVerification("User Details");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");
	}


	@Test(priority = 380,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96047: TC-13-P-Verifying   update user  permission for  user Mangement.", enabled = true)
	public void UpdateuserPermission_UserManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management","2,3","User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "2,3", "User_Management_Role");
		}

	@Test(priority = 381,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96048: TC-14-P-Creating User with update user role  and checking the assigined permission functionality  for that user . ", enabled = true)
	public void VerifyupdatePermission_UserManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("User_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		userMag.editUserVerification("Update User","NewUser");
		smoke.Verify("Success");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}


	@Test(priority = 382,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96049: TC-15-P-Verifying   Delete user  permission for  user Mangement.", enabled = true)
	public void DeleteUserPermission_UserManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management","2,4","User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "2,4", "User_Management_Role");
		}

	@Test(priority = 383,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96050: TC-16-P-Creating User with delete user role  and checking the assigined permission functionality  for that user . ", enabled = true)
	public void VerifydeleteUserPermission_UserManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("User_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		userMag.deleteUserVerification();
		smoke.Verify("Success");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}


	@Test(priority = 384,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96051: TC-17-P-Verifying   upload theme permission for  user Mangement.", enabled = true)
	public void UploadUserPermission_UserManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		roleMgmt.createNewRole("User Management","5","User_Management_Role");
		roleMgmt.verifyCreateRoleSuccessMsg("User_Management_Role");
		roleMgmt.verifyRolePermissions("User Management", "5", "User_Management_Role");
		}

	@Test(priority = 385,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Smoke","Critical_Regression" }, description = "96052: TC-18-P-Creating User with upload theme role  and checking the assigined permission functionality  for that user .", enabled = true)
	public void VerifyUploadUserPermission_UserManagement() throws Exception{
		logger.assignCategory("Role Management");

		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("User_Management_Role");
		logout();
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
     	RoleMgmt.login(prop.getProperty("cust_username"), prop.getProperty("user_password"));
		userMag.theamMagVerification();
		smoke.Verify("Success");
		logout();
		login(prop.getProperty("username"), prop.getProperty("password"));
		user.editUserWithRole("defaultRole");

	}


}
