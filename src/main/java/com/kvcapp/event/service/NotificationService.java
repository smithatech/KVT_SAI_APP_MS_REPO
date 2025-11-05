package com.kvcapp.event.service;

import com.kvcapp.event.entity.AppUser;
import com.kvcapp.event.entity.Notification;
import com.kvcapp.event.entity.NotificationPreference;
import com.kvcapp.event.repository.NotificationPreferenceRepository;
import com.kvcapp.event.repository.NotificationRepository;
import com.kvcapp.event.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationPreferenceRepository prefRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, NotificationPreferenceRepository prefRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.prefRepository = prefRepository;
        this.userRepository = userRepository;
    }

    public List<Notification> forUser(String userId) {
        return notificationRepository.findByUser_UserId(userId);
    }

    @Transactional
    public void updatePreferences(String userId, java.util.List<NotificationPreference> prefs) {
        AppUser user = userRepository.findById(userId).orElseThrow();
        // naive approach: remove and re-add
        for (NotificationPreference p : prefRepository.findAll()) {
            if (p.getUser() != null && userId.equals(p.getUser().getUserId())) {
                prefRepository.delete(p);
            }
        }
        for (NotificationPreference p : prefs) {
            p.setId(null);
            p.setUser(user);
            prefRepository.save(p);
        }
    }

    @Transactional
    public Notification createNotification(String userId, String message, String link) {
        AppUser user = userRepository.findById(userId).orElseThrow();
        Notification n = new Notification(UUID.randomUUID().toString());
        n.setMessage(message);
        n.setLinkTo(link);
        n.setUser(user);
        return notificationRepository.save(n);
    }
}
