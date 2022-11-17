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
import com.sungshindev.sw_guide.data.Drink;
import com.sungshindev.sw_guide.data.Food;

import java.util.ArrayList;

public class DrinkRVAdapter extends RecyclerView.Adapter<DrinkRVAdapter.ViewHolder> {

    ArrayList<Drink> drinks;
    Context context;
    private int d_id;

    public interface OnItemClickListener{
        void onItemClicked(int position,int d_id);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener (OnItemClickListener listener){
        itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_store_drink,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                d_id=drinks.get(pos).getDrink_id();
                itemClickListener.onItemClicked(pos,d_id);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkRVAdapter.ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        int pos = holder.getAdapterPosition();
        viewHolder.title.setText(drinks.get(pos).getTitle());
        viewHolder.time.setText(drinks.get(pos).getTime().replace("\\n", "\n"));
        Glide.with(holder.itemView).load(drinks.get(pos).getImage_path()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public DrinkRVAdapter(Context context, ArrayList<Drink> drinks){
        this.drinks = drinks;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView time;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_drink_title);
            time = itemView.findViewById(R.id.item_drink_time);
            imageView = itemView.findViewById(R.id.item_drink_iv);
        }
    }
}
