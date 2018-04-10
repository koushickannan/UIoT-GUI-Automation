package com.automation.pageobjects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class MultiTenancy extends ElementBase {

	final static Logger Log = Logger.getLogger(MultiTenancy.class.getName());
	SmokeTest cmp = new SmokeTest();

	By customer_mgmt_dash = By.xpath(".//*[text()='Customer Mgmt.']");
	By customer_radio = By.xpath(".//input[@value='CUSTOMER'][@type='radio']");
	By select_tab = By.xpath(".//li[contains(@ng-click,'Customer')]");
	By search_quick_link = By.xpath(".//i[@class='icon icon-search']");
	By search_button = By.xpath(".//input[@value='Search']");
	By create = By.xpath(".//button[text()='Create']");
	By create_customer = By.id("cust_1");
	By search_customer = By.id("cust_2");
	By parent_customer = By.id("groupId");
	By roleId = By.id("roleId");
	By customer_name = By.name("sGroupName");
	By retention_time = By.name("sretentionTime");
	By customer_desc = By.name("sdescription");

	public static String Cust_Name1 = null;
	public static String Group = "";
	public static String Sub_Group = "";
	static String result = null;
	public static String Sub_Customer = null;
	public static int grup_count = 0;
	int parentCustDpCount = 0;

	public void Create_Sub_Customer() throws InterruptedException {

		try {
			Sub_Customer = "Auto_Sub_Cust" + randomNumber();

			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			findElement(create_customer, "Create Customer").click("Click on Create Customer");

			findElement(customer_radio, "Customer Radio Button").click("Select Customer Radio Button");

			findElement(customer_name, "Customer Name").sendKeys(Sub_Customer, "Enter Customer Name");

			findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/a"), "Parent Customer")
					.click("Click on Parent Customer");

			findElements(By.xpath("((.//*[@class='custom-select-search'])/../ul)[1]/li"), "Find Customer COunt");

			for (int i = 1; i <= _WebElements.size(); i++) {

				String actual = _WebElements.get(i).getText();

				if (actual.equals("IOT." + CustomerManagement.Cust_Name.toUpperCase())) {

					_WebElements.get(i).click();

					break;
				}

			}

			findElement(retention_time, "Retension Time").sendKeys(prop.getProperty("retention_period"),
					"Enter Retension Period");
			findElement(customer_desc, "Customer Description").sendKeys("Test Desc", "Enter Customer Description");
			findElement(By.xpath(".//*[contains(text(),'Business Data')]/input"), "Business Data")
					.click("Select Business Data");
			findElement(roleId, "Role").selectByValue("100", "Select Role");

			Thread.sleep(3000);
			findElement(create, "Create Button").click("Click on Create Button");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual, "Success", "Verify Sub Customer is Created");
			
			Log.info("Customer Create : " + Sub_Customer);
		} catch (Exception e) {

			Log.error("Unable to Create SubCustomer");
			Log.error(e.getMessage().toString());
			throw (e);
		}

	}

	public void Search_Sub_Customer() throws InterruptedException {
		try {

			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			Thread.sleep(2000);
			findElement(search_customer, "Search Customer").click("CLick on Search Customer");
			findElement(select_tab, "Customer Tab").click("Select Customer Tab");
			
			findElement(By.xpath(".//*[@class='btn quicklinks']"), "Search Quicklinks").click("Click on Search Quicklinks");
			findElement(By.xpath(".//*[@name='groupname']"), "Customer Name Text Box").sendKeys(Sub_Customer, "Enter Customer Name");
			
			findElement(By.xpath(".//*[@ng-model='searchCusObj.search.includeSubGroups']"), "Check Box").click("Click on Check Box");
			findElement(By.cssSelector("[ng-click='groupSearch()']"), "Search Button").click("Click on Search Button");
			
			findElement(
					By.xpath(
							"//thead[tr[th[contains(text(),'Customer Name')]]]/following-sibling::tbody/tr[1]/td[2]/a"),
					"Goto parent customer").click("click on parent customer");

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//thead[tr[th[contains(text(),'Customer Name')]]]/following-sibling::tbody/tr/td[1]")));
			
			findElement(By.xpath("//thead[tr[th[contains(text(),'Customer Name')]]]/following-sibling::tbody/tr/td[1]"), "Sub Customer").click("Click on Sub Customer");
			
			String subcustomer = findElement(
					By.cssSelector("[ng-bind='viewdata.parentGroupName']"),
					"Select SubCustomer Name").getAttribute(Attributes.TEXT);
			_assert.equals(subcustomer.toUpperCase().trim(),CustomerManagement.Cust_Name.toUpperCase().trim(), "Verify Sub Customer Name");

		} catch (Exception e) {

			Log.error("Unable to Search SubCustomer");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void Create_SubGroup() throws Exception {
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='GROUP'][@type='radio']")));
			findElement(By.xpath(".//input[@value='GROUP'][@type='radio']"), "Group Radio Button").click("Click Group Radio Button");

			Sub_Group = prop.getProperty("group_name") + randomNumber();

			findElement(By.xpath(".//input[@name='sGroupName']"), "Group Name").sendKeys(Sub_Group, "Enter Group Name");

			Thread.sleep(2000);

			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]"), "Customer Drop Down")
					.click("Click on Customer Drop Down");

			findElement(By.xpath("(.//input[@ng-model='searchTerm'])[1]"), "Customer Search Text Box").sendKeys(CustomerManagement.Cust_Name, "Enter Customer Name");
		
			Thread.sleep(2000);
			findElement(By.xpath("(.//*[@class='custom-select-search'])[1]/../ul/li[2]"), "Customer in Drop Down").click("Click on Customer");

			findElement(By.name("sdescription"), "Descrption").sendKeys(prop.getProperty("tenant_desc"),
					"Enter Description");
			findElement(By.xpath("//div[label[contains(text(),'Parent Group')]]/div/div/a"), "Goto Parent Group")
					.click("Click on parent group");
			Thread.sleep(2000);
			findElement(By.xpath("//div[label[contains(text(),'Parent Group')]]/div/div/div/ul/li[2]"),
					"Select the parent Group").click("Click on the parent group");

			findElement(By.xpath(".//button[text()='Create']"), "Create Button").submit("Click on Create Button");

			Thread.sleep(3000);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual, "Success", "Create Sub Group is verified");

			Log.info("Sub Group Create : " + Sub_Group);
		} catch (Exception e) {

			Log.error("Unable to create group");
			Log.error(e.toString());
			throw(e);
		}
	}

	public void Search_SubGroup() throws InterruptedException {
		try {

			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			Thread.sleep(2000);
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			
			
			findElement(By.xpath(".//*[@name='groupname']"), "Customer Name Text Box").sendKeys(Sub_Group, "Enter Sub Group Name");
			
			//findElement(By.xpath(".//*[@ng-model='searchCusObj.search.includeSubGroups']"), "Check Box").click("Click on Check Box");
			findElement(By.cssSelector("[ng-click='groupSearch()']"), "Search Button").click("Click on Search Button");
			
			findElement(By.xpath(".//*[@st-table='entityList']/tbody/tr[1]/td[2]/a"), "Sub Group Link")
					.click("Click on Sub Group Link");
			
			String subgroup = findElement(
					By.cssSelector("[ng-bind='viewdata.parentGroupName']"),
					"Select SubGroup Name").getAttribute(Attributes.TEXT);
			_assert.equals(subgroup.toUpperCase().trim(),SmokeTest.Group.toUpperCase().trim(), "Verify Sub Group Name");

			
		}

		catch (Exception e) {

			Log.error("Unable to Search Sub_Group");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void createGroup_Of_SubCustomer() throws Exception {
		try {

			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath(".//span[text()='Customer Mgmt.']"),"Customer Mgmt Tile").click("Click on Customer Mgmt Tile");
				Thread.sleep(2000);
			}
			Thread.sleep(2000);
			findElement(By.id("cust_1"), "Create Group").click("Click on Create Group");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='GROUP'][@type='radio']")));
			findElement(By.xpath(".//input[@value='GROUP'][@type='radio']"), "Group Radio Button").click("Click Group Radio Button");

			Group = prop.getProperty("group_name") + randomNumber();

			findElement(By.xpath(".//input[@name='sGroupName']"), "Group Name").sendKeys(Group, "Enter Group Name");
			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]"), "Customer Drop Down")
					.click("Click on Customer Drop Down");
			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]/div/ul/li[3]"), "Goto Sub_Custm")
					.click("Select_SubCustomer");

			Thread.sleep(2000);

			findElement(By.name("sdescription"), "Descrption").sendKeys(prop.getProperty("tenant_desc"),
					"Enter Description");
			findElement(By.xpath(".//button[text()='Create']"), "Create Button").submit("Click on Create Button");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual, "Success", "Create Sub Group is verified");

			Log.info("Group Create for sub customer : " + Group);

		} catch (Exception e) {

			Log.error("Unable to Cretae Group for SubCustomer ");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void deleteGroup_Of_AssociatedElement() throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			Thread.sleep(2000);
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div"), "Goto select Element")
					.click("Click on select Elment");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div/div/ul/li[2]"), "Select Customer")
					.click("Cick on customer");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");
			findElement(
					By.xpath(
							"//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr[1]/td[1]/input"),
					"Goto check box Element").click("Select Element");
			findElement(
					By.xpath(
							"//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr[1]/td[6]/span/i"),
					"goto delete button").click("Click Delete");
			findElement(By.xpath("//button[contains(text(),'OK')]"), "Goto OK BUTTON").click("Click on OK button");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-danger']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-danger']"), "Error Message")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual, "Error", "Updated Profile is verified");

		} catch (Exception e) {
			Log.error("Unable deleteGroup_Of_AssociatedElement ");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void deleteGroup_Of_NotAssociatedElement() throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath(".//span[text()='Customer Mgmt.']"),"Customer Mgmt Tile").click("Click on Customer Mgmt Tile");
				Thread.sleep(2000);

			}

			findElement(By.id("cust_1"), "Create Group").click("Click on Create Group");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='GROUP'][@type='radio']")));
			findElement(By.xpath(".//input[@value='GROUP'][@type='radio']"), "Group Radio Button").click("Click Group Radio Button");

			Group = prop.getProperty("group_name") + randomNumber();

			findElement(By.xpath(".//input[@name='sGroupName']"), "Group Name").sendKeys(Group, "Enter Group Name");
			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]"), "Customer Drop Down")
					.click("Click on Customer Drop Down");
			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]/div/ul/li[2]"), "Goto Element")
					.click("Click on Element");

			Thread.sleep(2000);

			findElement(By.name("sdescription"), "Descrption").sendKeys(prop.getProperty("tenant_desc"),
					"Enter Description");
			findElement(By.xpath("//div[label[contains(text(),'Parent Group')]]/div/div/a"), "Goto Parent Group")
					.click("Click on parent group");
			findElement(By.xpath("//div[label[contains(text(),'Parent Group')]]/div/div/div/ul/li[2]"),
					"Select the parent Group").click("Click on the parent group");
			findElement(By.xpath(".//button[text()='Create']"), "Create Button").submit("Click on Create Button");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual, "Success", "Create Sub Group is verified");

			Log.info("Group Create : " + Group);

			findElement(
					By.xpath(
							"//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr[1]/td[1]/input"),
					"Goto Chaeck Box").click("Click on Check Box");
			findElement(
					By.xpath(
							"//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr[1]/td[6]/span/i"),
					"goto delete button").click("Click Delete");
			findElement(By.xpath("//button[contains(text(),'OK')]"), "Goto OK BUTTON").click("Click on OK button");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual1 = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual1, "Success", "Delete Group is verified");
		} catch (Exception e) {
			Log.error("deleteGroup_Of_NotAssociatedElement");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void EditGroup_Of_AssociatedElement() throws Exception {

		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			Thread.sleep(2000);

			Group = prop.getProperty("group_name") + randomNumber();
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div"), "Goto select Element")
					.click("Click on select Elment");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div/div/ul/li[2]"), "Select Customer")
					.click("Cick on customer");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");
			findElement(
					By.xpath(
							"//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr[1]/td[1]/input"),
					"Goto check box Element").click("Select Element");
			findElement(
					By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr[1]/td[5]/a/i"),
					"goto Edit button").click("Click on Edit");
			findElement(By.xpath(".//input[@name='sGroupName']"), "Group Name").sendKeys(Sub_Group, "Enter Group Name");
			findElement(By.xpath("//button[contains(text(),'Update')]"), "Update Elements")
					.click("Click on Update Elements");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual1 = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual1, "Success", "Edit Group is verified");

		} catch (Exception e) {
			Log.error("Unable EditGroup_Of_AssociatedElement ");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void Verify_EditGroup() {
		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
			}
			findElement(By.xpath(".//*[@id='asset_3']"), "Goto Manage Asset").click("Click on manage asset");

			findElement(
					By.xpath("//thead[tr[th[span[contains(text(),'Edit')]]]]/following-sibling::tbody/tr[1]/td[2]/a"),
					"Goto Asset").click("Click on Asset");
			String actual = findElement(By.xpath("//div[label[contains(text(),'Group ID')]]/div[1]/span"), "Group ID")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual, SmokeTest.Group.toUpperCase(), "Verify the edited group through Asset");
		} catch (Exception e) {
			Log.error("Unable to verify Edit_Group through Asset  ");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void groupSearch_with_CustomerName() throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div"), "Goto select Element")
					.click("Click on select Elment");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div/div/ul/li[2]"), "Select Customer")
					.click("Cick on customer");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");
			findElement(
					By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr[1]/td[2]/a"),
					"Goto Group").click("click on Group");

			Thread.sleep(3000);
			String actual = findElement(By.xpath("//div[label[contains(text(),'Group Name')]]/div/span"), "Goto Group")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual, cmp.Group.toUpperCase(), "Group is searched by Customer name");

		} catch (Exception e) {
			Log.error("Unable to search the Group with the help of customer_name  ");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void GroupSearch_with_CustomerName_parentgroup() throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath(".//span[text()='Customer Mgmt.']"),"Customer Mgmt Tile").click("Click on Customer Mgmt Tile");
				Thread.sleep(2000);

			}

			findElement(By.id("cust_1"), "Create Group").click("Click on Create Group");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='GROUP'][@type='radio']")));
			findElement(By.xpath(".//input[@value='GROUP'][@type='radio']"), "Group Radio Button").click("Click Group Radio Button");

			Group = prop.getProperty("group_name") + randomNumber();

			findElement(By.xpath(".//input[@name='sGroupName']"), "Group Name").sendKeys(Group, "Enter Group Name");
			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]"), "Customer Drop Down")
					.click("Click on Customer Drop Down");
			findElement(By.xpath(".//div[contains(@ng-model,'parentId')]/div/ul/li[2]"), "Goto Element")
					.click("Click on Element");

			Thread.sleep(2000);

			findElement(By.name("sdescription"), "Descrption").sendKeys(prop.getProperty("tenant_desc"),
					"Enter Description");
			findElement(By.xpath("//div[label[contains(text(),'Parent Group')]]/div/div/a"), "Goto Parent Group")
					.click("Click on parent group");
			findElement(By.xpath("//div[label[contains(text(),'Parent Group')]]/div/div/div/ul/li[2]"),
					"Select the parent Group").click("Click on the parent group");
			findElement(By.xpath(".//button[text()='Create']"), "Create Button").submit("Click on Create Button");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual1 = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual1, "Success", "Create Sub Group is verified");

			Log.info("Group Create : " + Group);
			/*
			 * findElement(search_customer, "Search Custome/Group").click(
			 * "CLick on Search Customer/Group ");
			 * findElement(By.xpath("//a[text()='Group']"),"Goto Group Button"
			 * ).click("Click on group button");
			 */
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div"), "Goto select Element")
					.click("Click on select Elment");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div/div/ul/li[2]"), "Select Customer")
					.click("Cick on customer");
			findElement(By.xpath("//div[contains(text(),'Group List')]/div"), "Goto Group List").click("group list");
			findElement(By.xpath("//div[contains(text(),'Group List')]/div/div/ul/li[2]"),
					"Goto Grup & select parent group").click("Click to group");
			findElement(By.xpath("//div[contains(text(),'Include Sub Customers & Groups')]/input"), "Goto Include box")
					.click("Check Include box");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");

			String actual = findElement(
					By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr/td/a"),
					"Goto Group").getAttribute(Attributes.TEXT);
			_assert.contains(actual, "IOT." + CustomerManagement.Cust_Name.toUpperCase() + "."
					+ SmokeTest.Group.toUpperCase() + "." + Group.toUpperCase(),
					"Group is searched by Customer name & Parent Group");
		} catch (Exception e) {
			Log.error("Unable to search the Group with the help of customer_name & Parent Group Name");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void GroupSearch_with_CustomerName_parentgroup_without_IncludeBox() {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div"), "Goto select Element")
					.click("Click on select Elment");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div/div/ul/li[2]"), "Select Customer")
					.click("Cick on customer");
			findElement(By.xpath("//div[contains(text(),'Group List')]/div"), "Goto Group List").click("group list");
			findElement(By.xpath("//div[contains(text(),'Group List')]/div/div/ul/li[2]"),
					"Goto Grup & select parent group").click("Click to group");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");

			String actual = findElement(
					By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr/td/a"),
					"Goto Group").getAttribute(Attributes.TEXT);
			_assert.contains(actual, "IOT." + CustomerManagement.Cust_Name.toUpperCase() + "."
					+ SmokeTest.Group.toUpperCase() + "." + Sub_Group.toUpperCase(),
					"Group is searched by Customer name & Parent Group");
		} catch (Exception e) {
			Log.error(
					"Unable to search the Group with the help of customer_name & Parent Group Name without Include Group");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void GroupSearch_with_groupName() {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElement(By.xpath("//span[contains(text(),'Group Name')]/input"), "Goto group Name").sendKeys(Group,
					"Enter Group Name");
			findElement(By.xpath("//div[contains(text(),'Include Sub Customers & Groups')]/input"), "Goto Include box")
					.click("Check Include box");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");
			findElement(
					By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr[1]/td[2]/a"),
					"Goto Group").click("Click on Group");

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[label[contains(text(),'Group Name')]]/div/span")));
			String actual = findElement(By.xpath("//div[label[contains(text(),'Group Name')]]/div/span"), "Goto Group")
					.getAttribute(Attributes.TEXT);
			_assert.contains(actual, Group.toUpperCase(), "Group is searched by Customer name & Parent Group");
		} catch (Exception e) {
			Log.error("Unable to search the Group with the help  Group Name with Include Group");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void GroupSearch_with_CustomerName_parentgroup_GroupName() {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElement(By.xpath("//span[contains(text(),'Group Name')]/input"), "Goto grup Name").sendKeys(Group,
					"Group Name");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div"), "Goto select Element")
					.click("Click on select Elment");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div/div/ul/li[2]"), "Select Customer")
					.click("Cick on customer");
			findElement(By.xpath("//div[contains(text(),'Group List')]/div"), "Goto Group List").click("group list");
			findElement(By.xpath("//div[contains(text(),'Group List')]/div/div/ul/li[2]"),
					"Goto Grup & select parent group").click("Click to group");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");

			String actual = findElement(
					By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr/td/a"),
					"Goto Group").getAttribute(Attributes.TEXT);
			_assert.contains(
					actual, "IOT." + CustomerManagement.Cust_Name.toUpperCase() + "." + SmokeTest.Group.toUpperCase()
							+ "." + Sub_Group.toUpperCase(),
					"Group is searched by Customer name & Parent Group & Group Name");
		} catch (Exception e) {
			Log.error(
					"Unable to search the Group with the help of customer_name,Parent GroupName & Group Name without Include Group");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void GroupSearch_with_CustomerName_parentgroup_GroupName_IncludeSubGroup() {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElement(By.xpath("//span[contains(text(),'Group Name')]/input"), "Goto grup Name").sendKeys(Group,
					"Group Name");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div"), "Goto select Element")
					.click("Click on select Elment");
			findElement(By.xpath("//div[contains(text(),'Customer List')]/div/div/ul/li[2]"), "Select Customer")
					.click("Cick on customer");
			findElement(By.xpath("//div[contains(text(),'Group List')]/div"), "Goto Group List").click("group list");
			findElement(By.xpath("//div[contains(text(),'Group List')]/div/div/ul/li[2]"),
					"Goto Grup & select parent group").click("Click to group");
			findElement(By.xpath("//div[contains(text(),'Include Sub Customers & Groups')]/input"), "Goto Include box")
					.click("Check Include box");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");

			String actual = findElement(
					By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr/td/a"),
					"Goto Group").getAttribute(Attributes.TEXT);
			_assert.contains(
					actual, "IOT." + CustomerManagement.Cust_Name.toUpperCase() + "." + SmokeTest.Group.toUpperCase()
							+ "." + Sub_Group.toUpperCase(),
					"Group is searched by Customer name & Parent Group & Group Name");
		} catch (Exception e) {
			Log.error(
					"Unable to search the Group with the help of customer_name,Parent GroupName & Group Name with Include Sub Group");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void Group_Search_with_Reset() {
		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}
			findElement(search_customer, "Search Custome/Group").click("CLick on Search Customer/Group ");
			findElement(By.xpath("//a[text()='Group']"), "Goto Group Button").click("Click on group button");
			findElement(search_quick_link, "click on search quick link").click("click on quick link");
			findElements(By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr/td[2]/a"),
					"Goto Group List->Group Name Count");

			for (int i = 0; i < _WebElements.size(); i++) {
				String actual = findElement(By.xpath("//ul[li[contains(text(),'Group List')]]/li[2]/div"),
						"Get the visible count of Group List ").getAttribute(Attributes.TEXT);
				grup_count = _WebElements.size();
				if (actual.equals(String.valueOf(grup_count))) {

					break;
				}
			}
			Log.info("Group Count Before Reset :" + grup_count);
			findElement(By.xpath("//span[contains(text(),'Group Name')]/input"), "Goto grup Name").sendKeys(Group,
					"Group Name");
			findElement(search_button, "Goto Search Button").click("Click on Search Button");
			findElements(By.xpath("//input[@value='Reset']"), "Goto Reset Button").click("Click on Reset Button");
			findElements(By.xpath("//thead[tr[th[contains(text(),'Group Name')]]]/following-sibling::tbody/tr/td[2]/a"),
					"Goto Group List->Group Name Count");
			for (int i = 0; i < _WebElements.size(); i++) {
				String actual = findElement(By.xpath("//ul[li[contains(text(),'Group List')]]/li[2]/div"),
						"Get the visible count of Group List ").getAttribute(Attributes.TEXT);
				grup_count = _WebElements.size();
				if (actual.equals(String.valueOf(grup_count))) {
					System.out.println(grup_count);
					break;
				}
			}
			Log.info("Group Count After Reset :" + grup_count);
		} catch (Exception e) {
			Log.error("Unable to verify the Group after Reset");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	public void validateCustomerView() {

		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."), "Customer Mgmt Tile in Dashboard")
					.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}

			findElement(By.id("cust_2"), "Search Customer/Group").click("Click on search customer");
			findElement(By.xpath(".//*[contains(@ng-class,'Customer')]"), "Customer Tab")
					.click("Click on Customer Tab");

			findElement(By.cssSelector(".btn.quicklinks"), "Search Icon").click("Click on Search Icon");
			// findElement(By.cssSelector("[name='groupname']"), "Customer Name
			// Text Box").sendKeys(CustomerManagement.Cust_Name,"Enter Customer
			// Name");
			findElement(By.xpath(".//*[contains(@ng-model,'includeSubGroups')]"), "Sub group check box")
					.click("Click on Sub Group Check Box");
			findElement(By.cssSelector("[value='Search']"), "Search Button").click("Click on Search Button");
			findElement(By.xpath(".//*[contains(@ng-click,'goToViewPage')]"), "Customer Name Link")
					.click("Click on customer link");

			wait.until(ExpectedConditions.textToBePresentInElementValue(
					By.xpath("(.//*[text()='Parent Customer'])/../div/span"),
					CustomerManagement.Cust_Name.toUpperCase()));

			/*
			 * _assert.equals(findElement(By.xpath(
			 * "(.//*[text()='Group Name'])/../div/span"), "Customer Name")
			 * .getAttribute(Attributes.TEXT),
			 * CustomerManagement.Cust_Name.toUpperCase(),
			 * "Verify Customer Name");
			 */
			_assert.equals(
					findElement(By.xpath("(.//*[text()='Parent Customer'])/../div/span"), "Customer Name")
							.getAttribute(Attributes.TEXT),
					CustomerManagement.Cust_Name.toUpperCase(), "Verify Parent Customer Name");

		} catch (Exception e) {
			Log.error("Unable to Validate Customer View");
			Log.error(e.toString());
			throw (e);
		}
	}

	public void validateGroupView() {
		// Login as Customer User
		try {

			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."), "Customer Mgmt Tile in Dashboard")
					.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}

			findElement(By.id("cust_2"), "Search Customer/Group").click("Click on Search Group");
			// findElement(By.xpath(".//*[contains(@ng-class,'Group')]"),
			// "Customer Tab").click("Click on Group Tab");

			String xpathForGroup = "IOT." + CustomerManagement.Cust_Name.toUpperCase() + "."
					+ SmokeTest.Group.toUpperCase();

			findElement(By.xpath("//a[text()='Group']"),"Group Tab").click("Click on Group Tab");
			/*
			 * findElement(By.cssSelector(".btn.quicklinks"), "Search Icon"
			 * ).click("Click on Search Icon");
			 * findElement(By.cssSelector("[name='groupname']"),
			 * "Customer Name Text Box")
			 * .sendKeys(SmokeTest.Group.toUpperCase(), "Enter Group Name");
			 * findElement(By.xpath(
			 * ".//*[contains(@ng-model,'includeSubGroups')]"),
			 * "Sub group check box") .click("Click on Sub Group Check Box");
			 * findElement(By.cssSelector("[value='Search']"), "Search Button"
			 * ).click("Click on Search Button");
			 * findElement(By.xpath(".//*[contains(@ng-click,'goToViewPage')]"),
			 * "Customer Name Link") .click("Click on customer link");
			 */

			findElement(By.xpath(".//*[text()='" + xpathForGroup + "']"), "Customer Group").click("Click on Group");

			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Group Name']/../div/span")));
			wait.until(ExpectedConditions.textToBePresentInElementValue(
					By.xpath(".//*[text()='Group Name']/../div/span"), SmokeTest.Group.toUpperCase()));

			_assert.contains(findElement(By.xpath(".//*[text()='Group Name']/../div/span"), "Group Name Text")
					.getAttribute(Attributes.TEXT), SmokeTest.Group.toUpperCase(), "Verify Group Name");
			_assert.equals(
					findElement(By.xpath(".//*[text()='Parent Customer']/../div/span"), "Parent Customer Name Text")
							.getAttribute(Attributes.TEXT),
					CustomerManagement.Cust_Name.toUpperCase(), "Verify Customer Name");

			findElement(By.xpath(".//a[contains(@href,'toplogyView')]/div"), "Asset Topology Tile")
					.click("Click on Asset Topology");
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='fa
			// fa-spinner fa-spin']")));

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@class='fa fa-spinner fa-spin']")));

			_assert.equals(
					driver.findElement(By.xpath(".//*[text()='" + SmokeTest.Group.toUpperCase() + "']")).isDisplayed(),
					"Verify if Group is displayed in Asset Topology");

		} catch (Exception e) {
			Log.error("Unable to Validate Group Details");
			Log.error(e.toString());
			throw (e);
		}
	}

	public void validateSubCustomer() throws Exception {

		try {

			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."), "Customer Mgmt Tile in Dashboard")
					.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}

			findElement(By.id("cust_2"), "Search Customer/Group").click("Click on Search Group");
			findElement(By.xpath(".//*[contains(@ng-class,'Customer')]"), "Customer Tab")
					.click("Click on Customer Tab");

			findElement(By.cssSelector(".btn.quicklinks"), "Search Icon").click("Click on Search Icon");
			findElement(By.cssSelector("[name='groupname']"), "Customer Name Text Box")
					.sendKeys(Sub_Customer.toUpperCase(), "Enter SUB Customer Name");
			findElement(By.xpath(".//*[contains(@ng-model,'includeSubGroups')]"), "Sub group check box")
					.click("Click on Sub Group Check Box");
			findElement(By.cssSelector("[value='Search']"), "Search Button").click("Click on Search Button");
			findElement(By.xpath(".//*[contains(@ng-click,'goToViewPage')]"), "Customer Name Link")
					.click("Click on customer link");

			Thread.sleep(3000);

			_assert.equals(
					findElement(By.xpath(".//*[text()='Parent Customer']/../div/span"), "Parent Customer Name Text")
							.getAttribute(Attributes.TEXT),
					CustomerManagement.Cust_Name.toUpperCase(), "Verify Customer Name");

			findElement(By.xpath(".//a[contains(@href,'toplogyView')]/div"), "Asset Topology Tile")
					.click("Click on Asset Topology");
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='fa
			// fa-spinner fa-spin']")));

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@class='fa fa-spinner fa-spin']")));

			findElement(By.xpath(".//*[contains(text(),'AUTO_SUB_CUST')]"), "Sub Customer in Topology")
					.click("Click on Sub Customer");

			String defText = findElement(By.xpath(".//*[contains(text(),'AUTO_SUB_CUST')]/../ul[1]/li[1]/span"),
					"Default Text").getAttribute(Attributes.TEXT);

			_assert.equals(defText.toUpperCase(), "DEFAULT", "Check if Default group is present");

		} catch (Exception e) {
			Log.error("Unable to validate sub customer");
			Log.error(e.toString());
			throw (e);
		}
	}

	public void validateRetentionPeriod(String retention, boolean checkAlert) throws Exception {

		try {
			if (!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."), "Customer Mgmt Tile")
					.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
				findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard")
						.click("Click on Customer Mgmt in Dashboard");
			}

			findElement(create_customer, "Create Customer").click("Click on Create Customer");

			findElement(customer_radio, "Customer Radio Button").click("Select Customer Radio Button");

			findElement(customer_name, "Customer Name").sendKeys("Retention_Cust_" + randomNumber(),
					"Enter Customer Name");

			findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/a"), "Parent Customer")
					.click("Click on Parent Customer");

			findElements(By.xpath("((.//*[@class='custom-select-search'])/../ul)[1]/li"), "Find Customer COunt");

			for (int i = 1; i <= _WebElements.size(); i++) {

				String actual = _WebElements.get(i).getText();

				if (actual.equals("IOT." + CustomerManagement.Cust_Name.toUpperCase())) {

					_WebElements.get(i).click();

					break;
				}

			}

			findElement(retention_time, "Retention Time").sendKeys(retention, "Enter Retension Period");
			findElement(customer_desc, "Customer Description").sendKeys("Test Desc", "Enter Customer Description");
			findElement(By.xpath(".//*[contains(text(),'Business Data')]/input"), "Business Data")
					.click("Select Business Data");
			findElement(roleId, "Role").selectByValue("100", "Select Role");

			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//input[contains(@ng-model,'inherit')]")));
			findElement(By.xpath(".//input[contains(@ng-model,'inherit')]"), "Inherit Check Box")
					.click("Uncheck Inherit Resources");

			Thread.sleep(3000);
			findElement(create, "Create Button").click("Click on Create Button");

			if (checkAlert) {
				String alertTxt = findElement(By.xpath(".//*[@name='sretentionTime']/../span/small"), "Retention Alert")
						.getAttribute(Attributes.TEXT);

				_assert.contains(alertTxt, "invalid", "Verify Retention");
			} else {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
				String actual = findElement(By.xpath(".//*[@class='alert alert-success']"), "Success Alert")
						.getAttribute(Attributes.TEXT);
				_assert.contains(actual, "Success", "Create Sub Group is verified");
			}
		} catch (Exception e) {
			Log.error("Unable to Validate  Retention Period");
			Log.error(e.toString());
			throw (e);
		}

	}

	public void validateInheritCheckForDp() throws Exception {

		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."), "Asset Mgmt Tile")
					.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"), "Asset Mgmt Tile")
						.click("Click on Asset Mgmt Tile");
			}

			findElement(By.id("asset_2"), "Create Asset").click("Click on Create Asset");

			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"), "Customer Drop Down")
					.click("Click on Customer Drop Down");
			Thread.sleep(2000);
			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"), "Customer Drop Down")
			.click("Click on Customer Drop Down");
			Thread.sleep(2000);
			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"), "Customer Drop Down")
			.click("Click on Customer Drop Down");

			
			   FluentWait<WebDriver> waitFluent = new FluentWait<WebDriver>(driver);
				waitFluent.pollingEvery(1, TimeUnit.SECONDS);
				waitFluent.withTimeout(300, TimeUnit.SECONDS);
				waitFluent.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(".//*[@ng-model='deviceDetails.customerId']//*[@ng-hide='searchTerm']"),
						"There are no items to display"));
			
			Thread.sleep(2000);

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

			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),
					"Dp drop down").click("Click on Dp Drop Down");

			parentCustDpCount = findElements(By.xpath("(.//*[@class='custom-select-search'])[3]/../../div/ul/li"),
					"Get DP Count").getCount("Get Dp Count of Parent Customer");

			Log.info("Parent Customer Dp Count is " + parentCustDpCount);

			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"), "Customer Drop Down")
					.click("Click on Customer Drop Down");
			wait.until(ExpectedConditions.invisibilityOfElementWithText(
					By.xpath(".//*[@ng-model='deviceDetails.customerId']//*[@ng-hide='searchTerm']"),
					"There are no items to display"));
			Thread.sleep(2000);
			findElements(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li"), "Get Sub Customer Count");

			for (int i = 1; i <= _WebElements.size(); i++) {

				Thread.sleep(2000);

				String actual = _WebElements.get(i).getText();

				if (actual.trim().contains(Sub_Customer.toUpperCase())) {

					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}

			}
			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),
					"Dp drop down").click("Click on Dp Drop Down");

			int subCustDpCount = findElements(By.xpath("(.//*[@class='custom-select-search'])[3]/../../div/ul/li"),
					"Get DP Count").getCount("Get Dp Count of Sub Customer");

			Log.info("Sub Customer Dp Count is " + subCustDpCount);

			_assert.equals(parentCustDpCount, subCustDpCount, "Verify DP counts for Sub and Parent Customer");

		} catch (Exception e) {

			Log.error("Unable to Validate Inherit Check");
			Log.error(e.toString());
			throw (e);

		}

	}

	public void validateInheritUnCheckForDp() throws Exception {

		try {

			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."), "Asset Mgmt Tile")
					.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"), "Asset Mgmt Tile")
						.click("Click on Asset Mgmt Tile");
			}

			findElement(By.id("asset_2"), "Create Asset").click("Click on Create Asset");

			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"), "Customer Drop Down")
					.click("Click on Customer Drop Down");
			Thread.sleep(2000);
			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"), "Customer Drop Down")
			.click("Click on Customer Drop Down");
			Thread.sleep(2000);
			findElement(By.cssSelector("[ng-model='deviceDetails.customerId']"), "Customer Drop Down")
			.click("Click on Customer Drop Down");

			   FluentWait<WebDriver> waitFluent = new FluentWait<WebDriver>(driver);
						waitFluent.pollingEvery(1, TimeUnit.SECONDS);
						waitFluent.withTimeout(300, TimeUnit.SECONDS);
						waitFluent.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(".//*[@ng-model='deviceDetails.customerId']//*[@ng-hide='searchTerm']"),
								"There are no items to display"));
			Thread.sleep(2000);

			findElements(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li"), "Get Customer Count");

			for (int i = 1; i <= _WebElements.size()-2; i++) {

				Thread.sleep(2000);

				String actual = _WebElements.get(i).getText();

				if (actual.trim().contains("RETENTION_CUST")) {

					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}

			}

			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),
					"Dp drop down").click("Click on Dp Drop Down");

			_assert.contains(findElement(By.xpath("(.//span[@ng-hide='searchTerm'])[3]"), "Dp List Message")
					.getAttribute(Attributes.TEXT), "no items", "Check of 'No Items' message in Dp");
		} catch (Exception e) {
			Log.error("Unable to Validate Inherit Uncheck");
			Log.error(e.toString());
			throw (e);
		}
	}

	public void validateInheritCheckForCp() {

		try {
			findElement(By.xpath(".//span[text()='Device Manufacturer Profile']"), "DP tile").click("Click on DP");
			findElement(By.id("deviceP_1"), "Create").click("Click on Create");
			
			findElement(By.cssSelector("[ng-model='profileId']"),"Container Drop Down").click("Click on Container Drop Down");

			int containerCount = findElements(By.xpath(".//select[@ng-model='profileId']/option"),
					"Container Drop Down").getCount("Get Container Drop Down Count");

			_assert.greaterThan(containerCount, 1, "Check if Container Count is Greater Than 1");

		} catch (Exception e) {
			Log.error("Unable to validate In");
			Log.error(e.toString());
			throw (e);
		}
	}

	public void validateAssetMoveToDiffGroup() throws Exception {
		try {
			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."), "Asset Mgmt Tile")
					.getAttribute(Attributes.GENERAL, "class").contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"), "Asset Mgmt Tile")
						.click("Click on Asset Mgmt Tile");
			}

			findElement(By.id("asset_3"), "Manage Asset").click("Click on Manage Asset");

			findElement(By.xpath(".//table[@st-table='assetList']/tbody/tr[1]/td[9]/a"),
					"Edit Link of First displayed asset").click("Click on Edit Link");

			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")));

			findElement(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a"), "Group Drop Down")
					.click("Click on Group Drop Down");

			findElements(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/ul/li"), "Get Group Count");

			Thread.sleep(2000);

			for (int i = 1; i <= _WebElements.size(); i++) {

				Thread.sleep(2000);

				String actual = _WebElements.get(i).getText();

				if (actual.trim().contains("DEFAULT")) {

					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}

			}

			findElement(By.xpath(".//button[text()='Update']"), "Update Button").click("Click on Update Button");

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success ng-scope']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success ng-scope']"), "Success Alert")
					.getAttribute(Attributes.TEXT);

			_assert.contains(actual, "Success", "Verify Success");

			findElement(By.id("asset_3"), "Manage Asset").click("Click on Manage Asset");

			_assert.contains(
					findElement(By.xpath(".//table[@st-table='assetList']/tbody/tr[1]/td[4]"),
							"Group Name in Manage Asset").getAttribute(Attributes.TEXT),
					"DEFAULT", "Verify if device is updated to new group");

		} catch (Exception e) {
			Log.error("Unable validate of an asset can be moved to different group");
			Log.error(e.toString());
			throw (e);
		}
	}

}
