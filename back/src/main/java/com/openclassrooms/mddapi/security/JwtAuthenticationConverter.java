package com.openclassrooms.mddapi.security;

import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Custom JWT authentication converter that loads the User object as the principal
 */
@Component
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final ProjectUserDetailsService userDetailsService;

    public JwtAuthenticationConverter(ProjectUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Long id = jwt.getClaim("userId");
        String subject = jwt.getSubject();

        var userDetails = userDetailsService.loadUserByUserId(id.intValue());

        // Create authentication token with User as principal
        return new JwtAuthenticationToken(jwt, List.of(), subject) {
            @Override
            public Object getPrincipal() {
                return userDetails; // Return the User object instead of Jwt
            }
        };
    }
}
