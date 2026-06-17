package io.github.lefpap.genaibe.chat.controller;

import io.github.lefpap.genaibe.chat.service.ChatThreadService;
import io.github.lefpap.genaibe.chat.api.ApiChatThreadResponse;
import io.github.lefpap.genaibe.chat.api.ApiCreateChatThreadRequest;
import io.github.lefpap.genaibe.chat.model.ChatThread;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/threads")
public class ChatThreadController {


    private final ChatThreadService chatThreadService;

    public ChatThreadController(ChatThreadService chatThreadService) {
        this.chatThreadService = chatThreadService;
    }

    @PostMapping()
    public ApiChatThreadResponse createChatThread(@RequestBody ApiCreateChatThreadRequest request) {
        // Convert chat thread
        ChatThread chatThread = new ChatThread();
        chatThread.setTitle(request.title());
        ChatThread created = chatThreadService.create(chatThread);
        // Convert to api response
        return ApiChatThreadResponse.builder()
            .withId(created.getId())
            .withTitle(created.getTitle())
            .withCreatedAt(created.getCreatedAt())
            .withUpdatedAt(created.getUpdatedAt())
            .build();
    }
}
