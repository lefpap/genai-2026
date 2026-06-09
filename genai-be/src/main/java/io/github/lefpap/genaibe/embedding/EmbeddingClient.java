package io.github.lefpap.genaibe.embedding;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class EmbeddingClient {

    private final RestClient restClient;
    private final EmbeddingClientProperties properties;

    public EmbeddingClient(RestClient embeddingRestClient, EmbeddingClientProperties properties) {
        this.restClient = embeddingRestClient;
        this.properties = properties;
    }

    public EmbeddingResponse embedding(EmbeddingRequest request) {
        EmbeddingRequest effectiveRequest = request.toBuilder()
            .withModel(Objects.requireNonNullElseGet(request.model(), properties::defaultModel))
            .build();

        return restClient.post()
            .uri("/embeddings")
            .body(effectiveRequest)
            .retrieve()
            .body(EmbeddingResponse.class);
    }
}
