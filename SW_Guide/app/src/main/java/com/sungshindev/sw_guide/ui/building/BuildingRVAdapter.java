package com.sungshindev.sw_guide.ui.building;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Building;

import java.util.ArrayList;

public class BuildingRVAdapter extends RecyclerView.Adapter<BuildingRVAdapter.ViewHolder>{

    ArrayList<Building> buildings;
    Context context;
    private int b_id;

    public interface OnItemClickListener{
        void onItemClicked(int position, int b_id);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener (OnItemClickListener listener){
        itemClickListener = listener;
    }
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_building,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int pos = viewHolder.getAdapterPosition();
                b_id=buildings.get(pos).getBuilding_id();
                itemClickListener.onItemClicked(pos,b_id);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        int pos = holder.getAdapterPosition();
        viewHolder.textView.setText(buildings.get(pos).getTitle());
        Glide.with(holder.itemView)
                .load(buildings.get(pos).getImage_path())
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public BuildingRVAdapter(Context context, ArrayList<Building> buildings){
        this.buildings = buildings;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.item_building_title_tv);
            imageView = itemView.findViewById(R.id.item_building_iv);
        }
    }
}
