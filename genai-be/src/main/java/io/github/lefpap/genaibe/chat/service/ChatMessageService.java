package io.github.lefpap.genaibe.chat.service;

import io.github.lefpap.genaibe.chat.repository.ChatMessageRepository;
import io.github.lefpap.genaibe.chat.repository.ChatThreadRepository;
import io.github.lefpap.genaibe.chat.model.ChatMessage;
import io.github.lefpap.genaibe.chat.model.ChatThread;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ChatMessageService {

    private final ChatThreadRepository chatThreadRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatThreadRepository chatThreadRepository, ChatMessageRepository chatMessageRepository) {
        this.chatThreadRepository = chatThreadRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage create(ChatMessage chatMessage, UUID threadId) {
        ChatThread chatThread = chatThreadRepository.findById(threadId)
            .orElseThrow(() -> new NoSuchElementException("No thread found with id: " + threadId));
        chatMessage.setThread(chatThread);
        return chatMessageRepository.save(chatMessage);
    }
}
