/**
 * 
 */
package com.automation.pageobjects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

/**
 * @author aloksu
 *
 */
public class UOC_AdvanceAssetListView extends ElementBase{

	final static Logger Log = Logger.getLogger(UOC_AdvanceAssetListView.class.getName());
	
	By Expand_Button = By.xpath("//*[@id='sliderViewButtonId']");
	By AssetList_Tile = By.xpath("//*[@id='sd-sliderview']/div/div/div/hpe-widget/div/div[2]/hpe-action-radio-button/div/div[7]/div/div[1]/label");
	By AssetTitle = By.xpath("//*[@ng-show='widget.configuration.showTitle']");
	By List_dropdown = By.xpath("//*[@class='ui-grid-icon-container']");
	By Asset_ID = By.xpath("//*[@id='menuitem-8']/button");
	
	By Chart_Dropdown = By.xpath("//*[@id='highcharts-0']//*[@class='highcharts-button-symbol']");
	By Bar_Dropdown = By.xpath("//*[@id='highcharts-2']//*[@class='highcharts-button-symbol']");
	

	String Asset_Table = "Asset List";
	
	public static void setup(String Domain) throws Exception {

		try{
		invokeBrowser(prop.getProperty("browser_name"));

		driver.navigate().to(prop.getProperty("url"));
		
		if(prop.getProperty("browser_name").equals("ie"))
			driver.navigate ().to ("javascript:document.getElementById('overridelink').click()");
			
		Thread.sleep(4000);
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
		
	public void Open_listView(){
		
		try{
			findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
			
			findElement(AssetList_Tile, "Find Advance Asset List View").click("Click on Advance Asset List View");
			
			String Asset_List = findElement( AssetTitle, "Table Heading").getAttribute(Attributes.TEXT);
			  _assert.equals(Asset_List, Asset_Table, "Table should be displayed"); 
			
		}catch(Exception e){
			Log.error("Unable to open Advance Asset List View");
			throw(e);
		}
	}	
	
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
	
	public void AssetStatus_Options(ArrayList<String> expectedList) throws Exception{
		
		try{
		findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
		
		findElement(AssetList_Tile, "Find Advance Asset List View").click("Click on Advance Asset List View");
		
	    findElement(Chart_Dropdown, "Find dropdown under Assets Status").click("Clicking on dropdown button");
		Thread.sleep(3000);
		WebElement element= driver.findElement(By.xpath("//*[@class='highcharts-menu']"));
		
		List<WebElement> actualList=element.findElements(By.xpath("//*[@class='highcharts-menu-item']"));
		
		for(int iterator=0;iterator<actualList.size();iterator++){
			
			if(actualList.get(iterator).getText().trim().equalsIgnoreCase(expectedList.get(iterator).trim())){
				
				Log.info("Expected Value " +expectedList.get(iterator).trim()+ "----"+ "Actual Value " + actualList.get(iterator).getText().trim());
			}
		}
	}catch(Exception e){
		Log.error("All options present under Asset Status dropdown are not listed");
		throw(e);
	}
			
	}
	
    public void AssetType_Options(ArrayList<String> expectedList) throws Exception{
		try{
			
		findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
		
		findElement(AssetList_Tile, "Find Advance Asset List View").click("Click on Advance Asset List View");
		
	    findElement(Bar_Dropdown, "Find Asset Types dropdown").click("Clicking on Asset Types dropdown");
		Thread.sleep(5000);
		WebElement element= driver.findElement(By.xpath("//*[@class='highcharts-menu']"));
		
		List<WebElement> actualList=element.findElements(By.xpath("//*[@class='highcharts-menu-item']"));
		
		for(int iterator=0;iterator<actualList.size();iterator++){
			
			if(actualList.get(iterator).getText().trim().equalsIgnoreCase(expectedList.get(iterator).trim())){
				
				Log.info("Expected Value " +expectedList.get(iterator).trim()+ "----"+ "Actual Value " + actualList.get(iterator).getText().trim());
			}
		}
		}catch(Exception e){
			Log.error("All options present under Asset Types dropdown are not listed");
			throw(e);
		}
     }
	
        public void SelectList(String expectedList) throws Exception{
	
        try{
	     findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
	     findElement(AssetList_Tile, "Find Advance Asset List View").click("Click on Advance Asset List View");
         findElement(Chart_Dropdown, "Expand the dropdown").click("Click on file type to download");
	
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
        
        public void AssetList_hideCol(String ColumnName) throws Exception{
    		
    		try{
    			
                findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
                Thread.sleep(4000);
                int preColCount =  colCount();
    	        System.out.println("No of cols are : " +preColCount); 

//                roleProp.setProperty("columncount", preColCount);
    	        switch(ColumnName){
    			 
    			case "Asset_ID":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Asset ID')])[1]")).click();
    				int New_Count = colCount();
    				if (New_Count < preColCount) {
    					Log.info("Asset_ID column got disable");
    				}else {
    					Log.info(" Not able to disable Asset_ID column from asset list");
    				}
    				break;
    				
    			case "Asset_Name":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Asset Name')])[1]")).click();
    				int New_Count_b = colCount();

    				if (New_Count_b < preColCount) {
    					Log.info("Asset_Name column got disable");
    				}else {
    					Log.info(" Not able to disable Asset_Name column from asset list");
    				}
    				break;
    				
    			case "Asset_Status":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Asset Status')])[1]")).click();
    				int New_Count_c = colCount();

    				if (New_Count_c < preColCount) {
    					Log.info("Asset_Status column got disable");
    				}else {
    					Log.info(" Can not able to disable Asset_Status column from asset list");
    				}
    				break;
    				
    			case "Asset_Type":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Asset Type')])[1]")).click();
    				int New_Count_d = colCount();

    				if (New_Count_d < preColCount) {
    					Log.info("Asset_Type column got disable");
    				}else {
    					Log.info(" Can not able to disable Asset_Type column from asset list");
    				}
    				break;
    				
    			case "Device_profile_ID":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Device Profile ID')])[1]")).click();
    				int New_Count_e = colCount();

    				if (New_Count_e < preColCount) {
    					Log.info("Device_profile_ID column got disable");
    				}else {
    					Log.info(" Can not able to disable Device_profile_ID column from asset list");
    				}
    				break;
    				
    			case "Customer_Name":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Customer Name')])[1]")).click();
    				int New_Count_f = colCount();

    				if (New_Count_f < preColCount) {
    					Log.info("Customer_Name column got disable");
    				}else {
    					Log.info(" Can not able to disable Customer_Name column from asset list");
    				}
    				break;	
    			}
    		}catch(Exception e){
    			Log.error("Not able to open the dropdown and disable coulmns under Asset List table");
    			throw(e);
    		}
    	}
        
        public void Popup_Permissions() throws Exception{
        	try{
        		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[3]/button[1]/translate")).click();
        		Thread.sleep(2000);
                driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[3]/button[1]/translate")).click();
        	}catch(Exception e){
    			Log.error("Not able to close the popup window");
    			throw(e);
        }
    }
        
        public void AssetList_activeCol(String ColumnName) throws Exception{
    		
    		try{
    			
                findElement(Expand_Button, "Find Expand Button").click("Click to expand side bar");
                Thread.sleep(6000);
                int preColCount =  colCount();
    	        System.out.println("No of cols are : " +preColCount); 

//                roleProp.setProperty("columncount", preColCount);
    	        switch(ColumnName){
    			 
    			case "Asset_ID":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Asset ID')])[2]")).click();
    				int New_Count = colCount();
    				if (New_Count > preColCount) {
    					Log.info("Asset_ID column got active");
    				}else {
    					Log.info(" Can not able to active Asset_ID column from asset list");
    				}
    				break;
    				
    			case "Asset_Name":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Asset Name')])[2]")).click();
    				int New_Count_b = colCount();

    				if (New_Count_b > preColCount) {
    					Log.info("Asset_Name column got active");
    				}else {
    					Log.info(" Can not able to active Asset_Name column from asset list");
    				}
    				break;
    				
    			case "Asset_Status":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Asset Status')])[2]")).click();
    				int New_Count_c = colCount();

    				if (New_Count_c > preColCount) {
    					Log.info("Asset_Status column got active");
    				}else {
    					Log.info(" Can not able to active Asset_Status column from asset list");
    				}
    				break;
    				
    			case "Asset_Type":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Asset Type')])[2]")).click();
    				int New_Count_d = colCount();

    				if (New_Count_d > preColCount) {
    					Log.info("Asset_Type column got active");
    				}else {
    					Log.info(" Can not able to active Asset_Type column from asset list");
    				}
    				break;
    				
    			case "Device_profile_ID":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Device Profile ID')])[2]")).click();
    				int New_Count_e = colCount();

    				if (New_Count_e > preColCount) {
    					Log.info("Device_profile_ID column got active");
    				}else {
    					Log.info(" Can not able to active Device_profile_ID column from asset list");
    				}
    				break;
    				
    			case "Customer_Name":
    				driver.findElement(By.xpath(".//*[@class='ui-grid-icon-menu']")).click();
    				driver.findElement(By.xpath("(.//button[contains(text(),'Customer Name')])[2]")).click();
    				int New_Count_f = colCount();

    				if (New_Count_f > preColCount) {
    					Log.info("Customer_Name column got active");
    				}else {
    					Log.info(" Can not able to active Customer Name column from asset list");
    				}
    				break;
    			}
    		}catch(Exception e){
    			Log.error("Unable to open Advance Asset List View");
    			throw(e);
    		}
    	}
}












