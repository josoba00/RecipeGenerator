package com.johannabacker.recipeapp.service;

import com.johannabacker.recipeapp.model.Ingredient;
import com.johannabacker.recipeapp.model.Instruction;
import com.johannabacker.recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RecipeService {
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Recipe createRecipe(String name, int timeInSeconds, int portion, List<Ingredient> ingredients, List<Instruction> instructions, String notes){
        validateRecipe(name, ingredients, instructions);

        long id = idGenerator.getAndIncrement();
        Recipe recipe = new Recipe(id, name, timeInSeconds, portion, ingredients, instructions, notes);
        recipes.put(id, recipe);
        return recipe;
    }

    public List<Recipe> getAllRecipes(){
        return new ArrayList<>(recipes.values());
    }

    public Recipe getRecipeById(Long id){
        Recipe recipe = recipes.get(id);
        if (recipe == null){
            throw new NoSuchElementException("Recipe with id " + id + " not found!");
        }
        return recipe;
    }

    public Recipe updateRecipe(Long id,String name, int timeInSeconds, int portion, List<Ingredient> ingredients, List<Instruction> instructions, String notes){
        if (!recipes.containsKey(id)){
            throw new NoSuchElementException("Recipe with id " + id + " not found!");
        }

        validateRecipe(name, ingredients, instructions);

        Recipe updated =new Recipe(id, name, timeInSeconds, portion, ingredients, instructions, notes);
        recipes.put(id, updated);
        return updated;
    }

    public void deleteRecipe(Long id){
        if (!recipes.containsKey(id)){
            throw new NoSuchElementException("Recipe with id " + id + " not found!");
        }
        recipes.remove(id);
    }

    private void validateRecipe(String name, List<Ingredient> ingredients, List<Instruction> instructions) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Recipe name mus not be empty!");
        } if (ingredients.isEmpty()){
            throw new IllegalArgumentException("Recipe must contain at least one Ingredient!");
        } if (instructions.isEmpty()){
            throw new IllegalArgumentException("Recipe must contain at least one Instruction!");
        }
    }

}
