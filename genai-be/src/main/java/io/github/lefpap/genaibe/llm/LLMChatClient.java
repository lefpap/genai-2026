package io.github.lefpap.genaibe.llm;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class LLMChatClient {

    private final RestClient restClient;
    private final LLMChatClientProperties properties;

    public LLMChatClient(RestClient llmChatRestClient, LLMChatClientProperties properties) {
        this.restClient = llmChatRestClient;
        this.properties = properties;
    }

    public ChatCompletionResponse completion(ChatCompletionRequest request) {
        ChatCompletionRequest effectiveRequest = request.toBuilder()
            .withModel(Objects.requireNonNullElseGet(request.model(), properties::defaultModel))
            .build();

        return restClient.post()
            .uri("/chat/completions")
            .body(effectiveRequest)
            .retrieve()
            .body(ChatCompletionResponse.class);
    }

}
