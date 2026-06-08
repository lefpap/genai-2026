package dev.ctrlspace.genai2026.genaibe.chat;

import dev.ctrlspace.genai2026.genaibe.llm.ChatCompletionRequest;
import dev.ctrlspace.genai2026.genaibe.llm.ChatCompletionResponse;
import dev.ctrlspace.genai2026.genaibe.llm.LLMChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{threadId}/chat")
public class ChatController {

    private final LLMChatClient chatClient;

    public ChatController(LLMChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/messages")
    public ApiChatResponse message(@PathVariable String threadId, @RequestBody ApiChatRequest chatRequest) {
        var userMessage = ChatCompletionRequest.ChatMessage.user(chatRequest.message());
        var completionRequest = new ChatCompletionRequest(
            chatRequest.model(),
            List.of(userMessage)
        );

        ChatCompletionResponse completion = chatClient.completion(completionRequest);
        String content = completion.choices().getFirst().message().content();
        return new ApiChatResponse(content);
    }

    public record ApiChatRequest(
        String model,
        String message
    ) {
    }

    public record ApiChatResponse(
        String answer
    ) {
    }
}
