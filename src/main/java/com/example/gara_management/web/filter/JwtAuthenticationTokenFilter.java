package com.example.gara_management.web.filter;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.exception.UnauthorizedException;
import com.example.gara_management.security.core.userdetails.CustomUserDetailsService;
import com.example.gara_management.util.JwtUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  private final String jwtSecret;

  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    String jwt = parseJwt(request);
    if (StringUtils.isBlank(jwt)) {
      throw new JwtException("Token was not provided");
    }
    String username = JwtUtils.getSubjectFromJwtToken(jwt, jwtSecret);

    UserDetails userDetails;
    if (userDetailsService instanceof CustomUserDetailsService) {
      userDetails = ((CustomUserDetailsService) userDetailsService).loadUserByUsernameAndAccessToken(
          username, jwt);
      if (userDetails == null) {
        throw new UnauthorizedException("Token is invalid")
            .errorCode(ErrorCode.INVALID_TOKEN);
      }
    } else {
      userDetails = userDetailsService.loadUserByUsername(username);
    }
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    final String BEARER_PREFIX = "Bearer ";
    String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (StringUtils.startsWith(headerAuth, BEARER_PREFIX)) {
      return headerAuth.substring(BEARER_PREFIX.length());
    }

    return null;
  }

}
