package com.bosta.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

public class UpdateBankInfoTest {
    private final UpdateBankInfoAPIClient client = new UpdateBankInfoAPIClient();
    @Test
    public void testUpdateBankInfo() throws JsonProcessingException {
        SecurityScanner scanner = new SecurityScanner();
        scanner.runSecurityTests(client);
    }
}
