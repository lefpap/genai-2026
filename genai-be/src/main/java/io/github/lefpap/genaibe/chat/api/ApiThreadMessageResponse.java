package io.github.lefpap.genaibe.chat.api;

import java.time.Instant;
import java.util.UUID;

public record ApiThreadMessageResponse(
    UUID id,
    String role,
    String content,
    Instant createdAt
) {
}
