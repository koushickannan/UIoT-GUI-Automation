package com.hpe.iot.e2eflows;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.ACP;
import com.automation.pageobjects.ApplicationMgmt;
import com.automation.pageobjects.AssetMgmt;
import com.automation.pageobjects.AssetTopology;
import com.automation.pageobjects.CustomerManagement;
import com.automation.pageobjects.DefaultResources;
import com.automation.pageobjects.DeviceController;
import com.automation.pageobjects.SmokeTest;
import com.hpe.base.Base;
import com.hpe.iot.api.utilities.TestData;

import hpe.util.mqtt.client.Mqttpublish;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class Driver_E2EFlows extends Base {

	SmokeTest smoke = new SmokeTest();
	DefaultResources def = new DefaultResources();
	CustomerManagement cust = new CustomerManagement();
	AssetMgmt asset = new AssetMgmt();

	ACP acp = new ACP();
	ApplicationMgmt app = new ApplicationMgmt();
	Mqttpublish mqttUplink = new Mqttpublish();
	AssetTopology ap = new AssetTopology();
	Http_E2EFlow http = new Http_E2EFlow();
	DeviceController dc = new DeviceController();

	/*
	 * @BeforeSuite(groups = { "Smoke", "Critical_Regression" }) public void start()
	 * throws Exception { setup();
	 * 
	 * if (def.checkIfAcpAndAppExists()) { login(prop.getProperty("username"),
	 * prop.getProperty("password")); def.CreateACP(); def.Verify("Success");
	 * def.AddNewApplciation(); def.Verify("Success"); logout(); }
	 * 
	 * }
	 * 
	 * @BeforeTest(groups = { "Smoke", "Critical_Regression" }) public void
	 * connection_setup() throws IOException { try { if
	 * (Boolean.valueOf(Base_API.prop_api.getProperty("dbValidation"))) {
	 * 
	 * Tunneling.dbserverTunnel(); JDBCUtils.Connect();
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.out.println("Tunneling and DB Setup Fail"); }
	 * 
	 * }
	 * 
	 * @AfterSuite(groups = { "Smoke", "Critical_Regression", "Functional" }) public
	 * void clean() throws Exception { closeBrowser(); }
	 */
	
	/*@BeforeSuite(groups = { "Smoke", "Critical_Regression" })
	public void start() throws Exception {
		setup();

	}*/

	@AfterMethod(groups = { "Smoke", "Critical_Regression", "Functional" })
	public void exit(ITestResult result) throws Exception {

		logout();
	}

	@Test(priority = 600,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Upload CP for MQTT Device,ALM-ID:92274", enabled = true)
	public void UploadCPForMQTT() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		smoke.ContainerUpload("mqttcp.xml");
		smoke.Verify("Success!");

	}

	@Test(priority = 601,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Upload DP for MQTT Device,ALM-ID:92275", enabled = true)
	public void UploadDPForMQTT() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		smoke.DeviceMgmtUpload("mqttdp.xml", true, assetDetailsProp.getProperty("MQ_containerProfileName"));
		smoke.Verify("Success!");

	}

	@Test(priority = 602,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Create MQTT Device Controller,ALM-ID:92258", enabled = true)
	public void CreateMQTTDeviceController() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		if (dc.CreateMQTTDeviceController()) {
			dc.VerifyDeviceController("Success");
			result = true;
		}

		_assert.equals(result, "DC Creation ");
	}

	@Test(priority = 603, groups = { "Smoke",
			"Critical_Regression" }, description = " MQTT Device Controller Authentication,ALM-ID:92281", enabled = true,dependsOnMethods = { "CreateMQTTDeviceController","com.automation.drivers.DriverScript_Smoke.TestCase_80533" })
	public void MQTTDC_Authentication() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		boolean result = false;
		if (mqttUplink.MQTTDC_Subscribe(assetDetailsProp.getProperty("mqtt.dc.name"),
				assetDetailsProp.getProperty("mqtt.dc.password"), assetDetailsProp.getProperty("mqtt.broker.url"),
				false)) {
			result = true;
		}
		_assert.equals(result, "DC Authentication");
	}

	@Test(priority = 604, groups = { "Smoke",
			"Critical_Regression" }, description = " MQTT Device Controller Authentication for Invalid credentials,ALM-ID:92282", enabled = true,dependsOnMethods = { "CreateMQTTDeviceController","com.automation.drivers.DriverScript_Smoke.TestCase_80533" })
	public void MQTTDC_InvalidCredentials() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		if (!mqttUplink.MQTTDC_Subscribe(assetDetailsProp.getProperty("mqtt.dc.name"), "invalid",
				assetDetailsProp.getProperty("mqtt.broker.url"), false)) {
			result = true;
		}
		_assert.equals(result, "DC Authentication with Invalid Credentials");
	}

	@Test(priority = 605, groups = { "Smoke",
			"Critical_Regression" }, description = " MQTT Device Controller Authentication-FOr Unauthorized topic,ALM-ID:92283", enabled = true,dependsOnMethods = { "CreateMQTTDeviceController","com.automation.drivers.DriverScript_Smoke.TestCase_80533" })
	public void MQTTDC_Unauthorized_Topic() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		if (!mqttUplink.MQTTDC_Subscribe(assetDetailsProp.getProperty("mqtt.dc.name"),
				assetDetailsProp.getProperty("mqtt.dc.password"), assetDetailsProp.getProperty("mqtt.broker.url"),
				true)) {
			result = true;
		}

		_assert.equals(result, "DC Authentication with Invalid Credentials");

	}

	@Test(priority = 606,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Create MQTT Basic Authentication Asset,ALM-ID:92277", enabled = true)
	public void CreateMQTTAsset_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		asset.createMQTTAsset(true, "BASIC_AUTH", false);
		smoke.Verify("Success");
		asset.retrieveAssetResourceID(assetDetailsProp.getProperty("MQ_asset_name"), "MQ_asset_resourceID");

	}

	@Test(priority = 607,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Adding ACP to MQTT Device Basic Authentication,8363", enabled = true)

	public void AddDeviceToACP_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("MQ_asset_resourceID"));
		smoke.Verify("Success");
	}

	@Test(priority = 608,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Subscribing to MQTT Device Conatiner for Basic Security", enabled = true)

	public void AddSubscriptionToDeviceContainer_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("MQ_asset_resourceID"), "commands");
		smoke.Verify("Success");
	}

	@Test(priority = 609,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify MQTT Uplink for Basic Security,ALM-ID:92261", enabled = true)

	public void MqttUplink_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQ_asset_resourceID"),"MQ_asset_name");

		if (mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQ_deviceUsername"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy")) {
			login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
			ap.Verify_Readings(assetDetailsProp.getProperty("MQ_asset_resourceID"),
					Integer.toString(initialReadings + 1),"MQ_asset_name");
			result = true;
		}

		_assert.equals(result, "Mqtt Uplink for Basic Authentication Device");
	}

	@Test(priority = 610,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Basic Authentication Asset connection to broker without credentials,ALM-ID:92265", enabled = true)

	public void MqttConnectBroker_BA_Nocredentials() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		if (!mqttUplink.uplinkMQTT("NoCred", "NoCred", assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy")) {
			result = true;
		}

		_assert.equals(result, "Broker Connection withour Credentials for Basic Authentication Device");
	}

	@Test(priority = 611,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Unauthorized MQTT Uplink for Basic Security,ALM-ID:92263", enabled = true)

	public void MqttUplink_Unauthorised_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		if (!mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQ_deviceUsername_Bad"),
				assetDetailsProp.getProperty("MQ_devicepassword_Bad"), assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "DUMMY")) {
			result = true;
		}

		_assert.equals(result, "Mqtt UplinkUnAuthorized-username and password");
	}

	@Test(priority = 612,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Unauthorized MQTT Uplink for Basic Security,ALM-ID:92267", enabled = true)

	public void MqttUplink_Unauthorised_Topic_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		asset.createMQTTAsset(true, "BASIC_AUTH", true);
		smoke.Verify("Success");
		if (!mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQ_deviceUsername"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("mqtt.broker.url"), true,
				assetDetailsProp.getProperty("MQ_deviceUsername_unauthorized"))) {
			result = true;

		}

		_assert.equals(result, "Unauthorized Topic Access is Restriction for Basic Authentication Devices");
	}

	@Test(priority = 613,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Downlink for the Basic Auth Device,ALM-ID:92272", enabled = true)
	public void MqttDownlink_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQ_asset_resourceID"),"MQ_asset_name");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQ_deviceUsername"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));
		if (http.API_HttpDownlink_new("commands", "MQ_asset_name")) {
			login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
			ap.Verify_Readings(assetDetailsProp.getProperty("MQ_asset_resourceID"),
					Integer.toString(initialReadings + 1),"MQ_asset_name");
			result = true;

		}

		_assert.equals(result, "Downlink Message Received in the DSM");
	}

	@Test(priority = 614,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Validate whether Basic Auth Device received the downlink from platform,ALM-ID:92272", enabled = true)
	public void MqttDownlinkDevice_Subscribe_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		FileInputStream fis = new FileInputStream("mqtt.properties");
		Properties testProp = new Properties();
		testProp.load(fis);
		if (testProp.getProperty("mqtt.downlink.message").contains(TestData.getDownlinkBody_PatternMatch())) {
			result = true;

		}
		_assert.equals(result, "Basic Auth Device got the Downlink message sent from the UIoT Platform");
	}

	@Test(priority = 615,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Create MQTT NO Security Authentication Device,ALM-ID:92276", enabled = true)

	public void CreateMQTTAsset_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		asset.createMQTTAsset(true, "NO_SECURITY", false);
		smoke.Verify("Success");
		asset.retrieveAssetResourceID(assetDetailsProp.getProperty("MQ_asset_name"), "MQ_asset_resourceID");

	}

	@Test(priority = 616,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Adding ACP to MQTT Device_NS", enabled = true)

	public void AddDeviceToACP_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("MQ_asset_resourceID"));
		smoke.Verify("Success");
	}

	@Test(priority = 617,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Subscribing to MQTT Device Conatiner for No Security Device", enabled = true)

	public void AddSubscriptionToDeviceContainer_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("MQ_asset_resourceID"), "commands");
		smoke.Verify("Success");

	}

	@Test(priority = 618,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify MQTT Uplink for No Security Device,ALM-ID:92262", enabled = true)

	public void MqttUplink_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQ_asset_resourceID"),"MQ_asset_name");

		if (mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQ_deviceUsername"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "DUMMY")) {
			login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
			ap.Verify_Readings(assetDetailsProp.getProperty("MQ_asset_resourceID"),
					Integer.toString(initialReadings + 1),"MQ_asset_name");
			result = true;
		}
		_assert.equals(result, "No Security Device Uplink");

	}

	@Test(priority = 619,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Unauthorized MQTT Uplink for No Security Device,ALM-ID:92264", enabled = true)

	public void MqttUplink_Unauthorised_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		if (!mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQ_deviceUsername_Bad"),
				assetDetailsProp.getProperty("MQ_devicepassword_Bad"), assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "DUMMY")) {
			result = true;

		}
		_assert.equals(result, "Unauthorized credential Restriction for No Security Device");
	}

	@Test(priority = 620,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Unauthorized MQTT Uplink for No Security Device,ALM-ID:92268", enabled = true)

	public void MqttUplink_Unauthorised_Topic_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		asset.createMQTTAsset(true, "NO_SECURITY", true);
		smoke.Verify("Success");

		if (!mqttUplink.uplinkMQTT(assetDetailsProp.getProperty("MQ_deviceUsername"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("mqtt.broker.url"), true,
				assetDetailsProp.getProperty("MQ_deviceUsername_unauthorized"))) {
			result = true;

		}

		_assert.equals(result, "Unauthorized Topic Access is Restriction-NoSecurity Device");
	}

	@Test(priority = 621,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Downlink for the No Security Device,ALM-ID:92271", enabled = true)
	public void MqttDownlink_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("MQ_asset_resourceID"),"MQ_asset_name");

		mqttUplink.downlinkMQTT(assetDetailsProp.getProperty("MQ_deviceUsername"),
				assetDetailsProp.getProperty("MQ_devicePassword"), assetDetailsProp.getProperty("mqtt.broker.url"));

		if(http.API_HttpDownlink_new("commands", "MQ_asset_name"))  {
 

			login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
			ap.Verify_Readings(assetDetailsProp.getProperty("MQ_asset_resourceID"),
					Integer.toString(initialReadings + 1),"MQ_asset_name");
			result = true;

		}

		_assert.equals(result, "Downlink Message Received in the DSM");
	}

	@Test(priority = 622,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Validate whether No Security Device received the downlink from platform,ALM-ID:92280", enabled = true)
	public void MqttDownlinkDevice_Subscribe_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		boolean result = false;
		FileInputStream fis = new FileInputStream("mqtt.properties");
		Properties testProp = new Properties();
		testProp.load(fis);
		if (testProp.getProperty("mqtt.downlink.message").contains(TestData.getDownlinkBody_PatternMatch())) {
			result = true;

		}
		_assert.equals(result, "No security Device got the Downlink message sent from the UIoT Platform");
	}

	@Test(priority = 623,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Basic Authentication Asset connection to broker without credentials,ALM-ID:92266", enabled = true)
	public void MqttConnectBroker_NS_Nocredentials() throws Exception {
		logger.assignCategory("E2E Flows");

		boolean result = false;
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		if (!mqttUplink.uplinkMQTT("No Cred", "Nocred", assetDetailsProp.getProperty("MQ_asset_name"),
				assetDetailsProp.getProperty("mqtt.broker.url"), false, "Dummy")) {

			result = true;
		}

		_assert.equals(result, "Broker Connection withour Credentials for No Security Device");
	}

	/* Http E2E Flow */

	@Test(priority = 624,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verfiy if upload container profile is Successfull for HTTP Device, ALM_ID : 96897", enabled = true)

	public void UploadCPForHttp() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		smoke.ContainerUpload("httpcp.xml");
		smoke.Verify("Success!");

	}

	@Test(priority = 625,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify if upload device profie is Successfull for HTTP Device, ALM_ID : 96898", enabled = true)
	public void UploadDPForHttp() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		smoke.DeviceMgmtUpload("HTTP_DP.xml", true, assetDetailsProp.getProperty("HTTP_Asset_CP"));
		smoke.Verify("Success!");

	}

	@Test(priority = 626,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Create HTTP Device with AuthType: NO_SECURITY", enabled = false)
	public void CreateHttpAsset_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		asset.CreateHttpAsset(true, "NO_SECURITY");
		smoke.Verify("Success");
		asset.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Asset"), "HTTP_Asset_RI");

	}

	@Test(priority = 627,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Adding Http device to ACP target resource. AuthType:No_Security", enabled = false)
	public void AddHttpDeviceToACP_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_RI"));
		smoke.Verify("Success");
	}

	@Test(priority = 628,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Subscribing HTTP Device Conatiner. AuthType:No_Security", enabled = false)
	public void AddSubscriptionToHttpDeviceContainer_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_RI"), "commands");
		smoke.Verify("Success");

	}

	@Test(priority = 629,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http Uplink for AuthType: NO_SECURITY -- CR=35190", enabled = false)

	public void HttpUplink_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Asset_RI"),"HTTP_Asset");
		logout();
		http.API_HttpUplink(assetDetailsProp.getProperty("HTTP_Asset"),assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "commands");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Asset");

	}

	@Test(priority = 630,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http downlink. AuthType:No_Security", enabled = false)
	public void HttpDownlink_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Asset_RI"),"HTTP_Asset");
		logout();
		http.API_HttpDownlink("commands", assetDetailsProp.getProperty("HTTP_Asset"));
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Asset");

	}

	@Test(priority = 631,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http uplink with Nested container.AuthType:No_Security -- CR=35190", enabled = false)

	public void HttpUplink_Nestedcontainer_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Asset_RI"),"HTTP_Asset");
		logout();
		http.API_HttpUplink_NestedContainer(assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "uplink1/uplink2");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Asset");

	}

	@Test(priority = 632,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http uplink with unauthorized credentails.AuthType:No_Security", enabled = false)

	public void HttpUplink_Unauthorized_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		http.API_HttpUplink_BadCredentials(assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_Username_Bad"),
				assetDetailsProp.getProperty("HTTP_Asset_Password_Bad"), "commands");

	}

	@Test(priority = 633,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http downlink with Nested container.AuthType:No_Security", enabled = false)

	public void HttpDownlink_Nestedcontainer_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Asset_RI"),"HTTP_Asset");
		logout();
		http.API_HttpDownlink_NestedContainer("uplink1/uplink2", assetDetailsProp.getProperty("HTTP_Asset"));
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Asset");

	}

	@Test(priority = 634,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http uplink without mandatory headers.AuthType:No_Security", enabled = false)

	public void HttpUplink_Headers_NS() throws Exception {
		logger.assignCategory("E2E Flows");

		http.API_HttpUplink_Headers(assetDetailsProp.getProperty("HTTP_Asset"), "commands",assetDetailsProp.getProperty("HTTP_Asset_Username"));
	}

	@Test(priority = 635,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http downlink without mandatory headers.AuthType:No_Security", enabled = false)

	public void HttpDownlink_Headers_NS() throws Exception {

		http.API_HttpDownlink_Headers("commands", "HTTP_Asset");
	}

	@Test(priority = 636,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Create HTTP Device with AuthType: BASIC_AUTH, ALM_ID : 96900", enabled = true)

	public void CreateHttpAsset_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		asset.CreateHttpAsset(true, "BASIC_AUTH");
		smoke.Verify("Success");
		asset.retrieveAssetResourceID(assetDetailsProp.getProperty("HTTP_Asset"), "HTTP_Asset_RI");

	}

	@Test(priority = 637,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Adding Http device to ACP target resource.AuthType: BASIC_AUTH, ALM_ID : 96902", enabled = true)
	public void AddHttpDeviceToACP_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		acp.attachDeviceToAcp(assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_RI"));
		smoke.Verify("Success");
	}

	@Test(priority = 638,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Subscribing HTTP Device Conatiner.AuthType: BASIC_AUTH, ALM_ID : 96901", enabled = true)
	public void AddSubscriptionToHttpDeviceContainer_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		app.addSubscriptionToApplication(assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_RI"), "commands");
		smoke.Verify("Success");
	}

	@Test(priority = 639,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http Uplink for AuthType: BASIC_AUTH, ALM_ID : 96886", enabled = true)

	public void API_HttpUplink_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Asset_RI"),"HTTP_Asset");
		logout();
		http.API_HttpUplink(assetDetailsProp.getProperty("HTTP_Asset"),assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "commands");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));

		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Asset");

	}

	@Test(priority = 640,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http downlink.AuthType: BASIC_AUTH, ALM_ID : 96895", enabled = true)
	public void API_HttpDownlink_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount(assetDetailsProp.getProperty("HTTP_Asset_RI"),"HTTP_Asset");
		logout();
		http.API_HttpDownlink("commands", assetDetailsProp.getProperty("HTTP_Asset"));
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		ap.Verify_Readings(assetDetailsProp.getProperty("HTTP_Asset_RI"), Integer.toString(initialReadings + 1),"HTTP_Asset");

	}

	@Test(priority = 641,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http uplink with unauthorized credentials.AuthType: BASIC_AUTH, ALM_ID : 96888", enabled = true)

	public void API_HttpUplink_Unauthorized_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		http.API_HttpUplink_BadCredentials(assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_Username_Bad"),
				assetDetailsProp.getProperty("HTTP_Asset_Password_Bad"), "commands");

	}

	@Test(priority = 642,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http uplink with Nested container.AuthType: BASIC_AUTH, ALM_ID : 96887", enabled = true)

	public void API_HttpUplink_Nestedcontainer_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount_NC(assetDetailsProp.getProperty("HTTP_Asset_RI"),assetDetailsProp.getProperty("HTTP_Asset"),"uplink1","uplink2");
		logout();
		http.API_HttpUplink_NestedContainer(assetDetailsProp.getProperty("HTTP_Asset"),
				assetDetailsProp.getProperty("HTTP_Asset_Username"),
				assetDetailsProp.getProperty("HTTP_Asset_Password"), "uplink1/uplink2");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		ap.Verify_Readings_NC(assetDetailsProp.getProperty("HTTP_Asset_RI"), Integer.toString(initialReadings + 1), assetDetailsProp.getProperty("HTTP_Asset"),"uplink1","uplink2");

	}

	@Test(priority = 643,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http downlink with Nested container.AuthType:BASIC_AUTH, ALM_ID : 96894", enabled = true)

	public void API_HttpDownlink_Nestedcontainer_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		int initialReadings = ap.GetReadingCount_NC(assetDetailsProp.getProperty("HTTP_Asset_RI"),assetDetailsProp.getProperty("HTTP_Asset"),"uplink1","uplink2");
		logout();
		http.API_HttpDownlink_NestedContainer("uplink1/uplink2", assetDetailsProp.getProperty("HTTP_Asset"));
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		ap.Verify_Readings_NC(assetDetailsProp.getProperty("HTTP_Asset_RI"), Integer.toString(initialReadings + 1), assetDetailsProp.getProperty("HTTP_Asset"),"uplink1","uplink2");

	}

	@Test(priority = 644,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http uplink without mandatory headers.AuthType:BASIC_AUTH, ALM_ID : 96889", enabled = true)

	public void API_HttpUplink_Headers_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		http.API_HttpUplink_Headers(assetDetailsProp.getProperty("HTTP_Asset"), "commands",assetDetailsProp.getProperty("HTTP_Asset_Username"));

	}

	@Test(priority = 645,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke",
			"Critical_Regression" }, description = "Verify Http downlink without mandatory headers.AuthType:BASIC_AUTH, ALM_ID : 96896", enabled = true)

	public void API_HttpDownlink_Headers_BA() throws Exception {
		logger.assignCategory("E2E Flows");

		http.API_HttpDownlink_Headers("commands", "HTTP_Asset");
	}
}
