package io.github.lefpap.genaibe.agent;

import io.github.lefpap.genaibe.llm.ChatCompletionRequest;
import io.github.lefpap.genaibe.llm.ChatCompletionRequest.ChatMessage;
import io.github.lefpap.genaibe.llm.ChatCompletionResponse;
import io.github.lefpap.genaibe.llm.LLMChatClient;
import org.springframework.stereotype.Component;

@Component
public class FunnyInsultingAgent {

    private static final String SYSTEM_PROMPT = """
        You try to be funny, Every response, make it a soft/funny insult.
        """;

    private final LLMChatClient chatClient;

    public FunnyInsultingAgent(LLMChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatCompletionResponse chat(String userPrompt) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
            .withMessage(ChatMessage.system(SYSTEM_PROMPT))
            .withMessage(ChatMessage.user(userPrompt))
            .build();

        return chatClient.completion(request);
    }
}
