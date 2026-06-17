package io.github.lefpap.genaibe.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

    @NativeQuery("""
        SELECT d.*
        FROM document d
        ORDER BY d.embedding <-> CAST(:embedding AS vector)
        LIMIT :limit
        """)
    List<Document> findBySimilarity(float[] embedding, int limit);
}
