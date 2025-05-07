package com.example.hackaton1.service;


import com.example.hackaton1.model.entity.User;
import com.example.hackaton1.model.entity.UserLimit;
import com.example.hackaton1.repository.UserLimitRepository;
import com.example.hackaton1.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserLimitRepository userLimitRepository;

    public UserService(UserRepository userRepository, UserLimitRepository userLimitRepository) {
        this.userRepository = userRepository;
        this.userLimitRepository = userLimitRepository;
    }

    @Transactional
    public User createUser(User user) {
        // Validación básica
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Valid email is required");
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);

        // Actualizar campos permitidos
        if (userDetails.getUsername() != null) {
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }
        // Continuar con otros campos...

        return userRepository.save(user);
    }

    @Transactional
    public UserLimit addUserLimit(Long userId, UserLimit limit) {
        User user = getUserById(userId);
        limit.setUser(user);
        return userLimitRepository.save(limit);
    }

    public Object getUserConsumption(Long id) {
        User user = getUserById(id);

        // Simulación de datos de consumo
        return new Object() {
            public final Long userId = user.getId();
            public final String username = user.getUsername();
            public final Integer totalRequests = 500;
            public final Integer usedRequests = 230;
            public final Integer remainingRequests = 270;
            public final Integer totalTokens = 200000;
            public final Integer usedTokens = 85000;
        };
    }
}