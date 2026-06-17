package io.github.lefpap.genaibe.chat;

import io.github.lefpap.genaibe.document.DocumentTools;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{threadId}/chat")
public class ChatController {

    private static final String SYSTEM_PROMPT = """
        You are a helpful assistant that can answer job posting related. When answering questions, you should use the
        information from the documents to provide accurate and relevant responses. If the answer is not found in the 
        documents, you should respond with "I'm sorry, I don't have enough information to answer that question.".
        """;

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatBuilder, DocumentTools documentTools) {
        this.chatClient = chatBuilder
            .defaultAdvisors(AdvisorParams.toolCallingAdvisorAutoRegister(true))
            .defaultTools(documentTools)
            .defaultSystem(SYSTEM_PROMPT)
            .build();

    }

    @PostMapping("/messages")
    public ApiChatResponse message(@PathVariable String threadId, @RequestBody ApiChatRequest chatRequest) {
        String answer = chatClient.prompt()
            .user(chatRequest.message())
            .call()
            .content();

        return new ApiChatResponse(answer);
    }

    public record ApiChatRequest(
        String message
    ) {
    }

    public record ApiChatResponse(
        String answer
    ) {
    }
}
