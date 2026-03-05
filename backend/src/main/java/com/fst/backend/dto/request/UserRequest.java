package com.fst.backend.dto.request;

import com.fst.backend.persistence.entity.UserPrivilegeLevel;

public class UserRequest {
    private String username;
    private String email;
    private String password;

    private UserPrivilegeLevel privilegeLevel;

    private Boolean enabled;

    public UserRequest() {}

    public UserRequest(String username, String email, String password, UserPrivilegeLevel privilegeLevel, Boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.privilegeLevel = privilegeLevel;
        this.enabled = enabled;
    }

    // Getters

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {this.password = password;}

    public UserPrivilegeLevel getPrivilegeLevel() {
        return privilegeLevel;
    }
    public void setPrivilegeLevel(UserPrivilegeLevel privilegeLevel) {this.privilegeLevel = privilegeLevel;}

    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {this.enabled = enabled;}
}
