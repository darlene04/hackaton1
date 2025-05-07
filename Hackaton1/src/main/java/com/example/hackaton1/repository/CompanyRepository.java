package com.example.hackaton1.repository;


import com.example.hackaton1.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByRuc(String ruc);

    List<Company> findByActive(boolean active);

    @Query("SELECT c FROM Company c WHERE c.name LIKE %:name%")
    List<Company> searchByName(String name);

    boolean existsByRuc(String ruc);
}