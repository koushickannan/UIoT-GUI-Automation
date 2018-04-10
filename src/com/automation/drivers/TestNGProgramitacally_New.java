package com.automation.drivers;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class TestNGProgramitacally_New {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		XmlSuite suite = new XmlSuite();
		suite.setName("Automation Suite");
		suite.addListener("com.hpe.testng.TestNGAnnotationTransformer");
		 
		XmlTest test = new XmlTest(suite);
		test.setName("IOT Tests");
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.automation.drivers.DriverScript_Smoke"));
		classes.add(new XmlClass("com.hpe.iot.api.drivers.Smoke_API"));
		classes.add(new XmlClass("com.automation.drivers.DriverScript_CriticalRegression"));
		classes.add(new XmlClass("com.automation.drivers.DriverScript_CriticalRegression_2"));
		classes.add(new XmlClass("com.automation.drivers.DriverScript_CriticalRegression_1_4"));
		classes.add(new XmlClass("com.automation.drivers.DriverScript_RoleMgmt"));
		classes.add(new XmlClass("com.automation.drivers.DriverScript_RoutingRule"));
		classes.add(new XmlClass("com.automation.drivers.DriverScript_BulkImport"));
		classes.add(new XmlClass("com.hpe.iot.e2eflows.Driver_E2EFlows"));
		classes.add(new XmlClass("com.automation.drivers.DriverScript_UoC"));
		test.setXmlClasses(classes) ;
		
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();

		
	}

}
