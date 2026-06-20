package io.github.lefpap.genaibe.document.controller;

import io.github.lefpap.genaibe.document.api.ApiDocumentEnrichedResponse;
import io.github.lefpap.genaibe.document.api.ApiDocumentResponse;
import io.github.lefpap.genaibe.document.api.ApiUploadPlainTextDocumentRequest;
import io.github.lefpap.genaibe.document.model.Document;
import io.github.lefpap.genaibe.document.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public List<ApiDocumentResponse> listDocuments() {
        return documentService.getDocuments().stream()
                .map(DocumentController::toApiDocumentResponse)
                .toList();
    }

    @GetMapping("/{documentId}")
    public ApiDocumentEnrichedResponse getDocument(@PathVariable UUID documentId) {
        Document document = documentService.getDocument(documentId);
        return toApiDocumentEnrichedResponse(document);
    }

    @PostMapping("/upload/plain")
    public ApiDocumentResponse uploadPlainText(@RequestBody ApiUploadPlainTextDocumentRequest request) {
        Document created = documentService.saveDocument(request.toDocument());
        return toApiDocumentResponse(created);
    }

    private static ApiDocumentResponse toApiDocumentResponse(Document document) {
        return ApiDocumentResponse.builder()
                .withId(document.getId())
                .withTitle(document.getTitle())
                .withCreatedAt(document.getCreatedAt())
                .build();
    }

    private static ApiDocumentEnrichedResponse toApiDocumentEnrichedResponse(Document document) {
        return ApiDocumentEnrichedResponse.builder()
                .withId(document.getId())
                .withTitle(document.getTitle())
                .withContent(document.getContent())
                .withCreatedAt(document.getCreatedAt())
                .build();
    }
}
