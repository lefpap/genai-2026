package io.github.lefpap.genaibe.chat.service;

import io.github.lefpap.genaibe.chat.model.ChatMessage;
import io.github.lefpap.genaibe.chat.model.ChatThread;
import io.github.lefpap.genaibe.chat.repository.ChatMessageRepository;
import io.github.lefpap.genaibe.chat.repository.ChatThreadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ChatService {

    private final ChatThreadRepository chatThreadRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatThreadRepository chatThreadRepository, ChatMessageRepository chatMessageRepository) {
        this.chatThreadRepository = chatThreadRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    public List<ChatThread> getChatThreads() {
        return chatThreadRepository.findAll();
    }

    public ChatThread createChatThread(ChatThread chatThread) {
        return chatThreadRepository.save(chatThread);
    }

    public void deleteChatThread(UUID threadId) {
        chatThreadRepository.deleteById(threadId);
    }

    public ChatMessage createChatMessage(UUID threadId, ChatMessage chatMessage) {
        ChatThread chatThread = chatThreadRepository.findById(threadId)
            .orElseThrow(() -> new NoSuchElementException("Thread with id " + threadId + " does not exist"));
        chatMessage.setThread(chatThread);
        return chatMessageRepository.save(chatMessage);
    }


    public List<ChatMessage> getChatThreadHistory(UUID threadId) {
        if (!chatThreadRepository.existsById(threadId)) {
            throw new NoSuchElementException("No thread found with id: " + threadId);
        }
        return chatMessageRepository.findByThread_IdOrderByCreatedAtAsc(threadId);
    }

    @Transactional
    public void deleteChatThreadHistory(UUID threadId) {
        chatMessageRepository.deleteByThread_Id(threadId);
    }


}
