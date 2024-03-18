package com.galka.springbootai.api;

import com.galka.springbootai.model.Model;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private OllamaChatClient ollamaChatClient;

    @PostMapping("/ask")
    public String callOllamaRestEntity(@RequestBody Model request) {
        if (!StringUtils.hasText(request.getQuestion())) {
            return "Please provide valid question";
        }
        String response = ollamaChatClient.call(request.getQuestion());
        return response;
    }

}
