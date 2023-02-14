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
  private final String[] PATHS= {"/genres","/filmseries","/dcharacters"};

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
            .requestMatchers(HttpMethod.POST, PATH + PATHS[0]).hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, PATH + PATHS[0]).hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.PUT, PATH + PATHS[0]).hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, PATH + PATHS[0] + "/{id}").hasRole("ADMIN"))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, PATH + PATHS[1]).hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, PATH + PATHS[1] + "/{id}").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.GET, PATH + PATHS[1]).hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.GET, PATH + PATHS[1] + "/filter").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.PUT, PATH + PATHS[1] + "/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, PATH + PATHS[1] + "/{id}").hasRole("ADMIN"))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, PATH + PATHS[2]).hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, PATH + PATHS[2]+ "/{id}").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET,PATH + PATHS[2]).hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.GET,PATH + PATHS[2] + "/filter").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.PUT, PATH + PATHS[2] + "/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, PATH + PATHS[2] + "/{id}").hasRole("ADMIN"))
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}
