package com.fst.backend.dto.mapper;

import com.fst.backend.dto.request.UserRequest;
import com.fst.backend.dto.response.UserResponse;
import com.fst.backend.persistence.entity.UserEntity;
import com.fst.backend.security.auth.PasswordHasher;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordHasher passwordHasher;

    public UserMapper(PasswordHasher passwordHasher) {
        this.passwordHasher = passwordHasher;
    }
    public UserResponse toResponse(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }
    public UserEntity toEntity(UserRequest userRequest) {
        return new UserEntity(
                userRequest.getUsername(),
                userRequest.getEmail(),
                passwordHasher.hashPassword(userRequest.getPassword()),
                userRequest.getPrivilegeLevel()
        );
    }
}
