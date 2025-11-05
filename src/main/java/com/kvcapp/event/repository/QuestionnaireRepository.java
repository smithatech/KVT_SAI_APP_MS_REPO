package com.kvcapp.event.repository;

import com.kvcapp.event.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, String> {}
