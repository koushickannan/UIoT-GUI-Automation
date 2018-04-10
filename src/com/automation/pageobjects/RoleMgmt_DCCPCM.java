package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementAction;
import com.hpe.webdriver.ElementBase;

public class RoleMgmt_DCCPCM extends ElementBase{
	
	final static Logger Log = Logger.getLogger(RoleMgmt.class.getName());
	
	By roleMgmtTile = By.xpath(".//*[text()='Role Management']");
	By roleNameTextBox=By.id("roleName");
	By roleDescTextBox=By.id("roleDesc");
	By successAlert = By.cssSelector("[ng-show='serverSuccess']");
	/*By searchRole = By.cssSelector("[placeholder='Search']");*/
	By searchRole = By.xpath("*//input[@ng-model='roleSearch.roleName']");
	By searchRoleList = By.xpath(".//*[contains(@ng-repeat,'roleSearch')]");
	By searchRoleEditIcon = By.xpath(".//*[contains(@ng-repeat,'roleSearch')]/span[3]/i");
	By loader = By.cssSelector("[ng-show='loader']");
	By accordion = By.cssSelector("[class='accordion']>div:nth-child(1)");
	By createButton = By.xpath(".//button[contains(text(),'Create')]");
	
	String roleName = null;

	
	public boolean CreateDeviceController() throws InterruptedException {
		boolean result =false;
		

		try{
		
		findElement(By.xpath(".//span[text()='Device Controllers']"),"DC Tile").click("Click on DC Tile");
		findElement(By.id("dcm_1"),"Manage DC Option").click("Click on Manage DC Option");

		Thread.sleep(3000);
		findElement(By.id("DCName"),"DC Name Text Box").sendKeys(prop.getProperty("controller_name") + randomNumber(),"Enter DC Name");
		findElement(By.id("RoutingType"),"Routing Type Drop Down").selectByIndex(1,"Select Index 1");
		findElement(By.id("TChannel"),"Transport Channel Text Box").sendKeys("http"+randomNumber(),"Enter Transport Channel");
		Thread.sleep(1000);
		findElement(By.cssSelector("[name='oneM2M_compliant']"),"Selecting OneM2M Checkbox").click("Clicking Check box");
		findElement(By.cssSelector("[id='onem2mSchema'][ng-model='dcMgmt.onem2mSchema']"),"Selecting  oneM2M Schema").selectByValue("1.10", "Select latest schema");
		findElements(By.cssSelector("[id='dc_enabled'][ng-model='dcMgmt.enabled']"),"Dc Enabled").click("check DC enabled");
		findElement(By.name("HTTPEndpoint"),"HTTP End Point Text Box").sendKeys(prop.getProperty("url"),"Enter HTTP End Point");
		
		findElement(By.xpath(".//button[contains(text(),'Create')]"),"Create Button").click("Click on Create");
		result=true;
		}catch(Exception e){
			
			Log.error("Unable to Create DC");
			Log.error(e.toString());
			throw(e);
			
		}
		return result;
	}

	public void VerifyDC(String Expected) {

		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-if='successSaveMsg']")));
		
		
		String actual = findElement(By.xpath(".//*[@ng-if='successSaveMsg']"),"Success Alert").getAttribute(Attributes.TEXT);

		_assert.contains(actual, Expected, "Verify DC Creation Success Message");
		
		}catch(Exception e){
			
			Log.error("Unable to Verify DC Success Msg");
			Log.error(e.toString());
			throw(e);
			
		}

	}
	
	public void SearchContainerProfile(String containerProfileName) throws Exception {

		findElement(By.xpath(".//span[text()='Container Profile']"), "Container Profile Tile")
				.click("Click on Container Profile");

		Thread.sleep(2000);
		findElement(By.id("containerP_2"), "Search Container").click("Click on search container ");

		Thread.sleep(2000);

		findElement(By.xpath("//div[@class='col-sm-8']/ul/li/a/i"), "Find Search icon")
		.click("Click on Search icon");

		Thread.sleep(3000);

		findElement(By.xpath("//input[@ng-model='profileName']"), "Profile name").sendKeys(containerProfileName, "Enter Profile Name");

	}
	
	public void SelectAllRole(String roleType, String roleProperty) throws Exception {

		try {

			roleName = roleProperty + "_" + randomNumber();

			findElement(roleMgmtTile, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
			wait.until(ExpectedConditions.elementToBeClickable(accordion));

			findElement(roleNameTextBox, "Enter the Role Name").sendKeys(roleName, "Enter Role name");
			findElement(roleDescTextBox, "Enter Description").sendKeys("Automation Test Role", "Entering description");

			findElement(By.xpath("//div/h3/span[contains(text(),'" + roleType + "')][1]/../span[@id='header_']"),
					"Role Type as " + roleType).click("Click on Role Type  " + roleType + "without Expanding");

			findElement(createButton, "Create Button").click("Click on Create Button");

		} catch (Exception e) {

			Log.error("Unable to select ALL Role");
			Log.error(e.toString());
			throw (e);

		}

	}
	
	public void DeleteRole(String roleProperty) throws Exception {

		try {

			findElement(roleMgmtTile, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
			findElement(searchRole, "Search Role Text Box").sendKeys(roleProp.getProperty(roleProperty),
					"Enter Role that has to be Searched");
			wait.until(ExpectedConditions.visibilityOfElementLocated(accordion));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(searchRoleList,
					roleProp.getProperty(roleProperty).toUpperCase()));

			findElement(By.xpath("*//li/span[contains(@ng-click,'deleteRole')]/i"), "Delete Icon in search")
					.click("Delete Role");
			alertAccept("Click ok");

		} catch (Exception e) {

			Log.error("Unable to Delete Role");
			Log.error(e.toString());
			throw (e);

		}

	}
	
	public void SearchGroup() throws Exception {

		try {
			if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
					.contains("selected")) {

				Thread.sleep(2500);
				driver.findElement(By.xpath(".//span[text()='Customer Mgmt.']")).click();
				Thread.sleep(2000);
				
				
				

			}
			Thread.sleep(2000);
			findElement(By.id("cust_2"), "Search Group").click("Click on Search Group");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			findElement(By.xpath("*//input[@name='groupname']"), "Group Name").sendKeys(prop.getProperty("group_name"), "Enter Group name");
			
			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]"), "Customer List Drop Down")
					.click("Click on Customer Drop Down");
			

			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/div/div/input"), "Customer Drop Down Text Box")
					.sendKeys(CustomerManagement.Cust_Name.toUpperCase(), "Enter Customer Name");
			Thread.sleep(3000);
			
			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li[2]"), "Group name creat").click("Click on Customer Drop Down List");
			
			findElement(By.xpath("*//input[@value='Search']"), "Search button").click("Click on Search");
			Thread.sleep(2000);
			findElement(By.xpath("*//a[@class='ng-binding'][1]"), "Group List").click("Click on Group");
			
			/*JavascriptExecutor js = (JavascriptExecutor)driver;
			findElement(By.xpath("/div/form/div/div/span[@id='groupNameEdit']"), "Verify Group name");
			
			String actualName = (String) js.executeScript("return arguments[0].text",_WebElement);*/
			
			/*_assert.equals(actualName, prop.getProperty("group_name"), "Verify Group name");*/
			
			

		} catch (Exception e) {
			Log.error("Unable to search and verify group");
			Log.error(e.toString());
			throw (e);
		}

	}
	
	public void UpdateGroup() throws Exception {

		try {
			if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
					.contains("selected")) {

				Thread.sleep(2500);
				driver.findElement(By.xpath(".//span[text()='Customer Mgmt.']")).click();
				Thread.sleep(2000);
				
				
				

			}
			Thread.sleep(2000);
			findElement(By.id("cust_2"), "Search Group").click("Click on Search Group");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			findElement(By.xpath("*//input[@name='groupname']"), "Group Name").sendKeys(prop.getProperty("group_name"), "Enter Group name");
			
			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]"), "Customer List Drop Down")
					.click("Click on Customer Drop Down");
			

			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/div/div/input"), "Customer Drop Down Text Box")
					.sendKeys(CustomerManagement.Cust_Name.toUpperCase(), "Enter Customer Name");
			Thread.sleep(3000);
			
			findElement(By.xpath("//td/a/i[contains(@class,'fa')]"), "Group Edit Icon").click("Click on edit group");
			String desc = "Group Description" + randomNumber();
			findElement(By.xpath("*//textarea[@name='sdescription']"), "Description field").sendKeys(desc, "enter description");
			findElement(By.xpath(".//button[contains(text(),'Update')]"), "Update Button").click("Click on Update Button");
			
			
			

		} catch (Exception e) {
			Log.error("Unable to update group");
			Log.error(e.toString());
			throw (e);
		}

	}
	
	public void DeleteGroup() throws Exception {

		try {
			if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
					.contains("selected")) {

				Thread.sleep(2500);
				driver.findElement(By.xpath(".//span[text()='Customer Mgmt.']")).click();
				Thread.sleep(2000);
				
				
				

			}
			Thread.sleep(2000);
			findElement(By.id("cust_2"), "Search Group").click("Click on Search Group");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			findElement(By.xpath("*//input[@name='groupname']"), "Group Name").sendKeys(prop.getProperty("group_name"), "Enter Group name");
			
			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]"), "Customer List Drop Down")
					.click("Click on Customer Drop Down");
			

			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/div/div/input"), "Customer Drop Down Text Box")
					.sendKeys( CustomerManagement.Cust_Name.toUpperCase(), "Enter Customer Name");
			Thread.sleep(3000);
			
			findElement(By.xpath("*//td/span/i[@class='fa fa-trash']"), "Group delete button").click("Click on delete group");
			
			findElement(By.xpath("*//button[@id='removeGroup']"), "Delete group alert message").click("Click Ok");
			
			

		} catch (Exception e) {
			Log.error("Unable to delete group");
			Log.error(e.toString());
			throw (e);
		}

	}

}
