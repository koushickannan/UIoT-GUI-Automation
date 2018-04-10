package com.hpe.iot.e2eflows;

import static com.jayway.restassured.RestAssured.with;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.apache.http.HttpStatus;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.automation.pageobjects.AssetMgmt;

import com.hpe.iot.api.base.Base_API;
import com.hpe.iot.api.utilities.TestData;

public class Http_E2EFlow extends Base_API {
	
	static Random randomno = new Random();
	static int r= randomno.nextInt(1000-10)+10;

	final static Logger logger = Logger.getLogger(Http_E2EFlow.class);

	public void API_HttpUplink(String asset,String deviceID,String username, String password, String container) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			AssetMgmt assetmgmt = new AssetMgmt();
			String resourceIdentifier = "ul" + randomNumber();
						
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", username)
					.header("Authorization", assetmgmt.Base64Conversion(username, password))
					.given()
					.body(TestData.getHttpUplinkBody())
					.when()
					.post(prop_api.getProperty("dc_base_uri") +asset+"/"+container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_CREATED, "Uplink Response");*/
			Assert.assertEquals(resp.getStatusCode(),HttpStatus.SC_CREATED);
			

		} catch (Exception e) {

			logger.error("Http Uplink Failed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	
	public void API_HttpDownlink(String Container, String asset) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			
			String resourceIdentifier = "dl" + randomNumber();
		
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", Base_API.origin)
					.header("Authorization", Base_API.auth)
					.given()
					.body(TestData.getDownlinkBody())
					.when()
					.post(prop_api.getProperty("base_uri") + asset+"/"+Container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_CREATED, "Downlink Response");*/
			Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);
			
		} catch (Exception e) {

			logger.error("Http Downlink Failed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void API_HttpUplink_NestedContainer(String asset,String username, String password, String container) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			AssetMgmt assetmgmt = new AssetMgmt();
			String resourceIdentifier = "ul" + randomNumber();
			
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", username)
					.header("Authorization", assetmgmt.Base64Conversion(username, password))
					.given()
					.body(TestData.getHttpUplinkBody())
					.when()
					.post(prop_api.getProperty("dc_base_uri") +asset+"/"+container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_CREATED, "Uplink nested container Response");*/
			Assert.assertEquals(resp.getStatusCode(),HttpStatus.SC_CREATED);
			

		} catch (Exception e) {

			logger.error("Http Uplink nested container Failed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void API_HttpDownlink_NestedContainer(String Container, String asset) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			
			String resourceIdentifier = "dl" + randomNumber();
		
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", Base_API.origin)
					.header("Authorization", Base_API.auth)
					.given()
					.body(TestData.getDownlinkBody())
					.when()
					.post(prop_api.getProperty("base_uri") + asset+"/"+Container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_CREATED, "Downlink nested container Response");*/
			Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);
			

		} catch (Exception e) {

			logger.error("Http Downlink Failed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void API_HttpUplink_Headers(String asset, String container, String username) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {

			String resourceIdentifier = "ul" + randomNumber();
			
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-Origin", username)
					.header("X-M2M-RI", resourceIdentifier)
					.given()
					.body(TestData.getHttpUplinkBody())
					.when()
					.post(prop_api.getProperty("dc_base_uri") +asset+"/"+container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Unauthorized Response");*/
			Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_UNAUTHORIZED);
			

		} catch (Exception e) {

			logger.error("Http Uplink Failed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void API_HttpDownlink_Headers(String Container, String assetNamePropertyKey) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			
			String resourceIdentifier = "dl" + randomNumber();
		
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", Base_API.origin)
					.given()
					.body(TestData.getDownlinkBody())
					.when()
					.post(prop_api.getProperty("base_uri") + assetDetailsProp.getProperty(assetNamePropertyKey)+"/"+Container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Unauthorized Response");*/
			Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_UNAUTHORIZED);
			

		} catch (Exception e) {

			logger.error("Http Downlink -Mandatory header missing");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	public boolean API_HttpDownlink_new(String Container, String assetNamePropertyKey) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			
			String resourceIdentifier = "dl" + randomNumber();
		
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", Base_API.origin)
					.header("Authorization", Base_API.auth)
					.given()
					.body(TestData.getDownlinkBody())
					.when()
					.post(prop_api.getProperty("base_uri") +assetDetailsProp.getProperty(assetNamePropertyKey)+"/"+Container+"?ty=4&rt=3");

			/*Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);*/
			if(resp.getStatusCode() == HttpStatus.SC_CREATED) {
				return true;
			}
			else {
				return false;
			}

		} catch (Exception e) {

			logger.error("Http Downlink Failed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void API_HttpUplink_BadCredentials(String asset,String username, String password, String container) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			AssetMgmt assetmgmt = new AssetMgmt();
			String resourceIdentifier = "ul" + randomNumber();
						
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", username)
					.header("Authorization", assetmgmt.Base64Conversion(username, password))
					.given()
					.body(TestData.getHttpUplinkBody())
					.when()
					.post(prop_api.getProperty("dc_base_uri") +asset+"/"+container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Bad credentials Response");*/
			Assert.assertEquals(resp.getStatusCode(),HttpStatus.SC_UNAUTHORIZED);
			

		} catch (Exception e) {

			logger.error("Http Uplink -- Authorization passed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
		
	
	public void API_HttpUplinkEncryptDecrypt(String asset,String deviceEUI,String devAddr,String username, String password, String container) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			AssetMgmt assetmgmt = new AssetMgmt();
			String resourceIdentifier = "ul" + randomNumber();
						
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", username)
					.header("Authorization", assetmgmt.Base64Conversion(username, password))
					.given()
					.body(TestData.getHttpUplinkBody_Encrypt(deviceEUI, devAddr))
					.when()
					.post(prop_api.getProperty("dc_base_uri") +asset+"/"+container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_CREATED, "Uplink Response");*/
			Assert.assertEquals(resp.getStatusCode(),HttpStatus.SC_CREATED);
			

		} catch (Exception e) {

			logger.error("Http Uplink Failed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	public void API_HttpDownlinkEncryptDecrypt(String Container, String asset,String deviceEUI,String devAddr) throws InvalidFormatException, IOException, ParseException, SQLException {

		try {
			
			String resourceIdentifier = "dl" + randomNumber();
		
			resp = with().log().all()
					.header("Content-Type", "application/vnd.onem2m-res+json;ty=4")
					.header("Accept", "application/vnd.onem2m-res+json")
					.header("X-M2M-RI", resourceIdentifier)
					.header("X-M2M-Origin", Base_API.origin)
					.header("Authorization", Base_API.auth)
					.given()
					.body(TestData.getDownlinkBody_Encrypt(deviceEUI, devAddr))
					.when()
					.post(prop_api.getProperty("base_uri") + asset+"/"+Container+"?ty=4&rt=3");

			/*_assert.equals(resp.getStatusCode(), HttpStatus.SC_CREATED, "Downlink Response");*/
			Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_CREATED);
			
		} catch (Exception e) {

			logger.error("Http Downlink Failed");
			logger.error(e.getMessage().toString());
			throw (e);
		}
	}
	
	
}
