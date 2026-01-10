package com.johannabacker.recipeapp.model;

import lombok.Data;

@Data
public class Instruction {
    private Long id;
    private int order;
    private String step;
}
