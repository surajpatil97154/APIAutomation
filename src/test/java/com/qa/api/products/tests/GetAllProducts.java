package com.qa.api.products.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAllProducts extends BaseTest{
	
	@Test
	public void getAllProducts() {
		Response response = restClinet.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINTS, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertTrue(response.statusLine().contains("OK"));
		Product[] deseralizedJSON = JsonUtil.deseralize(response, Product[].class);
		
		for(Product product: deseralizedJSON) {
			System.out.println(product.getId());
			System.out.println(product.getTitle());
			System.out.println(product.getPrice());
			System.out.println(product.getCategory());
			System.out.println(product.getDescription());
			System.out.println(product.getRating().getRate());
			System.out.println(product.getRating().getCount());
		}
	}
	



}
