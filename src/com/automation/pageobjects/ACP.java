package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.iot.api.base.Base_API;
import com.hpe.webdriver.ElementBase;

public class ACP extends ElementBase{

	final static Logger Log = Logger.getLogger(ACP.class.getName());
	
	By acp_dashboard = By.xpath(".//*[text()='Access Control Policy']");
	By search_acp = By.xpath(".//*[@href='/dsm/accessControlPolicy/searchRule.htm']");
	By pencil = By.xpath(".//*[starts-with(@href , '/dsm/accessControlPolicy/editRule')]");
	By update = By.xpath(".//button[@ng-click='addACP()']");
	
	// attachDeviceToAcp
	
	By selectTargetResource = By.id("acpSelect");
	By doneButton = By.xpath(".//button[@ng-click='addResource()']");
	By treeSearch = By.cssSelector("[id='hpeTree_treeSearch']");
	
	public void attachDeviceToAcp(String resourceName , String resourceId) throws Exception{
		try{
			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"ACP Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Access Control Policy']")).click();
				Thread.sleep(3000);
			}
			findElement(search_acp, "Search Acp").click("Click on Search Acp");

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//*[text()='" + prop.getProperty("acp_name") + "']")));
			findElement(By.xpath(".//*[text()='" + prop.getProperty("acp_name") + "']"), "Acp").click("Click on Acp");

			findElement(pencil, "Edit Icon").click("Click on Edit Icon");

			Thread.sleep(3000);

			findElement(selectTargetResource, "Acp Select").click("Click on Acp Select");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-if='!isTreeLoaded']")));
			Thread.sleep(2000);
			findElement(By.xpath(".//*[text()='"+SmokeTest.Group.toUpperCase()+"']"), "Group Tag").click("Click on Group Tag");
			
			findElement(treeSearch, "Device Search Text Box").sendKeys(resourceName, "Enter Device Name");
			
			Thread.sleep(3000);
			findElement(By.xpath(".//span[contains(@ng-if,'match.model.resourceType')]"), "Locate Search").click("Search Asset");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li[@id='" + resourceId + "']/span")));
			
			findElement(By.xpath(".//li[@id='" + resourceId + "']/span"), "Asset Name "+resourceName).click("Click on Asset "+resourceName);
			Thread.sleep(2000);
			findElement(doneButton, "Done").click("Click on Done");

			Thread.sleep(2000);
			findElement(update, "Update Button").click("Click on Update Button");
			Thread.sleep(2000);
			
		}catch(Exception e){
			
			Log.error("Unable to Attach Device "+resourceName +"to ACP");
			throw(e);
		}
		
	}
	
	public void Search_ACP(){
		if(!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"ACP Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
		findElement(acp_dashboard,"ACP Dashboard").click("Click on ACP");
		}
		findElement(search_acp, "Search ACP").click("Click on Search ACP");
		
		findElements(By.xpath("(.//table)[2]//tbody//tr"),"ACP Row");
		int count = _WebElements.size();
		
		_assert.equals(count, 1, "Verify If ACP Search is displayed");
		
	}
	
	public void Update_ACP(){
		if(!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"ACP Tile").getAttribute(Attributes.GENERAL,"class").contains("selected")){
		findElement(acp_dashboard,"ACP Dashboard").click("Click on ACP");
		}
		findElement(search_acp, "Search ACP").click("Click on Search ACP");
		findElement(pencil, "Pencil").click("Click on Pencil");
		findElement(update, "Update Button").click("Click on Update Button");
	}
	
	public void Verify_Update(){
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
		String actual = findElement(By.xpath(".//*[@class='alert alert-success']//strong"),"Alert Text").getAttribute(Attributes.TEXT);

		SmokeTest.compare(actual, "Success");
		
	}
}
