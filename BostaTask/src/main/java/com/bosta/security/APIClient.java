package com.bosta.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;

import java.util.Map;

public interface APIClient {
    Response getResponse(Map<String, Object> payload) throws JsonProcessingException;
}