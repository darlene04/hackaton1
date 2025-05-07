package com.example.hackaton1.controller;

import com.example.hackaton1.model.entity.CompanyRestriction;
import com.example.hackaton1.service.RestrictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
public class RestrictionController {

    private final RestrictionService restrictionService;

    public RestrictionController(RestrictionService restrictionService) {
        this.restrictionService = restrictionService;
    }

    @PostMapping
    public ResponseEntity<CompanyRestriction> createRestriction(@RequestBody CompanyRestriction restriction) {
        CompanyRestriction savedRestriction = restrictionService.createRestriction(restriction);
        return ResponseEntity.ok(savedRestriction);
    }

    @GetMapping
    public ResponseEntity<List<CompanyRestriction>> getAllRestrictions() {
        List<CompanyRestriction> restrictions = restrictionService.getAllRestrictions();
        return ResponseEntity.ok(restrictions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyRestriction> updateRestriction(
            @PathVariable Long id,
            @RequestBody CompanyRestriction restriction) {
        CompanyRestriction updatedRestriction = restrictionService.updateRestriction(id, restriction);
        return ResponseEntity.ok(updatedRestriction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestriction(@PathVariable Long id) {
        restrictionService.deleteRestriction(id);
        return ResponseEntity.noContent().build();
    }
}