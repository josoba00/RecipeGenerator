package com.johannabacker.recipeapp.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LlamaAIService {

    @Autowired
    private OllamaChatModel chatModel;

    public String generateResult(String systemPrompt, String userPrompt){

        String content = systemPrompt + "/n" + userPrompt;

        Prompt prompt = new Prompt(content);

        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }

    public String generateResult(String prompt){
        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt
                ));
        return response.getResult().getOutput().getText();
    }

}
