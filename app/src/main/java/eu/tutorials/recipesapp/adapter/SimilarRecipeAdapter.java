package eu.tutorials.recipesapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eu.tutorials.recipesapp.R;
import eu.tutorials.recipesapp.listener.RecipeClickListener;
import eu.tutorials.recipesapp.model.SimilarRecipeResponse;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeAdapter.ViewHolder> {

    Context context;
    List<SimilarRecipeResponse> similarRecipeResponseList;
    RecipeClickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> similarRecipeResponseList, RecipeClickListener listener) {
        this.context = context;
        this.similarRecipeResponseList = similarRecipeResponseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similar_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimilarRecipeResponse similarRecipeResponse = similarRecipeResponseList.get(position);
        holder.bind(similarRecipeResponse);
    }

    @Override
    public int getItemCount() {
        return similarRecipeResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView similar_recipe_holder;
        TextView txt_similar_title, txt_similar_serving;
        ImageView img_similar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            similar_recipe_holder = itemView.findViewById(R.id.similar_recipe_holder);
            txt_similar_title = itemView.findViewById(R.id.txt_similar_title);
            txt_similar_serving = itemView.findViewById(R.id.txt_similar_serving);
            img_similar = itemView.findViewById(R.id.img_similar);
        }

        public void bind(SimilarRecipeResponse similarRecipeResponse) {
            txt_similar_title.setText(similarRecipeResponse.getTitle());
            txt_similar_title.setSelected(true);
            txt_similar_serving.setText(similarRecipeResponse.getServings() + " person");
            txt_similar_title.setSelected(true);
            Picasso.get().load("https://spoonacular.com/recipeImages/" + similarRecipeResponseList.get(getAdapterPosition()).getId() + "-556x370" + "." + similarRecipeResponse.getImageType()).into(img_similar);
            Log.i("TAG", "https://spoonacular.com/recipeImages/" + similarRecipeResponseList.get(getAdapterPosition()).getId() + "-556x370" + "." + similarRecipeResponse.getImageType());
            similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRecipeClicked(String.valueOf(similarRecipeResponseList.get(getAdapterPosition()).getId()));
                }
            });
        }
    }
}
