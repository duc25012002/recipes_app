package eu.tutorials.recipesapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallInstructions {
    @GET("recipes/{id}/analyzedInstructions")
    Call<List<InstructionsResponse>> callInstructions(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );
}
