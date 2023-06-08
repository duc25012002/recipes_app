package eu.tutorials.recipesapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eu.tutorials.recipesapp.R;
import eu.tutorials.recipesapp.model.Ingredient;

public class InstructionsIngredientsAdapter extends RecyclerView.Adapter<InstructionsIngredientsAdapter.ViewHolder> {
    Context context;
    List<Ingredient> ingredientList;

    public InstructionsIngredientsAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instruction_steps_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_instruction_step_item;
        TextView txt_instruction_step_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_instruction_step_item = itemView.findViewById(R.id.img_instruction_step_item);
            txt_instruction_step_item = itemView.findViewById(R.id.txt_instruction_step_item);
        }

        public void bind(Ingredient ingredient) {
            Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + ingredient.getImage()).into(img_instruction_step_item);
            Log.i("TAG1", "https://spoonacular.com/cdn/ingredients_100x100/" + ingredient.getImage());
            txt_instruction_step_item.setText(ingredientList.get(getAdapterPosition()).getName());
            txt_instruction_step_item.setSelected(true);

        }
    }
}
