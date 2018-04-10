package com.automation.drivers;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.AddressPoolMgmt;
import com.automation.pageobjects.CustomerAccProvisioning;
import com.automation.pageobjects.CustomerManagement;
import com.automation.pageobjects.Device_TCOM;
import com.automation.pageobjects.MultiTenancy;
import com.automation.pageobjects.PolicyManagement;
import com.automation.pageobjects.SmokeTest;
import com.hpe.base.Base;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_CriticalRegression_1_4 extends Base {

	AddressPoolMgmt addPool = new AddressPoolMgmt();
	PolicyManagement qos=new PolicyManagement();
	Device_TCOM dto=new Device_TCOM();
	MultiTenancy multi= new MultiTenancy();
	CustomerAccProvisioning accProvision = new CustomerAccProvisioning();
	CustomerManagement cust = new CustomerManagement();
	SmokeTest st      = new SmokeTest(); 
	
	/*@BeforeSuite(groups = { "Smoke", "Regression", "Functional" })
	public void start() throws Exception {
		setup();
	}


	@AfterSuite(groups = { "Smoke", "Regression", "Functional" })
	public void clean() throws Exception {
		closeBrowser();
	}*/

	@AfterMethod(groups = { "Smoke", "Critical_Regression"})
	public void exit(ITestResult result) throws Exception {
		
		st.logout();
		//closeBrowser();
	}
	
	
	
	 
	@Test(priority = 82, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Tile , ALM Id = 88392", enabled = true)
	public void AddPoolMgmt_VerifyTile() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password")); 

		//login("sep13two", prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Verify Tile");
		addPool.VerifyTile();
	}
	
	
	
	 
	@Test(priority = 83, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Create DevEUI Address Pool , ALM Id = 88393", enabled = true)
	public void AddPoolMgmt_CreateDevEUI() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create DevEUI");
		addPool.CreateDevEUI(true);
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.Verify();
	}
	
	
	 
	 
	@Test(priority = 84, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Create DevAddr Address Pool , ALM Id = 88394", enabled = true)
	public void AddPoolMgmt_CreateDevAddr() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create DevEUI");
		addPool.CreateDevAddr(true,"10");
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.Verify();
	}
	
	 
	 
	@Test(priority = 85, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Pool Search , ALM Id = 88403", enabled = true)
	public void AddPoolMgmt_VerifyPoolSearch() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify Search Pool");
		addPool.SearchPool(false);

	}
	
	
	
	 
	@Test(priority = 86, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Manual Entry of Addresses , ALM Id = 88404", enabled = true)
	public void AddPoolMgmt_VerifyManualAddEntry() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify Manual Entry");
		addPool.VerifyManualAddressEntry();

	}
	
	@Test(priority = 87, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Individual Pool Search , ALM Id = 88410", enabled = true)
	public void AddPoolMgmt_IndividualPoolSearch() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify Individual Pool Search");
		addPool.SearchPool(true);
		
	}
	
	@Test(priority = 88, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Pool Inheritance , ALM ID = 88408", enabled = true)
	public void AddPoolMgmt_PoolInheritance() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create Sub Customer");
		addPool.CreateSubCustomer();
		
		logger.log(LogStatus.INFO, "Verify Sub Customer");
		addPool.Verify();
		
		logger.log(LogStatus.INFO, "Create DevEUI");
		addPool.CreateDevEUI(false);
		
		logger.log(LogStatus.INFO, "Verify Create DevEUI");
		addPool.Verify();
		
	    logger.log(LogStatus.INFO, "Create Pool with Sub Customer");
	    addPool.CreateDeviceWithPoolAddr(true);
	    
	    logger.log(LogStatus.INFO, "Verify Success");
	    addPool.Verify();
	}
	
	@Test(priority = 89, groups = { "Critical_Regression" }, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, description = "Verify Pool Availability , ALM Id = 88409", enabled = true)
	public void AddPoolMgmt_PoolAvailability() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create Pool with Parent Customer");
	    addPool.CreateDeviceWithPoolAddr(false);
	    
	    logger.log(LogStatus.INFO, "Verify Success");
	    addPool.Verify();

		logger.log(LogStatus.INFO, "Verify Pool Availability");
		addPool.poolAvailability();
		
	}
	

	
	@Test(priority = 90, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Pool creation failure for same range , ALM Id = 88414", enabled = true)
	public void AddPoolMgmt_CreateDevEUIForSameRange() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create DevEUI Pool 1");
		addPool.CreateDevEUISameRange(false,"10","11");
		
		logger.log(LogStatus.INFO, "Verify Pool 1 Creation");
		addPool.Verify();
		
		logger.log(LogStatus.INFO, "Create DevEUI Pool 2");
		addPool.CreateDevEUISameRange(false,"10","11");
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.VerifyPoolForSameRange();
	}
	

	
	@Test(priority = 91, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify DevAddr MSP , ALM Id = 88412", enabled = true)
	public void AddPoolMgmt_DevAddrMSP() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify Individual Pool Search");
		addPool.DevAddrMSPCheck();
		
	}
	
	
	
	@Test(priority = 92, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Pool Overlap , ALM Id = 88407", enabled = true)
	public void AddPoolMgmt_PoolOverlap() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create DevEUI");
		addPool.CreateDevEUISameRange(false,"10","11");
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.VerifyPoolForSameRange();
	}
	
	@Test(priority = 93, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Pool Status Change , ALM Id = 88415", enabled = true)
	public void AddPoolMgmt_PoolStatusChange() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		
	    logger.log(LogStatus.INFO, "Verify Pool Status Change");
	    addPool.poolAvailability();
	}
	
	
	
	
	
	 
	@Test(priority = 94, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Update DevEUI Pool , ALM Id = 88395", enabled = true)
	public void AddPoolMgmt_UpdateDevEUI() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Update DevEUI");
		addPool.UpdateDevEUI();
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.VerifyDevEUIUpdate();
	}
	
	
	
	 
	@Test(priority = 95, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Update DevAddr Pool , ALM Id = 88396", enabled = true)
	public void AddPoolMgmt_UpdateDevAddr() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Update DevAddr");
		addPool.UpdateDevAddr();
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.VerifyDevAddrUpdate();
	}
	
	
	
	 
	@Test(priority = 96, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Delete DevEUI , ALM Id = 88397", enabled = true)
	public void AddPoolMgmt_DeleteDevEUI() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Create DevEUI");
		addPool.CreateDevEUI(false);
		
		logger.log(LogStatus.INFO, "Verify Create DevEUI");
		addPool.Verify();
		
	    logger.log(LogStatus.INFO, "Delete DevEUI");
	    addPool.DeletePoolDevEUI();
	}
	
	
	
	 
	@Test(priority = 97, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Create Default DevEUI Address Pool , ALM Id = 88398", enabled = true)
	public void AddPoolMgmt_CreateDefaultDevEUI() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create DevEUI");
		addPool.CreateDevEUI(true);
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.Verify();
	}
	
	
	
	 
	@Test(priority = 98, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Create Default DevAddr Address Pool , ALM Id = 88417", enabled = true)
	public void AddPoolMgmt_CreateDefaultDevAddr() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create Deafult DevAddr");
		addPool.CreateDevAddr(true,"12");
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.Verify();
	}
	
	
	 
	 
	@Test(priority = 99, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Delete DevAddr Pool , ALM Id = 88398", enabled = true)
	public void AddPoolMgmt_DeleteDevAddr() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create DevEUI");
		addPool.CreateDevAddr(false,"12");
		
		logger.log(LogStatus.INFO, "Verify Create DevEUI");
		addPool.Verify();
		
	    logger.log(LogStatus.INFO, "Delete DevEUI");
	    addPool.DeletePoolDevAddr();
	}
	
	
	  
	 
	@Test(priority = 100, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify Address Exists after Pool Deletion , ALM Id = 88405", enabled = true)
	public void AddPoolMgmt_VerifyAddrAfterPoolDelete() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify Manual Entry");
		addPool.VerifyDevEUIAfterPoolDelete();

	}
	
	
	 
	 
	@Test(priority = 101, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Create Multiple Pools for Same Customer", enabled = true)
	public void AddPoolMgmt_CreateMultiplePool() throws Exception {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		//login("sep13two", prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Create DevEUI");
		addPool.CreateDevEUI(false);
		
	    logger.log(LogStatus.INFO, "Verify");
	    addPool.Verify();
	}
	
	/**
	 * Author Manish
	 */
	@Test(priority = 102, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify if a new policy can be created , ALM Id = 88418", enabled = true)
	public void QoS_verify_new_Policy_Created() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify if a new policy can be created");
		qos.Validate_Upload_QoS_Device_Profile();
		qos.Verify_if_a_new_policy_can_be_created();
}
	/**
	 * Author Manish
	 */
	
	@Test(priority =103, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Validate if user can add key value pair dynamically , ALM Id = 88419", enabled = true)
	public void QoS_validate_Dynamic_Key() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Validate if user can add key value pair dynamically");
		qos.Validate_if_user_can_add_key_value_pair_dynamically();
}
	
	/**
	 * Author Manish
	 */
	
	@Test(priority = 104, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify if a error message is displayed if QoS name is not in specific format , ALM Id =88420", enabled = true)
	public void QoS_verify_Error_Msg() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Validate if user can add key value pair dynamically");
		qos.verify_error_message_if_QoS_name_is_not_in_specific_format();
}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 105, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify if QoS can be updated , ALM Id = 88423", enabled = true)
	public void QoS_verify_Update() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify if QoS can be updated");
		qos.Verify_QoS_canbe_updated();
}

	/**
	 * Author Manish
	 */
	
	@Test(priority =106, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify if QoS list can displayed , ALM Id = 88425", enabled = true)
	public void QoS_verify_List_Update() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify if QoS list can displayed");
		qos.Verify_QoS_list_can_displayed();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 107, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify if QoS can be provisioned for customers , ALM Id = 88426", enabled = true)
	public void QoS_provisioned_for_customers() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		//login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		login(prop.getProperty("cust_username"),prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify if QoS can be provisioned for customers");
		qos.Verify_QoS_provisioned_for_customers();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 108, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "011-Verify if Customer can specify the policy name during device provisioning , ALM Id = 88428", enabled = true)
	public void QoS_policy_Nmae_Device_provison() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		//login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		login(prop.getProperty("cust_username"),prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "011-Verify if Customer can specify the policy name during device provisioning");
		qos.Specify_the_policy_name_during_device_provisioning(true, false);
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority =109, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify if delete fails for a policy in case devices are assigned , ALM Id = 88431", enabled = true)
	public void QoS_delete_Policy_assigend_Asset() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		//login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		login(prop.getProperty("cust_username"),prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify if delete fails for a policy in case devices are assigned");
		qos.delete_fails_for_policy_if_devices_assigned();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority =110, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify if only integer part of policy name should be assigned to device asset parameter , ALM Id = 88432", enabled = true)
	public void QoS_only_Integer_PartOf_Policy_Assigend() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		//login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		login(prop.getProperty("cust_username"),prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify if only integer part of policy name should be assigned to device asset parameter");
		qos.Specify_the_policy_name_during_device_provisioning(true, false);
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 111, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "013-Verify if a policy name is changed a new QoS name has to be propagated to sub systems , ALM Id = 88430", enabled = true)
	public void QoS_policy_name_is_changed_new_QoS_name() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		//login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		login(prop.getProperty("cust_username"),prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "013-Verify if a policy name is changed a new QoS name has to be propagated to sub systems");
		qos.verify_a_policy_name_changed_new_QoS_name();
		}
	
	/**
	 * Author Manish
	 */
	
	@Test(priority = 112, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Verify if QoS can be deleted if no devices are assigned , ALM Id = 88424", enabled = true)
	public void QoS_delete_policy() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		//login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		login(prop.getProperty("cust_username"),prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "Verify if QoS can be deleted if no devices are assigned");
		qos.Delete_QoS_no_Device_assigend();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 113, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "004-Verify if mutiple key value pairs can be added , ALM Id = 88421", enabled = true)
	public void QoS_multiple_key_added() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		//login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		login(prop.getProperty("cust_username"),prop.getProperty("new_password"));

		logger.log(LogStatus.INFO, "004-Verify if mutiple key value pairs can be added");
		qos.Validate_if_user_can_add_key_value_pair_dynamically();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 114, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "005-Verify if key values pairs can be deleted , ALM Id = 88422", enabled = true)
	public void QoS_multiple_key_Deleted() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "005-Verify if key values pairs can be deleted");
		qos.key_value_pairs_deleted();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 115, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "TC-001-P-Validate the user is able to upload the Tcom device profile", enabled = true)
	public void Tcom_Device_Upload() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "TC-001-P-Validate the user is able to upload the Tcom device profile");
		dto.Validate_Upload_TCOM_Device_Profile();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 116, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "TC-002-P- Validate the create asset functinality with Tcom device profile , ALM Id = 88482", enabled = true)
	public void Tcom_Device_Create() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "TC-002-P- Validate the create asset functinality with Tcom device profile");
		dto.create_Asset_with_TCOM_DeviceProfile(true, false,true);
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 117, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Without device auto reg and Key inputValidate the device creation with only asset_name,type,tenant,device profile , ALM Id = 88483", enabled = true)
	public void Tcom_without_auto_genration_tcom_Device_Create() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Without device auto reg and Key inputValidate the device creation with only asset_name,type,tenant,device profile");
		dto.without_autoreg_keygenration(true, false,true);
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 118, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = " Validate the device With auto reg and no Key input , ALM Id = 88488", enabled = true)
	public void Tcom_create_device_with_autoReg_NoKeyInput() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, " Validate the device With auto reg and no Key input:");
		dto.createDevice_with_AutoReg_and_noKeyInput(true, false);
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 119, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Device should be  updated successfully without auto reg and key genration if it was generated previously then it should not be changed", enabled = true)
	public void Tcom_update_device_without_autoReg_NoKeyInput() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Device should be  updated successfully without auto reg and key genration if it was generated previously then it should not be changed");
		dto.update_device_witiout_keygen_autoreg();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 120, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Device should be  updated successfully with auto reg, key genration/update , ALM Id = 88489", enabled = true)
	public void Tcom_update_device_with_autoReg_and_KeyInput() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Device should be  updated successfully with auto reg, key genration/update ");
		dto.update_device_with_AutoReg_and_Key_input();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 121, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Device should be  deleted successfully and keys should be deleted from database", enabled = true)
	public void Tcom_delete_device_with_autoReg_and_KeyInput() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Device should be  deleted successfully and keys should be deleted from database");
		dto.delete_device();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 122, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Device should be  created successfully without auto reg and key genration should happen as entered , ALM Id = 88485", enabled = true)
	public void Tcom_Create_device_without_autoReg_and_KeyInputValue() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Device should be  created successfully without auto reg and key genration should happen as entered");
		dto.Create_Device_without_AutoReg_With_InputKeyValue(true,false,true );
		}
	
	/**
	 * Author Manish
	 */
	
	@Test(priority = 123, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Device should be  created successfully with auto reg and key genration should happen as entered , ALM Id = 88489", enabled = true)
	public void Tcom_Create_device_with_autoReg_and_KeyInputValue() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Device should be  created successfully with auto reg and key genration should happen as entered");
		dto.Create_Device_with_AutoReg_With_InputKeyValue(true,false );
		}
	
	/**
	 * Author Manish
	 */
	
	@Test(priority = 124, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Device should be  updated successfully without auto reg and key genration should happen as entered", enabled = true)
	public void Tcom_Update_device_Without_autoReg_and_KeyInputValue() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Device should be  updated successfully without auto reg and key genration should happen as entered");
		dto.update_device_without_AutoReg_and_KeyInputValue(true,false);
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 125, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Device should be  updated successfully with auto reg, key genration should happen with updated value  as entered", enabled = true)
	public void Tcom_Update_device_With_autoReg_and_KeyInputValue() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "Device should be  updated successfully with auto reg, key genration should happen with updated value  as entered");
		dto.update_device_with_AutoReg_and_KeyInputValue(true, false);
		}

	/**
	 * Author Manish
	 */
	
	@Test(priority = 126, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "User should be able to upload csv with the Tcom device profile for Lora ABP device , ALM Id = 88493", enabled = true)
	public void Tcom_upload_Device() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "User should be able to upload csv with the Tcom device profile for Lora ABP device");
		dto.device_Import();
		}
	/**
	 * Author Manish
	 */
	
	@Test(priority = 127, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "User should be able to update csv with the Tcom device profile , ALM Id = 88494", enabled = true)
	public void Tcom_update_Device() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "User should be able to update csv with the Tcom device profile");
		dto.update_device_Import();
		}
	
	/**
	 * Author Manish
	 */
	
	@Test(priority = 128, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "User should be able to delete some value csv with the Tcom device profile , ALM Id = 88495", enabled = true)
	public void Tcom_delete_Device() throws Throwable {

		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		//login(prop.getProperty("username"),prop.getProperty("password"));

		logger.log(LogStatus.INFO, "User should be able to delete csv with the Tcom device profile");
		dto.delete_device_Import();
		}
	
	/**
	 * Jeevan
	 * @throws Exception
	 */
	
	@Test(priority = 129, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Create User Profile , ALM Id = 88433", enabled = true)
	public void CustomerAccProvision_CreateUserProfile() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		
		logger.log(LogStatus.INFO, "Create Role");
		accProvision.CreateRole();
		
		logger.log(LogStatus.INFO, "Create User Profile");
		accProvision.CreateUserProfile();
		
		logger.log(LogStatus.INFO, "Verify Success Message");
		accProvision.Verify();
		
	}
	
	/**
	 * Jeevan
	 * @throws Exception
	 */
	
	@Test(priority = 130, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533","CustomerAccProvision_CreateUserProfile"}, groups = { "Critical_Regression" }, description = "Update User Profile , ALM Id = 88434", enabled = true)
	public void CustomerAccProvision_UpdateUserProfile() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		
		logger.log(LogStatus.INFO, "Update User Profile");
		accProvision.UpdateProfile();
	}
	
	/**
	 * Jeevan
	 * @throws Exception
	 */
	@Test(priority = 131, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533","CustomerAccProvision_CreateUserProfile"}, groups = { "Critical_Regression" }, description = "Verify Customer Attributes , ALM Id = 88437", enabled = true)
	public void CustomerAccProvision_VerifyCustomerAttributes() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		
		logger.log(LogStatus.INFO, "Update User Profile");
		accProvision.CustomerAttributesCheck();
	}
	
	/**
	 * Jeevan
	 * @throws Exception
	 */
	@Test(priority = 132, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533","CustomerAccProvision_CreateUserProfile"}, groups = { "Critical_Regression" }, description = "Verify Role , ALM Id = 88436", enabled = true)
	public void CustomerAccProvision_VerifyRole() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		
		logger.log(LogStatus.INFO, "Verify If Role Can be Added");
		accProvision.VerifyIfRoleCanBeAdded();
	}
	
	/**
	 * Jeevan
	 * @throws Exception
	 */
	@Test(priority = 133, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533","CustomerAccProvision_CreateUserProfile"}, groups = { "Critical_Regression" }, description = "Delete User Profile , ALM Id = 88435", enabled = true)
	public void CustomerAccProvision_DeleteProfile() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		
		logger.log(LogStatus.INFO, "Verify If Profile Can be Deleted");
		accProvision.DeleteProfile();
	}
	

	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 134,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Create sub-customer is successful , ALM Id = 88599", enabled = true)
	public void multiTenancy_Create_SubCustomer() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Verify that SubCustomer is able to Created");
		multi.Create_Sub_Customer();
		
	}
	
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 135,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "verify sub-customer is successful , ALM Id = 88617", enabled = true)
	public void multiTenancy_verify_SubCustomer() throws Exception{
		
        logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Verifying if Sub_Customer Creation is Success");
		multi.Search_Sub_Customer();
	}
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 136,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Create SubGroup is successful for specific customer and its specific group , ALM Id = 88617", enabled = true)
	public void MultiTenancy_Create_Subgroup() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Verify that SubGroup is able to Created");
		multi.Create_SubGroup();
		
	}
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 137,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "verify SubGroup is successful for specific customer and its specific group", enabled = true)
	public void MultiTenancy_verify_Subgroup() throws Exception{
		
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Verifying if Sub_Group Creation is Success");
		multi.Search_SubGroup();
	}
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 138,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "verify Group is successfuly created for subcustomer , ALM Id = 88602 ", enabled = true)
	public void MmultiTenancy_createGroup_Of_SubCustomer() throws Exception{
		
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "verify Group is successfuly created for subcustomer ");
		multi.createGroup_Of_SubCustomer();
	}
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 139,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Group can not be deleted from the platform if members are associated with the group , ALM Id = 88604 ", enabled = true)
	public void MultiTenancy_DeleteGroup_Of_AssositedElement() throws Exception{
		
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, "Group can not be deleted from the platform if members are associated with the group. ");
		multi.deleteGroup_Of_AssociatedElement();
	}
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 140,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = " Group can  be deleted from the platform if no member associated with the group , ALM Id = 88605", enabled = true)
	public void MultiTenancy_delete_Group() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		logger.log(LogStatus.INFO, " Group can  be deleted from the platform if no member associated with the group");
		multi.deleteGroup_Of_NotAssociatedElement();;
		
	}
	
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 141,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Group search with the help of customer_name , ALM Id = 88609", enabled = true)
	public void MultiTenancy_Search_Group() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, " Group search with the help of customer_name");
		multi.groupSearch_with_CustomerName();
		
		
		
		
	}
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 142,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Group search with the help of customer_name & Parent_Group Name , ALM Id = 88610", enabled = true)
	public void MultiTenancy_Search_Group_with_CustomerName_And_ParentName() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, " Create Sub_Group ");
		multi.Create_SubGroup();
		
		logger.log(LogStatus.INFO, " Search group with the help of CustomerName & Group_Name");
		multi.GroupSearch_with_CustomerName_parentgroup();
		
		
		
	}		
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 143,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Group search with the help of customer_name & Parent_Group Name , ALM Id = 88611", enabled = true)
	public void MultiTenancy_Search_Group_with_CustomerName_And_ParentName_Without_IncludeGroup() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, " Create Sub_Group ");
		multi.Create_SubGroup();
		
		logger.log(LogStatus.INFO, " Search group with the help of CustomerName & Group_Name");
		multi.GroupSearch_with_CustomerName_parentgroup_without_IncludeBox();
		
		}
	
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 144,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Group search with the help Group Name , ALM Id = 88608", enabled = true)
	public void MultiTenancy_GroupSearch_With_GroupName() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, " Group search with the help Group Name ");
		multi.GroupSearch_with_groupName();
		
		
}
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 145,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Group search with the help of customer_name & Parent_Group Name & Group Name , ALM Id = 88611", enabled = true)
	public void MultiTenancy_Search_Group_with_CustomerName_ParentGroupName_GroupName() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, " Create Sub_Group ");
		multi.Create_SubGroup();
		
		logger.log(LogStatus.INFO, " Search group with the help of  Group_Name,Customer Name,Parent Group Name");
		multi.GroupSearch_with_CustomerName_parentgroup_GroupName();
		
		}
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 146,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Group search with the help of customer_name & Parent_Group Name & Group Name , ALM Id = 88612", enabled = true)
	public void MultiTenancy_Search_Group_with_CustomerName_ParentGroupName_GroupName_IncludeSubGroup() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		
		logger.log(LogStatus.INFO, " Search group with the help of  Group_Name,Customer Name,Parent Group Name");
		multi.GroupSearch_with_CustomerName_parentgroup_GroupName_IncludeSubGroup();
		
		}
	
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 147, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"}, groups = { "Critical_Regression" }, description = "Group search with the help of Reset , ALM Id = 88614", enabled = true)
	public void Multitenancy_Group_reset() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		
		logger.log(LogStatus.INFO, " Group search with the help of Reset");
		multi.Group_Search_with_Reset();
		
		}	
	/**
	 *  Manish
	 * @throws Exception
	 */
	@Test(priority = 148,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Group can  be Edit from the platform  member associated with the group , ALM Id = 88607", enabled = true)
	public void MultiTenancy_Edit_Group() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("new_user_name"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, " Create new asset");
		st.CreateNewAsset(true, false);
		
		logger.log(LogStatus.INFO, " Verify Create Asset Success Message");
		st.Verify("Success");
		
		logger.log(LogStatus.INFO, " Group can  be Edit from the platform  member associated with the group");
		multi.EditGroup_Of_AssociatedElement();
		
		logger.log(LogStatus.INFO, " Group can  be verified through  associated Element");
		multi.Verify_EditGroup();
		
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 149,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Customer View , ALM Id = 88615", enabled = true)
	public void MultiTenancy_ValidateCustomerView() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Customer View");
		multi.validateCustomerView();
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 150,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Group  View , ALM Id = 88616", enabled = true)
	public void MultiTenancy_ValidateGroupView() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Group View");
		multi.validateGroupView();
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 151,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Sub Customer  View , ALM Id = 88617", enabled = true)
	public void MultiTenancy_ValidateSubCustomerView() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Sub Customer View");
		multi.validateSubCustomer();
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 152,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Retention Negative , ALM Id = 88618", enabled = true)
	public void MultiTenancy_ValidateRetentionNegative() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Retention Negative");
		multi.validateRetentionPeriod("-12", true);
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 153,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Retention 180+ , ALM Id  = 88619", enabled = true)
	public void MultiTenancy_ValidateRetention180Exceed() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Retention Negative");
		multi.validateRetentionPeriod("190", true);
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 154,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Retention Blank , ALM Id = 88621", enabled = true)
	public void MultiTenancy_ValidateRetentionBlank() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Retention Blank");
		multi.validateRetentionPeriod("", false);
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 155,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Retention as 180 , ALM Id = 88620", enabled = true)
	public void MultiTenancy_ValidateRetentionAs180() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Retention as 180");
		multi.validateRetentionPeriod("180", false);
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 156,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Inherit Check , ALM Id = 88622", enabled = true)
	public void MultiTenancy_InheritCheckForDp() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Inheritance for Dp");
		multi.validateInheritCheckForDp();
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 157,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Inherit UnCheck , ALM Id = 88623", enabled = true)
	public void MultiTenancy_InheritUnCheckForDp() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Non Inheritance for Dp");
		multi.validateInheritUnCheckForDp();
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 158,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Validate Inherit Check for Cp , ALM Id = 88624", enabled = true)
	public void MultiTenancy_InheritCheckForCp() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Inheritance for Cp");
		multi.validateInheritCheckForCp();
		
	}
	
	/**
	 * Jeevan
	 */
	@Test(priority = 158,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533"},  groups = { "Critical_Regression" }, description = "Check if a Device can be updated to another group , ALM Id = 88626", enabled = true)
	public void MultiTenancy_MoveAssetToDiffGroup() throws Exception{
		
		logger.assignCategory("Critical Regression");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("cust_username"), prop.getProperty("new_password"));
		
		logger.log(LogStatus.INFO, "Validate Retention Negative");
		multi.validateAssetMoveToDiffGroup();
		
	}
	
	
}
