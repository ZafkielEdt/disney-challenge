package com.challenge.disney.config.security;

import com.challenge.disney.common.JwtUtils;
import com.challenge.disney.exception.InsufficientPermissionException;
import com.challenge.disney.model.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;

  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String token = jwtUtils.getToken(request);

    if (!Objects.isNull(token)) {
      try {
        String username = jwtUtils.getUsernameFromToken(token);
        setAuthentication(username, token, request);
      } catch (Exception e) {
        System.err.println("UNAUTHORIZED");
      }
    }

    filterChain.doFilter(request, response);
  }

  private void setAuthentication(String username, String token, HttpServletRequest request)
      throws Exception {

    if (Objects.isNull(username) && !jwtUtils.isValidToken(token)) {
      throw new InsufficientPermissionException(
          "Access denied. Please, try to login again or contact your admin.");
    }

    User user = (User) userDetailsService.loadUserByUsername(username);
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            null,
            user.getAuthorities());
    
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }
}
