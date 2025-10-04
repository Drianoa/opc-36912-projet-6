package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProjectUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ProjectUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Implementation of {@link UserDetailsService#loadUserByUsername(String)}
     * <p>
     * Loads a user by their email address.
     *
     * @param email the email address of the user to load
     * @return a populated {@link UserDetails} object representing the user
     * @throws UsernameNotFoundException if the user with the given email address is not found
     */
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findUserByEmail(email)
                .map(user -> new User(user.getEmail(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
