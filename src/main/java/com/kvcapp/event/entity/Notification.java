package com.kvcapp.event.entity;

import jakarta.persistence.*;

@Entity
public class Notification {
    @Id
    private String notificationId;
    @Column(length = 2000)
    private String message;
    private String linkTo;
    private boolean isRead = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Notification() {}
    public Notification(String id) { this.notificationId = id; }

    public String getNotificationId() { return notificationId; }
    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getLinkTo() { return linkTo; }
    public void setLinkTo(String linkTo) { this.linkTo = linkTo; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }
}
