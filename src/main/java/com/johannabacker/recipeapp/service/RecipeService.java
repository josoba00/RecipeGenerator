package com.johannabacker.recipeapp.service;

import com.johannabacker.recipeapp.model.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id);

    Recipe updateRecipe(Recipe recipe);

    void deleteRecipe(Long id);
}
