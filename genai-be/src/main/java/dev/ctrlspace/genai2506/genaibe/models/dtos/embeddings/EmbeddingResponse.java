package dev.ctrlspace.genai2506.genaibe.models.dtos.embeddings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddingResponse {

    private String object;
    private List<EmbeddingData> data;
    private String model;
    private EmbeddingUsage usage;

}
