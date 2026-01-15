package com.johannabacker.recipeapp.service;

import com.johannabacker.recipeapp.model.Ingredient;
import com.johannabacker.recipeapp.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TagClassifierService {

    @Autowired
    private LlamaAIService aiService;

    private String buildIngredientContext(Recipe recipe) {
        String ingredients = recipe.getIngredients().stream()
                .map(Ingredient::getName)
                .collect(Collectors.joining(", "));

        return """
        Du bist ein Assistent, der Kochrezepte anhand ihrer Zutaten klassifiziert.

        Zutaten: [%s]

        """.formatted(ingredients);
    }


    public String classifyRecipe(Recipe recipe){
        String userPrompt = """
        Hier ist das Rezept, das du klassifizieren sollst.

        {
          "recipeName": "%s",
          "ingredients": [%s]
        }
        """.formatted(
                recipe.getName(),
                recipe.getIngredients().stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.joining(", "))
        );

        return aiService.generateResult(userPrompt);
    }

    public String classifyDietType(Recipe recipe){
        String base = buildIngredientContext(recipe);

        String task = """
        Bestimme nur anhand der Zutaten, ob das Rezept vegetarisch, vegan, oder fleischhaltig ist.
        
        Die Definition der Kategorien ist wie folgt:
            vegan
                Kein Fleisch
                Keine tierischen Erzeugnisse
            vegetarisch
                Kein Fleisch
                Tierische Erzeugnisse
            fleischhaltig
                Mindestens eine Zutat ist eindeutig Fleisch, Wurst, Fisch oder Meeresfrucht.
        
        Falls das Rezept sowohl vegetarisch als auch vegan wäre, klassifiziere es als vegan.
        
        Erwartungen/Ausgabe:
        Antworte mit genau einem Wort: "Vegan", "Vegetarisch" oder "Fleischhaltig"
        """;

        String prompt = base + task;

        return aiService.generateResult(prompt);
    }

}
