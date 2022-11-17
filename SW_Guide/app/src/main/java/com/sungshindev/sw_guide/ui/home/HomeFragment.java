package com.sungshindev.sw_guide.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.sungshindev.sw_guide.data.Building;
import com.sungshindev.sw_guide.data.HomeTip;
import com.sungshindev.sw_guide.data.Question;
import com.sungshindev.sw_guide.ui.building.BuildingRVAdapter;
import com.sungshindev.sw_guide.ui.login.LoginActivity;
import com.sungshindev.sw_guide.ui.question.QuestionDetailActivity;
import com.sungshindev.sw_guide.ui.question.QuestionRVAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    TextView loginBtn;
    private RecyclerView rv;
    private HomeRVAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);

        loginBtn = rootView.findViewById(R.id.home_login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });




        ArrayList<HomeTip> homeTips = new ArrayList();

        rv = rootView.findViewById(R.id.home_tip_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("HomeTip");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                homeTips.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    HomeTip hometip= snapshot.getValue(HomeTip.class);
                    homeTips.add(hometip);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new HomeRVAdapter(getContext(),homeTips);
        rv.setAdapter(adapter);


        adapter.setOnItemClickListener(new HomeRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {

            }
        });

        return rootView;

    }
}
