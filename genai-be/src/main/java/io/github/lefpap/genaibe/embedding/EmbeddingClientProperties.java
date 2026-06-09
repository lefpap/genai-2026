package io.github.lefpap.genaibe.embedding;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("embeddings.client")
public record EmbeddingClientProperties(
    String baseUrl,
    String apiKey,
    String defaultModel
) {}
