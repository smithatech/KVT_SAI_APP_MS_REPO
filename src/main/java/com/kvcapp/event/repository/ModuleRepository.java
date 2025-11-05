package com.kvcapp.event.repository;

import com.kvcapp.event.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<ModuleEntity, String> {}
