package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class CustomerAccProvisioning extends ElementBase{
	
	final static Logger Log = Logger.getLogger(CustomerAccProvisioning.class.getName());
	By accordion = By.cssSelector("[class='accordion']>div:nth-child(1)");
	String profile_name = "";
	public  void CreateUserProfile () throws Exception{
		try{
			if (!findElement(By.xpath("(.//span[text()='Users'])/.."),"Users Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath(".//span[text()='Users']"),"Users Tile").click("Click on Users Tile");
				Thread.sleep(2000);
			}
			
			profile_name = "Auto"+randomNumber();
			findElement(By.cssSelector("[href='/dsm/userProfile/addUserProfile.htm']"),"Create User Profile").click("Click on Create Customer Profile");
			findElement(By.name("sresourceName"),"Profile Name Text Box").sendKeys(profile_name, "Enter Profile Name");
			Log.info("Profile Name "+ profile_name);
			findElement(By.cssSelector("[ng-model='pair.key']"),"Key Text Box").sendKeys("AutoTest","Enter Key Value");
			findElement(By.cssSelector("[ng-model='pair.value']"),"Pair Text Box").sendKeys("AutoKey", "Enter Pair Value");
			findElement(By.cssSelector("[ng-model='pair.description']"),"Description Text Box").sendKeys("AutoDescription","Enter Desciption");
			findElement(By.cssSelector("[ng-click='addRow()']"), "Add Row Button").click("Click on Add Row");
			findElement(By.id("roleId"),"Role Drop Down").selectByIndex(1,"Select Custom Role");
			
			findElement(By.xpath(".//*[contains(@ng-click,'createUserProfile()')]"),"Create Button").click("Click on Create Button");
			
			
		}catch(Exception e){
			
			Log.error("Unable to Create Customer User Profile ");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	public void Verify(){
		try{
			
		String msg =	findElement(By.xpath("(.//*[@class='alert alert-success'])"), "Success Message Alert").getAttribute(Attributes.TEXT);
			
		_assert.contains(msg, "Success","Verfiy Success Message");
		
		Log.info("Customer User Profile Created is "+ profile_name);
			
			
		}catch(Exception e){
			
			Log.error("Unable to Verify Success Message");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void CreateRole(){
		try{
			
			findElement(By.cssSelector("[href='/dsm/angular/roleMgmt/#']>div"),"Role Mgmt Tile").click("Click on Role Mgmt Tile");
			
			wait.until(ExpectedConditions.elementToBeClickable(accordion));
			findElement(By.id("roleName"),"Role Name Text Box").click("Click on Role Name");
			findElement(By.id("roleName"),"Role Name Text Box").sendKeys("CustomRole","Enter Role Name");
			Log.info("");
			
			findElement(By.id("roleDesc"),"Role Desc Text Box").click("Click on Role Desc");
			findElement(By.id("roleDesc"),"Role Desc Text Box").sendKeys("Test Desc", "Enter Role Description");
			findElement(By.xpath("(//*[text()[contains(.,'User Management')]])[1]/span[@id='header_']"),"User Mgmt Tick Mark").click("Click on User Mgmt Role");
			findElement(By.xpath("(//*[text()[contains(.,'Device Management')]])[1]/span[@id='header_']"),"Device Mgmt Tick Mark").click("Click on Device Mgmt Role");
		
			findElement(By.xpath(".//button[contains(@ng-click,'roleAction')]"),"Create Role Button").click("Click on Create Button");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-show='serverSuccess']")));
			
			String msg = findElement(By.xpath(".//*[@ng-show='serverSuccess']"),"Success Alert Msg").getAttribute(Attributes.TEXT);
			
			_assert.contains(msg, "Success", "Verify Role Creation Success Message");
			
		}catch(Exception e){
			
			Log.error("Unable to Create New Role");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	public void UpdateProfile() throws Exception{
		try{
			if (!findElement(By.xpath("(.//span[text()='Users'])/.."),"Users Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath(".//span[text()='Users']"),"Users Tile").click("Click on Users Tile");
				Thread.sleep(2000);
			}
			
			findElement(By.cssSelector("[href='/dsm/userProfile/searchUserProfile.htm']"),"Search User Profile Tile").click("Click on Search User Profile");
			
			findElement(By.cssSelector("[class='fa fa-pencil']"),"Edit Icon").click("Click on Edit Icon");
			findElement(By.cssSelector("[ng-model='pair.description']"),"Description Text Box").sendKeys("AutoDescription Update","Enter Desciption");
			
			findElement(By.xpath(".//*[contains(@ng-click,'updateUserProfile()')]"),"Update Button").click("Click on Update Button");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alert alert-success']")));
			String msg = findElement(By.cssSelector("[class='alert alert-success']"),"Update Success Message").getAttribute(Attributes.TEXT);
			
			_assert.contains(msg,"Success", "Verify Success Update");
			
		}catch(Exception e){
			Log.error("Unable to Update Customer Profile");
			Log.error(e.toString());
			throw(e);
			
		}
	}
	
	public void DeleteProfile() throws Exception{
		try{
			
			if (!findElement(By.xpath("(.//span[text()='Users'])/.."),"Users Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath(".//span[text()='Users']"),"Users Tile").click("Click on Users Tile");
				Thread.sleep(2000);
			}
			
			findElement(By.cssSelector("[href='/dsm/userProfile/searchUserProfile.htm']"),"Search User Profile Tile").click("Click on Search User Profile");
			
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".totaldata.redbg.ng-binding"),"1"));
			
			String beforeDelete = findElement(By.cssSelector(".totaldata.redbg.ng-binding"),"Profile Count Before Delete").getAttribute(Attributes.TEXT);
			
			findElement(By.cssSelector(".fa.fa-trash.link-icons"),"Delete Icon").click("Click on Delete Icon");
			
			alertAccept("Confirm Delete");
			
			driver.navigate().refresh();
			
			String afterDelete = findElement(By.cssSelector(".totaldata.redbg.ng-binding"),"Profile Count After Delete").getAttribute(Attributes.TEXT);
			
			_assert.equals(Integer.parseInt(afterDelete), Integer.parseInt(beforeDelete)-1, "Verify Count After Delete");
			
		}catch(Exception e){
			Log.error("Unable to Delete Profile");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void CustomerAttributesCheck() throws Exception{
		
		try{
			
			if(!findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/.."),"Customer Mgmt Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
				findElement(By.xpath(".//*[text()='Customer Mgmt.']"), "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
				}
				findElement(By.id("cust_2"), "Create Customer").click("Click on Create Customer");
				findElement(By.xpath(".//li[contains(@ng-click,'Customer')]"), "Customer Tab").click("Select Customer Tab");
				
				findElement(By.xpath(".//*[@st-table='customerList']/tbody/tr[1]/td[5]/a"),"Edit Icon").click("Click on Edit Icon");
				Thread.sleep(2000);
				_assert.equals(driver.findElement(By.xpath("(.//*[contains(text(),'aep_pass_through')])[1]")).isDisplayed(), "Verify aes_pass_through");
		}catch(Exception e){
			
			Log.error("Unable to Verify Customer Attributes");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	public void VerifyIfRoleCanBeAdded() throws Exception{
		 try{
			 if (!findElement(By.xpath("(.//span[text()='Users'])/.."),"Users Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")) {

					Thread.sleep(2500);
					findElement(By.xpath(".//span[text()='Users']"),"Users Tile").click("Click on Users Tile");
					Thread.sleep(2000);
				}
			 findElement(By.cssSelector("[href='/dsm/userProfile/addUserProfile.htm']"),"Create User Profile").click("Click on Create Customer Profile");
			 
			 findElement(By.cssSelector("[id=roleId]"), "Role Drop Down").click("Click on Role Drop Down");
			 int count =findElements(By.cssSelector("#roleId>option"),"Role Drop Down").getCount("Count for Role Drop Down");
			 
			 _assert.equals(count, 3, "Verify if Role Drop has values");
			 
		 }catch(Exception e){
			 Log.error("Unable to Check if Role can be added");
			 Log.error(e.toString());
			 throw(e);
		 }
	}

}
