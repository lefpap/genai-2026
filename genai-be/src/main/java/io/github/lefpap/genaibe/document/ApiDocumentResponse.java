package io.github.lefpap.genaibe.document;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true, setterPrefix = "with")
public record ApiDocumentResponse(
    UUID id,
    String title,
    Instant createdAt
) { }