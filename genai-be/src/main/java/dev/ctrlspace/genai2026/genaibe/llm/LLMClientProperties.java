package dev.ctrlspace.genai2026.genaibe.llm;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Configuration for any number of OpenAI-compatible completion providers.
 * <p>
 * The map key (e.g. {@code gemini}) is the logical provider name used to look up a client.
 */
@ConfigurationProperties("llms")
public record LLMClientProperties(Map<String, Provider> providers) {

    public record Provider(
        String baseUrl,
        String apiKey
    ) {
    }
}
