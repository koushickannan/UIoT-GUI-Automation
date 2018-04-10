package com.automation.pageobjects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class RoleMgmt extends ElementBase {
	
	final static Logger Log = Logger.getLogger(RoleMgmt.class.getName());
	
	By roleMgmtTile = By.xpath(".//*[text()='Role Management']");
	By roleNameTextBox=By.id("roleName");
	By roleDescTextBox=By.id("roleDesc");
	By successAlert = By.cssSelector("[ng-show='serverSuccess']");
	By searchRole = By.cssSelector("[placeholder='Search']");
	By searchRoleList = By.xpath(".//*[contains(@ng-repeat,'roleSearch')]");
	By searchRoleEditIcon = By.xpath(".//*[contains(@ng-repeat,'roleSearch')]/span[3]/i");
	By loader = By.cssSelector("[ng-show='loader']");
	By accordion = By.cssSelector("[class='accordion']>div:nth-child(1)");
	By createButton = By.xpath(".//button[contains(text(),'Create')]");
	By failureAlert = By.cssSelector("[ng-show='serverErr']");
	
	String roleName = null;
	
	public void createNewRole(String roleType , String permissions , String roleProperty){
		
		try{
		 roleName = roleProperty+"_"+randomNumber();
		
		findElement(roleMgmtTile, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
		wait.until(ExpectedConditions.elementToBeClickable(accordion));
		findElement(By.xpath("(.//*[contains(text(),'"+roleType+"')])[1]"),"Role Type as "+roleType).click("Click on Role Type as "+roleType);
		
		findElement(roleNameTextBox,"Enter the Role Name").sendKeys(roleName, "Enter Role name");
		findElement(roleDescTextBox,"Enter Description").sendKeys("Automation Test Role", "Entering description");
		
		String[] permissionsArray=permissions.split(",");
		

		for(int iterator=0;iterator<permissionsArray.length;iterator++){
		
		switch(permissionsArray[iterator]){
		 
		case "1":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[1]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "2":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[2]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "3":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[3]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "4":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[4]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "5":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[5]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "6":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[6]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "7":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[7]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "8":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[8]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "9":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[9]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "10":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[10]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "11":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[11]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "12":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[12]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "13":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[13]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "14":
			driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[14]/div")).click();
			Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		}
		
		}
		
		
		findElement(createButton, "Create Button").click("Click on Create Button");
	
	}catch(Exception e){
		Log.error("Unable to Create New Role");
		throw(e);
		
	}
	}
	
	public void verifyCreateRoleSuccessMsg(String roleProperty){
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
		
		_assert.contains(findElement(successAlert, "Success Alert").getAttribute(Attributes.TEXT),"Success", "Verify Role Create Success Message");
		
		roleProp.setProperty(roleProperty, roleName);
		Log.info("Role Create is "+roleName);
		
		}catch(Exception e){
			Log.error("Unable to Verify Success Message");
			throw(e);
		}
		
	}
	
	public void verifyRolePermissions(String roleType,String permissions,String roleProperty){
		try{
		
		findElement(roleMgmtTile, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
		findElement(searchRole, "Search Role Text Box").sendKeys(roleProp.getProperty(roleProperty).toUpperCase(), "Enter Role that has to be Searched");
		wait.until(ExpectedConditions.visibilityOfElementLocated(accordion));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(searchRoleList, roleProp.getProperty(roleProperty).toUpperCase()));
		
		findElement(searchRoleEditIcon, "Edit Icon in Search").click("Click on Edit Icon");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(accordion));
		
		findElement(By.xpath("(.//*[contains(text(),'"+roleType+"')])[1]"),"Role Type as "+roleType).click("Click on Role Type as "+roleType);
		
		String[] permissionsArray=permissions.split(",");
		
		String classAttributeValue = "";

		for(int iterator=0;iterator<permissionsArray.length;iterator++){
		
		switch(permissionsArray[iterator]){
		 
		case "1":
			classAttributeValue = driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[1]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
			
		case "2":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[2]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "3":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[3]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "4":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[4]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "5":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[5]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "6":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[6]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "7":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[7]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "8":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[8]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "9":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[9]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "10":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[10]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "11":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[11]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "12":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[12]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "13":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[13]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		case "14":
			classAttributeValue =driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[14]/div")).getAttribute("class");
			_assert.equals(classAttributeValue,"task_box_checked", "Verify Permission for Role Type as "+roleType+ "with Permission index as "+iterator);
			break;
		}
		
		}
	}catch(Exception e){
		Log.error("Unable Verify Permissions for role "+ roleProp.getProperty(roleProperty));
		throw(e);
	}
	}
	
	public  void checkTileDisplayed(int expectedDisplayCount){
		try{
			
			int anchorCount = 	findElements(By.xpath(".//*[@class='row m-l-none']/a"),"Count of anchor tiles").getCount("Count of anchor Tiles");
			int divCount = findElements(By.xpath(".//*[@class='row m-l-none']/div[contains(@class,'menucolor')]"), "Count of Div Tiles").getCount("Count of div Tiles");
			
			Log.info("Anchor Count = "+anchorCount);
			Log.info("Div Count = "+divCount);
			
			int totalCountofTilesDisplayed = anchorCount + divCount;
			
			Log.info("Total Count of Tiles Displayed =" +totalCountofTilesDisplayed);
			
			for(int i =1;i<=anchorCount;i++){
				
				
				Log.info("Tile Displayed = "+ findElement(By.xpath(".//*[@class='row m-l-none']/a["+i+"]/div"),"").getAttribute(Attributes.TEXT));
			}
			for(int i =1;i<=divCount;i++){
				
				
				Log.info("Tile Displayed = "+ findElement(By.xpath(".//*[@class='row m-l-none']/div[contains(@class,'menucolor')]["+i+"]"),"").getAttribute(Attributes.TEXT));
			}
			
			_assert.equals(totalCountofTilesDisplayed, expectedDisplayCount, "Verify the Count if Displayed Tiles");
			
			
		}catch(Exception e){
			Log.error("Unable to Check if Tiles are Displayed");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void SelectAllRole(String roleType, String roleProperty) throws Exception {

		roleName = roleProperty + "_" + randomNumber();

		findElement(roleMgmtTile, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
		wait.until(ExpectedConditions.elementToBeClickable(accordion));
		

		findElement(roleNameTextBox, "Enter the Role Name").sendKeys(roleName, "Enter Role name");
		findElement(roleDescTextBox, "Enter Description").sendKeys("Automation Test Role", "Entering description");
		
		findElement(By.xpath("//div/h3/span[contains(text(),'"+roleType+"')][1]/../span[@id='header_']"),
				"Role Type as " + roleType).click("Click on Role Type  " + roleType + " without Expanding");
		
		findElement(createButton, "Create Button").click("Click on Create Button");
		
	}
	
	
	public void SelectAllRole_WithTree(String roleType, String roleProperty) throws Exception {

		roleName = roleProperty + "_" + randomNumber();

		findElement(roleMgmtTile, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
		wait.until(ExpectedConditions.elementToBeClickable(accordion));
		findElement(roleNameTextBox, "Enter the Role Name").sendKeys(roleName, "Enter Role name");
		findElement(roleDescTextBox, "Enter Description").sendKeys("Automation Test Role", "Entering description");
		findElement(By.xpath(".//div/h3/span[contains(text(),'"+roleType+"')]"),"Role Type as " + roleType).click("Click on"+roleType+" with Tree Expanding");
		findElement(By.xpath(".//div/h3/span[contains(text(),'"+roleType+"')][1]/../span[@id='header_']"),"Role Type as " + roleType).click("Click on Role Type  " + roleType + " with Tree Expanding");
		Thread.sleep(2000);
		findElement(createButton, "Create Button").click("Click on Create Button");

	}

	
	public void verifyDeleteRoleSuccessMsg(String roleProperty){
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
		
		_assert.contains(findElement(successAlert, "Success Alert").getAttribute(Attributes.TEXT),"Success", "Verify Role delete Success Message");
		
		roleProp.setProperty(roleProperty, roleName);
		Log.info("Role Create is "+roleName);
		
		}catch(Exception e){
			Log.error("Unable to Verify Success Message");
			throw(e);
		}
		
	}
	
	public void verifyDeleteRoleFailureMsg(String roleProperty){
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(failureAlert));
		
		_assert.contains(findElement(failureAlert, "Failure Alert").getAttribute(Attributes.TEXT),"Alert", "Verify Role delete alert Message is displayed");
		
		roleProp.setProperty(roleProperty, roleName);
		Log.info("Role Create is "+roleName);
		
		}catch(Exception e){
			Log.error("Unable to Verify failure Message");
			throw(e);
		}
		
	}
	public void tempUser(String roleProperty) throws InterruptedException {

		try {
			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class")
					.contains("selected")) {

				Thread.sleep(2500);
				driver.findElement(By.xpath(".//span[text()='Users']")).click();
				Thread.sleep(2000);

			}
			findElement(By.id("users_3"), "Create User").click("Click on Create User");

			Thread.sleep(2000);
			// WaitForFirefoxDOM();

			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down")
			.click("Click on Customer Drop Down");
	
			Thread.sleep(5000);

			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down")
			.click("Click on Customer Drop Down");
			Thread.sleep(2000);
			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");
			
			FluentWait<WebDriver> waitFluent = new FluentWait<WebDriver>(driver);
			waitFluent.pollingEvery(1, TimeUnit.SECONDS);
			waitFluent.withTimeout(300, TimeUnit.SECONDS);
			waitFluent.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(".//*[@ng-model='userDetails.customerId']//*[@ng-hide='searchTerm']"),
					"There are no items to display"));

			wait.until(ExpectedConditions.invisibilityOfElementWithText(
					By.xpath(".//*[@ng-model='userDetails.customerId']//*[@ng-hide='searchTerm']"),
					"There are no items to display"));
			Thread.sleep(2000);

			do {
				findElements(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li"), "Get Customer Count");

			} while (_WebElements.size() <= 1);
			
			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/div/input"),"Customer Drop Down Text Box").sendKeys("IoT", "Enter Customer Name");

			Thread.sleep(3000);
			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click on Customer Drop Down list");


			Thread.sleep(2000);

	

			findElement(By.id("firstName"), "First Name").sendKeys("tempUser", "Enter First Name");
			findElement(By.id("lName"), "Last Name").sendKeys("tempuser", "Enter Last Name");
			findElement(By.name("email"), "Email").sendKeys("auto_user_" + randomNumber() + "@test.com",
					"Enter Email Id");
			findElement(By.id("phoneNumber"), "Phone Number").sendKeys(prop.getProperty("phone"), "Enter Phone Number");

			//WaitForFirefoxDOM();

			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down")
					.click("Click on Role Drop Down");

			findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");

			
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys(roleProp.getProperty(roleProperty), "Enter Role Name");
			Thread.sleep(3000);
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");


		
				String newuser = "tempuser" + randomNumber();
				Log.info("Admin User Created : " + newuser);
				roleProp.setProperty("tempUser", newuser);

				findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
				findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
				findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"),
						"Enter Passowrd Agin");
		

			try {

				if (driver.findElement(By.id("iotSubSystem_3_combobox")).isDisplayed()) {

					new Select(driver.findElement(By.id("iotSubSystem_3_combobox"))).selectByValue("ROLE_DEVELOPER");

				}
			} catch (Exception e) {

				Log.info("Catching No Such Element Exception for Sub System");
			}

			findElement(By.cssSelector("[sysname='Partner Management']"), "Partner Mgmt Check Box").click("Uncheck Partner Mgmt");
			findElement(By.cssSelector("[sysname='Orbiwise Platform']"), "Orbiwise Platform").click("Uncheck Orbiwise Platform");
			
			findElement(By.xpath(".//input[@value='Create']"), "Create button").click("Click on Create Button");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//input[@value='Create']")));

		} catch (Exception e) {
			Log.error("Unable to Create New User");
			Log.error(e.toString());
			throw (e);
		}
	}
	
	public void newCreate_combinedRole(String roleType , String permissions , String roleType1 , String permissions1, String roleProperty) throws Exception{
		try{
			 roleName = roleProperty+"_"+randomNumber();
			
			findElement(roleMgmtTile, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(accordion));
			
			findElement(roleNameTextBox,"Enter the Role Name").sendKeys(roleName, "Enter Role name");
			findElement(roleDescTextBox,"Enter Description").sendKeys("Automation Test Role", "Entering description");
			
			findElement(By.xpath("(.//*[contains(text(),'"+roleType+"')])[1]"),"Role Type as "+roleType).click("Click on Role Type as "+roleType);
			String[] permissionsArray=permissions.split(",");
			
			for(int iterator=0;iterator<permissionsArray.length;iterator++){
			
			switch(permissionsArray[iterator]){
			 
			case "1":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[1]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "2":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[2]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "3":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[3]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "4":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[4]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "5":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[5]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "6":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[6]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "7":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[7]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "8":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[8]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "9":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[9]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "10":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[10]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "11":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[11]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "12":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[12]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "13":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[13]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			case "14":
				driver.findElement(By.xpath(".//*[contains(text(),'"+roleType+"')]/../div/ul/li[14]/div")).click();
				Log.info("Permission added for Role Type as "+roleType+ "with Permission index as "+iterator);
				break;
			}
			
			}
			
			findElement(By.xpath("(.//*[contains(text(),'"+roleType1+"')])[1]"),"Role Type as "+roleType1).click("Click on Role Type as "+roleType1);
			String[] permissionsArray1=permissions1.split(",");
			
			for(int iterator=0;iterator<permissionsArray1.length;iterator++){
				
				switch(permissionsArray1[iterator]){
				 
				case "1":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[1]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "2":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[2]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "3":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[3]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "4":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[4]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "5":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[5]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "6":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[6]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "7":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[7]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "8":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[8]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "9":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[9]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "10":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[10]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "11":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[11]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "12":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[12]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "13":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[13]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				case "14":
					driver.findElement(By.xpath(".//*[contains(text(),'"+roleType1+"')]/../div/ul/li[14]/div")).click();
					Log.info("Permission added for Role Type as "+roleType1+ "with Permission index as "+iterator);
					break;
				}
				
				}
			findElement(createButton, "Create Button").click("Click on Create Button");
		
		}catch(Exception e){
			Log.error("Unable to Create New Role");
			throw(e);
			
		}
	}
	
	public void editcreatedRole(String roleProperty){
		try{
		
		findElement(roleMgmtTile, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
		findElement(searchRole, "Search Role Text Box").sendKeys(roleProp.getProperty(roleProperty).toUpperCase(), "Enter Role that has to be Searched");
		wait.until(ExpectedConditions.visibilityOfElementLocated(accordion));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(searchRoleList, roleProp.getProperty(roleProperty).toUpperCase()));
		
		findElement(searchRoleEditIcon, "Edit Icon in Search").click("Click on Edit Icon");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='currentPage']")));
 		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Manage Role", "Verifying page title");
		
		}catch(Exception e){
			Log.error("Unable to open edit Role page");
			throw(e);
			
		}
	}
}
