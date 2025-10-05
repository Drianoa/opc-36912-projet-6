package com.openclassrooms.mddapi.features.auth.controller;


import com.openclassrooms.mddapi.features.auth.UserService;
import com.openclassrooms.mddapi.features.auth.dto.LoginRequestDto;
import com.openclassrooms.mddapi.features.auth.dto.LoginResponseDto;
import com.openclassrooms.mddapi.features.auth.dto.RegisterDto;
import com.openclassrooms.mddapi.features.auth.dto.UserResponseDto;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.security.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication management")
public class AuthController {


    private final UserService userService;
    private final ConversionService conversionService;
    private final AuthenticationService authenticationService;

    /**
     * Endpoint for logging in a user.
     * <p>
     * Logs in a user with the provided email and password.
     *
     * @param request the login request containing the email and password
     * @return a response containing the JWT token generated for the user
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {

        String jwt = authenticationService.authenticate(request.email(), request.password());
        LoginResponseDto response = new LoginResponseDto(jwt);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Endpoint for obtaining  the currently authenticated user.
     *
     * @param principal the authenticated user injected by Spring
     * @return a response containing user information
     */
    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponseDto> me(Principal principal) {

        var userEntity = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found for email: " + principal.getName()));

        UserResponseDto responseDto = conversionService.convert(userEntity, UserResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    /**
     * Endpoint for registering a new user.
     * <p>
     * Registers a new user with the provided email, name, and password.
     *
     * @param registerDto the registration request containing the email, name, and password
     * @return a response containing the JWT token generated for the user
     * @throws IllegalArgumentException if the email is already taken
     *
     */
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(@Valid @RequestBody RegisterDto registerDto) {
        User userEntity = userService.register(registerDto);
        // Authentifie l'utilisateur tout juste crée dans spring security
        String jwt = authenticationService.authenticate(userEntity.getEmail(), registerDto.password());
        LoginResponseDto response = new LoginResponseDto(jwt);
        return ResponseEntity.ok(response);
    }

}
