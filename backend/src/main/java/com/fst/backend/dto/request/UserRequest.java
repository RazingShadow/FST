package com.fst.backend.dto.request;

import com.fst.backend.persistence.entity.UserPrivilegeLevel;

public class UserRequest {
    private String username;
    private String email;
    private String password;

    private UserPrivilegeLevel privilegeLevel;

    private Boolean enabled;

    public UserRequest() {}

    public UserRequest(String username, String email, String passwordHash, UserPrivilegeLevel privilegeLevel, Boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = passwordHash;
        this.privilegeLevel = privilegeLevel;
        this.enabled = enabled;
    }

    // Getters

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserPrivilegeLevel getPrivilegeLevel() {
        return privilegeLevel;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
