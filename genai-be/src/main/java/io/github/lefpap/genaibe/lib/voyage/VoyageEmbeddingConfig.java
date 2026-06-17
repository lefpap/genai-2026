package io.github.lefpap.genaibe.lib.voyage;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(VoyageEmbeddingProperties.class)
public class VoyageEmbeddingConfig {

    @Bean
    RestClient voyageRestClient(RestClient.Builder restClientBuilder, VoyageEmbeddingProperties properties) {
        return restClientBuilder.clone()
            .baseUrl(properties.baseUrl())
            .defaultHeaders(headers -> headers.setBearerAuth(properties.apiKey()))
            .build();
    }
}
