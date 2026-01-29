package com.johannabacker.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String unit;
    private int amount;
    private String name;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonIgnore
    private Recipe recipe;

    public Ingredient(String unit, int amount, String name) {
        this.unit = unit;
        this.amount = amount;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient that)) return false;
        if (id == null || that.getId() == null) return false;
        return id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
