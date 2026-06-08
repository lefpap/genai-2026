package dev.ctrlspace.genai2026.genaibe.llm;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Builds one pre-configured {@link RestClient} (base URL + auth header) per provider
 * declared under {@code llms.providers} and exposes them through an {@link LLMChatClientRegistry}.
 */
@Configuration
@EnableConfigurationProperties(LLMChatClientProperties.class)
public class LLMChatClientConfig {

    @Bean
    public LLMChatClientRegistry llmClientRegistry(LLMChatClientProperties properties, RestClient.Builder builder) {
        Map<String, LLMChatClientRegistry.ProviderClient> clients = properties.providers().entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> buildClient(builder, entry.getValue())
            ));
        return new LLMChatClientRegistry(clients, properties.defaultProvider());
    }

    private LLMChatClientRegistry.ProviderClient buildClient(RestClient.Builder builder, LLMChatClientProperties.Provider provider) {
        // clone() so each provider gets its own base URL/auth without mutating the shared builder
        RestClient client = builder.clone()
            .baseUrl(provider.baseUrl())
            .defaultHeaders(headers -> headers.setBearerAuth(provider.apiKey()))
            .build();
        return new LLMChatClientRegistry.ProviderClient(client, provider.defaultModel());
    }
}
