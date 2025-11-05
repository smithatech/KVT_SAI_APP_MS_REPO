package com.kvcapp.event.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Questionnaire {
    @Id
    private String questionnaireId;
    private String name;
    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.Draft;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    public enum Status { Draft, Active, Archived }

    public Questionnaire() {}
    public Questionnaire(String id) { this.questionnaireId = id; }

    public String getQuestionnaireId() { return questionnaireId; }
    public void setQuestionnaireId(String questionnaireId) { this.questionnaireId = questionnaireId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
}
