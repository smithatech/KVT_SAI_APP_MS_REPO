package com.kvcapp.event.entity;

import jakarta.persistence.*;

@Entity
public class NotificationPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType; // Email, In-App, Push

    private String category;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    public enum NotificationType { Email, In_App, Push }

    public NotificationPreference() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public NotificationType getNotificationType() { return notificationType; }
    public void setNotificationType(NotificationType notificationType) { this.notificationType = notificationType; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }
}
