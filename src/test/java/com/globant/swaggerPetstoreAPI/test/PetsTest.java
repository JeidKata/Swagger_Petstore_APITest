package com.globant.swaggerPetstoreAPI.test;

import io.restassured.path.json.JsonPath;
import com.globant.swaggerPetstoreAPI.config.TestRunner;
import com.globant.swaggerPetstoreAPI.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class PetsTest extends TestRunner {

    private List<Long> petId;
    private long specificPetId;

    @Test(testName = "Get all pets available")
    public void getAvailablePetsTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), "/pet/findByStatus?status=available", getApiKey());

        List<Long> petIds = response.jsonPath().getList("id", Long.class);
        petId = petIds;
    }

    @Test (testName = "Get a specific pet by ID", description = "Show details of a specific pet")
    public void getSpecificPetTest() {
           if (petId != null && !petId.isEmpty()) {
            specificPetId = petId.getFirst();
            Response respuesta = RequestBuilder.getRequest(getApiUrl(), String.format("/pet/%d", specificPetId), getApiKey());

            JsonPath responseJson = respuesta.jsonPath();
            assertEquals(responseJson.getLong("id"), specificPetId, "Expected pet ID does not match actual response.");
            assertEquals(responseJson.getString("status"), "available", "Expected pet status to be 'available', but got: " + responseJson.getString("status"));
            assertEquals(respuesta.getStatusCode(), 200, "Expected status code 200, but got: " + respuesta.getStatusCode());
        } else {
            System.out.println("No pets available to test.");
        }
    }
}
