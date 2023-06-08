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
import eu.tutorials.recipesapp.model.Equipment;

public class InstructionsEquipmentsAdapter extends RecyclerView.Adapter<InstructionsEquipmentsAdapter.ViewHolder> {
    Context context;
    List<Equipment> equipmentList;

    public InstructionsEquipmentsAdapter(Context context, List<Equipment> equipmentList) {
        this.context = context;
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_instruction_steps_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipment equipment = equipmentList.get(position);
        holder.bind(equipment);
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_instruction_step_item;
        TextView txt_instruction_step_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_instruction_step_item = itemView.findViewById(R.id.img_instruction_step_item);
            txt_instruction_step_item = itemView.findViewById(R.id.txt_instruction_step_item);
        }

        public void bind(Equipment equipment) {
            Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/" + equipment.getImage()).into(img_instruction_step_item);
            Log.i("TAG2", "https://spoonacular.com/cdn/equipment_100x100/" + "slow-cooker.jpg");
            txt_instruction_step_item.setText(equipmentList.get(getAdapterPosition()).getName());
            txt_instruction_step_item.setSelected(true);

        }
    }
}
