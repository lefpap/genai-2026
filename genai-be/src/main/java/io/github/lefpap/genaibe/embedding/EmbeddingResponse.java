package io.github.lefpap.genaibe.embedding;

import tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

public record EmbeddingResponse(
    List<Data> data,
    String model,
    Usage usage
) {

    public record Data(
        String object,
        List<Double> embedding,
        String index,
        String text
    ) {
    }

    @JsonNaming(SnakeCaseStrategy.class)
    public record Usage(
        Integer totalTokens
    ) {
    }
}
