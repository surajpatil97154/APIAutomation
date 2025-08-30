package com.qa.api.utils;

import java.util.List;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathUtil {

	public static <T> T get(Response response, String xmlPath) {
		String responseBody = response.getBody().asString();
		XmlPath path = new XmlPath(responseBody);
		return path.get(xmlPath);

	}

	public static <T> List<T> getList(Response response, String xmlPath) {
		String responseBody = response.getBody().asString();
		XmlPath path = new XmlPath(responseBody);
		return path.getList(xmlPath);

	}

}
