package com.automation.pageobjects;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.hpe.base.Attributes;
import com.hpe.iot.api.base.Base_API;
import com.hpe.utilities.CSVUtils;
import com.hpe.webdriver.ElementBase;

public class SmokeTest extends ElementBase {

	static String result = null;

	public static String CustID = "";
	public static String Group = "";
	public static String asset = "";
	public static ArrayList<String> assetList = new ArrayList<String>();

	// String tenant = "";

	final static Logger Log = Logger.getLogger(SmokeTest.class.getName());

	public static String compare(String actual, String expected) {

		result = "Pass";

		try {
			Assert.assertTrue(actual.contains(expected));
		} catch (AssertionError e) {
			result = "Fail";
		}
		return result;
	}

	public void selectMenu(int Row, int Colm) throws Exception {

		int count = driver.findElements(By.cssSelector(".nav-label")).size();

		for (int i = 1; i <= count; i++) {

			if (i != 2) {

				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(".//*[@class='row m-l-none']/div[" + i + "]/span")));

				String text = driver.findElement(By.xpath(".//*[@class='row m-l-none']/div[" + i + "]/span")).getText();

				if (text.equals(com.hpe.utilities.ExcelLib.getCellData(Row, Colm))) {

					driver.findElement(By.xpath(".//*[@class='row m-l-none']/div[" + i + "]/span")).click();

					Thread.sleep(2000);

					break;
				}
			}

		}

	}

	public void EnterGroupValues() throws Exception {

		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath(".//span[text()='Customer Mgmt.']"),"Customer Mgmt Tile").click("Click on Customer Mgmt");
				Thread.sleep(2000);

			}
			Thread.sleep(2000);
			findElement(By.id("cust_1"), "Create Group").click("Click on Create Group");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='GROUP']")));
			findElement(By.xpath(".//input[@value='GROUP']"), "Group Radio Button").click("Click Group Radio Button");

			Group = prop.getProperty("group_name") + randomNumber();

			prop.setProperty("group_name", Group);

			findElement(By.xpath(".//input[@name='sGroupName']"), "Group Name").sendKeys(Group, "Enter Group Name");

			Thread.sleep(2000);

			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]"), "Customer Drop Down")
					.click("Click on Customer Drop Down");
			findElements(By.xpath(".//div[contains(@ng-model,'parentId')]/div/ul/li"), "Get Customer Count");

			
			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]/div/div/input"), "Customer Drop Down Text Box").sendKeys(CustomerManagement.Cust_Name.toUpperCase(), "Enter Customer Name");
			Thread.sleep(3000);
			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]/div/ul/li[2]"), "Customer Drop Down List").click("Click on Customer Drop Down List");
			/*for (int i = 1; i <= _WebElements.size(); i++) {

				wait.until(ExpectedConditions.elementToBeClickable(_WebElements.get(i)));
				String actual = _WebElements.get(i).getText();

				if (actual.trim().contains(CustomerManagement.Cust_Name.toUpperCase())) {

					_WebElements.get(i).click();

					break;
				}

			}*/

			Thread.sleep(2000);

			findElement(By.name("sdescription"), "Descrption").sendKeys(prop.getProperty("tenant_desc"),
					"Enter Description");

			findElement(By.xpath(".//button[text()='Create']"), "Create Button").submit("Click on Create Button");

			Thread.sleep(3000);
			Log.info("Group Create : " + Group);
		} catch (Exception e) {

			Log.error("Unable to create group");
			Log.error(e.toString());
		}
	}

	public void VerifyTenant() throws Exception {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
		String actual = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
				.getAttribute(Attributes.TEXT);

		result = compare(actual, prop.getProperty("group_name"));

	}

	public void EnterUserValues(boolean CustomerUser) throws InterruptedException {

		try {
			if (!findElement(By.xpath("(.//span[text()='Users'])/.."),"User Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath(".//span[text()='Users']"),"Users Tile").click("Click on Users Tile");
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
			
			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/div/input"),"Customer Drop Down Text Box").sendKeys(CustomerManagement.Cust_Name.toUpperCase(), "Enter Customer Name");

			Thread.sleep(3000);
			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click on Customer Drop Down list");
			/*for (int i = 1; i <= _WebElements.size(); i++) {

				wait.until(ExpectedConditions.elementToBeClickable(_WebElements.get(i)));

				String actual = _WebElements.get(i).getText();

				if (actual.trim().contains(CustomerManagement.Cust_Name.toUpperCase())) {

					_WebElements.get(i).click();

					break;
				}

			}*/

			Thread.sleep(2000);

			if (!CustomerUser) {
				wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(".//div[@ng-model='userDetails.groupId']/a")));
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//div[@ng-model='userDetails.groupId']/a")));
				findElement(By.xpath(".//div[@ng-model='userDetails.groupId']/a"), "Group Drop Down")
						.click("Click on Group Drop Down");
				do {
					findElements(By.xpath(".//div[contains(@ng-model,'groupId')]/div/ul/li"), "Get Group Count");
				} while (_WebElements.size() <= 1);

				findElement(By.xpath(".//*[@ng-model='userDetails.groupId']/div/div/input"),"Group Drop Down Text Box").sendKeys(SmokeTest.Group.toUpperCase(), "Enter Customer Name");

				Thread.sleep(3000);
				wait.until(ExpectedConditions.invisibilityOfElementWithText(
						By.xpath(".//*[@ng-model='userDetails.groupId']//*[@ng-hide='searchTerm']"),
						"There are no items to display"));
				findElement(By.xpath(".//*[@ng-model='userDetails.groupId']/div/ul/li[2]"),"Group Drop Down List").click("Click on Group Drop Down list");
				
			/*	for (int i = 1; i <= _WebElements.size(); i++) {

					wait.until(ExpectedConditions.elementToBeClickable(_WebElements.get(i)));

					String actual = _WebElements.get(i).getText();

					if (actual.trim().contains(SmokeTest.Group.toUpperCase())) {

						_WebElements.get(i).click();

						break;
					}

				}*/
			}

			findElement(By.id("firstName"), "First Name").sendKeys(prop.getProperty("first_name"), "Enter First Name");
			findElement(By.id("lName"), "Last Name").sendKeys(prop.getProperty("last_name"), "Enter Last Name");
			findElement(By.name("email"), "Email").sendKeys("auto_user_" + randomNumber() + "@test.com",
					"Enter Email Id");
			findElement(By.id("phoneNumber"), "Phone Number").sendKeys(prop.getProperty("phone"), "Enter Phone Number");

			WaitForFirefoxDOM();

			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down")
					.click("Click on Role Drop Down");

			findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), 2));
			
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
			Thread.sleep(3000);
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
			/*for (int i = 1; i <= _WebElements.size(); i++) {

				wait.until(ExpectedConditions.elementToBeClickable(_WebElements.get(i)));

				String actual = _WebElements.get(i).getText();

				if (actual.trim().contains("DSM_ADMIN")) {

					_WebElements.get(i).click();

					break;
				}

			}*/

			if (!CustomerUser) {
				String newuser = "autogroup"+ randomNumber();
				Log.info("Group User Created : " + newuser);
				prop.setProperty("new_user_name", newuser);

				findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
				findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
				findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"),
						"Enter Passowrd Agin");
			}

			else {

				String newuser = "autocustuser" + randomNumber();
				Log.info("Customer User Created : " + newuser);
				prop.setProperty("cust_username", newuser.toLowerCase());

				findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
				findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
				findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"),
						"Confirm Password");
			}

			try {

				if (driver.findElement(By.id("iotSubSystem_3_combobox")).isDisplayed()) {

					new Select(driver.findElement(By.id("iotSubSystem_3_combobox"))).selectByValue("ROLE_DEVELOPER");

				}
			} catch (Exception e) {

				Log.info("Catching No Such Element Exception for Sub System");
			}

			WaitForFirefoxDOM();
			System.out.println();
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

	public void VerifyNewUser() throws Exception {

		try {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);

			result = compare(actual, prop.getProperty("new_user_name").toLowerCase());

			if (!driver.findElement(By.xpath(".//*[@messages='statusMessages']/div/div[1]")).getAttribute("class").contains("ng-hide")) {

				String error = driver.findElement(By.xpath(".//*[@class='alert alert-danger']/span")).getText();

				if (error.contains("Ldap")) {

					Log.error("Unable to Create User : LDAP Error");
					// Assert.fail();
				}
			}
		} catch (Exception e) {

			Log.error("Unable to Verify User");
			Log.error(e.toString());
			throw (e);
		}

	}

	public void VerifyDSMAdminRole() throws Exception {

		findElement(By.name("searchAction"), "Search Drop Down").selectByValue("user", "Select User");
		findElement(By.name("searchText"), "Search Box").sendKeys(prop.getProperty("new_user_name").toLowerCase(),
				Keys.ENTER, "Enter User Name");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("new_user_name"))));

		findElement(By.linkText(prop.getProperty("new_user_name")), "User Link").click("Click on User Link");

		Thread.sleep(3000);
		// WaitForFirefoxDOM();
		String actual = findElement(By.cssSelector(".table-header>div:nth-child(1)"), "Header")
				.getAttribute(Attributes.TEXT);

		result = compare(actual, "DSM_ADMIN");
	}

	public void ContainerUpload(String containerProfileName) throws Exception {

		findElement(By.xpath(".//span[text()='Container Profile']"), "Container Profile Tile")
				.click("Click on Container Profile");

		Thread.sleep(2000);
		findElement(By.id("containerP_1"), "Create Container").click("Click on Create ");

		Thread.sleep(2000);

		findElement(By.xpath(".//*[@type='file']"), "Upload")
				.sendKeys(System.getProperty("user.dir") + "\\testdata\\"+containerProfileName, "Upload File");

		Thread.sleep(3000);

		findElement(By.xpath(".//*[text()='Submit']"), "Create Button").click("Click on Create Button");

	}

	public void DeviceMgmtUpload(String fileName, boolean cpAttach,String... cpName) throws Exception {
		try {
			if(!findElement(By.xpath("(.//span[text()='Device Manufacturer Profile'])/.."), "DP tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			
				findElement(By.xpath("(.//span[text()='Device Manufacturer Profile'])"), "DP tile").click("Click on DP Tile");

			}
			findElement(By.id("deviceP_1"), "Create").click("Click on Create DP");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".treeview>li")));
			int count = findElements(By.cssSelector(".treeview>li"), "Tree").getCount("Get Tree Count");

			for (int i = 1; i <= count; i++) {

				String cust_name = findElement(By.cssSelector(".treeview>li:nth-child(" + i + ")>span"), "")
						.getAttribute(Attributes.TEXT);

				if (cust_name.equals(CustomerManagement.Cust_Name.toUpperCase())) {

					driver.findElement(By.cssSelector(".treeview>li:nth-child(" + i + ")>input")).click();

					CustID = driver.findElement(By.cssSelector(".treeview>li:nth-child(" + i + ")>input"))
							.getAttribute("data-resourceid");

					Log.info("Customer ID is " + CustID);
					break;
				}
				
			}
			
			

			findElement(By.xpath(".//*[@type='file']"), "Upload")
					.sendKeys(System.getProperty("user.dir") + "\\testdata\\"+fileName, "Upload File");
			if (cpAttach == true) {
				findElement(By.xpath(".//*[@ng-options='container.profileId as container.profileName for container in containerProfileList']"),"Select ContainerProfileField").selectByText(cpName[0],"select CP");
			}
			
			

			Thread.sleep(3000);

			findElement(By.xpath(".//*[contains(text(),'Submit')]"), "Create Button").click("Click on Submit");
		} catch (Exception e) {

			System.out.println(e);
			throw e;
		}

	}

	public void CreateNewAsset(boolean autoProvision, boolean generateKeyImport) throws InterruptedException {
		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
			}

			findElement(By.id("asset_2"), "Create Asset").click("Click on Create Asset");

			asset = prop.getProperty("asset_name") + randomNumber();

			Base_API.asset_name = asset;

			findElement(By.name("resourceName"), "Asset Name").sendKeys(asset, "Enter Asset Name");

			assetList.add(asset);

			findElement(By.id("resourceType"), "Asset Type").selectByValue("SENSOR", "Select Asset Type");

			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/a"), "Customer Drop Down")
					.click("Click on Customer Drop Down");

			findElements(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li"), "Get Customer Count");

			for (int i = 1; i <= _WebElements.size(); i++) {

				Thread.sleep(2000);

				String actual = _WebElements.get(i).getText();

				if (actual.trim().contains(CustomerManagement.Cust_Name.toUpperCase())) {

					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}

			}

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")));

			driver.findElement(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")).click();

			findElements(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/ul/li"), "Get Group Count");

			Thread.sleep(2000);

			for (int i = 1; i <= _WebElements.size(); i++) {

				Thread.sleep(2000);

				String actual = _WebElements.get(i).getText();

				if (actual.trim().contains(SmokeTest.Group.toUpperCase())) {

					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}

			}

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));

			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),
					"Dp drop down").click("Click on Dp Drop Down");

			findElements(By.xpath("(.//*[@class='custom-select-search'])[3]/../../div/ul/li"), "Get DP Count");

			for (int i = 1; i <= _WebElements.size() - 1; i++) {

				Thread.sleep(2000);

				String actual = _WebElements.get(i).getText();

				if (actual.equals(prop.getProperty("device_profile") + "-" + prop.getProperty("model") + "-"
						+ prop.getProperty("version"))) {

					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}

			}

			if (autoProvision == false) {

				findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div/input"), "Autoprovision")
						.click("Uncheck AutoProvision");
			}
			
			findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");

			String rand = "12345" + randomNumber();
			Thread.sleep(2000);
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-if='assetParamView']/div/div")));
			findElement(By.id("iotParamsDevAddr"), "DevAddr").sendKeys(rand, "Enter DevAddr");
			findElement(By.id("iotParamsDevEUI"), "DevEUI").sendKeys("AA" + randomNumber(), "Enter DevEUI");
			findElement(By.id("iotParamsAppEUI"), "AppEUI").sendKeys("BB" + randomNumber(), "Enter AppEUI");
			findElement(By.xpath(".//*[@value='GenericFlow']"), "FlowID").click("Select Flow ID");
			findElement(By.name("assetParams_PreferedDC"), "PreferedDC").click("Select PreferedDC");
			Thread.sleep(2000);
			if (generateKeyImport) {
				String csvFile = System.getProperty("user.dir") + "\\testdata\\KMS_OTA.csv";
				FileWriter writer = new FileWriter(csvFile);
				CSVUtils.writeLine(writer, Arrays.asList("[DevAddr#" + rand + "],AppSKey,B087CF619BBF6B"));

				Log.info("Key Import CSV Generated");
				writer.flush();
				writer.close();
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[text()='Create'])[1]")));
			findElement(By.xpath("(.//*[text()='Create'])[1]"), "Create Button").submit("Click on Create Button");

			Log.info("Asset Created : " + asset);
		} catch (Exception e) {

			Log.error(e.toString());

		}

	}

	public void ManageAsset() throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
			}

			findElement(By.id("asset_3"), "Manage AAsset").click("Click on Manage Asset");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Box").click("Click on Search Box");
			Thread.sleep(2000);
			findElement(By.name("assetname"), "Asset Name").sendKeys(Base_API.asset_name, "Enter Asset Name");
			findElement(By.xpath(".//*[@value='Search']"), "Search button").click("Click on Search Button");

			Thread.sleep(2000);

			String assetid = driver.findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr/td[2]/a"))
					.getAttribute("href");
			Base_API.nodelink = assetid.substring(assetid.lastIndexOf("=") + 1, assetid.length());

			nodelink = Base_API.nodelink;
			Log.info("Node Link " + Base_API.nodelink);

		} catch (Exception e) {
			throw e;
		}
	}

	public void CreateACP() throws Exception {

		try {
			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"Acp Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				findElement(By.xpath(".//span[text()='Access Control Policy']"),"ACP Tile").click("Cick on ACP Tile");
			}
			findElement(By.id("acp_1"), "Create ACP").click("Click on Create ACP");

			String acp = prop.getProperty("acp_name") + randomNumber();

			prop.setProperty("acp_name", acp);

			findElement(By.id("acpName"), "Acp Name").sendKeys(acp, "Enter Acp Name");
			findElement(By.id("acpDescription"), "Acp description").sendKeys(prop.getProperty("acp_desc"),
					"Enter Acp description");

			findElement(By.id("acpSelect"), "Acp Select").click("Click on Acp Select");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//li[@id='" + Base_API.nodelink + "']/span")));
			Thread.sleep(2000);
			findElement(By.xpath(".//li[@id='" + Base_API.nodelink + "']/span"), "Device List")
					.click("Click on Device");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@ng-model='$root.acpLogicalResource']"), "Containers").selectByIndex(1,
					"Select Container");
			Thread.sleep(2000);
			findElement(By.id("acpLocationSelect"), "Container Type").selectByIndex(0, "Select Container Type");
			findElement(By.xpath(".//button[@ng-click='subscribe()']"), "Done").click("Click on Done");

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

	public void EditACPRule() throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"Acp Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Access Control Policy']"),"ACP Tile").click("Cick on ACP Tile");
				Thread.sleep(3000);
			}
			findElement(By.id("acp_3"), "Search Acp").click("Click on Search Acp");

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//*[text()='" + prop.getProperty("acp_name") + "']")));
			findElement(By.xpath(".//*[text()='" + prop.getProperty("acp_name") + "']"), "Acp").click("Click on Acp");

			findElement(By.cssSelector(".fa.fa-pencil"), "Edit Icon").click("Click on Edit Icon");

			Thread.sleep(3000);

			findElement(By.id("acpDescription"), "ACP Description").sendKeys("Edit Desc", "Edit Description");

			findElement(By.id("acpSelect"), "Acp Select").click("Click on Acp Select");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));
			Thread.sleep(2000);
			findElement(By.xpath(".//li[@id='" + Base_API.nodelink + "']/span"), "Acp").click("Click on Acp");
			Thread.sleep(2000);
			findElement(By.xpath(".//button[@ng-click='addResource()']"), "Done").click("Click on Done");

			Thread.sleep(2000);
			findElement(By.xpath(".//*[text()='Update']"), "Update Button").click("Click on Update Button");
			Thread.sleep(2000);
		} catch (Exception e) {

			Log.error("Unable to Edit ACP");
			Log.error(e.toString());
			throw (e);
		}

	}

	public void AddNewApplciation() throws InterruptedException {
		try {
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Application Mgmt.']"),"Application Mgmt Tile").click("Click on Application Mgmt Tile");
				Thread.sleep(2000);
			}
			findElement(By.id("ae_1"), "Create App").click("Click on Create Application");

			String newapp = prop.getProperty("app_name") + randomNumber();

			Base_API.app_name = newapp;

			prop.setProperty("app_name", newapp);

			findElement(By.cssSelector("#appNameDiv>input"), "App Name").sendKeys(newapp, "Enter App");
			Thread.sleep(2000);
			findElement(By.xpath("(.//*[@id='sgroupId'])[1]"), "Customer Drop Down").selectByValue(CustID,
					"Select Customer");

			findElement(By.cssSelector("[ng-model='aeDetails.active']>small"), "Active").click("Click on Active tag");

			findElement(By.id("selectPol"), "Acp Select").click("Click on Acp Select");
			findElement(By.xpath(".//*[@ng-model='searchCriteria']"), "Acp Search")
					.sendKeys(prop.getProperty("acp_name"), "Enter Acp");
			findElement(By.xpath(".//tr[@ng-click='selectPolicy(acp)']/td[1]"), "Acp List").click("Click on Acp");
			Thread.sleep(2000);
			findElement(By.xpath(".//button[@ng-click='dynamicPopover.close()']"), "Done").click("Click on Done");

			findElement(By.xpath("(.//*[@id='sgroupId'])[2]"), "").selectByText("HTTP", "");
			findElement(By.xpath(".//*[@id='urlNameDiv']/textarea"), "End Point").sendKeys("http://dsm",
					"Enter End Point");

			findElement(By.xpath(".//*[text()='Create']"), "Create Button").click("Click Create Button");

			Log.info("Application Created : " + newapp);
		} catch (Exception e) {
			throw (e);
		}

	}

	public void EditNewApp() throws InterruptedException {
		try {
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Application Mgmt.']"),"Application Mgmt Tile").click("Click on App Mgmt Tile");
				Thread.sleep(2000);
			}
			findElement(By.id("ae_2"), "Manage App").click("Click on Manage App");

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//*[text()='" + prop.getProperty("app_name") + "']")));
			findElement(By.xpath(".//*[text()='" + prop.getProperty("app_name") + "']"), "App list")
					.click("Click on Application");

			Thread.sleep(2000);
			findElement(By.cssSelector(".fa.fa-pencil"), "Edit Icon").click("Click on Edit Icon");
			String option = "";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-model='aeDetails.groupId']")));
			Thread.sleep(3000);
			//wait.until(ExpectedConditions.stalenessOf((WebElement) By.xpath(".//*[@ng-model='aeDetails.groupId']")));
			do {
				option = findElement(By.xpath(".//*[@ng-model='aeDetails.groupId']"), "Customer DropDown")
						.getFirstSelectedOption("Get Customer Value");
				Log.info("Customer Drop Down Selected Value " + option);
			} while (!option.contains("IOT"));

			findElement(By.cssSelector("#selectPol"), "Acp Select").click("Click on Acp Select");
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//*[text()='" + prop.getProperty("acp_name") + "']")));
			findElement(By.xpath(".//*[text()='" + prop.getProperty("acp_name") + "']"), "Acp List")
					.click("Click on Acp");
			findElement(By.xpath(".//*[text()='Done']"), "Done").click("Click on DOne");

			findElement(By.xpath(".//*[text()='Update']"), "Update Button").click("Click on Update Button");

		} catch (Exception e) {

			Log.error("Unable to Edit Application");
			Log.error(e.toString());
			throw (e);
		}

	}

	public void Manage_App() throws Exception {

		try {
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Application Mgmt.']"),"App Mgmt Tile").click("Click on App Mgmt Tile");
				Thread.sleep(2000);
			}
			findElement(By.id("ae_2"), "Manage App").click("Click on Manage App");
			Thread.sleep(2000);
			findElement(By.linkText(Base_API.app_name), "App list").click("Click on App list");

			Thread.sleep(2000);
			Base_API.origin = driver.findElement(By.xpath(".//b[text()='User Name']/../..//div[1]/span")).getText();
			findElement(By.xpath(".//input[@ng-model='password']"), "Password").sendKeys("password", "Rest Password");
			findElement(By.xpath(".//button[text()='Reset password']"), "Retest Password")
					.click("Click on Rest Password");

			Thread.sleep(2000);
			Log.info("Origin = " + Base_API.origin);

			String toencode = Base_API.origin + ":password";
			Base_API.auth = Base64.getEncoder().encodeToString(toencode.getBytes("utf-8"));

			Log.info("Auth = " + Base_API.auth);

		} catch (Exception e) {

			throw (e);
		}

	}

	public void VerifyNewPolicy() throws InterruptedException {

		boolean throwErr = true;

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[text()='" + prop.getProperty("app_name") + "']")));
		findElement(By.xpath(".//*[text()='" + prop.getProperty("app_name") + "']"), "App list").click("Click on App");

		int count = findElements(By.xpath(".//*[text()='Access Policy']/../div/span"), "Acp List")
				.getCount("Get Acp Count");

		for (int i = 1; i <= count; i++) {

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(".//*[text()='Access Policy']/../div/span[" + i + "]")));
			String acp = driver.findElement(By.xpath(".//*[text()='Access Policy']/../div/span[" + i + "]")).getText();
			System.out.println(acp);
			if (acp.equals(prop.getProperty("acp_name"))) {

				throwErr = false;
			}
		}
		if (throwErr) {

			throw new AssertionError("Failed to Varify New Policy");
		}
	}

	public void CreateNewScheduler() throws InterruptedException {
		if (!driver.findElement(By.xpath("(.//span[text()='Scheduler'])/..")).getAttribute("class")
				.contains("selected")) {
			driver.findElement(By.xpath(".//span[text()='Scheduler']")).click();
			Thread.sleep(2000);
		}
		driver.findElement(By.id("scheduler_1")).click();

		// WaitForFirefoxDOM();

		driver.findElement(By.id("scheduleName")).sendKeys(prop.getProperty("scheduler_name") + randomNumber());

		new Select(driver.findElement(By.xpath(".//*[text()='SLA Name']/../div/select")))
				.selectByVisibleText(prop.getProperty("new_sla"));

		new Select(driver.findElement(By.xpath("(.//*[text()='Tenant'])[2]/../div/select")))
				.selectByVisibleText("IOT." + prop.getProperty("group_name").toUpperCase());

		driver.findElement(By.xpath(".//*[text()='Create']")).click();

	}

	public void Verify(String Expected) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);

			_assert.contains(actual, Expected, "Verify Success");

			if (driver.findElements(By.xpath(".//*[@class='alert alert-danger']")).size() != 0) {

				String alert = findElement(By.xpath(".//*[@class='alert alert-danger']"), "Danger Alert")
						.getAttribute(Attributes.TEXT);

				Log.info(alert);
				if (alert.contains("Auto Registration Failed!")) {

					Log.error("Device Auto Registration Failed");
					//Assert.fail();
				}
			}
		} catch (Exception e) {

			Log.error("Unable to Verify Success Message");
			Log.error(e.toString());
			throw (e);
		}

	}

	public void CreateSLA() throws InterruptedException {
		if (!driver.findElement(By.xpath("(.//span[text()='Scheduler'])/..")).getAttribute("class")
				.contains("selected")) {
			driver.findElement(By.xpath(".//span[text()='Scheduler']")).click();
			Thread.sleep(2000);
		}
		driver.findElement(By.id("scheduler_3")).click();
		// WaitForFirefoxDOM();

		String sla = prop.getProperty("new_sla") + randomNumber();
		prop.setProperty("new_sla", sla);

		driver.findElement(By.id("slaName")).sendKeys(sla);
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(".//*[text()='Device Profile']/../div/select")))
				.selectByVisibleText(prop.getProperty("device_profile") + "-" + prop.getProperty("model"));
		new Select(driver.findElement(By.xpath(".//*[text()='Frequency']/../div/select")))
				.selectByVisibleText(prop.getProperty("sla_frequency"));
		new Select(driver.findElement(By.xpath(".//*[text()='SLA Types']/../div/select")))
				.selectByVisibleText(prop.getProperty("sla_type"));

		driver.findElement(By.xpath(".//*[text()='Create']")).click();

	}

	public void CreateDisplayConfig() throws InterruptedException {

		driver.findElement(By.xpath(".//span[text()='Display Configuration']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("displayP_1")).click();

		// WaitForFirefoxDOM();

		new Select(driver.findElement(By.id("groupId")))
				.selectByVisibleText(prop.getProperty("group_name").toUpperCase());

		Thread.sleep(2000);

		new Select(driver.findElement(By.id("deviceProfileId")))
				.selectByVisibleText(prop.getProperty("device_profile") + "-" + prop.getProperty("model"));

		Thread.sleep(3000);

		driver.findElement(By.id("dpName")).sendKeys(prop.getProperty("dp_name") + "_" + randomNumber());
		driver.findElement(By.id("profileName_0")).sendKeys(prop.getProperty("profile_name") + randomNumber());

		new Select(driver.findElement(By.id("profileType_0"))).selectByIndex(1);

		Thread.sleep(3000);

		driver.findElement(By.id("createProfile")).click();

	}

	public void CreateDeviceController() throws InterruptedException {

		try{
		
		findElement(By.xpath(".//span[text()='Device Controllers']"),"DC Tile").click("Click on DC Tile");
		findElement(By.id("dcm_1"),"Manage DC Option").click("Click on Manage DC Option");

		Thread.sleep(3000);
		findElement(By.id("DCName"),"DC Name Text Box").sendKeys(prop.getProperty("controller_name") + randomNumber(),"Enter DC Name");
		findElement(By.id("RoutingType"),"Routing Type Drop Down").selectByIndex(1,"Select Index 1");
		findElement(By.id("TChannel"),"Transport Channel Text Box").sendKeys("Test"+randomNumber(),"Enter Transport Channel");
		findElement(By.name("HTTPEndpoint"),"HTTP End Point Text Box").sendKeys(prop.getProperty("url"),"Enter HTTP End Point");

		findElement(By.xpath(".//button[contains(text(),'Create')]"),"Create Button").click("Click on Create");
		
		}catch(Exception e){
			
			Log.error("Unable to Create DC");
			Log.error(e.toString());
			throw(e);
		}

	}

	public void VerifyDeviceController(String Expected) {

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
	
	public void logout() throws Exception {

		try{
			if(Boolean.valueOf(prop.getProperty("isSSO"))){
		findElement(By.className("dropdown-toggle"),"Log out Menu").click("Click on Logout Menu");
		findElement(By.xpath(".//*[contains(@href,'logout')]"),"Logout Link").click("Click on Logout Link");

		Thread.sleep(2000);
			}
		
		else{
			findElement(By.className("dropdown-toggle"),"Log out Menu").click("Click on Logout Menu");
			findElement(By.xpath(".//*[contains(@href,'logout')]"),"Logout Link").click("Click on Logout Link");

			Thread.sleep(2000);
			
		}
		}
		catch(Exception e){
			
			Log.error("Unable to LOGOUT and Hence Re-Lauching the browser");
			Log.error(e.getMessage().toString());
			closeBrowser();
			setup();
			
		}
		}

	public void WaitForFirefoxDOM() throws InterruptedException {

		Thread.sleep(3000);
	}

}
