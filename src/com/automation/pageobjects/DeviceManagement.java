package com.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class DeviceManagement extends ElementBase {

	final static Logger Log = Logger.getLogger(DeviceManagement.class.getName());
	
	By asset_topology_dash = By.xpath(".//*[text()='Asset Topology']");
	By search = By.xpath("(.//*[@class='hitarea'])/../span");
	By select_device = By.xpath(".//*[@class='deviceArea ng-scope']/span/li[1]/span");
	
	
	public void VerifyDeviceMgmtTab(){
		try{
		findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
		findElement(By.xpath(".//span[text()='"+SmokeTest.assetList.get(0)+"']"), "Select Device").click("Click on Device");
		findElement(By.xpath(".//*[@ng-click='deviceManagement()']"), "Device Mgmt Link").click("Click on Device Mgmt Link");
		
		String dp_manufactuer = findElement(By.xpath(".//*[text()='Manufacturer:']/following-sibling::div/span"), "dp Manufacturer").getAttribute(Attributes.TEXT);
		
		String dp_model = findElement(By.xpath(".//*[text()='Model:']/following-sibling::div/span"), "dp Model").getAttribute(Attributes.TEXT);
		
		String dp_version = findElement(By.xpath(".//*[text()='Version:']/following-sibling::div/span"), "dp Version").getAttribute(Attributes.TEXT);
		
		_assert.equals(dp_manufactuer, prop.getProperty("device_profile"), "Verify DP Manufactuer");
		
		_assert.equals(dp_model,prop.getProperty("model"),"Verify DP Model");
		
		_assert.equals(dp_version,prop.getProperty("version"),"Verify DP Version");
		
		}catch(Exception e){
			
			Log.error("Unable to Verify Device Management Tab");
			Log.error(e.toString());
			throw(e);
		}
	}
	
	public void DeviceManagement_DiagnosticMonitering(){
		try{
			
			
			findElement(asset_topology_dash, "Asset Topology in Dashboard").click("Click on Asset Topology");
			findElement(By.xpath(".//span[text()='"+SmokeTest.assetList.get(0)+"']"), "Select Device").click("Click on Device");
			findElement(By.xpath(".//*[@ng-click='deviceManagement()']"), "Device Mgmt Link").click("Click on Device Mgmt Link");
			
			findElement(By.cssSelector("#diagnostic_and_monitoring>a"), "Diagnostic Monitoring Tab").click("Click on Diagnostic and Monitoring Tab");
			
			String mem = findElement(By.xpath("(.//*[@id='0-header']/div/ng-include/div/div[1]/h5)[2]"), "Memory").getAttribute(Attributes.TEXT);
			String bat = findElement(By.xpath("(.//*[@id='1-header']/div/ng-include/div/div[1]/h5)[2]"),"Battery").getAttribute(Attributes.TEXT);
			
			_assert.contains(mem, "Memory", "Verify Memory Capability");
			_assert.contains(bat,"Battery","Verify Battery Capability");
			
			
			
		}catch(Exception e){
			
			Log.error("Unable to Verify Diagnostic and Monitering in Device Management Screen");
			Log.error(e.toString());
			throw(e);
		}
	}
	
}
