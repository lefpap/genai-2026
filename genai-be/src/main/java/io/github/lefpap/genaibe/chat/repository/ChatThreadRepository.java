package io.github.lefpap.genaibe.chat.repository;

import io.github.lefpap.genaibe.chat.model.ChatThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatThreadRepository extends JpaRepository<ChatThread, UUID> {
}
