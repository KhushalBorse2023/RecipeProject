package com.khushalt.service;

import com.khushalt.model.Recipe;
import com.khushalt.model.User;

import java.util.List;

public interface RecipeService {
    public Recipe createRecipe(Recipe recipe, User user);

    public Recipe findRecipeById(Long Id) throws Exception;

    public void deleteRecipe(Long Id) throws Exception;

    public Recipe updateRecipe(Recipe recipe,Long Id) throws Exception;

    public List<Recipe> findAllRecipe();

    public Recipe likeRecipe(Long receipeId,User user) throws Exception;

}
