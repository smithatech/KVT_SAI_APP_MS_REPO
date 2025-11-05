package com.kvcapp.event.repository;

import com.kvcapp.event.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
