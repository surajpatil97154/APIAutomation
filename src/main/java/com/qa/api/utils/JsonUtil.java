package com.qa.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class JsonUtil {

	private static ObjectMapper objMapper = new ObjectMapper();

	public static <T> T deseralize(Response response, Class<T> targetClass) {
		try {
			return objMapper.readValue(response.getBody().asString(), targetClass);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			throw new RuntimeException("deseralization is failed.. for " + targetClass.getName());
		}

	}

}
