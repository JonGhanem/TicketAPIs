package com.ghanem.ticketapi.security;

import com.ghanem.ticketapi.constants.TicketConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        String requestType = httpRequest.getMethod();



        if ((requestType.equals(TicketConstants.POST) && !Arrays.asList(TicketConstants.EXCLUDE_POST_URLS).contains(path)) ||
                (requestType.equals(TicketConstants.GET) && !Arrays.asList(TicketConstants.EXCLUDE_GET_URLS).contains(path))) {
            try {
                Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception exp) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = httpResponse.getWriter();
                writer.print(exp.getMessage());
                writer.flush();
                writer.close();
            }
        }
        filterChain.doFilter(request, response);
    }
}
