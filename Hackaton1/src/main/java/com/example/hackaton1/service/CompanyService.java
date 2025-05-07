package com.example.hackaton1.service;


import com.example.hackaton1.model.entity.Company;
import com.example.hackaton1.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Company createCompany(Company company) {
        // Validación básica
        if (company.getName() == null || company.getName().isEmpty()) {
            throw new IllegalArgumentException("Company name is required");
        }

        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

    @Transactional
    public Company updateCompany(Long id, Company companyDetails) {
        Company company = getCompanyById(id);

        // Actualizar solo los campos permitidos
        if (companyDetails.getName() != null) {
            company.setName(companyDetails.getName());
        }
        if (companyDetails.getRuc() != null) {
            company.setRuc(companyDetails.getRuc());
        }
        // Continuar con otros campos...

        return companyRepository.save(company);
    }

    @Transactional
    public Company updateCompanyStatus(Long id, boolean active) {
        Company company = getCompanyById(id);
        company.setActive(active);
        return companyRepository.save(company);
    }

    public Object getCompanyConsumption(Long id) {
        Company company = getCompanyById(id);

        // Simulación de datos de consumo
        return new Object() {
            public final Long companyId = company.getId();
            public final String companyName = company.getName();
            public final Integer totalRequests = 1500;
            public final Integer usedRequests = 780;
            public final Integer remainingRequests = 720;
            public final Integer totalTokens = 1000000;
            public final Integer usedTokens = 450000;
        };
    }
}