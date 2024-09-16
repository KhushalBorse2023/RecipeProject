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

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String Jwt) throws Exception {
        User user = userService.findUserByJwt(Jwt);
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

    @PostMapping("/{recipeId}/like")
    public Recipe LikeRecipe(@RequestHeader("Authorization") String Jwt, @PathVariable Long recipeId) throws Exception {
        User user = userService.findUserByJwt(Jwt);
        Recipe updatedRecipe = recipeService.likeRecipe(recipeId, user);
        return updatedRecipe;
    }

}