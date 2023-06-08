package eu.tutorials.recipesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import eu.tutorials.recipesapp.R;
import eu.tutorials.recipesapp.listener.RecipeClickListener;
import eu.tutorials.recipesapp.model.Recipe;

public class RandomRecipesAdapter extends RecyclerView.Adapter<RandomRecipesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Recipe> recipeArrayList;

    RecipeClickListener listener;

    public RandomRecipesAdapter(Context context, ArrayList<Recipe> recipeArrayList, RecipeClickListener listener) {
        this.context = context;
        this.recipeArrayList = recipeArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_random_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipeArrayList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView random_list_container;
        TextView txt_title, txt_servings, txt_likes, txt_time;
        ImageView img_food;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            random_list_container = itemView.findViewById(R.id.random_list_container);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_servings = itemView.findViewById(R.id.txt_servings);
            txt_likes = itemView.findViewById(R.id.txt_likes);
            txt_time = itemView.findViewById(R.id.txt_time);
            img_food = itemView.findViewById(R.id.img_food);
        }

        public void bind(Recipe recipe) {
            txt_title.setText(recipe.getTitle());
            txt_title.setSelected(true);

            txt_servings.setText(recipe.getServings() + " Servings");

            txt_likes.setText(recipe.getAggregateLikes() + " Likes");

            txt_time.setText(recipe.getReadyInMinutes() + " Minutes");

            Picasso.get().load(recipe.getImage()).into(img_food);

            random_list_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRecipeClicked(String.valueOf(recipeArrayList.get(getAdapterPosition()).id));

                }
            });

        }
    }
}
