package com.challenge.app.config.security;

import com.challenge.app.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import org.springframework.http.HttpStatus;

public class ErrorResponseUtils {

  private ErrorResponseUtils() {

  }

  public static void setCustomResponse(HttpServletResponse response) throws IOException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("json/application");
    ObjectMapper mapper = new ObjectMapper();
    response.getWriter().write(mapper.writeValueAsString(getGenericErrorResponse()));
  }

  private static ErrorResponse getGenericErrorResponse() {
    return new ErrorResponse(
        HttpStatus.FORBIDDEN.value(),
        Collections.singletonList(
            "Access denied. Please, try to login again or contact your admin."),
        Timestamp.from(Instant.now())
    );
  }
}
