package com.kvcapp.event.repository;

import com.kvcapp.event.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, String> {}
