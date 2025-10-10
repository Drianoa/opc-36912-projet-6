package com.openclassrooms.mddapi.features.auth;

import com.openclassrooms.mddapi.model.User;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmailOrUsername(String email, String username);

    Optional<User> findUserByUsernameIsInOrEmailIsIn(Collection<String> usernames, Collection<String> emails);

    Optional<User> findUserByEmail(String email);
}
