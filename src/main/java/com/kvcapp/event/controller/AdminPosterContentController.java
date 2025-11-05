package com.kvcapp.event.controller;

import com.kvcapp.event.dto.PosterContentDtos;
import com.kvcapp.event.entity.PosterContent;
import com.kvcapp.event.service.PosterContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminPosterContentController {
    private final PosterContentService posterContentService;

    public AdminPosterContentController(PosterContentService posterContentService) {
        this.posterContentService = posterContentService;
    }

    @PostMapping("/stalls/{stallId}/content")
    public ResponseEntity<?> addOrUpdateContent(@PathVariable String stallId, @RequestBody PosterContentDtos.NewPosterContent req) {
        try {
            PosterContent pc = new PosterContent();
            pc.setContentText(req.contentText);
            pc.setLanguageCode(req.languageCode);
            pc.setPosterMediaUrl(req.posterMediaUrl);
            return ResponseEntity.status(HttpStatus.CREATED).body(posterContentService.addOrUpdate(stallId, pc));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error","Stall not found"));
        }
    }

    @GetMapping("/stalls/{stallId}/content")
    public java.util.List<PosterContent> getStallContent(@PathVariable String stallId) {
        return posterContentService.listForStall(stallId);
    }
}
