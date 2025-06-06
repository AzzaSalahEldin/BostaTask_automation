package com.bosta.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;

import java.util.*;

public class SecurityScanner {


    private final OpenAISecurityTester aiTester = new OpenAISecurityTester();


    public void runSecurityTests(APIClient client) throws JsonProcessingException {
        Map<String, Object> basePayload = getValidPayload();
        List<Map<String, Object>> attacks = aiTester.generateMaliciousPayloads(basePayload);

        for (Map<String, Object> attack : attacks) {
            Response response = client.getResponse(attack);
            System.out.println("Response Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asString());
        }
    }

    private Map<String, Object> getValidPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("businessLocationId", "Q1MCImvcs");

        Map<String, String> contactPerson = new HashMap<>();
        contactPerson.put("_id", "pvE7i1MA_8");
        contactPerson.put("name", "Elkholaey");
        contactPerson.put("phone", "+201202436309");
        payload.put("contactPerson", contactPerson);

        payload.put("scheduledDate", "2025-04-15");
        payload.put("numberOfParcels", "3");
        payload.put("hasBigItems", false);

        Map<String, String> repeatedData = new HashMap<>();
        repeatedData.put("repeatedType", "One Time");
        payload.put("repeatedData", repeatedData);

        payload.put("creationSrc", "Web");

        return payload;
    }
}
