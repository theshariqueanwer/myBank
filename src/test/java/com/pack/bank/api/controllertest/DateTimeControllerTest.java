package com.pack.bank.api.controllertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DateTimeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api";
    }

    @Test
    public void testCalculateFutureDateV1() {
        String url = getBaseUrl() + "/v1/date?days=90";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(200, response.getStatusCodeValue());
        // Ensure the response contains the correct format
        assert response.getBody().contains("Future Date (V1): ");
    }

    @Test
    public void testCalculateFutureDateV2() {
        String url = getBaseUrl() + "/v2/date?months=6&days=50";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(200, response.getStatusCodeValue());
        // Ensure the response contains the correct format
        assert response.getBody().contains("Future Date (V2): ");
    }

    @Test
    public void testCalculateFutureDateV3_ValidDate() {
        String url = getBaseUrl() + "/v3/date?specificDate=01-12-2024&days=50";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(200, response.getStatusCodeValue());
        // Ensure the response contains the correct format
        assert response.getBody().contains("Future Date (V3): ");
    }

    @Test
    public void testCalculateFutureDateV3_InvalidDate() {
        String url = getBaseUrl() + "/v3/date?specificDate=2024-12-01&days=50";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(400, response.getStatusCodeValue());
        assert response.getBody().contains("Invalid date format. Please use 'dd-MM-yyyy'.");
    }

    @Test
    public void testCalculateFutureDateV4_ValidDate() {
        String url = getBaseUrl() + "/v4/date?specificDate=01-12-2024&months=6&days=50";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(200, response.getStatusCodeValue());
        // Ensure the response contains the correct format
        assert response.getBody().contains("Future Date (V4): ");
    }

    @Test
    public void testCalculateFutureDateV4_InvalidDate() {
        String url = getBaseUrl() + "/v4/date?specificDate=2024-12-01&months=6&days=50";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(400, response.getStatusCodeValue());
        assert response.getBody().contains("Invalid date format. Please use 'dd-MM-yyyy'.");
    }
}

