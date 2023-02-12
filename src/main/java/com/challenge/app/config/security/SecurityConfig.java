package com.challenge.app.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthorizationFilter jwtAuthorizationFilter;

  private final String PATH = "/api/v2";

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
      throws Exception {

    return httpSecurity.
        csrf()
        .disable()
        .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, PATH + "/login").permitAll()
                .requestMatchers(HttpMethod.POST, PATH + "/register").permitAll())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/api/v2/genres").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/v2/genres").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/v2/genres/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/v2/genres/{id}").hasRole("ADMIN"))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, PATH + "/filmseries").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, PATH + "/filmseries/{id}").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.GET, PATH + "/filmseries").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.PUT, PATH + "/filmseries/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, PATH + "/filmseries/{id}").hasRole("ADMIN"))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, PATH + "/dcharacters").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, PATH + "/dcharacters/{id}").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET,PATH + "/dcharacters").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.PUT, PATH + "/dcharacters/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, PATH + "/dcharacters/{id}").hasRole("ADMIN"))
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}
