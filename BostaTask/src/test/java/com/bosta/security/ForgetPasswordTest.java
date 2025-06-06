package com.bosta.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

public class ForgetPasswordTest {
    private final ForgetPasswordAPIClient client = new ForgetPasswordAPIClient();
    @Test
    public void testForgetPassword() throws JsonProcessingException {
        SecurityScanner scanner = new SecurityScanner();
        scanner.runSecurityTests(client);
    }
}
