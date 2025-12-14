package rnd.elasticsearch.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import rnd.elasticsearch.model.DocumentEntity;
import rnd.elasticsearch.service.DocumentService;

@SpringBootTest
@AutoConfigureMockMvc
class AccessControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DocumentService documentService;

    // @Autowired
    // private DocumentRepository documentRepository;

    @BeforeEach
    void setUp() {
        // Clean up user data
        documentService.deleteAllDocuments();
    }   

    @Test
    void createDocument_ShouldReturnCreatedDocument() throws Exception {
        // Given
        DocumentEntity doc = new DocumentEntity("one", "Sample Title", "Sample content", "John Doe", "2024-06-10T10:00:00");
        String docExpected = objectMapper.writeValueAsString(doc);

        // When & Then
        mockMvc.perform(post("/api/documents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doc)))
                .andExpect(status().isCreated())
                .andExpect(content().string(docExpected));
    }
}

