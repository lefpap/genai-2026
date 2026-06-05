package dev.ctrlspace.genai2026.genaibe.llm;

import dev.ctrlspace.genai2026.genaibe.llm.dto.ChatCompletionRequest;
import dev.ctrlspace.genai2026.genaibe.llm.dto.ChatCompletionResponse;
import org.springframework.stereotype.Service;

@Service
public class LLMChatClient {

    private final LLMClientRegistry registry;

    public LLMChatClient(LLMClientRegistry registry) {
        this.registry = registry;
    }

    public ChatCompletionResponse completion(String provider, ChatCompletionRequest request) {
        return registry.get(provider).post()
            .uri("/chat/completions")
            .body(request)
            .retrieve()
            .body(ChatCompletionResponse.class);
    }

}
