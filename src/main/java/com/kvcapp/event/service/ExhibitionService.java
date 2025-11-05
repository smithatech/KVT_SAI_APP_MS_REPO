package com.kvcapp.event.service;

import com.kvcapp.event.entity.Exhibition;
import com.kvcapp.event.entity.ModuleEntity;
import com.kvcapp.event.repository.ExhibitionRepository;
import com.kvcapp.event.repository.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ModuleRepository moduleRepository;

    public ExhibitionService(ExhibitionRepository exhibitionRepository, ModuleRepository moduleRepository) {
        this.exhibitionRepository = exhibitionRepository;
        this.moduleRepository = moduleRepository;
    }

    public List<Exhibition> findAll() { return exhibitionRepository.findAll(); }

    public Optional<Exhibition> findById(String id) { return exhibitionRepository.findById(id); }

    @Transactional
    public Exhibition create(Exhibition e) {
        if (e.getExhibitionId() == null || e.getExhibitionId().isBlank()) {
            e.setExhibitionId(UUID.randomUUID().toString());
        }
        return exhibitionRepository.save(e);
    }

    @Transactional
    public Exhibition update(String id, Exhibition update) {
        Exhibition existing = exhibitionRepository.findById(id).orElseThrow();
        existing.setName(update.getName());
        existing.setDescription(update.getDescription());
        existing.setStartDate(update.getStartDate());
        existing.setEndDate(update.getEndDate());
        existing.setLocation(update.getLocation());
        existing.setStatus(update.getStatus());
        return exhibitionRepository.save(existing);
    }

    @Transactional
    public void delete(String id) { exhibitionRepository.deleteById(id); }

    @Transactional
    public ModuleEntity addModule(String exhibitionId, ModuleEntity m) {
        Exhibition ex = exhibitionRepository.findById(exhibitionId).orElseThrow();
        if (m.getModuleId() == null || m.getModuleId().isBlank()) {
            m.setModuleId(UUID.randomUUID().toString());
        }
        m.setExhibition(ex);
        return moduleRepository.save(m);
    }
}
