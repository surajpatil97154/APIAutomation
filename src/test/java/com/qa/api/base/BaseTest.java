package com.qa.api.base;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.Restclient;


@Listeners(ChainTestListener.class)
public class BaseTest {
	
	protected Restclient restClinet;
	
	//********API Base URLS**********//
	protected final static String BASE_URL_GOREST = "https://gorest.co.in";
	
	
	//********API EndPoints**********//
	protected final static String GOREST_USERS_ENDPOINTS = "/public/v2/users";
	
	@BeforeTest
	public void setup() {
		restClinet = new Restclient();
	}
	

}
