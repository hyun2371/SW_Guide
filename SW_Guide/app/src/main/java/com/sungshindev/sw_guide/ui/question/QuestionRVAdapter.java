package com.sungshindev.sw_guide.ui.question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Question;

import java.util.ArrayList;

public class QuestionRVAdapter extends RecyclerView.Adapter<QuestionRVAdapter.ViewHolder>{

    ArrayList<Question> questions;
    Context context;

    public interface OnItemClickListener{
        void onItemClicked(int position);
    }

    private QuestionRVAdapter.OnItemClickListener itemClickListener;

    public void setOnItemClickListener(QuestionRVAdapter.OnItemClickListener listener) {
        itemClickListener = listener;
    }
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);
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
        QuestionRVAdapter.ViewHolder viewHolder = (QuestionRVAdapter.ViewHolder)holder;
        int pos = holder.getAdapterPosition();
        viewHolder.button.setText(questions.get(pos).getQ());

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public QuestionRVAdapter(Context context, ArrayList<Question>questions) {
        this.questions = questions;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        public ViewHolder(View itemView){
            super(itemView);
            button = itemView.findViewById(R.id.item_question_button);
        }
    }

}
