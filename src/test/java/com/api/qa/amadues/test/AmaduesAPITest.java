package com.api.qa.amadues.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmaduesAPITest extends BaseTest{
	String accessToken;
	@BeforeMethod
	public void getAccessToken() {
		Response postResponse = restClinet.post(BASE_URL_AMADUES, AMADUES_OAUTH2_ENDPOINTS, ConfigManager.get("clientId"), ConfigManager.get("clientSecret"), ConfigManager.get("grand_type"), ContentType.URLENC);
		accessToken= postResponse.jsonPath().getString("access_token");
		System.out.println("Access token -> "+accessToken);
		ConfigManager.set("bearerToken", accessToken);
	}
	
	@Test
	public void getFlieghtDetails() {
		Map<String, String> queryParam = Map.of("origin","PAR", "maxPrice", "200");
		Response response = restClinet.get(BASE_URL_AMADUES,AMADUES_FLIGHT_ENDPOINTS, queryParam, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	

}
