package com.sungshindev.sw_guide.ui.store;

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
import com.sungshindev.sw_guide.data.Food;
import com.sungshindev.sw_guide.ui.building.BuildingDetailActivity;
import com.sungshindev.sw_guide.ui.building.BuildingRVAdapter;

import java.util.ArrayList;

public class FoodFragment extends Fragment {
    private RecyclerView rv;
    private FoodRVAdapter adapter;

    private ArrayList<Food> foods;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_food,container,false);
        foods = new ArrayList<>();

        rv = rootView.findViewById(R.id.store_food_rv);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Food");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foods.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Food food = snapshot.getValue(Food.class);
                    foods.add(food);
                }
                adapter.notifyDataSetChanged();;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new FoodRVAdapter(getContext(),foods);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new FoodRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getActivity(), StoreDetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
