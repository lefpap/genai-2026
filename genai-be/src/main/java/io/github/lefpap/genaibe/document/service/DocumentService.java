package io.github.lefpap.genaibe.document.service;

import io.github.lefpap.genaibe.document.model.Document;
import io.github.lefpap.genaibe.document.repository.DocumentRepository;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DocumentService {

    private final EmbeddingModel embeddingModel;
    private final DocumentRepository documentRepository;

    public DocumentService(EmbeddingModel embeddingModel, DocumentRepository documentRepository) {
        this.embeddingModel = embeddingModel;
        this.documentRepository = documentRepository;
    }

    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocument(UUID id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Document not found with id: " + id));
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
