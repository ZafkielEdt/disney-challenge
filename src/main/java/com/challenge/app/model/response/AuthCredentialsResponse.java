package com.challenge.app.model.response;

public record AuthCredentialsResponse(
    String username,
    String token
) {}
