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
import eu.tutorials.recipesapp.model.InstructionsResponse;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapter.ViewHolder> {
    Context context;
    List<InstructionsResponse> list;

    public InstructionAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_instructions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_instruction_name.setText(list.get(position).getName());
        holder.rec_instruction_steps.setHasFixedSize(true);
        holder.rec_instruction_steps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        InstructionStepAdapter instructionStepAdapter = new InstructionStepAdapter(context, list.get(position).getSteps());
        holder.rec_instruction_steps.setAdapter(instructionStepAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_instruction_name;
        RecyclerView rec_instruction_steps;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_instruction_name = itemView.findViewById(R.id.txt_instruction_name);
            rec_instruction_steps = itemView.findViewById(R.id.rec_instruction_steps);
        }
    }
}
