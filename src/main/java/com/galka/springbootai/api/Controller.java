package com.galka.springbootai.api;

import com.galka.springbootai.model.Model;
import com.galka.springbootai.service.OllamaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/ollama")
public class Controller {
    Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private OllamaService ollamaService;

    @Operation(summary = "Ask simple text question")
    @ApiResponse(responseCode = "200", description = "Ollama LLM model response")
    @PostMapping("/asksimplequestion")
    public String askSimpleQuestion(@RequestBody @Parameter(description = "Request model") Model request) {
        log.info(request.toString());
        if (!StringUtils.hasText(request.getQuestion())) {
            return "Please provide valid question";
        }
        String response = ollamaService.call(request.getQuestion());
        log.info(response);
        return response;
    }

    @PostMapping("/askquestion")
    public ChatResponse askQuestion(@RequestBody OllamaApi.ChatRequest request) {
        log.info(request.toString());
        String questions = request.messages().stream()
                .map(OllamaApi.Message::content)
                .collect(Collectors.joining());
        OllamaOptions ollamaOptions = OllamaOptions.create().withModel(request.model());
        Prompt prompt = new Prompt(questions, ollamaOptions);
        ChatResponse chatResponse = ollamaService.call(prompt);
        log.info(chatResponse.toString());
        return chatResponse;
    }

}
