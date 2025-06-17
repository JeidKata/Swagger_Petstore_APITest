package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.config.TestRunner;
import com.globant.swaggerPetstoreAPI.model.store.GetOrderResponseDTO;
import com.globant.swaggerPetstoreAPI.model.store.OrderDTO;
import com.globant.swaggerPetstoreAPI.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class StoreTest extends TestRunner {

    /**
     * This method creates an order in the store.
     * It uses the OrderDTO model to create the order object.
     */
    @Test(testName = "Create Order Test",
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
}
