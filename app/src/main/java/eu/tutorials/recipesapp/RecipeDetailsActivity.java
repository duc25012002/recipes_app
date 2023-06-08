package eu.tutorials.recipesapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eu.tutorials.recipesapp.adapter.IngredientsAdapter;
import eu.tutorials.recipesapp.adapter.InstructionAdapter;
import eu.tutorials.recipesapp.adapter.SimilarRecipeAdapter;
import eu.tutorials.recipesapp.listener.InstructionsListener;
import eu.tutorials.recipesapp.listener.RecipeClickListener;
import eu.tutorials.recipesapp.listener.RecipeDetailsListener;
import eu.tutorials.recipesapp.listener.SimilarRecipesListener;
import eu.tutorials.recipesapp.model.InstructionsResponse;
import eu.tutorials.recipesapp.model.RecipeDetailsResponse;
import eu.tutorials.recipesapp.model.SimilarRecipeResponse;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView txt_meal_name, txt_meal_source, txt_meal_summary;
    ImageView img_meal_image;
    RecyclerView rec_meal_ingredients, rec_meal_similar, rec_meal_instruction;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionAdapter instructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        initView();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        manager.getSimilarRecipes(similarRecipesListener, id);
        manager.getInstructions(instructionsListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();
    }

    private void initView() {
        txt_meal_name = findViewById(R.id.txt_meal_name);
        txt_meal_source = findViewById(R.id.txt_meal_source);
        txt_meal_summary = findViewById(R.id.txt_meal_summary);
        img_meal_image = findViewById(R.id.img_meal_image);
        rec_meal_ingredients = findViewById(R.id.rec_meal_ingredients);
        rec_meal_similar = findViewById(R.id.rec_meal_similar);
        rec_meal_instruction = findViewById(R.id.rec_meal_instruction);
    }

    public String fixTag(String tags) {
        String updatedTags = tags.replace("</b>", " ").replace("<b>", " ");
        return updatedTags;
    }


    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse recipeDetailsResponse, String message) {
            dialog.dismiss();
            txt_meal_name.setText(recipeDetailsResponse.getTitle());
            txt_meal_source.setText(recipeDetailsResponse.getSourceName());
            txt_meal_summary.setText(fixTag(recipeDetailsResponse.getSummary()));
            Picasso.get().load(recipeDetailsResponse.getImage()).into(img_meal_image);
            rec_meal_ingredients.setHasFixedSize(true);
            rec_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivity.this, recipeDetailsResponse.extendedIngredients);
            rec_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> recipeResponses, String message) {
            rec_meal_similar.setHasFixedSize(true);
            rec_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailsActivity.this, recipeResponses, recipeClickListener);
            rec_meal_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };


    public final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Toast.makeText(RecipeDetailsActivity.this, id, Toast.LENGTH_SHORT).show();
        }
    };
    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            rec_meal_instruction.setHasFixedSize(true);
            rec_meal_instruction.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
            instructionAdapter = new InstructionAdapter(RecipeDetailsActivity.this, response);
            rec_meal_instruction.setAdapter(instructionAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, id, Toast.LENGTH_SHORT).show();

        }
    };
}