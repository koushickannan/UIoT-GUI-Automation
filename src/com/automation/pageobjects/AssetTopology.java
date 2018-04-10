package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.base.Base;
import com.hpe.webdriver.ElementBase;

public class AssetTopology extends ElementBase{
	
	final static Logger Log = Logger.getLogger(AssetTopology.class.getName());
	
	By asset_topology_dash = By.xpath(".//*[text()='Asset Topology']");
	By search = By.xpath("(.//*[@class='hitarea'])/../span");
	By select_device = By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span");
	By device_status = By.xpath("(.//label[text()='Device Status:'])/../div/span");
	By device_status_color = By.xpath(".//img[@ng-hide='deviceImageData']");
	By device_profile = By.xpath(".//a[@ng-bind='deviceProfile']");
	By device_name = By.xpath(".//*[@ng-bind='deviceName']");
	
	//By tenant_tile = By.xpath(".//div[@ng-click='kpiClick("+"tenant"+")']/span");
	By users_tile = By.xpath(".//*[contains(@ng-click,'users')]/span");
	By active_devices = By.xpath("(.//*[contains(@ng-click,'activeDevices')])[1]/span");
	By inactive_devices = By.xpath("(.//*[contains(@ng-click,'activeDevices')])[2]/span");
	By readings = By.xpath("(.//*[text()='Recent Readings'])/../../table/tbody/tr");
	
	public void Verify_Device_Count(){
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		findElement(search, "Group Name").click("Click on Group Name");
		
		//String tenant_count = findElement(tenant_tile, "Tenant Tile").getAttribute(Attributes.TEXT);
		String users_count="";
		String active_count="";
		
		do{
		
		 users_count = findElement(users_tile, "Users Tile").getAttribute(Attributes.TEXT);
		 Log.info("Users Count is "+users_count);
		 
		}while(Integer.parseInt(users_count)==0);
		
		 active_count = findElement(active_devices, "Application Tile").getAttribute(Attributes.TEXT);
		 Log.info("Active Devices Count is "+active_count);
		
		//_assert.equals(tenant_count.trim(), "1", "Verify the Tenant Count");
		_assert.equals(users_count.trim(), "1", "Verify the Users Count");
		_assert.equals(Integer.parseInt(active_count.trim()),1, "Verify the Active Devices Count");
		}catch (Exception e) {
			
			Log.error("Failed to Validate Device Count");
			Log.error(e.toString());
			throw(e);
			
		}
		
	}
	
	public void Verify_Device(){
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		//findElement(search, "Search Device").sendKeys(SmokeTest.assetList.get(1), Keys.ENTER, "Enter Device details");
		findElement(By.xpath(".//span[text()='"+SmokeTest.assetList.get(0)+"']"), "Select Device").click("Click on Device");
		
		String status = findElement(device_status, "Device Status").getAttribute(Attributes.TEXT);
		String color = findElement(device_status_color, "Device Profile Color").getAttribute(Attributes.GENERAL,"ng-src");
		
		if(status.equals("Provisioned")){
		
			_assert.contains(color, "Provisioned","Verify Device Status and Color");
		}
		
		String name = findElement(device_name, "Device Name").getAttribute(Attributes.TEXT);
		
		_assert.equals(name.trim(),SmokeTest.assetList.get(0), "Verifying Device Name");
		
		}catch(Exception e){
			
			Log.error("Unable to Verify Device");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	
	public int GetReadingCount(String resourceId, String assetPropertyKey) throws Exception{
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		Thread.sleep(3000);
		findElement(By.cssSelector("[ng-model='hpeTree_assetName']"), "Asset Topology Search Bar").sendKeys(assetDetailsProp.getProperty(assetPropertyKey),"Enter Asset Name");
		Thread.sleep(3000);
		findElement(By.xpath(".//a/i[@class='fa fa-search']"), "Locate Search").click("Search Asset");
		
		/*findElement(By.id("sel_"+resourceId), "Select Device").click("Click on Device");*/
		
		
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));
		
		
			
			findElement(By.xpath("(.//ul/li[@id='"+resourceId+"' and @class='ng-scope']/span)[1]"), "Locate Device").click("Click Device");
		
		
		//findElement(By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span"), "Search Device").click("Select Device");
		findElement(By.xpath("(.//i[@class='fa fa-tachometer'])/.."), "Readings").click("Click on Readings");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		int downlinkReadingCount = driver.findElements(By.xpath(".//*[@ng-show='ReadingLoading']/../tr")).size();
		Log.info("Messages Count is "+ downlinkReadingCount);
		int ActualMessages =downlinkReadingCount-1;
		Log.info("Actual Message"+ActualMessages);
		return ActualMessages;
		}catch(Exception e){
			
			Log.error("Get Reading Count Failed Readings");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void Verify_Readings(String resourceId,String numberOfMessages, String assetPropertyKey) throws Exception{
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));
		findElement(By.cssSelector("[ng-model='hpeTree_assetName']"), "Asset Topology Search Bar").sendKeys(assetDetailsProp.getProperty(assetPropertyKey),"Enter Asset Name");
		Thread.sleep(3000);
		findElement(By.xpath(".//a/i[@class='fa fa-search']"), "Locate Search").click("Search Asset");
		
		/*findElement(By.id("sel_"+resourceId), "Select Device").click("Click on Device");*/
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));
		
		
			
		findElement(By.xpath("(.//ul/li[@id='"+resourceId+"' and @class='ng-scope']/span)[1]"), "Locate Device").click("Click Device");
		
		
		//findElement(By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span"), "Search Device").click("Select Device");
		findElement(By.xpath("(.//i[@class='fa fa-tachometer'])/.."), "Readings").click("Click on Readings");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		int downlinkReadings = driver.findElements(By.xpath(".//*[@ng-show='ReadingLoading']/../tr")).size();
		Log.info("Messages Count is "+ downlinkReadings);
		_assert.contains(String.valueOf(downlinkReadings-1),numberOfMessages, "Verify Top 5  Messages");
		}catch(Exception e){
			
			Log.error("Failed to Validate Top5 Readings");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	public boolean Verify_Readings_Negative(String resourceId,String numberOfMessages) throws Exception{
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		findElement(By.id("sel_"+resourceId), "Select Device").click("Click on Device");
		Thread.sleep(2000);
		//findElement(By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span"), "Search Device").click("Select Device");
		findElement(By.xpath("(.//i[@class='fa fa-tachometer'])/.."), "Readings").click("Click on Readings");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		int downlinkReadings = driver.findElements(By.xpath(".//*[@ng-show='ReadingLoading']/../tr")).size();
		Log.info("Messages Count is "+ downlinkReadings);
		if((downlinkReadings==Integer.parseInt(numberOfMessages))) {
		
		return true;
		}
		else {
			return false;
		}
		}catch(Exception e){
			
			Log.error("Failed to Validate Top5 Readings");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	public void Verify_Readings_Filter() throws Exception{
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		findElement(By.id("sel_"+Base.nodelink), "Select Device").click("Click on Device");
		Thread.sleep(2000);
		//findElement(By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span"), "Search Device").click("Select Device");
		findElement(By.xpath("(.//i[@class='fa fa-tachometer'])/.."), "Readings").click("Click on Readings");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		findElement(By.xpath(".//span[contains(@ng-click,'getContainerTree()')]"),"Container Tree").click("Click on Container Tree");
		
		findElement(By.xpath(".//*[@ng-repeat='container in containerList']/span"),"Device").click("Click on Device");
		findElement(By.xpath(".//span[@fullname=' / commands']"), "Commands Container").click("Click on Commands Container");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		int downlinkReadings = driver.findElements(By.xpath(".//*[@ng-show='ReadingLoading']/../tr")).size();
		Log.info("Messages Count is "+ downlinkReadings);
		_assert.contains(String.valueOf(downlinkReadings-1),"5", "Verify Top 5 Downling Messages");
		}catch(Exception e){
			
			Log.error("Failed to Validate Readings Container Filter");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	public void Verify_Readings_Search() throws Exception{
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		findElement(By.id("sel_"+Base.nodelink), "Select Device").click("Click on Device");
		Thread.sleep(2000);
		//findElement(By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span"), "Search Device").click("Select Device");
		findElement(By.xpath("(.//i[@class='fa fa-tachometer'])/.."), "Readings").click("Click on Readings");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		findElement(By.xpath(".//span[contains(@ng-click,'searchShow ')]"),"Search Box").click("Click on Search Box");
		
		findElement(By.id("msgDeliveryStatus"),"Message Delivery Status").selectByValue("string:NO_ROUTE_PROVIDED", "Select UKNOWN Type");
		findElement(By.id("search"), "Search Button").click("Click on Search Button");
		
		int downlinkReadings = driver.findElements(By.xpath(".//*[@ng-show='ReadingLoading']/../tr")).size();
		Log.info("Messages Count is "+ downlinkReadings);
		_assert.contains(String.valueOf(downlinkReadings-1),"0", "Verify Top 5 Downling Messages");
		
		}catch(Exception e){
			
			Log.error("Failed to Validate Readings Search Functionality");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void MoveDeviceStatusTo(String status) throws Exception{
		try{
			
			findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
			findElement(By.xpath(".//span[text()='"+SmokeTest.assetList.get(0)+"']"), "Select Device").click("Click on Device");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//i[contains(@class,'pencil')]")));
			Thread.sleep(3000);
			String name="";
			do{
				
				 name = findElement(By.xpath(".//*[contains(@ng-bind,'deviceStatus')]"),"Device Status").getAttribute(Attributes.TEXT);
			}while(name=="");
			
			findElement(By.xpath(".//i[contains(@class,'pencil')]"), "Edit Icon").click("Click on Edit Icon");
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-if='assetParamView']/div/div")));
			findElement(By.id("status"), "Status Drop Down").selectByValue(status, ""+status+" as Status");
			findElement(By.xpath(".//button[text()='Update']"),"Update Button").click("Click on Update Button");
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(.//*[@class='cssload-container'])[1]")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(@class,'alert-success')]/strong")));
			
		}catch(Exception e){
			Log.error("Unable to Move Device Status to Active");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void VerifyDeviceStatus(String expected_status) throws Exception{
		try{
			
			findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
			findElement(By.xpath(".//span[text()='"+SmokeTest.assetList.get(0)+"']"), "Select Device").click("Click on Device");
			String name="";
			Thread.sleep(3000);
			do{
				
				 name = findElement(By.xpath(".//*[contains(@ng-bind,'deviceStatus')]"),"Device Status").getAttribute(Attributes.TEXT);
			}while(name.equals(""));
			//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@ng-hide='deviceImageData']")));
			
			String actual_status = findElement(By.xpath(".//*[text()='Device Status:']/following-sibling::div/span"), "Device Status").getAttribute(Attributes.TEXT);
			
			_assert.equals(actual_status, expected_status, "Verify Status Change of Device");
		}catch(Exception e){
			Log.error("Unable to Verify Device Status");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public int GetReadingCount_NC(String resourceId, String assetName, String parentContainer, String childContainer) throws Exception{
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		findElement(By.cssSelector("[ng-model='hpeTree_assetName']"), "Asset Topology Search Bar").sendKeys(assetDetailsProp.getProperty("MQ_asset_name"),Keys.ENTER,"Search Asset");
		Thread.sleep(3000);
		findElement(By.id("sel_"+resourceId), "Select Device").click("Click on Device");
		Thread.sleep(2000);
		//findElement(By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span"), "Search Device").click("Select Device");
		findElement(By.xpath("(.//i[@class='fa fa-tachometer'])/.."), "Readings").click("Click on Readings");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		findElement(By.xpath("*//ul/li[@class='noborder']/span/button/i"), "readings tree icon").click("Click on reading tree icon");
		findElement(By.xpath("(.//ul/li/span[contains(text(), '"+assetName+"')])[2]"), "Click on device name").click("Click on device name");
		findElement(By.xpath("(.//ul/li/span[contains(text(),'"+parentContainer+"')])[1]"), "Click on parent container").click("Click on parent container");
		findElement(By.xpath("(.//ul/li/span[contains(text(),'"+childContainer+"')])[1]"), "Click on child container").click("Click on child container");
		Thread.sleep(3000);
		
		int ReadingCount = driver.findElements(By.xpath(".//*[@ng-show='ReadingLoading']/../tr")).size();
		Log.info("Messages Count is "+ ReadingCount);
		int ActualMessages =ReadingCount-1;
		Log.info("Actual Message"+ActualMessages);
		return ActualMessages;
		}catch(Exception e){
			
			Log.error("Get Reading Count Failed Readings");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void Verify_Readings_NC(String resourceId,String numberOfMessages,  String assetName, String parentContainer, String childContainer) throws Exception{
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		findElement(By.id("sel_"+resourceId), "Select Device").click("Click on Device");
		Thread.sleep(2000);
		//findElement(By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span"), "Search Device").click("Select Device");
		findElement(By.xpath("(.//i[@class='fa fa-tachometer'])/.."), "Readings").click("Click on Readings");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		
		findElement(By.xpath("*//ul/li[@class='noborder']/span/button/i"), "readings tree icon").click("Click on reading tree icon");
		findElement(By.xpath("(.//ul/li/span[contains(text(), '"+assetName+"')])[2]"), "Click on device name").click("Click on device name");
		findElement(By.xpath("(.//ul/li/span[contains(text(),'"+parentContainer+"')])[1]"), "Click on parent container").click("Click on parent container");
		findElement(By.xpath("(.//ul/li/span[contains(text(),'"+childContainer+"')])[1]"), "Click on child container").click("Click on child container");
		Thread.sleep(3000);
		
		int Readings = driver.findElements(By.xpath(".//*[@ng-show='ReadingLoading']/../tr")).size();
		Log.info("Messages Count is "+ Readings);
		_assert.contains(String.valueOf(Readings-1),numberOfMessages, "Verify Top 5  Messages");
		}catch(Exception e){
			
			Log.error("Failed to Validate Top5 Readings");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	public void Verify_Readings_Decryption(String resourceId,String numberOfMessages,String flowType, String assetPropertyKey) throws Exception{
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		
		findElement(By.cssSelector("[ng-model='hpeTree_assetName']"), "Asset Topology Search Bar").sendKeys(assetDetailsProp.getProperty(assetPropertyKey),"Enter Asset Name");
		Thread.sleep(3000);
		findElement(By.xpath(".//a/i[@class='fa fa-search']"), "Locate Search").click("Search Asset");
		
		/*findElement(By.id("sel_"+resourceId), "Select Device").click("Click on Device");*/
		
		
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));
		
	
			
		findElement(By.xpath("(.//ul/li[@id='"+resourceId+"' and @class='ng-scope']/span)[1]"), "Locate Device").click("Click Device");
			
		
		Thread.sleep(2000);
		//findElement(By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span"), "Search Device").click("Select Device");
		findElement(By.xpath("(.//i[@class='fa fa-tachometer'])/.."), "Readings").click("Click on Readings");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='ReadingLoading']/td/div")));
		int downlinkReadings = driver.findElements(By.xpath(".//*[@ng-show='ReadingLoading']/../tr")).size();
		Log.info("Messages Count is "+ downlinkReadings);
		_assert.contains(String.valueOf(downlinkReadings-1),numberOfMessages, "Verify Top 5  Messages");
		
		if(flowType == "uplink") {
			
			findElement(By.xpath("(.//*[text()='View JSON'])[1]"), "Locate View JSON button").click("Click View JSON");
			String Captured = findElement(By.xpath("//*[@id=\"json-renderer\"]/ul/li[2]/ul/li[17]/a"), "Locate CapturedObjects").getAttribute(Attributes.TEXT);
			_assert.equals(Captured, "CapturedObjects", "Verify Decryption Captured Objects");
			
		}
		
			if (flowType == "downlink") {

				findElement(By.xpath("(.//*[text()='View JSON'])[1]"), "Locate View JSON button")
						.click("Click View JSON");
				String Captured = findElement(By.xpath("//*[@id=\"json-renderer\"]/ul/li/ul/li[10]"),
						"Locate FRMPayloadClearText").getAttribute(Attributes.TEXT);
				
				String[] tempManip=Captured.split(":");
				String captureClearText = tempManip[0];
				_assert.equals(captureClearText, "LORA-FRMPayloadClearText", "Verify encrypted cleartext Objects");
				
				String enocdeOutput = tempManip[1];
				assetDetailsProp.setProperty("Encrypted_Key", enocdeOutput);
				Log.info("Encrypted output :" + enocdeOutput);

			}
			
			Thread.sleep(2000);
			findElement(By.xpath("(.//button[@type='button'])[5]"), "Close Button").click("Click Close");
			Thread.sleep(2000);
		
		}catch(Exception e){
			
			Log.error("Failed to Validate Decrypted object");
			Log.error(e.toString());
			throw(e);
		}
		
	}

}
