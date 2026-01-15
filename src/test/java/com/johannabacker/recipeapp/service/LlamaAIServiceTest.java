package com.johannabacker.recipeapp.service;

import com.johannabacker.recipeapp.model.DietType;
import com.johannabacker.recipeapp.model.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LlamaAIServiceTest {

    @Autowired
    private LlamaAIService service;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ClassifierService classifyService;

    @Test
    void generateResult() {
        String output = service.generateResult("Kannst du deutssch, wenn ja begrüße mich!");
        System.out.println(output);
        Assertions.assertNotNull(output);
    }

    @Test
    void generateResultWithPrompt() {
        Recipe recipe = recipeService.getRecipeById(20L);
        DietType dietType = classifyService.classifyDietType(recipe);

        assertEquals(DietType.VEGAN, dietType);
    }

    @Test
    void generateResultWithMeat() {
        Recipe recipe = recipeService.getRecipeById(10L);
        DietType dietType = classifyService.classifyDietType(recipe);

        assertEquals(DietType.MEAT, dietType);
    }
}