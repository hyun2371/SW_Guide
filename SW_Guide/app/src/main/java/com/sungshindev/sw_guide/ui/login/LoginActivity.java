package com.sungshindev.sw_guide.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.ui.MainActivity;
import com.sungshindev.sw_guide.ui.home.HomeFragment;

import java.util.regex.Pattern;

class shared_preferences
{

    static String pref_user_email = "user_email";

    static public SharedPreferences get_shared_preferences(Context ctx) {//모든 액티비티에서 인스턴스 얻음
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void set_user_email(Context ctx, String user_email) {//이메일 저장
        SharedPreferences.Editor editor = get_shared_preferences(ctx).edit();
        editor.putString(pref_user_email, user_email);
        editor.commit();//커밋은 필수
    }

    public static String get_user_email(Context ctx) {//저장된 이메일 가져오기
        return get_shared_preferences(ctx).getString(pref_user_email, "");
    }

    public static void clear_user(Context ctx) {//로그아웃 시 데이터 삭제
        SharedPreferences.Editor editor = get_shared_preferences(ctx).edit();
        editor.clear();
        editor.commit();//커밋은 필수
    }
}

public class LoginActivity extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth;//파이어베이스 인증
    private DatabaseReference mDatabaseRef;              //실시간 데이터베이스
    private EditText mEtEmail, mEtPwd;
    private FirebaseDatabase database;//로그인 입력필드
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference UserDatabaseRef = FirebaseDatabase.getInstance().getReference("SW_Guide");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //세로 고정

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SW_Guide");

        mEtEmail = (EditText) findViewById(R.id.login_email);
        mEtPwd = (EditText) findViewById(R.id.login_pwd);

        Pattern pattern = android.util.Patterns.EMAIL_ADDRESS;
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                Log.d("login",mEtEmail.getText().toString());
                if (strEmail.equals("")){
                    showDialog("이메일을 입력해주세요.");
                    return;
                }
                if(!pattern.matcher(strEmail).matches()){
                    showDialog("이메일 형식을 확인해주세요.");
                    return;
                }

                if (strPwd.equals("")){
                    showDialog("비밀번호를 입력해주세요.");
                    return;
                }
                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful()){
                            //saveNickname();
                            //로그인 성공
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);

                            Toast.makeText(LoginActivity.this, "로그인 완료",Toast.LENGTH_SHORT).show();

                        } else {
                            showDialog("아이디 또는 비밀번호가 일치하지 않습니다");
                        }
                    }
                });

            }
        });


        Button btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Button btn_backtohome= findViewById(R.id.btn_backtohome);
        btn_backtohome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    private void showDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }


}