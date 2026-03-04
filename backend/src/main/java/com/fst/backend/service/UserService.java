package com.fst.backend.service;

import com.fst.backend.dto.request.UserRequest;
import com.fst.backend.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    UserResponse getUserById(Long id);
    void deleteUser(Long id);
    List<UserResponse> getAllUsers();

}
