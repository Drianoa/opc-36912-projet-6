package com.openclassrooms.mddapi.features.auth;

import com.openclassrooms.mddapi.features.auth.dto.RegisterDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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
    @Transactional
    public User register(@Valid RegisterDto registerDto) {

        var logins = List.of(registerDto.email(), registerDto.username());

        userRepository.findUserByUsernameIsInOrEmailIsIn(logins, logins).ifPresent(u -> {
            throw new RuntimeException("User already exists: " + String.join(",", logins));
        });

        var userEntity = new User();
        userEntity.setEmail(registerDto.email());
        userEntity.setUsername(registerDto.username());
        userEntity.setPassword(encoder.encode(registerDto.password()));

        return userRepository.save(userEntity);
    }

    /**
     * Updates an existing user with the provided registration details.
     *
     * @param registerDto the DTO containing user update information
     * @return the updated user entity
     * @throws RuntimeException if a user with the given email already exists
     */
    @Transactional
    public User update(@Valid RegisterDto registerDto, Integer userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        var logins = List.of(registerDto.email(), registerDto.username());

        userRepository
                .findUserByUsernameIsInOrEmailIsInAndIdIsNot(logins, logins, userId)
                .ifPresent(u -> {
                    throw new RuntimeException("User already exists: " + String.join(",", logins));
                });

        user.setEmail(registerDto.email());
        user.setUsername(registerDto.username());
        user.setPassword(encoder.encode(registerDto.password()));

        return userRepository.save(user);
    }
}
