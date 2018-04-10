/**
 * 
 */
package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementAction;
import com.hpe.webdriver.ElementBase;


/**
 * @author aloksu
 *
 */
public class RoleMgmt_AppEntity extends ElementBase{

	final static Logger Log = Logger.getLogger(ApplicationMgmt.class.getName());
	
	By App_mgmtTile = By.xpath("html/body/table/tbody/tr/td[1]/div[2]/div[1]");
	By register_app = By.xpath("//*[@id='ae_1']");
	By app_mgmt = By.xpath(".//*[text()='Application Mgmt.']");
	By Register_title = By.id("currentPage");
	By App_name  =  By.name("sresourceName");
	By Customer_dropdown = By.xpath("//*[@id='sgroupId']");
	By active_button  =  By.xpath("//*[@class='switch ng-empty ng-valid']");
	By Notif_EndPoint  =  By.xpath(".//*[@id='urlNameDiv']/textarea");
	By Create_Button  =  By.xpath("//*[@class='btn grommetux-button-primary']");
	By successAlert  =  By.xpath("//*[@class='alert alert-success']");
	
	By SearchApp_Tile  =  By.xpath("//*[@class='submenu routing menucolor6 selected']/ul");
	By Search_Icon  =  By.xpath("//*[@class='btn quicklinks']");
	By Application  =  By.xpath("//*[@placeholder='Application Name']");
	By Search_Button  =  By.xpath(" //*[@class='btn grommetux-button-primary']");
	By Items  =  By.xpath("//*[@style='float: left']");
	By Edit_Button = By.xpath("//*[@class='fa fa-pencil']");
	By Update_Button = By.xpath(".//*[@class='btn grommetux-button-primary']");
	By Delete_Button  =  By.xpath("//*[@class='fa fa-trash']");
	By Delete_Success_Msg  =  By.xpath("//*[@class='alert alert-success ng-scope']");
	
	By FirstUser = By.xpath(".//*[@ng-repeat='data in data|filter:filteredName']/td[2]/a");
	By ViewApp_Title = By.xpath(".//*[@class='pageHeader']");
	
	By DataProp_Button  =  By.xpath(".//*[@class='icons-sm']");
	By Attribute_Box  =  By.xpath(".//*[@placeholder='Attribute']");
	By Column_Box  =  By.xpath(".//*[@placeholder='Column Name']");
	By DataPro_Active  =  By.xpath(".//*[@class='switch ng-valid ng-not-empty']");
	By DataPro_Start  =  By.xpath(".//*[@ng-click='kafkaStart()']");
	By DataPro_Success  =  By.xpath(".//*[@class='alert alert-success']");
	
	By Create_ResourceGroup  = By.xpath("//*[@id='ae_3']");
	By Enter_Group  =  By.xpath("//*[@name='sGroupName']");
	By MembersSelect_Button  =  By.xpath("//*[@id='acpSelect']");
	By View_ResourceGroup  =  By.xpath("//*[@id='ae_4']");
	By GroupName_Col  =  By.xpath("//*[@st-table='oneM2MGroupList']/tbody/tr/td[1]/a");
	By Resource_Edit  =  By.xpath("//*[@st-table='oneM2MGroupList']/tbody/tr/td[6]/a");
	By Resource_Delete  =  By.xpath("//*[@st-table='oneM2MGroupList']/tbody/tr/td[7]/a");
	By Resource_DeleteMsg  =  By.xpath("//*[@class='alert alert-success']");
	
	
	public final String name = "Register Application";
	public final String SearchName = "Search Application";
	public final String Items_found = "1 items found";
	public final String Resource_Group = "View Resource Group";
	String CustomerName = null;
	String CreateGroup = null;
	
	
	public void Add_Application(String app_name, String Cust, String Protocol) throws Exception{
		
		try{
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
				Thread.sleep(1000);
			}
			
			CustomerName = app_name+"_"+randomNumber();
			findElement(register_app, "Click on Register").click("Clicked on Register option");		
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, name, "Verifying page title"); 
			
		    findElement(App_name, "Enter Name").sendKeys(CustomerName, "Enter Role name");
            //findElement(By.xpath("//*[@id='sgroupId']"), "Enter Group").selectByText("IoT", "Selecting IoT");
		    Thread.sleep(1000);
		    findElement(By.xpath("//*[@id='sgroupId']"), "Enter Group").selectByIndex(1, "Selecting IoT");
              
		    findElement(active_button, "Click on Active").click("Click on button");
		    
		    findElement(By.xpath("//*[@name='protocol']"), "Enter Protocol").selectByText("HTTP", "Selecting HTTP");
		    
            findElement(Notif_EndPoint, "Enter Application Name").sendKeys("http://15.112.152.181:8080/dsm/", "Entering Url");
            
            findElement(Create_Button, "Find Click Button").click("Click on Create button");  
			   
			   wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
			   _assert.contains(findElement(successAlert, "Success Alert").getAttribute(Attributes.TEXT),"Success", "Verify Role Create Success Message");
		      
		}catch(Exception e){
			Log.error("Unable to Create Application");
			throw(e);
		}	
	}
	
	public void Search_Application(String App_Name) throws Exception{
		
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
				Thread.sleep(2000);
			}
              findElement(SearchApp_Tile,"Click Search Application").click("clicking on Search App");
              
              findElement(Search_Icon,"Click on Search").click("clicking on Search Icon");
              
              findElement(Application, "Enter Application Name").sendKeys(App_Name, "Entering App_Name");
              
              findElement(Search_Button, "Search for App").click("Clicking Search Button");
              
              wait.until(ExpectedConditions.visibilityOfElementLocated(Items));
           
              String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
  			  _assert.equals(pageName, SearchName, "Verifying page title"); 
			
		}catch(Exception e){
			Log.error("Unable to Search Application");
			throw(e);
		}
			
	}
	
	public void Update_Application() throws Exception{
		
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
				Thread.sleep(2000);
			}
              findElement(SearchApp_Tile,"Click Search Application").click("clicking on Search App");
              
              findElement(Edit_Button, "Check for Edit Button").click("Click on Edit");
              
              findElement(Notif_EndPoint, "Update Notification Endpoint").sendKeys("http://15.112.152.181:8080/dsm/", "Enter update information");
              
              findElement(Update_Button, "Find Update Button").click("Click on Update");
			
              wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
				
			  _assert.contains(findElement(successAlert, "Success Alert").getAttribute(Attributes.TEXT),"Success", "Verify Role Create Success Message");
              Thread.sleep(2000);
              
		}catch(Exception e){
			Log.error("Unable to Update Application");
			throw(e);
		}
	
	}
	
	public void Delete_Application() throws Exception{
		
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
				Thread.sleep(2000);
			}
			
			findElement(SearchApp_Tile,"Click Search Application").click("clicking on Search App");
			
			findElement(Delete_Button,"Find Delete Button").click("clicking on Delete Icon");
			
			alertAccept("Click on Ok");
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(Delete_Success_Msg));
			
			_assert.contains(findElement(Delete_Success_Msg, "Delete success alert").getAttribute(Attributes.TEXT),"Success", "Verify Application delete Success Message");
			Thread.sleep(2000);
			
		}catch(Exception e){
			Log.error("Unable to Delete Application");
			throw(e);
		}
	}
	
	public void View_Application(String userTile) throws Exception{
		
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
				Thread.sleep(2000);
			}
			
			findElement(SearchApp_Tile,"Click Search Application").click("clicking on Search App");
			
			findElement(FirstUser,"First User").click("clicking on first user");
			
            wait.until(ExpectedConditions.visibilityOfElementLocated(ViewApp_Title));
            
            String pageName = findElement(ViewApp_Title, "Page Title").getAttribute(Attributes.TEXT);
            Log.info(pageName);
            _assert.equals(pageName, userTile, "Verifying page title");
			
		}catch(Exception e){
			Log.error("Unable to View Application");
			throw(e);
		}
	}
	
	public void Data_Propagation(String Attribute, String Column, String Val) throws Exception{
		
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
				Thread.sleep(2000);
			}
			
			findElement(SearchApp_Tile,"Click Search Application").click("clicking on Search App");
			
			findElement(DataProp_Button,"Find Data Propagation edit button").click("Click on Data Propagation");
			
			findElement(Attribute_Box, "Find Attribute Box").sendKeys(Attribute, "Enter Attribute");
			
			findElement(Column_Box, "Find Column Box").sendKeys(Column, "Enter Column");
			
			findElement(By.xpath(".//*[@ng-model='payload.dataType']"), "Enter Payload").selectByText(Val, "Selecting Value");
			
			findElement(active_button, "Find Active").click("Click on Active");
			
			findElement(DataPro_Start, "Find Start Button").click("Click Start");
			Thread.sleep(10000);
	
			wait.until(ExpectedConditions.visibilityOfElementLocated(DataPro_Success));
			_assert.contains(findElement(DataPro_Success, "Verify Message").getAttribute(Attributes.TEXT),"Success", 
			"Message should be displayed");
			
		}catch(Exception e){
			Log.error("Unable to Create Data Propogation");
			throw(e);
		}
	}
	
	public void Create_ResourceGroup(String resource_group) throws Exception{
		//incomplete function
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click(); 
			}
			
			findElement(Create_ResourceGroup,"Find Create Resource Group in dropdown ").click("clicking on Create Resource Group");
			
			CreateGroup  =  resource_group+"_"+randomNumber();
			
			findElement(Enter_Group, "Find Group Name box").sendKeys(CreateGroup, "Enter some Name");
			
			findElement(MembersSelect_Button, "Find Select Members Button").click("Click");
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));
			Thread.sleep(2000);
			
			
		}catch(Exception e){
			Log.error("Unable to Create Data Propogation");
			throw(e);
		}
	}
	
	public void View_ResourceGroup() throws Exception{
		
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click(); 
			}
			
			findElement(View_ResourceGroup, "Find Search Resource").click("Click on Search Resource");
			
			findElement(GroupName_Col, "Locate 1st option").click("Click on 1st option under Group Name");
			Thread.sleep(3000);
			String pageName = findElement(By.xpath("//*[@id='currentPage']"), "Page Title").getAttribute(Attributes.TEXT);
			_assert.equals(pageName, Resource_Group, "Verifying page title"); 
			
		  }catch(Exception e){
			Log.error("Unable to View Resource Group");
			throw(e);
		}
		
	}
	
	public void Update_ResourceGroup(){
		//incomplete function
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click(); 
			}
			
			findElement(View_ResourceGroup, "Find Search Resource").click("Click on Search Resource");
			
			findElement(Resource_Edit, "Find Edit icon").click("Click on Search Resource");
			
		}catch(Exception e){
			Log.error("Unable to Update Resource Group");
			throw(e);
	}
	
	}
	
	public void Delete_ResourceGroup() throws Exception{
		
       try{
			
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt Tile").getAttribute(Attributes.TEXT,"class")
					.contains("selected")){
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click(); 
			}
			
			findElement(View_ResourceGroup, "Find Search Resource").click("Click on Search Resource");
			
			findElement(Resource_Delete, "Find Edit icon").click("Click on Search Resource");
			
			alertAccept("Click on Ok");
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(Resource_DeleteMsg));
			
			_assert.contains(findElement(Resource_DeleteMsg, "Delete success alert").getAttribute(Attributes.TEXT),"Success", "Verify Resource Group delete Success Message");
			Thread.sleep(2000);
			
		}catch(Exception e){
			Log.error("Unable to Delete Resource Group");
			throw(e);
		}
	}
}

