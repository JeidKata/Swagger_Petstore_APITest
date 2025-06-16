package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.config.TestRunner;
import com.globant.swaggerPetstoreAPI.model.users.GetUserResponseDTO;
import com.globant.swaggerPetstoreAPI.request.RequestBuilder;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserTest extends TestRunner {

    private  String username = "Jeidy";
    private  String password = "J1234y";

    @Test(testName = "Create a new user")
    public void createUserTest() {
        RestAssured
                .given()
                .baseUri(getApiUrl())
                .header("content-type", ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(String.format("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"username\": \"%s\",\n" +
                        "  \"firstName\": \"jeidy\",\n" +
                        "  \"lastName\": \"olaya\",\n" +
                        "  \"email\": \"jkos@gmail.com\",\n" +
                        "  \"password\": \"%s\",\n" +
                        "  \"phone\": \"12345\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}", username, password))
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(testName = "Login with valid credentials")
    public void loginTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), String.format("/user/login?username=%s&password=%s", username, password), getApiKey());

        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());
        GetUserResponseDTO getUserResponseDTO = response.as(GetUserResponseDTO.class);
        System.out.println(getUserResponseDTO.toString());
    }

    @Test(testName = "Logout user")
    public void logoutTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), "/user/logout", getApiKey());

        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());
    }
}
