package com.example.hackaton1.service;

import com.example.hackaton1.model.entity.AIRequest;
import com.example.hackaton1.model.enums.ModelType;
import com.example.hackaton1.repository.AIRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class AIService {

    private final AIRequestRepository aiRequestRepository;

    public AIService(AIRequestRepository aiRequestRepository) {
        this.aiRequestRepository = aiRequestRepository;
    }

    @Transactional
    public AIRequest processChatRequest(String prompt) {
        // Simulación sin integración real con IA
        AIRequest request = new AIRequest();
        request.setQuery(prompt);
        request.setResponse("This is a simulated response for: " + prompt);
        request.setModelUsed(ModelType.OPENAI_GPT4);
        request.setTokensConsumed(prompt.length() / 4); // Estimación aproximada
        request.setSuccess(true);

        return aiRequestRepository.save(request);
    }

    @Transactional
    public AIRequest processCompletionRequest(String prompt) {
        // Simulación sin integración real con IA
        AIRequest request = new AIRequest();
        request.setQuery(prompt);
        request.setResponse("Simulated completion for: " + prompt);
        request.setModelUsed(ModelType.META_LLAMA);
        request.setTokensConsumed(prompt.length() / 4);
        request.setSuccess(true);

        return aiRequestRepository.save(request);
    }

    @Transactional
    public AIRequest processMultimodalRequest(String prompt, String imageUrl) {
        // Simulación sin integración real con IA
        AIRequest request = new AIRequest();
        request.setQuery(prompt);
        request.setResponse("Simulated multimodal response for image: " + imageUrl + " with prompt: " + prompt);
        request.setModelUsed(ModelType.MULTIMODAL_CLIP);
        request.setTokensConsumed((prompt.length() / 4) + 100); // +100 por la imagen
        request.setFileName(imageUrl != null ? imageUrl.substring(imageUrl.lastIndexOf('/') + 1) : null);
        request.setSuccess(true);

        return aiRequestRepository.save(request);
    }

    public List<String> getAvailableModels() {
        // Lista hardcodeada sin integración real
        return Arrays.asList(
                "OPENAI_GPT4",
                "META_LLAMA_2",
                "DEEPSEEK_CHAT",
                "CLIP"
        );
    }

    public List<AIRequest> getRequestHistory() {
        // Obtener las últimas 10 solicitudes como ejemplo
        return aiRequestRepository.findTop10ByOrderByTimestampDesc();
    }
}