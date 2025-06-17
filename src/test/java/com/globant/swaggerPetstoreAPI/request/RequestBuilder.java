package com.globant.swaggerPetstoreAPI.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;


public class RequestBuilder {
    private static final String APIKEY = "apiKey";

    public static Response getRequest(String apiUrl, String path, String apiKey ) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(apiUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .header(APIKEY, apiKey)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());


        return requestSpecification.get(path);
    }

    public static Response postRequest(String apiUrl, String path, String apiKey, Object body) {
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(apiUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .header(APIKEY, apiKey)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(body);


        return requestSpecification.post(path);
    }
}
