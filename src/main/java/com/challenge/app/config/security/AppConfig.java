package com.challenge.app.config.security;

import com.challenge.app.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppConfig {

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
      BCryptPasswordEncoder encoder,
      UserDetailsServiceImpl userDetails) throws Exception {
    return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetails)
        .passwordEncoder(encoder)
        .and()
        .build();
  }
}
