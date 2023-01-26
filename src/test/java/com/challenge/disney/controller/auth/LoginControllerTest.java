package com.challenge.disney.controller.auth;

import static com.challenge.disney.common.AbstractAuthUtils.buildLoginRequest;
import static org.junit.jupiter.api.Assertions.*;

import com.challenge.disney.common.AbstractIntegrationAuthTestUtils;
import com.challenge.disney.model.request.auth.LoginRequest;
import com.challenge.disney.model.response.auth.LoginResponse;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LoginControllerTest extends AbstractIntegrationAuthTestUtils {


  @Test
  void loginShouldReturnLoginResponse() throws URISyntaxException {
    httpHeaders.set("Content-Type", "application/json");

    HttpEntity<LoginRequest> requestHttpEntity = new HttpEntity<>(buildLoginRequest(), httpHeaders);

    ResponseEntity<LoginResponse> response = testRestTemplate
        .exchange(buildUri("/api/v2/login"),
            HttpMethod.POST,
            requestHttpEntity,
            LoginResponse.class);

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
  }
}