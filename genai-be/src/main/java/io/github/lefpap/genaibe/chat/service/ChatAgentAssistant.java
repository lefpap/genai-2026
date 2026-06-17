package io.github.lefpap.genaibe.chat.service;

import io.github.lefpap.genaibe.document.tool.DocumentTools;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatAgentAssistant {

    private static final String SYSTEM_PROMPT = """
        You are a helpful assistant that can answer job posting related. When answering questions, you should use the
        information from the documents to provide accurate and relevant responses. If the answer is not found in the 
        documents, you should respond with "I'm sorry, I don't have enough information to answer that question.".
        """;

    private final ChatClient chatClient;

    public ChatAgentAssistant(ChatClient.Builder chatBuilder, DocumentTools documentTools) {
        this.chatClient = chatBuilder
            .defaultAdvisors(AdvisorParams.toolCallingAdvisorAutoRegister(true))
            .defaultTools(documentTools)
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
