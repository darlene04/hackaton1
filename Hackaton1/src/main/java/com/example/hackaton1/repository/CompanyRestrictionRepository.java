package com.example.hackaton1.repository;


import com.example.hackaton1.model.entity.CompanyRestriction;
import com.example.hackaton1.model.enums.ModelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRestrictionRepository extends JpaRepository<CompanyRestriction, Long> {

    List<CompanyRestriction> findByCompanyId(Long companyId);

    List<CompanyRestriction> findByCompanyIdAndModelType(Long companyId, ModelType modelType);

    boolean existsByCompanyIdAndModelType(Long companyId, ModelType modelType);
}