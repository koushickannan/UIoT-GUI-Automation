/**
 * 
 */
package com.automation.pageobjects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementAction;
import com.hpe.webdriver.ElementBase;

/**
 * @author dsingh1
 *
 */

//public class UoC_Asset_param_view {

//}


public class UoC_Asset_param_view extends ElementBase{

	
	final static Logger Log = Logger.getLogger(UoC_Asset_param_view.class.getName());
	
	By Expand_Button = By.xpath(".//*[@id='sliderViewButtonId']");
	By AssetParam_view_Titel = By.xpath(".//*[@class = \"hpe-action-radio-button-widget\"]/div[7]/div[1]/div[2]/label/i");
	By AssetTitle = By.xpath(".//*[@ng-show= \"widget.configuration.showTitle\" ]");
	By AssetList_Tile = By.xpath("//*[@id='sd-sliderview']/div/div/div/hpe-widget/div/div[2]/hpe-action-radio-button/div/div[7]/div/div[1]/label");
	By Chart_Dropdown_a = By.xpath("//*[@id='highcharts-0']//*[@class='highcharts-button-symbol']");
	By Chart_Dropdown_b = By.xpath("//*[@id='highcharts-2']//*[@class='highcharts-button-symbol']");
	By PopUp_save = By.xpath("/html/body/div[1]/div/div/div/div[3]/button[1]/translate");
	By PopUp_Ok = By.xpath("/html/body/div[1]/div/div/div/div[3]/button[1]/translate");
		
	String Asset_Table = "Asset List";

	
	public static void setup(String Domain) throws Exception {

		try{
			
		invokeBrowser(prop.getProperty("browser_name"));

		driver.navigate().to(prop.getProperty("url_uoc"));
		
		if(prop.getProperty("browser_name").equals("ie"))
			driver.navigate ().to ("javascript:document.getElementById('overridelink').click()");
			
		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		
		if(Boolean.valueOf(prop.getProperty("isSSO"))){
				if(driver.getTitle().contains("Log in to")){
					Log.info("CONFIG : URL Loaded Successfully");
					
					}
					else{
						throw new Exception();
					}
				
			}
		else if(!Boolean.valueOf(prop.getProperty("isSSO"))){
			if (Domain == "UoC") {
				if(driver.getTitle().contains("UIoT Console")){
					
					Log.info("CONFIG : URL Loaded Successfully");
					
					}
				
			}else {
				throw new Exception();
			}
			if(driver.getTitle().contains("HPE | Universal Internet of Things")){
				
				Log.info("CONFIG : URL Loaded Successfully");
				
				}
			else{
				throw new Exception();
			}
			
		  }
		}catch(Exception e){
			
			Log.error("CONFIG ERROR : Unable to load URL , Internet/Application is down");
			
			throw(e);
		}
		
	}

	public void logout() throws Exception {

		try{
			if(Boolean.valueOf(prop.getProperty("isSSO"))){
		findElement(By.xpath(".//*[@class = \"container-fluid\"]/div[2]/ul/li[6]/a"),"admin  Menu").click("Click on admin drop down Menu");
		findElement(By.xpath(".//*[@role= \"menuitem\"]/a"),"Logout Link").click("Click on Logout Link");
		

		Thread.sleep(2000);
			}
		
		else{
			findElement(By.xpath(".//*[@class = \"container-fluid\"]/div[2]/ul/li[6]/a"),"admin  Menu").click("Click on admin drop down Menu");
			findElement(By.xpath(".//*[@role= \"menuitem\"]/a"),"Logout Link").click("Click on Logout Link");

			Thread.sleep(2000);
			
		}
		}
		catch(Exception e){
			
			Log.error("Unable to LOGOUT and Hence Re-Lauching the browser");
			Log.error(e.getMessage().toString());
			closeBrowser();
			setup();
			
		}
		}

	public int colCount() {

		
		List<WebElement> col = driver.findElements(By.xpath("(.//*[@role='rowgroup'])[1]/div/div/div/div/div/div"));
		int totalrowCount = col.size();
        return totalrowCount;

	}

	public void handling_popup() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[3]/button[1]/translate")).click();
		Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[3]/button[1]/translate")).click();

	}
	
	public void Open_listView(){
		
		try{
			findElement(Expand_Button, "Finding Expand Button").click("Click to expand side bar");
			
			findElement(AssetParam_view_Titel, "Find Asset Parameter view").click("Click on Asset parameter view slide bar");
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(AssetTitle));
			  // _assert.contains(findElement(AssetTitle, "Asset table title").getAttribute(Attributes.TEXT),"Asset_Table", "Table should be displayed");
			
			String Asset_List = findElement( AssetTitle, "Table Heading").getAttribute(Attributes.TEXT);
			  _assert.equals(Asset_List, Asset_Table, "Table should be displayed"); 
			
		}catch(Exception e){
			Log.error("Unable to open Advance Asset List View");
			throw(e);
		}
	}
	
	public void Asset_hideCol(String ColumnName) throws Exception{
		
		try{
			
            findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
            Thread.sleep(6000);
            int preColCount =  colCount();
	        System.out.println("No of cols are : " +preColCount); 

//            roleProp.setProperty("columncount", preColCount);
	        switch(ColumnName){
			 
			case "Customer_Name":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),'Customer Name')])[1]")).click();
				int New_Count = colCount();
				if (New_Count < preColCount) {
					Log.info("Customer_Name column got disable");
				}else {
					Log.info(" Can not able to disable Customer_Name column from asset list");
				}
				
				break;
			case "Customer_Id":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),' Customer Id')])[1]")).click();
				int New_Count_b = colCount();

				if (New_Count_b < preColCount) {
					Log.info("Customer_Id column got disable");
				}else {
					Log.info(" Can not able to disable Customer_Id column from asset list");
				}

				break;
			case "Asset_Id":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),' Asset Id')])[1]")).click();
				int New_Count_c = colCount();

				if (New_Count_c < preColCount) {
					Log.info("Asset_Id column got disable");
				}else {
					Log.info(" Can not able to disable Asset_Id column from asset list");
				}

				break;
			case "Asset_Name":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),' Asset Name')])[1]")).click();
				int New_Count_d = colCount();

				if (New_Count_d < preColCount) {
					Log.info("Asset_Name column got disable");
				}else {
					Log.info(" Can not able to disable Asset_Name column from asset list");
				}

				break;
			case "Device_profile_ID":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),' Device Profile ID')])[1]")).click();
				int New_Count_e = colCount();

				if (New_Count_e < preColCount) {
					Log.info("Device_profile_ID column got disable");
				}else {
					Log.info(" Can not able to disable Device_profile_ID column from asset list");
				}

				break;
				
			}
		}catch(Exception e){
			Log.error("Unable to open Advance Asset List View");
			throw(e);
		}
	}

	public void Asset_activeCol(String ColumnName) throws Exception{
		
		try{
			
            findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
            Thread.sleep(9000);
            int preColCount =  colCount();
	        System.out.println("No of cols are : " +preColCount); 

//            roleProp.setProperty("columncount", preColCount);
	        switch(ColumnName){
			 
			case "Customer_Name":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),'Customer Name')])[2]")).click();
				int New_Count = colCount();
				if (New_Count > preColCount) {
					Log.info("Customer_Name column got active");
				}else {
					Log.info(" Can not able to active Customer_Name column from asset list");
				}
				
				break;
			case "Customer_Id":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),' Customer Id')])[2]")).click();
				int New_Count_b = colCount();

				if (New_Count_b > preColCount) {
					Log.info("Customer_Id column got active");
				}else {
					Log.info(" Can not able to active Customer_Id column from asset list");
				}

				break;
			case "Asset_Id":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),' Asset Id')])[2]")).click();
				int New_Count_c = colCount();

				if (New_Count_c > preColCount) {
					Log.info("Asset_Id column got active");
				}else {
					Log.info(" Can not able to active Asset_Id column from asset list");
				}

				break;
			case "Asset_Name":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),' Asset Name')])[2]")).click();
				int New_Count_d = colCount();

				if (New_Count_d > preColCount) {
					Log.info("Asset_Name column got active");
				}else {
					Log.info(" Can not able to active Asset_Name column from asset list");
				}

				break;
			case "Device_profile_ID":
				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
				driver.findElement(By.xpath("(.//button[contains(text(),' Device Profile ID')])[2]")).click();
				int New_Count_e = colCount();

				if (New_Count_e > preColCount) {
					Log.info("Device_profile_ID column got active");
				}else {
					Log.info(" Can not able to active Device_profile_ID column from asset list");
				}

				break;
				
			}
		}catch(Exception e){
			Log.error("Unable to open Advance Asset List View");
			throw(e);
		}
	}

// newly Added Method //
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}
	
	public void DeleteFile(String downloadPath, String fileName){
		
		File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName)){
	            dir_contents[i].delete();
	            break;
	        }
	     }
		
	}

	public void AssetStatus_Options(ArrayList<String> expectedList,String option) throws Exception{
		
		try{
		findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
		if (option == "a"){
			findElement(Chart_Dropdown_a, "Find dropdown under Assets Status").click("Clicking on first dropdown button");
		}else {
		    findElement(Chart_Dropdown_b, "Find dropdown under Assets Status").click("Clicking on second dropdown button");
				
		}
		Thread.sleep(3000);
		WebElement element= driver.findElement(By.xpath("//*[@class='highcharts-menu']"));
		
		List<WebElement> actualList=element.findElements(By.xpath("//*[@class='highcharts-menu-item']"));
		
		for(int iterator=0;iterator<actualList.size();iterator++){
			
			if(actualList.get(iterator).getText().trim().equalsIgnoreCase(expectedList.get(iterator).trim())){
				System.out.println(actualList.get(iterator).getText().equalsIgnoreCase(expectedList.get(iterator)));
				//logger.log(LogStatus.INFO, "Expected Value" +expectedList.get(iterator).trim()+ "----------"+ "Actual Value" + actualList.get(iterator).getText().trim());
				//Log.debug("Expected Value" +expectedList.get(iterator).trim()+ "----------"+ "Actual Value" + actualList.get(iterator).getText().trim());
			}
		}
	}catch(Exception e){
		Log.error("All options present under Asset Status dropdown are not listed");
		throw(e);
	}
			
	}

    public void SelectList(String expectedList,String graphoption) throws Exception{
    	
        try{
        
        	if (graphoption == "a") {
                findElement(Chart_Dropdown_a, "Click").click("Clicking");
        		
        	}else {
                findElement(Chart_Dropdown_b, "Click").click("Clicking");
        		
        	}
		 WebElement element= driver.findElement(By.xpath("//*[@class='highcharts-menu']"));
		 List<WebElement> actualList=element.findElements(By.xpath("//*[@class='highcharts-menu-item']"));
		 for(int iterator=0;iterator<actualList.size();iterator++){
			if(actualList.get(iterator).getText().trim().equalsIgnoreCase(expectedList)){
				actualList.get(iterator).click();
				break;
			}
		  }
        }catch(Exception e){
			Log.error("Download not successful");
			throw(e);
		} 
	
	}
 
	
}












