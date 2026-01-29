package com.johannabacker.recipeapp.repository;

import com.johannabacker.recipeapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findFirstByRecipeId(Long recipeId);

}
