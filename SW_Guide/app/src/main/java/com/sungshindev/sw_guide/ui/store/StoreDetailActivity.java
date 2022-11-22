package com.sungshindev.sw_guide.ui.store;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Food;
import com.sungshindev.sw_guide.databinding.ActivityDetailStoreBinding;
import com.sungshindev.sw_guide.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class StoreDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityDetailStoreBinding binding;
    String num;
    String title;
    String time;
    String recommend;
    String category;
    String str_lat;
    String str_lon;
    double lat;
    double lon;

    private DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        String ref; //어느 테이블 참조할지
        String child;
        super.onCreate(savedInstanceState);
        binding = ActivityDetailStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        int kind = intent.getIntExtra("kind",0);

        //drink vs food
        if (kind==1){
            ref="Food";
            if (id<10)
                child = String.format("Food_0%s",id);
            else
                child = String.format("Food_%s",id);
        } else {
            ref="Drink";
            if (id<10)
                child = String.format("Drink_0%s",id);
            else
                child = String.format("Drink_%s",id);
        }
        readData(ref,child);


        binding.storeBackBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        binding.storeCallBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String tel = "tel:"+num;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                startActivity(intent);
            }
        });
    }


    private void readData(String ref,String child) {
        reference = FirebaseDatabase.getInstance().getReference(ref);
        reference.child(child).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("store/startofReadData","0");
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        str_lat =dataSnapshot.child("lat").getValue().toString();
                        str_lon = dataSnapshot.child("lon").getValue().toString();
                        lat = Double.parseDouble(str_lat);
                        lon = Double.parseDouble(str_lon);

                        getMap();

                        title = String.valueOf(dataSnapshot.child("title").getValue());
                        binding.storeTitleTv.setText(title);
                        category = String.valueOf(dataSnapshot.child("category").getValue());
                        binding.storeCategoryTv.setText(category);
                        time = String.valueOf(dataSnapshot.child("time").getValue()).replace("\\n", "\n");
                        binding.storeTimeTv.setText(time);
                        num = String.valueOf(dataSnapshot.child("num").getValue());
                        binding.storeNumTv.setText(num);
                        recommend = String.valueOf(dataSnapshot.child("recommend").getValue()).replace("\\n", "\n");
                        binding.storeRecommendTv.setText(recommend);


                    }
                    else{
                        Log.d("storeDetail","Food doesn't exist");
                    }

                }else {
                    Log.d("storeDetail","Failed to read");
                }
            }
        });
    }

    public void getMap(){
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d("store/map",Double.toString(lat));
        CameraPosition cameraPosition =
                new CameraPosition(new LatLng(lat,lon), 16);
        Marker marker = new Marker();

        marker.setPosition(new LatLng(lat,lon));
        marker.setMap(naverMap);
        naverMap.setCameraPosition(cameraPosition);

    }
}
