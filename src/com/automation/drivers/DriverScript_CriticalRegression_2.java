package com.automation.drivers;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.ACP_ADD_EDIT;
import com.automation.pageobjects.AssetMgmt;
import com.automation.pageobjects.AssetTopology;
import com.automation.pageobjects.CustomerManagement;
import com.automation.pageobjects.DeviceManagement;
import com.automation.pageobjects.DeviceProfile;
import com.automation.pageobjects.SmokeTest;
import com.automation.pageobjects.Users;
import com.hpe.base.Base;
import com.hpe.iot.api.base.Base_API;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_CriticalRegression_2 extends Base {

	
	AssetTopology topo = new AssetTopology();
	AssetMgmt asset = new AssetMgmt();
	DeviceProfile devi=new DeviceProfile();
	SmokeTest smoke = new SmokeTest();
	ACP_ADD_EDIT acp=new ACP_ADD_EDIT();
	CustomerManagement cust = new CustomerManagement();
	DeviceManagement device_mgmt = new DeviceManagement();
	Users user = new Users();
	
	
	
/*	@BeforeSuite(groups = { "Smoke", "Regression", "Functional" })
	public void start() throws Exception {
		setup();
	}
	
	@AfterSuite(groups = { "Smoke", "Regression", "Functional" })
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
	@Test(priority = 56, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Device in Asset Topology , ALM Id = 88260", enabled = true)
	public void Asset_Topology_Verify_Device() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verifying Device in Asset Topology");
		topo.Verify_Device();
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 57, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Tenant/Device Count in Asset Topology, ALM Id = 88261", enabled = true)
	public void Asset_Topology_Verify_Count() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verifying Tenant/Device Count in Asset Topology");
		topo.Verify_Device_Count();
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 58, groups = { "Critical_Regression" },dependsOnMethods = { "com.hpe.iot.api.drivers.Smoke_API.API_Downlink","com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, description = "Verify Device Top5 Readings , ALM Id = 88261", enabled = true)
	public void Asset_Topology_Verify_Top5_Readings() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verifying Top5 Readings");
		
		//Store the device name in the property file and pass the key in the below method..
		topo.Verify_Readings(Base.nodelink,"5",Base_API.asset_name);
	}
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 59, groups = { "Critical_Regression" },dependsOnMethods = { "com.hpe.iot.api.drivers.Smoke_API.API_Downlink","com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, description = "Verify Device Readings Filter , ALM Id = 88262", enabled = true)
	public void Asset_Topology_Verify_Readings_Filter() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verifying Readings Filter");
		topo.Verify_Readings_Filter();
	}
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 60, groups = { "Critical_Regression" },dependsOnMethods = { "com.hpe.iot.api.drivers.Smoke_API.API_Downlink","com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, description = "Verify Device Readings , ALM Id = 88263", enabled =true)
	public void Asset_Topology_Verify_Readings() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify Readings");
		
		//Store the device name in the property file and pass the key in the below method..
		topo.Verify_Readings(Base.nodelink,"5",Base_API.asset_name);
	}
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 61, dependsOnMethods = { "com.hpe.iot.api.drivers.Smoke_API.API_Downlink","com.automation.drivers.DriverScript_Smoke.TestCase_80533" },groups = { "Critical_Regression" }, description = "Verify Device Readings , ALM Id = 88264", enabled = true)
	public void Asset_Topology_Verify_Readings_Search() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Search Reading");
		topo.Verify_Readings_Search();
	}
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 62, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Asset Key Import , ALM Id = 88280", enabled =true)
	public void Asset_Key_Import() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Asset Key Import");
		asset.Key_Import();
		
		asset.Verify("Success");
	}
	
	/*
	 * @author Jeevan
	 */
	@Test(priority = 63, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Asset KPI , ALM Id = 88281", enabled = true)
	public void Asset_KPI() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Filter Asset KPI by Name");
		asset.Asset_KPI("Asset Name");
		
		logger.log(LogStatus.INFO, "Filter Asset KPI by Type");
		asset.Asset_KPI("Asset Type");
		
		logger.log(LogStatus.INFO, "Filter Asset KPI by Profile");
		asset.Asset_KPI("Device Profile");
		
		asset.Verify_Asset_KPI();
	}

	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 64, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Codec Upload", enabled = true)
	public void Codec_Upload() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Codec Upload");
		asset.Codec_Upload();
		
		asset.Verify("Success");
	}
	
	
	/*
	 * @author Manish
	 * 
	 */
	@Test(priority = 65, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Editing a Raw XML for Device Profile , ALM Id = 88287", enabled =true)
	public void DP_Raw_XML_Edit() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));


		logger.log(LogStatus.INFO, "Open Raw XML");
		devi.Open_Raw_XML();
		
        logger.log(LogStatus.INFO, "View_Copy_XML");
		devi.View_Copy_XML();
		
		logger.log(LogStatus.INFO, "Edit Raw XML");
		devi.Edit_XML_File();
		
		logger.log(LogStatus.INFO, "Update XML");
		devi.Update_XML();
        }
	
	/*
	 * @author Manish
	 * 
	 */
	@Test(priority = 66, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Edit Capabilites , ALM Id = 88288", enabled = true)
	public void DP_Raw_XML_Edit_Capabilities() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
				
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
        logger.log(LogStatus.INFO, "Editing the Capabilites");
        devi.Update_Capablities();
        }

	/*
	 * @author Manish
	 * 
	 */
	@Test(priority = 67, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Customer Tree Structure , ALM Id = 88290", enabled =true)
	public void DP_Tree_Structure() throws Exception{
		logger.assignCategory("Critical Regression");
		
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
    	logger.log(LogStatus.INFO, "Verifying Device Customer Tree");
    	
    	
		devi.Verify_Device_Customer_Tree();
	}
	
	/*
	 * @author Manish
	 * 
	 */
	@Test(priority = 68, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Assign_Device_Profile_To_Subcustomer , ALM Id = 88291", enabled =true)
	public void RemapEntity_DeviceGroup() throws Exception{
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
	
		login(prop.getProperty("username"), prop.getProperty("password"));

		devi.Create_Sub_Customer();
		
		logout();
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Verifying Device Profile");
		devi.Assign_Device_Profile_To_Subcustomer();
	}
	/*
	 * @author Manish
	 * 
	 */
	@Test(priority = 69, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Deselect_SubEntity_InRemape , ALM Id = 88291", enabled =true)
		public void RemapEntity_DeviceGroup_ForDeselect() throws Exception{
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verifying Device Profile");
		devi.Deselect_SubEntity_InRemape();;
	}
	/*
	 * @author Manish
	 * 
	 */

	@Test(priority = 70, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = " Device can be assigned as a target resource to ACP , ALM Id = 88299", enabled =true)
	public void ACP_Edit() throws Exception{
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Device can be assigned as a target resource to ACP");
		acp.Target_Device_to_ACP();
	
	}
	/*
	 * @author Manish
	 * 
	 */
		
	@Test(priority = 71, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = {"Critical_Regression"}, description = "Add ACP to Apllication Mgt , ALM Id = 88300", enabled =true)
	public void Applicatio_Add_ACP() throws Exception{
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Adding Acp to container profile");
		acp.Add_Acp_To_Application();;
	}
	/*
	 * @author Manish
	 * 
	 */


	@Test(priority = 72, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Add_Subscription_for_Container_To_device , ALM Id = 88311", enabled =true)
	public void Add_Subscription_for_Container_To_device() throws Exception{
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Adding Acp to container profile");
		acp.Add_Subscription_for_Container_to_Device();
	}

	/*
	 * @author Manish
	 * 
	 */


	@Test(priority = 73, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Add_subscription_for_a_device , ALM Id = 88310", enabled =true)
	public void Add_Subscription_To_device() throws Exception{
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Adding Acp to container profile");
		acp.Add_subscription_for_a_device();
	}
	

	
	/*
	 * @author Jeevan
	 */
	@Test(priority = 74, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Remove Multiple Asset , ALM Id = 88278", enabled = true)
	public void Asset_Remove_MultipleAsset() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create New Asset");
		smoke.CreateNewAsset(true,true);
		smoke.Verify("Success!");
		
		smoke.CreateNewAsset(true,true);
		smoke.Verify("Success!");
		
		logger.log(LogStatus.INFO, "Remove Asset");
		asset.SelectAssetToBeRemoved();
		
		logger.log(LogStatus.INFO, "Verify Multiple Asset Removal");
		asset.Remove_AssetAndVerify();
	}
	
	/*
	 * @author Jeevan
	 */
	@Test(priority = 75, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Device Management Tab , ALM Id = 88319", enabled = true)
	public void DeviceMgmt_Tab() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		

		logger.log(LogStatus.INFO, "Verify Device Mgmt Tab");
		device_mgmt.VerifyDeviceMgmtTab();
	}
	
	/*
	 * @author Jeevan
	 */
	@Test(priority = 76, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Diagnostic and Monitering , ALM Id = 88323", enabled = true)
	public void DeviceMgmt_Diagnostic() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		logger.log(LogStatus.INFO, "Verify Device Mgmt Tab");
		device_mgmt.DeviceManagement_DiagnosticMonitering();
	}
	
	/*
	 * @author Jeevan
	 */
	@Test(priority = 77, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Device Status Change Active->Disabled", enabled = false)
	public void DLC_MoveDeviceFromActiveToDisabled() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));  

		//login("user3987", prop.getProperty("new_password"));  
		
		logger.log(LogStatus.INFO, "Verify Device Status to Active");
		topo.MoveDeviceStatusTo("Active");
		topo.VerifyDeviceStatus("Active");
		
		logger.log(LogStatus.INFO, "Verify Device Status to Disabled");
		topo.MoveDeviceStatusTo("Disabled");
		topo.VerifyDeviceStatus("Disabled");
	}
	
	/*
	 * @author Jeevan
	 */
	@Test(priority = 78, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Device Status Change Disabled->Removed", enabled = false)
	public void DLC_MoveDeviceFromActiveToRemoved() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));  

		//login("user3987", prop.getProperty("new_password"));  
		
		logger.log(LogStatus.INFO, "Verify Device Status to Removed");
		topo.MoveDeviceStatusTo("Removed");

	}
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 79, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Asset Bulk Device Import , ALM Id = 88277", enabled = true)
	public void Asset_Bulk_Device_Import() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Device Bulk UPload");
		asset.Device_Import();
		
		asset.Verify("Success");
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 80, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "User Subsystem Verify , ALM Id = 88341", enabled = true)
	public void UsersSubsSystemVerify() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Verify Subsystem");
		user.Users_Subsystem();
		
	}
	
	/*
	 * @author Jeevan
	 * 
	 */
	@Test(priority = 81, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Edit User Subsystem Verify , ALM Id = 88343", enabled = true)
	public void EditUsersSubsSystemVerify() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Verify Subsystem");
		user.EditUser_Subsytem();
		
	}
	
	
}
