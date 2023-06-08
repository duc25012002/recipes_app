package eu.tutorials.recipesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import eu.tutorials.recipesapp.adapter.RandomRecipesAdapter;
import eu.tutorials.recipesapp.listener.RandomRecipeResponseListener;
import eu.tutorials.recipesapp.listener.RecipeClickListener;
import eu.tutorials.recipesapp.model.RandomRecipeApiResponse;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    RequestManager manager;
    RandomRecipesAdapter randomRecipesAdapter;
    RecyclerView rec_random;

    Spinner spinner;

    List<String> tags = new ArrayList<String>();
    SearchView search_view_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");


        //Search
        search_view_home = findViewById(R.id.search_view_home);
        search_view_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
                progressDialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        //Spinner
        spinner = findViewById(R.id.spinner_tags);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectionListener);
        manager = new RequestManager(this);

        // Add an initial element to the 'tags' ArrayList
        tags.add("initial value");
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse recipeApiResponse, String message) {
            progressDialog.dismiss();
            rec_random = findViewById(R.id.rec_random);
            rec_random.setHasFixedSize(true);
            rec_random.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            randomRecipesAdapter = new RandomRecipesAdapter(MainActivity.this, recipeApiResponse.recipes, recipeClickListener);
            rec_random.setAdapter(randomRecipesAdapter);
        }

        @Override
        public void didError(String message) {
            Snackbar.make(MainActivity.this.getCurrentFocus(), message, Snackbar.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectionListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (!tags.isEmpty()) {
                tags.clear();
            }
            tags.add(parent.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener, tags);
            progressDialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(MainActivity.this, RecipeDetailsActivity.class).putExtra("id", id));
        }
    };
}