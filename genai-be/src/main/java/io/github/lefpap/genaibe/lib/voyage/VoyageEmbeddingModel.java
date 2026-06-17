package io.github.lefpap.genaibe.lib.voyage;

import org.jspecify.annotations.NonNull;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Spring AI {@link EmbeddingModel} backed by the Voyage AI embeddings API.
 *
 * <p>Voyage is OpenAI-compatible for the embedding vectors, but its {@code usage}
 * object omits {@code prompt_tokens}, which the bundled openai-java SDK (used by
 * Spring AI's {@code OpenAiEmbeddingModel}) treats as required and rejects. We
 * therefore call Voyage with a plain {@link RestClient} and map the result into
 * Spring AI's abstractions, so the rest of the app keeps depending only on
 * {@link EmbeddingModel}.
 */
@Component
public class VoyageEmbeddingModel implements EmbeddingModel {

    private final RestClient restClient;
    private final VoyageEmbeddingProperties properties;

    public VoyageEmbeddingModel(RestClient voyageRestClient, VoyageEmbeddingProperties properties) {
        this.properties = properties;
        this.restClient = voyageRestClient;
    }

    @Override
    public @NonNull EmbeddingResponse call(EmbeddingRequest request) {
        var options = request.getOptions();
        String model = (options != null && options.getModel() != null) ? options.getModel() : properties.model();

        VoyageResponse response = restClient.post()
            .uri("/embeddings")
            .body(new VoyageRequest(model, request.getInstructions()))
            .retrieve()
            .body(VoyageResponse.class);

        List<Embedding> embeddings = Objects.requireNonNull(response).data().stream()
            .sorted(Comparator.comparingInt(VoyageResponse.Data::index))
            .map(data -> new Embedding(data.embedding(), data.index()))
            .toList();

        return new EmbeddingResponse(embeddings);
    }

    @Override
    public float @NonNull [] embed(Document document) {
        String text = Objects.requireNonNull(document.getText());
        return embed(text);
    }

    private record VoyageRequest(String model, List<String> input) {
    }

    private record VoyageResponse(List<Data> data) {
        record Data(int index, float[] embedding) {
        }
    }
}
