package com.galka.springbootai.config;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("spring.ai.ollama.url")
    String apiUrl;
    @Value("spring.ai.ollama.chat.options.model")
    String model;

    @Bean
    public OllamaChatClient ollamaChatClient() {
        return  new OllamaChatClient(new OllamaApi(apiUrl))
                .withDefaultOptions(OllamaOptions.create()
                        .withModel(OllamaOptions.DEFAULT_MODEL)
                        .withTemperature(0.9f));
    }
}
