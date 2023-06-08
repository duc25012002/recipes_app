package eu.tutorials.recipesapp.listener;

import eu.tutorials.recipesapp.model.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse recipeApiResponse, String message);
    void didError(String message);
}
