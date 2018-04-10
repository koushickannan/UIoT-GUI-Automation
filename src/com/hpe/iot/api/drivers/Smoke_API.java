package com.hpe.iot.api.drivers;

import static com.jayway.restassured.RestAssured.given;

import static com.jayway.restassured.RestAssured.with;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hpe.base.Base;
import com.hpe.iot.api.base.Base_API;
import com.hpe.iot.api.utilities.JDBCUtils;
import com.hpe.iot.api.utilities.TestData;
import com.hpe.iot.api.utilities.Tunneling;

@Listeners(com.hpe.testng.TestNgListeners.class)
public class Smoke_API extends Base_API {
	final static Logger logger = Logger.getLogger(Smoke_API.class);


	@Test(priority=18, dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whether the application is able to access AE information , ALM Id = 88216")
	public static void API_accessAE() throws URISyntaxException {

		try{
		Base.logger.assignCategory("API");
		resp = given().log().all().params("rt", "3")
				.proxy("web-proxy.sgp.hpecorp.net",8080)
				.header("X-M2M-Origin", Base_API.origin.trim())
				.header("Authorization", Base_API.auth.trim())
				.header("X-M2M-RI", "1234k1jds12")
				.header("Content-Type", "application/vnd.onem2m-res+json;ty=9")
				.header("Accept", "application/vnd.onem2m-res+json")
				.when()
				.get(prop_api.getProperty("base_uri")+Base_API.asset_name);

		Assert.assertEquals(resp.getStatusCode(),HttpStatus.SC_OK);

		Assert.assertTrue(resp.getBody().asString().contains(Base_API.nodelink));
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}

	}

	@Test(priority=19,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whether a device group can be created , ALM Id = 88224")
	public static void API_Create_Group() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		Base.logger.assignCategory("API");
		resp = with().log().all().proxy("web-proxy.sgp.hpecorp.net",8080).header("X-M2M-Origin", Base_API.origin.trim()).header("Authorization", Base_API.auth.trim())
				.header("X-M2M-RI", "1234klkjds12").header("Content-Type", "application/vnd.onem2m-res+json;ty=9")
				.given().body(TestData.getgroupcreateBody()).when().post(prop_api.getProperty("base_uri"));

		Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);

		String groupName = TestData.getGroupName();

		if (JDBCUtils.connection != null) {
			result_set = JDBCUtils.Query("Select * from onem2mgroup where resource_name=\t" + "'" + groupName + "'");

			if (result_set != null) {

				result_set.next();

				result_set.getString("resource_name").equalsIgnoreCase(groupName);
			}
		}
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}

	}

	@Test(priority=20,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate Whether Application can able to create Container for device , ALM Id = 88215")
	public void API_Create_Container() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		Base.logger.assignCategory("API");
		
		resp = with().log().all().proxy("web-proxy.sgp.hpecorp.net",8080).header("X-M2M-Origin", Base_API.origin).header("Authorization", Base_API.auth)
				.header("X-M2M-RI", "1234klkjds12").header("Content-Type", "application/vnd.onem2m-res+json;ty=3")
				.given().body(TestData.getcontainercreateBody()).when().post(prop_api.getProperty("base_uri")+Base_API.asset_name);

		Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);

		String containerName = TestData.getContainerName();

		if (JDBCUtils.connection != null) {
			result_set = JDBCUtils.Query("Select * from container where resource_name=\t" + "'" + containerName + "'");

			if (result_set != null) {

				result_set.next();

				result_set.getString("resource_name").equalsIgnoreCase(containerName);
			}
		}
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}

	}

	@Test(priority=21,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whether the application is able to create subscription for a group , ALM Id = 88222")
	public void API_CreateSubscriptionGroup() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		
		Base.logger.assignCategory("API");
		
		resp = with().log().all()
				.proxy("web-proxy.sgp.hpecorp.net",8080)
				.header("X-M2M-Origin", Base_API.origin)
				.header("Authorization", Base_API.auth)
				.header("X-M2M-RI", "1234klkjds12")
				.header("Content-Type", "application/vnd.onem2m-res+json;ty=23")
				.given()
				.body(TestData.getsubscreateBody_GRP())
				.when()
				.post(prop_api.getProperty("base_uri") + TestData.getGroupName());

		Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);

		String subsName = TestData.getSubscriptionName_GRP();

		if (JDBCUtils.connection != null) {
			result_set = JDBCUtils.Query("Select * from subscription where resource_name=\t" + "'" + subsName + "'");

			if (result_set != null) {

				result_set.next();

				result_set.getString("resource_name").equalsIgnoreCase(subsName);
			}
		}
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}
	}

	@Test(priority=22,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whether the application is able to create subscription for AE , ALM Id = 88220")
	public void API_CreateSubscriptionAE() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		Base.logger.assignCategory("API");
		
		resp = with().log().all().proxy("web-proxy.sgp.hpecorp.net",8080).header("X-M2M-Origin", Base_API.origin).header("Authorization", Base_API.auth)
				.header("X-M2M-RI", "1234klkjds12").header("Content-Type", "application/vnd.onem2m-res+json;ty=23")
				.given().body(TestData.getsubscreateBody_AE()).when().post(prop_api.getProperty("base_uri")+Base_API.asset_name);

		Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);

		String subsNameAE = TestData.getSubscriptionName_AE();

		if (JDBCUtils.connection != null) {
			result_set = JDBCUtils.Query("Select * from subscription where resource_name=\t" + "'" + subsNameAE + "'");

			if (result_set != null) {

				result_set.next();

				result_set.getString("resource_name").equalsIgnoreCase(subsNameAE);
			}
		}
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}
	}

	@Test(priority=23,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whether the application is able to create subscription for AE's container , ALM Id = 88221")
	public void API_CreateSubscriptionCNT() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		Base.logger.assignCategory("API");
		
		resp = with().log().all().proxy("web-proxy.sgp.hpecorp.net",8080).header("X-M2M-Origin", Base_API.origin).header("Authorization", Base_API.auth)
				.header("X-M2M-RI", "1234klkjds12").header("Content-Type", "application/vnd.onem2m-res+json;ty=23")
				.given().body(TestData.getsubscreateBody_CNT()).when()
				.post(prop_api.getProperty("base_uri")+Base_API.asset_name+"/" + TestData.getContainerName());

		Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);

		String containerNameCNT = TestData.getSubscriptionName_CNT();

		if (JDBCUtils.connection != null) {
			result_set = JDBCUtils
					.Query("Select * from subscription where resource_name=\t" + "'" + containerNameCNT + "'");

			if (result_set != null) {

				result_set.next();

				result_set.getString("resource_name").equalsIgnoreCase(containerNameCNT);
			}
		}
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}
	}

	@Test(priority=24,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whether the application is able to access the AE's container data , ALM Id = 88217")
	public void API_GetContainerData() throws InvalidFormatException, IOException, ParseException {

		try{
		Base.logger.assignCategory("API");
		
		resp = given().log().all().params("rt", "3")
				.proxy("web-proxy.sgp.hpecorp.net",8080)
				.header("X-M2M-Origin", Base_API.origin).header("Authorization", Base_API.auth).header("X-M2M-RI", "1234klkjds12")
				.header("Content-Type", "application/vnd.onem2m-res+json;ty=3")
				.header("Accept", "application/vnd.onem2m-res+json").when()
				.get(prop_api.getProperty("base_uri")+Base_API.asset_name+"/" + TestData.getContainerName());

		Assert.assertEquals(resp.getStatusCode(),HttpStatus.SC_OK);

		Assert.assertTrue(resp.getBody().asString().contains(TestData.getContainerName()));
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}

	}

	@Test(priority=25,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whether the application is able to update the AE data , ALM Id = 88218")
	public void API_UpdateAE() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		Base.logger.assignCategory("API");
		
		resp = with().log().all().proxy("web-proxy.sgp.hpecorp.net",8080).header("X-M2M-Origin", Base_API.origin).header("Authorization", Base_API.auth)
				.header("X-M2M-RI", "1234klkjds12").header("Content-Type", "application/vnd.onem2m-res+json;ty=9")
				.given().body(TestData.getupdateAEBody()).when().put(prop_api.getProperty("base_uri")+Base_API.asset_name);

		Assert.assertEquals( resp.getStatusCode(),HttpStatus.SC_OK);

		String expectedLAB = "[\"" + TestData.getAEUpdatelabel() + "\"]";

		if (JDBCUtils.connection != null) {
			result_set = JDBCUtils.Query("Select * from application_entity where resource_name=\t" + "'july3'");

			if (result_set != null) {

				result_set.next();

				result_set.getString("labels").equalsIgnoreCase(expectedLAB);
			}
		}
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}

	}

	@Test(priority=26,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whetehr the application is able to update the AE's container data , ALM Id = 88217")
	public void API_UpdateCNT() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		Base.logger.assignCategory("API");
		
		resp = with().log().all().proxy("web-proxy.sgp.hpecorp.net",8080).header("X-M2M-Origin", Base_API.origin).header("Authorization", Base_API.auth)
				.header("X-M2M-RI", "1234klkjds12").header("Content-Type", "application/vnd.onem2m-res+json;ty=9")
				.given().body(TestData.getupdateAEBody()).when().put(prop_api.getProperty("base_uri")+Base_API.asset_name);

		Assert.assertEquals(resp.getStatusCode(),HttpStatus.SC_OK);

		String expectedLAB = "[\"" + TestData.getAEUpdatelabel() + "\"]";

		if (JDBCUtils.connection != null) {
			result_set = JDBCUtils.Query("Select * from application_entity where resource_name=\t" + "'july3'");

			if (result_set != null) {

				if(result_set.next()){
				result_set.getString("labels").equalsIgnoreCase(expectedLAB);
				}
			}
		}
		}catch(Exception e){
			logger.error(e.getMessage().toString());
			throw(e);
		}

	}

	@Test(priority=27,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate whether the application is able subscribe to its metatdata , ALM Id = 88223")
	public void API_CreateSubscriptionMetaData() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		Base.logger.assignCategory("API");
		
		resp = with().log().all().proxy("web-proxy.sgp.hpecorp.net",8080).header("X-M2M-Origin", Base_API.origin).header("Authorization", Base_API.auth)
				.header("X-M2M-RI", "1234klkjds12").header("Content-Type", "application/vnd.onem2m-res+json;ty=23")
				.given().body(TestData.getsubscreateBody_Meta()).when()
				.post(prop_api.getProperty("base_uri")+Base_API.asset_name);

		Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);

		String subsMetaData = TestData.getSubscriptionName_meta();

		if (JDBCUtils.connection != null) {
			result_set = JDBCUtils
					.Query("Select * from subscription where resource_name=\t" + "'" + subsMetaData + "'");

			if (result_set != null) {

				result_set.next();

				result_set.getString("resource_name").equalsIgnoreCase(subsMetaData);
			}
		}
	
	}catch(Exception e){
		logger.error(e.getMessage().toString());
		throw(e);
	}
	}
	
	@Test(priority=28,dependsOnMethods = { "com.automation.drivers.DriverScript_Smoke.TestCase_80533" }, groups = { "Smoke","Critical_Regression" }, description="Validate Downlink , ALM Id = 88214")
	public void API_Downlink() throws InvalidFormatException, IOException, ParseException, SQLException {

		try{
		
		Base.logger.assignCategory("API");
		
		resp = with().log().all()
				.proxy("web-proxy.sgp.hpecorp.net",8080)
				.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
				.header("Accept", "application/vnd.onem2m-res+json")
				.header("X-M2M-RI", "1234klkjds12")
				.header("X-M2M-NM", "nm-ci")
				.header("X-M2M-Origin",Base_API.origin)
				.header("Authorization",Base_API.auth)
				.given()
				.body(TestData.getDownlinkBody())
				.when()
				.post(prop_api.getProperty("base_uri") + Base_API.asset_name+"/commands?rt=3");

		Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);
		
		with().log().all()
		.proxy("web-proxy.sgp.hpecorp.net",8080)
		.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
		.header("Accept", "application/vnd.onem2m-res+json")
		.header("X-M2M-RI", "1234klkjds12")
		.header("X-M2M-NM", "nm-ci")
		.header("X-M2M-Origin",Base_API.origin)
		.header("Authorization",Base_API.auth)
		.given()
		.body(TestData.getDownlinkBody())
		.when()
		.post(prop_api.getProperty("base_uri") + Base_API.asset_name+"/commands?rt=3");
		
		with().log().all()
		.proxy("web-proxy.sgp.hpecorp.net",8080)
		.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
		.header("Accept", "application/vnd.onem2m-res+json")
		.header("X-M2M-RI", "1234klkjds12")
		.header("X-M2M-NM", "nm-ci")
		.header("X-M2M-Origin",Base_API.origin)
		.header("Authorization",Base_API.auth)
		.given()
		.body(TestData.getDownlinkBody())
		.when()
		.post(prop_api.getProperty("base_uri") + Base_API.asset_name+"/commands?rt=3");
		
		with().log().all()
		.proxy("web-proxy.sgp.hpecorp.net",8080)
		.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
		.header("Accept", "application/vnd.onem2m-res+json")
		.header("X-M2M-RI", "1234klkjds12")
		.header("X-M2M-NM", "nm-ci")
		.header("X-M2M-Origin",Base_API.origin)
		.header("Authorization",Base_API.auth)
		.given()
		.body(TestData.getDownlinkBody())
		.when()
		.post(prop_api.getProperty("base_uri") + Base_API.asset_name+"/commands?rt=3");
		
		with().log().all()
		.proxy("web-proxy.sgp.hpecorp.net",8080)
		.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
		.header("Accept", "application/vnd.onem2m-res+json")
		.header("X-M2M-RI", "1234klkjds12")
		.header("X-M2M-NM", "nm-ci")
		.header("X-M2M-Origin",Base_API.origin)
		.header("Authorization",Base_API.auth)
		.given()
		.body(TestData.getDownlinkBody())
		.when()
		.post(prop_api.getProperty("base_uri") + Base_API.asset_name+"/commands?rt=3");

	
		}catch(Exception e){
			
			logger.error(e.getMessage().toString());
			throw(e);
		}
	}
}
