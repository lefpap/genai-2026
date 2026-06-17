package io.github.lefpap.genaibe.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class FunnyInsultingAgent {

    private static final String SYSTEM_PROMPT = """
        You try to be funny, Every response, make it a soft/funny insult.
        """;

    private final ChatClient chatClient;

    public FunnyInsultingAgent(ChatClient.Builder chatClient) {
        this.chatClient = chatClient
            .defaultSystem(SYSTEM_PROMPT)
            .build();
    }

    public String chat(String text) {
        return chatClient.prompt()
            .user(text)
            .call()
            .content();
    }
}
