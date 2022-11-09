package com.sungshindev.sw_guide.ui.building;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Building;

import java.util.ArrayList;

public class BuildingFragment extends Fragment {
    private RecyclerView rv;
    private BuildingRVAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_building,container,false);
        ArrayList<Building> buildings = new ArrayList();

        buildings.add(new Building("수정관",R.drawable.building_sujung));
        buildings.add(new Building("성신관",R.drawable.building_sungshin));
        buildings.add(new Building("난향관",R.drawable.building_nanhyang));
        buildings.add(new Building("중앙도서관",R.drawable.building_library));
        buildings.add(new Building("학생회관",R.drawable.building_library));
        buildings.add(new Building("성미료",R.drawable.building_library));
        rv = rootView.findViewById(R.id.building_recyclerview);
        adapter = new BuildingRVAdapter(getContext(),buildings);

        rv.setLayoutManager(new GridLayoutManager(getContext(),2));
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new BuildingRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getActivity(), BuildingDetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}