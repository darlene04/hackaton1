package com.example.hackaton1.repository;


import com.example.hackaton1.model.entity.AIRequest;
import com.example.hackaton1.model.entity.User;
import com.example.hackaton1.model.enums.ModelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AIRequestRepository extends JpaRepository<AIRequest, Long> {

    List<AIRequest> findByUser(User user);

    List<AIRequest> findByUserAndTimestampBetween(User user, LocalDateTime start, LocalDateTime end);

    List<AIRequest> findByModelUsed(ModelType modelType);

    @Query("SELECT ar FROM AIRequest ar WHERE ar.user.company.id = :companyId")
    List<AIRequest> findByCompanyId(Long companyId);

    @Query("SELECT ar FROM AIRequest ar WHERE ar.user.id = :userId ORDER BY ar.timestamp DESC")
    List<AIRequest> findTop10ByUserIdOrderByTimestampDesc(Long userId);

    @Query("SELECT SUM(ar.tokensConsumed) FROM AIRequest ar WHERE ar.user.id = :userId")
    Long sumTokensConsumedByUserId(Long userId);

    Page<AIRequest> findByUserOrderByTimestampDesc(User user, Pageable pageable);

    List<AIRequest> findTop10ByOrderByTimestampDesc();
}