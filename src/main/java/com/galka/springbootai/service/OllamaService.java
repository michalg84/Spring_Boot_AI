package com.galka.springbootai.service;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

    @Autowired
    private OllamaChatClient ollamaChatClient;

    public String call(String question) {
        return ollamaChatClient.call(question);
    }

    public ChatResponse call(Prompt prompt) {
        return ollamaChatClient.call(prompt);
    }
}
