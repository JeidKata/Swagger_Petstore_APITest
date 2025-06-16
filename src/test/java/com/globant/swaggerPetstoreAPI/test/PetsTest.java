package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.config.TestRunner;
import com.globant.swaggerPetstoreAPI.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class PetsTest extends TestRunner {

    private List<Integer> petId;
    private long specificPetId;

    @Test(testName = "Get all pets available")
    public void getAvailablePetsTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), "/pet/findByStatus?status=available", getApiKey());

        List<Integer> petIds = response.jsonPath().getList("id");
        petId = petIds;
    }

    @Test (testName = "Get a specific pet by ID", description = "Show details of a specific pet")
    public void getSpecificPetTest() {
        if (petId != null && !petId.isEmpty()) {
            specificPetId = petId.getFirst();
            Response response = RequestBuilder.getRequest(getApiUrl(), String.format("/pet/%d", specificPetId), getApiKey());
            assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());
        } else {
            System.out.println("No pets available to test.");
        }
    }
}
