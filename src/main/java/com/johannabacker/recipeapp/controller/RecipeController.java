package com.johannabacker.recipeapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @GetMapping("/hello")
    public String hello(){
        return "Recipe API is running";
    }
}
