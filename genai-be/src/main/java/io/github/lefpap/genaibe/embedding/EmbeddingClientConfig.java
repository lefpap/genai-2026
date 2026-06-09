package io.github.lefpap.genaibe.embedding;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(EmbeddingClientProperties.class)
public class EmbeddingClientConfig {

    @Bean
    public RestClient embeddingRestClient(RestClient.Builder restClientBuilder, EmbeddingClientProperties properties) {
        return restClientBuilder.clone()
            .baseUrl(properties.baseUrl())
            .defaultHeaders(headers -> headers.setBearerAuth(properties.apiKey()))
            .build();
    }

}
