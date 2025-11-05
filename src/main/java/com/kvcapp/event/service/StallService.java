package com.kvcapp.event.service;

import com.kvcapp.event.entity.ModuleEntity;
import com.kvcapp.event.entity.Stall;
import com.kvcapp.event.repository.ModuleRepository;
import com.kvcapp.event.repository.StallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StallService {
    private final StallRepository stallRepository;
    private final ModuleRepository moduleRepository;

    public StallService(StallRepository stallRepository, ModuleRepository moduleRepository) {
        this.stallRepository = stallRepository;
        this.moduleRepository = moduleRepository;
    }

    @Transactional
    public Stall create(String moduleId, Stall stall) {
        ModuleEntity m = moduleRepository.findById(moduleId).orElseThrow();
        if (stall.getStallId() == null || stall.getStallId().isBlank()) {
            stall.setStallId(UUID.randomUUID().toString());
        }
        stall.setModule(m);
        return stallRepository.save(stall);
    }

    public java.util.Optional<Stall> findById(String id) { return stallRepository.findById(id); }

    @Transactional
    public Stall update(String id, Stall update) {
        Stall existing = stallRepository.findById(id).orElseThrow();
        existing.setName(update.getName());
        existing.setDescription(update.getDescription());
        existing.setLayout(update.getLayout());
        existing.setStallNumber(update.getStallNumber());
        return stallRepository.save(existing);
    }

    @Transactional
    public void delete(String id) { stallRepository.deleteById(id); }
}
