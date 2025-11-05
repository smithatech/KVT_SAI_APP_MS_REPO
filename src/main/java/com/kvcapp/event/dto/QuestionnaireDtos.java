package com.kvcapp.event.dto;

import java.util.List;

public class QuestionnaireDtos {
    public static class NewQuestionnaire {
        public String name;
        public String description;
        public String status; // Draft, Active, Archived
        public java.util.List<Question> questions;
    }
    public static class Question {
        public String questionText;
        public String answerType; // MultipleChoice, FreeText, Rating
        public java.util.List<String> options;
    }
}
