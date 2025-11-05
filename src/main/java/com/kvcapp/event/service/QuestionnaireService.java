package com.kvcapp.event.service;

import com.kvcapp.event.entity.Question;
import com.kvcapp.event.entity.Questionnaire;
import com.kvcapp.event.repository.QuestionRepository;
import com.kvcapp.event.repository.QuestionnaireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;

    public QuestionnaireService(QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
    }

    public List<Questionnaire> findAll() { return questionnaireRepository.findAll(); }

    @Transactional
    public Questionnaire create(Questionnaire q, java.util.List<Question> items) {
        if (q.getQuestionnaireId() == null || q.getQuestionnaireId().isBlank()) {
            q.setQuestionnaireId(UUID.randomUUID().toString());
        }
        Questionnaire saved = questionnaireRepository.save(q);
        if (items != null) {
            for (Question it : items) {
                it.setQuestionnaire(saved);
                questionRepository.save(it);
            }
        }
        return saved;
    }

    @Transactional
    public Questionnaire update(String id, Questionnaire update, java.util.List<Question> items) {
        Questionnaire existing = questionnaireRepository.findById(id).orElseThrow();
        existing.setName(update.getName());
        existing.setDescription(update.getDescription());
        existing.setStatus(update.getStatus());
        Questionnaire saved = questionnaireRepository.save(existing);
        if (items != null) {
            // simple strategy: clear and re-add
            for (Question q : questionRepository.findAll()) {
                if (q.getQuestionnaire() != null && id.equals(q.getQuestionnaire().getQuestionnaireId())) {
                    questionRepository.delete(q);
                }
            }
            for (Question it : items) {
                it.setId(null);
                it.setQuestionnaire(saved);
                questionRepository.save(it);
            }
        }
        return saved;
    }
}
