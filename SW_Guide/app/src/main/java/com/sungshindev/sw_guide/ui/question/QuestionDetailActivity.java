package com.sungshindev.sw_guide.ui.question;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.databinding.ActivityDetailQuestionBinding;

public class QuestionDetailActivity extends AppCompatActivity {
    Button backBtn;
    TextView atext;
    ImageView aimage;
    String a;
    String image_path;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String ref = "Question";
        String child;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_question);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        int id = intent.getIntExtra("qid", 0);
        Log.d("qid",String.valueOf(id));

        child = String.format("Question_0%s", id);

        backBtn = findViewById(R.id.question_back_btn);
        atext = findViewById(R.id.answer_textView);
        aimage = findViewById(R.id.answer_imageView);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference(ref);
        reference.child(child).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        image_path = String.valueOf(dataSnapshot.child("image_path").getValue());
                        Glide.with(aimage).load(dataSnapshot.child("image_path").getValue()).into(aimage);
                        a = String.valueOf(dataSnapshot.child("a").getValue());
                        atext.setText(a);

                    }
                }
            }
        });
    }
}
