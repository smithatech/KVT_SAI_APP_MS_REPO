package com.kvcapp.event.dto;

import java.util.List;

public class UserPrefDtos {
    public static class UserAnswers {
        public String questionnaireId;
        public java.util.List<UserAnswer> answers;
    }
    public static class UserAnswer {
        public String questionId;
        public String answer;
    }

    public static class NotificationPreferences {
        public java.util.List<NotificationPreference> preferences;
    }
    public static class NotificationPreference {
        public String notificationType; // Email, In-App, Push
        public String category;
        public Boolean enabled;
    }
}
