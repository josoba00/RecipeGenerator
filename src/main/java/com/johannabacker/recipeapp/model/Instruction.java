package com.johannabacker.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instruction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int orderNumber;
    private String step;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonIgnore
    private Recipe recipe;

    public Instruction(int orderNumber, String step) {
        this.orderNumber = orderNumber;
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instruction that)) return false;
        if (id == null || that.getId() == null) return false;
        return id.equals(that.getId());
    }


    @Override
    public int hashCode() {
        return 31;
    }
}
