package com.johannabacker.recipeapp.service;

import com.johannabacker.recipeapp.model.Ingredient;
import com.johannabacker.recipeapp.model.Instruction;
import com.johannabacker.recipeapp.model.Recipe;
import com.johannabacker.recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Recipe recipe){
        validateRecipe(recipe);
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id){
        Recipe recipe = recipeRepository.findFirstByRecipeId(id);
        if (recipe == null){
            throw new NoSuchElementException("Recipe with id " + id + " not found!");
        }
        return recipe;
    }

    public Recipe updateRecipe(Recipe recipe){
        if (recipe.getRecipeId() == null){
            throw new IllegalArgumentException("Recipe id must not be null for update!");
        }

        if (!recipeRepository.existsById(recipe.getRecipeId())){
            throw new NoSuchElementException("Recipe with id " + recipe.getRecipeId() + " not found!");
        }
        validateRecipe(recipe);
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id){
        Recipe recipe = recipeRepository.findFirstByRecipeId(id);
        if (recipe == null){
            throw new NoSuchElementException("Recipe with id " + recipe.getRecipeId() + " not found!");
        }
        recipeRepository.delete(recipe);
    }

    private void validateRecipe(Recipe recipe) {
        // check general recipe setup
        if (recipe.getName() == null || recipe.getName().isBlank()) {
            throw new IllegalArgumentException("Recipe name must not be empty!");
        } if (recipe.getPortion() <= 0) {
            throw new IllegalArgumentException("Portion of Recipe must be higher than zero");
        } if (recipe.getIngredients().isEmpty()){
            throw new IllegalArgumentException("Recipe must contain at least one Ingredient!");
        } if (recipe.getInstructions().isEmpty()){
            throw new IllegalArgumentException("Recipe must contain at least one Instruction!");
        }

        // check ingredients
        if (recipe.getIngredients().stream().map(Ingredient::getUnit).anyMatch(unit -> unit == null || unit.isBlank())){
            throw new IllegalArgumentException("Ingredients must contain a unit!");
        } if (recipe.getIngredients().stream().map(Ingredient::getAmount).anyMatch(amount -> amount <= 0)){
            throw new IllegalArgumentException("Ingredients must contain an amount higher than zero!");
        } if (recipe.getIngredients().stream().map(Ingredient::getName).anyMatch(name -> name == null || name.isBlank())) {
            throw new IllegalArgumentException("Ingredients must contain a name!");
        }

        // check instructions
        if (recipe.getInstructions().stream().map(Instruction::getOrderNumber).anyMatch(orderNr -> orderNr <= 0)){
            throw new IllegalArgumentException("Instructions must have positive order numbers!");
        } if (recipe.getInstructions().stream().map(Instruction::getOrderNumber).distinct().count() != recipe.getInstructions().size()){
            throw new IllegalArgumentException("Instructions must have unique order numbers!");
        } if (recipe.getInstructions().stream().map(Instruction::getStep).anyMatch(step -> step == null || step.isBlank())){
            throw new IllegalArgumentException("Instructions must have a name!");
        }
    }
}
