package com.kvcapp.event.controller;

import com.kvcapp.event.dto.QuestionnaireDtos;
import com.kvcapp.event.entity.Question;
import com.kvcapp.event.entity.Questionnaire;
import com.kvcapp.event.service.QuestionnaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminQuestionnaireController {
    private final QuestionnaireService questionnaireService;

    public AdminQuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @PostMapping("/questionnaires")
    public ResponseEntity<?> create(@RequestBody QuestionnaireDtos.NewQuestionnaire req) {
        Questionnaire q = new Questionnaire();
        q.setName(req.name);
        q.setDescription(req.description);
        if (req.status != null) q.setStatus(Questionnaire.Status.valueOf(req.status));
        java.util.List<Question> items = new java.util.ArrayList<>();
        if (req.questions != null) {
            for (QuestionnaireDtos.Question i : req.questions) {
                Question item = new Question();
                item.setQuestionText(i.questionText);
                if (i.answerType != null) item.setAnswerType(Question.AnswerType.valueOf(i.answerType));
                if (i.options != null && !i.options.isEmpty()) {
                    item.setOptionsCsv(String.join(",", i.options));
                }
                items.add(item);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(questionnaireService.create(q, items));
    }

    @GetMapping("/questionnaires")
    public java.util.List<Questionnaire> list() {
        return questionnaireService.findAll();
    }

    @PutMapping("/questionnaires/{questionnaireId}")
    public ResponseEntity<?> update(@PathVariable String questionnaireId, @RequestBody QuestionnaireDtos.NewQuestionnaire req) {
        Questionnaire q = new Questionnaire();
        q.setName(req.name);
        q.setDescription(req.description);
        if (req.status != null) q.setStatus(Questionnaire.Status.valueOf(req.status));
        java.util.List<Question> items = new java.util.ArrayList<>();
        if (req.questions != null) {
            for (QuestionnaireDtos.Question i : req.questions) {
                Question item = new Question();
                item.setQuestionText(i.questionText);
                if (i.answerType != null) item.setAnswerType(Question.AnswerType.valueOf(i.answerType));
                if (i.options != null && !i.options.isEmpty()) {
                    item.setOptionsCsv(String.join(",", i.options));
                }
                items.add(item);
            }
        }
        try {
            return ResponseEntity.ok(questionnaireService.update(questionnaireId, q, items));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Questionnaire not found"));
        }
    }
}
