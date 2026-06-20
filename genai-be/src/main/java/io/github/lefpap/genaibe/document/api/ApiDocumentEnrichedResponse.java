package io.github.lefpap.genaibe.document.api;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true, setterPrefix = "with")
public record ApiDocumentEnrichedResponse(
    UUID id,
    String title,
    String content,
    Instant createdAt
) { }