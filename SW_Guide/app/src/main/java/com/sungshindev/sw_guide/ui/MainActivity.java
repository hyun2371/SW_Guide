package com.sungshindev.sw_guide.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.databinding.ActivityMainBinding;
import com.sungshindev.sw_guide.ui.building.BuildingFragment;
import com.sungshindev.sw_guide.ui.home.HomeFragment;
import com.sungshindev.sw_guide.ui.question.QuestionFragment;
import com.sungshindev.sw_guide.ui.store.StoreFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item-> {

            switch (item.getItemId()){
                case R.id.menu_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.menu_question:
                    replaceFragment(new QuestionFragment());
                    break;
                case R.id.menu_building:
                    replaceFragment(new BuildingFragment());
                    break;
                case R.id.menu_store:
                    replaceFragment(new StoreFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout,fragment);
        fragmentTransaction.commit();
    }




}