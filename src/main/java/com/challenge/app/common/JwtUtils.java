package com.challenge.app.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  private final String AUTH_HEADER_PARAM_NAME = "Authorization";
  private final String AUTH_HEADER_TOKEN_PREFIX = "Bearer";
  @Value("${security.jwt.secret}")
  private String SIGNING_KEY;
  private final Long EXPIRE_IN = 600000L;
  private final String AUTH_HEADER_USERNAME = "username";
  private final String AUTH_HEADER_ROLES = "roles";

  public String getToken(HttpServletRequest request) {
    String authToken = request.getHeader(AUTH_HEADER_PARAM_NAME);
    if (Objects.isNull(authToken)) {
      return null;
    }
    return authToken.substring(AUTH_HEADER_TOKEN_PREFIX.length());
  }

  public String getUsernameFromToken(String token) throws Exception {
    String username = null;

    try {
      final Claims claims = Jwts.
          parserBuilder().
          setSigningKey(Keys.hmacShaKeyFor(SIGNING_KEY.getBytes()))
          .build()
          .parseClaimsJws(token).
          getBody();
      username = String.valueOf(claims.get(AUTH_HEADER_USERNAME));
    } catch (JwtException e) {
      throw new Exception("INVALID JWT TOKEN");
    }
    return username;
  }

  
  public boolean isValidToken(String token) throws Exception {
    boolean isValid = true;
    try {
      final Claims claims = Jwts.
          parserBuilder().
          setSigningKey(Keys.hmacShaKeyFor(SIGNING_KEY.getBytes())).build()
          .parseClaimsJws(token).getBody();
      isValid = !(claims.getExpiration().before(new Date()));
    } catch (JwtException e) {
      throw new Exception("INVALID JWT TOKEN");
    }

    return isValid;
  }

  public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
    Claims claims = Jwts.claims();
    String roles = authorities.stream().map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
    claims.put(AUTH_HEADER_USERNAME, username);
    claims.put(AUTH_HEADER_ROLES, roles);

    Date expiration = Date.from(Instant.ofEpochMilli(new Date().getTime() + EXPIRE_IN));

    return Jwts.builder()
        .setSubject(username).
        setClaims(claims).
        setIssuedAt(new Date()).
        setExpiration(expiration)
        .signWith(Keys.hmacShaKeyFor(SIGNING_KEY.getBytes()),
            SignatureAlgorithm.HS256).compact();
  }
}
