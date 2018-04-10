package com.automation.drivers;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.pageobjects.ApplicationMgmt;
import com.automation.pageobjects.SmokeTest;
import com.automation.pageobjects.UOC_AdvanceAssetListView;
import com.automation.pageobjects.Users;
import com.automation.pageobjects.UoC_Asset_param_view;
import com.hpe.base.Base;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class DriverScript_UoC extends Base {

	UoC_Asset_param_view UoCAssetParam = new UoC_Asset_param_view();
	Users user = new Users();
	SmokeTest smoke = new SmokeTest();
	ApplicationMgmt aem = new ApplicationMgmt();
	UOC_AdvanceAssetListView Assetlist = new UOC_AdvanceAssetListView();
	String Download_path = System.getProperty("user.dir")+"//testdata//";
	String Delete_path = System.getProperty("user.dir")+"//testdata//";



	/*@BeforeSuite(groups = { "Smoke", "Regression", "Functional" })
	public void start() throws Exception {
		UoC_Asset_param_view.setup("UoC");
		
	}
	
	@AfterSuite(groups = { "Smoke", "Regression", "Functional" })
	public void clean() throws Exception {
		closeBrowser();
	}*/

/*	@AfterMethod(groups = { "Smoke", "Critical_Regression"})
	public void exit(ITestResult result) throws Exception {
		
		UoCAssetParam.logout();
		//closeBrowser();
	} */


        @Test(priority = 701, groups = { "Smoke", "Critical_Regression" }, description = "Open Advance Asset List View page", enabled = true)
    	public void Open_AssetListView() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
    		
    		Assetlist.Open_listView();
    		Assetlist.logout();
        }
        
        @Test(priority = 702, groups = { "Smoke", "Critical_Regression" }, description = "Check the options present under Asset Status chart dropdown", enabled = true)
    	public void AssetStatus_AllOptions() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.AssetStatus_Options(new ArrayList<String>(
    			    Arrays.asList("Print chart","Download PNG image","Download JPEG image","Download PDF document","Download SVG vector image","Download CSV","Download XLS")));
    		Assetlist.logout();
        }
        
        @Test(priority = 703, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download PNG image of Assets Status chart", enabled = true)
    	public void AssetStatusPNG_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download PNG image");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "chart.png"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "chart.png");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 704, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download JPEG image of Assets Status chart", enabled = true)
    	public void AssetStatusJPEG_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download JPEG image");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "chart.jpeg"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "chart.jpeg");
    		Assetlist.logout();

        }
        
        @Test(priority = 705, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download PDF file of Assets Status chart", enabled = true)
    	public void AssetStatusPDF_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download PDF document");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "chart.pdf"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "chart.pdf");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 706, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download SVG Vector image of Assets Status chart", enabled = true)
    	public void AssetStatusSVGvector_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download SVG vector image");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "chart.svg"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "chart.svg");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 707, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download CSV file of Assets Status chart", enabled = true)
    	public void AssetStatusCSV_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download CSV");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "assets-status.csv"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "assets-status.csv");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 708, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download XLS file of Assets Status chart", enabled = true)
    	public void AssetStatusXLS_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download XLS");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "assets-status.xls"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "assets-status.xls");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 709, groups = { "Smoke", "Critical_Regression" }, description = "Check the options present under Assets Type graph dropdown", enabled = true)
    	public void AssetType_AllOptions() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.AssetType_Options(new ArrayList<String>(
    	     Arrays.asList("Print chart","Download PNG image","Download JPEG image","Download PDF document","Download SVG vector image","Download CSV","Download XLS")));
    		Assetlist.logout();
        }
        
        @Test(priority = 710, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download PNG image of Assets Type graph", enabled = true)
    	public void AssetTypePNG_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download PNG image");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "chart.png"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "chart.png");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 711, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download JPEG image of Assets Type graph", enabled = true)
    	public void AssetTypeJPEG_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download JPEG image");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "chart.jpeg"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "chart.jpeg");
    		Assetlist.logout();

        }
        
        @Test(priority = 712, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download PDF file of Assets Type graph", enabled = true)
    	public void AssetTypePDF_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download PDF document");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "chart.pdf"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "chart.pdf");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 713, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download SVG Vector image of Assets Type graph", enabled = true)
    	public void AssetTypeSVGvector_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download SVG vector image");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "chart.svg"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "chart.svg");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 714, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download CSV file of Assets Type graph", enabled = true)
    	public void AssetTpyeCSV_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download CSV");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "assets-status.csv"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "assets-status.csv");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 715, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download XLS file of Assets Type graph", enabled = true)
    	public void AssetTypeXLS_download() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.SelectList("Download XLS");
    		Thread.sleep(3000);
    		Assert.assertTrue(Assetlist.isFileDownloaded(Download_path, "assets-status.xls"), "Failed to download Expected document");
    		Thread.sleep(3000);
    		Assetlist.DeleteFile(Delete_path, "assets-status.xls");
    		Assetlist.logout();
    		
        }
        
        @Test(priority = 716, groups = { "Smoke", "Critical_Regression" }, description = "Check able to hide Asset Id column from Asset List dropdwon", enabled = true)
    	public void hide_AssetID_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_hideCol("Asset_ID");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
        
        @Test(priority = 717, groups = { "Smoke", "Critical_Regression" }, description = "Check able to hide Asset Name column from Asset List dropdwon", enabled = true)
    	public void hide_AssetName_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		wait(2000);
    		Assetlist.AssetList_hideCol("Asset_Name");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
       
        @Test(priority = 718, groups = { "Smoke", "Critical_Regression" }, description = "Check able to hide Asset Status column from Asset List dropdwon", enabled = true)
    	public void hide_AssetStatus_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_hideCol("Asset_Status");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
        
        @Test(priority = 719, groups = { "Smoke", "Critical_Regression" }, description = "Check able to hide Asset Type column from Asset List dropdwon", enabled = true)
    	public void hide_AssetType_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_hideCol("Asset_Type");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
        
        @Test(priority = 720, groups = { "Smoke", "Critical_Regression" }, description = "Check able to hide Device Profile ID column from Asset List dropdwon", enabled = true)
    	public void hide_DeviceProfileID_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_hideCol("Device_profile_ID");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
        
        @Test(priority = 721, groups = { "Smoke", "Critical_Regression" }, description = "Check able to hide Customer Name column from Asset List dropdwon", enabled = true)
    	public void hide_CustomerName_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_hideCol("Customer_Name");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
        
        @Test(priority = 722, groups = { "Smoke", "Critical_Regression" }, description = "Check able to activate Asset ID column from Asset List dropdwon", enabled = true)
    	public void activate_AssetID_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_activeCol("Asset_ID");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }

      @Test(priority = 723, groups = { "Smoke", "Critical_Regression" }, description = "Check able to activate Asset Name column from Asset List dropdwon", enabled = true)
    	public void activate_AssetName_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_activeCol("Asset_Name");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
        
      @Test(priority = 724, groups = { "Smoke", "Critical_Regression" }, description = "Check able to activate Asset Status column from Asset List dropdwon", enabled = true)
    	public void activate_AssetStatus_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_activeCol("Asset_Status");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }

      @Test(priority = 725, groups = { "Smoke", "Critical_Regression" }, description = "Check able to activate Asset Type column from Asset List dropdwon", enabled = true)
    	public void activate_AssetType_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_activeCol("Asset_Type");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
      
      @Test(priority = 726, groups = { "Smoke", "Critical_Regression" }, description = "Check able to activate Device Profile ID column from Asset List dropdwon", enabled = true)
    	public void activate_DeviceProfileID_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_activeCol("Device_profile_ID");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }

      @Test(priority = 727, groups = { "Smoke", "Critical_Regression" }, description = "Check able to activate Customer Name column from Asset List dropdwon", enabled = true)
    	public void activate_CustomerName_AssetList() throws Exception{
    		
    		logger.assignCategory("UOC");
    		logger.log(LogStatus.INFO, "Logging in");
    		login(prop.getProperty("username"), prop.getProperty("password"));
    		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
            
    		Assetlist.Open_listView();
    		Assetlist.AssetList_activeCol("Customer_Name");
    		Assetlist.logout();
    		Assetlist.Popup_Permissions();
        }
	
	
//Asset Parameter view
	
    @Test(priority = 728, groups = { "Smoke", "Critical_Regression" }, description = "Open  Asset parameter View list page", enabled = true)
	public void Open_Parameters_View_list() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		
		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
		
		UoCAssetParam.Open_listView();
		UoCAssetParam.logout();
    }

	
    @Test(priority = 729, groups = { "Smoke", "Critical_Regression" }, description = "Check the options present under Graph chart A in Asset parameter view module", enabled = true)
 	public void Graph_A_AllOptions() throws Exception{
 		
 		logger.assignCategory("UOC");
 		logger.log(LogStatus.INFO, "Logging in");
 		login(prop.getProperty("username"), prop.getProperty("password"));
 		logger.log(LogStatus.INFO, "Try to access graph view in Asset parameter view");
        UoCAssetParam.Open_listView();
 		UoCAssetParam.AssetStatus_Options(new ArrayList<String>(
 			    Arrays.asList("Print chart","Download PNG image","Download JPEG image","Download PDF document","Download SVG vector image","Download CSV","Download XLS")),"a");
 		UoCAssetParam.logout();
     }

    
    @Test(priority = 730, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download PNG image of Graph A chart", enabled = true)
	public void Graph_A_PNG_download() throws Exception{
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
		UoCAssetParam.SelectList("Download PNG image","a");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.png"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.png");
		UoCAssetParam.logout();
		
    }
    
    @Test(priority = 731, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download JPEG image of  Graph A chart", enabled = true)
	public void Graph_A_JPEG_download() throws Exception{
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download JPEG image","a");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.jpeg"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.jpeg");
		UoCAssetParam.logout();

    }
    
    @Test(priority = 732, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download PDF file of  Graph A chart", enabled = true)
	public void Graph_A_PDF_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download PDF document","a");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.pdf"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.pdf");
		UoCAssetParam.logout();
		
    }
    
    @Test(priority = 733, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download SVG Vector image of  Graph A chart", enabled = true)
	public void Graph_A_SVGvector_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download SVG vector image","a");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.svg"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.svg");
		UoCAssetParam.logout();
		
    }
    
    @Test(priority = 734, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download CSV file of  Graph A chart", enabled = true)
	public void Graph_A_CSV_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download CSV","a");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.csv"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "assets-status.csv");
		UoCAssetParam.logout();
		
    }
    
    @Test(priority = 735, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download XLS file of  Graph A chart", enabled = true)
	public void Graph_A_XLS_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download XLS","a");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.xls"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "assets-status.xls");
		UoCAssetParam.logout();
		
    }

    
    @Test(priority = 736, groups = { "Smoke", "Critical_Regression" }, description = "Check the options present under Graph chart B in Asset parameter view module", enabled = true)
	public void Graph_B_AllOptions() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		//"Print chart","Download PNG image","Download JPEG image","Download PDF document","Download SVG vector image","Download CSV","Download XLS"
		logger.log(LogStatus.INFO, "Try to access Advance Asset List View");
        
        UoCAssetParam.Open_listView();
 		UoCAssetParam.AssetStatus_Options(new ArrayList<String>(
 			    Arrays.asList("Print chart","Download PNG image","Download JPEG image","Download PDF document","Download SVG vector image","Download CSV","Download XLS")),"b");
 		UoCAssetParam.logout();
    }
   
    @Test(priority = 736, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download PNG image of Graph B chart", enabled = true)
	public void Graph_B_PNG_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download PNG image","b");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.png"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.png");
		UoCAssetParam.logout();
		
    }
    
    @Test(priority = 737, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download JPEG image of Graph B chart", enabled = true)
	public void Graph_B_JPEG_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download JPEG image","b");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.jpeg"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.jpeg");
		UoCAssetParam.logout();

    }
    
    @Test(priority = 738, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download PDF file of Graph B chart", enabled = true)
	public void Graph_B_PDF_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download PDF document","b");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.pdf"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.pdf");
		UoCAssetParam.logout();
		
    }
    
    @Test(priority = 739, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download SVG Vector image of Graph B chart", enabled = true)
	public void Graph_B_SVGvector_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download SVG vector image","b");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.svg"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.svg");
		UoCAssetParam.logout();
		
    }
    
    @Test(priority = 740, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download CSV file of Graph B chart", enabled = true)
	public void Graph_B_CSV_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download CSV","b");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.csv"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.csv");
		UoCAssetParam.logout();
		
    }
    
    @Test(priority = 741, groups = { "Smoke", "Critical_Regression" }, description = "Check User is able to download XLS file of Graph B chart", enabled = true)
	public void Graph_B_XLS_download() throws Exception{
		
		logger.assignCategory("UOC");
		logger.log(LogStatus.INFO, "Logging in");
		login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "Try to access Asset Parameters view");
        UoCAssetParam.Open_listView();
        UoCAssetParam.SelectList("Download XLS","b");
		Thread.sleep(3000);
		Assert.assertTrue(UoCAssetParam.isFileDownloaded(Download_path, "chart.xls"), "Failed to download Expected document");
		Thread.sleep(3000);
		UoCAssetParam.DeleteFile(Delete_path, "chart.xls");
		UoCAssetParam.logout();
		
    }

 	@Test(priority = 742, groups = { "Smoke", "Critical_Regression" }, description = " Check able to  hide customer Name column from Asset List dropdwon", enabled = true)
    public void hide_Customer_name_Asset_list() throws Exception {
		logger.assignCategory("UOC");

 		login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_hideCol("Customer_Name");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 
    }
   
    @Test(priority = 743, groups = { "Smoke", "New_data" }, description = "Check able to hide customer ID column from Asset List dropdwon", enabled = true)
    public void hide_Customer_id_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_hideCol("Customer_Id");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 

    }
    @Test(priority = 44, groups = { "Smoke", "New_data" }, description = "Check able to hide Asset ID column from Asset List dropdwon", enabled = true)
    public void hide_Asset_id_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_hideCol("Asset_Id");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 

    }
    @Test(priority = 745, groups = { "Smoke", "New_data" }, description = "Check able to hide Asset Name column from Asset List dropdwon", enabled = true)
    public void hide_Asset_name_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_hideCol("Asset_Name");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 

    }

    @Test(priority = 746, groups = { "Smoke", "New_data" }, description = "Check able to hide Device profile ID column from Asset List dropdwon", enabled = true)
    public void hide_Device_profile_ID_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_hideCol("Device_profile_ID");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 

    }

    @Test(priority = 747, groups = { "Smoke", "Critical_Regression" }, description = "Check able to activate customer Name column from Asset List dropdwon", enabled = true)
    public void active_Customer_name_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_activeCol("Customer_Name");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 
 
    }
   
    @Test(priority = 748, groups = { "Smoke", "New_data" }, description = "Check able to activate customer ID column from Asset List dropdwon", enabled = true)
    public void active_Customer_id_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_activeCol("Customer_Id");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 

    }
    @Test(priority = 749, groups = { "Smoke", "New_data" }, description = "Check able to activate Asset ID column from Asset List dropdwon", enabled = true)
    public void active_Asset_id_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_activeCol("Asset_Id");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 

    }
    @Test(priority = 750, groups = { "Smoke", "New_data" }, description = "Check able to activate Asset Name column from Asset List dropdwon", enabled = true)
    public void active_Asset_name_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_activeCol("Asset_Name");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 

    }

    @Test(priority = 751, groups = { "Smoke", "New_data" }, description = "Check able to activate Device profile ID column from Asset List dropdwon", enabled = true)
    public void active_Device_profile_ID_Asset_list() throws Exception {
		logger.assignCategory("UOC");

    	login(prop.getProperty("username"), prop.getProperty("password"));
        UoCAssetParam.Open_listView();
        UoCAssetParam.Asset_activeCol("Device_profile_ID");
        UoCAssetParam.logout();
        UoCAssetParam.handling_popup(); 

    }

}

