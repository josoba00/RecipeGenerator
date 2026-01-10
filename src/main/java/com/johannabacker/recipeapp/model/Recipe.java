package com.johannabacker.recipeapp.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;
    private int timeInSeconds;
    private int portion;

    private List<Ingredient> ingredients;
    private List<Instruction> instructions;
    private String notes;
}
