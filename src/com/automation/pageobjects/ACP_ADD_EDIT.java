package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.hpe.webdriver.ElementBase;

public class ACP_ADD_EDIT extends ElementBase {

	final static Logger Log = Logger.getLogger(ElementBase.class.getName());

	By acp_dashboard = By.xpath(".//*[text()='Access Control Policy']");
	By search_acp = By.xpath(".//*[@href='/dsm/accessControlPolicy/searchRule.htm']");
	By pencil = By.xpath(".//*[starts-with(@href , '/dsm/accessControlPolicy/editRule')]");
	By update = By.xpath(".//button[@ng-click='addACP()']");
	By Target_Select=By.xpath(".//*[@id='acpSelect']");
	By Target_Search=By.xpath(".//*[@id='hpeTree_treeSearch']");
	By Target_Group=By.xpath("//span[contains(text(),'GROUP_AUTOMATION_"+SmokeTest.Group+"')]");
	By AsstMgt_Group=By.xpath("//span[contains(text(),'Automation_Asset_"+SmokeTest.asset+"')]");
	By Target_LogicalResource=By.xpath("//div[h2[contains(text(),' Logical Resource')]]/form/div[1]/div/select");
	By Select_Container=By.xpath("//div[h2[contains(text(),' Logical Resource')]]/form/div[1]/div/select/option[2]");
	By Select_Commond=By.xpath("//div[h2[contains(text(),' Logical Resource')]]/form/div[2]/div/select");
	By Select_default=By.xpath("//div[h2[contains(text(),' Logical Resource')]]/form/div[2]/div/select/option[2]");
	By App_Mgt=By.xpath(".//*[text()='Application Mgmt.']");
	By App_Mgt_Search=By.xpath("//li[contains(text(),'Search Application')]");
	By App_Mgt_Select=By.xpath(".//*[@id='selectPol']");
	By Select_ACP=By.xpath("//div[table[thead[tr[th[contains(text(),'Policy Name')]]]]]/div/table/tbody/tr/td[1]");
	By Select_DONE=By.xpath("//button[contains(text(),'Done')]");
	By Select_Update=By.xpath("//button[contains(text(),'Update')]");
	
	public void Target_Device_to_ACP() throws Exception{
		try{
		
	 if(!driver.findElement(By.xpath("(.//*[text()='Access Control Policy'])/..")).getAttribute("class").contains("selected")){
	    Thread.sleep(2500);
		findElement(acp_dashboard,"ACP Dashboard").click("Click on ACP");
		Thread.sleep(2500);
		}
	 
		findElement(search_acp, "Search ACP").click("Click on Search ACP");
		
		findElement(pencil,"Select Edit pencil of ACP ").click("Click on Pencil Button");
		
		findElement(Target_Select, "select target").click("click on select button");
		
		findElement(Target_Group, "Goto_Group").click("click on the group icon");
		/*//findElement(AsstMgt_Group, "Asst_id").click("click on the created asset_ID");
		Thread.sleep(2000);
		//Click on Logical resource
		findElement(Target_LogicalResource, "GoTo Logical Resource").click("Click on Select button");
		
		//Select Resource
		findElement(Select_Container, "Goto Container profile").click("click on Container Profile");
		
		//Select Command
		findElement(Select_Commond, "Goto_Commond").click("click on commond");
		
		//select Default
		findElement(Select_default, "Goto_Default").click("click on Default");*/
		
		//Click on Ok button
		findElement(By.xpath(".//button[@ng-click='addResource()']"),"Ok Button").click("Click on Ok Button");
		
		//Click on Update button
		findElement(By.xpath("//button[contains(text(),'Update')]"),"Update Button").click("Cick on Update Button");
		
		Thread.sleep(3000);
		}
		catch(Exception e){
			
			Log.error("Unable to find the msg--Update is failed");
			Log.error(e.getMessage().toString());
			
			throw (e);
		}
		
		}
	
	public void Add_Acp_To_Application() throws Exception{
		
		try{
		if(!driver.findElement(By.xpath("(.//*[text()='Application Mgmt.'])/..")).getAttribute("class").contains("selected")){
			Thread.sleep(2500);
			findElement(App_Mgt, "Application Mgmt").click("Click on Application Mgmt");
			Thread.sleep(2500);
			}
		findElement(App_Mgt_Search, "Goto search button").click("click on search button");
		
		findElement(By.xpath("//table[thead[tr[th[contains(text(),'Edit')]]]]/tbody/tr/td[8]/a/i"),"Edit Button").click("Click on Edit Button");
		
		findElement(App_Mgt_Select, "select_Assign_Policy").click("click on assign Policy");
		
		findElement(Select_ACP, "Select ACP Policy").click("Click on ACP Policy");
		findElement(Select_DONE,"Got Done").click("Click on Done");
		
		findElement(Select_Update, "Goto update").click("Click on update");
		Thread.sleep(3000);
		}catch(Exception e){
			Log.error("Unable to find the msg--Update is failed");
			Log.error(e.getMessage().toString());
			
			throw (e);

		}
}
	public void Add_Subscription_for_Container_to_Device() throws Exception{
		
		try{
		if(!driver.findElement(By.xpath("(.//*[text()='Application Mgmt.'])/..")).getAttribute("class").contains("selected")){
			Thread.sleep(2500);
			findElement(App_Mgt, "Application Mgmt").click("Click on Application Mgmt");
			Thread.sleep(2500);
			}
		//Click on search Application
		findElement(App_Mgt_Search, "Goto search button").click("click on search button");
		
		//Click on Subscription Button
		findElement(By.xpath("//table[thead[tr[th[contains(text(),'Edit')]]]]/tbody/tr/td[6]/a/i"),"Goto subscription button").click("click on subscription pencil");
		
		//Click on Add Subscription
		findElement(By.xpath("//i[contains(text(),'Add Subscription')]"),"Goto Add subscription Button").click("Click on subscription button");
		
		//Click on Group & Asst user
		findElement(Target_Group, "Goto_Group").click("click on the group icon");
		findElement(AsstMgt_Group, "Asst_id").click("click on the created asset_ID");
		Thread.sleep(3000);
		
		//Click on Logical resource
		findElement(Target_LogicalResource, "GoTo Logical Resource").click("Click on Select button");
		
		//Select Resource
		findElement(Select_Container, "Goto Container profile").click("click on Container Profile");
		
		Thread.sleep(2500);
		
		//Select Command
		findElement(Select_Commond, "Goto_Commond").click("click on commond");
		
		//select Default
		findElement(Select_default, "Goto_Default").click("click on Default");
		
		//Click on Ok button
		findElement(By.xpath("//button[contains(text(),'OK')]"),"goto ok button").click("click on ok button");
		
		Thread.sleep(2000);
		}catch(Exception e){
			Log.error("Unable to find the msg--Update is failed");
			Log.error(e.getMessage().toString());
			throw(e);
			
		}
	}
	
	public void Add_subscription_for_a_device(){
		try{
			if(!driver.findElement(By.xpath("(.//*[text()='Application Mgmt.'])/..")).getAttribute("class").contains("selected")){
				Thread.sleep(2500);
				findElement(App_Mgt, "Application Mgmt").click("Click on Application Mgmt");
				Thread.sleep(2500);
				}
			//Click on search Application
			findElement(App_Mgt_Search, "Goto search button").click("click on search button");
			
			//Click on Subscription Button
			findElement(By.xpath("//table[thead[tr[th[contains(text(),'Edit')]]]]/tbody/tr/td[5]/a/i"),"Goto subscription button").click("click on subscription pencil");
			
			//Click on Add Subscription
			findElement(By.xpath("//i[contains(text(),'Add Subscription')]"),"Goto Add subscription Button").click("Click on subscription button");
			//Click on Group & Asst user
			findElement(AsstMgt_Group, "Asst_id").click("click on the created asset_ID");
			Thread.sleep(2500);
			//Click on Ok button
			findElement(By.xpath("//button[contains(text(),'OK')]"),"goto ok button").click("click on ok button");
		}
		catch(Exception e)
		{
			
		}
	}
}
























