package com.fst.backend.service.impl;

import com.fst.backend.dto.exception.UserNotFoundException;
import com.fst.backend.dto.mapper.UserResponseMapper;
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
    private final UserResponseMapper userResponseMapper;

    private final PasswordHasher passwordHasher; // ToDO: inject correct encoder

    public UserServiceImpl(
            UserRepository userRepository,
            UserResponseMapper userResponseMapper,
            PasswordHasher passwordHasher
    ) {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
        this.passwordHasher = passwordHasher;
    }
    @Override
    public UserResponse createUser(UserRequest request) {
        UserEntity user = new UserEntity(
                request.getUsername(),
                request.getEmail(),
                passwordHasher.hashPassword(request.getPassword()),
                request.getPrivilegeLevel()
        );
        userRepository.save(user); // save transient entity to repository, get back managed entity with ID
        return userResponseMapper.toResponse(user); // map managed entity to response DTO
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {

        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        applyUpdates(existing, request);

        return userResponseMapper.toResponse(userRepository.save(existing));
    }

    private void applyUpdates(UserEntity entity, UserRequest request) {

        Optional.ofNullable(request.getUsername())
                .ifPresent(entity::setUsername);

        Optional.ofNullable(request.getEmail())
                .ifPresent(entity::setEmail);

        Optional.ofNullable(request.getPassword())
                .map(passwordHasher::hashPassword)
                .ifPresent(entity::setPasswordHash);

        Optional.ofNullable(request.getEnabled())
                .ifPresent(entity::setEnabled);

        Optional.ofNullable(request.getPrivilegeLevel())
                .ifPresent(entity::setPrivilegeLevel);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userResponseMapper::toResponse)
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
        return userRepository.findAll().stream()
                .map(userResponseMapper::toResponse)
                .toList();
    }


}
