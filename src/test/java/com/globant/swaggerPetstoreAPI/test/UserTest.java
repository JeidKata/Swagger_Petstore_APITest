package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.config.TestRunner;
import com.globant.swaggerPetstoreAPI.model.users.GetUserResponseDTO;
import com.globant.swaggerPetstoreAPI.model.users.UserDTO;
import com.globant.swaggerPetstoreAPI.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserTest extends TestRunner {

    private  String username = "Jeidy";
    private  String password = "J1234y";

    /**
     * This test creates a new user in the pet store.
     * It uses the UserDTO model to create the user object.
     */
    @Test(testName = "Create a new user")
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
    }

    /**
     * This test retrieves a user by their username.
     * It checks if the user exists and verifies the response.
     */
    @Test(testName = "Login with valid credentials")
    public void loginTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), String.format("/user/login?username=%s&password=%s", username, password), getApiKey());

        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());
        GetUserResponseDTO getUserResponseDTO = response.as(GetUserResponseDTO.class);
        System.out.println(getUserResponseDTO.toString());
    }

    /**
     * This test retrieves a user by their username.
     * It checks if the user exists and verifies the response.
     */
    @Test(testName = "Logout user")
    public void logoutTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), "/user/logout", getApiKey());

        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());
    }
}
