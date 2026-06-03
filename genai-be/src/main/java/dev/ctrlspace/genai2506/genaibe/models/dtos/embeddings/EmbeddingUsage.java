package dev.ctrlspace.genai2506.genaibe.models.dtos.embeddings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddingUsage {

    @JsonProperty("total_tokens")
    private int totalTokens;

}
