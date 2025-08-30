package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
@Epic("EPIC No- 50 : Go Rest API Get Users feature")
@Story("User story 23212: go rest api ")
public class GetUserTest extends BaseTest{
	
	@Description("Go rest API - Get all users ")
	@Owner("Suraj Patil")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getAllUsersTest() {
		Response response = restClinet.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	@Description("Go rest API - Get all users with Query Parameter")
	@Owner("Suraj Patil")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void getAllUsersWithQueryParamTest() {
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("name", "Divya Sharma");
		queryParam.put("status", "active");
		
		Response response = restClinet.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, queryParam, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	@Test(enabled = false)
	public void getSingleUserTest() {
		String userId ="8045476";
		Response response = restClinet.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
		Assert.assertNotNull(response.jsonPath().getString("id"));
	}
	

}
