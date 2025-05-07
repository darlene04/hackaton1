package com.example.hackaton1.service;

import com.example.hackaton1.model.entity.CompanyRestriction;
import com.example.hackaton1.repository.CompanyRestrictionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestrictionService {

    private final CompanyRestrictionRepository restrictionRepository;

    public RestrictionService(CompanyRestrictionRepository restrictionRepository) {
        this.restrictionRepository = restrictionRepository;
    }

    @Transactional
    public CompanyRestriction createRestriction(CompanyRestriction restriction) {
        // Validación básica
        if (restriction.getModelType() == null) {
            throw new IllegalArgumentException("Model type is required");
        }

        return restrictionRepository.save(restriction);
    }

    public List<CompanyRestriction> getAllRestrictions() {
        return restrictionRepository.findAll();
    }

    @Transactional
    public CompanyRestriction updateRestriction(Long id, CompanyRestriction restrictionDetails) {
        CompanyRestriction restriction = getRestrictionById(id);

        // Actualizar campos permitidos
        if (restrictionDetails.getModelType() != null) {
            restriction.setModelType(restrictionDetails.getModelType());
        }
        if (restrictionDetails.getRequestLimit() > 0) {
            restriction.setRequestLimit(restrictionDetails.getRequestLimit());
        }
        // Continuar con otros campos...

        return restrictionRepository.save(restriction);
    }

    @Transactional
    public void deleteRestriction(Long id) {
        CompanyRestriction restriction = getRestrictionById(id);
        restrictionRepository.delete(restriction);
    }

    public CompanyRestriction getRestrictionById(Long id) {
        return restrictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restriction not found with id: " + id));
    }
}