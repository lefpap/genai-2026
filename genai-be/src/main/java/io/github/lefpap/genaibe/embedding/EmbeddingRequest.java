package io.github.lefpap.genaibe.embedding;


import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder(toBuilder = true, setterPrefix = "with")
public record EmbeddingRequest(
    String model,
    @Singular("inputs") List<String> input
) {
}
