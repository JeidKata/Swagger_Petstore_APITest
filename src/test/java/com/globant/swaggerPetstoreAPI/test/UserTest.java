package com.globant.swaggerPetstoreAPI.test;

import com.globant.swaggerPetstoreAPI.config.TestRunner;
import com.globant.swaggerPetstoreAPI.request.RequestBuilder;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserTest extends TestRunner {

    private final String username = "Jeidy";
    private final String password = "J1234y";

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

    @Test(testName = "Login with valid credentials")
    public void loginTest() {
        Response response = RequestBuilder.getRequest(getApiUrl(), String.format("/user/login?username=%s&password=%s", username, password), getApiKey());

        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but got: " + response.getStatusCode());
    }

    @Test(testName = "Logout user")
    public void logoutTest() {
        RestAssured
                .given()
                .baseUri(getApiUrl())
                .header("content-type", ContentType.APPLICATION_JSON.getMimeType())//esta línea asegura que el servidor sepa que el cuerpo de la solicitud está en formato JSON.
                .filter(new RequestLoggingFilter())//permite ver exactamente qué datos se están enviando al servidor.
                .filter(new ResponseLoggingFilter())//útil para depuración, ya que permite ver exactamente qué devuelve el servidor.
                .when()
                .get("/user/logout")
                .then()
                .statusCode(200);
//                .log().all();//Si usas .filter(new ResponseLoggingFilter()), no necesitas .log().all(), ya que el filtro ya registra toda la respuesta automáticamente.
    }
}
