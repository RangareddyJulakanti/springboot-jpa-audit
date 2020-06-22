package com.ranga.config;

import com.ranga.model.LoggedUser;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;
@Component
public class RequestFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    try {
      LoggedUser.logIn(httpServletRequest.getHeader("USER_ID"));
      filterChain.doFilter(httpServletRequest, httpServletResponse);
      LoggedUser.logOut();
    } finally {
      LoggedUser.logOut();
    }
  }
}
