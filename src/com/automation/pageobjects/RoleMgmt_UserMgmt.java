package com.automation.pageobjects;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class RoleMgmt_UserMgmt extends ElementBase{
	final static Logger Log = Logger.getLogger(RoleMgmt_UserMgmt.class.getName());
	By UserTile = By.xpath(".//span[text()='Users']");
	By SearchUser = By.xpath("//*[@id = \"users_2\"]");
	By FirstUser = By.xpath(".//*[@st-table='userList']/tbody/tr[1]/td[2]/a");
	By editButton = By.xpath(".//*[@st-table='userList']/tbody/tr[1]/td[9]/a/i");
	By delButton = By.xpath(".//*[@st-table='userList']/tbody/tr[1]/td[10]");
	By editFirstName = By.xpath("//*[@id = \"firstName\"]");
	By verifyingUpdateUser = By.xpath(".//*[@bcmodulename='User Management' and contains(text(),'Update User')]");
	By verifyingUserInfo = By.xpath(".//*[@bcmodulename='User Management' and contains(text(),'User Details')]");
	By updateButton = By.xpath("//*[@value = \"Update\"]");
	By theamMang = By.xpath("//*[@id = \"users_8\"]");
	By addNewThem = By.xpath("//*[@class = \"btn grommetux-button-primary\"]");
	By addingthemName = By.xpath("//*[@id= \"userName\" and contains(@placeholder,\"ex.Theme Name\")]");
	By createtheamButton = By.xpath("//*[@class= \"btn grommetux-button-primary\" and contains(text(),\"Create\")]");
	By CustMangTile = By.xpath(".//span[text()='Customer Mgmt.']");
	By SearchCust = By.xpath(".//*[@id='cust_2' and contains(text(),\"Search Customer\")]");
	By GroupName = By.xpath(".//*[@st-table='entityList']/tbody/tr[1]/td[1]/a");
	By GroupEdit = By.xpath(".//*[@st-table='entityList']/tbody/tr[1]/td[4]/a");
	By GroupVerification = By.xpath(".//*[@class='pageHeader' and contains(text(),'Group Details')]");
	By EditUpdateGroupVerification = By.xpath(".//*[@class='header-title' and contains(text(),'Update Customer/Group')]");
	By EditGroupName = By.xpath(".//*[@ng-model='groupDetails.groupName']");
	By GroupUpdateButton = By.xpath(".//*[@ng-click='submitted=true' and contains(text(),\"Update\")]");
	By DeleteUser= By.xpath(".//*[@st-table='userList']/tbody/tr/td[10]/div/a/i");
	By DeleteGroup = By.xpath(".//*[@st-table='entityList']/tbody/tr[1]/td[6]/span/i");
	By customer_mgmt_dash = By.xpath(".//*[text()='Customer Mgmt.']");
	By create_customer = By.id("cust_1");
	By customer_radio = By.xpath(".//input[@value='CUSTOMER'][@type='radio']");
	By group_radio = By.xpath(".//input[@value='GROUP'][@type='radio']");
	By customer_name = By.name("sGroupName");
	String Cust_Name = "IOT";
	String Fist_name = "Auto_customer_usermanagement";
	String roleName = null;
	
	
public void alertDelete(String description){
	try{
	//wait.until(ExpectedConditions.alertIsPresent());
	String alertText = driver.switchTo().alert().getText();
	Log.info(alertText);
	driver.switchTo().alert().accept();
	Log.info("Alert :"+alertText+" : Accepted");
	}catch(Exception e){
		
		Log.error("Unable to Perform action on Alert "+ description);
		throw(e);
	}
	
}



public void ViewUserVerification(String userinfoTitel) throws InterruptedException {
	try {
		if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(UserTile,"User Tile ").click("clicking on User Tile");
			Thread.sleep(2000);
			findElement(SearchUser,"Search User").click("clicking on Search User");
			Thread.sleep(2000);
			findElement(SearchUser,"Search User").click("clicking on Search User");
			Thread.sleep(2000);

			findElement(FirstUser,"First User").click("clicking on first user");
			wait.until(ExpectedConditions.visibilityOfElementLocated(verifyingUserInfo));
			
			String pageName = findElement(verifyingUserInfo, "Page Title").getAttribute(Attributes.TEXT);
			Log.info(pageName);
			
			_assert.equals(pageName, userinfoTitel, "Verifying page title");
			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}
	}

public void ViewGroupVerification(String userinfoTitel) throws InterruptedException {
	try {
		if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(CustMangTile,"Customer Management Tile ").click("clicking on Customer Mangagment Tile");
			Thread.sleep(2000);
			findElement(SearchCust,"Search Group").click("clicking on Search Customer/group");
			Thread.sleep(2000);
			findElement(SearchCust,"Search Group").click("clicking on Search Customer/group");
			Thread.sleep(2000);
			findElement(GroupName,"First Group Name").click("clicking on first Group Name");
			wait.until(ExpectedConditions.visibilityOfElementLocated(GroupVerification));
			
			String pageName = findElement(GroupVerification, "Page Title").getAttribute(Attributes.TEXT);
			Log.info(pageName);
			
			_assert.equals(pageName, userinfoTitel, "Verifying page title");
			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}
	}

public void updateGroupVerification(String userinfoTitel) throws InterruptedException {
	try {
		if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(CustMangTile,"Customer Management Tile ").click("clicking on Customer Mangagment Tile");
			Thread.sleep(2000);
			findElement(SearchCust,"Search Group").click("clicking on Search Customer/group");
			Thread.sleep(2000);
			findElement(SearchCust,"Search Group").click("clicking on Search Customer/group");
			Thread.sleep(2000);
			findElement(GroupEdit,"edit Group").click("clicking on first Group Name edit button");
			wait.until(ExpectedConditions.visibilityOfElementLocated(EditUpdateGroupVerification));
			
			String pageName = findElement(EditUpdateGroupVerification, "Page Title").getAttribute(Attributes.TEXT);
			Log.info(pageName);
			
			_assert.equals(pageName, userinfoTitel, "Verifying page title");Thread.sleep(1500);
			findElement(EditGroupName,"customer name edit box").sendKeys("New_Customer01", "Editing customer name to New_customer01");
			findElement(GroupUpdateButton,"clicking update button").click("clicking update button for group update");
			
			
			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}
	}

public void deleteGroupVerification() throws InterruptedException {
	try {
		if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(CustMangTile,"Customer Management Tile ").click("clicking on Customer Mangagment Tile");
			Thread.sleep(2000);
			findElement(SearchCust,"Search Group").click("clicking on Search Customer/group");
			Thread.sleep(2000);
			findElement(SearchCust,"Search Group").click("clicking on Search Customer/group");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@data-toggle=\"tab\"and contains(text(),\"Group\")]"), "Group Tab").click("Click on Group Tab");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@name=\"groupname\"]"), "Group Name").sendKeys(prop.getProperty("group_name_role"), "Group Name");
			findElement(By.xpath(".//*[@class=\"dropdown-toggle ng-scope\" ]/span"), "customer List").click("Customer List");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class=\"custom-select-search\" ]/input[1]"), "enter customer name").sendKeys("IOT", "Providing IOT");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@role=\"menu\" ]/li[2]"), "clicking IOT fromList").click("selecting iot from List");
			Thread.sleep(2000);
			findElement(By.xpath(".//input[@type=\"submit\"]"), "clicking submit button").click("clicking submit button");
			Thread.sleep(3000);
			findElement(DeleteGroup,"delete Group Button").click("clicking on delete Button with the first Group name");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@id = 'removeGroup' and contains(text(),'OK')]"), "clicking delete Ok Button").click("Clicking on Delete OK Button Notification");
			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}

	}



public void editUserVerification(String userupdateTitel,String userFirstName) throws InterruptedException {
	try {
		if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(UserTile,"User Tile ").click("clicking on User Tile");
			Thread.sleep(2000);
			findElement(SearchUser,"Search User").click("clicking on Search User");
			Thread.sleep(2000);
			findElement(SearchUser,"Search Group").click("clicking on Search Customer/group");
			Thread.sleep(2000);
			findElement(editButton,"Edit Button").click("clicking on Edit Button");
			wait.until(ExpectedConditions.visibilityOfElementLocated(verifyingUpdateUser));
			
			String pageName = findElement(verifyingUpdateUser, "Page Title").getAttribute(Attributes.TEXT);
			Log.info(pageName);
			
			_assert.equals(pageName, userupdateTitel, "Verifying page title");
			findElement(editFirstName,"Edit User Name").sendKeys(userFirstName, "User First name");
			findElement(updateButton,"Update Button").click("clicking on update button");

			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}

	}
public void deleteUserVerification() throws InterruptedException {
	try {
		if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			driver.findElement(By.xpath(".//span[text()='Users']")).click();
			Thread.sleep(2000);
			findElement(SearchUser,"Search User").click("clicking on Search User");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			Thread.sleep(2000);
			findElement(By.name("username"), "user Name").sendKeys(prop.getProperty("cust_User_rolemag"), "Enter user Name");
			findElement(By.xpath(".//*[@value='Search']"), "Search button").click("Click on Search Button");
			Thread.sleep(2000);
			findElement(DeleteUser,"delete Group Button").click("clicking on delete Button ");
			
			findElement(By.xpath(".//*[@data-dismiss= 'modal' and contains(text(),'Delete')]"), "clicking delete Delete Button").click("Clicking on Delete Button Notification");

			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}

	}

public void theamMagVerification() throws InterruptedException {
	try {
		
		String themeName = "newTheme" + randomNumber();
		if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(UserTile,"User Tile ").click("clicking on User Tile");
			Thread.sleep(2000);
			findElement(theamMang,"Theam Mgmt").click("clicking on Theam Mgmt");
			Thread.sleep(2000);
			findElement(theamMang,"Theam Mgmt").click("clicking on Theam Mgmt");
			Thread.sleep(2000);
			findElement(theamMang,"Theam Mgmt").click("clicking on Theam Mgmt");
			findElement(addNewThem,"Add New theme Button").click("clicking on Adding new theme Button");
			findElement(addingthemName,"Adding new theme Name").sendKeys(themeName, "Theame Name");
			findElement(createtheamButton, "clicking create button").click("Clicking on create button");

			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}

	}

public void Create_Customer() throws InterruptedException{
	
	if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
	findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
	}
	findElement(create_customer, "Create Customer").click("Click on Create Customer");
	
	String Grop_Name = "Grop_Automation_"+randomNumber();
	
	findElement(group_radio, "Customer Radio Button").click("Select Customer Radio Button");
	
	findElement(customer_name, "Customer Name").sendKeys(Grop_Name, "Enter Customer Name");
	
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



}



public void CreateGrop_roleMag() throws Exception {

	try {
		if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			driver.findElement(By.xpath(".//span[text()='Customer Mgmt.']")).click();
			Thread.sleep(2000);

		}
		Thread.sleep(2000);
		findElement(By.id("cust_1"), "Create Group").click("Click on Create Group");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='GROUP']")));
		findElement(By.xpath(".//input[@value='GROUP']"), "Group Radio Button").click("Click Group Radio Button");

		String Group_name = prop.getProperty("group_name") + randomNumber();

		prop.setProperty("group_name_role", Group_name);

		findElement(By.xpath(".//input[@name='sGroupName']"), "Group Name").sendKeys(prop.getProperty("group_name_role"), "Enter Group Name");

		Thread.sleep(2000);

		findElement(By.xpath(".//div[contains(@ng-model,'parentId')]"), "Customer Drop Down")
				.click("Click on Customer Drop Down");
		findElements(By.xpath(".//div[contains(@ng-model,'parentId')]/div/ul/li"), "Get Customer Count");

		
		findElement(By.xpath(".//div[contains(@ng-model,'parentId')]/div/div/input"), "Customer Drop Down Text Box").sendKeys("IOT"/*CustomerManagement.Cust_Name.toUpperCase()*/, "Enter Customer Name");
		Thread.sleep(3000);
		findElement(By.xpath(".//div[contains(@ng-model,'parentId')]/div/ul/li[2]"), "Customer Drop Down List").click("Click on Customer Drop Down List");

		Thread.sleep(2000);
		findElement(By.name("sdescription"), "Descrption").sendKeys(prop.getProperty("tenant_desc"),
				"Enter Description");

		findElement(By.xpath(".//button[text()='Create']"), "Create Button").submit("Click on Create Button");

		Thread.sleep(3000);
		Log.info("Group Create : " + Group_name);
	} catch (Exception e) {

		Log.error("Unable to create group");
		Log.error(e.toString());
	}
}


public void Createuser_underiot_Customer() throws InterruptedException {

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
		findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down")
		.click("Click on Customer Drop Down");
		
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
		
		findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/div/input"),"Customer Drop Down Text Box").sendKeys(Cust_Name.toUpperCase(), "Enter Customer Name");

		Thread.sleep(3000);
		findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click on Customer Drop Down list");

		Thread.sleep(2000);
		findElement(By.id("firstName"), "First Name").sendKeys(prop.getProperty("first_name"), "Enter First Name");
		findElement(By.id("lName"), "Last Name").sendKeys(prop.getProperty("last_name"), "Enter Last Name");
		findElement(By.name("email"), "Email").sendKeys("auto_user_" + randomNumber() + "@test.com",
				"Enter Email Id");
		findElement(By.id("phoneNumber"), "Phone Number").sendKeys(prop.getProperty("phone"), "Enter Phone Number");

		WaitForFirefoxDOM();

		findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down")
				.click("Click on Role Drop Down");

		findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");

		findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
		Thread.sleep(3000);
		findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
		String newuser = "autocustuser" + randomNumber();
		Log.info("Customer User Created : " + newuser);
		prop.setProperty("cust_User_rolemag", newuser.toLowerCase());

		findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
		findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
		findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"),
				"Confirm Password");
		findElement(By.xpath(".//input[@value='Create']"), "Create button").click("Click on Create Button");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//input[@value='Create']")));

	} 
	catch (Exception e) {
		Log.error("Unable to Create New User");
		Log.error(e.toString());
		throw (e);
	}
 }



public void WaitForFirefoxDOM() throws InterruptedException {
	
		Thread.sleep(3000);
	}
	
}
