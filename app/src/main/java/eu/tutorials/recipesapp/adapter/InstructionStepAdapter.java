package eu.tutorials.recipesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.tutorials.recipesapp.R;
import eu.tutorials.recipesapp.model.Step;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepAdapter.ViewHolder> {
    Context context;
    List<Step> stepList;

    public InstructionStepAdapter(Context context, List<Step> stepList) {
        this.context = context;
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_instruction_steps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_instructions_step_title.setText(stepList.get(position).getStep());
        holder.txt_instructions_step_number.setText(String.valueOf(stepList.get(position).getNumber()));

        holder.rec_instructions_ingredients.setHasFixedSize(true);
        holder.rec_instructions_ingredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionsIngredientsAdapter ingredientsAdapter = new InstructionsIngredientsAdapter(context, stepList.get(position).ingredients);
        holder.rec_instructions_ingredients.setAdapter(ingredientsAdapter);

        holder.rec_instructions_equipment.setHasFixedSize(true);
        holder.rec_instructions_equipment.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionsEquipmentsAdapter instructionsEquipmentsAdapter = new InstructionsEquipmentsAdapter(context, stepList.get(position).equipment);
        holder.rec_instructions_equipment.setAdapter(instructionsEquipmentsAdapter);

    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_instructions_step_number, txt_instructions_step_title;
        RecyclerView rec_instructions_equipment, rec_instructions_ingredients;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_instructions_step_title = itemView.findViewById(R.id.txt_instructions_step_title);
            txt_instructions_step_number = itemView.findViewById(R.id.txt_instructions_step_number);
            rec_instructions_ingredients = itemView.findViewById(R.id.rec_instructions_ingredients);
            rec_instructions_equipment = itemView.findViewById(R.id.rec_instructions_equipment);
        }
    }
}
