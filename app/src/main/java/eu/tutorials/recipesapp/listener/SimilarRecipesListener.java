package eu.tutorials.recipesapp.listener;

import java.util.List;

import eu.tutorials.recipesapp.model.SimilarRecipeResponse;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> recipeResponses, String message);

    void didError(String message);
}
