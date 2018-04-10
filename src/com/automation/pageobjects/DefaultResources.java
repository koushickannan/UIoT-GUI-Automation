package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class DefaultResources extends ElementBase {

	final static Logger Log = Logger.getLogger(DefaultResources.class.getName());

	static String acp_default = "";

	public void CreateACP() throws Exception {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//span[text()='Access Control Policy']")));
			if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"ACP Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				findElement(By.xpath(".//span[text()='Access Control Policy']"),"ACP Tile").click("Click on ACP Tile");
			}
			driver.findElement(By.id("acp_1")).click();

			driver.findElement(By.id("acpName")).sendKeys("GUI_Access");
			driver.findElement(By.id("acpDescription")).sendKeys("Test");

	

			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[1]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[2]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[3]/label/input")).click();
			driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[4]/label/input")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//button[text()='Create']")).click();

			Log.info("ACP Created : " + "GUI_Access");
		} catch (Exception e) {
			Log.error("Unable to Create ACP " + e.toString());
			throw (e);
		}

	}

	public void AddNewApplciation() throws InterruptedException {
		try {
			if (!findElement(By.xpath("(.//span[text()='Application Mgmt.'])/.."),"Application Mgmt").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
				Thread.sleep(2000);
			}
			driver.findElement(By.id("ae_1")).click();

			driver.findElement(By.cssSelector("#appNameDiv>input")).sendKeys("IOT_GUI_App");
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("(.//*[@id='sgroupId'])[1]"))).selectByValue("1000");

			driver.findElement(By.cssSelector("[ng-model='aeDetails.active']>small")).click();

			driver.findElement(By.id("selectPol")).click();
			driver.findElement(By.xpath(".//*[@ng-model='searchCriteria']")).sendKeys("GUI_Access");
			driver.findElement(By.xpath(".//tr[@ng-click='selectPolicy(acp)']/td[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//button[@ng-click='dynamicPopover.close()']")).click();

			new Select(driver.findElement(By.xpath("(.//*[@id='sgroupId'])[2]"))).selectByVisibleText("HTTP");
			driver.findElement(By.xpath(".//*[@id='urlNameDiv']/textarea")).sendKeys(prop.getProperty("url"));

			driver.findElement(By.xpath(".//*[text()='Create']")).click();

			Log.info("Application Created : " + "IOT_GUI_App");
		} catch (Exception e) {
			throw (e);
		}

	}

	public void Verify(String Expected) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='alert alert-success']")));
			String actual = driver.findElement(By.xpath(".//*[@class='alert alert-success']")).getText();

			_assert.contains(actual, Expected, "Verify Success");

		} catch (Exception e) {

			Log.error("Unable to Verify Success Message");
			Log.error(e.toString());
			throw (e);
		}

	}

	public boolean checkIfAcpAndAppExists() throws Exception {

		try{
		
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Checking if there is any default ACP and App");
		
		boolean result = false;
		if (!findElement(By.xpath("(.//span[text()='Access Control Policy'])/.."),"ACP Tile").getAttribute(Attributes.GENERAL,"class")
				.contains("selected")) {

			findElement(By.xpath(".//span[text()='Access Control Policy']"),"ACP Tile").click("Click on ACP Tile");
			Thread.sleep(3000);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("acp_3")));
		findElement(By.id("acp_3"),"Search ACP").click("Click on Search ACP");
		
		Thread.sleep(10000);

		String acp_count = findElement(By.cssSelector(".totaldata.redbg.ng-binding"),"Get ACP Count").getAttribute(Attributes.TEXT);

		Log.info("ACP Count is "+ acp_count);
		if (!driver.findElement(By.xpath("(.//span[text()='Application Mgmt.'])/..")).getAttribute("class")
				.contains("selected")) {
			driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
			Thread.sleep(2000);
		}
		findElement(By.id("ae_2"),"Search Application").click("Click on Search Application");
		
		Thread.sleep(10000);

		String app_count = driver.findElement(By.cssSelector(".totaldata.redbg.ng-binding")).getText();
		
		Log.info("App Count is "+ app_count);

		if (acp_count.equals("0") || app_count.equals("0")) {

			result = true;
			
			Log.info("No deafult resources found , setting boolean to true");
		}else{
			
			Log.info("Default Resources found , hence skipping creation");
		}

		logout();
		return result;
		
		}catch(Exception e){
			Log.error("CONFIG ERROR : Unable to Check if DSM already has an ACP & Application");
			Log.error(e.toString());
			throw(e);
		}
		
	}
}
