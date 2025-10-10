package com.openclassrooms.mddapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    /**
     * Authenticates a user with the provided email and password.
     *
     * @param login    the email or username of the user
     * @param password the password of the user not encoded
     * @return the JWT token generated for the user
     */
    public String authenticate(String login, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);

        Authentication auth = authenticationManager.authenticate(token);

        return tokenService.generateToken(auth);
    }
}
