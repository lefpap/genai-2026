package dev.ctrlspace.genai2506.genaibe.models.dtos.embeddings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddingData {

    private String object;
    private List<Double> embedding;
    private int index;
    private String text;

}
