package com.sungshindev.sw_guide.ui.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.ui.MainActivity;
import com.sungshindev.sw_guide.ui.store.StoreDetailActivity;

public class StoreFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private Button storeDetailBtn;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_store,container,false);

        tabLayout = rootView.findViewById(R.id.store_tab_layout);
        viewPager2 = rootView.findViewById(R.id.store_vp);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        StoreVPAdapter adapter = new StoreVPAdapter(manager,getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("음식"));
        tabLayout.addTab(tabLayout.newTab().setText("카페"));

        tabLayout.addOnTabSelectedListener(this);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        return rootView;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager2.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}