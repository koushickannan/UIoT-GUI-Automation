package com.automation.pageobjects;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class DeviceController extends ElementBase {
	
	final static Logger Log = Logger.getLogger(DeviceController.class.getName());
	public boolean CreateMQTTDeviceController() throws InterruptedException {
		boolean result =false;

		try{
		
		findElement(By.xpath(".//span[text()='Device Controllers']"),"DC Tile").click("Click on DC Tile");
		findElement(By.id("dcm_1"),"Manage DC Option").click("Click on Manage DC Option");

		Thread.sleep(3000);
		findElement(By.id("DCName"),"DC Name Text Box").sendKeys(prop.getProperty("controller_name") + randomNumber(),"Enter DC Name");
		findElement(By.id("RoutingType"),"Routing Type Drop Down").selectByIndex(1,"Select Index 1");
		findElement(By.id("TChannel"),"Transport Channel Text Box").sendKeys("MQTT"+randomNumber(),"Enter Transport Channel");
		Thread.sleep(1000);
		findElement(By.cssSelector("[name='oneM2M_compliant']"),"Selecting OneM2M Checkbox").click("Clicking Check box");
		findElement(By.cssSelector("[id='onem2mSchema'][ng-model='dcMgmt.onem2mSchema']"),"Selecting  oneM2M Schema").selectByValue("1.10", "Select latest schema");
		findElements(By.cssSelector("[id='dc_enabled'][ng-model='dcMgmt.enabled']"),"Dc Enabled").click("check DC enabled");
		findElement(By.name("HTTPEndpoint"),"HTTP End Point Text Box").sendKeys(prop.getProperty("url"),"Enter HTTP End Point");
		findElement(By.cssSelector("[name='brokerIp']"),"Broker IP field").sendKeys(assetDetailsProp.getProperty("mqtt.broker.ip"), "Entering MQTTBroker IP");
		findElement(By.cssSelector("[name='brokerPort']"),"Broker Port field").sendKeys(assetDetailsProp.getProperty("mqtt.broker.port"), "Entering MQTTBroker Port");
		findElement(By.xpath(".//div[@ng-model='customerId']"),"Customer Field").click("Select Customer Field");
		
		findElement(By.xpath(".//li[@role='presentation']/a/div/span"),"Selecting Customer").click("Customer Selected");
		findElement(By.xpath(".//button[contains(text(),'Create')]"),"Create Button").click("Click on Create");
		result=true;
		}catch(Exception e){
			
			Log.error("Unable to Create DC");
			Log.error(e.toString());
			throw(e);
			
		}
		return result;
	}

	public void VerifyDeviceController(String Expected) {

		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@ng-if='successSaveMsg']")));
		
		String brokerDetails=findElement(By.xpath(".//*[@id=\"dcData\"]/div/div/div[1]/div/span[2]"),"BrokerDetails").getAttribute(Attributes.TEXT);
		String [] tempSplit = brokerDetails.split(",");
		String usernameTemp = tempSplit[0];
		String passwordTemp = tempSplit[1];
		String[] usernameSplit = usernameTemp.split(":");
		String dcName = usernameSplit[1].trim();
		String[] passwordSplit = passwordTemp.split(":");
		String dcPassword =passwordSplit[1].trim();
		assetDetailsProp.setProperty("mqtt.dc.name", dcName);
		Log.info("DC Name Fetched is"+dcName);
		assetDetailsProp.setProperty("mqtt.dc.password", dcPassword);
		Log.info("DC password fetched is"+dcPassword);
		String actual = findElement(By.xpath(".//*[@ng-if='successSaveMsg']"),"Success Alert").getAttribute(Attributes.TEXT);

		_assert.contains(actual, Expected, "Verify DC Creation Success Message");
		
		}catch(Exception e){
			
			Log.error("Unable to Verify DC Success Msg");
			Log.error(e.toString());
			throw(e);
			
		}

	}
	

}
