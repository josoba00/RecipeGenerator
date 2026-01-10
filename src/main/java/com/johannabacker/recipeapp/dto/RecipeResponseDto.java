package com.johannabacker.recipeapp.dto;

import com.johannabacker.recipeapp.model.Ingredient;
import com.johannabacker.recipeapp.model.Instruction;
import com.johannabacker.recipeapp.model.Recipe;

import java.util.List;

public class RecipeResponseDto {

    private Long id;
    private String name;
    private int timeInSeconds;
    private int portion;
    private List<Ingredient> ingredients;
    private List<Instruction> instructions;
    private String notes;

    public static RecipeResponseDto fromRecipe(Recipe recipe) {
        RecipeResponseDto dto = new RecipeResponseDto();
        dto.id = recipe.getId();
        dto.name = recipe.getName();
        dto.timeInSeconds = recipe.getTimeInSeconds();
        dto.portion = recipe.getPortion();
        dto.ingredients = recipe.getIngredients();
        dto.instructions = recipe.getInstructions();
        dto.notes = recipe.getNotes();
        return dto;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public int getPortion() {
        return portion;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public String getNotes() {
        return notes;
    }
}
