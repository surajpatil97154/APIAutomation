package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.Restclient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

@Listeners(ChainTestListener.class)
public class BaseTest {

	protected Restclient restClinet;

	// ********API Base URLS**********//
	protected final static String BASE_URL_GOREST = "https://gorest.co.in";
	protected final static String BASE_URL_LOGIN_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
	protected final static String BASE_URL_REQRES = "https://reqres.in";
	protected final static String BASE_URL_BASIC_AUTH = "https://the-internet.herokuapp.com";
	protected final static String BASE_URL_PRODUCTS = "https://fakestoreapi.com";
	protected final static String BASE_URL_AMADUES = "https://test.api.amadeus.com";

	// ********API EndPoints**********//
	protected final static String GOREST_USERS_ENDPOINTS = "/public/v2/users";
	protected final static String LOGIN_CONTACTS_ENDPOINTS = "/users/login";
	protected final static String CONATACTS_ENDPOINTS = "/contacts";
	protected final static String REQRES_ENDPOINTS = "/api/users";
	protected final static String BASIC_AUTH_ENDPOINTS = "/basic_auth";
	protected final static String PRODUCTS_ENDPOINTS = "/products";
	protected final static String AMADUES_OAUTH2_ENDPOINTS = "/v1/security/oauth2/token";
	protected final static String AMADUES_FLIGHT_ENDPOINTS = "/v1/shopping/flight-destinations";

	@BeforeSuite
	public void setupAllureReport() {
		RestAssured.filters(new AllureRestAssured());
	}

	@BeforeTest
	public void setup() {
		restClinet = new Restclient();
	}

}
