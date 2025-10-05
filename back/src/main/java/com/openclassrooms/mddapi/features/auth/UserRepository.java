package com.openclassrooms.mddapi.features.auth;

import com.openclassrooms.mddapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}
