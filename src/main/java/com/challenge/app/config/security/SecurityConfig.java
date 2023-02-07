package com.challenge.app.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthorizationFilter jwtAuthorizationFilter;

  private final String PATH = "/api/v2";

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
      throws Exception {

    return httpSecurity.
        csrf()
        .disable()
        .authorizeHttpRequests(
            auth -> auth.requestMatchers(HttpMethod.POST,
                PATH + "/login").permitAll())
        .authorizeHttpRequests(
            auth -> auth.requestMatchers(HttpMethod.POST,
                PATH + "/register").permitAll())
        .authorizeHttpRequests()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}
