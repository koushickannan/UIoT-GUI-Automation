package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class AddressPoolMgmt extends ElementBase {
	final static Logger logger = Logger.getLogger(AddressPoolMgmt.class);
	String available = "";
	
	static String defaultDevEUI , defaultDevAddr , DevEUI , DevAddr , device = ""; 
	
	static String startHexRange , endHexRange = "";
	
	public void VerifyTile(){
		try{
		if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
			}
		findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/create/']"),"Create Address Pool").click("Click on Create Add Pool");
		String pageName = findElement(By.xpath("(.//*[@id='currentPage']/span)[1]"),"Current Page").getAttribute(Attributes.TEXT);
		
		_assert.contains(pageName, "Create Address Pool","Verify Current Page");
		
		findElement(By.cssSelector("a[href*='addressPool/#/manage']"),"Search Pool").click("Click on Search");
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(.//*[@id='currentPage'])[1]"), "Manage Address Pools"));
		pageName = findElement(By.xpath("(.//*[@id='currentPage'])[1]"), "Current Page").getAttribute(Attributes.TEXT);
		
		_assert.contains(pageName, "Manage Address Pools","Verify Current Page");
		}catch(Exception e){
			
			logger.error(e.toString());
			throw(e);
			
		}
	}
	
	public void CreateDevEUI(boolean isDefaultPool){
		try{
		if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
			}
		findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/create/']"),"Create Address Pool").click("Click on Create Add Pool");
		
		findElement(By.cssSelector("div[name='poolType']"),"Pool Type").click("Click on Pool Type");
		
		findElement(By.cssSelector("ul[role='menu']>li:nth-child(3)"),"DevEUI").click("Select DevEUI");
		
		 int startRange = randomNumber();
		 int endRange = startRange+1;
		 startHexRange = Integer.toHexString(startRange);
		 endHexRange = Integer.toHexString(endRange);
		
		findElement(By.name("startRange"),"Start Range").sendKeys(startHexRange,"Enter Start Range"); 
		findElement(By.name("endRange"),"End Range").sendKeys(endHexRange,"Enter End Range");
		
		logger.info("Start Range :"+startHexRange);
		logger.info("End Range :"+endHexRange);
		
		findElement(By.name("customerId"),"Customer Drop Down").click("Click on Customer Drop Down");
		findElement(By.cssSelector("div[name='customerId']>div>ul>li:nth-child(2)"),"Select Customer").click("Click on Customer");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("defaultPool")));
		
		String deafaultvalue = "";
		if(isDefaultPool){
			
		findElement(By.name("defaultPool"),"Default Pool").click("Select Default Pool");
		if(isAlertPreset()){
			alertAccept("Accept");
		}
		deafaultvalue = "Default_DevEUI_"+randomNumber();
		findElement(By.cssSelector("input[name='poolName']"),"Pool Name").sendKeys(deafaultvalue,"Enter DevEUI");
		
		logger.info("DevEUI Created "+deafaultvalue);
		findElement(By.name("defaultPool"),"Default Pool").click("Select Default Pool");
		if(isAlertPreset()){
			alertAccept("Accept");
		}
		
		}else{
			deafaultvalue = "DevEUI_"+randomNumber();
			findElement(By.cssSelector("input[name='poolName']"),"Pool Name").sendKeys(deafaultvalue,"Enter DevEUI");
		}
		
		findElement(By.xpath(".//button[contains(text(),'Create')]"),"Create").click("Click on Create Button");
		
		if(isDefaultPool){
			defaultDevEUI=deafaultvalue;
		}else{
			DevEUI=deafaultvalue;
		}
		
		}catch(Exception e){
			logger.error(e.toString());
			throw(e);
		}
		
	}
	
	public void CreateDevEUISameRange(boolean isDefaultPool , String startRange , String endRange){
		
		try{
			if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
				findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
				}
			findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/create/']"),"Create Address Pool").click("Click on Create Add Pool");
			
			findElement(By.cssSelector("div[name='poolType']"),"Pool Type").click("Click on Pool Type");
			
			findElement(By.cssSelector("ul[role='menu']>li:nth-child(3)"),"DevEUI").click("Select DevEUI");
			
			
			findElement(By.name("startRange"),"Start Range").sendKeys(startRange,"Enter Start Range"); 
			findElement(By.name("endRange"),"End Range").sendKeys(endRange,"Enter End Range");
			
			logger.info("Start Range :"+startRange);
			logger.info("End Range :"+endRange);
			
			findElement(By.name("customerId"),"Customer Drop Down").click("Click on Customer Drop Down");
			findElement(By.cssSelector("div[name='customerId']>div>ul>li:nth-child(2)"),"Select Customer").click("Click on Customer");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.name("defaultPool")));
			
			String deafaultvalue = "";
			if(isDefaultPool){
				
			findElement(By.name("defaultPool"),"Default Pool").click("Select Default Pool");
			if(isAlertPreset()){
				alertAccept("Accept");
			}
			deafaultvalue = "Default_DevEUI_"+randomNumber();
			findElement(By.cssSelector("input[name='poolName']"),"Pool Name").sendKeys(deafaultvalue,"Enter DevEUI");
			
			logger.info("DevEUI Created "+deafaultvalue);
			findElement(By.name("defaultPool"),"Default Pool").click("Select Default Pool");
			if(isAlertPreset()){
				alertAccept("Accept");
			}
			
			}else{
				deafaultvalue = "DevEUI_"+randomNumber();
				findElement(By.cssSelector("input[name='poolName']"),"Pool Name").sendKeys(deafaultvalue,"Enter DevEUI");
			}
			
			findElement(By.xpath(".//button[contains(text(),'Create')]"),"Create").click("Click on Create Button");
			
			
			}catch(Exception e){
				logger.error(e.toString());
				throw(e);
			}
		
		
		
		
	}
	
	public void CreateDevAddr(boolean isDefaultPool,String startBits) throws Exception{
		
		try{
		
		if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
			}
		
		findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/create/']"),"Create Address Pool").click("Click on Create Add Pool");
		
		findElement(By.cssSelector("div[name='poolType']"),"Pool Type").click("Click on Pool Type");
		
		findElement(By.cssSelector("ul[role='menu']>li:nth-child(2)"),"DevAddr").click("Select DevAddr");
		
		
		String defaultValue = "";
		
		if(isDefaultPool){
		findElement(By.name("devAddrByteS1"), "Start Rage Byte 1").sendKeys(startBits, "Enter Start Range Byte 1");
		findElement(By.name("devAddrByteS2"), "Start Rage Byte 2").sendKeys("FF", "Enter Start Range Byte 2");
		findElement(By.name("devAddrByteS3"), "Start Rage Byte 3").sendKeys("FF", "Enter Start Range Byte 3");
		findElement(By.name("devAddrByteS4"), "Start Rage Byte 4").sendKeys("00", "Enter Start Range Byte 4");
		
		findElement(By.name("devAddrByteE1"), "End Range Byte 1").sendKeys(startBits,"Enter End Range Byte 1");
		findElement(By.name("devAddrByteE2"), "End Range Byte 2").sendKeys("FF","Enter End Range Byte 2");
		findElement(By.name("devAddrByteE3"), "End Range Byte 3").sendKeys("FF","Enter End Range Byte 3");
		findElement(By.name("devAddrByteE4"), "End Range Byte 4").sendKeys("01","Enter End Range Byte 4");
		
		logger.info("Start Range :"+"10 FF FF 00");
		logger.info("End Range :"+"10 FF FF 01");
		
		defaultValue = "Default_DevAddr_"+randomNumber();
		findElement(By.cssSelector("input[name='poolName']"),"Pool Name").sendKeys(defaultValue,"Enter DevEUI");
		
		logger.info("Default DevAddr :"+defaultValue);
		
		}
		else{
			
			findElement(By.name("devAddrByteS1"), "Start Rage Byte 1").sendKeys("8", "Enter Start Range Byte 1");
			findElement(By.name("devAddrByteS2"), "Start Rage Byte 2").sendKeys("FF", "Enter Start Range Byte 2");
			findElement(By.name("devAddrByteS3"), "Start Rage Byte 3").sendKeys("FF", "Enter Start Range Byte 3");
			findElement(By.name("devAddrByteS4"), "Start Rage Byte 4").sendKeys("00", "Enter Start Range Byte 4");
			
			findElement(By.name("devAddrByteE1"), "End Range Byte 1").sendKeys("8","Enter End Range Byte 1");
			findElement(By.name("devAddrByteE2"), "End Range Byte 2").sendKeys("FF","Enter End Range Byte 2");
			findElement(By.name("devAddrByteE3"), "End Range Byte 3").sendKeys("FF","Enter End Range Byte 3");
			findElement(By.name("devAddrByteE4"), "End Range Byte 4").sendKeys("05","Enter End Range Byte 4");
			
			logger.info("Start Range :"+"8 FF FF 00");
			logger.info("End Range :"+"8 FF FF 05");
			
			defaultValue = "DevAddr_"+randomNumber();
			findElement(By.cssSelector("input[name='poolName']"),"Pool Name").sendKeys(defaultValue,"Enter DevEUI");
			
			logger.info("DevAddr :"+defaultValue);
			
			
		}
		
		findElement(By.name("customerId"),"Customer Drop Down").click("Click on Customer Drop Down");
		findElement(By.cssSelector("div[name='customerId']>div>ul>li:nth-child(2)"),"Select Customer").click("Click on Customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("defaultPool")));
		if(isDefaultPool){
		findElement(By.name("defaultPool"),"Default Pool").click("Select Default Pool");
		if(isAlertPreset()){
			alertAccept("Accept");
		}
		findElement(By.name("defaultPool"),"Default Pool").click("Select Default Pool");
		if(isAlertPreset()){
			alertAccept("Accept");
		}
		}
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String netId = (String) js.executeScript("return document.getElementsByName('custNwkId')[0].getAttribute('class');");
		
		logger.info("Network Id Class Value is "+ netId);
		
		//findElement(By.name("custNwkId"),"Network ID").getAttribute(Attributes.GENERAL,"disabled");
		
		//String netId = findElement(By.name("custNwkId"), "Netwrok Id Disabled").getAttribute(Attributes.GENERAL,"ng-disabled");
		
		if(findElement(By.name("custNwkId"),"Network ID").getAttribute(Attributes.GENERAL,"class").contains("ng-empty")){
		findElement(By.name("custNwkId"),"Network ID").sendKeys("12","Enter Network ID");
		}
		
		findElement(By.xpath(".//button[contains(text(),'Create')]"),"Create").click("Click on Create Button");
		
		if(isDefaultPool){
			defaultDevAddr = defaultValue;
		}
		else{
			DevAddr = defaultValue;
		}
		
		}catch(Exception e){
			
			logger.error(e.toString());
			throw(e);
			
		}
	}
	
	public void Verify() throws Exception{
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
		
		Thread.sleep(3000);
		
		String msg = findElement(By.cssSelector(".alert.alert-success"),"Success Message").getAttribute(Attributes.TEXT);
		
		_assert.contains(msg, "Success","Verify Pool Success Message");
		
		if(driver.findElements(By.xpath(".//*[@class='alert alert-danger']")).size()!=0){
			
			String alert = findElement(By.xpath(".//*[@class='alert alert-danger']"), "Alert").getAttribute(Attributes.TEXT);
			
			logger.info(alert);
			if(alert.contains("Auto Registration Failed!")){
				
				logger.error("Device Auto Registration Failed");
			}
		}
		
		}catch(Exception e){
			
			logger.info("Unable to find Successs Message");
			logger.error(e.toString());
			throw(e);
		}
	}
	
	public void UpdateDevEUI() throws Exception{
		
		try{
		if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
			}
		findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
		
		findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
		logger.info("DevEUI Pools count is "+ _WebElements.size());
		
		for(int i=1;i<=_WebElements.size();i++){
			String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
			
			if(poolName.equals(DevEUI)){
				logger.info("Pool Name matches with " + DevEUI);
				available =	findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(3)"), "Available Range").getAttribute(Attributes.TEXT);
				logger.info("Available Pool Count is "+ available);
				
				findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(6)>a:nth-child(1)"), "Edit Icon").click("Click on Edit");
				break;
			}
		}
		Thread.sleep(2000);
		findElement(By.name("endRange"),"End Range").sendKeys(endHexRange,"Edit End Range");
		logger.info("End Range Updated to :"+ endHexRange);
		
		findElement(By.xpath(".//*[text()='Update']"),"Update Butto").click("Click on Update Button");
		}catch(Exception e){
			
			logger.error(e.toString());
			throw(e);
		}
	}
	
	public void VerifyDevEUIUpdate(){
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
		String msg = findElement(By.cssSelector(".alert.alert-success"),"Success Alert").getAttribute(Attributes.TEXT);
		_assert.contains(msg, "Success", "Verify Update Success");
		
		findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
		
		findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
		
		String afterEdit = "";
		for(int i=1;i<=_WebElements.size();i++){
			String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
			
			if(poolName.equals(defaultDevEUI)){
				
			afterEdit = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(3)"), "Available Range").getAttribute(Attributes.TEXT);
				break;
			}
		}
		
		_assert.contains(afterEdit, "2","Verify DevEUI Update");
		
		}catch(Exception e){
			
			logger.error(e.toString());
			throw(e);
		}
	}
	
	public void UpdateDevAddr(){
		try{
		
		if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
			}
		findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
		
		findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
		
		for(int i=1;i<=_WebElements.size();i++){
			String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
			
			if(poolName.equals(defaultDevAddr)){
				available =	findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(3)"), "Available Range").getAttribute(Attributes.TEXT);
				findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(6)>a:nth-child(1)"), "Edit Icon").click("Click on Edit");
				break;
			}
		}
		findElement(By.name("devAddrByteE4"), "End Range Byte 4").sendKeys("01","Edit End Range Byte 4");
		
		logger.info("End Range of Byte 4 Updated to : 01");
		findElement(By.xpath(".//*[text()='Update']"),"Update Button").click("Click on Update Button");
		}catch(Exception e){
			
			logger.error(e.toString());
			throw(e);
		}
	}
	
	public void VerifyDevAddrUpdate(){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
			String msg = findElement(By.cssSelector(".alert.alert-success"),"Success Alert").getAttribute(Attributes.TEXT);
		_assert.contains(msg, "Success", "Verify Update Success");
		
		findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
		
		findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
		
		String afterEdit = "";
		for(int i=1;i<=_WebElements.size();i++){
			String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
			
			if(poolName.equals(defaultDevAddr)){
				
			afterEdit = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(3)"), "Available Range").getAttribute(Attributes.TEXT);
				break;
			}
		}
		
		_assert.contains(afterEdit, "1","Verify DevEUI Update");
		
		}catch(Exception e){
			
			logger.error(e.toString());
			throw(e);
		}
	}
	
	public void DeletePoolDevEUI() throws Exception{
		try{
			
			if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
				findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
				}
			findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
			
			Thread.sleep(2000);
			String countBeforeDelete = findElement(By.cssSelector(".totaldata.redbg.ng-binding"),"Pool Count Before Delete").getAttribute(Attributes.TEXT);
			
			logger.info("Count Before Delete "+countBeforeDelete);
			findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
			
			for(int i=1;i<=_WebElements.size();i++){
				String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
				
				if(poolName.equals(DevEUI)){
					findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(6)>a:nth-child(2)"), "Delete Icon").click("Click on Edit");
					break;
				}
			}
			
			alertAccept("Confirm Delete of DevEUI");
			
			driver.navigate().refresh();
			
			Thread.sleep(2000);
			String countAfterDelete = findElement(By.cssSelector(".totaldata.redbg.ng-binding"),"Pool Count Before Delete").getAttribute(Attributes.TEXT);
			
			logger.info("Count After Delete "+countAfterDelete);
			
			_assert.equals(Integer.parseInt(countAfterDelete),Integer.parseInt(countBeforeDelete)-1, "Verify Delete of DevEUI");
			
		}catch(Exception e){
		logger.error(e.toString());
		throw(e);
		}
		
	}
	
	public void DeletePoolDevAddr() throws Exception{
		try{
			
			if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
				findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
				}
			findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
			
			Thread.sleep(3000);
			String countBeforeDelete = findElement(By.cssSelector(".totaldata.redbg.ng-binding"),"Pool Count Before Delete").getAttribute(Attributes.TEXT);
			logger.info("Count Before Delete "+countBeforeDelete);
			
			findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
			
			for(int i=1;i<=_WebElements.size();i++){
				String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
				
				if(poolName.equals(DevAddr)){
					findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(6)>a:nth-child(2)"), "Delete Icon").click("Click on Edit");
					break;
				}
			}
			
			alertAccept("Confirm Delete of DevAddr");
			driver.navigate().refresh();
			Thread.sleep(3000);
			String countAfterDelete = findElement(By.cssSelector(".totaldata.redbg.ng-binding"),"Pool Count Before Delete").getAttribute(Attributes.TEXT);
			logger.info("Count After Delete "+countAfterDelete);
			_assert.equals(Integer.parseInt(countAfterDelete),Integer.parseInt(countBeforeDelete)-1, "Verify Delete of DevAddr");
			
		}catch(Exception e){
		logger.error(e.toString());
		throw(e);
		}
		
	}
	
	public void SearchPool(boolean individualPool) throws Exception{
		try{
			if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
				findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
				}
			findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
			
			findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
			
			for(int i=1;i<=_WebElements.size();i++){
				String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
				
				if(poolName.equals(defaultDevEUI)){
					boolean available =	findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(3)"), "Available Range").isDisplayed();
					_assert.equals(available, "Verify Available Status");
					
					findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(5)>button"),"Address Pool Link").click("Click on Address Pool");
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='popover-content']")));
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='popover-content']")));
					
					findElement(By.xpath(".//input[contains(@ng-model,'searchCriteria')]"),"Popup Search").sendKeys(String.valueOf(startHexRange),"Enter Search Address");
					findElement(By.cssSelector("#searchContainer>a"), "Search Button").click("Click on Search Button");
					//wait

					Thread.sleep(2000);
					String searchResult = findElement(By.xpath("(.//*[text()='Searched Address:'])/../span[2]"),"Search Reasult").getAttribute(Attributes.TEXT);
					if(individualPool){
						_assert.contains(searchResult, "Available", "Verify Availability of Pool Search");
					}
					else{
					_assert.contains(searchResult, "Available", "Verify Availability of Pool Search");
					}
					findElement(By.xpath(".//input[contains(@ng-model,'searchCriteria')]"),"Popup Search").sendKeys("13","Enter Search Address");
					findElement(By.cssSelector("#searchContainer>a"), "Search Button").click("Click on Search Button");
					
					Thread.sleep(2000);
					 searchResult = findElement(By.xpath("(.//*[text()='Searched Address:'])/../span[2]"),"Search Reasult").getAttribute(Attributes.TEXT);
					_assert.contains(searchResult, "Invalid", "Verify Availability of Pool Search");
					
					findElement(By.xpath(".//button[text()='Done']"),"Popup Done").click("Click on Popup Done");
					
					break;
				}
			}
			
			
		}catch(Exception e){
			logger.error("Unable to Verify Pool Search");
			logger.error(e.toString());
			throw(e);
		}
	}
	
	public void VerifyManualAddressEntry() throws Exception{
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}
			
			findElement(By.id("asset_2"),"Create Asset").click("Click on Create Asset");
			
			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"),"Customer Drop Down").click("Click on Customer Drop Down");
			
			wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(".//*[@ng-model='deviceDetails.customerId']//*[@ng-hide='searchTerm']"),"There are no items to display"));
			findElement(By.xpath("(.//div[@class='custom-select-search'])[2]/../ul/li[2]"),"Customer Drop Down list").click("Select Customer");
			
			findElement(By.xpath(".//div[@ng-model='deviceDetails.deviceProfileId']"),"DP Drop Down").click("Click on DP Drop Down");
			
			wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(".//*[@ng-model='deviceDetails.deviceProfileId']//*[@ng-hide='searchTerm']"),"There are no items to display"));
			
			findElement(By.xpath("(.//div[@class='custom-select-search'])[3]/../ul/li[2]"),"Dp List").click("Click on Dp list");
	
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[ng-if='assetParamView']>div>div")));
		
			boolean deveui = findElement(By.cssSelector("input#iotParamsDevEUI"),"DevEUI Input").isDisplayed();
			boolean devaddr = findElement(By.cssSelector("input#iotParamsDevAddr"), "DevAddr Input").isDisplayed();
			
			_assert.equals(deveui, "Verify if DevEUI is Displayed");
			_assert.equals(devaddr, "Verify if DevAddr is Displayed");
			
		}catch(Exception e){
			
			logger.error(e.toString());
			throw(e);
		}
	}
	
	public void CreateDeviceWithPoolAddr(boolean createSubCustomer) throws Exception{
		
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}
			
			findElement(By.id("asset_2"),"Create Asset").click("Click on Create Asset");
			
			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"),"Customer Drop Down").click("Click on Customer Drop Down");
			
			if(createSubCustomer){
			findElement(By.xpath("(.//div[@class='custom-select-search'])[2]/../ul/li[3]"),"Customer Drop Down list").click("Select Customer");
			}
			else{
				
				findElement(By.xpath("(.//div[@class='custom-select-search'])[2]/../ul/li[2]"),"Customer Drop Down list").click("Select Customer");
				
			}
			
			findElement(By.xpath(".//div[@ng-model='deviceDetails.deviceProfileId']"),"DP Drop Down").click("Click on DP Drop Down");
			Thread.sleep(3000);
			findElement(By.xpath("(.//div[@class='custom-select-search'])[3]/../ul/li[2]"),"Dp List").click("Click on Dp list");
			
			device = "Auto_Device_AddPool_"+randomNumber();
			
			logger.info("Device Name Generated is "+device);
			
			findElement(By.name("resourceName"), "Asset Name").sendKeys(device, "Enter Asset Name");
			findElement(By.id("resourceType"), "Asset Type Drop Down").selectByValue("SENSOR", "Select SENSOR");
			
			findElement(By.xpath(".//*[contains(@ng-model,'deviceGroupId')]"),"Group Drop Down").click("Click on Group Dropdown");
			Thread.sleep(3000);
			findElement(By.xpath("(.//div[@class='custom-select-search'])[4]/../ul/li[2]"), "Group List").click("Click on Group List");
			
			Thread.sleep(2000);
			findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//section[@ng-if='assetParamView']/div")));
			findElement(By.cssSelector("button#iotParamsDevEUI"),"DevEUI Address Pool").click("Click on DevEUI Add Pool Link");
			Thread.sleep(3000);
			if(!createSubCustomer){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class,'popover ')]")));
			Thread.sleep(3000);
			findElement(By.cssSelector("[ng-model='searchCriteria']"),"Pool Search").sendKeys(defaultDevEUI, "Enter Pool Name");
			Thread.sleep(3000);
			}
			else{
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class,'popover ')]")));
				Thread.sleep(3000);
				findElement(By.cssSelector("[ng-model='searchCriteria']"),"Pool Search").sendKeys(defaultDevEUI, "Enter Pool Name");
				Thread.sleep(3000);
			}
			findElement(By.xpath(".//tr[contains(@ng-click,'selectPool')]"),"DevEUI from Pool").click("Click on DevEUI");
			Thread.sleep(2000);
			
			findElement(By.cssSelector("button#iotParamsDevAddr"),"DevAddr Address Pool").click("Click on DevAddr Add Pool Link");
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class,'popover ')]")));
			Thread.sleep(3000);
			findElement(By.cssSelector("[ng-model='searchCriteria']"),"Pool Search").sendKeys(defaultDevAddr, "Enter Pool Name");
			Thread.sleep(3000);
			findElement(By.xpath(".//tr[contains(@ng-click,'selectPool')]"),"DevAddr from Pool").click("Click on DevAddr");
			Thread.sleep(2000);
			
			findElement(By.id("iotParamsAppEUI"),"AppEUI").sendKeys(String.valueOf(randomNumber()),"Enter AppEUI");
			
			findElement(By.xpath("(.//button[text()='Create'])[1]"),"Create Button").click("Click on Create Button");
			
			
		}catch(Exception e){
			
			logger.error("Unable to create device with Address Pool");
			logger.error(e.toString());
			throw(e);
			
		}
		
	}
	
	public void VerifyDevEUIAfterPoolDelete(){
		
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}
			
			findElement(By.id("asset_3"),"Manage Asset").click("Click on Manage Asset");
			
			findElement(By.cssSelector("[class='btn quicklinks']"),"Search Quick").click("Click on Search");
			findElement(By.xpath(".//input[contains(@ng-model,'includeSubGroups')]"),"Include Sub Groups").click("Click on CheckBox");
			findElement(By.xpath(".//input[@ng-click='search()']"),"Search Button").click("Click on Search");
			
			findElement(By.linkText(device),"Device").click("Click on Device");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//label[text()='DevEUI'])/../div[1]/span")));
			
			String devEuiValue = findElement(By.xpath("(.//label[text()='DevEUI'])/../div[1]/span"),"DevEUI Value").getAttribute(Attributes.TEXT);
			
			_assert.equals(!devEuiValue.isEmpty(),"Verfiy DevEUI");
			
		}catch(Exception e){
			
			logger.error("Unable to find DevEUI for the Device");
			logger.error(e.toString());
			throw(e);
			
		}
		
	}
	
	public void CreateSubCustomer() throws Exception{
		try{
		if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(By.xpath(".//*[text()='Customer Mgmt.']"), "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
			}
		
		findElement(By.id("cust_1"), "Create Customer").click("Click on Create Customer");
		
		findElement(By.cssSelector("input[value='CUSTOMER'][type='radio']"), "Customer Radio Button").click("Customer Radio Button");
		
		findElement(By.name("sGroupName"),"Customer Name").sendKeys("SubCustomer"+randomNumber(),"Enter Sub Customer Name");
		
		findElement(By.cssSelector("[ng-model='groupDetails.parentId']"),"Customer Drop Down").click("Click on Customer Drop Down");
		findElement(By.xpath("(.//div[@class='custom-select-search'])[1]/../ul/li[2]"),"Customer Drop Down list").click("Select Customer");
		findElement(By.id("roleId"),"Role Drop Down").selectByValue("100", "Select DSM Admin Role");
		
		findElement(By.name("sdescription"), "Description").sendKeys("Test","Enter Description");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[text()='Create']")));
		Thread.sleep(3000);
		findElement(By.xpath(".//*[text()='Create']"),"Create Button").click("Click on Create Button");

		}catch(Exception e){
			
			logger.error("Unable to Create Sub Customer");
			logger.error(e.toString());
			throw(e);
		}
		
	}
	
	public void poolAvailability(){
		try{
			if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
				findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
				}
			findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
			
			findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
			
			for(int i=1;i<=_WebElements.size();i++){
				String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
				
				if(poolName.equals(defaultDevEUI)){
					available =	findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(3)"), "Available Range").getAttribute(Attributes.TEXT);
					break;
				}
			}
			
			_assert.contains(available, "Not Available", "Verify Pool Status");
			
		}catch(Exception e){
			
			logger.error("Unable to Verify Pool Availability");
			logger.error(e.toString());
			throw(e);
		}
		
	}
	
	public void DevAddrMSPCheck(){
		try{
		if(!findElement(By.xpath("(.//*[text()='Address Pool Mgmt.'])/.."),"Address Pool Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(By.xpath(".//*[text()='Address Pool Mgmt.']"), "Add Pool Mgmt link in Dashboard").click("Click on Add Pool Mgmt Link");
			}
		findElement(By.cssSelector("a[href='/dsm/angular/addressPool/#/manage/']"),"Manage Add Pool").click("Click on Manage");
		
		findElements(By.cssSelector("table[st-table='addrList']>tbody>tr"), "Pools Count");
		
		for(int i=1;i<=_WebElements.size();i++){
			String poolName = findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(1)"), "PoolName").getAttribute(Attributes.TEXT);
			
			if(poolName.equals(defaultDevAddr)){
				boolean available =	findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(3)"), "Available Range").isDisplayed();
				_assert.equals(available, "Verify Available Status");
				
				findElement(By.cssSelector("table[st-table='addrList']>tbody>tr:nth-child("+i+")>td:nth-child(5)>button"),"Address Pool Link").click("Click on Address Pool");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='popover-content']")));
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='popover-content']")));
				
				String availableRange = findElement(By.xpath(".//*[@ng-show='addrAvailable']/span"),"Available Range").getAttribute(Attributes.TEXT);
				
				_assert.contains(availableRange.trim(), "16", "Verify MSP of DevAddr");
				findElement(By.xpath(".//button[text()='Done']"),"Popup Done").click("Click on Popup Done");
				
				break;
			}
		}
		
		
	}catch(Exception e){
		logger.error("Unable to Verify Pool Search");
		logger.error(e.toString());
		throw(e);
	}
		
		
	}

	public void VerifyPoolForSameRange() {
		try{
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alert alert-danger']")));
			
			String msg = findElement(By.cssSelector("[class='alert alert-danger']"),"Error Message").getAttribute(Attributes.TEXT);
			
			_assert.contains(msg, "Error","Verify Pool Success Message");
			
			
			
		}catch(Exception e){
			
			logger.error(e.toString());
			throw(e);
		}
		
	}
	


}
