package io.github.lefpap.genaibe.document;

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
    public ApiDocumentResponse uploadPlainText(@RequestBody UploadPlainTextDocumentRequest request) {
        Document document = new Document();
        document.setTitle(request.title());
        document.setContent(request.content());

        Document created = documentService.saveDocument(document);
        return ApiDocumentResponse.builder()
            .withId(created.getId())
            .withTitle(created.getTitle())
            .withCreatedAt(created.getCreatedAt())
            .build();
    }
}
