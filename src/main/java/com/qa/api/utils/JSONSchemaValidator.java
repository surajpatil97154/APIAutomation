package com.qa.api.utils;

import com.qa.api.exceptions.ApiExceptions;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class JSONSchemaValidator {

	public static boolean validateSchema(Response response, String jsonFilePath) {

		try {
			response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonFilePath));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ApiExceptions("Json schema validation failed.. with error : "+e.getMessage()+" for "+jsonFilePath);
		}
	}

}
