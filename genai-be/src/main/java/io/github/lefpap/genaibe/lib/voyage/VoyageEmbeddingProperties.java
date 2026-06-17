package io.github.lefpap.genaibe.lib.voyage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("voyage")
public record VoyageEmbeddingProperties(
    String baseUrl,
    String apiKey,
    String model
) {}
