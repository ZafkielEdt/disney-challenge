package com.challenge.disney.controller.auth;

import static com.challenge.disney.common.AbstractAuthUtils.buildRegisterRequest;
import static org.junit.jupiter.api.Assertions.*;

import com.challenge.disney.common.AbstractIntegrationAuthTestUtils;
import com.challenge.disney.exception.ErrorResponse;
import com.challenge.disney.model.request.auth.RegisterRequest;
import com.challenge.disney.model.response.auth.RegisterResponse;
import java.net.URISyntaxException;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RegisterControllerTest extends AbstractIntegrationAuthTestUtils {


  @Test
  void registerShouldReturnOK() throws URISyntaxException {
    httpHeaders.set("Content-Type", "application/json");

    HttpEntity<RegisterRequest> requestHttpEntity = new HttpEntity<>(buildRegisterRequest(),
        httpHeaders);

    ResponseEntity<RegisterResponse> response = testRestTemplate
        .exchange(buildUri("/api/v2/register"),
            HttpMethod.POST,
            requestHttpEntity,
            RegisterResponse.class);

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Beth", Objects.requireNonNull(response.getBody()).getUsername());
    assertEquals("beth@lorem.com", response.getBody().getEmail());
  }

  @Test
  void registerShouldReturnBadRequest() throws URISyntaxException {
    httpHeaders.set("Content-Type", "application/json");

    HttpEntity<RegisterRequest> requestHttpEntity = new HttpEntity<>(buildRegisterRequest(),
        httpHeaders);

    ResponseEntity<ErrorResponse> response = testRestTemplate
        .exchange(buildUri("/api/v2/register"),
            HttpMethod.POST,
            requestHttpEntity,
            ErrorResponse.class);

    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
  }
}