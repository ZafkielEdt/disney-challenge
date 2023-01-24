package com.challenge.disney.common;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

public abstract class AbstractIntegrationAuthTestUtils {

  protected final TestRestTemplate testRestTemplate = new TestRestTemplate();

  protected final HttpHeaders httpHeaders = new HttpHeaders();

  @LocalServerPort
  private int port;

  protected URI buildUri(String path) throws URISyntaxException {
    return new URI("http://localhost:" + port + path);
  }
}
