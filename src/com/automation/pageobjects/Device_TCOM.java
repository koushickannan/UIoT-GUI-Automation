package com.automation.pageobjects;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hpe.base.Attributes;
import com.hpe.utilities.CSVUtils;
import com.hpe.webdriver.ElementBase;

public class Device_TCOM extends ElementBase {

	final static Logger Log = Logger.getLogger(Device_TCOM.class.getName());
	public static ArrayList<String> assetList = new ArrayList<String>();
	public static String asset                = "";
	public static int reandom_no              =0;
	public static int rand_no                 =1234567890;
	public static JavascriptExecutor jse;
   PolicyManagement poly=new PolicyManagement();
	
	By Device_Manufact_Profile            = By.xpath("//span[contains(text(),'Device Manufacturer Profile')]");
	By Search_Device_Manufacturer_Profile = By.id("deviceP_2");
	By Device_profile                     = By.xpath("//tr[1]/td[1]/a");
	By View_XML                           = By.xpath("//input[@value='View Raw XML']");
	By Xml_Data                           = By.id("dp-xml-viewer");
	By Title_Xml_Data                     = By.xpath(".//*[contains(text(),'XML Data')]");
	By update_xml_data                    = By.xpath(".//*[@id='myModal']/div[2]/div/div[3]/button[2]");
	By success_msg                        = By.xpath("//div[1]/strong");
	By Add_device_Manufacturer_profile    = By.xpath(".//*[@id='deviceP_1']");
	By Customer_Name                      = By.xpath(".//*[@id='Node_"+SmokeTest.CustID+ "']/div");
	By Tree_Check                         = By.xpath(".//*[@id='treeCheck_" + SmokeTest.CustID + "']");
	By Tree_hirearchy_SubGroup            = By.xpath("//li[span[contains(text(),'IOT')]]/ul/li[1]/div");
	By Clicking_SubGroup                  = By.xpath("//li[span[contains(text(),'IOT')]]/ul/li[1]/div/following-sibling::ul/li[1]/div");
	By Selecting_SubGroup                 = By.xpath("//li[span[contains(text(),'IOT')]]/ul/li[1]/div/following-sibling::ul/li[1]/input");
	By Browse_Device_XML_Profile          = By.xpath(".//*[contains(text(),'Device Profile (XML)')]/following-sibling::div/div/span/li/input");
	By Select_Container_Profile           = By.xpath("//div[label[contains(text(),'Container Profile')]]/div/select");
	By Edit_Remap                         = By.xpath("//thead[tr[th[text()='Remap Entity/Device Group']]]/following-sibling::tbody/tr[1]/td[8]/a[1]/i");
	By Update_Butoon                      = By.xpath("//input[@id='addruleBtn']");
	By Success_Msg                        = By.xpath(".//*[Contains(text(),'Success!')]");
	By device_name                        = By.id("displayName");
	By device_file_browse                 = By.id("fileId");
	By device_upload                      = By.xpath(".//*[@value='Import']");
	By asset_mgmt_dashboard               = By.xpath(".//*[text()='Asset Mgmt.']");
	By import_asset                       = By.xpath(".//a[@href='/dsm/assetmgmt/importAsset.htm']/li");
	By import_file_select                 = By.id("importTypeSelect");
	
	
	public void Validate_Upload_TCOM_Device_Profile() throws Exception{
		try {
			
			if (!findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
                     Thread.sleep(2500);
                    
				findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"").click("Click on Add device profile");
				Thread.sleep(2000);
				}
            findElement(Add_device_Manufacturer_profile, "Goto Add Device Manufacturer Profile").click("Click to add Device Profile");
            findElement(By.xpath(".//*[@id='treeCheck_"+SmokeTest.CustID+"']"),"Select the Customer").click("check the tree");
    		findElement(By.xpath(".//*[@type='file']"),"Goto Upload the file").sendKeys(System.getProperty("user.dir") + "\\testdata\\maniplase.xml","Click on Upload");
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
	public void create_Asset_with_TCOM_DeviceProfile(boolean autoProvision,boolean generateKeyImport,boolean autoRegistration ) throws Exception{
		try {
			
			   
			   if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
						.contains("selected")) {
					findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
					}
			  

				driver.findElement(By.id("asset_2")).click();

				asset = prop.getProperty("asset_name") + randomNumber();

				

				findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"");

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
					
					if(actual.trim().contains(SmokeTest.Group.toUpperCase())){
						
						_WebElements.get(i).click();
						Thread.sleep(2000);
						break;
					}
					
				}

				wait.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));

				findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
	            findElements(By.xpath("(.//*[@class='custom-select-search'])[3]/../../div/ul/li"),"Get DP Count");
						
				
				for(int i=1 ; i<=_WebElements.size()-1;i++){
					
					Thread.sleep(2000);
					
					String actual = _WebElements.get(i).getText();
					String Device_Profile="kapil-Soman-17.0";
					if(actual.contains(Device_Profile)){
						
						_WebElements.get(i).click();
						Thread.sleep(2000);
						break;
					}
					
				}

                   if (autoProvision ==true) {
                  driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div/input")).click();
				}
                   
                   findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");
                 reandom_no=rand_no+randomNumber();
				findElement(By.id("iotParamsDevEUI"), "Goto DevEUI").sendKeys(String.valueOf(reandom_no), "Send the int value");
				findElement(By.id("iotParamsDevAddr"),"Goto DevAddr").sendKeys(String.valueOf(reandom_no), "Send the keys");
				findElement(By.id("iotParamsAppEUI"),"Goto AppEUI").sendKeys(String.valueOf(reandom_no),"Send the keys");
				findElement(By.xpath("//li[1]/input[@name='assetParams_FlowId']"), "Goto Genric flow").click("Click on Generic flow");
				driver.findElement(By.id("iotParamsappskey")).sendKeys(Keys.CONTROL, "a");
				driver.findElement(By.id("iotParamsappskey")).sendKeys("");
				driver.findElement(By.id("iotParamsappskey")).sendKeys(String.valueOf(reandom_no));
				findElement(By.xpath("//input[@name='assetParams_PreferedDC']"),"Goto Preference DC").click("Click on Preference DC");
				findElement(By.xpath("(.//*[text()='Create'])[1]"),"Goto Create").submit("click on submit");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
				String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
				_assert.contains(actual, "Success", "Updated Profile is verified");
				Log.info("asset created:" + asset);
		} catch (Exception e) {
			Log.error("Unable to Create Asset_TCOM Device");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	}

	 public void without_autoreg_keygenration(boolean autoProvision,boolean generateKeyImport,boolean autoRegistration) throws Exception{
		 try {
			
			 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
						.contains("selected")) {
					findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt").click("Click on Asset Mgmt");
					}
			  

				driver.findElement(By.id("asset_2")).click();

				asset = prop.getProperty("asset_name") + randomNumber();

				

				findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"asset key Sent");

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
					
					if(actual.trim().contains(SmokeTest.Group.toUpperCase())){
						
						_WebElements.get(i).click();
						Thread.sleep(2000);
						break;
					}
					
				}
				
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
		         findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
			     findElement(By.xpath("//label[contains(text(),'Device Manufacturer Profile')]/following-sibling::div/div/div/div[1]/input"),"goto_search text").sendKeys("Kapil", "sent  keys");
				 findElement(By.xpath("//span[contains(text(),'kapil-Soman-17.0')]"),"Goto Milon Profile").click("Click on the Profile");

				 
				
              if (autoProvision == true) {
               driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div[1]/input")).click();
                             }
              if(autoRegistration==true){
               findElement(By.xpath("//section[header[contains(text(),'Asset Details')]]/div/div/div[7]/div[2]/div[1]/input"),"goto Auto Registration").click("Click on Check box");
              }
                //findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");
                reandom_no=rand_no+randomNumber();
				findElement(By.id("iotParamsDevEUI"), "Goto DevEUI").sendKeys(String.valueOf(reandom_no), "Send the int value");
				System.out.println();
				findElement(By.id("iotParamsDevAddr"),"Goto DevAddr").sendKeys(String.valueOf(reandom_no), "Send the keys");
				findElement(By.id("iotParamsAppEUI"),"Goto AppEUI").sendKeys(String.valueOf(reandom_no),"Send the keys");
				findElement(By.xpath("//li[1]/input[@name='assetParams_FlowId']"), "Goto Genric flow").click("Click on Generic flow");
				findElement(By.id("iotParamsappskey"),"").sendKeys("a", Keys.CONTROL, "Select all the values");
				findElement(By.id("iotParamsappskey"),"").sendKeys("",  "Deleate all the values");
				findElement(By.xpath("//input[@name='assetParams_PreferedDC']"),"Goto Preference DC").click("Click on Preference DC");
				//driver.findElement(By.id("iotParamsappskey")).sendKeys(Keys.CONTROL, "a");
				//driver.findElement(By.id("iotParamsappskey")).sendKeys("");
				//findElement(By.id("iotParamsappskey"),"send the value").sendKeys(String.valueOf(reandom_no),"send the value");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[text()='Create'])[1]")));
				findElement(By.xpath("(.//*[text()='Create'])[1]"),"Goto Create").click("click on submit");
				
				
				
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
				String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
				_assert.contains(actual, "Success", "Updated Profile is verified");
				
				
			 
		} catch (Exception e) {
			Log.error("Unable to create Asset_TCOM_Device");
			Log.error(e.getMessage().toString());
			throw (e);
		}
	
}
 public void createDevice_with_AutoReg_and_noKeyInput(boolean autoProvision,boolean generateKeyImport)throws Exception{
		 
		 try {
			 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
						.contains("selected")) {
					findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt");
					}
			  

				driver.findElement(By.id("asset_2")).click();

				asset = prop.getProperty("asset_name") + randomNumber();

				

				findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"");

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
					
					if(actual.trim().contains(SmokeTest.Group.toUpperCase())){
						
						_WebElements.get(i).click();
						Thread.sleep(2000);
						break;
					}
					
				}

				wait.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));

				findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
				findElement(By.xpath("//label[contains(text(),'Device Manufacturer Profile')]/following-sibling::div/div/div/div[1]/input"),"goto_search text").sendKeys("kapil", "sent keys");
				findElement(By.xpath("//span[contains(text(),'kapil-Soman-17.0')]"),"Goto Kapil Profile").click("Click on the Profile");
				
				
	          if (autoProvision == false) {
            driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div[1]/input")).click();
            
            }
             
              reandom_no=rand_no+randomNumber();
                findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");
				findElement(By.id("iotParamsDevEUI"), "Goto DevEUI").sendKeys(String.valueOf(reandom_no), "Send the int value");
				findElement(By.id("iotParamsDevAddr"),"Goto DevAddr").sendKeys(String.valueOf(reandom_no), "Send the keys");
				findElement(By.id("iotParamsAppEUI"),"Goto AppEUI").sendKeys(String.valueOf(reandom_no),"Send the keys");
				findElement(By.xpath("//li[1]/input[@name='assetParams_FlowId']"), "Goto Genric flow").click("Click on Generic flow");
				findElement(By.id("iotParamsappskey"),"").sendKeys("a", Keys.CONTROL, "Select all the values");
				findElement(By.id("iotParamsappskey"),"").sendKeys("",  "Deleate all the values");
				findElement(By.id("iotParamsappkey"),"").sendKeys("a", Keys.CONTROL, "Select all the values");
				findElement(By.id("iotParamsappkey"),"").sendKeys("",  "Deleate all the values");
				findElement(By.id("iotParamsnwkskey"),"").sendKeys("a", Keys.CONTROL, "Select all the values");
				findElement(By.id("iotParamsnwkskey"),"").sendKeys("",  "Deleate all the values");
				findElement(By.xpath("//input[@name='assetParams_PreferedDC']"),"Goto Preference DC").click("Click on Preference DC");
				findElement(By.xpath("(.//*[text()='Create'])[1]"),"Goto Create").submit("click on submit");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success ng-scope']")));
				String actual = findElement(By.xpath("//div[@class='alert alert-success ng-scope']"),"Success Message").getAttribute(Attributes.TEXT); 
				_assert.contains(actual, "Success", "Updated Profile is verified");

			
		} catch (Exception e) {
			
		}
	 }
 public void update_device_witiout_keygen_autoreg()throws Exception{
	 try {
		 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
				}

		 asset = prop.getProperty("asset_name") + randomNumber();
		 
		 
		 findElement(By.xpath(".//*[@id='asset_3']"), "Goto Manage Asset").click("Click on manage asset"); 
		 findElement(By.xpath("//thead[tr[th[span[contains(text(),'Edit')]]]]/following-sibling::tbody/tr[1]/td[9]/a"),"Goto Edit Button").click("Click on edit button");
		 findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
         findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
	     findElement(By.xpath("//label[contains(text(),'Device Manufacturer Profile')]/following-sibling::div/div/div/div[1]/input"),"goto_search text").sendKeys("Qos", "sent keys");
		 findElement(By.xpath("//a[contains(text(),'QoS1-QoS1 Vehicle Transponder v13.0-1.4')]"),"Goto qos Profile").click("Click on the Profile");
		 findElement(By.id("iotParamsQoS Class"),"Send the Qos_Policy_ID").sendKeys(String.valueOf(poly.reandom_no),"Send the random_no");
		 findElement(By.xpath("//button[contains(text(),'Update')]"),"Goto Update").click("Click on update");
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success ng-scope']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success ng-scope']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success!", "Updated Profile is verified");
	} catch (Exception e) {
		Log.error("Unable to update device without keygen");
		Log.error(e.getMessage().toString());
		throw (e);
	}
 }
 public void update_device_with_AutoReg_and_Key_input()throws Exception{
	 try {
		 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
				}

		 asset = prop.getProperty("asset_name") + randomNumber();
		 
		 
		 findElement(By.xpath(".//*[@id='asset_3']"), "Goto Manage Asset").click("Click on manage asset"); 
		 findElement(By.xpath("//thead[tr[th[span[contains(text(),'Edit')]]]]/following-sibling::tbody/tr[1]/td[9]/a"),"Goto Edit Button").click("Click on edit button");
		 findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"");
		
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
         findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
	     findElement(By.xpath("//label[contains(text(),'Device Manufacturer Profile')]/following-sibling::div/div/div/div[1]/input"),"goto_search text").sendKeys("Kapil", "sent keys");
	     Thread.sleep(2000);
		 findElement(By.xpath("//a[contains(text(),'kapil-Soman-17.0')]"),"Goto Kapil Profile").click("Click on the Profile");
		
		 findElement(By.id("iotParamsDevEUI"), "Goto DevEUI").sendKeys(String.valueOf(reandom_no), "Send the int value");
		 findElement(By.id("iotParamsDevAddr"),"Goto DevAddr").sendKeys(String.valueOf(reandom_no), "Send the keys");
		 //findElement(By.id("iotParamsAppEUI"),"Goto AppEUI").sendKeys(String.valueOf(reandom_no),"Send the keys");
		 //findElement(By.id("iotParamsappskey"),"").sendKeys(String.valueOf(reandom_no),"Enter the value");
		 //findElement(By.id("iotParamsappkey"),"").sendKeys(String.valueOf(reandom_no),"Enter the value");
		 //findElement(By.id("iotParamsnwkskey"),"").sendKeys(String.valueOf(reandom_no),"Enter the value");
		 
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]")));
		 findElement(By.xpath("//button[contains(text(),'Update')]"),"Goto Update").click("Click on update");
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success ng-scope']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success ng-scope']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success!", "Updated Profile is verified");
	} catch (Exception e) {
		Log.error("Unable to update device with keygen & AutoReg");
		Log.error(e.getMessage().toString());
		throw (e);
	}
 }
 
 public void delete_device() throws Exception{
	 try {
		 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt");
				}
		 findElement(By.xpath(".//*[@id='asset_3']"), "Goto Manage Asset").click("Click on manage asset"); 
		 findElement(By.xpath("//thead[tr[th[span[contains(text(),'Delete')]]]]/following-sibling::tbody/tr[1]/td[10]/a/i"),"Goto Edit Button").click("Click on edit button");
		 alertAccept("Accept the Alert");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success", "Updated Profile is verified");
	} catch (Exception e) {
		Log.error("Unable to delete device ");
		Log.error(e.getMessage().toString());
		throw (e);
	}
 }
 
 public void Create_Device_without_AutoReg_With_InputKeyValue(boolean autoProvision,boolean generateKeyImport,boolean autoRegistration)throws Exception{

	 try {
		
		 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
				}
		  

			driver.findElement(By.id("asset_2")).click();

			asset = prop.getProperty("asset_name") + randomNumber();

			

			findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"");

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

			
			
			for(int i=1 ; i<=_WebElements.size();i++){
				
				Thread.sleep(2000);
				
				String actual = _WebElements.get(i).getText();
				
				if(actual.trim().contains(SmokeTest.Group.toUpperCase())){
					
					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}
				
			}

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));

			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
            findElements(By.xpath("(.//*[@class='custom-select-search'])[3]/../../div/ul/li"),"Get DP Count");
					
			
			for(int i=1 ; i<=_WebElements.size()-1;i++){
				
				Thread.sleep(2000);
				
				String actual = _WebElements.get(i).getText();
				String Device_Profile="kapil-Soman-17.0";
				if(actual.contains(Device_Profile)){
					
					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}
				
			}

            if (autoProvision == false) {
           driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div[1]/input")).click();
           
			}
          
            if(autoRegistration==true){
                findElement(By.xpath("//section[header[contains(text(),'Asset Details')]]/div/div/div[7]/div[2]/div[1]/input"),"goto Auto Registration").click("Click on Check box");
               }
            
            //findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");
            reandom_no=rand_no+randomNumber();
			findElement(By.id("iotParamsDevEUI"), "Goto DevEUI").sendKeys(String.valueOf(reandom_no), "Send the int value");
			findElement(By.id("iotParamsDevAddr"),"Goto DevAddr").sendKeys(String.valueOf(reandom_no), "Send the keys");
			findElement(By.id("iotParamsAppEUI"),"Goto AppEUI").sendKeys(String.valueOf(reandom_no),"Send the keys");
			findElement(By.xpath("//li[1]/input[@name='assetParams_FlowId']"), "Goto Genric flow").click("Click on Generic flow");
			findElement(By.id("iotParamsappskey"),"").sendKeys("a", Keys.CONTROL, "Select all the values");
			findElement(By.id("iotParamsappskey"),"").sendKeys("",  "Deleate all the values");
			findElement(By.id("iotParamsappskey"),"").sendKeys(String.valueOf(reandom_no),  "Enter the Values");
			findElement(By.xpath("//input[@name='assetParams_PreferedDC']"),"Goto Preference DC").click("Click on Preference DC");
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[text()='Create'])[1]")));
			findElement(By.xpath("(.//*[text()='Create'])[1]"),"Goto Create").submit("click on submit");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success", "Updated Profile is verified");
		 
	} catch (Exception e) {
		Log.error("Unable to create Asset_TCOM_Device");
		Log.error(e.getMessage().toString());
		throw (e);
	}


 }
 public void Create_Device_with_AutoReg_With_InputKeyValue(boolean autoProvision,boolean generateKeyImport)throws Exception{

	 try {
		
		 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmt Tile");
				}
		  

			driver.findElement(By.id("asset_2")).click();

			asset = prop.getProperty("asset_name") + randomNumber();

			

			findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"");

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
				
				if(actual.trim().contains(SmokeTest.Group.toUpperCase())){
					
					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}
				
			}

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));

			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
            findElements(By.xpath("(.//*[@class='custom-select-search'])[3]/../../div/ul/li"),"Get DP Count");
					
			
			for(int i=1 ; i<=_WebElements.size()-1;i++){
				
				Thread.sleep(2000);
				
				String actual = _WebElements.get(i).getText();
				String Device_Profile="kapil-Soman-17.0";
				if(actual.contains(Device_Profile)){
					
					_WebElements.get(i).click();
					Thread.sleep(2000);
					break;
				}
				
			}

            if (autoProvision == false) {
           driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div[1]/input")).click();
           
			}
            
            findElement(By.name("authNType"), "Auth Type").selectByValue("NO_SECURITY", "Select NO_SECURITY");
            reandom_no=rand_no+randomNumber();
			findElement(By.id("iotParamsDevEUI"), "Goto DevEUI").sendKeys(String.valueOf(reandom_no), "Send the int value");
			findElement(By.id("iotParamsDevAddr"),"Goto DevAddr").sendKeys(String.valueOf(reandom_no), "Send the keys");
			findElement(By.id("iotParamsAppEUI"),"Goto AppEUI").sendKeys(String.valueOf(reandom_no),"Send the keys");
			findElement(By.xpath("//li[1]/input[@name='assetParams_FlowId']"), "Goto Genric flow").click("Click on Generic flow");
			findElement(By.id("iotParamsappskey"),"").sendKeys("a", Keys.CONTROL, "Select all the values");
			findElement(By.id("iotParamsappskey"),"").sendKeys("",  "Deleate all the values");
			findElement(By.id("iotParamsappskey"),"").sendKeys(String.valueOf(reandom_no),  "Enter the Values");
			findElement(By.xpath("//input[@name='assetParams_PreferedDC']"),"Goto Preference DC").click("Click on Preference DC");
			findElement(By.xpath("(.//*[text()='Create'])[1]"),"Goto Create").submit("click on submit");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success", "Updated Profile is verified");
		 
	} catch (Exception e) {
		Log.error("Unable to create Asset_TCOM_Device");
		Log.error(e.getMessage().toString());
		throw (e);
	}


 }
 
 public void update_device_without_AutoReg_and_KeyInputValue(boolean autoProvision,boolean generateKeyImport)throws Exception{
	 try {
		 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmgt Tile").click("Click on Asset Mgmt Tile");
				}

		 asset = prop.getProperty("asset_name") + randomNumber();
		 
		 
		 findElement(By.xpath(".//*[@id='asset_3']"), "Goto Manage Asset").click("Click on manage asset"); 
		 findElement(By.xpath("//thead[tr[th[span[contains(text(),'Edit')]]]]/following-sibling::tbody/tr[1]/td[9]/a"),"Goto Edit Button").click("Click on edit button");
		 findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"");
		
		 if (autoProvision == true) {
	           driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div[1]/input")).click();
	           }
		 
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
         findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
	     findElement(By.xpath("//label[contains(text(),'Device Manufacturer Profile')]/following-sibling::div/div/div/div[1]/input"),"goto_search text").sendKeys("kapil", "sent keys");
		 findElement(By.xpath("//span[contains(text(),'kapil-Soman-17.0')]"),"Goto Kapil Profile").click("Click on the Profile");
		

		 /*findElement(By.id("iotParamsappskey"),"").sendKeys(String.valueOf(reandom_no),"Enter the value");
		 findElement(By.id("iotParamsappkey"),"").sendKeys(String.valueOf(reandom_no),"Enter the value");
		 findElement(By.id("iotParamsnwkskey"),"").sendKeys(String.valueOf(reandom_no),"Enter the value");*/
		 
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]")));
		 findElement(By.xpath("//button[contains(text(),'Update')]"),"Goto Update").click("Click on update");
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success ng-scope']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success ng-scope']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success!", "Updated Profile is verified");
	} catch (Exception e) {
		Log.error("Unable to update device with keygen & AutoReg");
		Log.error(e.getMessage().toString());
		throw (e);
	}
 }
 
 public void update_device_with_AutoReg_and_KeyInputValue(boolean autoProvision,boolean generateKeyImport)throws Exception{
	 try {
		 if (!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				findElement(By.xpath(".//span[text()='Asset Mgmt.']"),"Asset Mgmt Tile").click("Click on Asset Mgmgt");
				}

		 asset = prop.getProperty("asset_name") + randomNumber();
		 Log.info("Edit Asset Values = "+ asset);
		 reandom_no=rand_no+randomNumber();
		 
		 findElement(By.xpath(".//*[@id='asset_3']"), "Goto Manage Asset").click("Click on manage asset"); 
		 findElement(By.xpath("//thead[tr[th[span[contains(text(),'Edit')]]]]/following-sibling::tbody/tr[1]/td[9]/a"),"Goto Edit Button").click("Click on edit button");
		 findElement(By.name("resourceName"),"Goto Asset Name").sendKeys(asset,"Edit Asset Name");
		
		 if (autoProvision == false) {
	           driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div[1]/input")).click();
	           }
		 
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));
         findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"),"Goto Device Profile").click("Click on Device Profile");
	     findElement(By.xpath("//label[contains(text(),'Device Manufacturer Profile')]/following-sibling::div/div/div/div[1]/input"),"goto_search text").sendKeys("kapil", "sent keys");
		 findElement(By.xpath("//span[contains(text(),'kapil-Soman-17.0')]"),"Goto Milon Profile").click("Click on the Profile");
		

		 findElement(By.id("iotParamsDevEUI"), "Goto DevEUI").sendKeys(String.valueOf(reandom_no), "Send the int value");
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]")));
		// jse.executeScript("scroll(840,1040);");
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]")));
		 findElement(By.xpath("//button[contains(text(),'Update')]"),"Goto Update").click("Click on update");
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success ng-scope']")));
			String actual = findElement(By.xpath(".//*[@class='alert alert-success ng-scope']"),"Success Message").getAttribute(Attributes.TEXT); 
			_assert.contains(actual, "Success!", "Updated Profile is verified");
	} catch (Exception e) {
		Log.error("Unable to update device with keygen & AutoReg");
		Log.error(e.getMessage().toString());
		throw (e);
	}
 }
 
 public void device_Import() throws Exception{
		
		if(!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
			}
		
		String csvFile = System.getProperty("user.dir")+"\\testdata\\bulkimport_manish.csv";
     FileWriter writer = new FileWriter(csvFile);
     
     String dp_name = "kapil"+"#"+"Soman"+"#17.0";

     for(int i=1;i<=10;i++){
    	 CSVUtils.writeLine(writer,Arrays.asList("CREATE","TcomDeviceA"+randomNumber(),"SENSOR","localhost",","+dp_name,","+"Provisioned",",,"+"TRUE","BASIC_AUTH","admin"+randomNumber()+"#password"+randomNumber(),""+",TRUE,FALSE,"+"[OW#False]","[DevEUI#Join"+Integer.toHexString(randomNumber())+",AppEUI#ABB"+i+",DevAddr#CC"+i+"]"));
     }
     
     Log.info("Bulk CSV Generated");
     writer.flush();
     writer.close();
		findElement(import_asset, "Import Asset").click("Click on Import Asset");
		findElement(import_file_select, "Import File Select").selectByValue("1", "Select Key Import");
		findElement(By.id("customerId"), "Customer").selectByValue(SmokeTest.CustID, "Select Customer");
		findElement(By.id("deviceGroup"),"Group").selectByText(SmokeTest.Group.toUpperCase(), "Select Group");
		findElement(device_name, "Device Description").sendKeys("Test Automation", "Entering Device Name");
		findElement(device_file_browse, "File Browser").sendKeys(System.getProperty("user.dir")+"\\testdata\\bulkimport_manish.csv","Click on File Browse");
		Thread.sleep(3000);
		findElement(device_upload, "Upload").click("Click on Upload");
		
		
	}
 public void update_device_Import() throws Exception{
		
		if(!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
			}
		
		String csvFile = System.getProperty("user.dir")+"\\testdata\\bulkimport_manish.csv";
  FileWriter writer = new FileWriter(csvFile);
  
  String dp_name = "kapil"+"#"+"Soman"+"#17.0";

  for(int i=1;i<=10;i++){
	  CSVUtils.writeLine(writer,Arrays.asList("CREATE","TcomDeviceB"+randomNumber(),"SENSOR","localhost",","+dp_name,","+"Provisioned",",,"+"TRUE","BASIC_AUTH","admin"+randomNumber()+"#password"+randomNumber(),""+",TRUE,FALSE,"+"[OW#False]","[DevEUI#Join"+Integer.toHexString(randomNumber())+",AppEUI#ABB"+i+",DevAddr#CC"+i+"]"));
  }
  
  Log.info("Bulk CSV Generated");
  writer.flush();
  writer.close();
		findElement(import_asset, "Import Asset").click("Click on Import Asset");
		findElement(import_file_select, "Import File Select").selectByValue("1", "Select Key Import");
		findElement(By.id("customerId"), "Customer").selectByValue(SmokeTest.CustID, "Select Customer");
		findElement(By.id("deviceGroup"),"Group").selectByText(SmokeTest.Group.toUpperCase(), "Select Group");
		findElement(device_name, "Device Description").sendKeys("Test Automation", "Entering Device Name");
		findElement(device_file_browse, "File Browser").sendKeys(System.getProperty("user.dir")+"\\testdata\\bulkimport_manish.csv","Click on File Browse");
		Thread.sleep(3000);
		findElement(device_upload, "Upload").click("Click on Upload");
		
		
	}
 public void delete_device_Import() throws Exception{
		
		if(!findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/.."),"Asset Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
			findElement(asset_mgmt_dashboard, "Asset Mgmt").click("Click on Asset Mgmt");
			}
		
		String csvFile = System.getProperty("user.dir")+"\\testdata\\bulkimport_manish.csv";
FileWriter writer = new FileWriter(csvFile);

String dp_name = "kapil"+"#"+"Soman"+"#17.0";

for(int i=1;i<=10;i++){
	CSVUtils.writeLine(writer,Arrays.asList("CREATE","TCOMDeviceC"+randomNumber(),"SENSOR","localhost",","+dp_name,","+"Provisioned",",,"+"TRUE","BASIC_AUTH","admin"+randomNumber()+"#password"+randomNumber(),""+",TRUE,FALSE,"+"[OW#False]","[DevEUI#Join"+Integer.toHexString(randomNumber())+",AppEUI#ABB"+i+",DevAddr#CC"+i+"]"));
}

Log.info("Bulk CSV Generated");
writer.flush();
writer.close();
		findElement(import_asset, "Import Asset").click("Click on Import Asset");
		findElement(import_file_select, "Import File Select").selectByValue("1", "Select Key Import");
		findElement(By.id("customerId"), "Customer").selectByValue(SmokeTest.CustID, "Select Customer");
		findElement(By.id("deviceGroup"),"Group").selectByText(SmokeTest.Group.toUpperCase(), "Select Group");
		findElement(device_name, "Device Description").sendKeys("Test Automation", "Entering Device Name");
		findElement(device_file_browse, "File Browser").sendKeys(System.getProperty("user.dir")+"\\testdata\\bulkimport_manish.csv","Click on File Browse");
		Thread.sleep(3000);
		findElement(device_upload, "Upload").click("Click on Upload");
		
		
	}
}
