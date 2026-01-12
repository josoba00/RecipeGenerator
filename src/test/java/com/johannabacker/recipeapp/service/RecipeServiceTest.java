package com.johannabacker.recipeapp.service;

import com.johannabacker.recipeapp.model.Ingredient;
import com.johannabacker.recipeapp.model.Instruction;
import com.johannabacker.recipeapp.model.Recipe;
import com.johannabacker.recipeapp.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.event.InternalFrameEvent;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("RecipeService - CRUD behaviour")
class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRepository recipeRepository;

    private List<Ingredient> eggIngredients;
    private List<Instruction> eggInstructions;
    private List<Ingredient> boloIngredients;
    private List<Instruction> boloInstructions;

    @BeforeEach
    void setUp() {
        recipeRepository.deleteAll();

        eggIngredients = List.of(
                new Ingredient("Stück", 4, "Eier"),
                new Ingredient("g", 600, "Kartoffeln"),
                new Ingredient("Stück", 1, "Zwiebel")
        );
        eggInstructions = List.of(
                new Instruction(1, "Ofen auf 200 Grad Umluft vorheizen."),
                new Instruction(2, "Zwiebel und Kartoffeln in Stücke schneiden."),
                new Instruction(3, "Zwiebel und Kartoffeln in den Ofen geben und für 30min backen."),
                new Instruction(4, "Eier in einer Pfanne ausbraten.")
        );

        boloIngredients = List.of(
                new Ingredient("Stück", 4, "Karotten"),
                new Ingredient("g", 600, "Hackfleisch"),
                new Ingredient("Stück", 1, "Zwiebel")
        );
        boloInstructions = List.of(
                new Instruction(1, "Zwiebel, Karotten in kleine Stücke schneiden."),
                new Instruction(2, "Hackfleisch scharf in der Pfanne anbraten."),
                new Instruction(3, "Zwiebel und Karotten hinzugeben und für 5min mit braten."),
                new Instruction(4, "Mit Brühe ablöschen.")
        );
    }

    @Nested
    @DisplayName("Create recipe")
    class CreateRecipeTests {

        @Test
        @DisplayName("creates a valid recipe with id and data")
        void createRecipe() {
            Recipe recipeCreated = recipeService.createRecipe(
                    new Recipe(
                    "Eier mit Kartoffeln",
                    1800,
                    4,
                    eggIngredients,
                    eggInstructions,
                    ""
                    )
            );

            Recipe recipeLoaded = recipeService.getRecipeById(recipeCreated.getRecipeId());

            assertAll(
                    () -> assertNotNull(recipeLoaded),
                    () -> assertEquals("Eier mit Kartoffeln", recipeLoaded.getName()),
                    () -> assertEquals(4, recipeLoaded.getPortion()),
                    () -> assertEquals(3, recipeLoaded.getIngredients().size()),
                    () -> assertEquals(4, recipeLoaded.getInstructions().size())
            );

        }

        @Test
        @DisplayName("throws on empty name")
        void createRecipeWithEmptyName() {
            Recipe invalid = new Recipe(
                    null,
                    1800,
                    4,
                    eggIngredients,
                    eggInstructions,
                    ""
            );

            assertThrows(IllegalArgumentException.class,
                    () -> recipeService.createRecipe(invalid));
        }

        @Test
        @DisplayName("throws on portion smaller than or equal zero")
        void createRecipeWithTooSmallPortionSize() {
            Recipe invalid = new Recipe(
                    "Eier mit Kartoffeln",
                    1800,
                    0,
                    eggIngredients,
                    eggInstructions,
                    ""
            );

            assertThrows(IllegalArgumentException.class,
                    () -> recipeService.createRecipe(invalid));
        }

        @Test
        @DisplayName("throws on empty ingredients")
        void createRecipeWithEmptyIngredients() {
            Recipe invalid = new Recipe(
                    "Eier mit Kartoffeln",
                    1800,
                    4,
                    List.of(),
                    eggInstructions,
                    ""
            );

            assertThrows(IllegalArgumentException.class,
                    () -> recipeService.createRecipe(invalid));
        }

        @Test
        @DisplayName("throws on empty instructions")
        void createRecipeWithEmptyInstruction() {
            Recipe invalid = new Recipe(
                    "Eier mit Kartoffeln",
                    1800,
                    4,
                    eggIngredients,
                    List.of(),
                    ""
            );

            assertThrows(IllegalArgumentException.class,
                    () -> recipeService.createRecipe(invalid));
        }

        @Test
        @DisplayName("throws when an instruction order is not positive")
        void createRecipeWithNonPositiveInstructionOrder() {
            List<Instruction> invalidInstructions = List.of(
                    new Instruction(0, "Ungültige Order"),
                    new Instruction(2, "Gültiger Schritt")
            );

            Recipe invalid = new Recipe(
                    "Fehlerhaftes Rezept",
                    1800,
                    2,
                    eggIngredients,
                    invalidInstructions,
                    ""
            );

            assertThrows(IllegalArgumentException.class,
                    () -> recipeService.createRecipe(invalid));
        }

        @Test
        @DisplayName("throws when an instruction order is not unique")
        void createRecipeWithDuplicateInstructionOrder() {
            List<Instruction> invalidInstructions = List.of(
                    new Instruction(1, "Schritt 1"),
                    new Instruction(2, "Schritt 2"),
                    new Instruction(2, "Schritt 2 (duplicate)")
            );

            Recipe invalid = new Recipe(
                    "Fehlerhaftes Rezept",
                    1800,
                    2,
                    eggIngredients,
                    invalidInstructions,
                    ""
            );

            assertThrows(IllegalArgumentException.class,
                    () -> recipeService.createRecipe(invalid));
        }

        @Test
        @DisplayName("throws when an instruction step is null or blank")
        void createRecipeWithInvalidInstructionStep() {
            List<Instruction> invalid0 = List.of(
                    new Instruction(1, ""),
                    new Instruction(2, "Zweiter Schritt")
            );
            List<Instruction> invalid1 = List.of(
                    new Instruction(1, "Erster Schritt"),
                    new Instruction(2, null)
            );

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> recipeService.createRecipe(
                                    new Recipe("Fehlerhaftes Rezept", 1800, 2, eggIngredients, invalid0, "")
                            )),
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> recipeService.createRecipe(
                                    new Recipe("Fehlerhaftes Rezept", 1800, 2, eggIngredients, invalid1, "")
                            ))
            );
        }

        @Test
        @DisplayName("throws when an ingredient unit is null or blank")
        void createRecipeWithInvalidIngredientUnit() {
            List<Ingredient> invalid0 = List.of(
                    new Ingredient("", 400, "Eier"),
                    new Ingredient("g", 300, "Kartoffeln")
            );
            List<Ingredient> invalid1 = List.of(
                    new Ingredient("Stück", 70, "Eier"),
                    new Ingredient(null, 120, "Kartoffeln")
            );

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> recipeService.createRecipe(
                                    new Recipe("Eier mit Kartoffeln", 1800, 2, invalid0, eggInstructions, "")
                            )),
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> recipeService.createRecipe(
                                    new Recipe("Eier mit Kartoffeln", 1800, 2, invalid1, eggInstructions, "")
                            ))
            );
        }

        @Test
        @DisplayName("throws when an ingredient amount is zero or negative")
        void createRecipeWithInvalidIngredientAmount() {
            List<Ingredient> invalid0 = List.of(
                    new Ingredient("g", 0, "Eier"),
                    new Ingredient("g", 300, "Kartoffeln")
            );
            List<Ingredient> invalid1 = List.of(
                    new Ingredient("g", 10, "Kartoffeln"),
                    new Ingredient("g", -5, "Eier")
            );

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> recipeService.createRecipe(
                                    new Recipe("Eier mit Kartoffeln", 1800, 2, invalid0, eggInstructions, "")
                            )),
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> recipeService.createRecipe(
                                    new Recipe("Eier mit Kartoffeln", 1800, 2, invalid1, eggInstructions, "")
                            ))
            );
        }

        @Test
        @DisplayName("throws when an ingredient name is null or blank")
        void createRecipeWithInvalidIngredientName() {
            List<Ingredient> invalid0 = List.of(
                    new Ingredient("g", 400, ""),
                    new Ingredient("g", 300, "Kartoffeln")
            );
            List<Ingredient> invalid1 = List.of(
                    new Ingredient("g", 400, "Kartoffeln"),
                    new Ingredient("g", 300, null)
            );

            assertAll(
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> recipeService.createRecipe(
                                    new Recipe("Eier mit Kartoffeln", 1800, 2, invalid0, eggInstructions, "")
                            )),
                    () -> assertThrows(IllegalArgumentException.class,
                            () -> recipeService.createRecipe(
                                    new Recipe("Eier mit Kartoffeln", 1800, 2, invalid1, eggInstructions, "")
                            ))
            );
        }
    }

    @Test
    @DisplayName("return all persisted recipes")
    void getAllRecipes() {
        recipeService.createRecipe(
                new Recipe("Bolognese",1800,2, boloIngredients, boloInstructions,""));
        recipeService.createRecipe(
                new Recipe("Eier mit Kartoffeln",1800,2, eggIngredients, eggInstructions,""));

        assertEquals(2, recipeService.getAllRecipes().size());
    }

    @Test
    void getRecipeById() {
        Recipe created = recipeService.createRecipe(new Recipe("Bolognese",1800,2, boloIngredients, boloInstructions,""));

        Recipe found = recipeService.getRecipeById(created.getRecipeId());

        assertAll(
                () -> assertNotNull(found),
                () -> assertEquals("Bolognese", found.getName()),
                () -> assertEquals(3, found.getIngredients().size())
        );
    }

    @Test
    void getRecipeByUnknownId() {
        assertThrows(NoSuchElementException.class, () -> recipeService.getRecipeById(9999L));
    }

    @Test
    void updateExistingRecipe() {
        Recipe created = recipeService.createRecipe(new Recipe("Bolognese",1800,2, boloIngredients, boloInstructions,""));
        Recipe updatedData = new Recipe("Bolognese new",2000,2, boloIngredients, boloInstructions,"Notizen");

        updatedData.setRecipeId(created.getRecipeId());
        Recipe updated = recipeService.updateRecipe(updatedData);

        assertAll(
                () -> assertEquals("Bolognese new", updated.getName()),
                () -> assertEquals(2000, updated.getTimeInSeconds()),
                () -> assertEquals(2, updated.getPortion()),
                () -> assertEquals(3, updated.getIngredients().size())
        );
    }

    @Test
    void updateRecipeWithNullId() {
        Recipe updatedData = new Recipe("Bolognese new",2000,2, boloIngredients, boloInstructions,"Notizen");

        assertThrows(IllegalArgumentException.class, () -> recipeService.updateRecipe(updatedData));
    }

    @Test
    void updateRecipeWithUnkownId() {
        Recipe updatedData = new Recipe("Bolognese new",2000,2, boloIngredients, boloInstructions,"Notizen");
        updatedData.setRecipeId(9999L);

        assertThrows(NoSuchElementException.class, () -> recipeService.updateRecipe(updatedData));
    }

    @Test
    void deleteExistingRecipe() {
        Recipe created = recipeService.createRecipe(new Recipe("Bolognese",1800,2, boloIngredients, boloInstructions,""));
        Long id = created.getRecipeId();

        recipeService.deleteRecipe(id);
        assertThrows(NoSuchElementException.class, () -> recipeService.getRecipeById(id));
    }

    @Test
    void deleteUnknownRecipe() {
        assertThrows(NoSuchElementException.class, () -> recipeService.getRecipeById(9999L));
    }

}