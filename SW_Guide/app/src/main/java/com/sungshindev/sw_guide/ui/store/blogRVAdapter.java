package com.sungshindev.sw_guide.ui.store;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Blog.BlogItems;
import com.sungshindev.sw_guide.data.Drink;

import java.util.ArrayList;

public class blogRVAdapter extends RecyclerView.Adapter<blogRVAdapter.ViewHolder>{
    Context context;
    ArrayList<BlogItems> blogItems;

    public interface OnItemClickListener{
        void onItemClicked(int position);
    }

    private blogRVAdapter.OnItemClickListener itemClickListener;

    public void setOnItemClickListener (blogRVAdapter.OnItemClickListener listener){
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("blog","onCreateViewHolder 호출");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_blog,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                itemClickListener.onItemClicked(pos);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        int pos = holder.getAdapterPosition();
        viewHolder.title.setText(Html.fromHtml(blogItems.get(pos).getTitle()));
        Log.d("blog/RVAdapter",blogItems.get(pos).getDescription());
        viewHolder.description.setText(Html.fromHtml(blogItems.get(pos).getDescription()));
        viewHolder.date.setText(blogItems.get(pos).getPostdate());
    }

    @Override
    public int getItemCount() {
        return blogItems.size();
    }

    public blogRVAdapter(Context context, ArrayList<BlogItems> blogItems){
        this.blogItems = blogItems;
        Log.d("blog/생성자",String.valueOf(blogItems.size()));
        this.context =context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            Log.d("blog","ViewHolder 호출");
            title = itemView.findViewById(R.id.item_blog_title);
            date = itemView.findViewById(R.id.item_blog_date);
            description = itemView.findViewById(R.id.item_blog_description);
        }
    }
}
