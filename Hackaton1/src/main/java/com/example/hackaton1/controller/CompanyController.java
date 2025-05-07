package com.example.hackaton1.controller;


import com.example.hackaton1.model.entity.Company;
import com.example.hackaton1.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company savedCompany = companyService.createCompany(company);
        return ResponseEntity.ok(savedCompany);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(id, company);
        return ResponseEntity.ok(updatedCompany);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Company> updateCompanyStatus(@PathVariable Long id, @RequestParam boolean active) {
        Company company = companyService.updateCompanyStatus(id, active);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<Object> getCompanyConsumption(@PathVariable Long id) {
        // Este endpoint debería devolver un DTO con el consumo
        Object consumption = companyService.getCompanyConsumption(id);
        return ResponseEntity.ok(consumption);
    }
}