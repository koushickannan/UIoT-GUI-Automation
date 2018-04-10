package com.automation.pageobjects;

import org.openqa.selenium.By;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class ApplicationVersion extends ElementBase {
	
	
	By  welcome = By.cssSelector(".text-muted.text-xs.block");
	By application = By.xpath(".//a[@data-target='#aboutModel']");
	By dsm_version = By.xpath(".//*[@id='aboutModel']/div[2]/div/div[1]/ul/li[1]");
	By dav_version = By.xpath(".//*[@id='aboutModel']/div[2]/div/div[2]/ul/li[1]");
	By close = By.xpath(".//*[text()='Close']");
	
	
	public  void ValidateApplicationVersion(){
		
		findElement(welcome, "Welcome link").click("Click on Welcome Link");
		findElement(application, "About Application Link").click("Click on About Application");
		String versionName = findElement(dsm_version, "DSM Version").getAttribute(Attributes.TEXT);
		
		_assert.equals(versionName, "Version :1.4.2", "Verifying DSM Version");
		
		findElement(close, "Close Button").click("Click on Close");
	}
	
	public void ValidateUserDetails(){
		
		findElement(welcome, "Welcome link").click("Click on Welcome Link");
		findElement(application, "About Application Link").click("Click on About Application");
		
		String versionName = findElement(dsm_version, "DSM Version").getAttribute(Attributes.TEXT);
		String davVersion = findElement(dav_version, "DAV Version").getAttribute(Attributes.TEXT);
		
		_assert.equals(versionName.trim(), "Version :1.4.2", "Verifying DSM Version");
		_assert.equals(davVersion.trim(), "Version :1.4.2", "Verifying DAV Version");
		
		findElement(close, "Close Button").click("Click on Close");
		
	}
	
	
	

}
