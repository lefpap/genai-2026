package io.github.lefpap.genaibe.chat.service;

import io.github.lefpap.genaibe.chat.model.ChatMessage;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Persists conversation history in the existing {@code chat_message} table so it
 * stays the single source of truth. Implementing {@link ChatMemory} directly keeps
 * {@link #add} append-only (the advisor calls it with only the new turn), which
 * preserves full history and stable row ids; {@link #get} returns just the recent
 * window that is replayed to the model. The {@code conversationId} is the thread id.
 */
@Component
public class ChatThreadMemory implements ChatMemory {

    private static final int MAX_MESSAGES = 20;

    private final ChatThreadService chatThreadService;

    public ChatThreadMemory(ChatThreadService chatThreadService) {
        this.chatThreadService = chatThreadService;
    }

    @Override
    @Transactional(readOnly = true)
    public @NonNull List<Message> get(@NonNull String conversationId) {
        UUID threadId = UUID.fromString(conversationId);
        List<ChatMessage> messages = chatThreadService.getChatThreadHistory(threadId);

        // Replay only the most recent window to the model.
        int from = Math.max(0, messages.size() - MAX_MESSAGES);
        return messages.subList(from, messages.size()).stream()
            .map(this::toAiMessage)
            .toList();
    }

    @Override
    @Transactional
    public void add(@NonNull String conversationId, List<Message> messages) {
        UUID threadId = UUID.fromString(conversationId);
        messages.stream()
            .filter(Predicate.not(m -> MessageType.SYSTEM.equals(m.getMessageType())))
            .forEach(m -> {
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setContent(m.getText());
                chatMessage.setRole(m.getMessageType().name());
                chatThreadService.createChatMessage(threadId, chatMessage);
            });
    }

    @Override
    @Transactional
    public void clear(@NonNull String conversationId) {
        UUID threadId = UUID.fromString(conversationId);
        chatThreadService.deleteChatThreadHistory(threadId);
    }

    private Message toAiMessage(ChatMessage message) {
        MessageType type = MessageType.valueOf(message.getRole());
        return switch (type) {
            case ASSISTANT -> new AssistantMessage(message.getContent());
            default -> new UserMessage(message.getContent());
        };
    }
}
