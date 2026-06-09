package io.github.lefpap.genaibe.llm;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration for any number of OpenAI-compatible completion providers.
 * <p>
 * The map key (e.g. {@code gemini}) is the logical provider name used to look up a client.
 * {@code defaultProvider} (optional) names the provider used when a caller does not specify one.
 */
@ConfigurationProperties("llms.client")
public record LLMChatClientProperties(
    String baseUrl,
    String apiKey,
    String defaultModel
) { }
