package dev.ctrlspace.genai2026.genaibe.llm.dto;

import java.util.List;

public record ChatCompletionRequest(String model, List<ChatMessage> messages) {

    public record ChatMessage(
        String role,
        String content
    ) {

        public static ChatMessage system(String content) {
            return new ChatMessage("system", content);
        }

        public static ChatMessage user(String content) {
            return new ChatMessage("user", content);
        }

        public static ChatMessage assistant(String content) {
            return new ChatMessage("assistant", content);
        }
    }
}
