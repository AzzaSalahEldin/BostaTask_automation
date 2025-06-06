package com.bosta.security;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PickupAPIClient implements APIClient{
    getTokenAPIClient tokenClient = new getTokenAPIClient();
    private final String BASE_URI = "https://app.bosta.co";
//    private final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Ink4UmhLS0QwQjlXU1JidFJnZ2JVaiIsInJvbGVzIjpbIkJVU0lORVNTX0FETUlOIl0sImJ1c2luZXNzQWRtaW5JbmZvIjp7ImJ1c2luZXNzSWQiOiJ6NXV2MTB3RkJFZXJtRWpiRVpBTXQiLCJidXNpbmVzc05hbWUiOiJhenphIn0sImNvdW50cnkiOnsiX2lkIjoiNjBlNDQ4MmM3Y2I3ZDRiYzQ4NDljNGQ1IiwibmFtZSI6IkVneXB0IiwibmFtZUFyIjoi2YXYtdixIiwiY29kZSI6IkVHIn0sImVtYWlsIjoiYXp6YXNhbGFoZWxkaW4xMUBnbWFpbC5jb20iLCJwaG9uZSI6IisyMDExMjkyMTE2NTYiLCJncm91cCI6eyJfaWQiOiJYYXFsQ0ZBIiwibmFtZSI6IkJVU0lORVNTX0ZVTExfQUNDRVNTIiwiY29kZSI6MTE1fSwidG9rZW5UeXBlIjoiQUNDRVNTIiwidG9rZW5WZXJzaW9uIjoiVjIiLCJzZXNzaW9uSWQiOiIwMUpYME1FNFFTNlNGUDlXWTdRSlNaUlQ3SiIsImlhdCI6MTc0OTE0Njg2NiwiZXhwIjoxNzUwMzU2NDY2fQ.OAscOXf0vdr8iA7gZ51-MiZqjy6ZXerF7BUG1Id1vVg";

    @Override
    public Response getResponse(Map<String, Object> payload) throws JsonProcessingException {
        String body = """
                {
                  "businessLocationId": "6044b12f463cb700137fc9f9",
                  "notes": "test",
                  "scheduledDate": "2021-06-10",
                  "contactPerson": {
                    "name": "Test Name",
                    "phone": "01001001000",
                    "email": "test@email.coom"
                  },
                  "repeatedData": {
                    "repeatedType": "Weekly",
                    "days": [
                      "Sunday"
                    ],
                    "startDate": "2021-06-10",
                    "endDate": "2021-10-10"
                  }
        }        
        """;
        Map<String, Object> baseBody = new ObjectMapper().readValue(body, Map.class);
        Map<String, Object> finalBody = new java.util.HashMap<>(baseBody);
        if (payload != null) {
            finalBody.putAll(payload); // overwrite or add
        }
        RestAssured.baseURI = BASE_URI;
       return  given()
                .header("Authorization", tokenClient.getAccessToken())
                .contentType(ContentType.JSON)
               .body(finalBody)
                .when()
                .post("/api/v2/pickups")
                .then()
                .statusCode(400)
                .log().all()
                .extract().response();

    }
}