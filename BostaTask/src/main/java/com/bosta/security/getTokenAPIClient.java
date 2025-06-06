package com.bosta.security;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class getTokenAPIClient {

    public String getAccessToken() {
        String requestBody = """
                    {
                      "email": "azzasalaheldin11@gmail.com",
                      "password": "Dalia@5566@zz"
                    }""";
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://app.bosta.co/api/v2/users/login")
                .then()
                .extract().response();
        String token = response.jsonPath().getString("data.token");
        return token;
    }
}
