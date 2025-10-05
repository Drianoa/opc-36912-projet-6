package com.openclassrooms.mddapi.features.auth;

import com.openclassrooms.mddapi.features.auth.dto.RegisterDto;
import com.openclassrooms.mddapi.model.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.Principal;
import java.util.Optional;

/**
 * Service class for handling user-related operations including registration, authentication, and user management.
 * This service provides methods to interact with user data and perform business logic related to users.
 */
@Service
@RequiredArgsConstructor
@Validated
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    /**
     * Finds a user by their email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the found user, or empty if no user is found
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    /**
     * Finds a user by their unique identifier.
     *
     * @param id the user ID to search for
     * @return the found user entity
     * @throws EntityNotFoundException if no user is found with the given ID
     */
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    /**
     * Registers a new user with the provided registration details.
     *
     * @param registerDto the DTO containing user registration information
     * @return the newly created user entity
     * @throws RuntimeException if a user with the given email already exists
     */
    public User register(@Valid RegisterDto registerDto) {
        userRepository.findUserByEmail(registerDto.email()).ifPresent(u -> {
            throw new RuntimeException("User already exists: " + registerDto.email());
        });
        var userEntity = new User();
        userEntity.setEmail(registerDto.email());
        userEntity.setName(registerDto.name());
        userEntity.setPassword(encoder.encode(registerDto.password()));

        return userRepository.save(userEntity);
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the authenticated user entity
     * @throws CredentialsExpiredException if the current user cannot be found in the database
     */
    public User currentUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return findByEmail(principal.getName())
                .orElseThrow(() -> new CredentialsExpiredException("User not found: " + principal.getName()));
    }

}
