package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.model.pets.GetPetsRespondeDTO;
import io.restassured.path.json.JsonPath;
import com.globant.swaggerPetstoreAPI.config.TestRunner;
import com.globant.swaggerPetstoreAPI.model.pets.PetDTO;
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
            Long idPet = responseJson.getLong("id");
            String statusPet = responseJson.getString("status");
            System.out.println(respuesta.getBody().asString());
            GetPetsRespondeDTO expectedPet = GetPetsRespondeDTO.builder()
                    .petsData(PetDTO.builder()
                            .id(specificPetId)
                            .status("available")
                            .build())
                    .build();
            PetDTO actualPet = respuesta.as(PetsDTO.class);
            assertEquals(actualPet, expectedPet, "Expected pet details do not match actual response.");
            assertEquals(respuesta.getStatusCode(), 200, "Expected status code 200, but got: " + respuesta.getStatusCode());
        } else {
            System.out.println("No pets available to test.");
        }
    }
}
