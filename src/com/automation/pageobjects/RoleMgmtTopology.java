package com.automation.pageobjects;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hpe.base.Attributes;
import com.hpe.iot.api.base.Base_API;
import com.hpe.webdriver.ElementBase;
public class RoleMgmtTopology extends ElementBase {

	final static Logger Log = Logger.getLogger(RoleMgmtTopology.class.getName());

	String task_selected="task_box_checked";
	String task_notselected ="task_check_box";



	By role_name = By.xpath(".//*[@id='roleName']");
	By role_description = By.xpath(".//*[@id='roleDesc']");
	By createButton = By.xpath(".//button[contains(text(),'Create')]");
	By search_role = By.xpath(".//input[@ng-model='roleSearch.roleName']");
	By first_searchlist = By.xpath(".//div[@class='p-l-md ']/ul/li[1]");
	By first_searchlistRoledelete= By.xpath(".//div[@class='p-l-md ']/ul/li[1]/span/following::span[1]/i[@class='fa fa-trash role_delete']");
	By first_searchlistRolededit= By.xpath(".//div[@class='p-l-md ']/ul/li[1]/span/following::span[2]/i[@class='fa fa-pencil role_delete']");
	By role_mgmt = By.xpath(".//*[text()='Role Management']");
	By role_display = By.xpath(".//div/div[@class='row m-n']/div");
	By topology_tree = By.xpath(".//*[@id='roleContainer']/div/div[7]/div/h3");
	By select_all_permission_checkicon = By.xpath(".//*[@id='roleContainer']/div/div[7]/div/h3/span[1]/following::span[1]");
	By view_topology = By.xpath(".//*[@id='roleContainer']/div/div[7]/div/div/ul/li/span/following::div[1]");
	By accordion = By.cssSelector("[class='accordion']>div:nth-child(6)");
	By searchRoleList = By.xpath(".//*[contains(@ng-repeat,'roleSearch')]");
	By successAlert = By.cssSelector("[ng-show='serverSuccess']");
	By roleNameTextBox=By.id("roleName");
	By roleDescTextBox=By.id("roleDesc");
	By acp_dashboard = By.xpath(".//*[text()='Access Control Policy']");
	public final String u="Users";
	public final String q="QoS Policy";
	public final String r="Role Management";
	public final String cm="Customer Mgmt.";
	public final String atp="Asset Topology";
	public final String asm="Asset Mgmt.";
	public final String apm="Address Pool Mgmt.";
	public final String am="Application Mgmt.";
	public final String rr="Routing Rule";
	public final String dmp="Device Manufacturer Profile";
	public final String acp="Access Control Policy";
	public final String cp="Container Profile";
	public final String dc="Device Controllers";
	public final String sd="Security Dashboard";
	public final String ua="User Activity";	
	
	String[] componentDriver= new String[] {"Users","QoS Policy","Role Management","Customer Mgmt.","Asset Topology","Asset Mgmt.","Address Pool Mgmt.","Application Mgmt.","Routing Rule","Device Manufacturer Profile","Access Control Policy","Container Profile","Device Controllers","Security Dashboard","User Activity"};

	public String CheckVibilityofComponent() {
		String result="";
		for (int i=0 ; i<=componentDriver.length-1; i++) {
			String xpathKeyword = componentDriver[i];
			String xpathExpression=".//span[text()='"+xpathKeyword+"']";
			try {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				if (driver.findElement(By.xpath(xpathExpression)).isDisplayed()) {

					result= xpathKeyword;
					Log.info("###########The Module\t"+result+"\tis found###########" );
				}

			}catch(Exception e) {

				Log.info(componentDriver[i]+"\t module is not Displayed");	
			}
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return result;
	}

	public void  RemoveRoleVerify_withUser(String roleProperty) throws Exception {	
		try {
			findElement(role_mgmt, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
			findElement(search_role, "Search Role Text Box").sendKeys(roleProp.getProperty(roleProperty), "Enter Role that has to be Searched");
			wait.until(ExpectedConditions.visibilityOfElementLocated(accordion));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(searchRoleList, roleProp.getProperty(roleProperty).toUpperCase()));
			findElement(first_searchlistRoledelete, "Delete Icon in Search").click("Click on Delete Icon");
			Thread.sleep(1000);
			alertAccept("Accept Remove Role Alert");			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[ng-show='serverErr']")));
			String msg_alert = findElement(By.cssSelector("[ng-show='serverErr']"),"Alert Message").getAttribute(Attributes.TEXT);
			_assert.contains(msg_alert, "Alert", "Remove Role Not possible as user is associated");

		}catch(Exception e){
			Log.error("Unable to Check if Tiles are Displayed");
			Log.error(e.toString());
			throw(e);
		}


	}

	public void  RemoveRoleVerify_withoutUser(String roleProperty) {	
		try {
			findElement(role_mgmt, "Role Mgmt Tile Description").click("Click on Role Mgmt Tile");
			findElement(search_role, "Search Role Text Box").sendKeys(roleProp.getProperty(roleProperty), "Enter Role that has to be Searched");
			wait.until(ExpectedConditions.visibilityOfElementLocated(accordion));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(searchRoleList, roleProp.getProperty(roleProperty).toUpperCase()));
			findElement(first_searchlistRoledelete, "Delete Icon in Search").click("Click on Delete Icon");
			alertAccept("Accept Remove Role Alert");			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[ng-show='serverSuccess']")));
			String msg_success = findElement(successAlert,"Success Message").getAttribute(Attributes.TEXT);
			_assert.contains(msg_success, "Success", "Remove Role  possible as user is not associated");

		}catch(Exception e){
			Log.error("Unable to Check if Tiles are Displayed");
			Log.error(e.toString());
			throw(e);
		}


	}
	public void CreateACP_RoleManagement() throws Exception {

		try {
			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"Acp Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				driver.findElement(By.xpath(".//span[text()='Access Control Policy']")).click();
			}
			findElement(By.id("acp_1"), "Create ACP").click("Click on Create ACP");

			String acp = prop.getProperty("acp_name") + randomNumber();

			prop.setProperty("acp_name", acp);

			findElement(By.id("acpName"), "Acp Name").sendKeys(acp, "Enter Acp Name");
			findElement(By.id("acpDescription"), "Acp description").sendKeys(prop.getProperty("acp_desc"),
					"Enter Acp description");
			
			

			//findElement(By.id("acpSelect"), "Acp Select").click("Click on Acp Select");

			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));

			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li[@id='" + Base_API.nodelink + "']/span")));
			//Thread.sleep(2000);
			//findElement(By.xpath(/*".//li[@id='" + Base_API.nodelink + "']/span"*/".//span[@class='seleDev group_sel_1000 active']"), "Device List").click("Click on Device");
			//Thread.sleep(2000);
			//findElement(By.xpath(".//*[@ng-model='$root.acpLogicalResource']"), "Containers").selectByIndex(1,"Select Container");
			Thread.sleep(2000);
			//findElement(By.id("acpLocationSelect"), "Container Type").selectByIndex(0, "Select Container Type");
			//findElement(By.xpath(".//button[@ng-click='subscribe()']"), "Done").click("Click on Done");

			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[1]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[2]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[3]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[4]/label/input")).click();
			findElement(By.xpath(".//button[text()='Create']"), "Create Button").click("Click on Create");

			Log.info("ACP Created : " + acp);
		} catch (Exception e) {
			Log.error("Unable to Create ACP " + e.toString());
			throw (e);
		}

	}
	
	
	public void CreateACP_RoleManagement_Options(String Permissions) throws Exception {

		try {
			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"Acp Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				driver.findElement(By.xpath(".//span[text()='Access Control Policy']")).click();
			}
			findElement(By.id("acp_1"), "Create ACP").click("Click on Create ACP");

			String acp = prop.getProperty("acp_name") + randomNumber();

			prop.setProperty("acp_name", acp);

			findElement(By.id("acpName"), "Acp Name").sendKeys(acp, "Enter Acp Name");
			
			findElement(By.id("acpDescription"), "Acp description").sendKeys(prop.getProperty("acp_desc"),
					"Enter Acp description");
			if(Permissions.equals("12")) {
			findElement(By.xpath(".//div[2]/div[3]/div/section/div/form/div[7]/div/select"), "Assign To Customer").selectByIndex(1, "Select Customer");
			}
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[1]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[2]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[3]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[4]/label/input")).click();
			findElement(By.xpath(".//button[text()='Create']"), "Create Button").click("Click on Create");

			Log.info("ACP Created : " + acp);
		} catch (Exception e) {
			Log.error("Unable to Create ACP " + e.toString());
			throw (e);
		}

	}
	
	public void Search_ACP_Rule_Alone() throws Exception{
		try{
		
	 if(!driver.findElement(By.xpath("(.//*[text()='Access Control Policy'])/..")).getAttribute("class").contains("selected")){
	    Thread.sleep(2500);
		findElement(acp_dashboard,"ACP Dashboard").click("Click on ACP");
		Thread.sleep(2500);
		}
	 
		if(findElement(By.id("acp_3"), "Search ACP").isDisplayed())
		{
			findElement(By.id("acp_3"), "Search ACP").click("Clicking Search ACP");
			Thread.sleep(2000);
			findElement(By.xpath(".//i[@class='icon icon-search']"), "Search ACP").isDisplayed();
			Log.info("Serach ACP rule is present");
		}
		
		}catch(Exception e){
			
			Log.error("Search ACP not found");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	
	public void Add_ACP_Rule_Alone() throws Exception{
		try{
		
	 if(!driver.findElement(By.xpath("(.//*[text()='Access Control Policy'])/..")).getAttribute("class").contains("selected")){
	    Thread.sleep(2500);
		findElement(acp_dashboard,"ACP Dashboard").click("Click on ACP");
		Thread.sleep(2500);
		}
	 
		if(findElement(By.id("acp_1"), "Add ACP").isDisplayed())
		{
			findElement(By.id("acp_1"), "Add ACP page opening").click("Clicking Add ACP");
			Thread.sleep(2000);
			findElement(By.id("acpName"), "Search ACP").isDisplayed();
			Log.info("Add ACP rule is present");
		}
		
		}catch(Exception e){
			
			Log.error("Add ACP not found");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}
	public void tempUser() throws InterruptedException {

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

			
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
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

		
		
}
