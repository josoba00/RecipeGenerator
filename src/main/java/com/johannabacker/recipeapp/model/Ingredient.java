package com.johannabacker.recipeapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ingredient")
@Data
public class Ingredient {
    @Id
    private Long id;

    private String unit;
    private int amount;
    private String name;
}
