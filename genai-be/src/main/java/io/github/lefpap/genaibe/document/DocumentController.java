package io.github.lefpap.genaibe.document;

import io.github.lefpap.genaibe.embedding.EmbeddingClient;
import io.github.lefpap.genaibe.embedding.EmbeddingRequest;
import io.github.lefpap.genaibe.embedding.EmbeddingResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentRepository documentRepository;
    private final EmbeddingClient embeddingClient;

    public DocumentController(DocumentRepository documentRepository, EmbeddingClient embeddingClient) {
        this.documentRepository = documentRepository;
        this.embeddingClient = embeddingClient;
    }

    @PostMapping("/plain")
    public ApiDocumentResponse uploadPlainText(@RequestBody UploadPlainTextDocumentRequest request) {

        List<Double> embedding = createEmbedding(request.content());
        Document document = new Document();
        document.setTitle(request.title());
        document.setContent(request.content());
        document.setEmbedding(embedding);

        Document created = documentRepository.save(document);
        return ApiDocumentResponse.builder()
            .withId(created.getId())
            .withTitle(created.getTitle())
            .withCreatedAt(created.getCreatedAt())
            .build();
    }

    private List<Double> createEmbedding(String input) {
        EmbeddingRequest req = EmbeddingRequest.builder()
            .withInput(input)
            .build();

        EmbeddingResponse res = embeddingClient.embedding(req);
        return res.data().getFirst().embedding();
    }
}
