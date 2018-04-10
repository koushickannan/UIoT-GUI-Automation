package com.hpe.iot.api.drivers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.Base64;

import com.automation.pageobjects.CustomerManagement;
import com.automation.pageobjects.SmokeTest;
import com.hpe.base.Base;
import com.hpe.iot.api.base.Base_API;

public class API_Setup extends Base {
	final static Logger logger = Logger.getLogger(API_Setup.class);
	
	static String acp = null;
	
	public static void CreateAsset(){
	
		try {
			if (!driver.findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/..")).getAttribute("class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}

			driver.findElement(By.id("asset_2")).click();

			com.hpe.iot.api.base.Base_API.asset_name = "API_Asset_Automation" + randomNumber();

			// WaitForFirefoxDOM();

			driver.findElement(By.name("resourceName")).sendKeys(com.hpe.iot.api.base.Base_API.asset_name);


			// WaitForFirefoxDOM();

			new Select(driver.findElement(By.id("resourceType"))).selectByValue("SENSOR");

			driver.findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/a")).click();

			int custSize = driver.findElements(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li")).size();

			for (int i = 1; i <= custSize; i++) {

				String custName = driver
						.findElement(
								By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li[" + i + "]/a/div/span"))
						.getText();

				if (custName.trim().equals("IoT")) {

					driver.findElement(By.xpath(".//div[contains(@ng-model,'customerId')]/div/ul/li[" + i + "]/a"))
							.click();
					
					Thread.sleep(2000);

					break;
				}
			}

			driver.findElement(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/a")).click();

			int grpSize = driver.findElements(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/ul/li")).size();
			
			Thread.sleep(2000);
			
			for (int i = 1; i <= grpSize; i++) {

				String grpName = driver
						.findElement(
								By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/ul/li[" + i + "]/a/div/span"))
						.getText();

				if (grpName.trim().equals("DEFAULT")) {

					driver.findElement(By.xpath(".//div[contains(@ng-model,'deviceGroupId')]/div/ul/li[" + i + "]/a"))
							.click();

					break;
				}
			}

			// driver.findElement(By.xpath("(.//*[@class='custom-select-search'])[1]/../ul/li[1]")).click();

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@ng-show='deviceProfileLoading']")));

			driver.findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])[2]/..//div[2]/div/a/span"))
					.click();

			int count1 = driver.findElements(By.xpath("(.//*[@class='custom-select-search'])[3]/../../div/ul/li"))
					.size();

			for (int i = 1; i <= count1 - 1; i++) {

				// driver.findElement(By.xpath("(.//*[@class='custom-select-search'])[3]/../../a")).click();

				String name = driver
						.findElement(By.xpath(
								"(.//*[@class='custom-select-search'])[3]/../../div/ul/li[" + i + "]/a/div[1]/span"))
						.getText();

				if (name.equals(prop.getProperty("device_profile") + "-" + prop.getProperty("model") + "-"
						+ prop.getProperty("version"))) {

					driver.findElement(
							By.xpath("(.//*[@class='custom-select-search'])[3]/../ul/li[" + i + "]/a/div[1]/span"))
							.click();
					break;
				}

			}


				driver.findElement(By.xpath(".//*[text()='Transport Channels']/..//div/div/input")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("iotParamsdeviceAddress")).sendKeys("123456789");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[text()='Create'])[1]")));
			driver.findElement(By.xpath("(.//*[text()='Create'])[1]")).submit();

			logger.info("Asset Created : " + Base_API.asset_name);
		} catch (Exception e) {

			logger.error(e.toString());

		}
		
	}
	
	public static void ManageAsset(){
		try{
			if (!driver.findElement(By.xpath("(.//span[text()='Asset Mgmt.'])/..")).getAttribute("class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Asset Mgmt.']")).click();
			}
			
			driver.findElement(By.id("asset_3")).click();
			driver.findElement(By.xpath(".//*[@class='btn quicklinks']")).click();
			driver.findElement(By.name("assetname")).sendKeys(Base_API.asset_name);
			driver.findElement(By.xpath(".//*[@value='Search']"));
			
			String assetid=driver.findElement(By.xpath(".//*[@st-table='assetList']/tbody/tr/td[2]/a")).getAttribute("href");
			Base_API.nodelink = assetid.substring(assetid.lastIndexOf("="), assetid.length());
			System.out.println(Base_API.nodelink);
			
		}catch(Exception e){
			
		}
		
		
	}
	public static void CreateACP(){
		try{
		if (!driver.findElement(By.xpath("(.//span[text()='Access Control Policy'])/..")).getAttribute("class")
				.contains("selected")) {

			driver.findElement(By.xpath(".//span[text()='Access Control Policy']")).click();
		}
		driver.findElement(By.id("acp_1")).click();

		 acp = "API_Automation_ACP" + randomNumber();

		// WaitForFirefoxDOM();

		driver.findElement(By.id("acpName")).sendKeys(acp);
		driver.findElement(By.id("acpDescription")).sendKeys("Desc");
		
		driver.findElement(By.id("acpSelect")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(.//*[@class='treeview'])[1]/li/ul[1]/li")));
		
		driver.findElement(By.xpath(".//*[@id='hpeTree_treeSearch']")).sendKeys(Base_API.asset_name);
		driver.findElement(By.xpath(".//*[@ng-click='hpeTree_searchAssetTree()']")).click();
		driver.findElement(By.id(Base_API.nodelink)).click();
		driver.findElement(By.xpath(".//button[text()='OK']")).click();
		
		driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[1]/label/input")).click();
		driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[2]/label/input")).click();
		driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[3]/label/input")).click();
		driver.findElement(By.xpath(".//*[text()='Allowed Operations']/../div/div[4]/label/input")).click();
		driver.findElement(By.xpath(".//button[text()='Create']")).click();

		logger.info("ACP Created : " + acp);
		
		}catch(Exception e){
			
		}
		
	}
	
	public static void CreateApp() throws InterruptedException{
		try{
		if (!driver.findElement(By.xpath("(.//span[text()='Application Mgmt.'])/..")).getAttribute("class")
				.contains("selected")) {
			driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
			Thread.sleep(2000);
		}
		driver.findElement(By.id("ae_1")).click();

		Base_API.app_name = "API_App_Automation" + randomNumber();


		// WaitForFirefoxDOM();

		driver.findElement(By.cssSelector("#appNameDiv>input")).sendKeys(Base_API.app_name);
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath("(.//*[@id='sgroupId'])[1]"))).selectByValue("1000");
		
		driver.findElement(By.id("selectPol")).click();
		driver.findElement(By.xpath(".//*[@ng-model='searchCriteria']")).sendKeys(acp);
		driver.findElement(By.xpath(".//tr[@ng-click='selectPolicy(acp)']/td[1]")).click();
		driver.findElement(By.xpath(".//button[text()='Done']"));
		
		new Select(driver.findElement(By.xpath("(.//*[@id='sgroupId'])[2]"))).selectByVisibleText("HTTP");
		driver.findElement(By.xpath(".//*[@id='urlNameDiv']/textarea")).sendKeys(prop.getProperty("url"));

		driver.findElement(By.xpath(".//*[text()='Create']")).click();

		logger.info("Application Created : " + Base_API.app_name);
		}catch(Exception e){
			
		}
		
	}
	public static void Manage_App(){
		
		try{
			if (!driver.findElement(By.xpath("(.//span[text()='Application Mgmt.'])/..")).getAttribute("class")
					.contains("selected")) {
				driver.findElement(By.xpath(".//span[text()='Application Mgmt.']")).click();
				Thread.sleep(2000);
			}
			driver.findElement(By.id("ae_2")).click();
			driver.findElement(By.xpath(".//*[@ng-model='name']")).sendKeys(Base_API.app_name);
			driver.findElement(By.xpath(".//button[contains(@ng-click,'searchEntity(')]")).click();
			driver.findElement(By.xpath("(.//*[@class='fa fa-pencil'])[3]")).click();
			driver.findElement(By.linkText(Base_API.app_name)).click();
			
			Base_API.origin = driver.findElement(By.xpath(".//b[text()='User Name']/../..//div[1]/span")).getText();
			driver.findElement(By.xpath(".//input[@ng-model='password']")).sendKeys("password");
			driver.findElement(By.xpath(".//button[text()='Reset password']")).click();
			
			logger.info("Origin = " +Base_API.origin);
			
			String  toencode = Base_API.origin+":password";
			Base_API.auth = Base64.getEncoder().encodeToString(toencode.getBytes("utf-8"));
			
			logger.info("Origin = " +Base_API.auth);
			
			
		}catch(Exception e){
			
		}
		
		
	}
}
