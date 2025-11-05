package com.kvcapp.event.repository;

import com.kvcapp.event.entity.PosterContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosterContentRepository extends JpaRepository<PosterContent, String> {
    java.util.List<PosterContent> findByStall_StallId(String stallId);
}
