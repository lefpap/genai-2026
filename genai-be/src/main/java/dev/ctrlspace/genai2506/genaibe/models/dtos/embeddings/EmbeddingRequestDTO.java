package dev.ctrlspace.genai2506.genaibe.models.dtos.embeddings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddingRequestDTO {

    private List<String> input;
    private String model;

}
