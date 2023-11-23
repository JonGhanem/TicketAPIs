package com.ghanem.ticketapi.security;

import com.ghanem.ticketapi.constants.TicketConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(TicketConstants.AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(TicketConstants.AUTH_TOKEN )) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}