package com.kvcapp.event.controller;

import com.kvcapp.event.dto.ModuleDtos;
import com.kvcapp.event.dto.StallDtos;
import com.kvcapp.event.entity.ModuleEntity;
import com.kvcapp.event.entity.Stall;
import com.kvcapp.event.service.ModuleService;
import com.kvcapp.event.service.StallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminModuleAndStallController {
    private final ModuleService moduleService;
    private final StallService stallService;

    public AdminModuleAndStallController(ModuleService moduleService, StallService stallService) {
        this.moduleService = moduleService;
        this.stallService = stallService;
    }

    @PutMapping("/modules/{moduleId}")
    public ResponseEntity<?> updateModule(@PathVariable String moduleId, @RequestBody ModuleDtos.UpdateModule req) {
        try {
            ModuleEntity m = new ModuleEntity();
            m.setName(req.name);
            m.setDescription(req.description);
            m.setAssignedTeamId(req.assignedTeamId);
            return ResponseEntity.ok(moduleService.update(moduleId, m));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Module not found"));
        }
    }

    @DeleteMapping("/modules/{moduleId}")
    public ResponseEntity<?> deleteModule(@PathVariable String moduleId) {
        moduleService.delete(moduleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/modules/{moduleId}/stalls")
    public ResponseEntity<?> createStall(@PathVariable String moduleId, @RequestBody StallDtos.NewStall req) {
        try {
            Stall s = new Stall();
            s.setName(req.name);
            s.setDescription(req.description);
            s.setStallNumber(req.stallNumber);
            s.setLayout(req.layout);
            return ResponseEntity.status(HttpStatus.CREATED).body(stallService.create(moduleId, s));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Module not found"));
        }
    }

    @PutMapping("/stalls/{stallId}")
    public ResponseEntity<?> updateStall(@PathVariable String stallId, @RequestBody StallDtos.UpdateStall req) {
        try {
            Stall s = new Stall();
            s.setName(req.name);
            s.setDescription(req.description);
            s.setStallNumber(req.stallNumber);
            s.setLayout(req.layout);
            return ResponseEntity.ok(stallService.update(stallId, s));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Stall not found"));
        }
    }

    @DeleteMapping("/stalls/{stallId}")
    public ResponseEntity<?> deleteStall(@PathVariable String stallId) {
        stallService.delete(stallId);
        return ResponseEntity.noContent().build();
    }
}
