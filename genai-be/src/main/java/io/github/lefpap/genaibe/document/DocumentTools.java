package io.github.lefpap.genaibe.document;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentTools {

    private final DocumentService documentService;

    public DocumentTools(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Tool(
        name = "search_documents",
        description = "Searches documents based on a query and returns relevant results."
    )
    public List<Document> searchDocuments(@ToolParam(description = "The search query for finding relevant documents") String query) {
        return documentService.semanticSearch(query);
    }
}
