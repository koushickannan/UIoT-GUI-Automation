package com.automation.pageobjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hpe.base.Attributes;
import com.hpe.webdriver.ElementBase;

public class DeviceProfile extends ElementBase {
	
	final static Logger Log = Logger.getLogger(ElementBase.class.getName()); 
	
	//Customer Elements
	By customer_mgmt_dash  = By.xpath(".//*[text()='Customer Mgmt.']");
	
	
	
	By create_customer     = By.id("cust_1");
	By customer_radio      = By.xpath(".//input[@value='CUSTOMER'][@type='radio']");
	By search_customer     = By.id("cust_2");
	By select_tab          = By.xpath(".//li[contains(@ng-click,'Customer')]");
	By search_quick_link   = By.xpath(".//i[@class='icon icon-search']");
	By search_button       = By.xpath(".//input[@value='Search']");
	
	By customer_name       = By.name("sGroupName");
	By parent_customer     = By.id("groupId");
	By retention_time      = By.name("sretentionTime");
	By customer_desc       = By.name("sdescription");
	By roleId              = By.id("roleId");
	By create              = By.xpath(".//button[text()='Create']");

	By Device_Manufact_Profile            = By.xpath("//span[contains(text(),'Device Manufacturer Profile')]");
	By Search_Device_Manufacturer_Profile = By.id("deviceP_2");
	By Device_profile                     = By.xpath("//tr[1]/td[1]/a");
	By View_XML                           = By.xpath("//input[@value='View Raw XML']");
	By Xml_Data                           = By.id("dp-xml-viewer");
	By Title_Xml_Data                     = By.xpath(".//*[contains(text(),'XML Data')]");
	By update_xml_data                    = By.xpath(".//*[@id='myModal']/div[2]/div/div[3]/button[2]");
	By success_msg                        = By.xpath("//div[1]/strong");
	By Add_device_Manufacturer_profile    = By.xpath(".//*[@id='deviceP_1']");
	By Customer_Name                      = By.xpath(".//*[@id='Node_"+SmokeTest.CustID+ "']/div");
	By Tree_Check                         = By.xpath(".//*[@id='treeCheck_" + SmokeTest.CustID + "']");
	By Tree_hirearchy_SubGroup            = By.xpath("//li[span[contains(text(),'IOT')]]/ul/li[1]/div");
	By Clicking_SubGroup                  = By.xpath("//li[span[contains(text(),'IOT')]]/ul/li[1]/div/following-sibling::ul/li[1]/div");
	By Selecting_SubGroup                 = By.xpath("//li[span[contains(text(),'IOT')]]/ul/li[1]/div/following-sibling::ul/li[1]/input");
	By Browse_Device_XML_Profile          = By.xpath(".//*[contains(text(),'Device Profile (XML)')]/following-sibling::div/div/span/li/input");
	By Select_Container_Profile           = By.xpath("//div[label[contains(text(),'Container Profile')]]/div/select");
	By Edit_Remap                         = By.xpath("//thead[tr[th[text()='Remap Entity/Device Group']]]/following-sibling::tbody/tr[1]/td[8]/a[1]/i");
	By Update_Butoon                      = By.xpath("//input[@id='addruleBtn']");
	By Success_Msg                        = By.xpath(".//*[Contains(text(),'Success!')]");

	public void Open_Raw_XML() throws InterruptedException {
		
		
		if (!findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"DP Tile").getAttribute(Attributes.GENERAL,"class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"DP Tile").click("Click on DP Tile");
			Thread.sleep(2000);
		}

		//findElement(Device_Manufact_Profile, "Device_Manufacturer_Profile").click("Clicking on Device Manufacture Profile");

		findElement(Search_Device_Manufacturer_Profile, "Search_device_Manufacturer_Profile")
				.click("Clicking on Search_Device_Manufacturer_Profile");

		findElement(Device_profile, "selecting Device profile").click("click on selected profile");

		findElement(View_XML, "View_Raw_XML").click("open_Raw_Xml_file");

		Thread.sleep(3000);

	}

	public void View_Copy_XML()
			throws IOException, ParserConfigurationException, SAXException, TransformerException, InterruptedException {
		String XML = driver.findElement(By.id("dp-xml-viewer")).getText();
		BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\testdata\\edit_xml.xml"));
		
		out.write(XML);
		out.close();

		ModifyDeviceProfileXML(System.getProperty("user.dir") + "\\testdata\\edit_xml.xml");

		
		
	}

	public void Edit_XML_File()
			throws TransformerException, SAXException, IOException, ParserConfigurationException, InterruptedException {
		try{

		String modify = DeviceProfile.xml2string(System.getProperty("user.dir") + "\\testdata\\edit_xml.xml");
		driver.findElement(By.id("dp-xml-viewer")).sendKeys(Keys.CONTROL, "a");
		driver.findElement(By.id("dp-xml-viewer")).sendKeys("");
		driver.findElement(By.id("dp-xml-viewer")).sendKeys(modify);
		}catch(Exception e){
			
			throw(e);
		}

	}

	public static String xml2string(String filename)
			throws TransformerException, SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filename);
		StringWriter stringWriter = new StringWriter();
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));
		String strFileContent = stringWriter.toString(); // This is string data
															// of xml file

		return strFileContent;

	}

	public static void ModifyDeviceProfileXML(String filepath)
			throws ParserConfigurationException, SAXException, IOException, TransformerException, InterruptedException {

		String file = filepath;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
		String version;

		// get the root element
		Node capabilities = doc.getFirstChild();

		// Get the Capability element
		Node Capability = doc.getElementsByTagName("Capability").item(0);

		// loop the Capability child node
		NodeList list = Capability.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {

			Node node = list.item(i);

			// get the Category element, and update the value
			if ("Category".equals(node.getNodeName())) {
				node.setTextContent("Identifier123");
				break;
			}

		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(file));
		transformer.transform(source, result);
		System.out.println("Done");
	}

	public void Update_XML() throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='myModal']/div[2]/div/div[3]/button[2]")).click();
		Thread.sleep(3000);
		alertAccept("Alert Update Profile");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[1]/strong")));
		
		String succesmsg = driver.findElement(By.xpath("//div[1]/strong")).getText();
		System.out.println(succesmsg);
		_assert.contains(succesmsg, "Success", "Verifying the Success Message");
		
		
		Thread.sleep(3000);
	}
	
	
	public void Update_Capablities() throws InterruptedException
	{
		try{
		if (!findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"DP Tile").getAttribute(Attributes.GENERAL,"class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"DP Tile").click("Click on DP Tile");
			Thread.sleep(2000);
		}
		
		findElement(Search_Device_Manufacturer_Profile, "Search_device_Manufacturer_Profile").click("Clicking on Search_Device_Manufacturer_Profile");

      findElement(Device_profile, "selecting Device profile").click("click on selected profile");
      
      //Goto the Capablities
      
      String CAPABILITIES_UPDATE = findElement(By.xpath("//td[contains(text(),'Identifier123')]"),"Text of Edit").getAttribute(Attributes.TEXT);
      //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[1]/strong")));
		System.out.println(CAPABILITIES_UPDATE);
		_assert.contains(CAPABILITIES_UPDATE, "Identifier123", "Verifying the Capability Update");
		
		Thread.sleep(3000);
	}catch(Exception e){
		Log.error("Unable to find the msg--Update is failed");
		Log.error(e.getMessage().toString());
		throw(e);
	}
	}
	

	public void Verify_Device_Customer_Tree() throws Exception {
		try{
		if (!findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"DP Tile").getAttribute(Attributes.GENERAL,"class")
				.contains("selected")) {

			Thread.sleep(2500);
			findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"DP Tile").click("Click on DP Tile");
			Thread.sleep(2000);

		}
		findElement(Add_device_Manufacturer_profile, "click on add devce profile").click("click on add device");
		findElement(By.xpath(".//*[@id='treeCheck_"+SmokeTest.CustID+"']"),"Select the Customer").click("check the tree");
		
		driver.findElement(By.xpath(".//*[@type='file']")).sendKeys(System.getProperty("user.dir") + "\\testdata\\QoS.xml");

		
		//RobotCopyPasteEnter(System.getProperty("user.dir") + "\\testdata\\deviceprofile.xml");

		// Selecting Container profile
		findElement(By.xpath("//div[label[contains(text(),'Container Profile')]]/div/select"),"goto device profile  browser").click("click on Browse");
		findElement(By.xpath("//div[label[contains(text(),'Container Profile')]]/div/select/option[2]"),"goto container profile browser").click("");

		// click on submit button
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

		Thread.sleep(3000);
		}catch(Exception e){
			Log.error("Unable to find the msg--Update is failed");
			Log.error(e.getMessage().toString());
			throw(e);
		}
	}

	public void Assign_Device_Profile_To_Subcustomer() throws Exception {
		if (!driver.findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/..")).getAttribute("class")
				.contains("selected")) {

			Thread.sleep(2500);
			driver.findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/..")).click();
			Thread.sleep(2000);
		}
		
		findElement(By.xpath(".//*[@id='deviceP_1']")," element_description").click("Click on Add Device Profile");
		
		// Clicking on root node group
		   findElement(By.xpath(".//*[@id='treeCheck_" + SmokeTest.CustID + "']"),"Click on the root button").click("Click parent root");
					
	        // Click on Browsing the file
				driver.findElement(By.xpath(".//*[@type='file']")).sendKeys(System.getProperty("user.dir") + "\\testdata\\deviceprofile_Manish.xml");

		
		// Selecting Container profile
		findElement(By.xpath("//div[label[contains(text(),'Container Profile')]]/div/select"),"goto container profile").click("click to select container profile");
		findElement(By.xpath("//div[label[contains(text(),'Container Profile')]]/div/select/option[2]"),"Select Container Profile").click("Select container profile");

		// click on submit button
		findElement(By.xpath("//button[contains(text(),'Submit')]"),"Click on submit button").click("Submit button");

		
		// Verify Success message

		/*
		 * findElement(Success_Msg, "Successfully updated"
		 * ).click("Succeefully_updated"); String
		 * Success=driver.findElement(By.xpath(
		 * ".//*[Contains(text(),'Success!')]")).getText();
		 * System.out.println(Success); _assert.contains(Success, "Success!",
		 * "Success fully updated");
		 */
		Thread.sleep(3000);
		//
	}


	public void Deselect_SubEntity_InRemape() throws Exception {

		try{
			if (!findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"DP Tile").getAttribute(Attributes.GENERAL,"class")
					.contains("selected")) {

				Thread.sleep(2500);
				findElement(By.xpath("(.//*[text()='Device Manufacturer Profile'])/.."),"DP Tile").click("Click on DP Tile");
				Thread.sleep(2000);
		}
		
			findElement(Search_Device_Manufacturer_Profile, "Search_device_Manufacturer_Profile")
			.click("Clicking on Search_Device_Manufacturer_Profile");

		

		// Click on Edit & Remap
		findElement(Edit_Remap, "Select Edit & Remap").click("Click on Pencil Button");
		
		// Clicking on root node group
		   findElement(By.xpath(".//*[@id='treeCheck_" + SmokeTest.CustID + "']"),"Click on the root button").click("Click parent root");
		   findElement(By.xpath(".//*[@id='treeCheck_" + SmokeTest.CustID + "']"),"Click on the root button").click("Click parent root");
		
		

		

		// click on Update Button
		findElement(Update_Butoon, "GotoUpdate_Butto").click("Successfully_clicked on update Button");

		
		
		}catch(Exception e){
			
			throw(e);
		}

	}
	
	public void Create_Sub_Customer() throws InterruptedException{
		
		try{
		String Sub_Customer = "Customer_Automation_"+randomNumber();
		
		if(!driver.findElement(By.xpath("(.//span[text()='Customer Mgmt.'])/..")).getAttribute("class").contains("selected")){
		findElement(customer_mgmt_dash, "Customer Mgmt in Dashboard").click("Click on Customer Mgmt in Dashboard");
		}
		findElement(create_customer, "Create Customer").click("Click on Create Customer");
		
		
		findElement(customer_radio, "Customer Radio Button").click("Select Customer Radio Button");
		
		findElement(customer_name, "Customer Name").sendKeys(Sub_Customer, "Enter Customer Name");
		
		findElement(By.xpath(".//*[contains(@ng-model,'parentId')]/a"), "Parent Customer").click("Click on Parent Customer");
		
		findElements(By.xpath("((.//*[@class='custom-select-search'])/../ul)[1]/li"), "Find Customer COunt");
		
		for(int i=1 ; i<=_WebElements.size();i++){
			
			String actual = _WebElements.get(i).getText();
			
			if(actual.equals("IOT."+CustomerManagement.Cust_Name.toUpperCase())){
				
				_WebElements.get(i).click();
				
				break;
			}
			
		}
		//findElement(parent_customer, "Parent Tenant").selectByIndex(1, "Select Parent Tenant");
		
		findElement(retention_time, "Retension Time").sendKeys(prop.getProperty("retention_period"),"Enter Retension Period");
		findElement(customer_desc, "Customer Description").sendKeys("Test Desc", "Enter Customer Description");
		findElement(By.xpath(".//*[contains(text(),'Business Data')]/input"),"Business Data").click("Select Business Data");
		findElement(roleId, "Role").selectByValue("100", "Select Role");
		
		Thread.sleep(3000);
		findElement(create, "Create Button").submit("Submit on Create Button");
		Log.info("Customer Create : " + Sub_Customer);
		}catch(Exception e){
			
			throw(e);
		}
		
		
		
	}

}
