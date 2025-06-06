package com.bosta.security;

import java.util.*;

public class OpenAISecurityTester {
    public List<Map<String, Object>> generateMaliciousPayloads(Map<String, Object> basePayload) {
        List<Map<String, Object>> payloads = new ArrayList<>();

        Map<String, Object> sqlInjection = new HashMap<>(basePayload);
        sqlInjection.put("clientName", "Robert'); DROP TABLE Clients;--");

        Map<String, Object> xssAttack = new HashMap<>(basePayload);
        Map<String, String> contactPerson = new HashMap<>();
        contactPerson.put("_id", "pvE7i1MA_8");
        contactPerson.put("name", "<script>alert('XSS')</script>");
        contactPerson.put("phone", "+201202436309");
        xssAttack.put("contactPerson", contactPerson);

        payloads.add(sqlInjection);
        payloads.add(xssAttack);

        return payloads;
    }
}
