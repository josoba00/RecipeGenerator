package com.johannabacker.recipeapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeId;

    private String name;
    private int timeInSeconds;
    private int portion;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Instruction> instructions;

    private String notes;

    public Recipe(String name, int timeInSeconds, int portion, List<Ingredient> ingredients, List<Instruction> instructions, String notes) {
        this.name = name;
        this.timeInSeconds = timeInSeconds;
        this.portion = portion;
        this.ingredients = new ArrayList<>();
        this.instructions = new ArrayList<>();
        this.notes = notes;

        if (ingredients != null) {
            addIngredients(ingredients);
        }
        if (instructions != null){
            addInstructions(instructions);
        }
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

    public void addIngredients(List<Ingredient> ingredientList){
        ingredientList.forEach(this::addIngredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
        ingredient.setRecipe(null);
    }

    public void addInstruction(Instruction instruction){
        instructions.add(instruction);
        instruction.setRecipe(this);
    }

    public void addInstructions(List<Instruction> instructionList){
        instructionList.forEach(this::addInstruction);
    }

    public void removeInstruction(Instruction instruction){
        instructions.remove(instruction);
        instruction.setRecipe(null);
    }
}
