package io.github.lefpap.genaibe.chat.controller;

import io.github.lefpap.genaibe.chat.api.*;
import io.github.lefpap.genaibe.chat.model.ChatThread;
import io.github.lefpap.genaibe.chat.service.ChatAgentAssistant;
import io.github.lefpap.genaibe.chat.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/threads")
public class ChatController {

    private final ChatService chatService;
    private final ChatAgentAssistant chatAgentAssistant;

    public ChatController(ChatService chatService, ChatAgentAssistant chatAgentAssistant) {
        this.chatService = chatService;
        this.chatAgentAssistant = chatAgentAssistant;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApiChatThreadResponse> listChatThreads() {
        List<ChatThread> chatThreads = chatService.getChatThreads();
        return chatThreads.stream()
            .map(thread -> ApiChatThreadResponse.builder()
                .withId(thread.getId())
                .withTitle(thread.getTitle())
                .withCreatedAt(thread.getCreatedAt())
                .withUpdatedAt(thread.getUpdatedAt())
                .build())
            .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiChatThreadResponse createChatThread(@RequestBody ApiCreateChatThreadRequest request) {
        ChatThread chatThread = new ChatThread();
        chatThread.setTitle(request.title());
        ChatThread created = chatService.createChatThread(chatThread);
        return ApiChatThreadResponse.builder()
            .withId(created.getId())
            .withTitle(created.getTitle())
            .withCreatedAt(created.getCreatedAt())
            .withUpdatedAt(created.getUpdatedAt())
            .build();
    }

    @DeleteMapping("/{threadId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChatThread(@PathVariable UUID threadId) {
        chatService.deleteChatThread(threadId);
    }

    @PostMapping("/{threadId}/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiChatMessageResponse sendChatMessage(@PathVariable UUID threadId, @RequestBody ApiChatMessageRequest chatRequest) {
        String answer = chatAgentAssistant.chat(threadId, chatRequest.message());
        return new ApiChatMessageResponse(answer);
    }

    @GetMapping("/{threadId}/messages")
    @ResponseStatus(HttpStatus.OK)
    public List<ApiThreadMessageResponse> listChatThreadHistory(@PathVariable UUID threadId) {
        return chatService.getChatThreadHistory(threadId).stream()
            .map(message -> new ApiThreadMessageResponse(
                message.getId(),
                message.getRole(),
                message.getContent(),
                message.getCreatedAt()))
            .toList();
    }


    @DeleteMapping("/{threadId}/messages")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearChatMessageHistory(@PathVariable UUID threadId) {
        chatService.deleteChatThreadHistory(threadId);
    }
}
