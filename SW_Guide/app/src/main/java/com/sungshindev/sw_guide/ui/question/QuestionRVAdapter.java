package com.sungshindev.sw_guide.ui.question;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Drink;
import com.sungshindev.sw_guide.data.Question;
import com.sungshindev.sw_guide.ui.store.FoodRVAdapter;

import java.util.ArrayList;

public class QuestionRVAdapter extends RecyclerView.Adapter<QuestionRVAdapter.ViewHolder> {

    ArrayList<Question> questions;
    Context context;
    private int qid;

    public interface OnItemClickListener{
        void onItemClicked(int position,int qid);
    }

    private QuestionRVAdapter.OnItemClickListener itemClickListener;

    public void setOnItemClickListener (QuestionRVAdapter.OnItemClickListener listener){
        itemClickListener = listener;
    }

    @Override
    public QuestionRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question,parent,false);

        QuestionRVAdapter.ViewHolder viewHolder = new QuestionRVAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                qid=questions.get(pos).getQid();
                itemClickListener.onItemClicked(pos,qid);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionRVAdapter.ViewHolder holder, int position) {
        QuestionRVAdapter.ViewHolder viewHolder = holder;
        int pos = holder.getAdapterPosition();
        viewHolder.t.setText(questions.get(pos).getQ());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public QuestionRVAdapter(Context context, ArrayList<Question> questions){
        this.questions = questions;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView t;
        public ViewHolder(View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.q_t);
        }
    }
}
