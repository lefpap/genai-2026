package io.github.lefpap.genaibe.chat.repository;

import io.github.lefpap.genaibe.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {

    List<ChatMessage> findByThread_IdOrderByCreatedAtAsc(UUID threadId);

    void deleteByThread_Id(UUID threadId);
}
