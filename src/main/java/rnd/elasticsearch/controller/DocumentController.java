package rnd.elasticsearch.controller;

import lombok.RequiredArgsConstructor;
import rnd.elasticsearch.model.DocumentEntity;
import rnd.elasticsearch.service.DocumentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentEntity> createDocument(@RequestBody DocumentEntity document) {
        DocumentEntity savedDocument = documentService.saveDocument(document);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentEntity> getDocument(@PathVariable String id) {
        return documentService.getDocumentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DocumentEntity>> getAllDocuments() {
        List<DocumentEntity> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DocumentEntity>> searchDocuments(
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        
        List<DocumentEntity> results;
        
        if (content != null && !content.isEmpty()) {
            results = documentService.searchByContent(content);
        } else if (title != null && !title.isEmpty()) {
            results = documentService.searchByTitle(title);
        } else if (author != null && !author.isEmpty()) {
            results = documentService.searchByAuthor(author);
        } else {
            results = documentService.getAllDocuments();
        }
        
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}