package com.qa.api.client;

import java.util.Map;

import static org.hamcrest.Matchers.*;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.ApiExceptions;
import com.qa.api.manager.ConfigManager;
import static io.restassured.RestAssured.expect;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Restclient {
	
	//define response spec
	private ResponseSpecification responseCode200 = expect().statusCode(200);
	private ResponseSpecification responseCode201 = expect().statusCode(201);
	private ResponseSpecification responseCode401 = expect().statusCode(401);
	private ResponseSpecification responseCode200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification responseCode200or404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	
	private RequestSpecification setupRequest(String basURL,AuthType authType, ContentType contentType ) {
		
		RequestSpecification request = RestAssured.given().log().all()
					.baseUri(basURL)
					.contentType(contentType)
					.accept(contentType);
		
		switch (authType) {
			case BEARER_TOKEN:
				request.header("Authorization", "Bearer "+ConfigManager.get("bearerToken"));
				break;
			case OAUTH2:
				request.header("Authorization", "Bearer "+"oauth2 token");
			case BASIC_AUTH:
				request.header("Authorization", "Basic "+"basic auth token");	
				break;	
			case API_KEY:
				request.header("x-api-key", "api key");	
				break;
			case NO_AUTH:
				System.out.println("Auth is not required...");
				break;	

			default:
				System.out.println("This auth is not supported please pass the right AuthType..");
				throw new ApiExceptions("====Invalid AuthType====");
		}
		
		return request;
	}
	
	private void applyParams(RequestSpecification request, Map<String, String> queryParams, Map<String, String> pathParams) {
		
		if(queryParams!=null) {
			request.queryParams(queryParams);
		}
		
		if(pathParams!=null) {
			request.pathParams(pathParams);
		}
	}
	
	//CRUD :
	
	//get:
	/**
	 * Get Method to call get API
	 * @param baseURL
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authtype
	 * @param contentType
	 * @return it will return get api response
	 */
	public Response get(String baseURL, String endPoint, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType ,ContentType contentType) {
		
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.get(endPoint).then().spec(responseCode200or404).extract().response();
		response.prettyPrint();
		return response;
		
	}
	/**
	 * Post Method is for post API
	 * @param <T>
	 * @param baseURL
	 * @param body
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return it will return the response after run post api
	 */
	public <T>Response post(String baseURL,T body,String endPoint,Map<String, String> queryParams, Map<String , String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		
		Response response = request.body(body).post(endPoint).then().spec(responseCode201).extract().response();
		response.prettyPrint();
		return response;
	}

}

























