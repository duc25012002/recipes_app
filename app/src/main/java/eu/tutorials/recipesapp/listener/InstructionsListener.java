package eu.tutorials.recipesapp.listener;

import java.util.List;

import eu.tutorials.recipesapp.model.InstructionsResponse;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);

    void didError(String message);

}
