package com.qa.api.gorest.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.XMLPathUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestAPIWithXMLTest extends BaseTest{
	
	@Test
	public <T> void getXMLTest() {
		Response response = restClinet.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS+".xml", null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		
		List<T> list = XMLPathUtil.getList(response, "objects.object.id");
		
		for(T n : list) {
			System.out.println(n);
		}
	}
	
	

}
