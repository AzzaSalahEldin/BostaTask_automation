import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class BostaTest {
    private final String BASE_URI = "https://stg-app.bosta.co";
    private final String TOKEN = "Bearer " + System.getenv("BOSTA_API_TOKEN");

    @Test
    public void testXSSInContactName() {
        RestAssured.baseURI = BASE_URI;
        System.out.println(TOKEN);

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

        given()
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/v2/pickups")
                .then()
                .statusCode(not(500))
                .body("message", not(containsString("unexpected")));
    }
}
