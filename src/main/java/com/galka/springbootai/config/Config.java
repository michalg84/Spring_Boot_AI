package com.galka.springbootai.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
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

    @Bean
    public GroupedOpenApi usersGroup(@Value("3.0.2") String appVersion) {
        return GroupedOpenApi.builder().group("users")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                    return operation;
                })
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Users API").version(appVersion)))
                .packagesToScan("org.springdoc.demo.app2")
                .build();
    }
}
