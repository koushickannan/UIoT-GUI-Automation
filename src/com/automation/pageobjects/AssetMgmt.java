package com.automation.pageobjects;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.hpe.base.Attributes;
import com.hpe.utilities.CSVUtils;
import com.hpe.webdriver.ElementBase;
import com.opencsv.CSVReader;

import org.apache.commons.codec.binary.Base64;

public class AssetMgmt extends ElementBase {
	
	final static Logger Log = Logger.getLogger(AssetMgmt.class.getName());
	
	public static String http_asset = "";
	public static ArrayList<String> assetList = new ArrayList<String>();

	
	By asset_mgmt_dashboard = By.xpath(".//*[text()='Asset Mgmt.']");
	By manage_asset = By.xpath(".//*[@href='/dsm/assetmgmt/searchAssetForm.htm']");
	By remove_asset = By.xpath(".//a[contains(@ng-click,'deleteAssetInBulk')]");
	
	By import_asset = By.xpath(".//a[@href='/dsm/assetmgmt/importAsset.htm']/li");
	
	// Key Import
	By import_file_select = By.id("importTypeSelect");
	By key_name = By.id("keyDisplayNameNew");
	By file_browse = By.id("keyFileIdNew");
	By upload = By.xpath("(.//*[@name='Upload'])[2]");
	
	// Device Import
	By device_name = By.id("displayName");
	By device_file_browse = By.id("fileId");
	By device_upload = By.xpath(".//*[@value='Import']");
	
	// Manage Codec
	By manage_codec = By.xpath(".//a[@href='/dsm/assetmgmt/importCodec.htm']/li");
	By codec_identifier = By.id("codecId");
	By codec_name = By.id("codecName");
	By codec_version = By.id("codecVersion");
	By codec_desc = By.id("codecDesc");
	By codec_file_browser = By.id("codecFileId");
	By codec_upload = By.xpath(".//*[@value='Upload']");
	
	//Asset KPI
	By asset_api = By.xpath(".//*[@href='/dsm/assetmgmt/kpi.htm']");
	By asset_name = By.name("assetname");
	By asset_type = By.xpath(".//*[contains(@ng-model,'searchPairs[1]')]");
	By device_profile = By.id("deviceProfileId");
	By search = By.xpath(".//*[@value='Search']");
	By asset_table = By.cssSelector("#asset>tbody>tr:nth-child(1)>td:nth-child(1)>a");
	
	//Connectivity Mgmt
	By connect_mgmt = By.xpath(".//*[text()='Connectivity Mgmt']");
	
	
	//Create MQTT Asset
	By createAsset = By.id("asset_2");
	By assetName = By.name("resourceName");
	
	By assetType = By.name("resourceType");
	By poi = By.name("host");
	By customerDropDownLink = By.xpath(".//div[contains(@ng-model,'customerId')]/a");
	By customerSearchInput = By.xpath(".//div[contains(@ng-model,'customerId')]/div/div/input");
	By customerSearchSelect = By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li[2]");
	
	By groupDropDownLink = By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a");
	By groupSearchInput = By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/div/input");
	By groupSearchSelect = By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/ul/li[2]");
	
	By dpDropDownLink = By.xpath(".//div[contains(@ng-model,'deviceProfileId')]/a");
	By dpSearchInput = By.xpath(".//div[contains(@ng-model,'deviceProfileId')]/div/div/input");
	By dpSearchSelect = By.xpath(".//div[contains(@ng-model,'deviceProfileId')]/div/ul/li[2]");
	
	By authTypeDropDown = By.name("authNType");
	By autoProvisionCheckBox = By.xpath(".//*[text()='Transport Channels']/..//div/div/input");
	
	By usernameNOSECURITY1 = By.xpath("(.//*[contains(@ng-show,'deviceDetails.customerId')])[3]/span[1]");
	By usernameNOSECURITY2 = By.xpath("(.//*[contains(@ng-show,'deviceDetails.customerId')])[3]/label");
	By passwordNOSECURITY = By.xpath(".//*[@ng-model='deviceDetails.password'][@type='text']");
	
	By usernameBASIC_AUTH = By.xpath(".//*[@ng-change='userNameOnChange()']/../input[1]");
	By passwordBASIC_AUTH = By.xpath(".//*[@ng-model='deviceDetails.password'][@type='password']");
	
	By mqttDeviceID = By.id("iotParamsDeviceID");
	By mqttCreateButton = By.xpath("(.//*[text()='Create'])[1]");
	
	By customerPrefix_BASIC_AUTH = By.xpath(".//span[@ng-show='deviceDetails.customerId != 0']");
	
	/*
	 * Retrieve Asset resource ID webElemnets
	 */
	By searchQuickLink = By.xpath(".//a[@class='btn quicklinks']");
	By assetNameInSearch = By.name("assetname");
	By searchButton = By.cssSelector("input[value='Search']");
	By subGroupCheckBox = By.cssSelector("input[ng-model='searchObj.search.includeSubGroups']");
	By searchedResultexist = By.xpath(".//table[@st-table='assetList']/tbody");
	By searchResult=By.xpath(".//table[@class='table  table-bordered  dt-responsive nowrap ng-isolate-scope']/tbody/tr/td/a[@class='ng-binding']");
	By authType = By.xpath("//div[@ng-show='showPasswordReset']/div/div[2]/div/span");
	
	
	
	
	public void createMQTTAsset(boolean autoProvision, String authType, boolean unAuthorized) throws Exception {
		
		try {
			
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}
			
			findElement(createAsset, "Create Asset").click("Click on Create Asset");
			String tempManip= assetDetailsProp.getProperty("MQ_asset_name");
			int startIndex = tempManip.indexOf("m");
			int endIndex = tempManip.lastIndexOf("t");
			String mqttassetName = tempManip.substring(startIndex, endIndex+1)+randomNumber();
			if(unAuthorized==true){
				assetDetailsProp.setProperty("MQ_asset_name_unauthorized", mqttassetName);	
			}
			if(unAuthorized==false){
			assetDetailsProp.setProperty("MQ_asset_name", mqttassetName);
			}
					
			
			
			findElement(assetName, "Asset Name Text Box").sendKeys(mqttassetName, "Enter Asset Name");
			findElement(assetType, "Asset type drop down").selectByValue("SENSOR", "Select SENSOR as asset type");
			findElement(poi, "POI Text Box").sendKeys(assetDetailsProp.getProperty("MQ_poi"), "Enter POI Value");
			
			findElement(customerDropDownLink, "Customer Drop Down Link").click("Click on Customer Drop Down Link");
			findElement(customerSearchInput, "Customer Search Field").sendKeys(CustomerManagement.Cust_Name.toUpperCase(), "Enter Customer Name");
			Thread.sleep(2000);
			findElement(customerSearchSelect, "Customer from the list").click("Select customer form the list");
			
			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")));
			
			findElement(groupDropDownLink, "Group Drop Down Link").click("Click on Group Drop Down Link");
			findElement(groupSearchInput, "Group Search Field").sendKeys(SmokeTest.Group.toUpperCase(), "Enter Group Name");
			Thread.sleep(2000);
			findElement(groupSearchSelect, "Group from the list").click("Select group form the list");
			
			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")));
			
			findElement(dpDropDownLink, "DP Drop Down Link").click("Click on DP Drop Down Link");
			findElement(dpSearchInput, "DP Search Field").sendKeys("bravo", "Enter DP Name");
			Thread.sleep(2000);
			findElement(dpSearchSelect, "DP from the list").click("Select DP form the list");
			
			if (autoProvision == false) {

				findElement(autoProvisionCheckBox, "Autoprovision")
						.click("Uncheck AutoProvision");
			}
			
			if (authType=="NO_SECURITY") {
				
				findElement(authTypeDropDown, "Auth Type Drop Down").selectByValue("NO_SECURITY", "Select NO_SECURITY as Auth Type");
				
				
				String removeSpace1 =findElement(usernameNOSECURITY1, "UserName part 1 for NoSecurity").getAttribute(Attributes.TEXT);
				
				removeSpace1= removeSpace1.trim();
				String removeSpace2= findElement(usernameNOSECURITY2, "UserName part 1 for NoSecurity").getAttribute(Attributes.TEXT);
				removeSpace2= removeSpace2.trim();
				String noSecurityusername =removeSpace1+removeSpace2;
				
				if(unAuthorized==false) {
				assetDetailsProp.setProperty("MQ_deviceUsername", noSecurityusername);
				Log.info("User Name for NOSecurity is "+noSecurityusername);
				}
				if(unAuthorized==true) {
					assetDetailsProp.setProperty("MQ_deviceUsername_unauthorized", noSecurityusername);
				}
				
				
				assetDetailsProp.setProperty("MQ_devicePassword", findElement(passwordNOSECURITY, "Password for NoSecurity").getAttribute(Attributes.TEXT));
				Log.info("Password for NOSecurity is "+findElement(passwordNOSECURITY, "Password for NoSecurity").getAttribute(Attributes.TEXT));
				
			}
			
			if (authType=="BASIC_AUTH") {
				
				
				
				findElement(authTypeDropDown, "Auth Type Drop Down").selectByValue("BASIC_AUTH", "Select BASIC_AUTH as Auth Type");
				String CutomerPrefix=findElement(customerPrefix_BASIC_AUTH, "CutomerPrefixID").getAttribute(Attributes.TEXT);
				 findElement(usernameBASIC_AUTH, "UserName for Basic_Athentication").sendKeys(mqttassetName, "Sending the suffix of username");
				 findElement(passwordBASIC_AUTH, "Password for Basic_Athentication").sendKeys("password", "Sending the suffix of password");
				 
				 if(unAuthorized==false) {
				 assetDetailsProp.setProperty("MQ_deviceUsername",CutomerPrefix+mqttassetName);
				 Log.info("User Name for the Basic AUTH stored is"+CutomerPrefix+mqttassetName);
				 assetDetailsProp.setProperty("MQ_devicePassword","password");
			
			}
				 
				 if(unAuthorized==true) {
				 assetDetailsProp.setProperty("MQ_deviceUsername_unauthorized",CutomerPrefix+mqttassetName);
				 Log.info("User Name for the unauthrozied Basic AUTH stored is"+CutomerPrefix+mqttassetName);
				 assetDetailsProp.setProperty("MQ_devicePassword","password");
			
			}
				 
			}
			
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-if='assetParamView']/div/div")));
			
			
			findElement(mqttDeviceID, "Device ID Text Box").sendKeys(mqttassetName, "Enter Device ID");
			findElement(mqttCreateButton, "Create Button").click("Click on Create Button");
			
			
		}catch(Exception e) {
			
			
			Log.error("Unable to Create MQTT Asset");
			Log.error(e.toString());
			throw(e);
			
			
		}
		
	}

	
	public void retrieveAssetResourceID(String assetName, String propertyKey) {
		
		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}
			
			findElement(manage_asset, "ManageAsset Tile").click("Clicking ManageAsset");
			findElement(searchQuickLink,"Selecting the Quick Link").click("Clicking the Quick Search Icon");
			findElement(subGroupCheckBox,"Checkbox Search").click("Checking the Check box");
			findElement(assetNameInSearch,"Selecting the AssetName Search").sendKeys(assetName, "Sending Asset Name");
			findElement(searchButton,"Search Button").click("CLicking Serach Button");
			Thread.sleep(1000);
			if(!wait.until(ExpectedConditions.invisibilityOfElementWithText(searchedResultexist,"Check if the resource exist"))) {
				Log.error("Resource not found- Please check the Resource availablity");
				
			}
			findElement(searchResult,"Result Search").click("Select the Search result");
			
			String manipurl = driver.getCurrentUrl();
			String[] tempManip=manipurl.split("=");
			assetDetailsProp.setProperty(propertyKey,tempManip[1]);
			Log.info("Asset Resource Id set in the File is"+tempManip[1]);
			
		}catch(Exception e) {
			
			Log.error("Unable to retrieve resource id for asset "+assetName);
			
			
		}
	}
	
	
	public void Asset_KPI(String filter){
		if(!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
			}
		
		findElement(asset_api,"Asset KPI").click("Click on Asset API");
		findElement(By.cssSelector(".btn.quicklinks"),"Search Button").click("Click on Search Button");;
		if(filter.equals("Asset Name")){
		findElement(asset_name, "Asset Name").sendKeys(SmokeTest.assetList.get(1), "Enter Asset Name");
		}
		if(filter.equals("Asset Type")){
		findElement(asset_type, "Asset Type").selectByValue("2", "Select Asset Type");
		}
		/*if(filter.equals("Device Profile")){
		findElement(device_profile, "Device Profile").selectByText(prop.getProperty("device_profile") + "-" + prop.getProperty("model"), "Select Deive Profile");
		}*/
		findElement(search, "Search Button").click("Search Submit");
		
		
	}
	
	public void Verify_Asset_KPI(){
		
		String asset_name = findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr[1]/td/a"), "Asset Table").getAttribute(Attributes.TEXT);
		
		_assert.contains(asset_name, "AutoAsset", "Verifying Asset Search");
		
		String asset_type = findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr[1]/td[4]"),"Asset Type").getAttribute(Attributes.TEXT);
		
		_assert.contains(asset_type, "SENSOR", "Verifying Asset Type");
		
		String cust_name = findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr[1]/td[2]"),"Customer Name").getAttribute(Attributes.TEXT);
		
		_assert.contains(cust_name, CustomerManagement.Cust_Name.toUpperCase(), "Verifying Customer Name");
	}
	
	
	public void Codec_Upload() throws Exception{
		if(!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
			}
		
		findElement(manage_codec, "Manage Codec").click("Click on Manage Codec");
		findElement(codec_identifier, "Codec Identifier").sendKeys("Test Automation", "Enter Codec Identifier");
		findElement(codec_name, "Codec Name").sendKeys("Test Codec ", "Enter Codec Name");
		findElement(codec_version, "Codec Version").sendKeys("Test Version", "Enter Codec Version");
		findElement(codec_desc, "Codec Description").sendKeys("Test Desc", "Enter Codec Desc");
		findElement(codec_file_browser, "Codec File Browser").sendKeys(System.getProperty("user.dir")+"\\testdata\\ino-0.0.1-SNAPSHOT-jar-with-dependencies1.5.jar","Click on Codec File Browser");
		
		findElement(codec_upload, "Codec Upload").click("Click on Codec Upoad");
		
	}
	
	public void Device_Import() throws Exception{
		
		if(!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
			}
		
		String csvFile = System.getProperty("user.dir")+"\\testdata\\bulkimport.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        String dp_name = prop.getProperty("device_profile")+"#"+prop.getProperty("model")+"#1.1";

        for(int i=1;i<=10;i++){
        	
        CSVUtils.writeLine(writer,Arrays.asList("CREATE","BulkDevice"+randomNumber(),"SENSOR","localhost",","+dp_name,","+"Provisioned",",,"+"TRUE","BASIC_AUTH","admin"+randomNumber()+"#password"+randomNumber(),""+",TRUE,FALSE,"+"[OW#False]","[DevEUI#Join"+Integer.toHexString(randomNumber())+",AppEUI#ABB"+i+",DevAddr#CC"+i+"]"));	
        //CSVUtils.writeLine(writer,Arrays.asList("CREATE",String.valueOf(i),"SENSOR","localhost",","+dp_name,","+"Provisioned",",,"+"false","[OW#False]","[DevEUI#Join"+Integer.toHexString(randomNumber())+",AppEUI#ABB"+i+",DevAddr#CC"+i+"]"));
        }
        
        Log.info("Bulk CSV Generated");
        writer.flush();
        writer.close();
		findElement(import_asset, "Import Asset").click("Click on Import Asset");
		findElement(import_file_select, "Import File Select").selectByValue("1", "Select Key Import");
		findElement(By.id("customerId"), "Customer").selectByValue(SmokeTest.CustID, "Select Customer");
		findElement(By.id("deviceGroup"),"Group").selectByText(SmokeTest.Group.toUpperCase(), "Select Group");
		findElement(device_name, "Device Description").sendKeys("Test Automation", "Entering Device Name");
		findElement(device_file_browse, "File Browser").sendKeys(System.getProperty("user.dir")+"\\testdata\\bulkimport.csv","Click on File Browse");
		Thread.sleep(3000);
		findElement(device_upload, "Upload").click("Click on Upload");
		
	}
	
	public void Key_Import() throws Exception{
		if(!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
			}
		
		findElement(import_asset, "Import Asset").click("Click on Import Asset");
		findElement(import_file_select, "Import File Select").selectByValue("3", "Select Key Import");
		findElement(key_name, "Key Description").sendKeys("Test Automation", "Entering Key Name");
		findElement(file_browse, "File Browser").sendKeys(System.getProperty("user.dir")+"\\testdata\\KMS_OTA.csv", "Enter Upload File");
		
		//RobotCopyPasteEnter(System.getProperty("user.dir")+"\\testdata\\KMS_OTA.csv");
		findElement(upload, "Upload").click("Click on Upload");
		
		
	}
	
public void Verify(String Expected) throws InterruptedException{
		
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
		String actual = driver.findElement(By.xpath(".//*[@class='alert alert-success']")).getText();

		 _assert.contains(actual, Expected,"Verify Success Message");
		
	}
	
	
	public void SelectAssetToBeRemoved() throws InterruptedException{
		try{
		if(!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
		findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
		}
		Thread.sleep(2000);
		findElement(manage_asset, "Manage Asset").click("Click on Manage Asset");
		
		Thread.sleep(2000);
		findElements(By.xpath(".//table[@st-table='assetList']/tbody/tr"),"Asset List Rows");
		Thread.sleep(2000);
		int rowCount = _WebElements.size();
		
		for(int i =1;i<=rowCount;i++){
			
			String asset_name = findElement(By.xpath(".//table[@st-table='assetList']/tbody/tr["+i+"]/td[2]/a"), "Asset Name").getAttribute(Attributes.TEXT);
			
			if(asset_name.trim().equals(SmokeTest.assetList.get(2).trim())){
				
				
				findElement(By.xpath(".//table[@st-table='assetList']/tbody/tr["+i+"]/td[1]/input"), "Asset Check Box").click("Click on Asset Check Box");
				
				//findElement(By.xpath(".//table[@st-table='assetList']/tbody/tr["+i+"]/td[10]/a/i"), "Delete Icon").click("Click on  Delete Icon");
				break;
			}
			
		}
		
		for(int i =1;i<=rowCount;i++){
			
			String asset_name = findElement(By.xpath(".//table[@st-table='assetList']/tbody/tr["+i+"]/td[2]/a"), "Asset Name").getAttribute(Attributes.TEXT);
			
			if(asset_name.trim().equals(SmokeTest.assetList.get(3).trim())){
				
				
				findElement(By.xpath(".//table[@st-table='assetList']/tbody/tr["+i+"]/td[1]/input"), "Asset Check Box").click("Click on Asset Check Box");
				
				//findElement(By.xpath(".//table[@st-table='assetList']/tbody/tr["+i+"]/td[10]/a/i"), "Delete Icon").click("Click on  Delete Icon");
				break;
			}
			
		}
		//findElement(remove_asset, "Remove Asset").click("Click on Remove Asset");
		
		/*alertAccept("Accept Remove Asset Alert");
		wait.until(ExpectedConditions
				.not(ExpectedConditions.alertIsPresent()));*/
		}catch(Exception e){
			Log.error(e.toString());
			throw (e);
		}
		
	}
	
	public void Remove_AssetAndVerify() throws Exception{
		try{
			String count_before = "",count_after="";
			
			do{
				count_before = findElement(By.cssSelector(".redbg"),"Red Count").getAttribute(Attributes.TEXT);
			}while(Integer.parseInt(count_before)==0);
			
			findElement(By.xpath(".//*[@ng-click='deleteAssetInBulk()']"), "Remove Asset Bulk").click("Click on Remove Button");
			alertAccept("Accept Remove Asset Alert");
			//alertAccept("Accept Remove Asset Alert");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String msg_success = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Msg").getAttribute(Attributes.TEXT);
			_assert.contains(msg_success, "Success", "Multiple Asset Delete Msg Verification");
			Thread.sleep(3000);
			count_after = findElement(By.cssSelector(".redbg"),"Red Count").getAttribute(Attributes.TEXT);
			_assert.equals(Integer.parseInt(count_after),Integer.parseInt(count_before)-2,"Verify Deleted Count");
			
		}catch(Exception e){
			Log.error("Unable to Remove Multiple Asset's");
			Log.error(e.toString());
			throw(e);
			
		}
	}
	
	public void Verify_Single_Remove_Asset() throws InterruptedException{
		
		Thread.sleep(2000);
		findElements(By.xpath(".//table[@st-table='assetList']/tbody/tr"),"Asset List Rows");
		int rowCount = _WebElements.size();
		_assert.equals(rowCount, 1, "Verify if Asset is deleted");
	}
	
	public void CreateHttpAsset(boolean autoProvision, String authType) throws InterruptedException {
		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."), "Asset Mgmt Tile")
					.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}

			findElement(By.id("asset_2"), "Create Asset").click("Click on Create Asset");

			String manipAsset = assetDetailsProp.getProperty("HTTP_Asset");
			int start = manipAsset.indexOf("h");
			int end = manipAsset.lastIndexOf("t");
			manipAsset = manipAsset.substring(start, end+1);
			String http_asset = manipAsset + randomNumber();
			assetDetailsProp.setProperty("HTTP_Asset", http_asset);

			Log.info("Asset Name :" + http_asset);

			findElement(By.name("resourceName"), "Asset Name").sendKeys(http_asset, "Enter Asset Name");

			findElement(By.id("resourceType"), "Asset Type").selectByValue("SENSOR", "Select Asset Type");

			findElement(By.xpath("(.//*[text()='Customer Name'])[1]/..//div[1]/div/a/span"), "Customer Drop Down")
					.click("Click on Customer Drop Down");

			findElement(By.xpath("//div[@ng-model='deviceDetails.customerId']/div/div/input"),
					"Customer Search Drop Down").sendKeys(CustomerManagement.Cust_Name.toUpperCase(),
							"Enter Customer Name");

			Thread.sleep(2000);

			findElement(By.xpath("//div[@ng-model='deviceDetails.customerId']/div/ul/li[2]"), "Select Customer")
					.click("Click on Customer");

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")));

			driver.findElement(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")).click();

			findElement(By.xpath(".//div[@ng-model='deviceDetails.deviceGroupId']/div/div/input"),
					"Group Search Drop Down").sendKeys(SmokeTest.Group.toUpperCase(), "Enter Group name");

			Thread.sleep(2000);

			findElement(By.xpath("//div[@ng-model='deviceDetails.deviceGroupId']/div/ul/li[2]"), "Select Customer")
					.click("Click on device Group");

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));

			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),
					"Dp drop down").click("Click on Dp Drop Down");

			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/div/div/input"),
					"Device Profile Search drop down").sendKeys("HTTPAuto", "Enter DP name");

			Thread.sleep(2000);

			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/div/ul/li[2]"),
					"Select Device Profile").click("Click on DP");

			if (autoProvision == false) {

				findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div/input"), "Autoprovision")
						.click("Uncheck AutoProvision");
			}

			Log.info("Auth Type :" + authType);

			/*if (authType == "NO_SECURITY") {

				findElement(By.name("authNType"), "Auth Type").selectByValue(authType, "Select" + authType);

				assetDetailsProp.setProperty("HTTP_Asset_Username",
						findElement(usernameNOSECURITY, "UserName for NoSecurity").getAttribute(Attributes.TEXT));
				Log.info("User Name for NOSecurity is "
						+ findElement(usernameNOSECURITY, "UserName for NoSecurity").getAttribute(Attributes.TEXT));

				assetDetailsProp.setProperty("HTTP_Asset_Password",
						findElement(passwordNOSECURITY, "Password for NoSecurity").getAttribute(Attributes.TEXT));
				Log.info("Password for NOSecurity is "
						+ findElement(passwordNOSECURITY, "Password for NoSecurity").getAttribute(Attributes.TEXT));

			}*/
			
			if (authType=="NO_SECURITY") {
				
				findElement(authTypeDropDown, "Auth Type Drop Down").selectByValue("NO_SECURITY", "Select NO_SECURITY as Auth Type");
				
				
				String removeSpace1 =findElement(usernameNOSECURITY1, "UserName part 1 for NoSecurity").getAttribute(Attributes.TEXT);
				
				removeSpace1= removeSpace1.trim();
				String removeSpace2= findElement(usernameNOSECURITY2, "UserName part 1 for NoSecurity").getAttribute(Attributes.TEXT);
				removeSpace2= removeSpace2.trim();
				String noSecurityusername =removeSpace1+removeSpace2;
				
			
				assetDetailsProp.setProperty("HTTP_Asset_Username", noSecurityusername);
				Log.info("User Name for NOSecurity is "+noSecurityusername);
					
				
				
				assetDetailsProp.setProperty("HTTP_Asset_Password", findElement(passwordNOSECURITY, "Password for NoSecurity").getAttribute(Attributes.TEXT));
				Log.info("Password for NOSecurity is "+findElement(passwordNOSECURITY, "Password for NoSecurity").getAttribute(Attributes.TEXT));
				
			}

			if (authType == "BASIC_AUTH") {

				findElement(By.name("authNType"), "Auth Type").selectByValue(authType, "Select" + authType);
				Thread.sleep(2000);
				String CustPrefix = findElement(By.xpath(".//span[@ng-show='deviceDetails.customerId != 0']"),
						"CutomerPrefixID").getAttribute(Attributes.TEXT);

				findElement(By.xpath("//input[1][@ng-model='deviceDetails.username']"), "Device user name")
						.sendKeys(http_asset, "Enter user nmae");
				findElement(By.xpath("//input[1][@ng-model='deviceDetails.password']"), "Device password")
						.sendKeys("password", "Enter Password");

				assetDetailsProp.setProperty("HTTP_Asset_Username", CustPrefix + http_asset);
				Log.info("User Name for the Basic AUTH stored is - " + CustPrefix + http_asset);
				assetDetailsProp.setProperty("HTTP_Asset_Password", "password");

			}

			Thread.sleep(2000);
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-if='assetParamView']/div/div")));
			findElement(By.id("iotParamsID"), "Device ID").sendKeys(http_asset, "Enter Device ID");
			Log.info("Device-ID :" + http_asset);
			findElement(By.xpath(".//*[@value='GenericFlow']"), "FlowID").click("Select Flow ID");

			Thread.sleep(2000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[text()='Create'])[1]")));
			findElement(By.xpath("(.//*[text()='Create'])[1]"), "Create Button").submit("Click on Create Button");

		} catch (Exception e) {

			Log.error("Unable to Create HTTP Asset");
			Log.error(e.toString());
			throw (e);

		}

	}
	
	public void RetrieveDeviceDetails(String assetPropertyKey, String flow, String usernamePropertyKey) throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}

			findElement(By.id("asset_3"), "Manage AAsset").click("Click on Manage Asset");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			Thread.sleep(2000);
			findElement(By.name("assetname"), "Asset Name").sendKeys(assetDetailsProp.getProperty(assetPropertyKey), "Enter Asset Name");
			findElement(By.xpath(".//*[@value='Search']"), "Search button").click("Click on Search Button");

			Thread.sleep(2000);

			findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr/td[2]/a"), "Locate Asset name")
					.click("Click on the Asset");

			Thread.sleep(2000);	

			if(authType != null) {
				
				String userName = findElement(By.xpath("//div[@ng-show='showPasswordReset']/div/div[4]/div/span"),
						"Device User name").getAttribute(Attributes.TEXT);

				Log.info("Device User name :" + userName);
				assetDetailsProp.setProperty(usernamePropertyKey, userName);
				
			}else {
				
				String userName = findElement(By.xpath("//div[@ng-show='showPasswordReset']/div/div[2]/div/span"),
						"Device User name").getAttribute(Attributes.TEXT);

				Log.info("Device User name :" + userName);
				assetDetailsProp.setProperty(usernamePropertyKey, userName);
			}
				
				
			if(flow == "http_normal") {
				
				String deviceID = findElement(By.xpath("(.//div[contains(@ng-repeat,'param1')]/div/span)[1]"), "Locate Device-ID").getAttribute(Attributes.TEXT);
				Log.info("Device-ID :" + deviceID);
				assetDetailsProp.setProperty("HTTP_Bulk_Asset_DeviceID", deviceID);
				
			}
			
			if(flow == "HTTP_Encrypt_Decrypt") {
				
				String devEUI = findElement(By.xpath("(.//div[contains(@ng-repeat,'param1')]/div/span)[1]"), "Locate DevEUI").getAttribute(Attributes.TEXT);
				Log.info("DevEUI :" + devEUI);
				assetDetailsProp.setProperty("HTTP_Bulk_Asset_DevEUI", devEUI);
				
				String deviceAddr = findElement(By.xpath("(.//div[contains(@ng-repeat,'param1')]/div/span)[2]"), "Locate Device-ID").getAttribute(Attributes.TEXT);
				Log.info("DevAddr :" + deviceAddr);
				assetDetailsProp.setProperty("HTTP_Bulk_Asset_DevAddr", deviceAddr);
				
				String appEUI = findElement(By.xpath("(.//div[contains(@ng-repeat,'param1')]/div/div/span)[1]"), "Locate AppEUI").getAttribute(Attributes.TEXT);
				Log.info("AppEUI :" + appEUI);
				assetDetailsProp.setProperty("HTTP_Bulk_Asset_AppEUI", appEUI);
			}
			
			if(flow == "mqtt_normal") {
				
				String deviceID = findElement(By.xpath("(.//div[contains(@ng-repeat,'param1')]/div/span)[1]"), "Locate Device-ID").getAttribute(Attributes.TEXT);
				Log.info("MQTT--Device-ID :" + deviceID);
				assetDetailsProp.setProperty("MQTT_Bulk_Asset_DeviceID", deviceID);
				
			}
			
			if(flow == "MQTT_Encrypt_Decrypt") {
				
				String devEUI = findElement(By.xpath("(.//div[contains(@ng-repeat,'param1')]/div/span)[1]"), "Locate DevEUI").getAttribute(Attributes.TEXT);
				Log.info("MQTT--DevEUI :" + devEUI);
				assetDetailsProp.setProperty("MQTT_Bulk_Asset_DevEUI", devEUI);
				
				String deviceAddr = findElement(By.xpath("(.//div[contains(@ng-repeat,'param1')]/div/span)[2]"), "Locate Device-ID").getAttribute(Attributes.TEXT);
				Log.info("MQTT--DevAddr :" + deviceAddr);
				assetDetailsProp.setProperty("MQTT_Bulk_Asset_DevAddr", deviceAddr);
				
				String appEUI = findElement(By.xpath("(.//div[contains(@ng-repeat,'param1')]/div/div/span)[1]"), "Locate AppEUI").getAttribute(Attributes.TEXT);
				Log.info("MQTT--AppEUI :" + appEUI);
				assetDetailsProp.setProperty("MQTT_Bulk_Asset_AppEUI", appEUI);
			}
				
			

		} catch (Exception e) {
			
			Log.error("Unable to retrieve device username");
			Log.error(e.toString());
			throw e;
		}
	}
	
	public void ResetDevicePassword(String Expected, String assetPropertyKey, String passwordKey) throws InterruptedException {
		try {
			
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}

			findElement(By.id("asset_3"), "Manage AAsset").click("Click on Manage Asset");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			Thread.sleep(2000);
			findElement(By.name("assetname"), "Asset Name").sendKeys(assetDetailsProp.getProperty(assetPropertyKey), "Enter Asset Name");
			findElement(By.xpath(".//*[@value='Search']"), "Search button").click("Click on Search Button");

			Thread.sleep(2000);

			findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr/td[2]/a"), "Locate Asset name")
					.click("Click on the Asset");

			Thread.sleep(2000);	
			
			findElement(By.xpath(".//input[@type='password']"), "Locate Device Password").sendKeys(assetDetailsProp.getProperty(passwordKey), "Enter Password");
			findElement(By.xpath("(.//button[@type='button'])[4]"), "Locate reset button").click("Reset Password");
			
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@ng-app='deviceApp']//div[2]/div/strong")));
			String actual = findElement(By.xpath("//div[@ng-app='deviceApp']//div[2]/div/strong"), "Success Alert")
					.getAttribute(Attributes.TEXT);

			_assert.contains(actual, Expected, "Verify Success");
			
			Log.info("Reset Password Success");

		} catch (Exception e) {

			Log.error("Unable to Verify Success Message");
			Log.error(e.toString());
			throw (e);
		}

	}
	
	public String Base64Conversion(String username, String password) throws IOException {
		try {
			
			String Auth = username + ":" + password;

			// encoding byte array into base 64
			byte[] encoded = Base64.encodeBase64(Auth.getBytes());

			System.out.println("Original String: " + Auth);
			String base64value = new String(encoded);
			System.out.println("Base64 Encoded String : " + base64value);
			return base64value;
		} catch (Exception e) {

			Log.error("Unable to do Base64 Conversion");
			Log.error(e.toString());
			throw (e);
		}

	}
	
	public void Bulk_Device_Import(int noOfDevice, String transportChannel, String assetKey, int deviceNo)
			throws Exception {

		String csvFile = null;

		if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."), "Asset Mgmt Tile")
				.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
		}

		Log.info("Transport Channel : " + transportChannel);

		/*Bulk Import -- HTTP-- BASIC_AUTH*/
		if (transportChannel == "http") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\httpbulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "HTTPAuto" + "#" + "oneM2M" + "#3.0";

			for (int i = 1; i <= noOfDevice; i++) {

				CSVUtils.writeLine(writer,
						Arrays.asList("CREATE", "http" + randomNumber(), "SENSOR", "localhost", "," + dp_name,
								"," + "Provisioned", ",," + "TRUE", "BASIC_AUTH",
								"http" + randomNumber() + "#password", "," + ",TRUE,TRUE," + "[HTTP#False]",
								"[ID#" + "httpid" + randomNumber() + ",FlowId#GenericFlow" + ",TestProfile#false"
										+ ",ContentType#JSON" + ",Decode#false" + ",DecodeType#SDKCodecId"
										+ ",DecodeInputKey#content" + ",DecodeMode#REPLACE" + ",Encode#false"
										+ ",EncodeType#SDKCodecId" + ",EncodeInputKey#content" + ",EncodeMode#REPLACE"
										+ ",UplinkPayloadXpath#content" + ",DownlinkPayloadXpath#content"
										+ ",Decryption#false" + ",Encryption#false]"));

			}

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

			/* Retrieve Device name from CSV.. */
			Thread.sleep(2000);
			pharseCSV(assetKey, deviceNo, "HTTP");
			Thread.sleep(2000);

		}

		if (transportChannel == "HTTP_Encrypt_Decrypt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\httpbulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "Encrypt" + "#" + "Decrypt" + "#4.0";

			for (int i = 1; i <= noOfDevice; i++) {

				CSVUtils.writeLine(writer,
						Arrays.asList("CREATE", "httped" + randomNumber(), "SENSOR", "localhost", "," + dp_name,
								"," + "Provisioned", ",," + "TRUE", "BASIC_AUTH",
								"httpedba" + randomNumber() + "#password", "," + ",TRUE,TRUE," + "[HTTP#False]",
								"[DevEUI#" + "httpedDevEUI" + randomNumber() + ",AppEUI#" + "httpedAppEUI" + randomNumber()
										+ ",DevAddr#" + "httpedDevAddr" + randomNumber() + ",FlowId#GenericFlow"
										+ ",TestProfile#false" + ",ContentType#JSON" + ",Encode#true" + ",Decode#true"
										+ ",Decryption#true" + ",Encryption#true" + ",PreferedDC#http_oneM2M"
										+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			}

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

			/* Retrieve Device name from CSV.. */
			Thread.sleep(2000);
			pharseCSV(assetKey, deviceNo, "HTTP");
			Thread.sleep(2000);

		}
		
		/*Bulk Import -- MQTT-- BASIC_AUTH*/
		if (transportChannel == "mqtt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\mqttbulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "bravo" + "#" + "Onem2mMQTT" + "#1.4";

			for (int i = 1; i <= noOfDevice; i++) {

				CSVUtils.writeLine(writer,
						Arrays.asList("CREATE", "mqtt" + randomNumber(), "SENSOR", "localhost", "," + dp_name,
								"," + "Provisioned", ",," + "TRUE", "BASIC_AUTH",
								"mqtt" + randomNumber() + "#password", "," + ",TRUE,TRUE," + "[MQTT#False]",
								"[DeviceID#" + "mqtt" + randomNumber() + "]"));

			}

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

			/* Retrieve Device name from CSV.. */
			Thread.sleep(2000);
			pharseCSV(assetKey, deviceNo, "MQTT");
			Thread.sleep(2000);

		}
		
		if (transportChannel == "MQTT_Encrypt_Decrypt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\mqttbulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "MQTT" + "#" + "Decryption" + "#2.0";

			for (int i = 1; i <= noOfDevice; i++) {

				CSVUtils.writeLine(writer,
						Arrays.asList("CREATE", "mqtted" + randomNumber(), "SENSOR", "localhost", "," + dp_name,
								"," + "Provisioned", ",," + "TRUE", "BASIC_AUTH",
								"mqttedba" + randomNumber() + "#password", "," + ",TRUE,TRUE," + "[MQTT#False]",
								"[DevEUI#" + "mqttedDevEUI" + randomNumber() + ",AppEUI#" + "mqttedAppEUI" + randomNumber()
										+ ",DevAddr#" + "mqttedDevAddr" + randomNumber() + ",FlowId#GenericFlow"
										+ ",TestProfile#false" + ",Encode#true" + ",Decode#true"
										+ ",Decryption#true" + ",Encryption#true" + ",PreferedDC#mqttOnem2m"
										+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			}

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

			/* Retrieve Device name from CSV.. */
			Thread.sleep(2000);
			pharseCSV(assetKey, deviceNo, "MQTT");
			Thread.sleep(2000);

		}
		
		
		/*Bulk Import -- MQTT-- NO_SECURITY*/
		if (transportChannel == "mqttNS") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\mqttbulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "bravo" + "#" + "Onem2mMQTT" + "#1.4";

			for (int i = 1; i <= noOfDevice; i++) {

				CSVUtils.writeLine(writer,
						Arrays.asList("CREATE", "mqttNS" + randomNumber(), "SENSOR", "localhost", "," + dp_name,
								"," + "Provisioned", ",," + "TRUE", "NO_SECURITY",
								"", "," + ",TRUE,TRUE," + "[MQTT#False]",
								"[DeviceID#" + "mqtt" + randomNumber() + "]"));

			}

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

			/* Retrieve Device name from CSV.. */
			Thread.sleep(2000);
			pharseCSV(assetKey, deviceNo, "MQTT");
			Thread.sleep(2000);

		}
		
		if (transportChannel == "MQTT_Encrypt_Decrypt_NS") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\mqttbulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "MQTT" + "#" + "Decryption" + "#2.0";

			for (int i = 1; i <= noOfDevice; i++) {

				CSVUtils.writeLine(writer,
						Arrays.asList("CREATE", "mqttNSed" + randomNumber(), "SENSOR", "localhost", "," + dp_name,
								"," + "Provisioned", ",," + "TRUE", "NO_SECURITY",
								"", "," + ",TRUE,TRUE," + "[MQTT#False]",
								"[DevEUI#" + "mqttNSedDevEUI" + randomNumber() + ",AppEUI#" + "mqttNSedAppEUI" + randomNumber()
										+ ",DevAddr#" + "mqttNSedDevAddr" + randomNumber() + ",FlowId#GenericFlow"
										+ ",TestProfile#false" + ",Encode#true" + ",Decode#true"
										+ ",Decryption#true" + ",Encryption#true" + ",PreferedDC#mqttOnem2m"
										+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			}

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

			/* Retrieve Device name from CSV.. */
			Thread.sleep(2000);
			pharseCSV(assetKey, deviceNo, "MQTT");
			Thread.sleep(2000);

		}

		findElement(import_asset, "Import Asset").click("Click on Import Asset");
		findElement(import_file_select, "Import File Select").selectByValue("1", "Select Key Import");
		findElement(By.id("customerId"), "Customer").selectByValue(SmokeTest.CustID, "Select Customer");
		findElement(By.id("deviceGroup"), "Group").selectByText(SmokeTest.Group.toUpperCase(),
				"Select Group");
		findElement(device_name, "Device Description").sendKeys("Test Automation", "Entering Device Name");
		findElement(device_file_browse, "File Browser").sendKeys(csvFile, "Click on File Browse");
		Thread.sleep(3000);
		findElement(device_upload, "Upload").click("Click on Upload");

	}
	
	public void pharseCSV(String assetKeyName, int deviceNo, String flowID) throws IOException {

		try {

			String csvFile = null;
			CSVReader cr = null;

			if (flowID == "HTTP") {

				csvFile = System.getProperty("user.dir") + "\\testdata\\httpbulkimport.csv";
				cr = new CSVReader(new FileReader(csvFile));

			}
			
			if (flowID == "MQTT") {

				csvFile = System.getProperty("user.dir") + "\\testdata\\mqttbulkimport.csv";
				cr = new CSVReader(new FileReader(csvFile));

			}

			String[] csvCell;
			String device1 = null;

			assetList.clear();

			while ((csvCell = cr.readNext()) != null) {

				device1 = csvCell[1];
				assetList.add(device1);

			}

			String deviceName = assetList.get(deviceNo);

			Log.info("Device Name - " + deviceName);
			assetDetailsProp.setProperty(assetKeyName, deviceName);

		} catch (Exception e) {

			Log.error("Unable to do retrieve device name");
			Log.error(e.toString());
			throw (e);

		}

	}
	
	public void Update_Bulk_Device_Import(String transportChannel) throws Exception {

		String csvFile = null;

		if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."), "Asset Mgmt Tile")
				.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
		}
		
		String DeviceUserName= null;
		String UserName= null;
		
		if(transportChannel == "http" || transportChannel == "HTTP_Encrypt_Decrypt") {
			
			DeviceUserName = assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username");
			String[] splitUser = DeviceUserName.split("-");
			UserName = splitUser[1];
			
		}
		
		if(transportChannel == "mqtt" || transportChannel == "MQTT_Encrypt_Decrypt" || transportChannel == "mqttNS" || transportChannel == "MQTT_Encrypt_Decrypt_NS") {
			
			DeviceUserName = assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username");
			String[] splitUser = DeviceUserName.split("-");
			UserName = splitUser[1];
			
		}
		

		Log.info("Transport Channel : " + transportChannel);

		
		/*Bulk Import -- HTTP -- BASIC_AUTH*/
		if (transportChannel == "http") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\updatebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "HTTPAuto" + "#" + "oneM2M" + "#3.0";
			CSVUtils.writeLine(writer,
					Arrays.asList("UPDATE", assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "BASIC_AUTH", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[HTTP#False]",
							"[ID#" + assetDetailsProp.getProperty("HTTP_Bulk_Asset_DeviceID") + ",FlowId#GenericFlow"
									+ ",TestProfile#false" + ",ContentType#JSON" + ",Decode#false"
									+ ",DecodeType#SDKCodecId" + ",DecodeInputKey#content" + ",DecodeMode#REPLACE"
									+ ",Encode#false" + ",EncodeType#SDKCodecId" + ",EncodeInputKey#content"
									+ ",EncodeMode#REPLACE" + ",UplinkPayloadXpath#content"
									+ ",DownlinkPayloadXpath#content" + ",Decryption#false" + ",Encryption#false]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}

		if (transportChannel == "HTTP_Encrypt_Decrypt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\updatebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "Encrypt" + "#" + "Decrypt" + "#4.0";
			CSVUtils.writeLine(writer,
					Arrays.asList("UPDATE", assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "BASIC_AUTH", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[HTTP#False]",
							"[DevEUI#" + assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI") + ",AppEUI#"
									+ assetDetailsProp.getProperty("HTTP_Bulk_Asset_AppEUI") + ",DevAddr#"
									+ assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr") + ",FlowId#GenericFlow"
									+ ",TestProfile#false" + ",Encode#true" + ",Decode#true" + ",Decryption#true"
									+ ",Encryption#true" + ",PreferedDC#http_oneM2M"
									+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}
		
		/*Bulk Import -- MQTT -- BASIC_AUTH*/
		if (transportChannel == "mqtt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\updatebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "bravo" + "#" + "Onem2mMQTT" + "#1.4";
			CSVUtils.writeLine(writer,
					Arrays.asList("UPDATE", assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "BASIC_AUTH", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[MQTT#False]",
							"[DeviceID#" + assetDetailsProp.getProperty("MQTT_Bulk_Asset_DeviceID") + "]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}

		if (transportChannel == "MQTT_Encrypt_Decrypt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\updatebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "MQTT" + "#" + "Decryption" + "#2.0";
			CSVUtils.writeLine(writer,
					Arrays.asList("UPDATE", assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "BASIC_AUTH", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[MQTT#False]",
							"[DevEUI#" + assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI") + ",AppEUI#"
									+ assetDetailsProp.getProperty("MQTT_Bulk_Asset_AppEUI") + ",DevAddr#"
									+ assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr") + ",FlowId#GenericFlow"
									+ ",TestProfile#false" + ",Encode#true" + ",Decode#true" + ",Decryption#true"
									+ ",Encryption#true" + ",PreferedDC#mqttOnem2m"
									+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}
		
		
		/*Bulk Import -- MQTT -- NO_SECURITY*/
		
		if (transportChannel == "mqttNS") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\updatebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "bravo" + "#" + "Onem2mMQTT" + "#1.4";
			CSVUtils.writeLine(writer,
					Arrays.asList("UPDATE", assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "NO_SECURITY", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[MQTT#False]",
							"[DeviceID#" + assetDetailsProp.getProperty("MQTT_Bulk_Asset_DeviceID") + "]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}

		if (transportChannel == "MQTT_Encrypt_Decrypt_NS") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\updatebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "MQTT" + "#" + "Decryption" + "#2.0";
			CSVUtils.writeLine(writer,
					Arrays.asList("UPDATE", assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "NO_SECURITY", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[MQTT#False]",
							"[DevEUI#" + assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI") + ",AppEUI#"
									+ assetDetailsProp.getProperty("MQTT_Bulk_Asset_AppEUI") + ",DevAddr#"
									+ assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr") + ",FlowId#GenericFlow"
									+ ",TestProfile#false" + ",Encode#true" + ",Decode#true" + ",Decryption#true"
									+ ",Encryption#true" + ",PreferedDC#mqttOnem2m"
									+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}
		

		findElement(import_asset, "Import Asset").click("Click on Import Asset");
		findElement(import_file_select, "Import File Select").selectByValue("1", "Select Key Import");
		findElement(By.id("customerId"), "Customer").selectByValue(SmokeTest.CustID, "Select Customer");
		findElement(By.id("deviceGroup"), "Group").selectByText(SmokeTest.Group.toUpperCase(), "Select Group");
		findElement(device_name, "Device Description").sendKeys("Test Automation", "Entering Device Name");
		findElement(device_file_browse, "File Browser").sendKeys(csvFile, "Click on File Browse");
		Thread.sleep(3000);
		findElement(device_upload, "Upload").click("Click on Upload");

	}
	
	public void Delete_Bulk_Device_Import(String transportChannel) throws Exception {

		String csvFile = null;

		if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."), "Asset Mgmt Tile")
				.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
		}

		String DeviceUserName = null;
		String UserName = null;
		
		if(transportChannel == "http" || transportChannel == "HTTP_Encrypt_Decrypt") {
			
			DeviceUserName = assetDetailsProp.getProperty("HTTP_Bulk_Asset_Username");
			String[] splitUser = DeviceUserName.split("-");
			UserName = splitUser[1];
			
		}
		
		if(transportChannel == "mqtt" || transportChannel == "MQTT_Encrypt_Decrypt" || transportChannel == "mqttNS" || transportChannel == "MQTT_Encrypt_Decrypt_NS") {
			
			DeviceUserName = assetDetailsProp.getProperty("MQTT_Bulk_Asset_Username");
			String[] splitUser = DeviceUserName.split("-");
			UserName = splitUser[1];
			
		}

		Log.info("Transport Channel : " + transportChannel);

		/*Bulk Import -- HTTP -- BASIC_AUTH*/
		if (transportChannel == "http") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\deletebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "HTTPAuto" + "#" + "oneM2M" + "#3.0";
			CSVUtils.writeLine(writer,
					Arrays.asList("DELETE", assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "BASIC_AUTH", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[HTTP#False]",
							"[ID#" + assetDetailsProp.getProperty("HTTP_Bulk_Asset_DeviceID") + ",FlowId#GenericFlow"
									+ ",TestProfile#false" + ",ContentType#JSON" + ",Decode#false"
									+ ",DecodeType#SDKCodecId" + ",DecodeInputKey#content" + ",DecodeMode#REPLACE"
									+ ",Encode#false" + ",EncodeType#SDKCodecId" + ",EncodeInputKey#content"
									+ ",EncodeMode#REPLACE" + ",UplinkPayloadXpath#content"
									+ ",DownlinkPayloadXpath#content" + ",Decryption#false" + ",Encryption#false]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}

		if (transportChannel == "HTTP_Encrypt_Decrypt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\deletebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "Encrypt" + "#" + "Decrypt" + "#4.0";
			CSVUtils.writeLine(writer,
					Arrays.asList("DELETE", assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "BASIC_AUTH", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[HTTP#False]",
							"[DevEUI#" + assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevEUI") + ",AppEUI#"
									+ assetDetailsProp.getProperty("HTTP_Bulk_Asset_AppEUI") + ",DevAddr#"
									+ assetDetailsProp.getProperty("HTTP_Bulk_Asset_DevAddr") + ",FlowId#GenericFlow"
									+ ",TestProfile#false" + ",Encode#true" + ",Decode#true" + ",Decryption#true"
									+ ",Encryption#true" + ",PreferedDC#http_oneM2M"
									+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}
		
		/*Bulk Import -- MQTT -- BASIC_AUTH*/
		if (transportChannel == "mqtt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\deletebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "bravo" + "#" + "Onem2mMQTT" + "#1.4";
			CSVUtils.writeLine(writer,
					Arrays.asList("DELETE", assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "BASIC_AUTH", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[MQTT#False]",
							"[DeviceID#" + assetDetailsProp.getProperty("MQTT_Bulk_Asset_DeviceID") + "]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}

		if (transportChannel == "MQTT_Encrypt_Decrypt") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\deletebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "MQTT" + "#" + "Decryption" + "#2.0";
			CSVUtils.writeLine(writer,
					Arrays.asList("DELETE", assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "BASIC_AUTH", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[MQTT#False]",
							"[DevEUI#" + assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI") + ",AppEUI#"
									+ assetDetailsProp.getProperty("MQTT_Bulk_Asset_AppEUI") + ",DevAddr#"
									+ assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr") + ",FlowId#GenericFlow"
									+ ",TestProfile#false" + ",Encode#true" + ",Decode#true" + ",Decryption#true"
									+ ",Encryption#true" + ",PreferedDC#mqttOnem2m"
									+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}
		
		/*Bulk Import -- MQTT -- NO_SECURITY*/
		
		if (transportChannel == "mqttNS") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\deletebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "bravo" + "#" + "Onem2mMQTT" + "#1.4";
			CSVUtils.writeLine(writer,
					Arrays.asList("DELETE", assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "NO_SECURITY", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[MQTT#False]",
							"[DeviceID#" + assetDetailsProp.getProperty("MQTT_Bulk_Asset_DeviceID") + "]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}

		if (transportChannel == "MQTT_Encrypt_Decrypt_NS") {

			csvFile = System.getProperty("user.dir") + "\\testdata\\deletebulkimport.csv";
			FileWriter writer = new FileWriter(csvFile);

			String dp_name = "MQTT" + "#" + "Decryption" + "#2.0";
			CSVUtils.writeLine(writer,
					Arrays.asList("DELETE", assetDetailsProp.getProperty("MQTT_Bulk_Asset"), "SENSOR", "localhost",
							"," + dp_name, "," + assetDetailsProp.getProperty("Asset_Status"), ",," + "TRUE", "NO_SECURITY", UserName + "#password@123",
							"," + ",TRUE,TRUE," + "[MQTT#False]",
							"[DevEUI#" + assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevEUI") + ",AppEUI#"
									+ assetDetailsProp.getProperty("MQTT_Bulk_Asset_AppEUI") + ",DevAddr#"
									+ assetDetailsProp.getProperty("MQTT_Bulk_Asset_DevAddr") + ",FlowId#GenericFlow"
									+ ",TestProfile#false" + ",Encode#true" + ",Decode#true" + ",Decryption#true"
									+ ",Encryption#true" + ",PreferedDC#mqttOnem2m"
									+ ",appskey#3E5C3F4C942D567577748FCC85DF2290" + ",KMSLogicalName#appskey]"));

			Log.info("Bulk CSV Generated");
			writer.flush();
			writer.close();

		}

		findElement(import_asset, "Import Asset").click("Click on Import Asset");
		findElement(import_file_select, "Import File Select").selectByValue("1", "Select Key Import");
		findElement(By.id("customerId"), "Customer").selectByValue(SmokeTest.CustID, "Select Customer");
		findElement(By.id("deviceGroup"), "Group").selectByText(SmokeTest.Group.toUpperCase(), "Select Group");
		findElement(device_name, "Device Description").sendKeys("Test Automation", "Entering Device Name");
		findElement(device_file_browse, "File Browser").sendKeys(csvFile, "Click on File Browse");
		Thread.sleep(3000);
		findElement(device_upload, "Upload").click("Click on Upload");

	}
	
	public void VerifyRemoveAsset_BulkUpload(String assetPropertyKey) throws Exception{
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}

			findElement(By.id("asset_3"), "Manage AAsset").click("Click on Manage Asset");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			Thread.sleep(2000);
			findElement(By.name("assetname"), "Asset Name").sendKeys(assetDetailsProp.getProperty(assetPropertyKey), "Enter Asset Name");
			
			findElement(By.xpath("(*//input[@type='checkbox'])[1]"), "Include sub-groups").click("Click Include sub-groups");
			findElement(By.xpath("(*//div[@class='col-sm-2']/select)[4]"), "Status Drop-Downdon").click("Click on status drop-down");
			findElement(By.xpath("(*//div[@class='col-sm-2']/select/option[6])"), "Removed Status").click("Select Removed");
			
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@value='Search']"), "Search button").click("Click on Search Button");	
			Thread.sleep(2000);
			
			findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr/td[2]/a"), "Locate Asset name")
			.click("Click on the Asset");
			Thread.sleep(2000);
			
			String status = findElement(By.xpath("(.//div[@class='form-group']/div/span)[8]"), "Locate Asset status").getAttribute(Attributes.TEXT);
			
			_assert.equals(status, "Removed", "Verify Asset status");

	
		}catch(Exception e){
			Log.error("Unable to verify removed asset");
			Log.error(e.toString());
			throw(e);
			
		}
	}

	public void RetrieveDeviceStatus(String assetPropertyKey) throws Exception {
		try {
			
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}

			findElement(By.id("asset_3"), "Manage AAsset").click("Click on Manage Asset");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			Thread.sleep(2000);
			findElement(By.name("assetname"), "Asset Name").sendKeys(assetDetailsProp.getProperty(assetPropertyKey), "Enter Asset Name");
			
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@value='Search']"), "Search button").click("Click on Search Button");	
			Thread.sleep(2000);
			
			findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr/td[2]/a"), "Locate Asset name")
			.click("Click on the Asset");
			Thread.sleep(2000);

			String deviceStatus = findElement(By.xpath("(.//div[@class='form-group']/div/span)[8]"),
					"Locate Asset status").getAttribute(Attributes.TEXT);
			Log.info("Device Status :" + deviceStatus);
			assetDetailsProp.setProperty("Asset_Status", deviceStatus);

		} catch (Exception e) {
			Log.error("Unable to retrieve asset status");
			Log.error(e.toString());
			throw (e);

		}
	}
	public void AddAcptoDevice(String assetPropertyKey, String acpName) throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}

			findElement(By.id("asset_3"), "Manage AAsset").click("Click on Manage Asset");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			Thread.sleep(2000);
			findElement(By.name("assetname"), "Asset Name").sendKeys(assetDetailsProp.getProperty("HTTP_Bulk_Asset"), "Enter Asset Name");
			findElement(By.xpath(".//*[@value='Search']"), "Search button").click("Click on Search Button");

			Thread.sleep(2000);

			findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr/td[9]/a"), "Locate Asset edit button")
					.click("Click on the Asset edit button");
			Thread.sleep(2000);
			findElement(By.xpath("//*[@id=\"selectPol\"]"),"clicking on select button").click("clicking on select button");Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='ng-binding' and contains(text(),'"+acpName+"')]")));
			findElement(By.xpath(".//*[contains(text(),'"+acpName+"')]"), "Locate policy Name")
			.click("Click on policy name");
			findElement(By.xpath(".//*[@type=\"button\" and contains(text(),'Done')]"), "clicking Done")
			.click("Click Done button");
			Thread.sleep(2000);
			findElement(By.xpath(".//button[@ng-click =\"submitted=true\"]"), "clicking Update")
			.click("Click Update button");
			Thread.sleep(2000);
		} catch (Exception e) {
			
			Log.error("Unable to retrieve device username");
			Log.error(e.toString());
			throw e;
		}
	}

}
