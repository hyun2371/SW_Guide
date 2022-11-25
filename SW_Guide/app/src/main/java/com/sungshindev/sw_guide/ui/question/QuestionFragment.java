package com.sungshindev.sw_guide.ui.question;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Question;


import java.util.ArrayList;

public class QuestionFragment extends Fragment {
    private RecyclerView rv;
    private QuestionRVAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<Question> questions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_question, container,false);
        questions = new ArrayList();

        rv = rootView.findViewById(R.id.question_recyclerview);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Question");
        Log.d("questionSS","dddd");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questions.clear();
                Log.d("questionaa","dddd");
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Question question = snapshot.getValue(Question.class);
                    questions.add(question);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new QuestionRVAdapter(getContext(),questions);
        rv.setAdapter(adapter);


        adapter.setOnItemClickListener(new QuestionRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position,int qid) {
                Log.d("question","itemClicked");
                Intent intent = new Intent(getActivity(), QuestionDetailActivity.class);
                intent.putExtra("qid",qid);
                startActivity(intent);
            }
        });

        return rootView;
    }


}

