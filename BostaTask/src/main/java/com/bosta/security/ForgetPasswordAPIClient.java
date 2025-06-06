package com.bosta.security;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ForgetPasswordAPIClient implements APIClient{
    private final String BASE_URI = "https://stg-app.bosta.co";

    @Override
    public Response getResponse(Map<String, Object> payload) {
        Map<String, Object> baseBody = Map.of("email", "azzasalaheldin11@gmail.com");
        Map<String, Object> finalBody = new java.util.HashMap<>(baseBody);
        if (payload != null) {
            finalBody.putAll(payload); // overwrite or add
        }
        RestAssured.baseURI = BASE_URI;
        return given()
                .contentType(ContentType.JSON)
                .body(finalBody)
                .when()
                .post("/api/v2/users/forget-password")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
    }
    }
