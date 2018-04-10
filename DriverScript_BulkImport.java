package com.automation.drivers;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.ACP;
import com.automation.pageobjects.ApplicationMgmt;
import com.automation.pageobjects.AssetMgmt;
import com.automation.pageobjects.AssetTopology;
import com.automation.pageobjects.SmokeTest;
import com.automation.pageobjects.Users;
import com.hpe.base.Base;
import com.hpe.iot.e2eflows.Http_E2EFlow;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_BulkImport extends Base {

	Users user = new Users();
	AssetMgmt assetMgmt = new AssetMgmt();
	ACP acp = new ACP();
	Http_E2EFlow http = new Http_E2EFlow();
	AssetTopology ap = new AssetTopology();
	ApplicationMgmt app = new ApplicationMgmt();
	SmokeTest smoke = new SmokeTest();

	@BeforeSuite(groups = { "Smoke", "Critical_Regression" })
	public void start() throws Exception {
		setup();

	}

	@AfterMethod(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void exit(ITestResult result) throws Exception {
		logout();
		if (result.getStatus() == ITestResult.FAILURE) {
			login(prop.getProperty("username"), prop.getProperty("password"));
			/*user.editUserWithRole("defaultRole");*/
			logout();
		}

	}

	@AfterSuite(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void clean() throws Exception {
		closeBrowser();
	}
	
	
	/*Upload Device profile with Encryption & Decryption parameters enabled..*/	
	
			
	@Test(priority = 100,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify if upload device profie is Successfull for HTTP Device-- Encryption & Decryption enabled", enabled = false)
	public void UploadDPForHttpEncryptDecrypt() throws Exception {
		
		logger.log(LogStatus.INFO, "User is Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Upload Device profile with encryption & decryption");
		smoke.DeviceMgmtUpload("Http_Encryption_Decryption_DP.xml", true,
				assetDetailsProp.getProperty("HTTP_Asset_CP"));
		
		logger.log(LogStatus.INFO, "Verify Upload success!!");
		smoke.Verify("Success!");

	}
	
	/*Bulk Import--> HTTP uplink Without Encryption & Decryption ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 101, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---HTTP uplink without decryption as Group user ", enabled = true)
	public void CreateBulkImport_SimpleUplinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Bulk_Device_Import(3,"http","HTTP_Bulk_Asset",1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpUplink(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DeviceID"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "default");		

		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");

	}
	
	@Test(priority = 102, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---HTTP uplink without decryption as Group user ", enabled = true)
	public void UpdateBulkImport_SimpleUplinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Update_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpUplink(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DeviceID"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), "default");
		

		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");

	}
	
	@Test(priority = 103, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---HTTP uplink without decryption as Group user ", enabled = true)
	public void DeleteBulkImport_SimpleUplinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Delete_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");

	}
	
	/*Bulk Import--> HTTP downlink Without Encryption & Decryption ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 104, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---HTTP downlink without encryption as Group user ", enabled = true)
	public void CreateBulkImport_SimpleDownlinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Bulk_Device_Import(3,"http","HTTP_Bulk_Asset",2);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"));
		
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");

	}
	
	@Test(priority = 105, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---HTTP downlink without encryption as Group user ", enabled = true)
	public void UpdateBulkImport_SimpleDownlinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Update_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"));
		
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");

	}
	
	@Test(priority = 106, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---HTTP downlink without encryption as Group user ", enabled = true)
	public void DeleteBulkImport_SimpleDownlinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Delete_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");

	}
	
	
	
	/*Bulk Import--> HTTP uplink with Decryption ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 107, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---HTTP uplink with decryption as Group user ", enabled = true)
	public void CreateBulkImport_DecryptUplinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Bulk_Device_Import(2,"HTTP_Encrypt_Decrypt","HTTP_Bulk_Asset",0);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","HTTP_Encrypt_Decrypt");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpUplinkEncryptDecrypt(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "default");	

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","HTTP_Bulk_Asset");

	}
	
	@Test(priority = 108, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---HTTP uplink with decryption as Group user ", enabled = true)
	public void UpdateBulkImport_DecryptUplinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Update_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
	
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpUplinkEncryptDecrypt(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), "default");		

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","HTTP_Bulk_Asset");

	}
	
	@Test(priority = 109, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---HTTP uplink with decryption as Group user ", enabled = true)
	public void DeleteBulkImport_DecryptUplinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Delete_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");

	}
	
	/*Bulk Import--> HTTP downlink With Encryption---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 110, /*dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" },*/ groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---HTTP downlink with encryption as Group user ", enabled = true)
	public void CreateBulkImport_EncryptDownlinkFlow_GU() throws Exception {
		
		/*login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Bulk_Device_Import(2,"HTTP_Encrypt_Decrypt","HTTP_Bulk_Asset",1);
		smoke.Verify("Success");
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","HTTP_Encrypt_Decrypt");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");	*/	
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"));
		
		/*ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","HTTP_Bulk_Asset");*/

	}
	
	@Test(priority = 111, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---HTTP downlink with encryption as Group user ", enabled = true)
	public void UpdateBulkImport_EncryptDownlinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Update_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");		
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"));
		
		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","HTTP_Bulk_Asset");

	}
	
	@Test(priority = 112, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---HTTP downlink with encryption as Group user ", enabled = true)
	public void DeleteBulkImport_EncryptDownlinkFlow_GU() throws Exception {
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Delete_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");

	}
}
