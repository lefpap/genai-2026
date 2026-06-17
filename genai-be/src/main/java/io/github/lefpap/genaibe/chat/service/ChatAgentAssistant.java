package io.github.lefpap.genaibe.chat.service;

import io.github.lefpap.genaibe.document.tool.DocumentTools;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatAgentAssistant {

    private static final String SYSTEM_PROMPT = """
        You are a helpful assistant that can answer job posting related. When answering questions, you should use the
        information from the documents to provide accurate and relevant responses. If the answer is not found in the
        documents, you should respond with "I'm sorry, I don't have enough information to answer that question.".
        """;

    private final ChatClient chatClient;

    public ChatAgentAssistant(ChatClient.Builder chatBuilder, DocumentTools documentTools, ChatMemory chatMemory) {
        this.chatClient = chatBuilder
            .defaultAdvisors(AdvisorParams.toolCallingAdvisorAutoRegister(true))
            .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
            .defaultTools(documentTools)
            .defaultSystem(SYSTEM_PROMPT)
            .build();
    }

    public String chat(UUID threadId, String text) {
        return chatClient.prompt()
            .user(text)
            .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, threadId.toString()))
            .call()
            .content();
    }
}
