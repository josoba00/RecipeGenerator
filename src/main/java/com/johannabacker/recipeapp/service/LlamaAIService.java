package com.johannabacker.recipeapp.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LlamaAIService {

    @Autowired
    private OllamaChatModel chatModel;

    public String generateResult(String prompt){
        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt
                ));
        return response.getResult().getOutput().getText();
    }

}
