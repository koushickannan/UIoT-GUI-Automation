package com.automation.pageobjects;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.hpe.base.Attributes;
import com.hpe.iot.api.base.Base_API;
import com.hpe.utilities.CSVUtils;
import com.hpe.webdriver.ElementBase;

public class ContainerProfile extends ElementBase {
	
	By containerProfileTile = By.xpath(".//span[text()='Container Profile']");
	By createContainerField= By.id("containerP_1");
	
	
	
	public void ContainerUpload( String containerProfileFile) throws Exception {

		findElement(containerProfileTile, "Container Profile Tile")
				.click("Click on Container Profile");

		findElement(createContainerField, "Create Container").click("Click on Create ");

		Thread.sleep(2000);

		findElement(By.xpath(".//*[@type='file']"), "Upload")
				.sendKeys(System.getProperty("user.dir") + "\\testdata\\"+containerProfileFile, "Upload File");

		Thread.sleep(3000);

		findElement(By.xpath(".//*[text()='Submit']"), "Create Button").click("Click on Create Button");

	}


}
