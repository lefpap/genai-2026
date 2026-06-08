package dev.ctrlspace.genai2026.genaibe.llm;

import dev.ctrlspace.genai2026.genaibe.llm.LLMChatClientRegistry.ProviderClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LLMChatClient {

    private final LLMChatClientRegistry registry;

    public LLMChatClient(LLMChatClientRegistry registry) {
        this.registry = registry;
    }

    /**
     * Completion against the configured default provider ({@code llms.default-provider}).
     */
    public ChatCompletionResponse completion(ChatCompletionRequest request) {
        return send(registry.getDefault(), request);
    }

    /**
     * Completion against an explicitly named provider.
     */
    public ChatCompletionResponse completion(String provider, ChatCompletionRequest request) {
        return send(registry.get(provider), request);
    }

    private ChatCompletionResponse send(ProviderClient provider, ChatCompletionRequest request) {
        ChatCompletionRequest effectiveRequest = request.toBuilder()
            .withModel(Objects.requireNonNullElseGet(request.model(), provider::defaultModel))
            .build();

        return provider.client().post()
            .uri("/chat/completions")
            .body(effectiveRequest)
            .retrieve()
            .body(ChatCompletionResponse.class);
    }

}
