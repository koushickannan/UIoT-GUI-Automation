package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class Dashboard extends ElementBase {
	
	final static Logger Log = Logger.getLogger(ElementBase.class.getName());

	By users_outside = By.xpath(".//span[@ng-bind='valueUsers']");
	By inside = By.cssSelector(".totaldata.redbg");
	By app_outside = By.xpath(".//span[@ng-bind='valueAE']");
	By active_outside = By.xpath(".//span[@ng-bind='valueActiveDevices']");
	By tenant_outside = By.xpath(".//span[@ng-bind='valueTenant']");

	By sensor_outside = By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child(2)>text>tspan");
	By gateway_outside = By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child(1)>text>tspan");

	By shortcut_group = By.xpath(".//*[@title='My Group Details']");
	By shortcut_import = By.xpath(".//*[@title='Import Assets']");
	By shortcut_displayconfig = By.xpath(".//*[@title='Create Display Configuration']");
	By shortcut_user = By.xpath(".//*[@title='Create User']");
	By details = By.id("currentPage");

	By search_dropdown = By.name("searchAction");
	By search_button = By.cssSelector("#assetForm>div>button");

	By logo = By.cssSelector(".logo-element>a>img");

	public void VerifyCustomerNameLink() {

		findElement(shortcut_group, " Group Shortcut").click("Clicking on Group Shortcut");

		findElement(logo, "HPE Logo").click("Clicking on HPE Logo");

		_assert.equals(findElement(details, "Current Page Title").getAttribute(Attributes.TEXT, "Dashboard"),
				"Dashboard", "Verifying if current screen is Dasboard");

	}

	public void VerifyTenantCount() {

		String tenant_dashboard = findElement(tenant_outside, "Tenant link in Dashboard").getAttribute(Attributes.TEXT);

		findElement(tenant_outside, "Tenant link in Dashboard").click("Clicking on Tenant link in Dashboard");

		String tenant_inside = findElement(inside, "Tenant inside Dashboard").getAttribute(Attributes.TEXT);

		_assert.equals(tenant_dashboard, tenant_inside, "Validation of Users Count in Dashboard");

	}

	public void GlobalSearch_VerifyTheList() {

		int count = findElements(By.cssSelector(".form-control.search-select>option"), "Global Search Options")
				.getCount("Element count for Global Search");

		if (count != 0) {
			_assert.equals(_WebElements.get(0).getText(), "Device", "Verify Device in Global Search");
			_assert.equals(_WebElements.get(1).getText(), "User", "Verify User in Global Search");
			_assert.equals(_WebElements.get(2).getText(), "Customer", "Verify Customer in Global Search");
		}

	}

	public void GlobalSearch_VerifyFunctionality(String option) {

		findElement(search_dropdown, "Global Search Select").selectByText(option,
				" Selecting "+option+" from Global Search");

		findElement(search_button, "Search Button").click("Clicking on Search Button");

		if(option.equals("Customer")){
			
			String page_name = findElement(details, "Create User Shortcut").getAttribute(Attributes.TEXT);
			_assert.contains(page_name, "Customer", "Verifying Customer Global Search");
		}
		else{
		String page_name = findElement(details, "Create User Shortcut").getAttribute(Attributes.TEXT);
		_assert.contains(page_name, option, "Verifying " + option + " Global Search");
		}

	}

	public void GlobalSearch_VerifyFunctionalityForUser(String option) {

		findElement(search_dropdown, "Global Search Select").selectByValue(option,
				" Selecting Device from Global Search");

		findElement(search_button, "Search Button").click("Clicking on Search Button");

		String page_name = findElement(details, "Create User Shortcut").getAttribute(Attributes.TEXT);
		_assert.contains(page_name, option, "Verifying " + option + " Global Search");

	}

	public void VerifyCreateUserShortcut() {

		findElement(shortcut_user, "Create User Shortcut").click("Clicking on Create User Shortcut");

		String user_details = findElement(details, "Create User Shortcut").getAttribute(Attributes.TEXT);

		_assert.equals(user_details.trim(), "Create User", "Validation of Create User Shortcut");

	}

	public void VerifyDisplayConfigShortcut() {

		findElement(shortcut_displayconfig, "Display Config Shortcut").click("Clicking on Display Config Shortcut");

		String display_details = findElement(details, "Display Config Shortcut").getAttribute(Attributes.TEXT);

		_assert.equals(display_details.trim(), "Display Profile", "Validation of Display Config Shortcut");

	}

	public void VerifyKeyImportShortcut() {

		findElement(shortcut_import, "Key Import Shortcut").click("Clicking on Key Import Shortcut");

		String import_details = findElement(details, "Tenant Details").getAttribute(Attributes.TEXT);

		_assert.equals(import_details.trim(), "Import Assets", "Validation of Key Import Shortcut");

	}

	public void VerifyMyGroupShortCut() {

		findElement(shortcut_group, "My Group Shortcut").click("Clicking  My Group Shortcut");

		String tenant_details = findElement(details, "Group Details").getAttribute(Attributes.TEXT);

		_assert.equals(tenant_details.trim(), "Group Details", "Validation of My Group Shortcut");
	}

	public void VerifyGatewayCount() throws InterruptedException {

		Thread.sleep(2000);

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(gateway_outside));
		wait.until(ExpectedConditions.elementToBeClickable(gateway_outside));
		String gateway_dashboard=null , gateway_inside="";

	do{
		 gateway_dashboard = findElement(gateway_outside, "Gateway link in Dashboard")
				.getAttribute(Attributes.TEXT);
		 
		 
		 System.out.println("Dashboard Count is"+ gateway_dashboard);
	}while(Integer.parseInt(gateway_dashboard)==0);
		findElement(gateway_outside, "Sensor link in Dashboard").click("Clicking on Gateway link in Dashboard");

		for(int i =0;i<=30;i++){
			Thread.sleep(2000);
		 gateway_inside = findElement(inside, "Gateway inside Dashboard").getAttribute(Attributes.TEXT);
		 System.out.println("Inside Count is"+ gateway_inside);
		 
		}
		_assert.equals(gateway_inside,gateway_dashboard,"Validation of Gateway Count in Dashboard");

	}

	public void VerifySensorsCount() throws InterruptedException {

		Thread.sleep(2000);
		String sensor_dashboard = "" , sensor_inside="";

		findElements(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g"),"Count of Device");
		int device_type_count = _WebElements.size();
		Thread.sleep(4000);
		
		if(device_type_count==1){
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child("+1+")>text>tspan")));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child("+1+")>text>tspan")));
			
			do{
		sensor_dashboard = findElement(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child("+1+")>text>tspan"),"Count of Sensor").getAttribute(Attributes.TEXT);
		System.out.println("Dashboard Count is"+ sensor_dashboard);
			}while(Integer.parseInt(sensor_dashboard)==0);

		findElement(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child("+1+")>text>tspan"), "Sensor link in Dashboard").click("Clicking on Sensor link in Dashboard");
		
		}
		else{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child("+2+")>text>tspan")));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child("+2+")>text>tspan")));
			
			do{
			sensor_dashboard = findElement(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child("+2+")>text>tspan"),"Count of Sensor").getAttribute(Attributes.TEXT);
			System.out.println("Dashboard Count is"+ sensor_dashboard);
			}while(Integer.parseInt(sensor_dashboard)==0);
			findElement(By.cssSelector("#assetType>div>svg>g:nth-child(9)>g:nth-child("+2+")>text>tspan"),"Sensor Bar Graph").click("Click on Sensors Bar Graph");
		}
		
		for(int i =0;i<=30;i++){

			Thread.sleep(2000);
		 sensor_inside = findElement(inside, "Sensor inside Dashboard").getAttribute(Attributes.TEXT);
		
		 System.out.println("Inside Count is"+ sensor_inside);
		}

		_assert.equals(sensor_inside,sensor_dashboard, "Validation of Sensor Count in Dashboard");

	}

	public void VerifyUsersCount() {
		String user_dashboard , user_inside;
		do{
		 user_dashboard = findElement(users_outside, "Users link in Dashboard").getAttribute(Attributes.TEXT);

		 System.out.println("Dashboard Count is "+user_dashboard);
		}while(Integer.parseInt(user_dashboard)==0);
		
		findElement(users_outside, "Users link in Dashboard").click("Clicking on Users link in Dashboard");
		
		do{
		 user_inside = findElement(inside, "Users inside Dashboard").getAttribute(Attributes.TEXT);
		 System.out.println("Inside Count is "+user_inside);
		}while(Integer.parseInt(user_inside)==0);

		_assert.equals(user_inside.trim(),user_dashboard.trim(), "Validation of Users Count in Dashboard");

	}

	public void VerifyAppCount() throws InterruptedException {

		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(app_outside));
		wait.until(ExpectedConditions.elementToBeClickable(app_outside));
		String app_dashboard = null;
		String app_inside="";
		
		Thread.sleep(4000);
		for(int i=1;i<=20;i++){
		 app_dashboard = findElement(app_outside, "App link in Dashboard").getAttribute(Attributes.TEXT);
		 System.out.println("App count Dashboard is "+app_dashboard);
		if(Integer.parseInt(app_dashboard)>0){
			
			break;
		}
		
		}

		findElement(app_outside, "App link in Dashboard").click("Clicking on App link in Dashboard");
		
		Thread.sleep(3000);

		do{
		 app_inside = findElement(inside, "Apps inside Dashboard").getAttribute(Attributes.TEXT);
		 System.out.println("App count Inside is "+app_inside);
		}while(Integer.parseInt(app_inside)==0);

		_assert.equals(app_inside,app_dashboard, "Validation of App Count in Dashboard");

	}

	public void VerifyActiveCount() throws InterruptedException {

		Thread.sleep(2000);
		String active_dashboard="" , active_inside="";
		wait.until(ExpectedConditions.visibilityOfElementLocated(active_outside));
		wait.until(ExpectedConditions.elementToBeClickable(active_outside));
		
		Thread.sleep(2000);
		 active_dashboard = findElement(active_outside, "Active Devices link in Dashboard")
				.getAttribute(Attributes.TEXT);
		 Log.info("Dashboard Count is "+ active_dashboard);
		

		 Thread.sleep(2000);
		findElement(active_outside, "Active Devices link in Dashboard")
				.click("Clicking on Active Devices link in Dashboard");

		 active_inside = findElement(By.cssSelector(".pagination>li"), "Apps inside Dashboard").getAttribute(Attributes.TEXT);
		
		for(int count = 0; count <=30;count++){
			Thread.sleep(2000);
			active_inside = findElement(inside, "Apps inside Dashboard").getAttribute(Attributes.TEXT);
			Log.info("Inside Count is "+ active_inside);
			}
		
		
		

		_assert.contains(active_inside,active_dashboard, "Validation of Active Devices Count in Dashboard");

	}

}
