package com.qa.api.products.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithJsonPath extends BaseTest{
	
	@Test
	public <T> void getProductUsingJsonPath() {
		
		Response response = restClinet.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINTS, null, null, AuthType.NO_AUTH, ContentType.JSON);
		List<T> priceList = JsonPathValidatorUtil.read(response, "$[?(@.price>100)].price");
		System.out.println(priceList);

	}

}
