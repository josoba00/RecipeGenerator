package com.johannabacker.recipeapp.service;

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
    private TagClassifierService classifyService;

    @Test
    void generateResult() {
        String output = service.generateResult("Kannst du deutssch, wenn ja begrüße mich!");
        System.out.println(output);
        Assertions.assertNotNull(output);
    }

    @Test
    void generateResultWithPrompt() {
        Recipe recipe = recipeService.getRecipeById(20L);
        String dietType = classifyService.classifyDietType(recipe);
        String gluten = classifyService.classifyGluten(recipe);
        System.out.println(dietType + " " +  gluten);

        assertAll(
                () -> assertNotNull(dietType),
                () -> assertEquals("Vegan", dietType)
        );
    }

    @Test
    void generateResultWithMeat() {
        Recipe recipe = recipeService.getRecipeById(10L);
        String dietType = classifyService.classifyDietType(recipe);
        String gluten = classifyService.classifyGluten(recipe);
        System.out.println(dietType + " " +  gluten);

        assertAll(
                () -> assertNotNull(dietType),
                () -> assertEquals("Fleischhaltig", dietType)
        );
    }
}