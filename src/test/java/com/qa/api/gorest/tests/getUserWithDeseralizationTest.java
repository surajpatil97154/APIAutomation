package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtil;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getUserWithDeseralizationTest extends BaseTest{
	private String userId;
	
	@Test
	public void createUser() {
		
		User user = new User(null,"John",StringUtil.getRandomString(),"male","active");
		
		Response postResponse = restClinet.post(BASE_URL_GOREST, user, GOREST_USERS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		userId = postResponse.jsonPath().getString("id");
		System.out.println("User id is ==> "+userId);
		

		Response responseGet = restClinet.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		User deseralizedUser = JsonUtil.deseralize(responseGet, User.class);
		Assert.assertEquals(responseGet.jsonPath().getString("name"), deseralizedUser.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("email"), deseralizedUser.getEmail());
		Assert.assertEquals(responseGet.jsonPath().getString("gender"), deseralizedUser.getGender());
		Assert.assertEquals(responseGet.jsonPath().getString("status"), deseralizedUser.getStatus());
		
	}
	
	
	
	
	

}
