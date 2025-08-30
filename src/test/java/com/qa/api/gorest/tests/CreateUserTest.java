package com.qa.api.gorest.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.client.Restclient;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {
	
	/*
	 * handling data what will prefer ? storing data in dataprovider method or in excel ..?
	 *  * due to possibality  of data curreption in excel
	 *  * for Excel required to intigrate third party api (POI) to the framework instead dataprovider is in build feature of testNG
	 *  * reading data from excel required excel license version not piloted version
	 */

//	@DataProvider
//	public Object[][] getUserData() {
//
//		return new Object[][] { { "Suraj", "male", "active" }, { "Bhagyashri", "female", "inactive" },{ "Ram", "male", "active" } };
//
//	}
	
	@DataProvider
	public Object[][] getUserData(){
		return ExcelUtil.readExcelData(AppConstants.CREATE_USER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getUserData")
	public void createUserTest(String name, String gender, String status) {
//		User user = new User(null,"Suraj",StringUtil.getRandomString(), "male", "Active");
		User user = new User(null, name, StringUtil.getRandomString(), gender, status);
		Response response = restClinet.post(BASE_URL_GOREST, user, GOREST_USERS_ENDPOINTS, null, null,AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertNotNull(response.jsonPath().getString("id"));
	}

	@Test
	public void createUserTestWithJsonString() {
		String jsonString = "{\r\n" + "    \"name\": \"Suraj\",\r\n" + "    \"email\": \""
				+ StringUtil.getRandomString() + "\",\r\n" + "    \"gender\": \"male\",\r\n"
				+ "    \"status\": \"Active\"\r\n" + "}";

		Response response = restClinet.post(BASE_URL_GOREST, jsonString, GOREST_USERS_ENDPOINTS, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertNotNull(response.jsonPath().getString("id"));

	}

	@Test
	public void createUserTestWithJsonFile() {

		File jsonFile = new File("./src/test/resources/jsons/user.json");

		Response response = restClinet.post(BASE_URL_GOREST, jsonFile, GOREST_USERS_ENDPOINTS, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertNotNull(response.jsonPath().getString("id"));

	}

}
