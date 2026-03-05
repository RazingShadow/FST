package com.fst.backend.persistence.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserPrivilegeLevel privilegeLevel;

    @Column(nullable = false)
    private Boolean enabled;

    public UserEntity() { }

    public UserEntity(String username, String email, String passwordHash, UserPrivilegeLevel privilegeLevel) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.privilegeLevel = privilegeLevel;
        this.enabled = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public UserPrivilegeLevel getPrivilegeLevel() { return privilegeLevel; }
    public void setPrivilegeLevel(UserPrivilegeLevel privilegeLevel) { this.privilegeLevel = privilegeLevel; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
}
