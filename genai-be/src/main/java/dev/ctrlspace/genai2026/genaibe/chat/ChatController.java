package dev.ctrlspace.genai2026.genaibe.chat;

import dev.ctrlspace.genai2026.genaibe.agent.FunnyInsultingAgent;
import dev.ctrlspace.genai2026.genaibe.llm.LLMChatClient;
import dev.ctrlspace.genai2026.genaibe.llm.dto.ChatCompletionRequest;
import dev.ctrlspace.genai2026.genaibe.llm.dto.ChatCompletionResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{threadId}/chat")
public class ChatController {

    private final LLMChatClient chatClient;
    private final FunnyInsultingAgent insultingAgent;

    public ChatController(LLMChatClient chatClient, FunnyInsultingAgent insultingAgent) {
        this.chatClient = chatClient;
        this.insultingAgent = insultingAgent;
    }

    @PostMapping("/messages")
    public ChatCompletionResponse message(@PathVariable String threadId, @RequestBody ApiChatRequest chatRequest) {
        var userMessage = new ChatCompletionRequest.ChatMessage("user", chatRequest.userPrompt());
        var completionRequest = new ChatCompletionRequest(
            chatRequest.model(),
            List.of(userMessage)
        );
        return chatClient.completion(chatRequest.provider(), completionRequest);
    }

    @PostMapping("/agents/insulting-agent")
    public ChatCompletionResponse insultingAgent(@PathVariable String threadId, @RequestBody ApiChatAgentRequest agentRequest) {
        return insultingAgent.chat(agentRequest.userPrompt());
    }

    public record ApiChatRequest(
        String provider,
        String model,
        String userPrompt
    ) {
    }

    public record ApiChatAgentRequest(
        String userPrompt
    ) {
    }

    public record ApiChatResponse(
        String message
    ) {
    }
}
