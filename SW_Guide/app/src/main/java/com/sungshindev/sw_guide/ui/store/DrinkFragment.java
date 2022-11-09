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
import com.sungshindev.sw_guide.data.Drink;
import com.sungshindev.sw_guide.data.Food;

import java.util.ArrayList;

public class DrinkFragment extends Fragment {
    private RecyclerView rv;
    private DrinkRVAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ArrayList<Drink> drinks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_drink,container,false);
        drinks = new ArrayList<>();

        rv = rootView.findViewById(R.id.store_drink_rv);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Drink");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                drinks.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Drink drink = snapshot.getValue(Drink.class);
                    drinks.add(drink);
                }
                adapter.notifyDataSetChanged();;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new DrinkRVAdapter(getContext(),drinks);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new DrinkRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getActivity(), StoreDetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
