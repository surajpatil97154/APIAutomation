package com.qa.api.contact.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Contact;
import com.qa.api.pojo.ContactsCredentials;
import com.qa.api.utils.StringUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateContact extends BaseTest {
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
	public void createContactUsingJSONFile() {
		File jsonFile = new File("./src/test/resources/jsons/contact.json");
		
		Response postResponse = restClinet.post(BASE_URL_LOGIN_CONTACTS, jsonFile, CONATACTS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		System.out.println("post response id : "+ postResponse.jsonPath().get("_id"));
		Assert.assertNotNull(postResponse.jsonPath().getString("_id"));
	}
	
	@Test
	public void createContactUsingPOJO() {
		Contact contactJSON = Contact.builder()
				.firstName("Suraj")
				.lastName("Patil")
				.birthdate("1999-01-01")
				.email(StringUtil.getRandomString())
				.phone("9876896767").build();
		
		Response postResponse = restClinet.post(BASE_URL_LOGIN_CONTACTS, contactJSON, CONATACTS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		System.out.println("post response id : "+ postResponse.jsonPath().get("_id"));
		Assert.assertNotNull(postResponse.jsonPath().getString("_id"));
		Assert.assertEquals(postResponse.jsonPath().getString("firstName"), contactJSON.getFirstName());
		Assert.assertEquals(postResponse.jsonPath().getString("lastName"), contactJSON.getLastName());
	}
}
