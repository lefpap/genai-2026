package dev.ctrlspace.genai2026.genaibe.llm;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Builds one pre-configured {@link RestClient} (base URL + auth header) per provider
 * declared under {@code llms.providers} and exposes them through an {@link LLMClientRegistry}.
 */
@Configuration
@EnableConfigurationProperties(LLMClientProperties.class)
public class LLMClientConfig {

    @Bean
    public LLMClientRegistry llmClientRegistry(LLMClientProperties properties, RestClient.Builder builder) {
        Map<String, RestClient> clients = properties.providers().entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> buildClient(builder, entry.getValue())
            ));
        return new LLMClientRegistry(clients);
    }

    private RestClient buildClient(RestClient.Builder builder, LLMClientProperties.Provider provider) {
        // clone() so each provider gets its own base URL/auth without mutating the shared builder
        return builder.clone()
            .baseUrl(provider.baseUrl())
            .defaultHeaders(headers -> headers.setBearerAuth(provider.apiKey()))
            .build();
    }
}
