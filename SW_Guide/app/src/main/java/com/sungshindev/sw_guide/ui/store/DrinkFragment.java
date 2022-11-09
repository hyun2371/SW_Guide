package com.sungshindev.sw_guide.ui.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Drink;
import com.sungshindev.sw_guide.data.Food;

import java.util.ArrayList;

public class DrinkFragment extends Fragment {
    private RecyclerView rv;
    private DrinkRVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_drink,container,false);
        ArrayList<Drink> drinks = new ArrayList();
        drinks.add(new Drink("카페나무","11:00~21:00",R.drawable.store_food_iv));
        drinks.add(new Drink("카페나무","11:00~21:00",R.drawable.store_food_iv));
        drinks.add(new Drink("카페나무","11:00~21:00",R.drawable.store_food_iv));
        drinks.add(new Drink("카페나무","11:00~21:00",R.drawable.store_food_iv));
        drinks.add(new Drink("카페나무","11:00~21:00",R.drawable.store_food_iv));
        drinks.add(new Drink("카페나무","11:00~21:00",R.drawable.store_food_iv));

        rv = rootView.findViewById(R.id.store_drink_rv);
        adapter = new DrinkRVAdapter(getContext(),drinks);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));
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
