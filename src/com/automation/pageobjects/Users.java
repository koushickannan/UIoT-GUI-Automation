package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class Users extends ElementBase {

	public static String Theme_Name = null;

	final static Logger Log = Logger.getLogger(Users.class.getName());

	// Generic Elements
	By users_link = By.xpath(".//span[text()='Users']");
	By users_devices_groups_link = By.id("users_2");
	By customer_mgmt_dash = By.xpath(".//*[text()='Customer Mgmt.']");

	// Create Role
	/*
	 * By mygroupdetails = By.xpath(".//*[@title='My Group Details']"); By
	 * role_plus = By.xpath(".//*[@class='standard-icons iot_cursor_pointer']");
	 */
	By role_container = By.cssSelector("#roleContainer>div>div");
	By create_role = By.xpath(".//*[@href='/dsm/angular/roleMgmt/#']/div/span");

	// Create Theme
	By theme_mgmt = By.xpath(".//*[@href='/dsm/thememgmt/themeDetails.htm']");
	By add_theme = By.cssSelector(".fa.fa-plus.m-r-sm");
	By theme_name = By.id("userName");
	By app_header = By.xpath(".//*[text()='Application Header']/..//div//div//input");
	By page_header = By.xpath(".//*[text()='Page Header']/..//div//div//input");
	By create = By.xpath(".//*[text()='Create']");

	// Update Tenant
	By search_tenant = By.id("cust_2");
	By tenant_select = By.id("groupId");
	By tenant_name = By.id("groupName");
	By pencil_tenant = By.xpath(".//*[starts-with(@href,'/dsm/usermgmt/updateGroup')]");
	By update_tenant = By.xpath(".//button[text()='Update']");

	// Search User
	By search_button = By.xpath(".//*[@id='adSearchUser']/div/div[2]/div/button[1]");
	By tenant_select_user = By.id("groupId");
	By pencil = By.cssSelector(".fa.fa-pencil");
	By pencil_2 = By.xpath(".//*[starts-with(@href,'/dsm/usermgmt/updateUser')]");
	By update_button = By.xpath(".//input[@value='Update']");
	By search_icon = By.cssSelector(".icon.icon-search");
	By search_users = By.id("users_2");
	By username = By.id("username");
	// Test_User_Automation_5969

	// Pagination
	By active_page = By.cssSelector(".activepg");
	By page_count = By.cssSelector(".pagination.pull-right>a");
	By page_individial = By.xpath(".//*[@title='Go to page 2']");

	// Edit User with Role
	By editIcon = By.cssSelector("[class='fa fa-pencil']");

	public void editUserWithRole(String roleProperty) throws Exception {
		try {

			findElement(By.name("searchAction"), "Search Drop Down").selectByValue("user", "Select User");
			findElement(By.name("searchText"), "Search Box").sendKeys(prop.getProperty("cust_username").toLowerCase(),
					Keys.ENTER, "Enter User Name");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("cust_username").toLowerCase())));

			wait.until(ExpectedConditions.elementToBeClickable(editIcon));
			findElement(editIcon, "Edit Icon").click("Click on User Edit Icon");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-model='userDetails.roleId']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'userDetails.roleId')]")));
			
			Thread.sleep(3000);
			findElement(By.xpath(".//div[contains(@ng-model,'userDetails.roleId')]"), "Select drop down").click("Click on Role drop down");;
			
			Log.info("Role Name from Property file is "+roleProp.getProperty(roleProperty).toUpperCase() );

			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down")
					.sendKeys(roleProp.getProperty(roleProperty).toUpperCase(), "Enter Role Name");
			Thread.sleep(3000);
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List")
					.click("Click on Role Drop Down List");

			findElement(By.cssSelector("[value='Update']"), "Update Button").click("Click on Update Button");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alert alert-success']")));
			_assert.contains(
					findElement(By.cssSelector("[class='alert alert-success']"), "Success Alert")
							.getAttribute(Attributes.TEXT),
					"Success", "Verify User Update for role " + roleProp.getProperty(roleProperty));

		} catch (Exception e) {
			Log.error("Unable to Edit User with Role");
			Log.error(e.toString());
			throw (e);
		}
	}
	
	public void editGroupUserWithRole(String roleProperty) throws Exception {
		try {

			findElement(By.name("searchAction"), "Search Drop Down").selectByValue("user", "Select User");
			findElement(By.name("searchText"), "Search Box").sendKeys(prop.getProperty("new_user_name").toLowerCase(),
					Keys.ENTER, "Enter User Name");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("new_user_name").toLowerCase())));

			wait.until(ExpectedConditions.elementToBeClickable(editIcon));
			findElement(editIcon, "Edit Icon").click("Click on User Edit Icon");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-model='userDetails.roleId']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'userDetails.roleId')]")));
			
			Thread.sleep(3000);
			findElement(By.xpath(".//div[contains(@ng-model,'userDetails.roleId')]"), "Select drop down").click("Click on Role drop down");;
			
			Log.info("Role Name from Property file is "+roleProp.getProperty(roleProperty).toUpperCase() );

			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down")
					.sendKeys(roleProp.getProperty(roleProperty).toUpperCase(), "Enter Role Name");
			Thread.sleep(3000);
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List")
					.click("Click on Role Drop Down List");

			findElement(By.cssSelector("[value='Update']"), "Update Button").click("Click on Update Button");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alert alert-success']")));
			_assert.contains(
					findElement(By.cssSelector("[class='alert alert-success']"), "Success Alert")
							.getAttribute(Attributes.TEXT),
					"Success", "Verify User Update for role " + roleProp.getProperty(roleProperty));

		} catch (Exception e) {
			Log.error("Unable to Edit User with Role");
			Log.error(e.toString());
			throw (e);
		}
	}

	public void Apply_Theme() throws InterruptedException {
		if (!findElement(By.xpath("(.//span[text()='Users'])/.."),"Users Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")) {
			findElement(users_link, "Users link in Dashboard").click("Click on Users link in Dashboard");
		}
		findElement(theme_mgmt, "Theme Mgmt").click("Click on Theme Mgmt");

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#themeContainer>div>div:nth-child(2)>div")));

		findElement(By.xpath("(.//*[@class='previewcontainer'])[1]"), "User Created Theme")
				.moveToElement("Mouse Hover to Theme");

		// wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#themeContainer>div>div:nth-child(2)>div>ul>li>div>div:nth-child(2)>div:nth-child(1)>button:nth-child(1)")));

		findElement(By.xpath("(.//*[@class='previewcontainer'])[1]//div[1]//button[1]"), "Assign Button")
				.click("Click on Assign");

		// findElement(By.xpath(".//*[@placeholder='Search']"), "Tenant
		// Search").sendKeys(SmokeTest.tenant.toUpperCase().trim(),Keys.ENTER,
		// "Enter Tenant Name");
		Thread.sleep(2000);
		findElement(By.cssSelector(".treeview>li>input"), "Tenant Check Box").click("Click on Tenant Check Box");

		findElement(By.xpath(".//button[text()='Apply']"), "Apply Button").click("Click on Apply Button");
	}

	public void Create_Theme() {
		if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
			findElement(users_link, "Users link in Dashboard").click("Click on Users link in Dashboard");
		}
		findElement(theme_mgmt, "Theme Mgmt").click("Click on Theme Mgmt");
		findElement(add_theme, "Add Theme").click("Click on Add Theme");

		Theme_Name = "Automation_Theme_" + randomNumber();

		findElement(theme_name, "Theme Name").sendKeys(Theme_Name, "Enter Theme Name");

		findElement(app_header, "Application Header").sendKeys(
				"rgb(" + randomNumber() + "," + randomNumber() + "," + randomNumber() + ")",
				"Enter Application Header RGB Values");
		findElement(page_header, "Page Header").sendKeys(
				"rgb(" + randomNumber() + "," + randomNumber() + "," + randomNumber() + ")",
				"Enter Page Header RGB Values");

		findElement(create, "Create Button").click("Click on Create Button");

	}

	public void Verify_Theme(String Expected) {

		// .//*[@class='alert alert-success']//b//span[2]

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']//b//span[2]")));
		String actual = findElement(By.xpath(".//*[@class='alert alert-success']//b//span[2]"),"Success Message").getAttribute(Attributes.TEXT);

		_assert.contains(actual, Expected, "Verify Create Theme Success Message");

	}

	public void Create_Role(String roleName) {

		findElement(create_role, "Role Mgmt").click("Click on Role Mgmt");

		findElements(role_container, "Role Table");
		int role_count = _WebElements.size();

		for (int count = 1; count <= role_count; count++) {

			String desc = findElement(By.cssSelector("#roleContainer>div>div:nth-child(" + count + ")>div>h3"),
					"Text of Role").getAttribute(Attributes.TEXT);

			if (desc.trim().contains(roleName)) {

				findElement(By.cssSelector("#roleContainer>div>div:nth-child(" + count + ")>div>h3"), "Text of Role")
						.click("Click on " + roleName);

				findElement(
						By.cssSelector(
								"#roleContainer>div>div:nth-child(" + count + ")>div>div>ul>li:nth-child(1)>div"),
						"List Click").click("Click on List Element");
				;

				break;
			}
		}

		findElement(By.id("roleName"), "Role Name").sendKeys("Rule_Automation", "Enter Role Name");
		findElement(By.id("roleDesc"), "Role Name").sendKeys("Device", "Enter Role Desc Name");
		findElement(By.xpath(".//button[contains(text(),'Create')]"), "Save Role").click("Click on Save Role");

	}

	public void Search_Group() {
		if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
				.contains("selected")) {
			findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
		}
		findElement(search_tenant, "Search Tenant").click("Click on Search Tenant");
		findElement(By.xpath(".//*[contains(@ng-class,'Group')]"), "Group Tab").click("Click on Group Tab");
		findElement(search_icon, "Quick Search").click("Click on Quick Search");

		findElement(By.name("groupname"), "Group Name ").sendKeys(SmokeTest.Group, "Enter Group Name");
		// findElement(tenant_name, "Tenant
		// Name").sendKeys(SmokeTest.tenant.toUpperCase(),Keys.ENTER,"Enter
		// Tenant Name");
		findElement(By.xpath(".//*[@ng-model='searchObj.search.includeSubGroups']"), "Sub Group")
				.click("Click on Include Sub Groups");
		findElement(By.xpath(".//*[@value='Search']"), "Search Button").click("Click on Search");
	}

	public void Verify_Search_Tenant() {

		findElements(By.xpath(".//table[@st-table='entityList']/tbody/tr"), "Search Table");
		int table_count = _WebElements.size();

		if (table_count == 2) {

			String name = findElement(By.cssSelector(".//table[@st-table='entityList']/tbody/tr[1]/td[2]/a"),
					"Tenant Name").getAttribute(Attributes.TEXT);

			_assert.equals(name.trim(),
					"IOT." + CustomerManagement.Cust_Name.toUpperCase() + SmokeTest.Group.trim().toUpperCase(),
					"Verifying Valid Tenant Search");
		}
	}

	public void Update_Group() throws InterruptedException {
		if (!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class")
				.contains("selected")) {
			findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
		}
		findElement(search_tenant, "Search Tenant").click("Click on Search Tenant");
		findElement(By.xpath(".//*[contains(@ng-class,'Group')]"), "Group Tab").click("Click on Group Tab");
		findElement(search_icon, "Quick Search").click("Click on Quick Search");

		findElement(By.name("groupname"), "Group Name ").sendKeys(SmokeTest.Group, "Enter Group Name");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[@ng-model='searchObj.search.includeSubGroups']")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(".//*[@ng-model='searchObj.search.includeSubGroups']")));
		findElement(By.xpath(".//*[@ng-model='searchObj.search.includeSubGroups']"), "Sub Group Check Box")
				.click("Click on Include Sub Groups");

		findElement(By.xpath(".//*[@value='Search']"), "Search Button").click("Click on Search");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[@class='fa fa-pencil'])[1]")));
		findElement(By.xpath("(.//*[@class='fa fa-pencil'])[1]"), "Edit").click("Click on Edit Button");
		Thread.sleep(3000);
		findElement(update_tenant, "Update Button").click("Click on Update Button");

	}

	public void User_Update() throws InterruptedException {
		if (!findElement(By.xpath("(.//span[text()='Users'])/.."),"Users Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")) {
			findElement(users_link, "Users link in Dashboard").click("Click on Users link in Dashboard");
		}
		findElement(search_users, "Search User link in Dashboard").click("Click on Search User Link");
		findElement(search_icon, "Search Icon").click("Click on Search Icon");

		findElement(By.xpath(".//*[contains(@ng-model,'includeSubGroups')]"), "Sub Group")
				.click("Click on Include Sub Groups");

		// findElement(tenant_select_user, "Tenant Drop
		// Down").selectByValue(SmokeTest.CustID, "Selecting Customer");
		findElement(By.name("username"), "User Name").sendKeys(prop.getProperty("new_user_name"), "Enter User Name");
		findElement(By.xpath(".//*[@value='Search']"), "Search Button").click("Click on Search");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(pencil));
		findElement(pencil, "Pencil Icon").click("Click on Pencil Icon");
		Thread.sleep(3000);
		findElement(update_button, "Update Button").click("Click on Update Button");

	}

	public void Verify_Update(String Expected) {
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//*[contains(@class,'alert alert-success')]")));
			String actual = findElement(By.xpath(".//*[contains(@class,'alert alert-success')]/span"),
					"Success message Text").getAttribute(Attributes.TEXT);

			_assert.contains(actual, Expected, "Verify User Update");

		} catch (Exception e) {

			Log.error("Unable to find Success Message - Update is Failed");
			Log.error(e.getMessage().toString());
			throw (e);
		}

	}

	public void Verify_Pagination_Users() throws InterruptedException {
		if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
			findElement(users_link, "Users link in Dashboard").click("Click on Users link in Dashboard");
		}
		findElement(users_devices_groups_link, "Users Devices & Groups").click("Click on Users Devices & Groups");

		Thread.sleep(2000);
		// findElements(By.cssSelector("#srchUser>tbody>tr"), "Users row
		// count");

		String ucount = findElement(By.xpath(".//div[contains(@class,'totaldata')]"), "Users Count")
				.getAttribute(Attributes.TEXT);

		if (Integer.parseInt(ucount) > 10) {

			String current_page = findElement(By.xpath(".//li[@class='ng-scope active']/a"), "Active Page")
					.getAttribute(Attributes.TEXT);

			Log.info("Active Page " + current_page);
			findElement(By.xpath(".//li[@class='ng-scope active']/following-sibling::li[1]"), "Next Page")
					.click("Click on Next Page");

			String next_page = findElement(By.xpath(".//li[@class='ng-scope active']/a"), "Active Page")
					.getAttribute(Attributes.TEXT);
			Log.info("Next Page " + next_page);

			_assert.equals(Integer.parseInt(next_page), Integer.parseInt(current_page) + 1, "Verify Pagination");
		} else {

			Log.info("There are no pages to verify , hence passing the test case");

		}
	}

	public void Users_Subsystem() throws InterruptedException {

		try {
			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class")
					.contains("selected")) {
				findElement(users_link, "Users link in Dashboard").click("Click on Users link in Dashboard");
			}

			findElement(By.id("users_3"), "Create User").click("Click on Create User");

			Thread.sleep(2000);

			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down")
					.click("Click on Customer Dropdown");

			findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li[2]"), "Select Customer")
					.click("Select Customer");
			;

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(.//*[@id='iotSubSystems'])[2]")));

			String subsys = "", isCheckBoxChecked = "";

			subsys = findElement(By.xpath("(.//*[@id='iotSubSystems'])[2]"), "Partner Mgmt")
					.getAttribute(Attributes.GENERAL, "sysname");
			_assert.contains(subsys, "Partner Management", "Verify Subsystem 1");
			Log.info("Subsystem Found is " + subsys);

			subsys = findElement(By.xpath("(.//*[@id='iotSubSystems'])[3]"), "Smart Interaction Designer")
					.getAttribute(Attributes.GENERAL, "sysname");
			_assert.contains(subsys, "Smart Interaction Designer", "Verify Subsystem 2");
			Log.info("Subsystem Found is " + subsys);

			subsys = findElement(By.xpath("(.//*[@id='iotSubSystems'])[4]"), "Orbiwise")
					.getAttribute(Attributes.GENERAL, "sysname");
			_assert.contains(subsys, "Orbiwise", "Verify Subsystem 3");
			Log.info("Subsystem Found is " + subsys);

			isCheckBoxChecked = findElement(By.xpath("(.//*[@id='iotSubSystems'])[1]"), "Partner Mgmt")
					.getAttribute(Attributes.GENERAL, "class");
			_assert.contains(isCheckBoxChecked, "ng-not-empty", "Verify Subsystem 1 is checked by default");
			Log.info("Partner Mgmt is Checked By Default ");

			isCheckBoxChecked = findElement(By.xpath("(.//*[@id='iotSubSystems'])[2]"), "Partner Mgmt Checkbox")
					.getAttribute(Attributes.GENERAL, "class");
			_assert.contains(isCheckBoxChecked, "ng-not-empty", "Verify Subsystem 1 is checked by default");
			Log.info("Smart Interaction is Checked By Default ");

			isCheckBoxChecked = findElement(By.xpath("(.//*[@id='iotSubSystems'])[4]"), "Orbiwise")
					.getAttribute(Attributes.GENERAL, "class");
			_assert.contains(isCheckBoxChecked, "ng-not-empty", "Verify Subsystem 1 is checked by default");
			Log.info("Orbiwise is Checked By Default ");

		} catch (Exception e) {

			Log.error("Unable to Verify Users Sub-System");
			Log.error(e.toString());
			throw (e);
		}

	}

	public void EditUser_Subsytem() throws Exception {
		try {

			new Select(driver.findElement(By.name("searchAction"))).selectByValue("user");
			driver.findElement(By.name("searchText")).sendKeys(prop.getProperty("new_user_name").toLowerCase(),
					Keys.ENTER);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("new_user_name"))));

			findElement(By.xpath(".//*[@class='fa fa-pencil']"), "Pencil").click("Click on Pencil Icon");
			Thread.sleep(3000);

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(.//*[@id='iotSubSystems'])[2]")));

			String subsys = "";

			subsys = findElement(By.xpath("(.//*[@id='iotSubSystems'])[2]"), "Partner Mgmt")
					.getAttribute(Attributes.GENERAL, "sysname");
			_assert.contains(subsys, "Partner Management", "Verify Subsystem 1");
			Log.info("Subsystem Found is " + subsys);

			subsys = findElement(By.xpath("(.//*[@id='iotSubSystems'])[3]"), "Smart Interaction Designer")
					.getAttribute(Attributes.GENERAL, "sysname");
			_assert.contains(subsys, "Smart Interaction Designer", "Verify Subsystem 2");
			Log.info("Subsystem Found is " + subsys);

			subsys = findElement(By.xpath("(.//*[@id='iotSubSystems'])[4]"), "Orbiwise")
					.getAttribute(Attributes.GENERAL, "sysname");
			_assert.contains(subsys, "Orbiwise", "Verify Subsystem 3");
			Log.info("Subsystem Found is " + subsys);

		} catch (Exception e) {
			Log.error("Unable to Verify Edit Users Subsystem");
			Log.error(e.toString());
			throw (e);
		}
	}

}
