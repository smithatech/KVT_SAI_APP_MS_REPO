package com.kvcapp.event.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "question_item")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @Column(length = 2000)
    private String questionText;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType = AnswerType.FreeText;

    @Column(length = 2000)
    private String optionsCsv; // stored as comma-separated values

    public enum AnswerType { MultipleChoice, FreeText, Rating }

    public Question() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Questionnaire getQuestionnaire() { return questionnaire; }
    public void setQuestionnaire(Questionnaire questionnaire) { this.questionnaire = questionnaire; }
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public AnswerType getAnswerType() { return answerType; }
    public void setAnswerType(AnswerType answerType) { this.answerType = answerType; }
    public String getOptionsCsv() { return optionsCsv; }
    public void setOptionsCsv(String optionsCsv) { this.optionsCsv = optionsCsv; }
}
