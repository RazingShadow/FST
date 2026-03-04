package com.fst.backend.security.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Implementation of PasswordHasher using BCrypt algorithm.
 */

@Component
public class BCryptPasswordHasher implements PasswordHasher {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordHasher() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hashPassword(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public Boolean verifyPassword(String rawPassword, String hashedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, hashedPassword);
    }

}
