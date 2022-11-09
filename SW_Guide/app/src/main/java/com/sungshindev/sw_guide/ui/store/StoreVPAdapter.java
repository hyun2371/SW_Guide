package com.sungshindev.sw_guide.ui.store;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StoreVPAdapter extends FragmentStateAdapter {
    public StoreVPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position==1)
            return new DrinkFragment();
        return new FoodFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
