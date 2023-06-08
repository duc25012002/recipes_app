package eu.tutorials.recipesapp.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eu.tutorials.recipesapp.R;
import eu.tutorials.recipesapp.model.ExtendedIngredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int positionItem = position;
        ExtendedIngredient extendedIngredient = list.get(positionItem);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                holder.bind(extendedIngredient);
                String imageIngredients = "https://spoonacular.com/cdn/ingredients_100x100/" + list.get(positionItem).image;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(imageIngredients).into(holder.img_ingredients);

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_ingredients_quantity, txt_ingredients_name;
        ImageView img_ingredients;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ingredients_quantity = itemView.findViewById(R.id.txt_ingredients_quantity);
            txt_ingredients_name = itemView.findViewById(R.id.txt_ingredients_name);
            img_ingredients = itemView.findViewById(R.id.img_ingredients);
        }

        public void bind(ExtendedIngredient extendedIngredient) {
            txt_ingredients_quantity.setText(extendedIngredient.original);
            txt_ingredients_quantity.setSelected(true);
            txt_ingredients_name.setText(extendedIngredient.getName());
            txt_ingredients_name.setSelected(true);
        }
    }
}
