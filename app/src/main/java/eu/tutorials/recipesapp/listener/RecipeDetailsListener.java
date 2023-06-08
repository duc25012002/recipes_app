package eu.tutorials.recipesapp.listener;

import eu.tutorials.recipesapp.model.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse recipeDetailsResponse, String message);
    void didError(String message);
}
