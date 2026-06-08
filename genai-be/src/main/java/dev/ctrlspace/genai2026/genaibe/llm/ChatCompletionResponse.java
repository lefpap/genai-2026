package dev.ctrlspace.genai2026.genaibe.llm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Map;

/**
 * OpenAI-compatible {@code chat.completion} response.
 *
 * <p>Provider-specific extras (e.g. Google's {@code thought_signature}) are kept untyped in
 * {@link Message#extraContent()} so this DTO stays flexible across providers.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ChatCompletionResponse(
    String id,
    String object,
    Long created,
    String model,
    List<Choice> choices,
    Usage usage
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(SnakeCaseStrategy.class)
    public record Choice(
        Integer index,
        String finishReason,
        Message message
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(SnakeCaseStrategy.class)
    public record Message(
        String role,
        String content,
        Map<String, Object> extraContent
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(SnakeCaseStrategy.class)
    public record Usage(
        Integer promptTokens,
        Integer completionTokens,
        Integer totalTokens
    ) {
    }
}
