package com.challenge.app.config.security;


import com.challenge.app.common.JwtUtils;
import com.challenge.app.exception.InsufficientPermissionsException;
import com.challenge.app.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final UserDetailsServiceImpl userDetailsService;

  private final JwtUtils jwtUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String token = jwtUtils.getToken(request);

    if (!Objects.isNull(token)) {
      try {
        String username = jwtUtils.getUsernameFromToken(token);
        if (!Objects.isNull(username) && jwtUtils.isValidToken(token)) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              userDetails.getUsername(), null, userDetails.getAuthorities());

          SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
          throw new InsufficientPermissionsException("Access denied. Please, try to login again or contact your admin.");
        }
      } catch (Exception e) {
        ErrorResponseUtils.setCustomResponse(response);
      }
    }

    filterChain.doFilter(request, response);
  }
}
