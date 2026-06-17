package io.github.lefpap.genaibe.chat.controller;

import io.github.lefpap.genaibe.chat.service.ChatAgentAssistant;
import io.github.lefpap.genaibe.chat.service.ChatMessageService;
import io.github.lefpap.genaibe.chat.api.ApiChatMessageRequest;
import io.github.lefpap.genaibe.chat.api.ApiChatMessageResponse;
import io.github.lefpap.genaibe.chat.model.ChatMessage;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/{threadId}/chat")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final ChatAgentAssistant chatAgentAssistant;

    public ChatMessageController(ChatMessageService chatMessageService, ChatAgentAssistant chatAgentAssistant) {
        this.chatMessageService = chatMessageService;
        this.chatAgentAssistant = chatAgentAssistant;
    }

    @PostMapping("/messages")
    public ApiChatMessageResponse message(@PathVariable UUID threadId, @RequestBody ApiChatMessageRequest chatRequest) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(chatRequest.message());
        ChatMessage created = chatMessageService.create(chatMessage, threadId);

        String answer = chatAgentAssistant.chat(created.getContent());
        return new ApiChatMessageResponse(answer);
    }
}
