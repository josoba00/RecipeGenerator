package com.johannabacker.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String unit;
    private int amount;
    private String name;
}
