package io.github.lefpap.genaibe.chat.service;

import io.github.lefpap.genaibe.chat.repository.ChatThreadRepository;
import io.github.lefpap.genaibe.chat.model.ChatThread;
import org.springframework.stereotype.Service;

@Service
public class ChatThreadService {

    private final ChatThreadRepository chatThreadRepository;

    public ChatThreadService(ChatThreadRepository chatThreadRepository) {
        this.chatThreadRepository = chatThreadRepository;
    }

    public ChatThread create(ChatThread chatThread) {
        return chatThreadRepository.save(chatThread);
    }
}
