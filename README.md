## Spring Data Elasticsearch

This project is a helloworld style application to persist a JSON doc to Elasticsearch.


### Features

- elasticsearch:9.2.2 docker container
- Java 21
- Spring Boot 4
- REST APIs to store and query JSON docs

### Run Elasticsearch container
```
podman run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" docker.elastic.co/elasticsearch/elasticsearch:9.2.2

To confirm the above is running, wait for a minute and access
curl http://localhost:9200
```

### Build and Run

.\gradlew bootRun

### Test Cases

.\gradlew test

### Swagger doc to test all the APIs
```
http://localhost:8080/swagger-ui/index.html
```

