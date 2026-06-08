package dev.ctrlspace.genai2026.genaibe.llm;

import org.springframework.web.client.RestClient;

import java.util.Map;

/**
 * Resolves the pre-configured {@link RestClient} (base URL + auth baked in) plus the optional
 * default model for a given logical provider name. One client is built per entry declared under
 * {@code llms.providers}.
 */
public class LLMChatClientRegistry {

    private final Map<String, ProviderClient> clients;
    private final String defaultProvider;

    public LLMChatClientRegistry(Map<String, ProviderClient> clients, String defaultProvider) {
        this.clients = clients;
        this.defaultProvider = defaultProvider;
        if (defaultProvider != null && !clients.containsKey(defaultProvider)) {
            throw new IllegalArgumentException(
                "Default llm provider '%s' is not configured. Available: %s"
                    .formatted(defaultProvider, clients.keySet()));
        }
    }

    public ProviderClient get(String provider) {
        ProviderClient client = clients.get(provider);
        if (client == null) {
            throw new IllegalArgumentException(
                "No configured llm provider found for '%s'. Available: %s"
                    .formatted(provider, clients.keySet()));
        }
        return client;
    }

    public ProviderClient getDefault() {
        if (defaultProvider == null) {
            throw new IllegalStateException(
                "No default llm provider configured. Set 'llms.default-provider'. Available: %s"
                    .formatted(clients.keySet()));
        }
        return get(defaultProvider);
    }

    /**
     * A resolved provider: its pre-configured {@link RestClient} and optional default model
     * (used when a caller does not specify one). {@code defaultModel} may be {@code null}.
     */
    public record ProviderClient(RestClient client, String defaultModel) {
    }
}
