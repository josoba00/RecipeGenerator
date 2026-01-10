package com.johannabacker.recipeapp.service;

import com.johannabacker.recipeapp.model.Ingredient;
import com.johannabacker.recipeapp.model.Instruction;
import com.johannabacker.recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Recipe createRecipe(String name, int timeInSeconds, int portion, List<Ingredient> ingredients, List<Instruction> instructions, String notes){
        validateRecipe(name, timeInSeconds, portion, ingredients, instructions);

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

        validateRecipe(name, timeInSeconds, portion, ingredients, instructions);

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

    private void validateRecipe(String name, int timeInSeconds, int portion, List<Ingredient> ingredients, List<Instruction> instructions) {
        // check general recipe setup
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Recipe name must not be empty!");
        } if (portion <= 0) {
            throw new IllegalArgumentException("Portion of Recipe must be higher than zero");
        } if (ingredients.isEmpty()){
            throw new IllegalArgumentException("Recipe must contain at least one Ingredient!");
        } if (instructions.isEmpty()){
            throw new IllegalArgumentException("Recipe must contain at least one Instruction!");
        }

        // check ingredients
        if (ingredients.stream().map(Ingredient::getUnit).allMatch(unit -> unit!= null && !unit.isBlank())){
            throw new IllegalArgumentException("Ingredients must contain a unit!");
        } if (ingredients.stream().map(Ingredient::getAmount).allMatch(amount -> amount >= 0)){
            throw new IllegalArgumentException("Ingredients must contain an amount higher than zero!");
        } if (ingredients.stream().map(Ingredient::getName).allMatch(rname -> rname != null && !rname.isBlank())) {
            throw new IllegalArgumentException("Ingredients must contain a name!");
        }

        // check instructions
        if (instructions.stream().map(Instruction::getOrder).allMatch(order -> order != null && order > 0)){
            throw new IllegalArgumentException("Instructions must have positive order numbers!");
        } if (instructions.stream().map(Instruction::getOrder).distinct().count() == instructions.size()){
            throw new IllegalArgumentException("Instructions must have unique order numbers!");
        } if (instructions.stream().map(Instruction::getStep).allMatch(step -> step != null && !step.isBlank())){
            throw new IllegalArgumentException("Instructions must have a name!");
        }
    }
}
