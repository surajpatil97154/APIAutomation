package com.qa.api.contact.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAllConatcts extends BaseTest{

	private String tokenId;
	@BeforeMethod
	public void getToken() {
		ContactsCredentials creds = ContactsCredentials.builder()
							.email("naveenanimation20@gmail.com")
							.password("test@123")
							.build();
		Response response = restClinet.post(BASE_URL_LOGIN_CONTACTS, creds, LOGIN_CONTACTS_ENDPOINTS, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		tokenId = response.jsonPath().getString("token");
		System.out.println("conatcts api token id is : "+tokenId);
		ConfigManager.set("bearerToken", tokenId);
		
	}
	
	@Test
	public void getAllContacts() {
		Response responseGet = restClinet.get(BASE_URL_LOGIN_CONTACTS, LOGIN_CONTACTS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.getStatusCode(), 200);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
