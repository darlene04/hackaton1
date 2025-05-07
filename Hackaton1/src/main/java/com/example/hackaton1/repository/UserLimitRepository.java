package com.example.hackaton1.repository;


import com.example.hackaton1.model.entity.User;
import com.example.hackaton1.model.entity.UserLimit;
import com.example.hackaton1.model.enums.ModelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {

    List<UserLimit> findByUserId(Long userId);

    Optional<UserLimit> findByUserIdAndModelType(Long userId, ModelType modelType);

    List<UserLimit> findByUser(User user);
}