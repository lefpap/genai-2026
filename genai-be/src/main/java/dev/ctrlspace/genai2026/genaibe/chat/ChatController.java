package dev.ctrlspace.genai2026.genaibe.chat;

import dev.ctrlspace.genai2026.genaibe.llm.LLMChatClient;
import dev.ctrlspace.genai2026.genaibe.llm.dto.ChatCompletionRequest;
import dev.ctrlspace.genai2026.genaibe.llm.dto.ChatCompletionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/{threadId}/chat")
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private final LLMChatClient chatClient;

    public ChatController(LLMChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/messages")
    public ChatCompletionResponse message(@PathVariable String threadId, @RequestBody ApiChatRequest chatRequest) {
        log.debug("Chat request on thread {}: {}", threadId, chatRequest);

        if (chatRequest.textInput() == null || chatRequest.textInput().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "textInput must not be blank");
        }

        var userMessage = new ChatCompletionRequest.ChatMessage("user", chatRequest.textInput());
        var completionRequest = new ChatCompletionRequest(
            chatRequest.model(),
            List.of(userMessage)
        );
        return chatClient.completion(chatRequest.provider(), completionRequest);
    }

    public record ApiChatRequest(
        String provider,
        String model,
        String textInput
    ) {
    }

    public record ApiChatResponse(
        String message
    ) {
    }
}
