package com.fst.backend.service.impl;

import com.fst.backend.dto.exception.UserNotFoundException;
import com.fst.backend.dto.mapper.UserMapper;
import com.fst.backend.dto.request.UserRequest;
import com.fst.backend.dto.response.UserResponse;
import com.fst.backend.persistence.entity.UserEntity;
import com.fst.backend.persistence.repository.UserRepository;
import com.fst.backend.security.auth.PasswordHasher;
import com.fst.backend.service.UserService;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordHasher passwordHasher; // ToDO: inject correct encoder

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userResponseMapper,
            PasswordHasher passwordHasher
    ) {
        this.userRepository = userRepository;
        this.userMapper = userResponseMapper;
        this.passwordHasher = passwordHasher;
    }
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = userMapper.toEntity(userRequest);
        userRepository.save(userEntity); // save transient entity to repository, get back managed entity with ID
        return userMapper.toResponse(userEntity); // map managed entity to response DTO
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {

        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        applyUpdates(existing, userRequest);

        return userMapper.toResponse(userRepository.save(existing));
    }

    private void applyUpdates(UserEntity userEntity, UserRequest userRequest) {

        Optional.ofNullable(userRequest.getUsername())
                .ifPresent(userEntity::setUsername);

        Optional.ofNullable(userRequest.getEmail())
                .ifPresent(userEntity::setEmail);

        Optional.ofNullable(userRequest.getPassword())
                .map(passwordHasher::hashPassword)
                .ifPresent(userEntity::setPasswordHash);

        Optional.ofNullable(userRequest.getEnabled())
                .ifPresent(userEntity::setEnabled);

        Optional.ofNullable(userRequest.getPrivilegeLevel())
                .ifPresent(userEntity::setPrivilegeLevel);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }
}
