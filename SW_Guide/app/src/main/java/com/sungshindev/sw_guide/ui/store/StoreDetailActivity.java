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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Food;

import java.util.ArrayList;

public class StoreDetailActivity extends AppCompatActivity {
    Button backBtn;
    ImageButton bookmarkBtn;
    Button callBtn;
    TextView numText;
    TextView titleText;
    TextView categoryText;
    TextView timeText;
    TextView recommendText;
    String num;
    String title;
    String time;
    String recommend;
    String category;
    boolean isBookmarked=false;
    private DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        String ref; //어느 테이블 참조할지
        String child;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        int kind = intent.getIntExtra("kind",0);

        //drink vs food
        if (kind==1){
            ref="Food";
            child = String.format("Food_0%s",id);
        } else {
            ref="Drink";
            child = String.format("Drink_0%s",id);
        }

        Log.d("ff",String.valueOf(id));
        backBtn = findViewById(R.id.store_back_btn);
        bookmarkBtn = findViewById(R.id.store_star_btn);
        callBtn = findViewById(R.id.store_call_btn);
        numText = findViewById(R.id.store_num_tv);
        titleText = findViewById(R.id.store_title_tv);
        categoryText = findViewById(R.id.store_category_tv);
        timeText = findViewById(R.id.store_time_tv);
        recommendText = findViewById(R.id.store_recommend_tv);


        readData(ref,child);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bookmarkBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (!isBookmarked){
                    isBookmarked=true;
                    bookmarkBtn.setImageResource(R.drawable.ic_star_clicked);
                    Toast.makeText(getApplicationContext(),"북마크에 추가하였습니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    isBookmarked=false;
                    bookmarkBtn.setImageResource(R.drawable.ic_star_unclicked);
                    Toast.makeText(getApplicationContext(),"북마크를 취소하였습니다.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        callBtn.setOnClickListener(new View.OnClickListener(){
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
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        title = String.valueOf(dataSnapshot.child("title").getValue());
                        titleText.setText(title);
                        category = String.valueOf(dataSnapshot.child("category").getValue());
                        categoryText.setText(category);
                        time = String.valueOf(dataSnapshot.child("time").getValue());
                        timeText.setText(time);
                        num = String.valueOf(dataSnapshot.child("num").getValue());
                        numText.setText(num);

                        recommend = String.valueOf(dataSnapshot.child("recommend").getValue()).replace("\\n", "\n");
                        recommendText.setText(recommend);
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
}
