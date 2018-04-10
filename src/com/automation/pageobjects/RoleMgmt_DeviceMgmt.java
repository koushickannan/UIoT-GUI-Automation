package com.automation.pageobjects;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;




public class RoleMgmt_DeviceMgmt extends ElementBase {
	final static Logger Log = Logger.getLogger(RoleMgmt_DeviceMgmt.class.getName());
	By DeviceTile = By.xpath(".//*[@class =\"nav-label\" and contains(text(),\"Asset Mgmt.\")]");
	By ManagAsset = By.xpath(".//*[@id =\"asset_3\" and contains(text(),\"Manage Assets\")]");
	By searchButton = By.xpath(".//*[@class='col-sm-4']/ul/li/a");
	By searchAsseetName = By.xpath(".//*[@name='assetname' and contains(@placeholder,\"Asset Name\")]");
	By searchAssetButton = By.xpath(".//*[@class='btn grommetux-button-primary' and contains(@value,\"Search\")]");
	By AssertNamefound = By.xpath(".//*[@st-table='assetList']/tbody/tr/td[2]/a");
	By verifyingAssertName = By.xpath(".//*[@class='col-sm-offset-1 col-sm-6']/div[1]/div/span");
	By EditAssetButton = By.xpath(".//*[@st-table='assetList']/tbody/tr[1]/td[9]/a");
	By verifyingEditAssettitel = By.xpath(".//*[@class='pageHeader' and contains(text(),\"Edit Device\")]");
	By EditAssetName = By.xpath("//*[@name = \"resourceName\"]");
	By UpdateAssetButton = By.xpath("//*[@class= \"btn grommetux-button-primary\"]");
public void alertDelete(String description){
	try{
	//wait.until(ExpectedConditions.alertIsPresent());
	String alertText = driver.switchTo().alert().getText();
	Log.info(alertText);
	driver.switchTo().alert().accept();
	Log.info("Alert :"+alertText+" : Accepted");
	}catch(Exception e){
		
		Log.error("Unable to Perform action on Alert "+ description);
		throw(e);
	}
	
}
		
public void searchDeviceVerification(String searchDeviceName) throws InterruptedException {
	try {
		if (!driver.findElement(By.xpath(".//*[@class =\"nav-label\" and contains(text(),\"Asset Mgmt.\")]")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(DeviceTile,"User Tile ").click("clicking on Device Tile");
			Thread.sleep(2000);
			findElement(ManagAsset,"Manage User").click("clicking on Manage User");
			Thread.sleep(1000);
			findElement(searchButton,"Search Button").click("clicking on search button");
//			wait.until(ExpectedConditions.visibilityOfElementLocated(verifyingUserInfo));

			findElement(searchAsseetName,"Assert Name to sear").sendKeys(searchDeviceName, "Giving Device Name");
			findElement(searchAssetButton,"Search Button").click("clicking on search button");
			findElement(AssertNamefound,"Assert Name Found").click("clicking on assert name ");

			String AssertName = findElement(verifyingAssertName, "Assert name after search").getAttribute(Attributes.TEXT);
			Log.info(AssertName);
			
			_assert.equals(AssertName, searchDeviceName, "Verifying page title");
			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}
	}

public void editAssetVerification(String userupdateTitel,String AssetName) throws InterruptedException {
	try {
		if (!driver.findElement(By.xpath(".//*[@class =\"nav-label\" and contains(text(),\"Asset Mgmt.\")]")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(DeviceTile,"User Tile ").click("clicking on Device Tile");
			Thread.sleep(2000);
			findElement(ManagAsset,"Manage User").click("clicking on Manage User");
			Thread.sleep(1000);
			findElement(EditAssetButton,"Edit Asset Button").click("clicking edit asset button");
			wait.until(ExpectedConditions.visibilityOfElementLocated(verifyingEditAssettitel));
			
			String pageTitel = findElement(verifyingEditAssettitel, "Page Title").getAttribute(Attributes.TEXT);
			Log.info(pageTitel);
			
			_assert.equals(pageTitel, userupdateTitel, "Verifying page title");
			findElement(EditAssetName,"Edit Asset Name").sendKeys(AssetName, "EditAssetName");
			findElement(UpdateAssetButton,"Update Asset Button").click("clicking on update Asset button");

			}

		} catch (Exception e) {

			Log.info("Catching No Such Element Exception for Sub System");
		}

	}


}
