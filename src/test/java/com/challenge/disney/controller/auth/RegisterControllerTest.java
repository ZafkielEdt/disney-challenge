package com.challenge.disney.controller.auth;

import static org.junit.jupiter.api.Assertions.*;

import com.challenge.disney.model.request.RegisterRequest;
import com.challenge.disney.model.response.RegisterResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RegisterControllerTest {

  private final TestRestTemplate testRestTemplate = new TestRestTemplate();

  private final HttpHeaders httpHeaders = new HttpHeaders();

  @LocalServerPort
  private int port;


  @Test
  void register() throws URISyntaxException {
    httpHeaders.set("Content-Type", "application/json");

    HttpEntity<RegisterRequest> requestHttpEntity = new HttpEntity<>(buildRequest(), httpHeaders);

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

  private URI buildUri(String path) throws URISyntaxException {
    return new URI("http://localhost:" + port + path);
  }

  private RegisterRequest buildRequest() {
    RegisterRequest request = new RegisterRequest();
    request.setUsername("Beth");
    request.setEmail("beth@lorem.com");
    request.setPassword("12345678");
    return request;
  }
}