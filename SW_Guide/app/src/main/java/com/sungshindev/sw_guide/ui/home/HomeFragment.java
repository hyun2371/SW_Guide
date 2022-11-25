package com.sungshindev.sw_guide.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.HomeTip;
import com.sungshindev.sw_guide.ui.MainActivity;
import com.sungshindev.sw_guide.ui.login.LoginActivity;


import java.util.ArrayList;

public class HomeFragment extends Fragment {
    TextView nameID;
    TextView loginBtn;
    TextView homeTitle;
    private RecyclerView rv;
    private HomeRVAdapter adapter;
    ArrayList<HomeTip> homeTips = new ArrayList();
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference UserDatabaseRef = FirebaseDatabase.getInstance().getReference("SW_Guide");

    private void refresh() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        nameID = rootView.findViewById(R.id.some_id);
        homeTitle = rootView.findViewById(R.id.home_tip_title);
        loginBtn = rootView.findViewById(R.id.home_login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user == null) {
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                } else {
                    Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });


        rv = rootView.findViewById(R.id.home_tip_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("HomeTip");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                homeTips.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HomeTip hometip = snapshot.getValue(HomeTip.class);
                    homeTips.add(hometip);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new HomeRVAdapter(getContext(), homeTips);
        rv.setAdapter(adapter);


        adapter.setOnItemClickListener(new HomeRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {

            }
        });

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (user != null) {
            loginBtn.setText("로그아웃");

            UserDatabaseRef.child("UserAccount").child(user.getUid()).child("name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    nameID.setText(value);
                    homeTitle.setText(String.format("%s님에게 유용한 정보입니다",value));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

}
