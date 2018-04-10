package com.automation.pageobjects;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class PolicyManagement extends ElementBase {

	AssetMgmt ast=new AssetMgmt();
	final static Logger Log = Logger.getLogger(PolicyManagement.class.getName());
	public static String qos_name   = null;
	public static String key        =null;
	public static String key1       =null;
	public static String value      =null;
	public static String value1     =null;
	public static String Description=null;
	public static int reandom_no    =0;
	public static String asset = "";
	public static ArrayList<String> assetList = new ArrayList<String>();
	
	
	By Qos_DashBoard=By.xpath("//span[contains(text(),'QoS Policy')]");
	By Add_Policy   =By.xpath("//li[contains(text(),'Add Policy')]");
	By Policy_Name  =By.xpath("//input[@placeholder='Policy Name']");
	By Key_holder   =By.xpath("//input[@placeholder='key']");
	By value_holder =By.xpath("//input[@placeholder='value']");
	By Create       =By.xpath(".//*[@id='createpolicy']");
	By Success      =By.xpath("//span[contains(text(),'Success')]");
	By Error        =By.xpath("//span[contains(text(),'Error')]");
	By Key_Plus     =By.xpath("//div[input[@placeholder='Description']]/following-sibling::div/a/i");
	By Dynamic_Key  =By.xpath("//div[div[label[contains(text(),'Policy Definition')]]]/following-sibling::div[3]/div/div[1]/input");
	By Dynamic_value=By.xpath("//div[div[label[contains(text(),'Policy Definition')]]]/following-sibling::div[3]/div/div[2]/input");
	By Edit         =By.xpath("//thead[tr[th[contains(text(),'Edit')]]]/following-sibling::tbody/tr[1]/td[4]/a");
	By Manage_Policy=By.xpath("//li[contains(text(),'Manage Policy')]");
	By Update_Button=By.xpath("//input[@id='createpolicy']");
	By Search_Button=By.xpath("//i[@class='icon icon-search']");
	By QosNameSearch=By.xpath("//input[@placeholder='QoS Name']");
	By Search_Click =By.xpath("button[ng-click='searchQoS()']");
	By Name_Search  =By.xpath("//div[div[ul[li[contains(text(),'Policy List')]]]]/following-sibling::table/tbody/tr[1]/td[1]/a");
	By Searched_Name=By.xpath("//div[div[ul[li[contains(text(),'Policy List')]]]]/following-sibling::table/tbody/tr[1]/td[1]/a");
	By Select       =By.xpath("//span[text()='Select']");
	By Select_Cusm  =By.xpath("//a[span[text()='Select']]/following-sibling::div/ul/li[2]/a/div[1]/span");
	By Delete_Policy=By.xpath("//thead[tr[th[text()='Delete']]]/following-sibling::tbody/tr[1]/td[5]/a/i");
	By Add_device_profile= By.xpath(".//*[@id='deviceP_1']");
	
	
	
	
	public void Validate_Upload_QoS_Device_Profile() throws Exception{
		try {
			
			if (!findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
                     Thread.sleep(2500);
				findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"").click("Click on Add device profile");
				Thread.sleep(2000);
				}
            findElement(Add_device_profile, "Goto Add Device Manufacturer Profile").click("Click to add Device Profile");
            findElement(By.xpath(".//*[@id='treeCheck_"+SmokeTest.CustID+"']"),"Select the Customer").click("check the tree");
    		findElement(By.xpath(".//*[@type='file']"),"Goto Upload the file").sendKeys(System.getProperty("user.dir") + "\\testdata\\QoS.xml","Click on Upload");
    		findElement(By.xpath("//div[label[contains(text(),'Container Profile')]]/div/select"),"goto device profile  browser").click("click on Browse");
    		findElement(By.xpath("//div[label[contains(text(),'Container Profile')]]/div/select/option[2]"),"goto container profile browser").click("");
    		findElement(By.xpath("//button[contains(text(),'Submit')]"),"Goto Submit").click("Click on Submit");
            
		} 
		catch (Exception e) 
		{
			Log.error("Unable to upload the file");
			Log.error(e.getMessage().toString());
			throw (e);	
		}
    		
		
	}
	
	public void Verify_if_a_new_policy_can_be_created() throws Throwable{
		try {
			if (!findElement(By.xpath("//span[contains(text(),'QoS Policy')]"),"QoS Policy Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
                Thread.sleep(2500);
				findElement(By.xpath("//span[contains(text(),'QoS Policy')]"),"QoS Policy Tile").click("CLick on QoS Policy Tile");
				Thread.sleep(2000);
				}
			
			qos_name="QOS_CLASS:"+randomNumber();
			key="Key"+randomNumber();
			value="value"+randomNumber();
						
			findElement(Add_Policy,"Goto Add Policy").click("Click on add policy");
			findElement(Policy_Name, "Goto_Policy_Nmae").sendKeys(qos_name, "Policy_Name");
			findElement(Key_holder, "Goto_Key").sendKeys(key, "provied key name");
			findElement(value_holder, "Goto_value_holder").sendKeys(value, "Value_name");
			findElement(Create, "Goto_Crteate").click("Click on create button");
			
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success", "Created Profile is verified");
		} catch (Exception e) {
			Log.error("Unable to find the msg--Update is failed");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void Validate_if_user_can_add_key_value_pair_dynamically() throws Throwable{
		try {
			if (!findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Policy Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
                Thread.sleep(2500);
				findElement(By.xpath("//span[contains(text(),'QoS Policy')]"),"QoS Policy Tile").click("Click on QoS Policy");
				Thread.sleep(2000);
				}
			
			
			
			qos_name="QOS_CLASS:"+randomNumber();
			key="Key"+randomNumber();
			value="value"+randomNumber();
			key1="Key1"+randomNumber();
			value1="value1"+randomNumber();

			
			findElement(Add_Policy,"Goto Add Policy").click("Click on add policy");
			findElement(Policy_Name, "Goto_Policy_Nmae").sendKeys(qos_name, "Policy_Name");
			findElement(Key_holder, "Goto_Key").sendKeys(key, "provied key name");
			findElement(value_holder, "Goto_value_holder").sendKeys(value, "Value_name");
			findElement(Key_Plus, "Add_new_Dynamic_Key").click("Click on Dynamic key butoon");;
			findElement(Dynamic_Key, "Enter_Dynamic_Key").sendKeys(key1, "Enter_Dynamic_key");
			findElement(Dynamic_value, "Provied_Dynamic_Value").sendKeys(value1, "Enter_value");
			findElement(Create, "Goto_Crteate").click("Click on create button");
			
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success", "Created Profile is verified");
		} catch (Exception e) {
			Log.error("Unable to verify dynamic Key");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void verify_error_message_if_QoS_name_is_not_in_specific_format() throws Throwable{
		try {
			if (!findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
                Thread.sleep(2500);
				findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Tile").click("Click on QoS Tile");
				Thread.sleep(2000);
				}
			
			qos_name="QoS"+randomNumber();
			key="Key"+randomNumber();
			value="value"+randomNumber();
						
			findElement(Add_Policy,"Goto Add Policy").click("Click on add policy");
			findElement(Policy_Name, "Goto_Policy_Nmae").sendKeys(qos_name, "Policy_Name");
			findElement(Key_holder, "Goto_Key").sendKeys(key, "provied key name");
			findElement(value_holder, "Goto_value_holder").sendKeys(value, "Value_name");
			findElement(Create, "Goto_Crteate").click("Click on create button");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-danger']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-danger']"),"Error Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Error", "Verify Error Message");
		} catch (Exception e) {
			Log.error("Unable to Verify Error Message");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void Verify_QoS_canbe_updated() throws Exception{
     
		try{
			if (!findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
                Thread.sleep(2500);
				findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Tile").click("Click on QOS Tile");
				Thread.sleep(2000);
				}
			
			key="Key"+randomNumber();
			value="value"+randomNumber();
			
			 findElement(Manage_Policy,"Goto_Manage_Policy").click("Click on manage_Policy");
			 findElement(Edit,"Goto_Edit").click("click on edit button");
			 findElement(Key_holder,"Goto_key_holder").sendKeys(key,"Update_send_the _key");
			 findElement(value_holder, "Goto_value_holder").sendKeys(value, "Update_value");
			 findElement(Update_Button,"Goto_Update").click("Click_on_Update_Button");
			 
			 
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
				String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
				_assert.contains(actual, "Success", "Updated Profile is verified");

           }
     catch (Exception e){
	       Log.error("Unable to Update Qos message");
	       Log.error(e.getMessage().toString());
	       throw(e);
      }
	}
	
   public void Verify_QoS_list_can_displayed() throws Exception{
	   try{
		   if (!findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Policy Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
               Thread.sleep(2500);
				findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Policy").click("Click on QoS Policy");
				Thread.sleep(2000);
				}
		   
		   findElement(Manage_Policy,"Goto_Manage_Policy").click("Click on manage_Policy");
		   findElement(Search_Button,"Goto Searched button").click("Click_Search_Button");
		   String QOs_Name=findElement(Name_Search,"The_Name_have_to_Search").getAttribute(Attributes.TEXT);
		   System.out.println(qos_name);
		   findElement(QosNameSearch, "Goto_Name_Search_Field").sendKeys(QOs_Name, "fill the field with required name");
		   findElement(By.cssSelector("button[ng-click='searchQoS()']"), "Click on the search").click("Click the search button");
		   String actual=findElement(Searched_Name, "Qos_Name_found").getAttribute(Attributes.TEXT);
		   
		   
		   System.out.println(actual);
		   _assert.contains(actual,QOs_Name,"Qos List is Displayed ");
	   }
	   catch(Exception e){
		   Log.error("Unable to verify Qos List");
		   Log.error(e.getMessage().toString());
		   throw (e);
	   }
   }
   
   public void Verify_QoS_provisioned_for_customers() throws Exception{
	   
	   try {
		
		   if (!findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
              Thread.sleep(2500);
				findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Tile").click("Click on QoS Tile");
				Thread.sleep(2000);
				}
		   
		   reandom_no=randomNumber();
		    qos_name="QOS_CLASS:"+reandom_no;
			key="Key"+randomNumber();
			value="value"+randomNumber();
						
			findElement(Add_Policy,"Goto Add Policy").click("Click on add policy");
			findElement(Policy_Name, "Goto_Policy_Nmae").sendKeys(qos_name, "Policy_Name");
			findElement(Key_holder, "Goto_Key").sendKeys(key, "provied key name");
			findElement(value_holder, "Goto_value_holder").sendKeys(value, "Value_name");
			findElement(Select, "Goto select Customer").click("Click on Select Customer");
			findElement(Select_Cusm, "Select specified Customer").click("select Customer");
			findElement(Create, "Goto_Crteate").click("Click on create button");
			
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success", "Created Profile is verified");
	} catch (Exception e) {
		
		   Log.error("Unable to Qos_Provisend for Customer");
		   Log.error(e.getMessage().toString());
		   throw (e);
		
	}
   }
   
   public void Specify_the_policy_name_during_device_provisioning(boolean autoProvision,boolean generateKeyImport) throws Exception {
	   try{
		   Verify_QoS_provisioned_for_customers();
		   
		   if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmgt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
				}
		  

			driver.findElement(By.id("asset_2")).click();

			asset = prop.getProperty("asset_name") + randomNumber();

			

			findElement(By.name("resourceName"),"Goto asset").sendKeys(asset,"Sent the asset key");

            findElement(By.xpath(".//*[@id='resourceType']"),"Goto Asset Type").click("Click on asset type");
			new Select(driver.findElement(By.id("resourceType"))).selectByValue("SENSOR");

			driver.findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/a")).click();

			findElements(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li"),"Get Customer Count");
			
			for(int i=1 ; i<=_WebElements.size();i++){
				
				Thread.sleep(2000);
				String actual = _WebElements.get(i).getText();
				if(actual.trim().contains(CustomerManagement.Cust_Name.toUpperCase())){
					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}
				
			}
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")));

			driver.findElement(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")).click();

			findElements(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/ul/li"),"Get Group Count");

			Thread.sleep(2000);
			
			for(int i=1 ; i<=_WebElements.size();i++){
				
				Thread.sleep(2000);
				
				String actual = _WebElements.get(i).getText();
				String device_Group="DEFAULT";
				if(actual.trim().contains(device_Group)){
					
					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}
				
			}

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));

			driver.findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"))
					.click();

			findElements(By.xpath("(.//*[@class='custom-select-search'])[3]/../../div/ul/li"),"Get DP Count")
					;
			
			for(int i=1 ; i<=_WebElements.size()-1;i++){
				
				Thread.sleep(2000);
				
				String actual = _WebElements.get(i).getText();
				String Device_Profile="QoS1-QoS1 Vehicle Transponder v13.0-1.4";
				if(actual.contains(Device_Profile)){
					
					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}
				
			}


			if (autoProvision == false) {

				driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div/input")).click();
			}

			findElement(By.id("iotParamsQoS Class"),"Send the Qos_Policy_ID").sendKeys(String.valueOf(reandom_no),"Send the random_no");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[text()='Create'])[1]")));
			driver.findElement(By.xpath("(.//*[text()='Create'])[1]")).submit();

			Log.info("Asset Created : " + asset);
	   }
	   catch(Exception e){
		   Log.error("Unable to Specify Device provisnig");
		   Log.error(e.getMessage().toString());
           throw (e);
	   }
   }
	
   public void delete_fails_for_policy_if_devices_assigned() throws Exception{
	   try {
		
		   if (!findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Policy Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
               Thread.sleep(2500);
				findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Policy Tile").click("Click on QoS Policy");
				Thread.sleep(2000);
				}
		   
		   findElement(Manage_Policy,"Goto_Manage_Policy").click("Click on manage_Policy");
		   findElement(Delete_Policy,"Goto Delete button").click("Click the delete button");
		   alertAccept("accept the alert");
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-danger']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-danger']"),"Error Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Error", "Verify Error Message");

		   } 
	   catch (Exception e) {
		   Log.error("Unable to delete policy");
		   Log.error(e.getMessage().toString());
           throw (e);
	}
   }
   
   
   public void integer_part_of_policyName_should_assigned_device(){
	   try {
		   if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
				
				findElement(By.xpath(".//*[@id='asset_3']"), "Goto_Manage_Asset").click("Manage_Asset");
				findElement(By.xpath("//thead[tr[th[contains(text(),'Asset Name')]]]/following-sibling::tbody/tr[1]/td[2]/a"), "Goto Asset Name").click("Click on asset Name");
				String Intiger_Value=findElement(By.xpath("//div[label[contains(text(),'QoS Class')]]//following-sibling::div/span"), "Goto QoS policy Intiger Value").getAttribute(Attributes.TEXT);
				_assert.contains(Intiger_Value,String.valueOf(reandom_no), "Verify intiger value");
				}
		   

	} 
	   catch (Exception e) {
		   Log.error("Unable to assigend Intiger Part to policy");
		   Log.error(e.getMessage().toString());
		   throw (e);
	}
   }
   
   
   public void verify_a_policy_name_changed_new_QoS_name() throws Exception{
	   try{
		   Verify_QoS_provisioned_for_customers();
		   if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
				}
   findElement(By.id("asset_3"), "Goto asst Mgt").click("Click on asst Mgt");
   findElement(By.xpath("//thead[tr[th[span[contains(text(),'Edit')]]]]/following-sibling::tbody/tr[1]/td[9]/a"), "Goto Edit Asset").click("click Edit");
   findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
   
   Thread.sleep(3000);
   findElements(By.xpath("(.//*[@class='custom-select-search'])[2]/../../div/ul/li"),"Get DP Count");
			
	for(int i=1 ; i<=_WebElements.size()-1;i++){
		
		Thread.sleep(2000);
		
		String actual = _WebElements.get(i).getText();
		String Device_Profile="QoS1-QoS1 Vehicle Transponder v13.0-1.4";
		if(actual.contains(Device_Profile)){
			
			_WebElements.get(i).click();
			Thread.sleep(2000);
			break;
		}
		
	}
   findElement(By.id("iotParamsQoS Class"),"Send the Qos_Policy_ID").sendKeys(String.valueOf(reandom_no),"Send the random_no");
   findElement(By.xpath("//button[contains(text(),'Update')]"),"Goto Update").click("click on Update button");
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success ng-scope']")));
   String actual = findElement(By.xpath(".//*[@class='alert alert-success ng-scope']"),"Success Message").getAttribute(Attributes.TEXT); 
	_assert.contains(actual, "Success!", "Delete is successfull");
	   }
	   catch(Exception e){
		   Log.error("Unable to change policy name");
		   Log.error(e.getMessage().toString());
		   throw (e);
	   }
   }
   
   public void Delete_QoS_no_Device_assigend() throws Throwable {
	   
	 Verify_if_a_new_policy_can_be_created();
	 try {
		 if (!findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
            Thread.sleep(2500);
				findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Policy").click("Click on QoS Policy");
				Thread.sleep(2000);
				}
		   findElement(Manage_Policy,"Goto_Manage_Policy").click("Click on manage_Policy");
		   findElement(Delete_Policy,"Goto Delete button").click("Click the delete button");
		   alertAccept("accept the alert");
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
		   String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success", "Delete is successfull");
		 
	} catch (Exception e) {
		   Log.error("Unable to delete Policy");
		   Log.error(e.getMessage().toString());
		   throw (e);
	}
	   
	}
   public void key_value_pairs_deleted() throws Throwable{
	   Validate_if_user_can_add_key_value_pair_dynamically();
	   try {
		   if (!findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Policy Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
               Thread.sleep(2500);
				findElement(By.xpath("(//span[contains(text(),'QoS Policy')])/.."),"QoS Policy Tile").click("Click on QoS Policy Tile");
				Thread.sleep(2000);
				}
		   findElement(Manage_Policy,"Goto_Manage_Policy").click("Click on manage_Policy");
		   findElement(By.xpath("//thead[tr[th[contains(text(),'Edit')]]]/following-sibling::tbody/tr/td[4]/a/i"),"Goto Edit Button").click("Click on Edit Button");
		   findElement(By.xpath("//section[div[div[label[contains(text(),'Policy Definition')]]]]/div[4]/div/div[4]/a[1]"),"Goto Minus button").click("Click on minus button");
		   findElement(By.id("createpolicy"),"Goto Update button").click("Click to update button");
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
		   String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
		  _assert.contains(actual, "Success", "Delete is successfull");
		   
	} catch (Exception e) {
		Log.error("Unable to delete Multiple Key Value Pair");
		Log.error(e.getMessage().toString());
		throw (e);
	}
   }

   }

