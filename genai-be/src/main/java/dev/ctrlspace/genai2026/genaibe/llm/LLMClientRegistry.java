package dev.ctrlspace.genai2026.genaibe.llm;

import org.springframework.web.client.RestClient;

import java.util.Map;

/**
 * Resolves the pre-configured {@link RestClient} (base URL + auth baked in) for a given
 * logical provider name. One client is built per entry declared under {@code llms.providers}.
 */
public class LLMClientRegistry {

    private final Map<String, RestClient> clients;

    public LLMClientRegistry(Map<String, RestClient> clients) {
        this.clients = clients;
    }

    public RestClient get(String provider) {
        RestClient client = clients.get(provider);
        if (client == null) {
            throw new IllegalArgumentException(
                "No configured llm provider found for '%s'. Available: %s"
                    .formatted(provider, clients.keySet()));
        }
        return client;
    }
}
