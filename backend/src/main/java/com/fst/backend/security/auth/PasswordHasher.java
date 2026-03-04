package com.fst.backend.security.auth;

public interface PasswordHasher {
    String hashPassword(String rawPassword);
    Boolean verifyPassword(String rawPassword, String hashedPassword);
}
