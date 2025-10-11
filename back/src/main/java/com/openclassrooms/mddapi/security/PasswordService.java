package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.features.auth.UserRepository;
import com.openclassrooms.mddapi.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for updating user password hash.
 * <p>
 * This class implements the {@link UserDetailsPasswordService} interface, which is used by
 * the Spring Security framework to set the user's password when an encoding upgrade is needed.
 *
 * @see UserDetailsPasswordService
 */
@Service
public class PasswordService implements UserDetailsPasswordService {

    private final UserRepository userRepository;

    public PasswordService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Updates the password of a user.
     *
     * @param user        the user details of the user whose password is being updated
     * @param newPassword the new password to set
     * @return the updated user details object
     * @throws RuntimeException if the user is not found in the repository
     */
    @Override
    @Transactional
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        if (user instanceof User userEntity) {
            userEntity.setPassword(newPassword);
            return userRepository.save(userEntity);
        }
        throw new RuntimeException("User not found: " + user.getUsername());
    }
}
