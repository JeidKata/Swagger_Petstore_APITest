package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.config.TestRunner;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.util.List;

public class PetsTest extends TestRunner {
    private long specificPetId;

    @Test(testName = "Get all pets available",
          description = "This test retrieves all pets from the API and verifies the response.",
          groups = {"pets"})
    public List<Integer> getAvailablePetsTest() {
        Response response = RestAssured
                .given()
                .baseUri(getApiUrl())
                .header("content-type", ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get("/pet/findByStatus?status=available")
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> petIds = response.jsonPath().getList("id");
        return petIds;
    }
}
