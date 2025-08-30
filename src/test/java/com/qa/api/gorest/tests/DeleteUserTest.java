package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {

	@Test
	public void deleteUser() {
		// 1. create a user
		User user = new User(null,"Suraj", StringUtil.getRandomString(), "male", "active");

		Response postResponse = restClinet.post(BASE_URL_GOREST, user, GOREST_USERS_ENDPOINTS, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(postResponse.jsonPath().getString("name"), user.getName());
		Assert.assertNotNull(postResponse.jsonPath().getString("id"));

		// fetch userId
		String userId = postResponse.jsonPath().getString("id");
//		ChainTestListener.log("User id is :"+userId);
		System.out.println("User id is :" + userId);

		// Fetch user using same id
		Response getResponse = restClinet.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(getResponse.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(getResponse.jsonPath().getString("id"), userId);
		Assert.assertNotNull(getResponse.jsonPath().getString("id"));

		// Delete the User
		Response deleteResponse = restClinet.delete(BASE_URL_GOREST ,GOREST_USERS_ENDPOINTS + "/" + userId, null, null,AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(deleteResponse.statusLine().contains("No Content"));
        
		// Fetch user using same id after delete the user 
		getResponse = restClinet.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, null, null,AuthType.BEARER_TOKEN, ContentType.JSON);
		System.out.println("status line is :"+ getResponse.statusLine());
		Assert.assertTrue(getResponse.statusLine().contains("Not Found"));
		Assert.assertEquals(getResponse.statusCode(), 404);
	}

}
