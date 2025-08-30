package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathValidatorUtil {

	public static <T> T read(Response response, String jsonPath) {
		DocumentContext ctx = JsonPath.parse(response.getBody().asString());
		return ctx.read(jsonPath);
	}

	public static <T> List<T> readList(Response response, String jsonPath) {
		DocumentContext ctx = JsonPath.parse(response.getBody().asString());
		return ctx.read(jsonPath);
	}

	public static <T> List<Map<T, T>> readListOfMaps(Response response, String jsonPath) {
		DocumentContext ctx = JsonPath.parse(response.getBody().asString());
		return ctx.read(jsonPath);
	}

}
