package dev.ctrlspace.genai2026.genaibe.llm.dto;

import java.util.List;

public record ChatCompletionRequest(String model, List<ChatMessage> messages) {

    public record ChatMessage(
        String role,
        String content
    ) {
    }
}
