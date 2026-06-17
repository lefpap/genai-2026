package io.github.lefpap.genaibe.document.api;

import io.github.lefpap.genaibe.document.model.Document;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true, setterPrefix = "with")
public record ApiDocumentResponse(
    UUID id,
    String title,
    Instant createdAt
) {

    public static ApiDocumentResponse from(Document document) {
        return ApiDocumentResponse.builder()
            .withId(document.getId())
            .withTitle(document.getTitle())
            .withCreatedAt(document.getCreatedAt())
            .build();
    }
}