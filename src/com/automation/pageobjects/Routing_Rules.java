/**
 * 
 */
package com.automation.pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hpe.base.Attributes;
import com.hpe.base.SelectBy;
import com.hpe.webdriver.ElementAction;
import com.hpe.webdriver.ElementBase;
import com.relevantcodes.extentreports.LogStatus;

import javafx.scene.control.Alert;

/**
 * @author aloksu
 *
 */
public class Routing_Rules extends ElementBase{

	final static Logger Log = Logger.getLogger(Routing_Rules.class.getName());
	
	
	By Add_RulesPage = By.xpath(".//*[@id='routing_1']");
	By Manage_RulesPage = By.xpath("//*[@id='routing_2']");
	By Applied_RulePage = By.xpath("//*[@id='routing_3']");
	
	By CustName_Dropdown = By.xpath("//*[@id='addOSSForm']/table[1]/tbody/tr/td[2]/div/select");
	By RuleName_Box = By.xpath("//*[@name='ruleName']");
	By AtriName_Box = By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr/td[1]/div/input");
	By AtriName_2ndBox = By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr[2]/td[1]/div/input");
	By Condition_dropdown = By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr/td[2]/select");
	By Condition_2ndropdown = By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr[2]/td[2]/select");
	By Value_Box = By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr/td[3]/input");
	By Value_2ndBox = By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr[2]/td[3]/input");
	By Create_button = By.xpath("//*[@id='addruleBtn']");
	
	By alert_Success = By.xpath("//*[@class='alert alert-success']");
	By alert_Failure = By.xpath("//*[@class='alert alert-danger']");
	By alert_Delete = By.xpath("//*[@class='ng-isolate-scope']/div/div[2]");
	By alert_OSSDelete = By.xpath("//*[@ng-init='start=true']/div[4]");
	By alert_RuleDelete = By.xpath("//*[@ng-app='appliationEntityApp']/div[6]");
	By alert_UpdateSuccess = By.xpath("//*[@class='alert alert-success ng-scope']");
	
	By ManagePage_title = By.xpath("//*[@class='header-title']");
	By DataProp_Button  =  By.xpath("(//*[@class='ng-scope']/td[8]/a)[1]");
	By OSS_Button = By.xpath(".//*[@class='btn btn-primary ng-binding ng-scope']");
	By RuleName_Dropdown = By.xpath("//*[@id='roleId']");
	By Probable_Box = By.xpath("//*[@ng-model='assignment.additionalDetails.cause']");
	By Severity_dropdown = By.xpath("//*[@ng-model='assignment.additionalDetails.severity']");
	By AddDetails_Box = By.xpath("//*[@ng-model='assignment.additionalDetails.additionalText']");
	By Alarm_dropdown = By.xpath("//*[@name='alarmType']");
	By Active = By.xpath("//*[@ng-model='isActive']");
	By start_Button = By.xpath(".//*[@class='btn grommetux-button-primary']");
	By Delete_OSSButton = By.xpath("//*[@class='btn btn-danger']");
	
	By DataProp_Alert = By.xpath("//*[@class='alert alert-success']");
	By Cancel_Button = By.xpath("//*[@id='prevPage']");
	By Delete_Button = By.xpath("//*[@ng-click='deleteRow($index)']");
	By Delete_2ndButton = By.xpath("//*[@ng-form='tableForm']/tr[2]/td[4]/a");
	By Search_Button = By.xpath("//*[@class='btn grommetux-button-primary']"); 
	By AddNewAttri_Button = By.xpath("//*[@id='addOSSForm']/div[1]/div[3]/button");
	
    By SecondAttribute_Box = By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr[2]/td[1]/div/input");
    By Rule_NameFirst = By.xpath("//*[@st-table='ruleList']/tbody/tr/td[2]");
    By Popup_title = By.xpath("//*[@class='no-border']");
    
    By Rule_DeleteButton = By.xpath("//*[@ng-click='deleteRule(data)']");
    By Condition_button = By.xpath("//*[@st-table='ruleList']/tbody/tr/td[5]/button");
    By Cancel_inPopup = By.xpath("//*[@ng-click='dynamicPopover.close()']");
    By Search_Link = By.xpath("//*[@class='btn quicklinks']");
    By IncludeSubGroup = By.xpath(".//*[@type='checkbox']");
    By SearchRule_Box = By.xpath("//*[@ng-model='searchObj.search.searchPairs[0].value']");
    By RuleNameCOl_Value = By.xpath("//*[@st-table='ruleList']/tbody/tr/td[2]");
    By Cust_Drodpown = By.xpath("//*[@ng-class='{ disabled: disabled }']");
    By SearchBox = By.xpath("//*[@ng-model='searchTerm']");
    
    By Selected_Rule = By.id("1001");
    By Rule_CrossSign = By.xpath("//*[@class='fa fa-times']");
    By Edit_Icon = By.xpath("//*[@ng-repeat='data in ruleList track by $index']/td[7]/a");
	
	public final String AddRuletitle = "Create Rule";
	public final String ManageRuletitle = "Manage Rule";
	public final String AppliedRuletitle = "Applied Rules";
	public final String Dashboardtitle = "Dashboard";
	public final String EditRuletitle = "Update Rule";
	
	String Add_NewRule =null;
	String New_Attribute = null;
	//String Value = null;
	
	public void OpenAddRulepage_Routing() throws Exception{
		
		try{
			if (!findElement(By.xpath("(.//span[text()='Routing Rule'])/.."),"Routing Role Tile").getAttribute(Attributes.GENERAL,"class")
			.contains("selected")) {
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//span[text()='Routing Rule']")).click();
		
	  }
			Thread.sleep(2000);
			findElement(Add_RulesPage, "Find Add Rules option under Routing Rule tile").click("Click on Add Rules");
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, AddRuletitle, "Verifying page title"); 
		}catch(Exception e){
			Log.error("Add Rule Page not opening");
			throw(e);
	}
  }
	
       public void OpenAddRuleDoubleClick_Routing() throws Exception{
		
		   try{
			driver.findElement(By.xpath(".//span[text()='Routing Rule']")).click();
			Thread.sleep(1000);
			findElement(Add_RulesPage, "Find Add Rules option under Routing Rule tile").click("Click on Add Rules");
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, AddRuletitle, "Verifying page title"); 
		 }catch(Exception e){
			Log.error("Add Rule Page not opening");
			throw(e);
	}
  }

	public void OpenManageRulePage_Routing() throws Exception{
		try{
			if (!findElement(By.xpath("(.//span[text()='Routing Rule'])/.."),"Routing Role Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='Routing Rule']")).click();
				Thread.sleep(1000);
			}
			findElement(Manage_RulesPage, "Find Manage Rules under Routing Rule").click("Click on Manage Rules");
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, ManageRuletitle, "Verifying page title"); 
		}catch(Exception e){
			Log.error("Manage Rule Page not opening");
			throw(e);
	}
	}
	
	public void OpenAppliedRulePage_Routing() throws Exception{
		try{
			if (!findElement(By.xpath("(.//span[text()='Routing Rule'])/.."),"Routing Role Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath(".//span[text()='Routing Rule']")).click();
				Thread.sleep(1000);
			}
			
			findElement(Applied_RulePage, "Find Applied Rules under Routing Rule").click("Click on Applied Rules");
			String pageName = findElement(By.xpath("//*[@bcmodulename='Device Management']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, AppliedRuletitle, "Verifying page title"); 
			
		}catch(Exception e){
			Log.error("Applied Rule Page not opening");
			throw(e);
	}
	}
	
    public void AddRule_Routing(String New_Rule, String Temprature, String Value) throws Exception{
		
		try{
			Add_NewRule = New_Rule +"_"+randomNumber();
			New_Attribute = Temprature +" "+randomNumber();
			Value = ""+randomNumber();

			Thread.sleep(2000);
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").click("Click to expand dropdown");
			Thread.sleep(1000);
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").selectByIndex(1, "Selecting Customer from dropdown");
			findElement(RuleName_Box, "Find Rule Name edit box").sendKeys(Add_NewRule, "Enter the rule name");
			
			findElement(AtriName_Box, "Find Attribute Name edit box").sendKeys(New_Attribute, "Enter Attribute type");
			findElement(Condition_dropdown, "Find Condition dropdown").selectByValue(">", "Select Greater Then condition");
			findElement(Value_Box, "Find Value Edit box").sendKeys(Value, "Enter some value");
			
			findElement(Create_button, "Find Create Button").click("Click after entering all Information");
			Thread.sleep(1000);			
			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
			"Verify Rule Creation Success Message");
			
			prop.setProperty("routing_rule", Add_NewRule);
		}catch(Exception e){
			Log.error("Not able to Add new Rule from Add Rule page");
			throw(e);
	}
  }
    
      public void DuplicateRuleName_Creation(String New_Rule) throws Exception{
		
		try{
			Add_NewRule = New_Rule +"_"+randomNumber();
			Thread.sleep(2000);
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").click("Click to expand dropdown");
			Thread.sleep(2000);
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").selectByIndex(1, "Selecting Customer from dropdown");
			findElement(RuleName_Box, "Find Rule Name edit box").sendKeys(prop.getProperty("routing_rule"), "Enter existing Rule");
			
			findElement(AtriName_Box, "Find Attribute Name edit box").sendKeys("Auto Temprature", "Enter something as Attribute");
			findElement(Condition_dropdown, "Find Condition dropdown").selectByIndex(1, "Select Greater then option");
			findElement(Value_Box, "Find Value Edit box").sendKeys("80", "Enter some value");
			findElement(Create_button, "Find Create Button").click("Click after entering all Information");
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Failure));
			_assert.contains(findElement(alert_Failure, "Check for Failure alert message").getAttribute(Attributes.TEXT),"Error", 
			"Verify Alert message displayed");
		}catch(Exception e){
			Log.error("User is able to create Rule with existing rule name");
			throw(e);
	}
  }
    
    public void AddRule_Cancel(String New_Rule) throws Exception{
		
		try{
			Add_NewRule = New_Rule +"_"+randomNumber();
			Thread.sleep(3000);

			
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").selectByIndex(1, "Selecting IoT");


			findElement(CustName_Dropdown, "Find Customer Name Dropdown").click("Click to expand dropdown");
			Thread.sleep(1000);
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").selectByIndex(1, "Selecting Customer from dropdown");

			findElement(RuleName_Box, "Find Rule Name edit box").sendKeys(Add_NewRule, "Enter the rule name");
			
			findElement(Cancel_Button, "Find Cancel button").click("Click on Cancel button");;
			Thread.sleep(2000);
			String pageName = findElement(By.xpath("//*[@bcmodulename='Dashboard']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, Dashboardtitle, "Verifying page title");
			prop.setProperty("routing_rule", Add_NewRule);
		}catch(Exception e){
			Log.error("Clicking on Cancel not redirecting to Dashboard");
			throw(e);
	}
  }
    
    public void AddRuleto_Application() throws Exception{
    	
    	try{
    		findElement(DataProp_Button, "Find Data Propogation Icon").click("Click to open Data Propogation page");
    		findElement(OSS_Button, "Find Data OSS Button").click("Click on OSS");
    		Log.info(prop.getProperty("routing_rule"));
    		findElement(RuleName_Dropdown, "Find RuleName dropdown").selectByText(prop.getProperty("routing_rule"), "Add rule");
    		findElement(Probable_Box, "Find Probable Cause").sendKeys("Auto Test", "Enter Probable Cause");
    		Thread.sleep(1000);
    		findElement(Severity_dropdown, "Find Severity Dropdown").selectByIndex(2, "Select option in dropdown");
    		findElement(AddDetails_Box, "Find Additional Details box").sendKeys("Auto Test details", "Entering details");
    		findElement(Alarm_dropdown, "Find Alarm Type Dropdown").selectByIndex(1, "Select some option");
    		findElement(Active, "Find Active Button").click("Clicking to Activate");
    		findElement(start_Button, "Find Start Button").click("Clicking Start");
    		
    		Thread.sleep(1000);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(DataProp_Alert));
			_assert.contains(findElement(DataProp_Alert, "Check for success alert").getAttribute(Attributes.TEXT),"Data", 
			"Verify Rule Creation Success Message");
    		
    	}catch(Exception e){
			Log.error("Rule not added to the Application");
			throw(e);
	}
    }
    
    public void AddMultipleRulet_App() throws Exception{
    	
    	try{
    		findElement(DataProp_Button, "Find Data Propogation Icon").click("Click to open Data Propogation page");
    		findElement(OSS_Button, "Find Data OSS Button").click("Click on OSS");
    		
    		Thread.sleep(1000);
    		findElement(RuleName_Dropdown, "Find RuleName dropdown").selectByIndex(1, "Select first rules");
    		findElement(RuleName_Dropdown, "Find RuleName dropdown").selectByIndex(2, "Select second rules");
    		findElement(start_Button, "Find Start Button").click("Clicking Start");
    		
    		Thread.sleep(1000);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(DataProp_Alert));
			_assert.contains(findElement(DataProp_Alert, "Check for success alert").getAttribute(Attributes.TEXT),"Data", 
			"Verify Rule Creation Success Message");
    		
    	}catch(Exception e){
			Log.error("Rule not added to the Application");
			throw(e);
	}
    }
    
      public void Delete_AddedRule() throws Exception{
    	
    	try{
    		findElement(Delete_OSSButton, "Find Delete button").click("Click on Delete Button");
    		
    		Thread.sleep(1000);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(alert_OSSDelete));
			_assert.contains(findElement(alert_OSSDelete, "Check for success alert").getAttribute(Attributes.TEXT),"Kafka", 
			"Verify Deletion success message");
    	}catch(Exception e){
			Log.error("Added Rule not deleted");
			throw(e);
	 }
    }

     public void ConditionDropdown_Options() throws Exception{
		
		try{
		Select select = new Select(driver.findElement(By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr/td[2]/select")));
		Thread.sleep(2000);
		List<WebElement> actualList=select.getOptions();
		for(WebElement Wb:actualList){
			Log.info(Wb.getText());
		}
	}catch(Exception e){
		Log.error("Condition dropdown not opening");
		throw(e);
	 }		
	}
     
     public void AlertMessage_NewAttribute(String Actual) throws Exception{
 	
 		try{
 			driver.findElement(By.xpath("//*[@id='addOSSForm']/div[1]/div[3]/button")).click();		
 			logger.log(LogStatus.INFO, "Creating new Rule");
 			String alertText = driver.switchTo().alert().getText();
 			Log.info(alertText);
 			alertAccept("Click on Ok");
 			_assert.contains(Actual, alertText, "Checking the alert message");

 	}catch(Exception e){
 		Log.error("Correct error message not displayed");
 		throw(e);
 	 }	
 	} 
     
     public void AddNewAttribute_Row() throws Exception{
    	 	
  		try{		
  			logger.log(LogStatus.INFO, "Creating new Rule");
  			findElement(AtriName_Box, "Find Attribute Name edit box").sendKeys("Auto Temprature", "Enter something as Attribute");
			Thread.sleep(2000);
			findElement(Condition_dropdown, "Find Condition dropdown").selectByIndex(1, "Select Greater then option");
			findElement(Value_Box, "Find Value Edit box").sendKeys("80", "Enter some value");
  			
			driver.findElement(By.xpath("//*[@id='addOSSForm']/div[1]/div[3]/button")).click();	
			_assert.equals(SecondAttribute_Box);
  	}catch(Exception e){
  		Log.error("New Attribute row not added");
  		throw(e);
  	}
    }
     
     public void AlertMessage_DeleteAttribute(String Actual) throws Exception{
    	 	
  		try{
  			findElement(Delete_Button, "Find Delete Icon").click("Click to Delete");;		
  			logger.log(LogStatus.INFO, "Trying to delete default row");
  			String alertText = driver.switchTo().alert().getText();
  			Log.info(alertText);
  			//Thread.sleep(2000);
  			alertAccept("Click on Ok");
  			_assert.contains(Actual, alertText, "Check the alert message");

  	}catch(Exception e){
  		Log.error("Not able to delete add Attribute");
  		throw(e);
  	}	
  	}
     
     public void multipleFields_AddRule(String New_Rule, String Temprature, String Value) throws Exception{
 	 	
   		try{
   			Add_NewRule = New_Rule +"_"+randomNumber();
			New_Attribute = Temprature +" "+randomNumber();
			Value = ""+randomNumber();

			Thread.sleep(3000);
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").click("Click to expand dropdown");
			Thread.sleep(2000);
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").selectByIndex(1, "Selecting Customer from dropdown");
			findElement(RuleName_Box, "Find Rule Name edit box").sendKeys(Add_NewRule, "Enter the rule name");
			findElement(AtriName_Box, "Find Attribute Name edit box").sendKeys(New_Attribute, "Enter something as Attribute");
			findElement(Condition_dropdown, "Find Condition dropdown").selectByValue(">", "Greater then condition");
			findElement(Value_Box, "Find Value Edit box").sendKeys(Value, "Enter some value");
			
			findElement(AddNewAttri_Button, "Find Add New Attribute button").click("Click on button");
			Thread.sleep(1000);
			findElement(AtriName_2ndBox, "Find Attribute Name edit box").sendKeys(New_Attribute, "Enter something as Attribute");
			findElement(Condition_2ndropdown, "Find Condition dropdown").selectByValue("<", "Less then condition");
			findElement(Value_2ndBox, "Find Value Edit box").sendKeys(Value, "Enter some value");
			findElement(Create_button, "Find Create Button").click("Click after entering all Information");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Success));
			_assert.contains(findElement(alert_Success, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
			"Verify Rule Creation Success Message");
   	}catch(Exception e){
   		Log.error("Not able to create New Rule with multiple attribute");
   		throw(e);
   	}	
   	}
     
     public void addAttributeRow_Delete(String New_Rule, String Temprature, String Value) throws Exception{
  	 	
    		try{
    			Add_NewRule = New_Rule +"_"+randomNumber();
 			New_Attribute = Temprature +" "+randomNumber();
 			Value = ""+randomNumber();
 			
 			Thread.sleep(2000);
 			findElement(CustName_Dropdown, "Find Customer Name Dropdown").click("Click to expand dropdown");
 			Thread.sleep(2000);
			findElement(CustName_Dropdown, "Find Customer Name Dropdown").selectByIndex(1, "Selecting Customer from dropdown");
 			findElement(RuleName_Box, "Find Rule Name edit box").sendKeys(Add_NewRule, "Enter the rule name");
 			findElement(AtriName_Box, "Find Attribute Name edit box").sendKeys(New_Attribute, "Enter something as Attribute");
 			Thread.sleep(1000);
 			findElement(Condition_dropdown, "Find Condition dropdown").selectByValue(">", "Greater then condition");
 			findElement(Value_Box, "Find Value Edit box").sendKeys(Value, "Enter some value");
 			findElement(AddNewAttri_Button, "Find Add New Attribute button").click("Click on button");
 			Thread.sleep(1000);
 			
 			findElement(AtriName_2ndBox, "Find Attribute Name edit box").sendKeys(New_Attribute, "Enter something as Attribute");
 			Thread.sleep(1000);
 			findElement(Condition_2ndropdown, "Find Condition dropdown").selectByValue("<", "Less then condition");
 			findElement(Value_2ndBox, "Find Value Edit box").sendKeys(Value, "Enter some value");
 			findElement(Delete_2ndButton, "Find Delete Button").click("Click after entering all Information");
    	}catch(Exception e){
    		Log.error("Not able to delete added attribute row");
    		throw(e);
    	}	
    	}
     
     public void Delete_UnassignedRule() throws Exception{
   	 	
 		try{
			findElement(Rule_DeleteButton, "Find Delete Button").click("Click to Delete un-associated rule");
			String alertText = driver.switchTo().alert().getText();
			Log.info(alertText);
  			//Thread.sleep(2000);
  			alertAccept("Click on Ok");
  			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_Delete));
			_assert.contains(findElement(alert_Delete, "Check for Deletion success alert").getAttribute(Attributes.TEXT),"Success", 
			"Verify Rule Deletion success message");
			
 	}catch(Exception e){
 		Log.error("Not able to delete added Rule");
 		throw(e);
 	}	
 	}

      public void Delete_AssignedRule() throws Exception{
	 	
		try{
			if (!findElement(By.xpath("(.//span[text()='Routing Rule'])/.."),"Routing Role Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='Routing Rule']")).click();
				Thread.sleep(2000);
			}
			findElement(Manage_RulesPage, "Find Manage Rules under Routing Rule tile").click("Click on Manage Rules");
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, ManageRuletitle, "Verifying page title");	
			Log.error("Manage Rule page not opening");
			
		findElement(Rule_DeleteButton, "Find Delete Button").click("Click to Delete un-associated rule");
		String alertText = driver.switchTo().alert().getText();
		Log.info(alertText);
			//Thread.sleep(2000);
			alertAccept("Click on Ok");
		
	}catch(Exception e){
		Log.error("Able to delete Assigned Rule");
		throw(e);
	}	
	}
      
      public void AppliedRule_Conditionpopup() throws Exception{
  	 	
  		try{
  			Thread.sleep(1000);
  			findElement(Applied_RulePage, "Find Applied Rules under Routing Rule").click("Click on Applied Rules");
  			Thread.sleep(1000);
  			String pageName = findElement(By.xpath("//*[@bcmodulename='Device Management']"), "Page Title").getAttribute(Attributes.TEXT);
  			_assert.equals(pageName, AppliedRuletitle, "Verifying page title");	
  			findElement(Condition_button, "Find Conditions option").click("Click on Conditions");
  			findElement(Cancel_inPopup, "Find Cancel in the popup").click("Click to close the Popup");;
  	 }catch(Exception e){
  		Log.error("Conditions popup not opening");
  		throw(e);
  	 }	
  	}     
    
      public void Search_AddedRule() throws Exception{
    	 	
    		try{
    			String RuleName = prop.getProperty("routing_rule");
    			String Value = driver.findElement(By.xpath("//*[@st-table='ruleList']/tbody/tr/td[2]")).getText();
    			findElement(Search_Link, "Find Search Link").click("Click on Search to open Search Bar");
    			Thread.sleep(1000);
    			findElement(SearchRule_Box, "Find Rule Name search box").sendKeys(prop.getProperty("routing_rule"), "Enter Added Rule Name");
                findElement(Search_Button, "Find Search Button").click("Click on Search Button");
    			Thread.sleep(2000);
    			_assert.contains(RuleName, Value, "Compare the value");
    	}catch(Exception e){
    		Log.error("Not able to search for Rule");
    		throw(e);
    	}	
    	}   
      
      public void Search_ByCustName() throws Exception{
  	 	
  		try{
  			
  			String Value = driver.findElement(By.xpath("//*[@st-table='ruleList']/tbody/tr/td[3]")).getText();
  			findElement(Search_Link, "Find Search Link").click("Click on Search to open Search Bar");
  			Thread.sleep(1000);
  			findElement(Cust_Drodpown, "Find Customer Dropdown").click("Click to expand dropdown");
  			findElement(SearchBox, "Expand SearchBox").sendKeys(Value, "Select Customer");
  			Thread.sleep(2000);
  			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@role='menu']/li[2]")));
			findElement(By.xpath(".//*[@role='menu']/li[2]/a/div"),"Customer Drop Down List");
			
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("arguments[0].click();", _WebElement);
			
			findElement(IncludeSubGroup, "Find Include Sub group checkbox").click("Check the checkbox");
  			findElement(Search_Button, "Find Search Button").click("Click on Search Button");
  			
  			Thread.sleep(2000);
  			_assert.contains(findElement((By.xpath("//*[@st-table='ruleList']/tbody/tr/td[3]")), "Get Customer ID").getAttribute(Attributes.TEXT),"IoT", 
  					"Verify Rule Deletion success message");
  			//_assert.contains(AssertVal, Value, "Compare the value");
  	  }catch(Exception e){
  		Log.error("Not able to search for Rule");
  		throw(e);
  	 }	
  	}  
      
      public void DeleteRule_InDataProp() throws Exception{
    	 	
    		try{
    			findElement(Rule_CrossSign, "Find Added Rule").click("Click on cross to delete Rule");
    			String alertText = driver.switchTo().alert().getText();
    			Log.info(alertText);
    				//Thread.sleep(2000);
    				alertAccept("Click on Ok");
    			
    			Thread.sleep(1000);
    			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_RuleDelete));
    			_assert.contains(findElement(alert_RuleDelete, "Check for Deletion success alert").getAttribute(Attributes.TEXT),"Rule", 
    					"Verify Rule Deletion success message");
    	  }catch(Exception e){
    		Log.error("Not able to delete added Rule");
    		throw(e);
    	 }	
    	}
      
      public void Editing_AppliedRule(String Actual) throws Exception{
  	 	
  		try{
  			findElement(Edit_Icon, "Find Edit Icon").click("Click to Edit Rule");
  			String alertText = driver.switchTo().alert().getText();
  			Log.info(alertText);
  				//Thread.sleep(2000);
  			alertAccept("Click on Ok");
  			Thread.sleep(1000);
  			_assert.contains(Actual, alertText, "Check the alert message");
  	  }catch(Exception e){
  		Log.error("Able to Edit applied Rule");
  		throw(e);
  	 }	
  	}
      
      public void Editing_UnAppliedRule(String Temprature, String Value) throws Exception{
    	 	
    		try{
    			New_Attribute = Temprature +" "+randomNumber();
    			Value = ""+randomNumber();
    			
    			if (!findElement(By.xpath("(.//span[text()='Routing Rule'])/.."),"Routing Role Tile").getAttribute(Attributes.GENERAL,"class")
    					.contains("selected")) {
    				Thread.sleep(2000);
    				driver.findElement(By.xpath(".//span[text()='Routing Rule']")).click();
    			}
    			findElement(Manage_RulesPage, "Find Manage Rules under Routing Rule").click("Click on Manage Rules");
    			findElement(Edit_Icon, "Find Edit Icon").click("Click to Edit Rule");
    			String pageName = findElement(By.xpath("//*[@bcmodulename='Routing Rules']"), "Verifying Page Title").getAttribute(Attributes.TEXT);
    			_assert.equals(pageName, EditRuletitle, "Verifying page title");
    			driver.findElement(By.xpath("//*[@id='assetAttribTab']/tbody[2]/tr/td[1]/div/input")).clear();
    			findElement(AtriName_Box, "Find Attribute Name edit box").sendKeys(New_Attribute, "Enter Attribute type");
    			findElement(Condition_dropdown, "Find Condition dropdown").selectByValue("<", "Select condition");
    			findElement(Value_Box, "Find Value Edit box").sendKeys(Value, "Enter some value");
    			
    			findElement(Create_button, "Find Create Button").click("Click after entering all Information");
    			Thread.sleep(1000);			
    			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_UpdateSuccess));
    			_assert.contains(findElement(alert_UpdateSuccess, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
    			"Verify Rule Creation Success Message");
    			
    	  }catch(Exception e){
    		Log.error("Not able to edit UnApplied rule");
    		throw(e);
    	 }	
    	}
      
      public void AddAttribute_EditRule(String Temprature, String Value) throws Exception{
  	 	
  		try{
  			New_Attribute = Temprature +" "+randomNumber();
  			Value = ""+randomNumber();
  			
  			if (!findElement(By.xpath("(.//span[text()='Routing Rule'])/.."),"Routing Role Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[text()='Routing Rule']")).click();
			}
  			findElement(Manage_RulesPage, "Find Manage Rules under Routing Rule").click("Click on Manage Rules");
  			findElement(Edit_Icon, "Find Edit Icon").click("Click to Edit Rule");
  			String pageName = findElement(By.xpath("//*[@bcmodulename='Routing Rules']"), "Verifying Page Title").getAttribute(Attributes.TEXT);
  			_assert.equals(pageName, EditRuletitle, "Verifying page title");
  			
  			findElement(AddNewAttri_Button, "Find Add new Attribute").click("Click to add Attribute");
  			findElement(AtriName_2ndBox, "Find Attribute Name edit box").sendKeys(New_Attribute, "Enter something as Attribute");
			findElement(Condition_2ndropdown, "Find Condition dropdown").selectByValue("<", "Less then condition");
			findElement(Value_2ndBox, "Find Value Edit box").sendKeys(Value, "Enter some value");  			
  			findElement(Create_button, "Find Create Button").click("Click after entering all Information");
  			Thread.sleep(1000);			
  			wait.until(ExpectedConditions.visibilityOfElementLocated(alert_UpdateSuccess));
  			_assert.contains(findElement(alert_UpdateSuccess, "Check for success alert").getAttribute(Attributes.TEXT),"Success", 
  			"Verify Rule Creation Success Message");
  			
  	  }catch(Exception e){
  		Log.error("Not able to add new Attribute while editing a rule");
  		throw(e);
  	 }	
  	}
}
