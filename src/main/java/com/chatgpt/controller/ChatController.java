package com.chatgpt.controller;

import com.chatgpt.model.ChatRequest;
import com.chatgpt.model.ChatResponse;
import com.chatgpt.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.max-completions}")
    private int maxCompletions;

    @Value("${openai.temperature}")
    private double temperature;

    @Value("${openai.max_tokens}")
    private int maxTokens;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestParam("prompt") String prompt) {

        ChatRequest request = new ChatRequest(model,
                List.of(new Message("user", prompt)),
                maxCompletions,
                temperature,
                maxTokens);

        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        return response;
    }
}
