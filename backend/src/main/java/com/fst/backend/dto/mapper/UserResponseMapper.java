package com.fst.backend.dto.mapper;

import com.fst.backend.dto.response.UserResponse;
import com.fst.backend.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {
    public UserResponse toResponse(UserEntity entity) {
        return new UserResponse(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail()
        );
    }
}
