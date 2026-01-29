package com.johannabacker.recipeapp.controller;

import com.johannabacker.recipeapp.dto.RecipeRequestDto;
import com.johannabacker.recipeapp.dto.RecipeResponseDto;
import com.johannabacker.recipeapp.model.Ingredient;
import com.johannabacker.recipeapp.model.Recipe;
import com.johannabacker.recipeapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<RecipeResponseDto> createRecipe(@RequestBody RecipeRequestDto request){
        Recipe toCreate = new Recipe(request.getName(), request.getTimeInSeconds(), request.getPortion(), request.getIngredients(), request.getInstructions(), request.getNotes());
        Recipe created = recipeService.createRecipe(toCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(RecipeResponseDto.fromRecipe(created));
    }

    @GetMapping
    public List<RecipeResponseDto> getAllRecipes(){
        return recipeService.getAllRecipes().stream().map(RecipeResponseDto::fromRecipe).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RecipeResponseDto getRecipeById(@PathVariable Long id){
        Recipe recipe = recipeService.getRecipeById(id);
        return RecipeResponseDto.fromRecipe(recipe);
    }

    @PutMapping("/{id}")
    public RecipeResponseDto updateRecipe(@PathVariable Long id, @RequestBody RecipeRequestDto request){
        Recipe toUpdate = recipeService.getRecipeById(id);

        toUpdate.setName(request.getName());
        toUpdate.setTimeInSeconds(request.getTimeInSeconds());
        toUpdate.setPortion(request.getPortion());
        toUpdate.setNotes(request.getNotes());

        toUpdate.getIngredients().clear();
        toUpdate.getInstructions().clear();

        if (request.getIngredients() != null){
            request.getIngredients().forEach(toUpdate::addIngredient);
        }
        if (request.getInstructions() != null){
            request.getInstructions().forEach(toUpdate::addInstruction);
        }

        Recipe updated = recipeService.updateRecipe(toUpdate);
        return RecipeResponseDto.fromRecipe(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
