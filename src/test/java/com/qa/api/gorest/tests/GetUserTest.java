package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{
	
	@Test
	public void getAllUsersTest() {
		Response response = restClinet.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
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
