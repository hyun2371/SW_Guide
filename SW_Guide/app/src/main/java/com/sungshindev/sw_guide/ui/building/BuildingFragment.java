package com.sungshindev.sw_guide.ui.building;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Building;
import com.sungshindev.sw_guide.data.Food;

import java.util.ArrayList;

public class BuildingFragment extends Fragment {
    private RecyclerView rv;
    private BuildingRVAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_building,container,false);
        ArrayList<Building> buildings = new ArrayList();

        rv = rootView.findViewById(R.id.building_recyclerview);


        rv.setLayoutManager(new GridLayoutManager(getContext(),2));


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Building");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                buildings.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Building building = snapshot.getValue(Building.class);
                    buildings.add(building);
                }
                adapter.notifyDataSetChanged();;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new BuildingRVAdapter(getContext(),buildings);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new BuildingRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position,int b_id) {
                Intent intent = new Intent(getActivity(), BuildingDetailActivity.class);
                intent.putExtra("building_id",b_id);
                startActivity(intent);
            }
        });

        return rootView;
    }
}