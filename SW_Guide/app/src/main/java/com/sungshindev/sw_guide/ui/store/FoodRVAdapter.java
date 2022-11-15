package com.sungshindev.sw_guide.ui.store;

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
import com.sungshindev.sw_guide.data.Food;

import java.util.ArrayList;

public class FoodRVAdapter extends RecyclerView.Adapter<FoodRVAdapter.ViewHolder> {

    ArrayList<Food> foods;
    Context context;
    private int fid;

    public interface OnItemClickListener{
        void onItemClicked(int position,int fid);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener (OnItemClickListener listener){
        itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_store_food,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                fid=foods.get(pos).getFid();
                itemClickListener.onItemClicked(pos, fid);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRVAdapter.ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        int pos = holder.getAdapterPosition();
        viewHolder.title.setText(foods.get(pos).getTitle());
        viewHolder.time.setText(foods.get(pos).getTime());
        viewHolder.category.setText(foods.get(pos).getCategory());
        Glide.with(holder.itemView).load(foods.get(pos).getImage_path()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public FoodRVAdapter(Context context, ArrayList<Food> foods){
        this.foods = foods;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView time;
        TextView category;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_food_title);
            time = itemView.findViewById(R.id.item_food_time);
            category = itemView.findViewById(R.id.item_food_category);
            imageView = itemView.findViewById(R.id.item_food_iv);
        }
    }
}
