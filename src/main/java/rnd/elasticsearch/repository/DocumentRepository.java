
package rnd.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import rnd.elasticsearch.model.DocumentEntity;



public interface DocumentRepository extends ElasticsearchRepository<DocumentEntity, String>{

	    List<DocumentEntity> findByTitle(String title);
    
    List<DocumentEntity> findByAuthor(String author);
    
    List<DocumentEntity> findByContentContaining(String content);

}