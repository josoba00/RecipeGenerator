package com.johannabacker.recipeapp.service;

import com.johannabacker.recipeapp.model.Ingredient;
import com.johannabacker.recipeapp.model.Instruction;
import com.johannabacker.recipeapp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("RecipeService - CRUD behaviour")
class RecipeServiceTest {

    private RecipeService recipeService;
    private List<Ingredient> eggIngredients = List.of(
            new Ingredient("Stück", 4, "Eier"),
            new Ingredient("g", 600, "Kartoffeln"),
            new Ingredient("Stück", 1, "Zwiebel"));
    private List<Instruction> eggInstructions = List.of(
            new Instruction(1, "Ofen auf 200 Grad Umluft vorheizen."),
            new Instruction(2, "Zwiebel und Kartoffeln in Stücke schneiden."),
            new Instruction(3, "Zwiebel und Kartoffeln in den Ofen geben und für 30min backen."),
            new Instruction(4, "Eier in einer Pfanne ausbraten."));


    @BeforeEach
    void setUp(){
        recipeService = new RecipeService();
    }

    @Nested
    @DisplayName("Create recipe")
    class CreateRecipeTests {

        @Test
        @DisplayName("creates a valid recipe with id and data")
        void createRecipe() {
            Recipe recipeCreated = recipeService.createRecipe(
                    "Spiegeleier mit Kartoffeln",
                    1800,
                    4,
                    new ArrayList<>(eggIngredients),
                    new ArrayList<>(eggInstructions),
                    "");

            assertAll(
                    () -> assertNotNull(recipeCreated.getId()),
                    () -> assertEquals("Spiegeleier mit Kartoffeln", recipeCreated.getName()),
                    () -> assertEquals(4, recipeCreated.getPortion()),
                    () -> assertEquals(3, recipeCreated.getIngredients().size()),
                    () -> assertEquals(4, recipeCreated.getInstructions().size())
            );

        }

        @Test
        @DisplayName("throws on empty name")
        void createRecipeWithEmptyName() {
            assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                    null,
                    1800,
                    4,
                    eggIngredients,
                    eggInstructions,
                    ""
            ));
        }

        @Test
        @DisplayName("throws on portion smaller than or equal zero")
        void createRecipeWithTooSmallPortionSize() {
            assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                    "Spiegeleier mit Kartoffeln",
                    1800,
                    -1,
                    eggIngredients,
                    eggInstructions,
                    ""
            ));
        }

        @Test
        @DisplayName("throws on empty ingredients")
        void createRecipeWithEmptyIngredient() {
            assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                    "Spiegelei mit Kartoffeln",
                    1800,
                    4,
                    List.of(),
                    eggInstructions,
                    ""
            ));
        }

        @Test
        @DisplayName("throws on empty instructions")
        void createRecipeWithEmptyInstruction() {
            assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                    "Spiegelei mit Kartoffeln",
                    1800,
                    4,
                    eggIngredients,
                    List.of(),
                    ""
            ));
        }

        @Test
        @DisplayName("throws when an instruction order is not positive")
        void createRecipeWithNonPositiveInstructionOrder() {
            List<Instruction> invalidInstructions = List.of(
                    new Instruction(0, "Ungültige Order"),
                    new Instruction(2, "Gültiger Schritt")
            );

            assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                    "Fehlerhaftes Rezept",
                    1800,
                    2,
                    eggIngredients,
                    invalidInstructions,
                    ""
            ));
        }

        @Test
        @DisplayName("throws when an instruction order is not unique")
        void createRecipeWithDuplicateInstructionOrder() {
            List<Instruction> invalidInstructions = List.of(
                    new Instruction(1, "Schritt 1"),
                    new Instruction(2, "Schritt 2"),
                    new Instruction(2, "Schritt 2 (duplicate)")
            );

            assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                    "Fehlerhaftes Rezept",
                    1800,
                    2,
                    eggIngredients,
                    invalidInstructions,
                    ""
            ));
        }

        @Test
        @DisplayName("throws when an instruction step is null or blank")
        void createRecipeWithInvalidInstructionStep() {
            List<Instruction> invalidInstructions0 = List.of(
                    new Instruction(1, ""),                        // invalid step
                    new Instruction(2, "Zweiter Schritt")
            );

            List<Instruction> invalidInstructions1 = List.of(
                    new Instruction(1, "Erster Schritt"),                        // invalid step
                    new Instruction(2, null)
            );

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                            "Fehlerhaftes Rezept",
                            1800,
                            2,
                            eggIngredients,
                            invalidInstructions0,
                            ""
                    )),
                    () -> assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                            "Fehlerhaftes Rezept",
                            1800,
                            2,
                            eggIngredients,
                            invalidInstructions1,
                            ""
                    ))
            );


        }

        @Test
        @DisplayName("throws when an ingredient unit is null or blank")
        void createRecipeWithInvalidIngredientUnit() {
            List<Ingredient> invalidIngredients0 = List.of(
                    new Ingredient("", 400, "Spaghetti"),
                    new Ingredient("g", 300, "Hackfleisch")
            );

            List<Ingredient> invalidIngredients1 = List.of(
                    new Ingredient("Stück", 70, "Spaghetti"),
                    new Ingredient(null, 120, "Hackfleisch")
            );

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                            "Spaghetti",
                            1800,
                            2,
                            invalidIngredients0,
                            eggInstructions,
                            "")),
                    () -> assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                            "Spaghetti",
                            1800,
                            2,
                            invalidIngredients1,
                            eggInstructions,
                            ""))
            );
        }

        @Test
        @DisplayName("throws when an ingredient amount is zero or negative")
        void createRecipeWithInvalidIngredientAmount() {
            List<Ingredient> invalidIngredients0 = List.of(
                    new Ingredient("g", 0, "Spaghetti"),
                    new Ingredient("g", 300, "Hackfleisch")
            );

            List<Ingredient> invalidIngredients1 = List.of(
                    new Ingredient("g", 10, "Spaghetti"),
                    new Ingredient("g", -5, "Hackfleisch")
            );

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                            "Spaghetti",
                            1800,
                            2,
                            invalidIngredients0,
                            eggInstructions,
                            "")),
                    () -> assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                            "Spaghetti",
                            1800,
                            2,
                            invalidIngredients1,
                            eggInstructions,
                            ""))
            );
        }

        @Test
        @DisplayName("throws when an ingredient name is null or blank")
        void createRecipeWithInvalidIngredientName() {
            List<Ingredient> invalidIngredients0 = List.of(
                    new Ingredient("g", 400, ""),
                    new Ingredient("g", 300, "Hackfleisch")
            );

            List<Ingredient> invalidIngredients1 = List.of(
                    new Ingredient("g", 400, "Hackfleisch"),
                    new Ingredient("g", 300, null)
            );

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                            "Spaghetti",
                            1800,
                            2,
                            invalidIngredients0,
                            eggInstructions,
                            "")),
                    () -> assertThrows(IllegalArgumentException.class, () -> recipeService.createRecipe(
                            "Spaghetti",
                            1800,
                            2,
                            invalidIngredients1,
                            eggInstructions,
                            ""))
            );
        }
    }

    @Test
    void getAllRecipes() {
    }

    @Test
    void getRecipeById() {
    }

    @Test
    void getRecipeByUnknownId() {
    }

    @Test
    void updateExistingRecipe() {
    }

    @Test
    void updateUnknownRecipe() {
    }

    @Test
    void deleteExistingRecipe() {
    }

    @Test
    void deleteUnknownRecipe() {
    }

}