package io.github.lefpap.genaibe.document.api;

import io.github.lefpap.genaibe.document.model.Document;

public record ApiUploadPlainTextDocumentRequest(
    String title,
    String content
) {

    public Document toDocument() {
        Document document = new Document();
        document.setTitle(title);
        document.setContent(content);
        return document;
    }
}
