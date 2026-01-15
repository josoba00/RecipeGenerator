package com.johannabacker.recipeapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LlamaAIServiceTest {

    @Autowired
    private LlamaAIService service;

    @Test
    void generateResult() {
        String output = service.generateResult("Sag hallo!");
        System.out.println(output);
        Assertions.assertNotNull(output);
    }
}