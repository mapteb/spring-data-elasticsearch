package rnd.elasticsearch.service;

import lombok.RequiredArgsConstructor;
import rnd.elasticsearch.model.DocumentEntity;
import rnd.elasticsearch.repository.DocumentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentEntity saveDocument(DocumentEntity document) {
        try {
            if (document.getId() == null || document.getId().isEmpty()) {
                document.setId(UUID.randomUUID().toString());
            }
            if (document.getCreatedDate() == null) {
                document.setCreatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
            }
            return documentRepository.save(document);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save document", e);
        }
    }

    public Optional<DocumentEntity> getDocumentById(String id) {
        try {
            return documentRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve document", e);
        }
    }

    public List<DocumentEntity> getAllDocuments() {
        try {
            Iterable<DocumentEntity> alldocs =  documentRepository.findAll();
            return StreamSupport.stream(alldocs.spliterator(), false)
                .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve documents", e);
        }
    }

    public List<DocumentEntity> searchByContent(String searchTerm) {
        try {
            return documentRepository.findByContentContaining(searchTerm);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search documents", e);
        }
    }

    public List<DocumentEntity> searchByTitle(String title) {
        try {
            return documentRepository.findByTitle(title);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search documents", e);
        }
    }

    public List<DocumentEntity> searchByAuthor(String author) {
        try {
            return documentRepository.findByAuthor(author);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search documents", e);
        }
    }

    public void deleteDocument(String id) {
        try {
            documentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete document", e);
        }
    }

    public void deleteAllDocuments() {
        try {
            documentRepository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete document", e);
        }
    }    
}