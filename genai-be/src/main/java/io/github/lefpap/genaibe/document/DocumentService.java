package io.github.lefpap.genaibe.document;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final EmbeddingModel embeddingModel;
    private final DocumentRepository documentRepository;

    public DocumentService(EmbeddingModel embeddingModel, DocumentRepository documentRepository) {
        this.embeddingModel = embeddingModel;
        this.documentRepository = documentRepository;
    }

    public Document saveDocument(Document document) {
        float[] embedding = embeddingModel.embed(document.getContent());
        document.setEmbedding(embedding);
        return documentRepository.save(document);
    }

    public List<Document> semanticSearch(String text) {
        float[] embedding = embeddingModel.embed(text);
        return documentRepository.findBySimilarity(embedding, 5);
    }
}
