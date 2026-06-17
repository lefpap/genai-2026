package io.github.lefpap.genaibe.chat.api;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true, setterPrefix = "with")
public record ApiChatThreadResponse(
    UUID id,
    String title,
    Instant createdAt,
    Instant updatedAt
) {
}
