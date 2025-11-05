package com.kvcapp.event.service;

import com.kvcapp.event.entity.PosterContent;
import com.kvcapp.event.entity.Stall;
import com.kvcapp.event.repository.PosterContentRepository;
import com.kvcapp.event.repository.StallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PosterContentService {
    private final PosterContentRepository repo;
    private final StallRepository stallRepository;
    public PosterContentService(PosterContentRepository repo, StallRepository stallRepository) {
        this.repo = repo; this.stallRepository = stallRepository;
    }

    @Transactional
    public PosterContent addOrUpdate(String stallId, PosterContent pc) {
        Stall stall = stallRepository.findById(stallId).orElseThrow();
        if (pc.getContentId() == null || pc.getContentId().isBlank()) {
            pc.setContentId(UUID.randomUUID().toString());
        }
        pc.setStall(stall);
        return repo.save(pc);
    }

    public List<PosterContent> listForStall(String stallId) {
        return repo.findByStall_StallId(stallId);
    }
}
