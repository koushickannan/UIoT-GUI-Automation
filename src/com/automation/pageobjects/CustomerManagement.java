package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class CustomerManagement extends ElementBase {
	
	public static String Cust_Name = null;
	
	final static Logger Log = Logger.getLogger(CustomerManagement.class.getName());
	
	By customer_mgmt_dash = By.xpath(".//*[text()='Customer Mgmt.']");
	By create_customer = By.id("cust_1");
	By customer_radio = By.xpath(".//input[@value='CUSTOMER'][@type='radio']");
	By search_customer = By.id("cust_2");
	By select_tab = By.xpath(".//li[contains(@ng-click,'Customer')]");
	By search_quick_link = By.xpath(".//i[@class='icon icon-search']");
	By search_button = By.xpath(".//input[@value='Search']");
	
	By customer_name = By.name("sGroupName");
	By parent_customer = By.id("groupId");
	By retention_time = By.name("sretentionTime");
	By customer_desc = By.name("sdescription");
	By roleId = By.id("roleId");
	By create = By.xpath(".//button[text()='Create']");
	
	public void Search_Customer(boolean... conditions) throws InterruptedException{
		if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
		findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
		}
		Thread.sleep(2000);
		findElement(search_customer, "Search Customer").click("CLick on Search Customer");
		findElement(select_tab, "Customer Tab").click("Select Customer Tab");
		findElement(search_quick_link, "Quick Search").click("Click on Quick Search");
		
		//findElement(By.xpath(".//div[contains(text(),'Customer')]/select"), "Parent Tenant").selectByValue(SmokeTest.groupId, "Select Parent Tenant");
		findElement(search_button, "Search button ").click("Click on Search Button");
		if(conditions[0]){
		findElement(By.name("groupname"),"Customer Name").sendKeys(Cust_Name,Keys.ENTER, "Enter Customer Name");
		findElement(search_button, "Search button ").click("Click on Search Button");
		}
		if(conditions[1]){
			
			findElement(By.linkText("IOT."+Cust_Name.toUpperCase()), "Customer Link").click("Click on Customer Link");
			
			String actual = findElement(By.xpath("(.//*[@class='pageHeader'])[1]"), "Current Page").getAttribute(Attributes.TEXT);
			
			_assert.equals(actual, "Group Details", "Verifying Customer Details Page");
		}
	}
	
	public void Create_Customer() throws InterruptedException{
		
		if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
		findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
		}
		findElement(create_customer, "Create Customer").click("Click on Create Customer");
		
		Cust_Name = "Cust_Automation_"+randomNumber();
		
		findElement(customer_radio, "Customer Radio Button").click("Select Customer Radio Button");
		
		findElement(customer_name, "Customer Name").sendKeys(Cust_Name, "Enter Customer Name");
		
		findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/a"), "Parent Customer").click("Click on Parent Customer");
		
		
		findElements(By.xpath(".//*[contains(@ng-model,'parentId')]/div/ul/li"), "Find Customer COunt");
		
		for(int i=1 ; i<=_WebElements.size();i++){
			
			wait.until(ExpectedConditions.elementToBeClickable(_WebElements.get(i)));
			
			String actual = _WebElements.get(i).getText();
			
			if(actual.equals("IoT")){
				
				_WebElements.get(i).click();
				
				break;
			}
			
		}
		
		//findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/div/ul/li[1]/a"),"Select Parent Customer").click("Selecting IOT as Parent Customer");
		
		//findElement(parent_customer, "Parent Tenant").selectByIndex(1, "Select Parent Tenant");
		
		findElement(retention_time, "Retension Time").sendKeys(prop.getProperty("retention_period"),"Enter Retension Period");
		findElement(customer_desc, "Customer Description").sendKeys("Test Desc", "Enter Customer Description");
		findElement(By.xpath(".//*[contains(text(),'Business Data')]/input"),"Business Data").click("Select Business Data");
		findElement(By.cssSelector("[ng-model='groupDetails.inherit']"),"Inherit Resources").click("Uncheck Inherit Resources");
		findElement(roleId, "Role").selectByValue("100", "Select Role");
		
		Thread.sleep(3000);
		findElement(create, "Create Button").submit("Submit on Create Button");
		Log.info("Customer Create : " + Cust_Name);
		
	}
	
	public void Verify_Customer(){
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
		String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);

		_assert.contains(actual, "successfully", "Verify Create Customer Successs Message");
		 SmokeTest.compare(actual,"successfully");
		
	}

}
