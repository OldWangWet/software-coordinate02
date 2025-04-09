package com.oddfar.campus.business.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeepseekServiceImpl {

    private final WebClient webClient;
    private static final String DEEPSEEK_API_URL = "https://api.deepseek.com";
    private static final String AUTH_TOKEN = "Bearer sk-9104bb50f5204a99a2d3ad2c710f5575"; // 建议改为从配置读取

    public DeepseekServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(DEEPSEEK_API_URL)
                .defaultHeader("Authorization", AUTH_TOKEN)
                .build();
    }

    public Mono<String> sendChatCompletion(String userMessage) {
        // 构造请求体 - Java 8 兼容方式
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", userMessage);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "deepseek-chat");
        requestBody.put("messages", Collections.singletonList(message));
        requestBody.put("stream", false);

        return webClient.post()
                .uri("/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}