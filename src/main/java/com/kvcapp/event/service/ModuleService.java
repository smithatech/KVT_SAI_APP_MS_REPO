package com.kvcapp.event.service;

import com.kvcapp.event.entity.ModuleEntity;
import com.kvcapp.event.repository.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public java.util.Optional<ModuleEntity> findById(String id) { return moduleRepository.findById(id); }

    @Transactional
    public ModuleEntity update(String id, ModuleEntity update) {
        ModuleEntity existing = moduleRepository.findById(id).orElseThrow();
        existing.setName(update.getName());
        existing.setDescription(update.getDescription());
        existing.setAssignedTeamId(update.getAssignedTeamId());
        return moduleRepository.save(existing);
    }

    @Transactional
    public void delete(String id) { moduleRepository.deleteById(id); }
}
