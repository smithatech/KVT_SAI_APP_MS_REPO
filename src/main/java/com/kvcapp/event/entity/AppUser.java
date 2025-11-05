package com.kvcapp.event.entity;

import jakarta.persistence.*;

@Entity
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true)
    private String email;
    private String password;
    private String fullName;
    private String preferredLanguage;
    private String role; // ADMIN or USER

    public AppUser() {}
    public AppUser(String id) { this.userId = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
