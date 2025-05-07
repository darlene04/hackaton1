package com.example.hackaton1.controller;


import com.example.hackaton1.model.entity.AIRequest;
import com.example.hackaton1.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public ResponseEntity<AIRequest> chat(@RequestBody String prompt) {
        // Simulación sin integración real
        AIRequest request = aiService.processChatRequest(prompt);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/completion")
    public ResponseEntity<AIRequest> completion(@RequestBody String prompt) {
        // Simulación sin integración real
        AIRequest request = aiService.processCompletionRequest(prompt);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/multimodal")
    public ResponseEntity<AIRequest> multimodal(
            @RequestBody String prompt,
            @RequestParam(required = false) String imageUrl) {
        // Simulación sin integración real
        AIRequest request = aiService.processMultimodalRequest(prompt, imageUrl);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/models")
    public ResponseEntity<List<String>> getAvailableModels() {
        // Devuelve lista hardcodeada sin integración
        List<String> models = aiService.getAvailableModels();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/history")
    public ResponseEntity<List<AIRequest>> getRequestHistory() {
        List<AIRequest> history = aiService.getRequestHistory();
        return ResponseEntity.ok(history);
    }
}