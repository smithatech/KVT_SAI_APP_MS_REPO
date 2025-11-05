package com.kvcapp.event.controller;

import com.kvcapp.event.dto.UserPrefDtos;
import com.kvcapp.event.entity.*;
import com.kvcapp.event.repository.*;
import com.kvcapp.event.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class UserController {
    private final ExhibitionRepository exhibitionRepository;
    private final ModuleRepository moduleRepository;
    private final StallRepository stallRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private final NotificationService notificationService;
    public UserController(ExhibitionRepository exhibitionRepository, ModuleRepository moduleRepository,
                          StallRepository stallRepository, PosterContentRepository posterContentRepository,
                          QuestionnaireRepository questionnaireRepository, NotificationRepository notificationRepository,
                          NotificationService notificationService, UserRepository userRepository) {
        this.exhibitionRepository = exhibitionRepository;
        this.moduleRepository = moduleRepository;
        this.stallRepository = stallRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.notificationService = notificationService;
    }

    @GetMapping("/exhibitions")
    public java.util.List<Exhibition> getPublishedExhibitions(@RequestParam(required = false) String location) {
        return exhibitionRepository.findAll().stream()
                .filter(e -> e.getStatus() == Exhibition.Status.Published)
                .filter(e -> location == null || location.isBlank() || location.equalsIgnoreCase(e.getLocation()))
                .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/exhibitions/{exhibitionId}")
    public ResponseEntity<?> getExhibitionById(@PathVariable String exhibitionId) {
        return exhibitionRepository.findById(exhibitionId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Exhibition not found")));
    }

    @GetMapping("/modules/{moduleId}")
    public ResponseEntity<?> getModule(@PathVariable String moduleId) {
        return moduleRepository.findById(moduleId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Module not found")));
    }

    @GetMapping("/stalls/{stallId}")
    public ResponseEntity<?> getStall(@PathVariable String stallId, @RequestParam(required = false) String language) {
        return stallRepository.findById(stallId)
                .<ResponseEntity<?>>map(s -> {
                    // Optionally filter poster content by language
                    if (language != null && !language.isBlank()) {
                        s.getPosterContent().removeIf(pc -> pc.getLanguageCode() == null || !language.equalsIgnoreCase(pc.getLanguageCode()));
                    }
                    return ResponseEntity.ok(s);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Stall not found")));
    }

    @GetMapping("/me/questionnaires")
    public java.util.List<Questionnaire> myQuestionnaires(@RequestHeader("X-User-Id") String userId) {
        // naive: return all active questionnaires
        return questionnaireRepository.findAll().stream()
                .filter(q -> q.getStatus() == Questionnaire.Status.Active)
                .collect(Collectors.toList());
    }

    @PostMapping("/me/preferences")
    public ResponseEntity<?> submitPreferences(@RequestHeader("X-User-Id") String userId, @RequestBody UserPrefDtos.UserAnswers req) {
        // mock: simply create a notification that preferences were updated
        try {
            notificationService.createNotification(userId, "Preferences submitted for questionnaire " + req.questionnaireId, "/exhibitions");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error","Invalid request body"));
        }
    }

    @GetMapping("/me/notifications")
    public java.util.List<Notification> myNotifications(@RequestHeader("X-User-Id") String userId) {
        return notificationService.forUser(userId);
    }

    @PutMapping("/me/notifications/preferences")
    public ResponseEntity<?> updateNotifPrefs(@RequestHeader("X-User-Id") String userId, @RequestBody UserPrefDtos.NotificationPreferences req) {
        java.util.List<NotificationPreference> prefs = new java.util.ArrayList<>();
        if (req.preferences != null) {
            for (UserPrefDtos.NotificationPreference p : req.preferences) {
                NotificationPreference np = new NotificationPreference();
                if (p.notificationType != null) {
                    // map "-" to "_"
                    String name = p.notificationType.replace("-", "_").replace("In-App", "In_App");
                    np.setNotificationType(NotificationPreference.NotificationType.valueOf(name));
                }
                np.setCategory(p.category);
                np.setEnabled(Boolean.TRUE.equals(p.enabled));
                prefs.add(np);
            }
        }
        try {
            notificationService.updatePreferences(userId, prefs);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
