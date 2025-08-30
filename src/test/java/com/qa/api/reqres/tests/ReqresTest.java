package com.qa.api.reqres.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresTest extends BaseTest{
	
	
	@Test
	public void gerUserReqresTest() {
		
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("page", "2");
		
		Response response = restClinet.get(BASE_URL_REQRES, REQRES_ENDPOINTS, queryParam, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		
	}

}
