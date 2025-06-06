package com.bosta.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

public class PickupAPISecurityTest {
    private final PickupAPIClient client = new PickupAPIClient();
    @Test
    public void testPickupEndpointForVulnerabilities() throws JsonProcessingException {
        SecurityScanner scanner = new SecurityScanner();
        scanner.runSecurityTests(client);
    }
}