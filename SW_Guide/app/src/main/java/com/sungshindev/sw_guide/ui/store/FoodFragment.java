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
import com.sungshindev.sw_guide.data.Food;
import com.sungshindev.sw_guide.ui.building.BuildingDetailActivity;
import com.sungshindev.sw_guide.ui.building.BuildingRVAdapter;

import java.util.ArrayList;

public class FoodFragment extends Fragment {
    private RecyclerView rv;
    private FoodRVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_food,container,false);
        ArrayList<Food> foods = new ArrayList();
        foods.add(new Food("언앨리셰프","양식","11:00~21:00",R.drawable.store_item_iv));
        foods.add(new Food("언앨리셰프","양식","11:00~21:00",R.drawable.store_item_iv));
        foods.add(new Food("언앨리셰프","양식","11:00~21:00",R.drawable.store_item_iv));
        foods.add(new Food("언앨리셰프","양식","11:00~21:00",R.drawable.store_item_iv));
        foods.add(new Food("언앨리셰프","양식","11:00~21:00",R.drawable.store_item_iv));
        foods.add(new Food("언앨리셰프","양식","11:00~21:00",R.drawable.store_item_iv));

        rv = rootView.findViewById(R.id.store_food_rv);
        adapter = new FoodRVAdapter(getContext(),foods);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));
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
