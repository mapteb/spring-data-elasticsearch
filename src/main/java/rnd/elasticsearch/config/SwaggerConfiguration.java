package rnd.elasticsearch.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    // Option 1: Group API to show only UserController
    @Bean
    public GroupedOpenApi empApi() {
        return GroupedOpenApi.builder()
                .group("emp-api")
                .displayName("Emp API")
                .pathsToMatch("/api/documents/**")
                .build();
    }
}