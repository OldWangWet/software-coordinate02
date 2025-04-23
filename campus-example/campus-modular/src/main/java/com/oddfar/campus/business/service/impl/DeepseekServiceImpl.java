package com.oddfar.campus.business.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import java.util.List;

@Data
class ChatCompletionResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    private String system_fingerprint;
}

@Data
class Choice {
    private int index;
    private Message message;
    private Object logprobs;  // 可能是 null
    private String finish_reason;
}

@Data
class Message {
    private String role;
    private String content;
}

@Data
class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
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
        String tmp="你是一个审核员，判断以下内容是否可以发在校园墙上。内容只要不涉及暴力色情，不违反中国法律的都要通过。你的回复只包含两行，第一行只有一个数字，0表示不通过，1表示通过。第二行给出理由：\n";
        userMessage=tmp+userMessage;
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
                .bodyToMono(ChatCompletionResponse.class)  // 改为反序列化为对象
                .map(response -> {
                    // 提取第一个 choice 的 message content
                    if (response.getChoices() != null && !response.getChoices().isEmpty()) {
                        return response.getChoices().get(0).getMessage().getContent();
                    }
                    return "No response content";
                });
    }
}