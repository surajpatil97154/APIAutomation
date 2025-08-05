package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.client.Restclient;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {
	
	@Test
	public void createUserTest() {
		User user = new User("Suraj", StringUtil.getRandomString(), "male","Active");
		Response response = restClinet.post(BASE_URL_GOREST, user, GOREST_USERS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertNotNull(response.jsonPath().getString("id"));
		
	}

}
