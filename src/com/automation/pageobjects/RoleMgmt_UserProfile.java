package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class RoleMgmt_UserProfile extends ElementBase{
	
	RoleMgmt role = new RoleMgmt();
	
	final static Logger Log = Logger.getLogger(RoleMgmtTopology.class.getName());
	
	By userProfileNameTextBox = By.name("sresourceName");
	By keyTextBox = By.id("assetValue");
	By valueTextBox = By.cssSelector("[ng-model='pair.value']");
	By addButton = By.cssSelector("button[ng-click='addRow()']");
	By roleDropDown = By.id("roleId");
	By usersTile = By.xpath(".//span[text()='Users']");
	By createUserProfileTileList = By.id("users_9");
	By editUserProfileTileList = By.id("users_10");
	By createButton = By.xpath(".//button[text()='Create']");
	
	By editIcon = By.xpath("(.//*[@class='fa fa-pencil'])[1]");
	By updateButton = By.xpath(".//button[text()='Update']");
	By currentPage = By.id("currentPage");
	
	
	
	public void createUserProfile(String roleName){

		try{
				
			role.checkTileDisplayed(2);
			
		if (!driver.findElement(usersTile).getAttribute("class").contains("selected")) {
			findElement(usersTile, "Users link in Dashboard").click("Click on Users link in Dashboard");
		}
		
		findElement(createUserProfileTileList, "Create User Profile Tile List").click("Click on Create User Profile");
		findElement(userProfileNameTextBox, "User Profile Text Box").sendKeys("Rand"+randomNumber(), "Enter User Profile Name");
		findElement(keyTextBox, "Key Text Box").sendKeys("TestKey", "Enter Key Value");
		findElement(valueTextBox, "Vale Text Box").sendKeys("TestValue", "Enter Value");
		findElement(addButton, "Add Button").click("Click on Add Button");
		findElement(roleDropDown, "Role Drop Down").selectByText(roleName.toUpperCase(), "Role Name");
		
		findElement(createButton, "Create Button").click("Click on Create Button");
		}catch(Exception e){
			Log.error("Unable to Create User Profile");
			Log.error(e.toString());
			throw(e);
		}
		
	}
	
	public void viewUserProfile(){
		
		try{
			role.checkTileDisplayed(2);
			
			if (!driver.findElement(usersTile).getAttribute("class").contains("selected")) {
				findElement(usersTile, "Users link in Dashboard").click("Click on Users link in Dashboard");
			}
			
			findElement(editUserProfileTileList,"Edit User Profile List").click("Click on Edit User Profile List");
			
			_assert.equals(findElement(currentPage, "Current Page").getAttribute(Attributes.TEXT).trim(),"Search User Profile", "Verify View User Profile");
			
		}catch(Exception e){
			Log.error("Unable to View User Profile");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void editUserProfile(){
		try{
			
			findElement(editIcon, "Edit Icon").click("Click on Edit Icon");
			
			findElement(keyTextBox, "Key Text Box").sendKeys("Edit Key", "Enter Edit Key Values");
			findElement(updateButton, "Update Button").click("Click on Update Button");
			
			
		}catch(Exception e){
			Log.error("Unable to Edit User Profile");
			throw(e);
		}
	}
	
	public void deleteUserProfile(){
		try{
			
			findElement(By.xpath("(.//*[contains(@ng-click,'deleteUserProfile')])[1]"),"Delete Icon").click("Click on Delete Icon");
			
			alertAccept("Delete Confirmation Alert");
			
			
		}catch(Exception e){
			Log.error("Unable to delete User Profile");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	

	

}
