package dev.ctrlspace.genai2506.genaibe.services;

import dev.ctrlspace.genai2506.genaibe.models.dtos.completions.ChatCompletionResponse;
import dev.ctrlspace.genai2506.genaibe.models.dtos.completions.ContentPart;
import dev.ctrlspace.genai2506.genaibe.models.dtos.completions.MessageDTO;
import dev.ctrlspace.genai2506.genaibe.models.dtos.completions.RequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CompletionsApiService {

    private String apiKey;


    public CompletionsApiService(@Value("${llms.groq.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCompletion(String url, String model, String userQuestion) {
        RestTemplate restTemplate = new RestTemplate();

        // 2. Build headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        String systemPrompt = "You try to be funny, Every response, make it a soft/funny insult.";

        RequestDTO requestBody = new RequestDTO(
                model,
                List.of(
                        new MessageDTO(
                                "system",
                                List.of(
                                        new ContentPart(
                                                "text",
                                                systemPrompt))),
                    new MessageDTO(
                            "user",
                            List.of(
                                    new ContentPart(
                                            "text",
                                            userQuestion)))
        ));



        HttpEntity request = new HttpEntity<>(requestBody, headers);
        ResponseEntity response = restTemplate.postForEntity(
                url,
                request,
                ChatCompletionResponse.class);

        ChatCompletionResponse responseBody = (ChatCompletionResponse) response.getBody();

        return responseBody.getChoices().get(0).getMessage().getContent();
    }
}
