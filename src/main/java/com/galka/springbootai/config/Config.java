package com.galka.springbootai.config;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(name = "application", value = "classpath:application.properties")
public class Config {
    @Value("${spring.ai.ollama.base-url}")
    String apiUrl;
    @Value("${spring.ai.ollama.chat.options.model}")
    String model;
    @Value("${spring.ai.ollama.chat.options.temperature}")
    Float temperature;

    @Bean
    public OllamaChatClient ollamaChatClient() {
        return new OllamaChatClient(new OllamaApi(apiUrl))
                .withDefaultOptions(OllamaOptions.create()
                        .withModel(model)
                        .withTemperature(temperature));
    }
}
