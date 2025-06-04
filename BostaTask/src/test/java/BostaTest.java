import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class BostaTest {
    private final String BASE_URI = "https://stg-app.bosta.co";
    private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Ik1uN3FpVk9jQnFiWlNaTUFwbzFMdyIsInJvbGVzIjpbIkJVU0lORVNTX0FETUlOIl0sImJ1c2luZXNzQWRtaW5JbmZvIjp7ImJ1c2luZXNzSWQiOiJBWG5keU5kbXh6V2EweHVFdVJycmIiLCJidXNpbmVzc05hbWUiOiLYqtiz2Kog2KjYstmG2LMifSwia291bnRyeSI6eyJfaWQiOiI2MGU0NDgyYzdjYjdkNGJjNDg0OWM0ZDUiLCJuYW1lIjoiRWd5cHQiLCJuYW1lQXIiOiLZhdi12LEiLCJjb2RlIjoiRUcifSwiaW1haWwiOiJtb2hhbWVkLmVsa2hvbGFleSsxQGJvc3RhLmNvIiwicGhvbmUiOiIrMjAxMjAyNDM2MzA5IiwiZ3JvdXAiOnsiX2lkIjoiWGFxbENGQSIiLCJuYW1lIjoiQk9VSU5FU1NfRlVMTF9BQ0NFU1MiLCJjb2RlIjoxMTV9LCJ0b2tlblR5cGUiOiJBQ0NFU1MiLCJ0b2tlblZlcnNpb24iOiJWMiIsInNlc3Npb25JZCI6IjAxSldKSzQ4M0FTRjg0NjZLRFhQWVpGTVQyIiwiaWF0IjoxNzQ0NzE1MjYwLCJleHAiOjE3NDU5MjQ4NjB9.jjFo0wZ1Dx8_mpA2tFPD3bsqVClRVxo4CWnFS2E3tpg";
    String body = """
        {
            "businessLocationId": "Q1MCImvcs",
            "contactPerson": {
                "_id": "pvE7i1MA_8",
                "name": "<script>alert('XSS')</script>",
                "phone": "+201202436309"
            },
            "scheduledDate": "2025-04-15",
            "numberOfParcels": "3",
            "hasBigItems": false,
            "repeatedData": {
                "repeatedType": "One Time"
            },
            "creationSrc": "Web"
        }
        """;
    @Test
    public void pickupWithValidToken() {
        RestAssured.baseURI = BASE_URI;
        given()
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/v2/pickups")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
    }
    @Test
    public void pickupWithInValidToken() {
        RestAssured.baseURI = BASE_URI;
        given()
                .basePath("/api/v2/pickups")
                .header("Authorization", "Bearer invalidtoken123")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then()
                .statusCode(401); // Unauthorized
    }

}
