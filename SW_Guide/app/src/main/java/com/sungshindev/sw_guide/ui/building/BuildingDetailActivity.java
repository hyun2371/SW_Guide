package com.sungshindev.sw_guide.ui.building;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.databinding.ActivityDetailBuildingBinding;
import com.sungshindev.sw_guide.databinding.ActivityDetailStoreBinding;

public class BuildingDetailActivity extends AppCompatActivity {
    String detail;
    String title;

    TextView titleTv;
    TextView detailTv;
    Button backBtn;
    ImageView imageView;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String ref = "Building";
        String child;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_building);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        detailTv = findViewById(R.id.building_detail_explain);
        titleTv = findViewById(R.id.building_detail_title);
        imageView = findViewById(R.id.building_detail_iv);
        backBtn = findViewById(R.id.building_back_btn);
        imageView = findViewById(R.id.building_detail_iv);


        Intent intent = getIntent();
        int id = intent.getIntExtra("building_id", 0);
        Log.d("building_id", String.valueOf(id));

        child = String.format("Building_0%s",id);
        readData(ref,child);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void readData(String ref,String child) {
        Log.d("buildingDetail","call readData()");

        reference = FirebaseDatabase.getInstance().getReference(ref);
        reference.child(child).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        detail =String.valueOf(dataSnapshot.child("explain").getValue()).replace("\\n", "\n");
                        detailTv.setText(detail);
                        title =String.valueOf(dataSnapshot.child("title").getValue());
                        titleTv.setText(title);
                        Glide.with(imageView).load(dataSnapshot.child("image_path").getValue())
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                                .into(imageView);
                    }
                    else{
                        Log.d("buildingDetail","building doesn't exist");
                    }

                }else {
                    Log.d("buildingDetail","Failed to read");
                }
            }
        });
    }
}
