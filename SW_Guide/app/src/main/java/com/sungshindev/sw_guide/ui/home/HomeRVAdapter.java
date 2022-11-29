package com.sungshindev.sw_guide.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.HomeTip;


import java.util.ArrayList;

public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.ViewHolder> {
    ArrayList<HomeTip> homeTips;
    Context context;

    public interface OnItemClickListener{
        void onItemClicked(int position,String url);
    }

    private HomeRVAdapter.OnItemClickListener itemClickListener;

    public void setOnItemClickListener (HomeRVAdapter.OnItemClickListener listener){
        itemClickListener = listener;
    }
    @NonNull
    public HomeRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_tip,parent,false);
        HomeRVAdapter.ViewHolder viewHolder = new HomeRVAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int pos = viewHolder.getAdapterPosition();
                String url = homeTips.get(pos).getClick_url();
                itemClickListener.onItemClicked(pos,url);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRVAdapter.ViewHolder holder, int position) {
        HomeRVAdapter.ViewHolder viewHolder = (HomeRVAdapter.ViewHolder)holder;
        int pos = holder.getAdapterPosition();
        viewHolder.textView.setText(homeTips.get(pos).getExplain());

        Glide.with(holder.itemView).load(homeTips.get(pos).getImg_path())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(holder.imageView);
        Log.d("ddddd",homeTips.get(pos).getImg_path());
    }


    @Override
    public int getItemCount() {
        return homeTips.size();
    }

    public HomeRVAdapter(Context context, ArrayList<HomeTip> homeTips){
        this.homeTips = homeTips;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.item_home_explain);
            imageView = itemView.findViewById(R.id.item_home_banner);
        }
    }
}
