package com.sungshindev.sw_guide.ui.store;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sungshindev.sw_guide.R;

public class StoreDetailActivity extends AppCompatActivity {

    Button backBtn;
    ImageButton bookmarkBtn;
    Button callBtn;
    TextView numText;
    String num;
    boolean isBookmarked=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);

        backBtn = findViewById(R.id.store_back_btn);
        bookmarkBtn = findViewById(R.id.store_star_btn);
        callBtn = findViewById(R.id.store_call_btn);
        numText = findViewById(R.id.store_num_tv);
        num = numText.getText().toString();

        Log.d("numbers",num);
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
                }
                else{
                    isBookmarked=false;
                    bookmarkBtn.setImageResource(R.drawable.ic_star_unclicked);
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
}
