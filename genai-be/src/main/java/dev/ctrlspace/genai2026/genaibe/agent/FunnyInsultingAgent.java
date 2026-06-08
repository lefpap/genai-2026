package dev.ctrlspace.genai2026.genaibe.agent;

import dev.ctrlspace.genai2026.genaibe.llm.LLMChatClient;
import dev.ctrlspace.genai2026.genaibe.llm.ChatCompletionRequest;
import dev.ctrlspace.genai2026.genaibe.llm.ChatCompletionRequest.ChatMessage;
import dev.ctrlspace.genai2026.genaibe.llm.ChatCompletionResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FunnyInsultingAgent {

    private static final String PROVIDER = "google";
    private static final String MODEL = "gemini-3.1-flash-lite";
    private static final String SYSTEM_PROMPT = """
        You try to be funny, Every response, make it a soft/funny insult.
        """;

    private final LLMChatClient chatClient;

    public FunnyInsultingAgent(LLMChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatCompletionResponse chat(String userPrompt) {
        var messages = List.of(
            ChatMessage.system(SYSTEM_PROMPT),
            ChatMessage.user(userPrompt)
        );

        ChatCompletionRequest request = new ChatCompletionRequest(MODEL, messages);
        return chatClient.completion(PROVIDER, request);
    }
}
