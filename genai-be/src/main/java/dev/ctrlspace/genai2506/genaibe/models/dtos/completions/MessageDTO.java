package dev.ctrlspace.genai2506.genaibe.models.dtos.completions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String role;
    private List<ContentPart> content;



}
