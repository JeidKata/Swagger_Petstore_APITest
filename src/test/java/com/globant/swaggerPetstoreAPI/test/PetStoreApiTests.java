package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.config.TestRunner;
import com.globant.swaggerPetstoreAPI.model.store.OrderDTO;
import com.globant.swaggerPetstoreAPI.model.users.GetUserResponseDTO;
import com.globant.swaggerPetstoreAPI.model.users.UserDTO;
import com.globant.swaggerPetstoreAPI.request.RequestBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PetStoreApiTests extends TestRunner {

    private  String username = "Jeidy";
    private  String password = "J1234y";

    private List<Long> petId;
    private long specificPetId;

    /**
     * This method creates a user and logs in with the created user.
     * It uses the UserDTO model to create the user object.
     */
    @Test(priority = 1, testName = "Create and login user")
    public void createUserTest() {
        UserDTO user = UserDTO.builder()
                .id(1)
                .username(username)
                .firstName("jeidy")
                .lastName("olaya")
                .email("jkos@gmail.com")
                .password(password)
                .phone("12345")
                .userStatus(0)
                .build();

        Response response = RequestBuilder.postRequest(
                getApiUrl(),
                "/user",
                getApiKey(),
                user);
        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());

        Response respon = RequestBuilder.getRequest(getApiUrl(), String.format("/user/login?username=%s&password=%s", username, password), getApiKey());

        assertEquals(respon.getStatusCode(), 200, "Expected status code 200, but got: " + respon.getStatusCode());
        GetUserResponseDTO getUserResponseDTO = respon.as(GetUserResponseDTO.class);
        System.out.println(getUserResponseDTO.toString());
    }

    /**
     * This method retrieves all pets available in the store.
     * It uses the API endpoint to get pets by status "available".
     */
    @Test(priority = 2, testName = "Get all pets available")
    public void getAvailablePetsTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), "/pet/findByStatus?status=available", getApiKey());

        List<Long> petIds = response.jsonPath().getList("id", Long.class);
        petId = petIds;
    }

    /**
     * This method retrieves a specific pet by its ID.
     * It checks if the pet ID exists and retrieves the details of that pet.
     */
    @Test (priority = 3, testName = "Get a specific pet by ID", description = "Show details of a specific pet")
    public void getSpecificPetTest() {
        getAvailablePetsTest();
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

    /**
     * This method creates an order in the store.
     * It uses the OrderDTO model to create the order object.
     */
    @Test(priority = 4, testName = "Create Order Test",
            description = "This test creates an order in the store and checks the response status code.")
    public void createOrderTest(){
        OrderDTO order = OrderDTO.builder()
                .id(1)
                .petId(1)
                .quantity(1)
                .shipDate(LocalDateTime.now())
                .status("placed")
                .complete(true)
                .build();

        Response response = RequestBuilder.postRequest(
                getApiUrl(),
                "/store/order",
                getApiKey(),
                order
        );
        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());
    }

    /**
     * This method logs out the user.
     * It uses the API endpoint to log out the user.
     */
    @Test(priority = 5, testName = "Logout user")
    public void logoutTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), "/user/logout", getApiKey());

        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());
    }
}
