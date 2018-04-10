package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class ApplicationMgmt extends ElementBase{

	final static Logger Log = Logger.getLogger(ApplicationMgmt.class.getName());
	
	By app_mgmt = By.xpath(".//*[text()='Application Mgmt.']");
	By add_routing = By.xpath(".//*[@href='/dsm/applicationEntityMgmt/addRoutingRule.htm']");
	By message_type = By.xpath(".//*[@id='assetAttribTab']/tbody/tr[2]/td[2]/div/input");
	By app_ui = By.xpath(".//*[@id='assetAttribTab']/tbody/tr[3]/td[2]/div/input");
	
	By tenant_name = By.xpath(".//*[@id='editAddForm']/table[2]/tbody/tr/td[2]/div/select");
	By app_name = By.xpath(".//*[@id='editAddForm']/table[2]/tbody/tr/td[4]/div/select");
	By rule_name = By.id("value");
	
	// assetAttribTab
	By manageApp = By.id("ae_2");
	By subscriptionEdit = By.xpath(".//*[contains(@href,'subscription')]");
	By addSubscriptionButton = By.id("acpSelect");
	By selectLogicalResource = By.xpath(".//select[contains(@ng-options,'resource.name')]");
	By containerType = By.id("acpLocationSelect");
	By okButton = By.cssSelector("[ng-click='subscribe()']");
	By treeSearch = By.cssSelector("[id='hpeTree_treeSearch']");
	
	public void addSubscriptionToApplication(String resourceName ,String resourceId , String containerName) throws Exception{
		
		try{
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Application Mgmt.']"),"Application Mgmt Tile").click("Click on Application Mgmt");
				Thread.sleep(2000);
			}
			findElement(manageApp, "Manage App").click("Click on Manage App");
			findElement(subscriptionEdit, "Subscription Edit Icon").click("Click on Subscription Icon");
			findElement(addSubscriptionButton, "Add Subscription Button").click("Click on Add Subscription Button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));
			Thread.sleep(2000);
			findElement(By.xpath(".//*[text()='"+SmokeTest.Group.toUpperCase()+"']"), "Group Tag").click("Click on Group Tag");
			
			findElement(treeSearch, "Device Search Text Box").sendKeys(resourceName, "Enter Device Name");
			
			Thread.sleep(3000);
			findElement(By.xpath(".//span[contains(@ng-if,'match.model.resourceType')]"), "Locate Search").click("Search Asset");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li[@id='" + resourceId + "']/span")));
			
			findElement(By.xpath(".//li[@id='" + resourceId + "']/span"), "Asset Name with ID "+resourceId).click("Click on Asset with ID "+resourceId);
			Thread.sleep(2000);
			
			findElement(selectLogicalResource, "Logical Resource Drop Down").selectByText("Container", "Select Container");
			findElement(containerType, "Container Type Drop Down").selectByText(containerName, "Select Container Type Drop Down");
			findElement(okButton, "Subcription Done").click("Click on Ok Button");
			Thread.sleep(2000);
			
		}catch(Exception e){
			
			Log.error("Unable to add subscribe for device id"+resourceId +"with container as "+ containerName);
			Log.error(e.toString());
			throw(e);
			
		}
	}
	
	public void Add_RoutingRule(){
		
		if(!driver.findElement(By.xpath("(.//*[text()='Application Mgmt.'])/..")).getAttribute("class").contains("selected")){
		findElement(app_mgmt, "Application Mgmt").click("Click on Application Mgmt");
		}
		findElement(add_routing, "Add Routing Rule").click("Click on Add Routing Rule");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("assetAttribTab")));
		
		findElement(message_type, "Message Type").sendKeys("AutomationType", "Enter Message Type");
		findElement(app_ui, "App UI").sendKeys("1234567890123456","Enter App UI Values");
		
		findElement(tenant_name, "Tenant Name").selectByText("IOT."+CustomerManagement.Cust_Name.toUpperCase()+"."+SmokeTest.Group.toUpperCase(), "Select Group Name");
		
		findElement(rule_name, "Rule Name").sendKeys("Rule_Automation_"+randomNumber(), "Enter Rule Name");
		
		findElement(By.xpath(".//*[@id='editAddForm']/table[2]/tbody/tr/td[4]/div/select"),"Application Name").selectByIndex(1, "Select Application Name");
		
		findElement(By.id("addruleBtn"),"Add Rule").click("Click on Create");
	}
	
	public void Verify_Role(){
		// .//*[@class='alert alert-success ng-scope']
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success ng-scope']")));
		String actual = findElement(By.xpath(".//*[@class='alert alert-success ng-scope']//strong"),"Success Alert").getAttribute(Attributes.TEXT);

		 SmokeTest.compare(actual, "Success");
		
	}
	
}
