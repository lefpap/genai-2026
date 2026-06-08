package dev.ctrlspace.genai2026.genaibe.llm;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true, setterPrefix = "with")
public record ChatCompletionRequest(String model, List<ChatMessage> messages) {

    public static ChatCompletionRequest messages(List<ChatMessage> messages) {
        return new ChatCompletionRequest(null, messages);
    }

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
    }
}
