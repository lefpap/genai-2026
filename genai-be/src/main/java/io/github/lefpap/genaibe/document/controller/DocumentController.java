package io.github.lefpap.genaibe.document.controller;

import io.github.lefpap.genaibe.document.model.Document;
import io.github.lefpap.genaibe.document.service.DocumentService;
import io.github.lefpap.genaibe.document.api.ApiDocumentResponse;
import io.github.lefpap.genaibe.document.api.ApiUploadPlainTextDocumentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/plain")
    public ApiDocumentResponse uploadPlainText(@RequestBody ApiUploadPlainTextDocumentRequest request) {
        Document created = documentService.saveDocument(request.toDocument());
        return ApiDocumentResponse.from(created);
    }
}
