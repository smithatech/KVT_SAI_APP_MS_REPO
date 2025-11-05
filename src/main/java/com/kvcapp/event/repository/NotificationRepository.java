package com.kvcapp.event.repository;

import com.kvcapp.event.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {
    java.util.List<Notification> findByUser_UserId(String userId);
}
