package com.sungshindev.sw_guide.ui.login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sungshindev.sw_guide.R;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity
{

    private FirebaseAuth mFirebaseAuth;                  //파이어베이스 인증
    private DatabaseReference mDatabaseRef;              //실시간 데이터베이스
    private EditText mEtEmail, mEtPwd, mEtName, mEtPnum; //회원가입 입력필드
    private Button mBtnRegister;                         //회원가입 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button btn_goback = (Button) findViewById(R.id.btn_GoBack);
        btn_goback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //로그인 화면으로 복귀
                finish();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SW_Guide");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtName = findViewById(R.id.et_name);
        mEtPnum = findViewById(R.id.et_pnum);

        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // 회원가입 처리
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strName = mEtName.getText().toString();
                String strPnum = mEtPnum.getText().toString();

                Pattern emailPattern = Patterns.EMAIL_ADDRESS;

                //입력없이 회원가입 버튼 누를 때 오류 잡은거
                if (strEmail.isEmpty()){
                    showDialog("이메일을 입력하세요.");
                    mEtEmail.requestFocus();
                    return;
                } else if(!emailPattern.matcher(strEmail).matches()){
                    showDialog("이메일 형식이 올바르지 않습니다.");
                    mEtEmail.requestFocus();
                    return;
                } else if (strPwd.isEmpty()){
                    showDialog("비밀번호를 입력하세요.");
                    mEtPwd.requestFocus();
                    return;
                } else if (strPwd.length() < 6 || strPwd.length() > 15){
                    showDialog("비밀번호를 6자~15자 사이로 입력해주세요.");
                    mEtPwd.requestFocus();
                    return;
                } else if (strName.isEmpty()){
                    showDialog("이름을 입력하세요.");
                    mEtName.requestFocus();
                    return;
                }  else if (strPnum.isEmpty()){
                    showDialog("전화번호를 입력하세요.");
                    mEtPnum.requestFocus();
                    return;
                }

                // Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); // firebaseuser 객체에 가져온다
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailID(firebaseUser.getEmail()); //로그인이 된 유저의 이메일을 정확하게 가져와야하기때문에
                            account.setPassword(strPwd); //사용자가 입력한 그대로 가져옴
                            account.setName(strName);
                            account.setPhonenum(strPnum);

                            // setValue : database에 insert(삽입) 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(SignUpActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SignUpActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
