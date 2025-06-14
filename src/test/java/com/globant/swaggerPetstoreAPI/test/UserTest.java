package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.config.TestRunner;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

public class UserTest extends TestRunner {
    @Test(testName = "Create a new user")
    public void createUserTest() {
        RestAssured
                .given()
                .baseUri(getApiUrl())
                .header("content-type", ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"username\": \"Jeidy\",\n" +
                        "  \"firstName\": \"jeidy\",\n" +
                        "  \"lastName\": \"olaya\",\n" +
                        "  \"email\": \"jkos@gmail.com\",\n" +
                        "  \"password\": \"J1234y\",\n" +
                        "  \"phone\": \"12345\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .log().all();
    }
}
