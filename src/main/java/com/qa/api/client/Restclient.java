package com.qa.api.client;

import java.io.File;
import java.util.Base64;
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
	private ResponseSpecification responseCode204 = expect().statusCode(204);
	private ResponseSpecification responseCode200or404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	private ResponseSpecification responseCode200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));

	
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
				request.header("Authorization", "Basic "+getBasicAuthToken());	
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
	
	private String getBasicAuthToken() {
		String credentials = ConfigManager.get("basicAuthUsername")+":"+ConfigManager.get("basicAuthPassword");
		return Base64.getEncoder().encodeToString(credentials.getBytes());
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
		Response response = request.body(body).post(endPoint).then().spec(responseCode200or201).extract().response();
		response.prettyPrint();
		return response;
	}
	/**
	 * 
	 * @param <T>
	 * @param baseURL
	 * @param body
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>Response post(String baseURL,File body,String endPoint,Map<String, String> queryParams, Map<String , String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		
		Response response = request.body(body).post(endPoint).then().spec(responseCode201).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T>Response post(String baseURL,String endPoint,String ClientId, String ClientSecret,String grand_type, ContentType contentType) {
		
		Response response = RestAssured.given()
						.formParam("grant_type", grand_type)
						.formParam("client_id", ClientId)
						.formParam("client_secret", ClientSecret)
		            .when().post(baseURL+endPoint);
		response.prettyPrint();
		return response;
		
	}
	/**
	 * 
	 * @param <T>
	 * @param baseURL
	 * @param body
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>Response put(String baseURL,T body,String endPoint,Map<String, String> queryParams, Map<String , String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		
		Response response = request.body(body).put(endPoint).then().spec(responseCode200).extract().response();
		response.prettyPrint();
		return response;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param baseURL
	 * @param body
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>Response patch(String baseURL,T body,String endPoint,Map<String, String> queryParams, Map<String , String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		
		Response response = request.body(body).patch(endPoint).then().spec(responseCode200).extract().response();
		response.prettyPrint();
		return response;
	}
	/**
	 * 
	 * @param <T>
	 * @param baseURL
	 * @param body
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>Response delete(String baseURL,String endPoint,Map<String, String> queryParams, Map<String , String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		applyParams(request, queryParams, pathParams);
		
		Response response = request.delete(endPoint).then().spec(responseCode204).extract().response();
		response.prettyPrint();
		return response;
	}
	
	

}

























