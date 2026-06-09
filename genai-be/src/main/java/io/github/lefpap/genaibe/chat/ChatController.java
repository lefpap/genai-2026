package io.github.lefpap.genaibe.chat;

import io.github.lefpap.genaibe.llm.ChatCompletionRequest;
import io.github.lefpap.genaibe.llm.ChatCompletionResponse;
import io.github.lefpap.genaibe.llm.LLMChatClient;
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
