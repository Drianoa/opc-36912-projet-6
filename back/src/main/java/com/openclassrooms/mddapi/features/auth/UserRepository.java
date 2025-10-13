package com.openclassrooms.mddapi.features.auth;

import com.openclassrooms.mddapi.model.User;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmailOrUsername(String email, String username);

    Optional<User> findUserByUsernameIsInOrEmailIsIn(Collection<String> usernames, Collection<String> emails);

    @Query("SELECT u FROM User u WHERE (u.username IN :usernames OR u.email IN :emails) AND u.id <> :userId")
    Optional<User> findUserByUsernameIsInOrEmailIsInAndIdIsNot(
            Collection<String> usernames, Collection<String> emails, Integer userId);

    Optional<User> findUserByEmail(String email);
}
