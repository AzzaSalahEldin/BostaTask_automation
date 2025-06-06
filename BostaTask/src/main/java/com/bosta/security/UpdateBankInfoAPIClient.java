package com.bosta.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;


public class UpdateBankInfoAPIClient implements APIClient{
    String BASE_URI = "https://stg-app.bosta.co";
    getTokenAPIClient tokenClient = new getTokenAPIClient();
    String jsonBody = """
            {
                "bankInfo": {
                    "beneficiaryName": "asd asdasdddssss",
                    "bankName": "SCB - ةانق كنب سیوسلا",
                    "accountNumber": "12131",
                    "ibanNumber": "asd2112dasadasasdasd221231",
                    "countryName": "Egypt"
                },
                "paymentInfoOtp": "1231"
            }
            """;
    @Override
    public Response getResponse(Map<String, Object> payload) throws JsonProcessingException {
        Map<String, Object> baseBody = new ObjectMapper().readValue(jsonBody, Map.class);
        Map<String, Object> finalBody = new java.util.HashMap<>(baseBody);
        if (payload != null) {
            finalBody.putAll(payload); // overwrite or add
        }
        RestAssured.baseURI = BASE_URI;
        return  RestAssured.given()
                .header("authorization", tokenClient.getAccessToken())
                .header("accept", "application/json, text/plain, */*")
                .header("content-type", "application/json")
                .header("origin", "https://stg-business.bosta.co")
                .header("referer", "https://stg-business.bosta.co/")
                .header("priority", "u=1, i")
                .header("sec-ch-ua", "\"Google Chrome\";v=\"135\", \"Not-A.Brand\";v=\"8\", \"Chromium\";v=\"135\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Linux\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-site")
                .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36")
                .header("x-device-fingerprint", "rvmsmy")
                .header("x-device-id", "eyJpcCI6IjQ1LjI0Mi43Ni4xNTUiLCJmaW5nZXJwcmludCI6InVibjF3MyIsInVzZXJBZ2VudCI6Ik1vemlsbGEvNS4wIChYMTE7IExpbnV4IHg4Nl82NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzEzMS4wLjAuMCBTYWZhcmkvNTM3LjM2In0=")
                .body(finalBody)
                .when()
                .post("/api/v2/businesses/add-bank-info")
                .then()
                .statusCode(401)
                .log().all()
                .extract().response();
}
}
