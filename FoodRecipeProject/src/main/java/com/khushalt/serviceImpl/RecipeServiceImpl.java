package com.khushalt.serviceImpl;

import com.khushalt.model.Recipe;
import com.khushalt.model.User;
import com.khushalt.repository.RecipeRepository;
import com.khushalt.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe newRecipe = Recipe.builder().
                description(recipe.getDescription()).
                image(recipe.getImage()).
                likes(recipe.getLikes()).
                title(recipe.getTitle()).user(user).
                vegeterisn(recipe.isVegeterisn()).
                createdAt(LocalDateTime.now()).build();

        return recipeRepository.save(newRecipe);
    }

    @Override
    public Recipe findRecipeById(Long Id) throws Exception {
        Optional<Recipe> recipe = recipeRepository.findById(Id);
        return recipe.orElseThrow(() -> new Exception("Recipe not found with id " + Id));

    }

    @Override
    public void deleteRecipe(Long Id) throws Exception {
        findRecipeById(Id);
        recipeRepository.deleteById(Id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long Id) throws Exception {
        Recipe oldRecipe = findRecipeById(Id);
        if (oldRecipe != null) {
            Recipe updatedRecipe = Recipe.builder()
                    .title(recipe.getTitle() != null ? recipe.getTitle() : oldRecipe.getTitle())
                    .image(recipe.getImage() != null ? recipe.getImage() : oldRecipe.getImage())
                    .description(recipe.getDescription() != null ? recipe.getDescription() : oldRecipe.getDescription())
                    .build();
            return recipeRepository.save(updatedRecipe);

        }
        throw new Exception("Recipe not found with id" + Id);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long receipeId, User user) throws Exception {
        Recipe recipe = findRecipeById(receipeId);
        if (recipe.getLikes().contains(user.getId())) recipe.getLikes().remove(user.getId());
        else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }
}
