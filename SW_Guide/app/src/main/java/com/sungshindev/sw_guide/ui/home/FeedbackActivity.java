package com.sungshindev.sw_guide.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.ui.MainActivity;

public class FeedbackActivity extends AppCompatActivity {
    View backBtn;
    EditText edit1;
    Button sendBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        backBtn = findViewById(R.id.feed_back_btn);
        edit1 = findViewById(R.id.edit1);
        sendBtn = findViewById(R.id.feedback_send_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");
                String[] address = {"swguide22@gmail.com"};
                email.putExtra(Intent.EXTRA_EMAIL, address);
                email.putExtra(Intent.EXTRA_SUBJECT, "SW Guide 피드백");
                email.putExtra(Intent.EXTRA_TEXT, "피드백 내용\n"+edit1.getText());
                startActivity(email);

                try{
                    startActivity(Intent.createChooser(email,"Please select Email"));
                }
                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(FeedbackActivity.this,"There are no Email Clients",Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
