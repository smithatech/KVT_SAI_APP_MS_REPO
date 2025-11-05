package com.kvcapp.event.controller;

import com.kvcapp.event.dto.ExhibitionDtos;
import com.kvcapp.event.dto.ModuleDtos;
import com.kvcapp.event.entity.Exhibition;
import com.kvcapp.event.entity.ModuleEntity;
import com.kvcapp.event.service.ExhibitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminExhibitionController {
    private final ExhibitionService exhibitionService;

    public AdminExhibitionController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    // Create Exhibition
    @PostMapping("/exhibitions")
    public ResponseEntity<Exhibition> createExhibition(@RequestBody ExhibitionDtos.NewExhibition req) {
        Exhibition e = new Exhibition();
        e.setName(req.name);
        e.setDescription(req.description);
        e.setStartDate(req.startDate);
        e.setEndDate(req.endDate);
        e.setLocation(req.location);
        if (req.status != null) {
            e.setStatus(Exhibition.Status.valueOf(req.status));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(exhibitionService.create(e));
    }

    // List all
    @GetMapping("/exhibitions")
    public List<Exhibition> listExhibitions() { return exhibitionService.findAll(); }

    // Get by id
    @GetMapping("/exhibitions/{exhibitionId}")
    public ResponseEntity<?> getExhibition(@PathVariable String exhibitionId) {
        return exhibitionService.findById(exhibitionId).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Exhibition not found")));
    }

    // Update
    @PutMapping("/exhibitions/{exhibitionId}")
    public ResponseEntity<?> updateExhibition(@PathVariable String exhibitionId, @RequestBody ExhibitionDtos.UpdateExhibition req) {
        try {
            Exhibition e = new Exhibition();
            e.setName(req.name);
            e.setDescription(req.description);
            e.setStartDate(req.startDate);
            e.setEndDate(req.endDate);
            e.setLocation(req.location);
            if (req.status != null) e.setStatus(Exhibition.Status.valueOf(req.status));
            return ResponseEntity.ok(exhibitionService.update(exhibitionId, e));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", "Exhibition not found"));
        }
    }

    // Delete
    @DeleteMapping("/exhibitions/{exhibitionId}")
    public ResponseEntity<?> deleteExhibition(@PathVariable String exhibitionId) {
        exhibitionService.delete(exhibitionId);
        return ResponseEntity.noContent().build();
    }

    // Create Module under exhibition
    @PostMapping("/exhibitions/{exhibitionId}/modules")
    public ResponseEntity<?> createModule(@PathVariable String exhibitionId, @RequestBody ModuleDtos.NewModule req) {
        try {
            ModuleEntity m = new ModuleEntity();
            m.setName(req.name);
            m.setDescription(req.description);
            m.setAssignedTeamId(req.assignedTeamId);
            return ResponseEntity.status(HttpStatus.CREATED).body(exhibitionService.addModule(exhibitionId, m));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", "Exhibition not found"));
        }
    }
}
