/**
 * 
 */
package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

/**
 * @author aloksu
 *
 */
public class Security_Filter extends ElementBase{
	
	final static Logger Log = Logger.getLogger(Routing_Rules.class.getName());
	
	By roleMgmtTile = By.xpath(".//*[text()='Role Management']");
	By accordion = By.cssSelector("[class='accordion']>div:nth-child(1)");
	By roleNameTextBox=By.id("roleName");
	By roleDescTextBox=By.id("roleDesc");
	By createButton = By.xpath(".//button[contains(text(),'Create')]");
	By successAlert = By.cssSelector("[ng-show='serverSuccess']");
	
	By Policy_Box = By.xpath("//*[@name='createQoS']");
	By PolicyDesc_Box = By.xpath("//*[@ng-model='policydescription']");
	By Definition_Box = By.xpath("//*[@ng-model='definition.key']");
	By Value_Box = By.xpath("//*[@ng-model='definition.value']");
	By Description_Box = By.xpath("//*[@ng-model='definition.description']");
	By Create_Button = By.id("createpolicy");
	By Name_Link = By.xpath("(//*[@class='ng-binding'])[1]");
	By Policy_link = By.xpath("//*[@st-table='qosList']/tbody/tr/td[1]/a");
	By Edit_Icon = By.xpath("//*[@st-table='qosList']/tbody/tr/td[4]/a");
	
	By App_Link = By.xpath("//*[@class='ng-scope']/td[2]/a");
	By AppEdit_Icon = By.xpath("//*[@class='ng-scope']/td[8]/a");
	By CreateResource_Link = By.xpath("//*[@class='ng-scope']/td[5]/a");
	By Subscription_Link = By.xpath("//*[@class='ng-scope']/td[6]/a");
	
	By alert_Success = By.xpath("//*[@class='alert alert-success']");
	By alert_Notification = By.xpath("/html/body/h3/b");
	By page_Title = By.xpath("//*[@id='currentPage']");
	
	//Create Customer locators
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
	By createCustomer_Button = By.xpath(".//button[text()='Create']");
	By createUser_Button = By.xpath(".//*[@value='Create']");
	By createProfile_Button = By.xpath("(//*[@type='submit'])[2]");
	
	By AssetName_Box = By.xpath("//*[@name='resourceName']");
	By AssetType_Dropdown = By.id("resourceType");
	By DeAddr_Box = By.id("iotParamsDevAddr");
	By include_checkbox = By.xpath(".//*[@ng-model='searchObj.search.includeSubGroups']");
	
	By groupDropDownLink = By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a");
	By groupSearchInput = By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/div/input");
	By groupSearchSelect = By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/ul/li[2]");
	
	By PoolType_Dropdown = By.xpath("(.//*[@ng-change='getAvailablePoolInfo()'])[1]");
	By Customer_Dropdown = By.xpath("(.//*[@ng-change='getAvailablePoolInfo()'])[2]");
	By Createpool_button = By.xpath(".//*[@class='btn grommetux-button-primary']");
	
	public final String ViewPolicytitle = "View QoS Policy";
	public final String EditPolicytitle = "Edit QoS Policy";
	public final String ViewApplicationtitle = "View Application";
	public final String EditApplicationtitle = "Edit Application";
	public final String CreateResourcetitle = "Create Resource Group";
	public final String Subscriptionstitle = "Subscriptions";
	public final String UpdateRuletitle = "Update Rule";
	public final String GroupDetailstitle = "Group Details";
	public final String EditCutomartitle = "Update Customer/Group";
	public final String SearchGrouptitle = "Search Customer / Group";
	
	//Role Search
	By searchRole = By.cssSelector("[placeholder='Search']");
	By searchRoleList = By.xpath(".//*[contains(@ng-repeat,'roleSearch')]");
	By searchRoleEditIcon = By.xpath(".//*[contains(@ng-repeat,'roleSearch')]/span[3]/i");
	
	String testUrl = null;
	String pageUrl = null;
	String roleName = null;
	public static String Cust_Name = null;
	public static String Group_Name = null;
	public static String Asset_Name = null;
	public static String ACP_Name = null;
	public static String AddPool_Name = null;
	
	
       public void CreateCustomerAsAdmin_Security() throws InterruptedException{
    	   
		try{
		if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			Thread.sleep(1000);
		    findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
		}
		findElement(create_customer, "Create Customer").click("Click on Create Customer");
		Cust_Name = "Cust_Automation_"+randomNumber();
		findElement(customer_radio, "Customer Radio Button").click("Select Customer Radio Button");
		findElement(customer_name, "Customer Name").sendKeys(Cust_Name, "Enter Customer Name");
		findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/a"), "Parent Customer").click("Click on Parent Customer");
		findElements(By.xpath(".//*[contains(@ng-model,'parentId')]/div/ul/li"), "Find Customer Count");
		
		for(int i=1 ; i<=_WebElements.size();i++){
			wait.until(ExpectedConditions.elementToBeClickable(_WebElements.get(i)));
			String actual = _WebElements.get(i).getText();
			if(actual.equals("IoT")){
				_WebElements.get(i).click();
				break;
			}	
		}
		findElement(retention_time, "Retension Time").sendKeys(prop.getProperty("retention_period"),"Enter Retension Period");
		findElement(customer_desc, "Customer Description").sendKeys("Test Desc", "Enter Customer Description");
		findElement(By.xpath(".//*[contains(text(),'Business Data')]/input"),"Business Data").click("Select Business Data");
		findElement(By.cssSelector("[ng-model='groupDetails.inherit']"),"Inherit Resources").click("Uncheck Inherit Resources");
		findElement(roleId, "Role").selectByValue("100", "Select Role");
		
		Thread.sleep(3000);
		findElement(createCustomer_Button, "Create Button").submit("Submit on Create Button");
		Log.info("Customer Create : " + Cust_Name);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
		String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
		_assert.contains(actual, "successfully", "Verify Create Customer Successs Message");
		
		}catch(Exception e){
   			Log.error("Not able to create new Customer");
   			throw(e);
   	}
	}
     
       public void CreateChildCustomer_Security() throws InterruptedException{
   		
    	try{
   		   if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
   			   Thread.sleep(2000);
   		       findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard"); 
   		 }
   		   
   		findElement(create_customer, "Create Customer").click("Click on Create Customer");
   		Cust_Name = "ChildCust_Auto_"+randomNumber();
   		findElement(customer_radio, "Customer Radio Button").click("Select Customer Radio Button");
   		findElement(customer_name, "Customer Name").sendKeys(Cust_Name, "Enter Customer Name");
   		findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/a"), "Parent Customer").click("Click on Parent Customer");
   		findElements(By.xpath(".//*[contains(@ng-model,'parentId')]/div/ul/li"), "Find Customer Count");
   		Thread.sleep(2000);
		findElement(By.xpath(".//*[@role='menu']/li[2]/a"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
		findElement(By.id("roleId"),"Find Role Dropdown").click("Click to expand Role dropdown");
		Thread.sleep(3000);
		findElement(By.id("roleId"),"Find Role").selectByIndex(1, "Select Role from dropdown");
		findElement(By.xpath(".//*[@ng-model='groupDetails.description']"),"Find Description").sendKeys("Child Customer Desc", "Enter description");
		Thread.sleep(2000);
		findElement(createCustomer_Button, "Create Button").submit("Submit on Create Button");
		Log.info("Customer Create : " + Cust_Name);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
		String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
		_assert.contains(actual, "successfully", "Verify Create Customer Successs Message");
		
    	}catch(Exception e){
   			Log.error("Not able to create child Customer");
   			throw(e);
   	}
   	}
       
       public void FirstCustUser_Security() throws Exception{
   		
   		try{
   			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
				Thread.sleep(1500);
				driver.findElement(By.xpath(".//span[text()='Users']")).click();
				Thread.sleep(2000);
			}
			findElement(By.id("users_3"), "Create User").click("Click on Create User");

			Thread.sleep(1000);
			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");
			
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
			findElement(By.id("firstName"), "First Name").sendKeys("cust", "Enter First Name");
			findElement(By.id("lName"), "Last Name").sendKeys("user", "Enter Last Name");
			findElement(By.name("email"), "Email").sendKeys("auto_user_" + randomNumber() + "@test.com", "Enter Email Id");
			findElement(By.id("phoneNumber"), "Phone Number").sendKeys("6473443837", "Enter Phone Number");
			
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down").click("Click on Role Drop Down");
            findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");
	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
	        
	        Thread.sleep(2000);
	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
			
	        String newuser = "securitycustuser" + randomNumber();
			Log.info("Customer User Created : " + newuser);
			securityProp.setProperty("customer_User1", newuser.toLowerCase());

			findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
			findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
			findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"), "Confirm Password");
			findElement(createUser_Button, "Create Button").submit("Submit on Create Button");
			Log.info("Customer Create : " + Cust_Name);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
			_assert.contains(actual, "successfully", "Verify Create User Successs Message");
			
   		}catch(Exception e){
   			Log.error("Not able to create new Customer User");
   			throw(e);
   	}
     } 
       
       public void SecondCustUser_Security() throws Exception{
      		
      		try{
      			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
   				Thread.sleep(1500);
   				driver.findElement(By.xpath(".//span[text()='Users']")).click();
   				Thread.sleep(2000);
   			}
   			findElement(By.id("users_3"), "Create User").click("Click on Create User");

   			Thread.sleep(1000);
   			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");
   			
   			Thread.sleep(2000);
   			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
   			Thread.sleep(2000);
   			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
   			findElement(By.id("firstName"), "First Name").sendKeys("cust", "Enter First Name");
   			findElement(By.id("lName"), "Last Name").sendKeys("user", "Enter Last Name");
   			findElement(By.name("email"), "Email").sendKeys("auto_user_" + randomNumber() + "@test.com", "Enter Email Id");
   			findElement(By.id("phoneNumber"), "Phone Number").sendKeys("6473443837", "Enter Phone Number");
   			
   			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down").click("Click on Role Drop Down");
               findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");
   	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
   	        
   	        Thread.sleep(2000);
   	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
   			
   	        String newuser = "securitycustuser" + randomNumber();
   			Log.info("Customer User Created : " + newuser);
   			securityProp.setProperty("customer_User2", newuser.toLowerCase());

   			findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
   			findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
   			findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"), "Confirm Password");
   			findElement(createUser_Button, "Create Button").submit("Submit on Create Button");
   			Log.info("Customer Create : " + Cust_Name);
   			
   			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
   			String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
   			_assert.contains(actual, "successfully", "Verify Create User Successs Message");
   			
      		}catch(Exception e){
      			Log.error("Not able to create new Customer User");
      			throw(e);
      	}
        } 
       
       public void ChildCustUser_Security() throws Exception{
     		
     		try{
     			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
  				Thread.sleep(1500);
  				driver.findElement(By.xpath(".//span[text()='Users']")).click();
  				Thread.sleep(2000);
  			}
  			findElement(By.id("users_3"), "Create User").click("Click on Create User");

  			Thread.sleep(1000);
  			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");
  			/*
  			Thread.sleep(2000);
  			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");*/
  			Thread.sleep(2000);
  			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[3]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
  			findElement(By.id("firstName"), "First Name").sendKeys("childcust", "Enter First Name");
  			findElement(By.id("lName"), "Last Name").sendKeys("user", "Enter Last Name");
  			findElement(By.name("email"), "Email").sendKeys("child_user_" + randomNumber() + "@test.com", "Enter Email Id");
  			findElement(By.id("phoneNumber"), "Phone Number").sendKeys("6473443837", "Enter Phone Number");
  			
  			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down").click("Click on Role Drop Down");
              findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");
  	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
  	        
  	        Thread.sleep(2000);
  	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
  			
  	        String newuser = "childcustuser" + randomNumber();
  			Log.info("Child Customer User Created : " + newuser);
  			securityProp.setProperty("child_CustomerUser", newuser.toLowerCase());

  			findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
  			findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
  			findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"), "Confirm Password");
  			findElement(createUser_Button, "Create Button").submit("Submit on Create Button");
  			Log.info("Customer Create : " + Cust_Name);
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
  			String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
  			_assert.contains(actual, "successfully", "Verify Create User Successs Message");
  			
     		}catch(Exception e){
     			Log.error("Not able to create new Child Customer User");
     			throw(e);
     	}
       }
       
       public void CreateUser_Security() throws Exception{
    		
    		try{
    			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
 				Thread.sleep(1500);
 				driver.findElement(By.xpath(".//span[text()='Users']")).click();
 				Thread.sleep(2000);
 			}
 			findElement(By.id("users_3"), "Create User").click("Click on Create User");

 			Thread.sleep(1000);
 			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");
 			/*
 			Thread.sleep(2000);
 			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");*/
 			Thread.sleep(2000);
 			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
 			findElement(By.id("firstName"), "First Name").sendKeys("newcust", "Enter First Name");
 			findElement(By.id("lName"), "Last Name").sendKeys("user", "Enter Last Name");
 			findElement(By.name("email"), "Email").sendKeys("new_user_" + randomNumber() + "@test.com", "Enter Email Id");
 			findElement(By.id("phoneNumber"), "Phone Number").sendKeys("6473443837", "Enter Phone Number");
 			
 			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down").click("Click on Role Drop Down");
             findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");
 	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
 	        
 	        Thread.sleep(2000);
 	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
 			
 	        String newuser = "AutoUser" + randomNumber();
 			Log.info("Child Customer User Created : " + newuser);
 			//securityProp.setProperty("child_CustomerUser", newuser.toLowerCase());
            
 			findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
 			findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
 			findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"), "Confirm Password");
 			findElement(createUser_Button, "Create Button").submit("Submit on Create Button");
 			Log.info("Customer Create : " + Cust_Name);
 			
 			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
 			String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
 			_assert.contains(actual, "successfully", "Verify Create User Successs Message");
 			
    		}catch(Exception e){
    			Log.error("Not able to create new Customer User");
    			throw(e);
    	}
      }
       
       public void ViewUser_Security() throws Exception{
    		
    		try{
    			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
 				Thread.sleep(1500);
 				driver.findElement(By.xpath(".//span[text()='Users']")).click();
 			}
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("users_2")));
 			findElement(By.id("users_2"), "Search Users").click("Click on Search Users");
 			Thread.sleep(1000);
 			findElement(By.xpath("//*[@ng-repeat='data in userList track by $index']/td[2]/a"), "Find created User").click("Click on User Name");
 			
    		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
    		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Search Users", "Verifying page title");
    		
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
    		}catch(Exception e){
    			Log.error("Not able to view User details");
    			throw(e);
    	}
      }
       
       public void EditUser_Security() throws Exception{
   		
   		try{
   			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
				Thread.sleep(1500);
				findElement(By.xpath(".//span[text()='Users']"),"Find Users tab").click("Click on Users");
			}
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("users_2")));
			findElement(By.id("users_2"), "Search Users").click("Click on Search Users");
			Thread.sleep(1000);
			findElement(By.xpath("//*[@ng-repeat='data in userList track by $index']/td[9]/a"), "Find Edit button").click("Click on Edit button");
			
   		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
   		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Search Users", "Verifying page title");
   		
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
   		}catch(Exception e){
   			Log.error("Not able to view User details");
   			throw(e);
   	}
     }
       
       public void GroupDetails_Security() throws Exception{
      		
      		try{
      			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
   				Thread.sleep(1500);
   				driver.findElement(By.xpath(".//span[text()='Users']")).click();
   			}
      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("users_7")));
   			findElement(By.id("users_7"), "My Group Details").click("Click on My Group Details");
   			
      		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
      		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
   			_assert.equals(pageName, "Group Details", "Verifying page title");
      		
   			Log.info("Retrieving current page url");
   			pageUrl = driver.getCurrentUrl();
   			System.out.println(pageUrl);
   			logout();
      		}catch(Exception e){
      			Log.error("Not able to view User details");
      			throw(e);
      	}
        }
       
       public void CreateUserProfile_Security() throws Exception{
    		
    		try{
    			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
 				Thread.sleep(1500);
 				driver.findElement(By.xpath(".//span[text()='Users']")).click();
 				Thread.sleep(2000);
 			}
 			findElement(By.id("users_9"), "Create User Profile").click("Click on Create User Profile");
 			
 			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
      		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
   			_assert.equals(pageName, "Create User Profile", "Verifying page title");

 			String UserProfile = "Auto_Profile_" + randomNumber();
 			
 			findElement(By.xpath("//*[@placeholder='User Profile Name']"), "User Profile Name").sendKeys(UserProfile, "Enter Profile Name");;
 			
 			Thread.sleep(2000);
 			findElement(By.id("assetValue"),"Find Key field").sendKeys("TestKey","Enter Key value");;
 			findElement(By.xpath("//*[@placeholder='value']"), "Find Value field").sendKeys("100", "Enter some value");
 			findElement(By.xpath("//*[@placeholder='description']"), "Find description field").sendKeys("Test Auto description", "Enter description");
 			
 			findElement(By.xpath("//*[@ng-model='roleId']"), "Find Role dropdown").click("Expand Role dropdown");
 			findElement(By.xpath("//*[@ng-model='roleId']"), "Find Role dropdown").selectByIndex(1, "Select Role from drodpwon");
 			findElement(createProfile_Button, "Create Button").click("Click Create Button");

 			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
			"Verify Rule Creation Success Message");
			Log.info("User Profile : " + UserProfile);
    		}catch(Exception e){
    			Log.error("Not able to create new User Profile");
    			throw(e);
    	}
      }
       
       public void EditUserProfile_Security() throws Exception{
      		
      		try{
      			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
   				Thread.sleep(1500);
   				driver.findElement(By.xpath(".//span[text()='Users']")).click();
   			}
      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("users_10")));
   			findElement(By.id("users_10"), "Search Users").click("Click on Search Users Profile");
   			Thread.sleep(1000);
   			findElement(By.xpath("(//*[@class='ng-scope bgwhite']/td[5]/a[1])[1]"), "Find Edit button").click("Click on Edit button");
   			
      		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
      		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
   			_assert.equals(pageName, "Create User Profile", "Verifying page title");
      		
   			Log.info("Retrieving current page url");
   			pageUrl = driver.getCurrentUrl();
   			System.out.println(pageUrl);
   			logout();
      		}catch(Exception e){
      			Log.error("Not able to edit User Profile");
      			throw(e);
      	}
        }
       
       public void DeleteUserProf_Security() throws Exception{
     	  	
    	   try{
     			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
  				Thread.sleep(1500);
  				driver.findElement(By.xpath(".//span[text()='Users']")).click();
  			}
     		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("users_10")));
  			findElement(By.id("users_10"), "Search Users").click("Click on Search Users Profile");
   		
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='ng-scope bgwhite']/td[5]/a[2]")));
   		findElement(By.xpath("//*[@class='ng-scope bgwhite']/td[5]/a[2]"), "Find delete button").click("Click to delete");
 		alertAccept("Click on Ok");
 		
 		}catch(Exception e){
 			Log.error("Not able to delete User Profile");
 			throw(e);
      }
     }
       
     public void retrieveUrl_Security() throws Exception{
    	  	
    	try{
    	    Log.info("Retrieving current page url");
      		pageUrl = driver.getCurrentUrl();
      		System.out.println(pageUrl);
      		logout();
 		}catch(Exception e){
 			Log.error("Not able to retrieve url");
 			throw(e);
      }
     }
       
       public void CreateGroup_Security() throws InterruptedException{
   		
   		if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
   		findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
   		}
   		findElement(create_customer, "Create Customer").click("Click on Create Customer");
   		Group_Name = "Group_Auto_"+randomNumber();
   		//findElement(customer_radio, "Customer Radio Button").click("Select Customer Radio Button");
   		findElement(customer_name, "Find Group Name box").sendKeys(Group_Name, "Enter Group Name");
   		
   		findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/a"), "Parent Customer").click("Click on Parent Customer");
   		Thread.sleep(1000);
   		findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
   		Thread.sleep(2000);
		//findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
		findElement(By.xpath(".//*[@role='menu']/li[2]/a"),"Customer Drop Down List").click("Select searched customer from dropdown");

   		findElement(customer_desc, "Customer Description").sendKeys("Test Desc", "Enter Customer Description");
   		findElement(createCustomer_Button, "Create Button").submit("Submit on Create Button");
   		Log.info("Group Create : " + Group_Name);
   		
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
   		String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
   		_assert.contains(actual, "successfully", "Verify Create Customer Successs Message");
   	}
       
       public void CreateChildGroup_Security() throws InterruptedException{
      		
      		if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
      			Thread.sleep(1000);
      		findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."), "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
      		}
      		findElement(create_customer, "Create Customer").click("Click on Create Customer");
      		Group_Name = "Group_Auto_"+randomNumber();
      		//findElement(customer_radio, "Customer Radio Button").click("Select Customer Radio Button");
      		findElement(customer_name, "Find Group Name box").sendKeys(Group_Name, "Enter Group Name");
      		
      		findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/a"), "Parent Customer").click("Click on Parent Customer");
      		Thread.sleep(1000);
      		//findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
      		
   		    //findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
   		    findElement(By.xpath(".//*[@role='menu']/li[2]/a"),"Customer Drop Down List").click("Select searched customer from dropdown");
            
      		findElement(customer_desc, "Customer Description").sendKeys("Test Desc", "Enter Customer Description");
      		findElement(createCustomer_Button, "Create Button").submit("Submit on Create Button");
      		Log.info("Group Create : " + Group_Name);
      		
      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
      		String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
      		_assert.contains(actual, "successfully", "Verify Create Customer Successs Message");
      	}   
        
      public void FirstGroupUser_Security() throws Exception{
     		
     		try{
     			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
  				Thread.sleep(1500);
  				driver.findElement(By.xpath(".//span[text()='Users']")).click();
  				Thread.sleep(2000);
  			}
  			findElement(By.id("users_3"), "Create User").click("Click on Create User");
  			Thread.sleep(1000);
  			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");

  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-model='searchTerm']")));
  			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
  			/*
  			Thread.sleep(1000);
  			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-model='searchTerm']")));
  			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
  			driver.findElement(By.xpath(".//*[@ng-model='searchTerm']")).clear();
  			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
  			Thread.sleep(4000);*/
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]")));
  			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
  			Thread.sleep(4000);
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@ng-model='userDetails.groupId']/a")));
  			findElement(By.xpath(".//div[@ng-model='userDetails.groupId']/a"), "Group Drop Down").click("Click on Group Drop Down");
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-model='userDetails.groupId']/div/div/input")));
  			findElement(By.xpath(".//*[@ng-model='userDetails.groupId']/div/div/input"), "Group Drop Down").sendKeys(Group_Name, "Enter Created Group");
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-model='userDetails.groupId']/div/ul/li[2]")));
  			findElement(By.xpath(".//*[@ng-model='userDetails.groupId']/div/ul/li[2]"),"Group Drop Down opens").click("Click to select from Group Drop Down");
  			
  			findElement(By.id("firstName"), "First Name").sendKeys("group", "Enter First Name");
  			findElement(By.id("lName"), "Last Name").sendKeys("user", "Enter Last Name");
  			findElement(By.name("email"), "Email").sendKeys("auto_guser_" + randomNumber() + "@test.com", "Enter Email Id");
  			findElement(By.id("phoneNumber"), "Phone Number").sendKeys("6473443836", "Enter Phone Number");
  			
  			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down").click("Click on Role Drop Down");
            findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");
  	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
  	        Thread.sleep(2000);
  	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
  			
  	        String newuser = "securitygroupuser" + randomNumber();
  			Log.info("Group User Created : " + newuser);
  			securityProp.setProperty("group_User1", newuser.toLowerCase());

  			findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
  			findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
  			findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"), "Confirm Password");
  			findElement(createUser_Button, "Create Button").submit("Submit on Create Button");
  			Log.info("Group Create : " + Group_Name);
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
  			String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
  			_assert.contains(actual, "successfully", "Verify Create User Successs Message");
  			
     		}catch(Exception e){
     			Log.error("Not able to create new Group User");
     			throw(e);
     	}
       }
      
      public void FrstGroupUser_Security() throws Exception{
     		
     		try{
     			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
  				Thread.sleep(1500);
  				driver.findElement(By.xpath(".//span[text()='Users']")).click();
  				Thread.sleep(2000);
  			}
  			findElement(By.id("users_3"), "Create User").click("Click on Create User");

  			Thread.sleep(1000);
  			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");
  			
  			Thread.sleep(2000);
  			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
  			Thread.sleep(2000);
  			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
  			
  			Thread.sleep(1000);
  			findElement(By.xpath(".//div[@ng-model='userDetails.groupId']/a"), "Group Drop Down").click("Click on Group Drop Down");
  			
  			Thread.sleep(2000);
  			findElement(By.xpath(".//*[@ng-model='userDetails.groupId']/div/div/input"), "Group Drop Down").sendKeys(Group_Name, "Enter Created Group");
  			Thread.sleep(2000);
  			findElement(By.xpath(".//*[@ng-model='userDetails.groupId']/div/ul/li[2]"),"Group Drop Down opens").click("Click to select from Group Drop Down");
  			
  			findElement(By.id("firstName"), "First Name").sendKeys("group", "Enter First Name");
  			findElement(By.id("lName"), "Last Name").sendKeys("user", "Enter Last Name");
  			findElement(By.name("email"), "Email").sendKeys("auto_guser_" + randomNumber() + "@test.com", "Enter Email Id");
  			findElement(By.id("phoneNumber"), "Phone Number").sendKeys("6473443836", "Enter Phone Number");
  			
  			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down").click("Click on Role Drop Down");
            findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");
  	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
  	        Thread.sleep(2000);
  	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
  			
  	        String newuser = "securitygroupuser" + randomNumber();
  			Log.info("Group User Created : " + newuser);
  			securityProp.setProperty("group_User1", newuser.toLowerCase());

  			findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
  			findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
  			findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"), "Confirm Password");
  			findElement(createUser_Button, "Create Button").submit("Submit on Create Button");
  			Log.info("Group Create : " + Group_Name);
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
  			String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
  			_assert.contains(actual, "successfully", "Verify Create User Successs Message");
  			
     		}catch(Exception e){
     			Log.error("Not able to create new Group User");
     			throw(e);
     	}
       }
      
      public void SecondGroupUser_Security() throws Exception{
   		
   		try{
   			if (!driver.findElement(By.xpath("(.//span[text()='Users'])/..")).getAttribute("class").contains("selected")) {
				Thread.sleep(1500);
				driver.findElement(By.xpath(".//span[text()='Users']")).click();
				Thread.sleep(2000);
			}
			findElement(By.id("users_3"), "Create User").click("Click on Create User");

			Thread.sleep(1000);
			findElement(By.xpath(".//div[@ng-model='userDetails.customerId']/a"), "Customer Drop Down").click("Click on Customer Drop Down");
			
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@ng-model='searchTerm']"), "Customer Drop Down").sendKeys(Cust_Name, "Enter Created Customer");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@ng-model='userDetails.customerId']/div/ul/li[2]"),"Customer Drop Down List").click("Click to select from Customer Drop Down");
			
			Thread.sleep(1000);
			findElement(By.xpath(".//div[@ng-model='userDetails.groupId']/a"), "Group Drop Down").click("Click on Group Drop Down");
			
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@ng-model='userDetails.groupId']/div/div/input"), "Group Drop Down").sendKeys(Group_Name, "Enter Created Group");
			Thread.sleep(2000);
			findElement(By.xpath(".//*[@ng-model='userDetails.groupId']/div/ul/li[2]"),"Group Drop Down opens").click("Click to select from Group Drop Down");
			
			findElement(By.id("firstName"), "First Name").sendKeys("group", "Enter First Name");
			findElement(By.id("lName"), "Last Name").sendKeys("user", "Enter Last Name");
			findElement(By.name("email"), "Email").sendKeys("auto_guser_" + randomNumber() + "@test.com", "Enter Email Id");
			findElement(By.id("phoneNumber"), "Phone Number").sendKeys("6473443836", "Enter Phone Number");
			
			findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/a"), "Role Drop Down").click("Click on Role Drop Down");
          findElements(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li"), "Get Role's Count");
	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/div/input"), "Role Drop Down").sendKeys("DSM_ADMIN", "Enter Role Name");
	        Thread.sleep(2000);
	        findElement(By.xpath(".//div[contains(@ng-model,'roleId')]/div/ul/li[2]"), "Role Drop Down List").click("Click on Role Drop Down List");
			
	        String newuser = "securitygroupuser" + randomNumber();
			Log.info("Group User Created : " + newuser);
			securityProp.setProperty("group_User2", newuser.toLowerCase());

			findElement(By.id("username"), "User Name").sendKeys(newuser, "Enter User Name");
			findElement(By.id("password"), "Password").sendKeys(prop.getProperty("new_password"), "Enter Password");
			findElement(By.id("confirmPassword"), "Confirm Password").sendKeys(prop.getProperty("new_password"), "Confirm Password");
			findElement(createUser_Button, "Create Button").submit("Submit on Create Button");
			Log.info("Group Create : " + Group_Name);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']/span"),"Success Alert").getAttribute(Attributes.TEXT);
			_assert.contains(actual, "successfully", "Verify Create User Successs Message");
			
   		}catch(Exception e){
   			Log.error("Not able to create new Group User");
   			throw(e);
   	}
     }
      
      public void UrlVerification_Security() throws Exception{
  		testUrl = "http://16.16.88.100:8080/dsm/dashboard/iotDashboard.htm";
  		try{
  			Log.info("Enter retrieved url");
  			driver.get(pageUrl);
  			Thread.sleep(2000);			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Notification));
  			_assert.contains(findElement(alert_Notification, "Check for success alert").getAttribute(Attributes.TEXT),"User", 
  			"Verify Rule Creation Success Message");
  			driver.get(securityProp.getProperty("testurl"));
  			//driver.quit();
  			//driver.get(testUrl);
  			//driver.get(prop.getProperty("url"));
  		}catch(Exception e){
  			Log.error("Not able to validate using link");
  			throw(e);
  	}
    }
      
      public void AddQoSPolicy_Security(String Policy, String Key) throws Exception{
  		
  		try{
  			if (!findElement(By.xpath("(.//span[text()='QoS Policy'])/.."),"QoS Policy Tile").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='QoS Policy']")).click();
  			}
  			findElement(By.id("qos_1"), "Add Policy").click("Click on Add Policy");
  			Thread.sleep(2000);
  			findElement(Policy_Box, "Find Policy Name field").sendKeys(Policy, "Enter Policy Name");
  			findElement(PolicyDesc_Box, "Find Policy Description field").sendKeys("Auto Test description", "Enter some description");
  			Thread.sleep(1000);
  			findElement(Definition_Box, "Find Key field").sendKeys(Key, "Enter some key value");
  			findElement(Value_Box, "Find Value field").sendKeys("Auto Value", "Enter some value");
  			findElement(Description_Box, "Find Description field").sendKeys("Test auto description", "Enter some description");
  			findElement(Create_Button, "Find Create Button").click("Click on Button");
  						
  			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
  			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
  			"Verify Rule Creation Success Message");
  		}catch(Exception e){
  			Log.error("Not able to add QoS Policy");
  			throw(e);
  	}
    }
    
        public void ViewQoSPolicy_Security() throws Exception{
		
		try{
			if (!findElement(By.xpath("(.//span[text()='QoS Policy'])/.."),"QoS Policy Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='QoS Policy']")).click();
			}
			findElement(By.id("qos_2"), "Find Manage Policy").click("Click on Manage Policy");
			findElement(Policy_link, "Find created Policy").click("Click on Policy to open View page");
			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
			Thread.sleep(2000);
			String pageName = findElement(By.xpath("(//*[@id='currentPage'])[1]"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, ViewPolicytitle, "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			//System.out.println(pageUrl);
			logout();
			
		}catch(Exception e){
			Log.error("Not able to view QoS Policy");
			throw(e);
	}
  }
      
      public void EditQoSPolicy_Security() throws Exception{
  		
  		try{
  			if (!findElement(By.xpath("(.//span[text()='QoS Policy'])/.."),"QoS Policy Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='QoS Policy']")).click();
			}
			findElement(By.id("qos_2"), "Find Manage Policy").click("Click on Manage Policy");
			findElement(Edit_Icon, "Find created Policy").click("Click on Policy to open View page");
			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
			Thread.sleep(2000);
			
			String pageName = findElement(By.xpath("(//*[@id='currentPage'])[1]"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, EditPolicytitle, "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to validate using link");
  			throw(e);
  	}
    }

      public void ViewApplication_Security() throws Exception{
  		
  		try{
  			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt.").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
  			}
  			findElement(By.id("ae_2"), "Find Search Application").click("Click on Search Application");
  			findElement(App_Link, "Find created Application").click("Click on Application to open View page");
  			/*wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
  			Thread.sleep(2000);
  			String pageName = findElement(By.xpath("(//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
  			_assert.equals(pageName, ViewApplicationtitle, "Verifying page title");*/
  			Log.info("Retrieving current page url");
  			pageUrl = driver.getCurrentUrl();
  			System.out.println(pageUrl);
  			logout();
  			
  		}catch(Exception e){
  			Log.error("Not able to view QoS Policy");
  			throw(e);
  	}
    }
      
      public void EditApplication_Security() throws Exception{
    		
    		try{
      			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt.").getAttribute(Attributes.GENERAL,"class")
      					.contains("selected")) {
      				Thread.sleep(1000);
      				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
      			}
      			findElement(By.id("ae_2"), "Find Search Application").click("Click on Search Application");
  			findElement(AppEdit_Icon, "Find created Application").click("Click on Edit icon");
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
  			Thread.sleep(2000);
  			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
  			_assert.equals(pageName, EditApplicationtitle, "Verifying page title");
  			Log.info("Retrieving current page url");
  			pageUrl = driver.getCurrentUrl();
  			System.out.println(pageUrl);
  			logout();
    		}catch(Exception e){
    			Log.error("Not able to validate using link");
    			throw(e);
    	}
      }
      
      public void CreateResource_Security() throws Exception{
  		
  		try{
    			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt.").getAttribute(Attributes.GENERAL,"class")
    					.contains("selected")) {
    				Thread.sleep(1000);
    				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
    			}
    			findElement(By.id("ae_2"), "Find Search Application").click("Click on Search Application");
			findElement(CreateResource_Link, "Find Create Resource Group link").click("Click on Create Resource Group link");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
			Thread.sleep(2000);
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, CreateResourcetitle, "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to validate using link");
  			throw(e);
  	}
    }
      
      public void SubscriptionApplication_Security() throws Exception{
    		
    		try{
      			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt.").getAttribute(Attributes.GENERAL,"class")
      					.contains("selected")) {
      				Thread.sleep(1000);
      				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
      			}
      			findElement(By.id("ae_2"), "Find Search Application").click("Click on Search Application");
  			findElement(Subscription_Link, "Find Subscription link").click("Click on Subscriptions link");
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
  			Thread.sleep(2000);
  			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
  			_assert.equals(pageName, Subscriptionstitle, "Verifying page title");
  			Log.info("Retrieving current page url");
  			pageUrl = driver.getCurrentUrl();
  			System.out.println(pageUrl);
  			logout();
    		}catch(Exception e){
    			Log.error("Not able to validate using link");
    			throw(e);
    	}
      }
      
      public void EditRouting_Security() throws Exception{
  		
  		try{
    			if (!findElement(By.xpath("(.//span[text()='Routing Rule'])/.."),"Application Mgmt.").getAttribute(Attributes.GENERAL,"class")
    					.contains("selected")) {
    				Thread.sleep(1000);
    				driver.findElement(By.xpath(".//span[text()='Routing Rule']")).click();
    			}
    			findElement(By.id("routing_2"), "Find Manage Rules").click("Click on Manage Rules");
			findElement(By.xpath(".//*[@st-pipe='refreshTable']/tbody/tr/td[7]/a"), "Find Edit Icon").click("Click on Edit Icon");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
			Thread.sleep(2000);
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, UpdateRuletitle, "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to validate using link");
  			throw(e);
  	}
    }
      
      public void ViewCustomer_Security() throws Exception{
    		
    		try{
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cust_2")));
      			findElement(By.id("cust_2"), "Find Search Customer/Group").click("Click on Search Customer/Group");
      			
  			findElement(By.xpath(".//*[@st-pipe='refreshCusTable']/tbody/tr/td[2]/a"), "Find Customer").click("Click on Customer name");
  			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
  			Thread.sleep(2000);
  			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
  			_assert.equals(pageName, GroupDetailstitle, "Verifying page title");
  			Log.info("Retrieving current page url");
  			pageUrl = driver.getCurrentUrl();
  			System.out.println(pageUrl);
  			logout();
    		}catch(Exception e){
    			Log.error("Not able to validate using link");
    			throw(e);
    	}
      }
      
      public void EditCreatedCustomer_Security() throws Exception{
  		
  		try{
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cust_2")));
    			findElement(By.id("cust_2"), "Find Search Customer/Group").click("Click on Search Customer/Group");
    			
		    findElement(By.xpath(".//*[@st-pipe='refreshCusTable']/tbody/tr/td[5]/a"), "Find Edit Icon").click("Click on Edit Icon");
			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
			Thread.sleep(2000);
			String pageName = findElement(By.xpath("//*[@class='col-sm-4']/h2"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, EditCutomartitle, "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to validate using link");
  			throw(e);
  	}
    }
      
      public void ViewGroup_Security() throws Exception{
  		
  		try{
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cust_2")));
    		findElement(By.id("cust_2"), "Find Search Customer/Group").click("Click on Search Customer/Group");
    			
    	    findElement(By.xpath(".//*[@class='nav nav-tabs black-text']/li[2]/a"), "Find Group Tab").click("Click on Group Tab");
    	    Thread.sleep(2000);
			findElement(By.xpath(".//*[@st-pipe='refreshTable']/tbody/tr/td[2]/a"), "Find Group Name").click("Click on Group Name");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			Thread.sleep(2000);
			_assert.equals(pageName, SearchGrouptitle, "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to validate using link");
  			throw(e);
  	}
    }
      
      public void EditGroup_Security() throws Exception{
    		
    		try{
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cust_2")));
      		findElement(By.id("cust_2"), "Find Search Customer/Group").click("Click on Search Customer/Group");
      			
      	    findElement(By.xpath(".//*[@class='nav nav-tabs black-text']/li[2]/a"), "Find Group Tab").click("Click on Group Tab");
      	    Thread.sleep(2000);
  			findElement(By.xpath(".//*[@st-pipe='refreshTable']/tbody/tr/td[5]/a"), "Find edit Group ").click("Click to edit Group");
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
  			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
  			Thread.sleep(2000);
  			_assert.equals(pageName, SearchGrouptitle, "Verifying page title");
  			Log.info("Retrieving current page url");
  			pageUrl = driver.getCurrentUrl();
  			System.out.println(pageUrl);
  			logout();
    		}catch(Exception e){
    			Log.error("Not able to validate using link");
    			throw(e);
    	}
      }
      
      public void AddNewAsset_Security() throws Exception{
  		
  		try{
    			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
    					.contains("selected")) {
    				Thread.sleep(1000);
    				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
    			}
    		findElement(By.id("asset_2"), "Find Add Asset").click("Click on Add Asset");
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
      		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Add Asset", "Verifying page title");
			
			Asset_Name = "Auto_Asset_"+randomNumber();
			findElement(AssetName_Box, "Find Asset Name").sendKeys(Asset_Name, "Enter some Asset Name");
			
			findElement(By.xpath(".//*[@class='dropdown-toggle ng-scope']"), "Find Customer dropdown").click("Click to expand Customer dropdown");
      		Thread.sleep(1000);
   		    findElement(By.xpath(".//*[@role='menu']/li[2]/a"),"Customer Drop Down List").click("Select searched customer from dropdown");
   		    
   		    wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")));
			
			findElement(groupDropDownLink, "Group Drop Down Link").click("Click on Group Drop Down Link");
			findElement(groupSearchInput, "Group Search Field").sendKeys("Default", "Enter Group Name");
			Thread.sleep(2000);
			findElement(groupSearchSelect, "Group from the list").click("Select group form the list");
		    findElement(AssetType_Dropdown, "Find Asset Type dropdown").selectByText("GATEWAY", "Select Gateway in dropdown");
		   
		    findElement(By.xpath(".//*[@ng-model='deviceDetails.deviceProfileId']/a"), "Find DP dropdown").click("Click to expand DP dropdown");
		    findElement(By.xpath(".//*[@ng-model='deviceDetails.deviceProfileId']/div/ul/li[2]/a"),"DP dropdown list").click("Select device profile from dropdown");
		
		    Thread.sleep(3000);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("authNType")));
		    findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");
		    
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iotParamsDevEUI")));
		    findElement(By.id("iotParamsDevEUI"), "DevEUI Box").sendKeys(Asset_Name, "Enter DevEUI");
		    
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iotParamsAppEUI")));
		    findElement(By.id("iotParamsAppEUI"), "DevAddr Box").sendKeys(Asset_Name, "Enter DevAppEUI");
		    
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iotParamsDevAddr")));
		    findElement(By.id("iotParamsDevAddr"), "DevAddr Box").sendKeys(Asset_Name, "Enter DevAddr");
		    
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[text()='Create'])[1]")));
			findElement(By.xpath("(.//*[text()='Create'])[1]"), "Create Button").submit("Click on Create Button");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
  			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
  			"Verify Rule Creation Success Message");
			
  		}catch(Exception e){
  			Log.error("Not able to add new Asset");
  			throw(e);
  	}
    }
      
      public void AddAssetwithGroup_Security() throws Exception{
    		
    		try{
      			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
      					.contains("selected")) {
      				Thread.sleep(1000);
      				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
      			}
      		findElement(By.id("asset_2"), "Find Add Asset").click("Click on Add Asset");
      		
      		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
        		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
  			_assert.equals(pageName, "Add Asset", "Verifying page title");
  			
  			Asset_Name = "Auto_Asset_"+randomNumber();
  			findElement(AssetName_Box, "Find Asset Name").sendKeys(Asset_Name, "Enter some Asset Name");
  			
  			findElement(By.xpath(".//*[@class='dropdown-toggle ng-scope']"), "Find Customer dropdown").click("Click to expand Customer dropdown");
        	Thread.sleep(1000);
     		findElement(By.xpath(".//*[@role='menu']/li[2]/a"),"Customer Drop Down List").click("Select searched customer from dropdown");
     		
     		wait.until(
  					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
  			wait.until(
  					ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")));
  			
  			findElement(By.xpath(".//*[@ng-model='deviceDetails.deviceGroupId']/a"), "Find Group dropdown").click("Click to expand Group dropdown");
        	Thread.sleep(1000);
     		findElement(By.xpath(".//*[@role='menu']/li[3]/a"),"Group Drop Down List").click("Select searched Group from dropdown");
  		    findElement(AssetType_Dropdown, "Find Asset Type dropdown").selectByText("GATEWAY", "Select Gateway in dropdown");
  		   
  		    findElement(By.xpath(".//*[@ng-model='deviceDetails.deviceProfileId']/a"), "Find DP dropdown").click("Click to expand DP dropdown");
  		    findElement(By.xpath(".//*[@ng-model='deviceDetails.deviceProfileId']/div/ul/li[2]/a"),"DP dropdown list").click("Select device profile from dropdown");
  		
  		    Thread.sleep(3000);
  		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("authNType")));
  		    findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");
  		    
  		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iotParamsDevEUI")));
  		    findElement(By.id("iotParamsDevEUI"), "DevEUI Box").sendKeys(Asset_Name, "Enter DevEUI");
  		    
  		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iotParamsAppEUI")));
  		    findElement(By.id("iotParamsAppEUI"), "DevAddr Box").sendKeys(Asset_Name, "Enter DevAppEUI");
  		    
  		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iotParamsDevAddr")));
  		    findElement(By.id("iotParamsDevAddr"), "DevAddr Box").sendKeys(Asset_Name, "Enter DevAddr");
  		    
  		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[text()='Create'])[1]")));
  			findElement(By.xpath("(.//*[text()='Create'])[1]"), "Create Button").submit("Click on Create Button");
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
    			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
    			"Verify Rule Creation Success Message");
  			
    		}catch(Exception e){
    			Log.error("Not able to add new Asset");
    			throw(e);
    	}
      }
      
      public void ViewAddedAsset_Security() throws Exception{
  		
    	  try{
  			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
  			}
  		    findElement(By.id("asset_3"), "Find Manage Asset").click("Click on Manage Asset");
  		    
    		findElement(By.xpath(".//*[@ng-repeat='data in assetList track by $index']/td[2]/a"), "Find Asset Name").click("Click on Asset Name");
    		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
    		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "View Asset", "Verifying page title");
    		
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to View Added Asset");
  			throw(e);
  	}
    }
      
      public void EditAddedAsset_Security() throws Exception{
    		
    	  try{
  			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
  			}
  		    findElement(By.id("asset_3"), "Find Manage Asset").click("Click on Manage Asset");
  			
    		findElement(By.xpath(".//*[@ng-repeat='data in assetList track by $index']/td[9]/a"), "Find edit button").click("Click on Edit");
    		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
    		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Edit Device", "Verifying page title");
    		
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to edit Added Asset");
  			throw(e);
  	}
    }
  
     public void ViewAssetKPI_Security() throws Exception{
    		
    	  try{
  			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
  			}
  		    findElement(By.id("asset_1"), "Find Asset KPI").click("Click on Asset KPI");
  			
    		findElement(By.xpath(".//*[@ng-repeat='data in assetList track by $index']/td[1]/a"), "Find created Asset").click("Click on Asset");
    		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
    		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "View Asset", "Verifying page title");
    		
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to edit Added Asset");
  			throw(e);
  	}
    }
     
     public void ViewAddedAssetGroup_Security() throws Exception{
   		
   	  try{
 			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
 					.contains("selected")) {
 				Thread.sleep(1000);
 				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
 			}
 		    findElement(By.id("asset_3"), "Find Manage Asset").click("Click on Manage Asset");
 		    findElement(search_quick_link, "Find Search Icon").click("Click on Search to expand");
 		    findElement(include_checkbox, "Find Include Sub Group").click("Check Include Sub Group");
 		    findElement(By.xpath(".//*[@value='Search']"), "Find Search Button").click("Click Search Button");
 		    
   		    findElement(By.xpath(".//*[@ng-repeat='data in assetList track by $index']/td[2]/a"), "Find Asset Name").click("Click on Asset Name");
   		    wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
   		    String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "View Asset", "Verifying page title");
   		
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
 		}catch(Exception e){
 			Log.error("Not able to View Added Asset");
 			throw(e);
 	}
   }
     
     public void EditAddedAssetGroup_Security() throws Exception{
 		
   	  try{
 			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
 					.contains("selected")) {
 				Thread.sleep(1000);
 				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
 			}
 		    findElement(By.id("asset_3"), "Find Manage Asset").click("Click on Manage Asset");
 		    findElement(search_quick_link, "Find Search Icon").click("Click on Search to expand");
		    findElement(include_checkbox, "Find Include Sub Group").click("Check Include Sub Group");
		    findElement(By.xpath(".//*[@value='Search']"), "Find Search Button").click("Click Search Button");
		    
   		    findElement(By.xpath(".//*[@ng-repeat='data in assetList track by $index']/td[9]/a"), "Find edit button").click("Click on Edit");
   		    wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
   		    String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Edit Device", "Verifying page title");
   		
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
 		}catch(Exception e){
 			Log.error("Not able to edit Added Asset");
 			throw(e);
 	}
   }
 
    public void ViewAssetKPIGroup_Security() throws Exception{
   		
   	  try{
 			if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
 					.contains("selected")) {
 				Thread.sleep(1000);
 				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
 			}
 		    findElement(By.id("asset_1"), "Find Asset KPI").click("Click on Asset KPI");
 		    findElement(search_quick_link, "Find Search Icon").click("Click on Search to expand");
		    findElement(include_checkbox, "Find Include Sub Group").click("Check Include Sub Group");
		    findElement(By.xpath(".//*[@value='Search']"), "Find Search Button").click("Click Search Button");
		    
   		    findElement(By.xpath(".//*[@ng-repeat='data in assetList track by $index']/td[1]/a"), "Find created Asset").click("Click on Asset");
   		    wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
   		    String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "View Asset", "Verifying page title");
   		
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
 		}catch(Exception e){
 			Log.error("Not able to edit Added Asset");
 			throw(e);
 	}
   }
      
      public void AddDevMfgProfile_Security(String fileName) throws Exception{
  		
  		try{	
  			if (!findElement(By.xpath("(.//span[text()='Device Manufacturer Profile'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='Device Manufacturer Profile']")).click();
  			}
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deviceP_1")));
    		findElement(By.id("deviceP_1"), "Find Add Device Manufacturer Profile").click("Click on Add Device Manufacturer Profile");
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".treeview>li")));
    		findElement(By.xpath(".//*[@ng-model='checkStatus']"), "Find the Customer").click("Click Checkbox to select Customer");
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@type='file']")));
    		findElement(By.xpath(".//*[@type='file']"), "Upload").sendKeys(System.getProperty("user.dir") + "\\testdata\\"+fileName, "Upload File");
	        /*if (cpAttach == true) {
		    findElement(By.xpath(".//*[@ng-options='container.profileId as container.profileName for container in containerProfileList']"),
		    		"Select ContainerProfileField").selectByText(cpName[0],"select CP");
	        }
	        */
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(text(),'Submit')]")));
	        findElement(By.xpath(".//*[contains(text(),'Submit')]"), "Create Button").click("Click on Submit");
	        		
  			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
  			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
  			"Verify Device Profile upload success message");
  		}catch(Exception e){
  			Log.error("Not able to upload Device Manufacturer Profile");
  			throw(e);
  	}
    }
      
      public void ViewDevMfgProfile_Security() throws Exception{
    		
    		try{	
    			if (!findElement(By.xpath("(.//span[text()='Device Manufacturer Profile'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Device Manufacturer Profile']")).click();
  			}
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deviceP_2")));
      		findElement(By.id("deviceP_2"), "Find Search Device Manufacturer Profile").click("Click on Search Device Manufacturer Profile");
      		
      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-repeat='data in listDP track by $index']/td/a")));
      		findElement(By.xpath(".//*[@ng-repeat='data in listDP track by $index']/td/a"), "Find added Profile").click("Click on Profile");
      		
      		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
      		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "View Device Profile", "Verifying page title");
			Log.info("Retrieving current page url");
  			pageUrl = driver.getCurrentUrl();
  			System.out.println(pageUrl);
  			logout();
    		}catch(Exception e){
    			Log.error("Not able to View Device Manufacturer Profile page");
    			throw(e);
    	}
      }
      
      public void EditDevMfgProfile_Security() throws Exception{
  		
  		try{	
  			if (!findElement(By.xpath("(.//span[text()='Device Manufacturer Profile'])/.."),"Asset Mgmt.").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='Device Manufacturer Profile']")).click();
			}
  			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deviceP_2")));
    		findElement(By.id("deviceP_2"), "Find Search Device Manufacturer Profile").click("Click on Search Device Manufacturer Profile");
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-repeat='data in listDP track by $index']/td[8]/a")));
    		findElement(By.xpath(".//*[@ng-repeat='data in listDP track by $index']/td[8]/a"), "Find added Profile").click("Click on Profile");
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
    		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Update Device Profile Entity/Device Group", "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to Edit Device Manufacturer Profile page");
  			throw(e);
  	}
    }
      
      public void AddACP_Security() throws Exception{
    		
    		try{	
    			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"Find ACP tab").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Access Control Policy']")).click();
    		}  
    		ACP_Name = "Auto_ACP_"+randomNumber();
    			
      		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("acp_1")));
      		findElement(By.id("acp_1"), "Find Add ACP Rule").click("Click on Add ACP Rule");
      		
      		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
      		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
  			_assert.equals(pageName, "Add ACP Rule", "Verifying page title");
      		
  			findElement(By.id("acpName"), "Find Name").sendKeys(ACP_Name, "Enter ACP name");
  			findElement(By.id("acpDescription"), "Find Description").sendKeys("Test automation description", "Enter some description");
  			findElement(By.xpath("(//*[@name='deviceProtocol'])[1]"), "Find Create checkbox").click("Check Create");

      		findElement(By.xpath(".//*[@ng-click='addACP()']"), "Find Create Button").click("Click on Create");
      		wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
  			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
  			"Verify created ACP success message");
    		}catch(Exception e){
    			Log.error("Not able to create ACP");
    			throw(e);
    	}
      }
      
      public void ViewAddedACP_Security() throws Exception{
  	
  			try{	
    			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"Find ACP tab").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Access Control Policy']")).click();
    		}
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("acp_3")));
          	findElement(By.id("acp_3"), "Find Search ACP Rule").click("Click on Search ACP Rule");
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-repeat='data in data']/td[2]/a")));
    		findElement(By.xpath(".//*[@ng-repeat='data in data']/td[2]/a"), "Find added ACP").click("Click on ACP");
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
    		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "ACP Details", "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
  		}catch(Exception e){
  			Log.error("Not able to View ACP");
  			throw(e);
       }
      }
      
      public void EditAddedACP_Security() throws Exception{
    	  	
			try{	
  			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"Find ACP tab").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='Access Control Policy']")).click();
  		}
  		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("acp_3")));
        	findElement(By.id("acp_3"), "Find Search ACP Rule").click("Click on Search ACP Rule");
  		
  		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-repeat='data in data']/td[4]/a[1]")));
  		findElement(By.xpath(".//*[@ng-repeat='data in data']/td[4]/a[1]"), "Find added ACP").click("Click on ACP");
  		
  		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
  		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Edit ACP", "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
		}catch(Exception e){
			Log.error("Not able to edit select ACP");
			throw(e);
     }
    }
      
      public void AddAddressPool_Security() throws Exception{
  		
  		try{	
  			if (!findElement(By.xpath("(.//span[text()='Address Pool Mgmt.'])/.."),"Find Address Pool Mgmt.").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='Address Pool Mgmt.']")).click();
  		}  
  			AddPool_Name = "Auto_AddressPool_"+randomNumber();
  			//String Start_Range = "1"+randomNumber();
  			//String End_Range = "10"+randomNumber();
  			
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='asset_2'])[2]")));
    		findElement(By.xpath("(//*[@id='asset_2'])[2]"), "Find Create Address Pool").click("Click on Create Address Pool");
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
    		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Create Address Pool", "Verifying page title");
    		
			findElement(By.xpath(".//*[@name='poolName']"), "Find Pool Name").sendKeys(AddPool_Name, "Enter Pool name");
			findElement(PoolType_Dropdown, "Find Description").click("Click to expand Pool Type dropdown");
			findElement(By.xpath("(.//*[@role='menu']/li[3])[1]"), "Find DevEUI").click("Click on DevEUI to select");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@name='startRange']")));
    		findElement(By.xpath(".//*[@name='startRange']"), "Find Pool Start Range").sendKeys("2124567", "Enter start range");
    		findElement(By.xpath(".//*[@name='endRange']"), "Find Pool End Range").sendKeys("2134567", "Enter end range");
    		
    		findElement(Customer_Dropdown, "Find Description").click("Click to expand Customer dropdown");
			findElement(By.xpath("(.//*[@role='menu']/li[2])[2]"), "Find Customer").click("Click to select Customer");
			findElement(Createpool_button, "Find Create Button").click("Click on Create");
    		wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
			"Verify created Address Pool success message");
  		}catch(Exception e){
  			Log.error("Not able to create Address Pool");
  			throw(e);
  	}
    }
      
      public void ViewAddedPool_Security() throws Exception{
    	  	
    	  try{	
    			if (!findElement(By.xpath("(.//span[text()='Address Pool Mgmt.'])/.."),"Find Address Pool Mgmt.").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Address Pool Mgmt.']")).click();
    		}
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='asset_3'])[2]")));
        findElement(By.xpath("(//*[@id='asset_3'])[2]"), "Find Manage Address Pool").click("Click on Manage Address Pool");
  		
  		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//*[@ng-repeat='data in addrList track by $index']/td/a)[1]")));
  		findElement(By.xpath("(.//*[@ng-repeat='data in addrList track by $index']/td/a)[1]"), "Find added Address Pool").click("Click on Address Pool");
  		
  		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
  		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Manage Address Pools", "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
		}catch(Exception e){
			Log.error("Not able to View Address Pool");
			throw(e);
     }
    }
      
      public void DeleteAddedPool_Security() throws Exception{
  	  	
    	  try{	
    			if (!findElement(By.xpath("(.//span[text()='Address Pool Mgmt.'])/.."),"Find Address Pool Mgmt.").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Address Pool Mgmt.']")).click();
    		}
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='asset_3'])[2]")));
        findElement(By.xpath("(//*[@id='asset_3'])[2]"), "Find Manage Address Pool").click("Click on Manage Address Pool");
  		
  		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-repeat='data in addrList track by $index']/td[6]/a[2]")));
  		findElement(By.xpath("//*[@ng-repeat='data in addrList track by $index']/td[6]/a[2]"), "Find delete button").click("Click to delete");
		alertAccept("Click on Ok");
		
		}catch(Exception e){
			Log.error("Not able to delete Address Pool");
			throw(e);
     }
    }
      
      public void EditAddedPool_Security() throws Exception{
  	  	
    	  try{	
    			if (!findElement(By.xpath("(.//span[text()='Address Pool Mgmt.'])/.."),"Find Address Pool Mgmt.").getAttribute(Attributes.GENERAL,"class")
  					.contains("selected")) {
  				Thread.sleep(1000);
  				driver.findElement(By.xpath(".//span[text()='Address Pool Mgmt.']")).click();
    		}
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='asset_3'])[2]")));
        findElement(By.xpath("(//*[@id='asset_3'])[2]"), "Find Manage Address Pool").click("Click on Manage Address Pool");
  		
  		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//*[@ng-repeat='data in addrList track by $index']/td[6]/a)[1]")));
  		findElement(By.xpath("(.//*[@ng-repeat='data in addrList track by $index']/td[6]/a)[1]"), "Find added Edit icon").click("Click on Edit icon");
  		
  		wait.until(ExpectedConditions.visibilityOfElementLocated(page_Title));
  		String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, "Manage Address Pools", "Verifying page title");
			Log.info("Retrieving current page url");
			pageUrl = driver.getCurrentUrl();
			System.out.println(pageUrl);
			logout();
		}catch(Exception e){
			Log.error("Not able to View Address Pool");
			throw(e);
     }
    }
        
}
