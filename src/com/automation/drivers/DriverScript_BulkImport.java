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

import hpe.util.mqtt.client.Mqttpublish;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_BulkImport extends Base {

	Users user = new Users();
	AssetMgmt assetMgmt = new AssetMgmt();
	ACP acp = new ACP();
	Http_E2EFlow http = new Http_E2EFlow();
	AssetTopology ap = new AssetTopology();
	ApplicationMgmt app = new ApplicationMgmt();
	SmokeTest smoke = new SmokeTest();
	Mqttpublish mqttUplink = new Mqttpublish();

	/*@BeforeSuite(groups = { "Smoke", "Critical_Regression" })
	public void start() throws Exception {
		setup();

	}*/

	@AfterMethod(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void exit(ITestResult result) throws Exception {
		logout();
		if (result.getStatus() == ITestResult.FAILURE) {
			login(prop.getProperty("username"), prop.getProperty("password"));
			/*user.editUserWithRole("defaultRole");*/
			logout();
		}

	}

	/*@AfterSuite(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void clean() throws Exception {
		closeBrowser();
	}*/
	
	
	/*Upload Container & Device profile with Encryption & Decryption parameters enabled..*/	
	
			
	@Test(priority = 500,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify if upload container profie is Successfull", enabled = false)
	public void UploadCP() throws Exception {
		logger.assignCategory("Bulk Import");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		smoke.ContainerUpload("httpcp.xml");
		smoke.Verify("Success!");

	}
	
	
	@Test(priority = 501,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
	"Critical_Regression" }, description = "Verify if upload device profie is Successfull for HTTP Device-- without Encryption & Decryption enabled", enabled = false)
	public void UploadDP_HTTPSimple() throws Exception {

		logger.assignCategory("Bulk Import");
			login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

			smoke.DeviceMgmtUpload("HTTP_DP.xml", true, assetDetailsProp.getProperty("HTTP_Asset_CP"));
			smoke.Verify("Success!");
			
	}
	
	@Test(priority = 502,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
	"Critical_Regression" }, description = "Verify if upload device profie is Successfull for MQTT Device-- without Encryption & Decryption enabled", enabled = false)
	public void UploadDP_MQTTSimple() throws Exception {

		logger.assignCategory("Bulk Import");

			login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

			smoke.DeviceMgmtUpload("mqttdp.xml", true, assetDetailsProp.getProperty("HTTP_Asset_CP"));
			smoke.Verify("Success!");
			
	}
	
	@Test(priority = 503,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
	"Critical_Regression" }, description = "Verify if upload device profie is Successfull for MQTT Device-- Encryption & Decryption enabled", enabled = true)
	public void UploadDP_MQTT_EncryptDecrypt() throws Exception {

		logger.assignCategory("Bulk Import");

			login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

			smoke.DeviceMgmtUpload("MQTT_Encryption_Decryption_DP.xml", true,
					assetDetailsProp.getProperty("HTTP_Asset_CP"));	
			smoke.Verify("Success!");
			
	}
	
	
	
	
	
	/*Bulk Import--> HTTP uplink Without Encryption & Decryption ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 504, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---HTTP uplink without decryption as Group user, ALM_ID : 96972 ", enabled = true)
	public void HttpCreate_SimpleUplinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Bulk_Device_Import(3,"http","HTTP_Bulk_Asset",1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpUplink(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DeviceID"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "default");		

		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		
		assetMgmt.RetrieveDeviceStatus("HTTP_Bulk_Asset");

	}
	
	@Test(priority = 505, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreate_SimpleUplinkFlow_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---HTTP uplink without decryption as Group user, ALM_ID : 96973 ", enabled = true)
	public void HttpUpdate_SimpleUplinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Update_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpUplink(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DeviceID"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), "default");
		

		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");

	}
	
	@Test(priority = 506, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreate_SimpleUplinkFlow_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---HTTP uplink without decryption as Group user, ALM_ID : 96974 ", enabled = true)
	public void HttpDelete_SimpleUplinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Delete_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");

	}
	
	/*Bulk Import--> HTTP downlink Without Encryption & Decryption ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 507, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---HTTP downlink without encryption as Group user, ALM_ID : 96978 ", enabled = true)
	public void HttpCreate_SimpleDownlinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Bulk_Device_Import(3,"http","HTTP_Bulk_Asset",2);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
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
		
		assetMgmt.RetrieveDeviceStatus("HTTP_Bulk_Asset");

	}
	
	@Test(priority = 508, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreate_SimpleDownlinkFlow_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---HTTP downlink without encryption as Group user, ALM_ID : 96979 ", enabled = true)
	public void HttpUpdate_SimpleDownlinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Update_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"));
		
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");

	}
	
	@Test(priority = 509, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreate_SimpleDownlinkFlow_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---HTTP downlink without encryption as Group user, ALM_ID : 96980 ", enabled = true)
	public void HttpDelete_SimpleDownlinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Delete_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");

	}
	
	
	
	@Test(priority = 510,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
	"Critical_Regression" }, description = "Verify if upload device profie is Successfull for HTTP Device-- Encryption & Decryption enabled", enabled = true)
	public void UploadDP_HTTP_EncryptDecrypt() throws Exception {

		logger.assignCategory("Bulk Import");

			login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

			smoke.DeviceMgmtUpload("Http_Encryption_Decryption_DP.xml", true,
					assetDetailsProp.getProperty("HTTP_Asset_CP"));	
			smoke.Verify("Success!");
			
	}
	
	/*Bulk Import--> HTTP uplink with Decryption ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 511, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---HTTP uplink with decryption as Group user, ALM_ID : 96966 ", enabled = true)
	public void HttpCreate_DecryptUplinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Bulk_Device_Import(2,"HTTP_Encrypt_Decrypt","HTTP_Bulk_Asset",0);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","HTTP_Encrypt_Decrypt","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpUplinkEncryptDecrypt(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "default");	

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","HTTP_Bulk_Asset");
		
		assetMgmt.RetrieveDeviceStatus("HTTP_Bulk_Asset");

	}
	
	@Test(priority = 512, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreate_DecryptUplinkFlow_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---HTTP uplink with decryption as Group user, ALM_ID : 96967 ", enabled = true)
	public void HttpUpdate_DecryptUplinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Update_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
	
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		
		http.API_HttpUplinkEncryptDecrypt(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), "default");		

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","HTTP_Bulk_Asset");

	}
	
	@Test(priority = 513, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreate_DecryptUplinkFlow_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---HTTP uplink with decryption as Group user, ALM_ID : 96968 ", enabled = true)
	public void HttpDelete_DecryptUplinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Delete_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");

	}
	
	/*Bulk Import--> HTTP downlink With Encryption---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 514, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---HTTP downlink with encryption as Group user, ALM_ID : 96960 ", enabled = true)
	public void HttpCreate_EncryptDownlinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Bulk_Device_Import(2,"HTTP_Encrypt_Decrypt","HTTP_Bulk_Asset",1);
		smoke.Verify("Success");
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","HTTP_Encrypt_Decrypt","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");		
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"));
		
		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","HTTP_Bulk_Asset");
		
		assetMgmt.RetrieveDeviceStatus("HTTP_Bulk_Asset");

	}
	
	@Test(priority = 515, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreate_EncryptDownlinkFlow_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---HTTP downlink with encryption as Group user, ALM_ID : 96961 ", enabled = true)
	public void HttpUpdate_EncryptDownlinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Update_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");		
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"));
		
		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","HTTP_Bulk_Asset");

	}
	
	@Test(priority = 516, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreate_EncryptDownlinkFlow_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---HTTP downlink with encryption as Group user, ALM_ID : 96962 ", enabled = true)
	public void HttpDelete_EncryptDownlinkFlow_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));		
		
		assetMgmt.Delete_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");

	}	
	
	
	
	/*Bulk Import--> MQTT uplink Without Encryption & Decryption --NO_SECURITY ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 517, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---MQTT NO_SECURITY uplink without decryption as Group user, ALM_ID : 97020 ", enabled = true)
	public void MQTTCreate_UplinkFlowNS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "mqttNS", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "mqtt_normal", "MQTT_Bulk_Asset_Username");
		assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy");

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");
		
		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 518, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkFlowNS_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---MQTT NO_SECURITY uplink without decryption as Group user, ALM_ID : 97021 ", enabled = true)
	public void MQTTUpdate_UplinkFlowNS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("mqttNS");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy");

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 519, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkFlowNS_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---MQTT NO_SECURITY uplink without decryption as Group user, ALM_ID : 97022 ", enabled = true)
	public void MQTTDelete_UplinkFlowNS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("mqttNS");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");

	}
	
	
	/*Bulk Import--> MQTT downlink Without Encryption & Decryption --NO_SECURITY ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 520, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---MQTT NO_SECURITY downlink without encryption as Group user, ALM_ID : 97026 ", enabled = true)
	public void MQTTCreate_DownlinkFlowNS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "mqttNS", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "mqtt_normal", "MQTT_Bulk_Asset_Username");
		assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"));

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 521, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkFlowNS_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---MQTT NO_SECURITY downlink without encryption as Group user, ALM_ID : 97027 ", enabled = true)
	public void MQTTUpdate_DownlinkFlowNS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("mqttNS");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"));

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 522, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkFlowNS_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---MQTT NO_SECURITY downlink without encryption as Group user, ALM_ID : 97028 ", enabled = true)
	public void MQTTDelete_DownlinkFlowNS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("mqttNS");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");

	}
	
	
	/*Bulk Import--> MQTT uplink Without Encryption & Decryption --BASIC_AUTH ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 523, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---MQTT NO_SECURITY uplink without decryption as Group user, ALM_ID : 96996 ", enabled = true)
	public void MQTTCreate_UplinkFlowBA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "mqtt", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "mqtt_normal", "MQTT_Bulk_Asset_Username");
		/*assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");*/
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy");

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");
		
		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");
		

	}
	
	@Test(priority = 524, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkFlowBA_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---MQTT NO_SECURITY uplink without decryption as Group user, ALM_ID : 96997 ", enabled = true)
	public void MQTTUpdate_UplinkFlowBA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("mqtt");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy");

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 525, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkFlowBA_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---MQTT NO_SECURITY uplink without decryption as Group user, ALM_ID : 96998 ", enabled = true)
	public void MQTTDelete_UplinkFlowBA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("mqtt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");

	}
	
	
	/*Bulk Import--> MQTT downlink Without Encryption & Decryption --BASIC_AUTH ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 526, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---MQTT BASIC_AUTH downlink without encryption as Group user, ALM_ID : 97002 ", enabled = true)
	public void MQTTCreate_DownlinkFlowBA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "mqtt", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "mqtt_normal", "MQTT_Bulk_Asset_Username");
		
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"));

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 527, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkFlowBA_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---MQTT BASIC_AUTH downlink without encryption as Group user, ALM_ID : 97003 ", enabled = true)
	public void MQTTUpdate_DownlinkFlowBA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("mqtt");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"));

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 528, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkFlowBA_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---MQTT BASIC_AUTH downlink without encryption as Group user, ALM_ID : 97004 ", enabled = true)
	public void MQTTDelete_DownlinkFlowBA_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("mqtt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}
	
	/*Bulk Import--> MQTT downlink With Encryption  --BASIC_AUTH ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 529, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---MQTT BASIC_AUTH downlink with encryption as Group user, ALM_ID : 96984 ", enabled = true)
	public void MQTTCreate_DownlinkED_BA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset_Username");
		
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 530, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkED_BA_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---MQTT BASIC_AUTH downlink with encryption as Group user, ALM_ID : 96985 ", enabled = true)
	public void MQTTUpdate_DownlinkED_BA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("MQTT_Encrypt_Decrypt");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 531, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkED_BA_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---MQTT BASIC_AUTH downlink with encryption as Group user, ALM_ID : 96986 ", enabled = true)
	public void MQTTDelete_DownlinkED_BA_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("MQTT_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}
	
	
	/*Bulk Import--> MQTT downlink With Encryption  --NO_SECURITY ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 532, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---MQTT NO_SECURITY downlink with encryption as Group user, ALM_ID : 97008 ", enabled = true)
	public void MQTTCreate_DownlinkED_NS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "MQTT_Encrypt_Decrypt_NS", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset_Username");
		assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 533, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkED_NS_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---MQTT NO_SECURITY downlink with encryption as Group user, ALM_ID : 97009 ", enabled = true)
	public void MQTTUpdate_DownlinkED_NS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("MQTT_Encrypt_Decrypt_NS");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 534, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkED_NS_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---MQTT NO_SECURITY downlink with encryption as Group user, ALM_ID : 97010 ", enabled = true)
	public void MQTTDelete_DownlinkED_NS_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("MQTT_Encrypt_Decrypt_NS");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}
	
	/*Bulk Import--> MQTT uplink With Decryption  --BASIC_AUTH ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 535, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---MQTT BASIC_AUTH uplink with decryption as Group user, ALM_ID : 96990 ", enabled = true)
	public void MQTTCreate_UplinkED_BA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset_Username");
		
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTTDecrypt(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy",assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 536, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkED_BA_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---MQTT BASIC_AUTH uplink with decryption as Group user, ALM_ID : 96991", enabled = true)
	public void MQTTUpdate_UplinkED_BA_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("MQTT_Encrypt_Decrypt");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTTDecrypt(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy",assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 537, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkED_BA_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---MQTT BASIC_AUTH uplink with decryption as Group user, ALM_ID : 96992", enabled = true)
	public void MQTTDelete_UplinkED_BA_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("MQTT_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}
	
	
	/*Bulk Import--> MQTT uplink With Decryption  --NO_SECURITY ---> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 538, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Create Bulk Import---MQTT NO_SECURITY uplink with decryption as Group user, ALM_ID : 97014 ", enabled = true)
	public void MQTTCreate_UplinkED_NS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "MQTT_Encrypt_Decrypt_NS", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset_Username");
		assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTTDecrypt(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy",assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 539, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkED_NS_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Update Bulk Import---MQTT NO_SECURITY uplink with decryption as Group user, ALM_ID : 97015  ", enabled = true)
	public void MQTTUpdate_UplinkED_NS_GU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("MQTT_Encrypt_Decrypt_NS");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTTDecrypt(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy",assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 540, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkED_NS_GU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "Delete Bulk Import---MQTT NO_SECURITY uplink with decryption as Group user, ALM_ID : 97016 ", enabled = true)
	public void MQTTDelete_UplinkED_NS_GU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("MQTT_Encrypt_Decrypt_NS");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}
	
	
	
	
/************************************************ Customer User ***********************************************************/
	
	/*Bulk Import -- HTTP Flow -- Without Encryption/Decryption------>Uplink-----> Customer User-------->CREATE/UPDATE/DELETE*/
	
	@Test(priority = 541, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96975:TC-016-CREATE HTTP devices through Bulk Import with minimal asset params and perform Uplink as CUSTOMER user", enabled = true)
	public void HttpCreateBulkUpload_CustomerUser_SimpleUplink() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Bulk_Device_Import(3,"http","HTTP_Bulk_Asset",1);		
		// Retrieve Asset Details//
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		//Add Http Device To ACP//
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));
		smoke.Verify("Success");
		//Add ACP to Device//
		assetMgmt.AddAcptoDevice(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				prop.getProperty("acp_name"));
		//Add Subscription to Http Device Container//
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		logout();
		
		//Http Uplink to the Device//
	
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
	
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		http.API_HttpUplink(
				assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DeviceID"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "default");
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		logout();
	
		
		
	}
	
	@Test(priority = 542, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreateBulkUpload_CustomerUser_SimpleUplink" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96976: TC-017-UPDATE HTTP devices through Bulk Import with minimal asset params and perform Uplink as CUSTOMER user", enabled = true)
	public void httpUpdateBulkUpload_CustomerUser_SimpleUplink() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Update_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		// Retrieve Asset Details//
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		//Add Http Device To ACP//
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));
		smoke.Verify("Success");
		//Add ACP to Device//
		assetMgmt.AddAcptoDevice(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				prop.getProperty("acp_name"));
		//Add Subscription to Http Device Container//
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		logout();
		//Http Uplink to the Device//
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
	
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		http.API_HttpUplink(
				assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DeviceID"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "default");
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		logout();
	
		
		
	}
	
	@Test(priority = 543, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.HttpCreateBulkUpload_CustomerUser_SimpleUplink" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96977: TC-018-DELETE HTTP devices through Bulk Import for devices uploaded for minimal asset params for Uplink as CUSTOMER user", enabled = true)
	public void httpDeleteBulkUpload_CustomerUser_SimpleUplink() throws Exception {
		logger.assignCategory("Bulk Import");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Delete_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");
		logout();
		
	}

	/*Bulk Import--> HTTP Flow --- Without Encryption & Decryption --->Downlink------> Group User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 544, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96981: TC-022-CREATE HTTP devices through Bulk Import with minimal asset params and perform downlink as CUSTOMER user", enabled = true)

	public void httpCreateDeviceBulkUpload_CustomerUser_SimpleDownlink() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Bulk_Device_Import(3, "http", "HTTP_Bulk_Asset", 1);
		smoke.Verify("Success!");
		// Retrieve Asset Details//
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		//Add Http Device To ACP//
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));
		smoke.Verify("Success");
		//Add ACP to Device//
		assetMgmt.AddAcptoDevice(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				prop.getProperty("acp_name"));
		//Add Subscription to Http Device Container//
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		logout();
		//Http DownLink to the Device//

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"));
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		logout();

		
		
	}

	@Test(priority = 545, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.httpCreateDeviceBulkUpload_CustomerUser_SimpleDownlink" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96982: TC-023-UPDATE HTTP devices through Bulk Import with minimal asset params and perform downlink as CUSTOMER user", enabled = true)

	public void httpUpdateBulkUpload_CustomerUser_SimpleDownlink() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Update_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		// Retrieve Asset Details//
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		//Add Http Device To ACP//
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));
		smoke.Verify("Success");
		//Add ACP to Device//
		assetMgmt.AddAcptoDevice(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				prop.getProperty("acp_name"));
		//Add Subscription to Http Device Container//
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		logout();
		
		//Http Downlink to the Device//

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("HTTP_Bulk_Asset"));
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		logout();

		
		
	}

	@Test(priority = 546, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.httpCreateDeviceBulkUpload_CustomerUser_SimpleDownlink" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96983: TC-024-DELETE HTTP devices through Bulk Import for devices uploaded for minimal asset params for downlink as CUSTOMER user", enabled = true)
	public void HTTPDeleteBulkUpload_CustomerUser_SimpleDownlink() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Delete_Bulk_Device_Import("http");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");
		logout();
		
	}

	
	/*Bulk Import -- HTTP Flow -- with Decryption------>Uplink-------> Customer User-------->CREATE/UPDATE/DELETE*/

	@Test(priority = 547, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96969: TC-010-CREATE HTTP devices through Bulk Import and perform a DECRYPTION for Uplink as CUSTOMER user", enabled = true)
	public void httpCreateBulkUpload_CustomerUser_Uplink_Decrypt() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Bulk_Device_Import(2,"HTTP_Encrypt_Decrypt","HTTP_Bulk_Asset",0);
		// Retrieve Asset Details//
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		//Add Http Device To ACP//
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));
		smoke.Verify("Success");
		//Add ACP to Device//
		assetMgmt.AddAcptoDevice(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				prop.getProperty("acp_name"));
		//Add Subscription to Http Device Container//
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		logout();
		
		//Http Uplink to the Device//

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		http.API_HttpUplinkEncryptDecrypt(
				assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "default");	
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		logout();

		}
		
	@Test(priority = 548, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.httpCreateBulkUpload_CustomerUser_Uplink_Decrypt" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96970: TC-011-UPDATE HTTP devices through Bulk Import and perform a DECRYPTION for Uplink as CUSTOMER user", enabled = true)
	public void httpUpdateBulkUpload_CustomerUser_Uplink_Decrypt() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Update_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		// Retrieve Asset Details//
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		//Add Http Device To ACP//
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));
		smoke.Verify("Success");
		//Add ACP to Device//
		assetMgmt.AddAcptoDevice(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				prop.getProperty("acp_name"));
		//Add Subscription to Http Device Container//
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		logout();

		//Http Uplink to the Device//
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		http.API_HttpUplinkEncryptDecrypt(
				assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "default");
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		logout();

		
		
	}

	@Test(priority = 549, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.httpCreateBulkUpload_CustomerUser_Uplink_Decrypt" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96971: TC-012-DELETE HTTP devices through Bulk Import for devices uploaded for DECRYPTION as CUSTOMER user", enabled = true)
	public void HTTPDeleteBulkUpload_CustomerUser_Uplink_Decrypt() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Delete_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");
		logout();
		
	}
	

	/*Bulk Import -- HTTP Flow -- with Decryption------>Downlink-------> Customer User-------->CREATE/UPDATE/DELETE | change needed************/
	
	@Test(priority = 550, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96981: TC-022-CREATE HTTP devices through Bulk Import with minimal asset params and perform downlink as CUSTOMER user", enabled = true)
	public void httpCreateBulkUpload_CustomerUser_Downlink_Encrypt() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Bulk_Device_Import(2,"HTTP_Encrypt_Decrypt","HTTP_Bulk_Asset",0);
		// Retrieve Asset Details//
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		//Add Http Device To ACP//
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));
		smoke.Verify("Success");
		//Add ACP to Device//
		assetMgmt.AddAcptoDevice(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				prop.getProperty("acp_name"));
		//Add Subscription to Http Device Container//
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		logout();

		//Http DownLink to the Device//

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		http.API_HttpDownlinkEncryptDecrypt("default",
				assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"));

		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		logout();

		
		
	}

	@Test(priority = 551, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.httpCreateBulkUpload_CustomerUser_Downlink_Encrypt" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96982: TC-023-UPDATE HTTP devices through Bulk Import with minimal asset params and perform downlink as CUSTOMER user", enabled = true)
	public void httpUpdateBulkUpload_CustomerUser_Downlink_Encrypt() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Update_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		// Retrieve Asset Details//
		assetMgmt.RetrieveDeviceDetails("HTTP_Bulk_Asset","http_normal","HTTP_Bulk_Asset_Username");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "HTTP_Bulk_Asset_RI");
		//Add Http Device To ACP//
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"));
		smoke.Verify("Success");
		//Add ACP to Device//
		assetMgmt.AddAcptoDevice(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				prop.getProperty("acp_name"));
		//Add Subscription to Http Device Container//
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), "default");
		logout();
		//Http Uplink to the Device//

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"),"HTTP_Bulk_Asset");
		http.API_HttpDownlinkEncryptDecrypt("default",
				assetDetailsProp.getProperty("HTTP_Bulk_Asset"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr"));
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Bulk_Asset");
		logout();

		
		
	}

	@Test(priority = 552, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.httpCreateBulkUpload_CustomerUser_Downlink_Encrypt" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96983: TC-024-DELETE HTTP devices through Bulk Import for devices uploaded for minimal asset params for downlink as CUSTOMER user", enabled = true)
	public void httpDeleteBulkUpload_CustomerUser_Downlink_Encrypt() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		assetMgmt.Delete_Bulk_Device_Import("HTTP_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("HTTP_Bulk_Asset");
		logout();
		
	}
	
	
	
	
	
	/*Bulk Import--> MQTT uplink Without Encryption & Decryption --NO_SECURITY ---> Customer User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 553, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97023: TC-064-CREATE MQTT devices through Bulk Import with AuthType as NO_SEC with minimal asset params and perform Uplink as CUSTOMER user ", enabled = true)
	public void MQTTCreate_UplinkFlowNS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "mqttNS", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "mqtt_normal", "MQTT_Bulk_Asset_Username");
		assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy");

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");
		
		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 554, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkFlowNS_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97024 :TC-065-UPDATE MQTT devices through Bulk Import with AuthType as NO_SEC with minimal asset params and perform Uplink as CUSTOMER user ", enabled = true)
	public void MQTTUpdate_UplinkFlowNS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("mqttNS");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy");

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 555, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkFlowNS_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97025: TC-066-DELETE MQTT devices through Bulk Import with AuthType as NO_SEC for devices uploaded for minimal asset params for Uplink as CUSTOMER user ", enabled = true)
	public void MQTTDelete_UplinkFlowNS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("mqttNS");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");

	}
	
	
	/*Bulk Import--> MQTT downlink Without Encryption & Decryption --NO_SECURITY ---> Customer User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 556, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97029: TC-070-CREATE MQTT devices through Bulk Import with AuthType as NO_SEC with minimal asset params and perform downlink as CUSTOMER user ", enabled = true)
	public void MQTTCreate_DownlinkFlowNS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "mqttNS", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "mqtt_normal", "MQTT_Bulk_Asset_Username");
		assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"));

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 557, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkFlowNS_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97030: TC-071-UPDATE MQTT devices through Bulk Import with AuthType as NO_SEC with minimal asset params and perform downlink as CUSTOMER user ", enabled = true)
	public void MQTTUpdate_DownlinkFlowNS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("mqttNS");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"));

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 558, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkFlowNS_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97031: TC-072-DELETE MQTT devices through Bulk Import with AuthType as NO_SEC for devices uploaded for minimal asset params for downlink as CUSTOMER user ", enabled = true)
	public void MQTTDelete_DownlinkFlowNS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("mqttNS");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");

	}
	
	
	/*Bulk Import--> MQTT uplink Without Encryption & Decryption --BASIC_AUTH ---> Customer User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 559, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "96999: TC-040-CREATE MQTT devices through Bulk Import with AuthType as BASIC_AUTH with minimal asset params and perform Uplink as CUSTOMER user ", enabled = false)
	public void MQTTCreate_UplinkFlowBA_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "mqtt", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "mqtt_normal", "MQTT_Bulk_Asset_Username");
		/*assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");*/
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy");

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");
		
		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");
		

	}
	
	@Test(priority = 560, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkFlowBA_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97000 : TC-041-UPDATE MQTT devices through Bulk Import with AuthType as BASIC_AUTH with minimal asset params and perform Uplink as CUSTOMER user ", enabled = false)
	public void MQTTUpdate_UplinkFlowBA_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("mqtt");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy");

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 561, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkFlowBA_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97001 : TC-042-DELETE MQTT devices through Bulk Import with AuthType as BASIC_AUTH for devices uploaded for minimal asset params for Uplink as CUSTOMER user ", enabled = false)
	public void MQTTDelete_UplinkFlowBA_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("mqtt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");

	}
	
	
	/*Bulk Import--> MQTT downlink Without Encryption & Decryption --BASIC_AUTH ---> Customer User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 562, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "97005: TC-046-CREATE MQTT devices through Bulk Import with AuthType as BASIC_AUTH with minimal asset params and perform downlink as CUSTOMER user ", enabled = true)
	public void MQTTCreate_DownlinkFlowBA_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "mqtt", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "mqtt_normal", "MQTT_Bulk_Asset_Username");
		
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"));

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 563, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkFlowBA_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "97006: TC-047-UPDATE MQTT devices through Bulk Import with AuthType as BASIC_AUTH with minimal asset params and perform downlink as CUSTOMER user", enabled = true)
	public void MQTTUpdate_DownlinkFlowBA_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("mqtt");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlink("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"));

		ap.Verify_Readings(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1),
				"MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 564, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkFlowBA_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, description = "97007: TC-048-DELETE MQTT devices through Bulk Import with AuthType as BASIC_AUTH for devices uploaded for minimal asset params for downlink as CUSTOMER user", enabled = true)
	public void MQTTDelete_DownlinkFlowBA_CU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("mqtt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}

	
	/*Bulk Import--> MQTT downlink With Encryption  --BASIC_AUTH ---> Customer User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 565, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96987: TC-028-CREATE MQTT devices through Bulk Import with AuthType as BASIC_AUTH and perform a ENCRYPTION for Downlink as CUSTOMER user ", enabled = true)
	public void MQTTCreate_DownlinkED_BA_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset_Username");
		
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 566, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkED_BA_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96988 : TC-029-UPDATE MQTT devices through Bulk Import with AuthType as BASIC_AUTH and perform a ENCRYPTION for Downlink as CUSTOMER user ", enabled = true)
	public void MQTTUpdate_DownlinkED_BA_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("MQTT_Encrypt_Decrypt");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 567, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkED_BA_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96989: TC-030-DELETE MQTT devices through Bulk Import with AuthType as BASIC_AUTH for devices uploaded for ENCRYPTION  as CUSTOMER user ", enabled = true)
	public void MQTTDelete_DownlinkED_BA_CU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("MQTT_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}
	

	/*Bulk Import--> MQTT uplink With Decryption  --BASIC_AUTH ---> customer User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 568, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96993: TC-034-CREATE MQTT devices through Bulk Import with AuthType as BASIC_AUTH and perform a DECRYPTION for Uplink as CUSTOMER user ", enabled = true)
	public void MQTTCreate_UplinkED_BA_CU() throws Exception {

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset_Username");
		
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTTDecrypt(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy",assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 569, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkED_BA_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "96994: TC-035-UPDATE MQTT devices through Bulk Import with AuthType as BASIC_AUTH and perform a DECRYPTION for Uplink as CUSTOMER user", enabled = true)
	public void MQTTUpdate_UplinkED_BA_CU() throws Exception {

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("MQTT_Encrypt_Decrypt");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTTDecrypt(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy",assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 570, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkED_BA_CU" }, groups = {
			"Smoke",
			"Critical_Regression" },
			description = "96995: TC-036-DELETE MQTT devices through Bulk Import with AuthType as BASIC_AUTH for devices uploaded for DECRYPTION as CUSTOMER user", enabled = true)
	public void MQTTDelete_UplinkED_BA_CU() throws Exception {
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("MQTT_Encrypt_Decrypt");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}
	
	
	
	/*Bulk Import--> MQTT downlink With Encryption  --NO_SECURITY ---> Customer User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 571, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "97011: TC-052-CREATE MQTT devices through Bulk Import with AuthType as NO_SEC and perform a ENCRYPTION for Downlink as CUSTOMER user ", enabled = true)
	public void MQTTCreate_DownlinkED_NS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "MQTT_Encrypt_Decrypt_NS", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset_Username");
		assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"));		
		smoke.Verify("Success");
		
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "default");
		smoke.Verify("Success");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 572, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkED_NS_CU" }, groups = {
			"Smoke",
			"Critical_Regression" },
			description = "97012 : TC-053-UPDATE MQTT devices through Bulk Import with AuthType as NO_SEC and perform a ENCRYPTION for Downlink as CUSTOMER use ", enabled = true)
	public void MQTTUpdate_DownlinkED_NS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("MQTT_Encrypt_Decrypt_NS");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		http.API_HttpDownlinkEncryptDecrypt("default", assetDetailsProp.getProperty("MQTT_Bulk_Asset"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "downlink","MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 573, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_DownlinkED_NS_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "97013 : TC-054-DELETE MQTT devices through Bulk Import with AuthType as NO_SEC for devices uploaded for ENCRYPTION  as CUSTOMER user ", enabled = true)
	public void MQTTDelete_DownlinkED_NS_CU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		
		assetMgmt.Delete_Bulk_Device_Import("MQTT_Encrypt_Decrypt_NS");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}

	
	
	/*Bulk Import--> MQTT uplink With Decryption  --NO_SECURITY ---> customer User --> CREATE/UPDATE/DELETE*/

	@Test(priority = 574, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = {
			"Smoke",
			"Critical_Regression" },
			description = "97017 : TC-058-CREATE MQTT devices through Bulk Import with AuthType as NO_SEC and perform a DECRYPTION for Uplink as CUSTOMER user ", enabled = true)
	public void MQTTCreate_UplinkED_NS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Bulk_Device_Import(2, "MQTT_Encrypt_Decrypt_NS", "MQTT_Bulk_Asset", 1);
		smoke.Verify("Success!");
		assetMgmt.RetrieveDeviceDetails("MQTT_Bulk_Asset", "MQTT_Encrypt_Decrypt", "MQTT_Bulk_Asset_Username");
		assetMgmt.ResetDevicePassword("Success!", "MQTT_Bulk_Asset", "MQ_devicePassword");
		assetMgmt.retrieveAssetResourceID(assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "MQTT_Bulk_Asset_RI");
		
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTTDecrypt(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy",assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","MQTT_Bulk_Asset");

		assetMgmt.RetrieveDeviceStatus("MQTT_Bulk_Asset");

	}
	
	@Test(priority = 575, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkED_NS_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "97018 : TC-059-UPDATE MQTT devices through Bulk Import with AuthType as NO_SEC and perform a DECRYPTION for Uplink as CUSTOMER user  ", enabled = true)
	public void MQTTUpdate_UplinkED_NS_CU() throws Exception {
		logger.assignCategory("Bulk Import");

		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		assetMgmt.Update_Bulk_Device_Import("MQTT_Encrypt_Decrypt_NS");
		smoke.Verify("Success!");

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), "MQTT_Bulk_Asset");

		mqttUplink.uplinkMQTTDecrypt(assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Bulk_Asset_Password"), assetDetailsProp.getProperty("MQTT_Bulk_Asset"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy",assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI"),
				assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr"));

		ap.Verify_Readings_Decryption(assetDetailsProp.getProperty("MQTT_Bulk_Asset_RI"), Integer.toString(initialReadings + 1), "uplink","MQTT_Bulk_Asset");

		
	}
	
	@Test(priority = 576, dependsOnMethods = { "com.automation.drivers.DriverScript_BulkImport.MQTTCreate_UplinkED_NS_CU" }, groups = {
			"Smoke",
			"Critical_Regression" }, 
			description = "97019: TC-060-DELETE MQTT devices through Bulk Import with AuthType as NO_SEC for devices uploaded for DECRYPTION as CUSTOMER user ", enabled = true)
	public void MQTTDelete_UplinkED_NS_CU() throws Exception {
		logger.assignCategory("Bulk Import");
		
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		assetMgmt.Delete_Bulk_Device_Import("MQTT_Encrypt_Decrypt_NS");
		smoke.Verify("Success!");
		assetMgmt.VerifyRemoveAsset_BulkUpload("MQTT_Bulk_Asset");
		
		
	}
	
}
