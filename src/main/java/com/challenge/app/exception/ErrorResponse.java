package com.challenge.app.exception;

import java.sql.Timestamp;
import java.util.List;

public record ErrorResponse(
    int status,
    List<String> messages,
    Timestamp timestamp
) {
}
