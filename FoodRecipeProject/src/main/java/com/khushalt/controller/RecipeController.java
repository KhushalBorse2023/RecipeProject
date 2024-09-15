package com.khushalt.controller;

import com.khushalt.model.Recipe;
import com.khushalt.model.User;
import com.khushalt.service.RecipeService;
import com.khushalt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Recipe createdRecipe = recipeService.createRecipe(recipe, user);
        return recipe;
    }

    @GetMapping
    public List<Recipe> getAllRecipe() throws Exception {
        List<Recipe> listOfRecipe = recipeService.findAllRecipe();
        return listOfRecipe;
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return "Recipe deleted successfully";
    }

    @PutMapping("/{recipeId}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long recipeId) throws Exception {
        Recipe updatedRecipe = recipeService.updateRecipe(recipe, recipeId);
        return updatedRecipe;
    }

    @PostMapping("/{recipeId}/user/{userId}")
    public Recipe LikeRecipe(@PathVariable Long userId, @PathVariable Long recipeId) throws Exception {
        User user = userService.findUserById(userId);
        Recipe updatedRecipe = recipeService.likeRecipe(recipeId, user);
        return updatedRecipe;
    }

}