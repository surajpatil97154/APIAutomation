package com.qa.api.schema.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JSONSchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPISchemaTest extends BaseTest {

	@Test
	public void getUserSchemaTest() {

		Response response = restClinet.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINTS, null, null, AuthType.NO_AUTH,ContentType.ANY);
		JSONSchemaValidator.validateSchema(response, "schema/productschema.json");
	}
}
