package com.sungshindev.sw_guide.ui.question;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sungshindev.sw_guide.R;
import com.sungshindev.sw_guide.data.Question;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {
    private RecyclerView rv;
    private QuestionRVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_question, container, false);
        ArrayList<Question> questions = new ArrayList();

        questions.add(new Question("Q: 학점 교류는 어떻게 신청할 수 있나요?"));
        questions.add(new Question("Q: 전과는 어떻게 신청할 수 있나요?"));
        questions.add(new Question("Q: 복수전공은 어떻게 신청할 수 있나요?"));
        questions.add(new Question("Q: 사물함은 어떻게 신청할 수 있나요?"));
        questions.add(new Question("Q: 교환학생은 어떻게 신청할 수 있나요?"));
        questions.add(new Question("Q: 시간표는 어떻게 짜는게 좋을까요?"));
        rv = rootView.findViewById(R.id.question_recyclerview);
        adapter = new QuestionRVAdapter(getContext(), questions);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);


        adapter.setOnItemClickListener(new QuestionRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getActivity(), QuestionDetailActivity.class);
                startActivity(intent);
            }
        });

        /*public void mOnpopupClick(View view){
            Intent intent = new Intent(this,QuestionDetailActivity.class);
            Intent.putExtra("data","Test Popup");
            startActivityForResult(intent, 1);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            if(requestCode==1){
                if(resultCode==RESULT_OK){
                    String result = data.getStringExtra("result");
                    backbtn.setText(result);
                }
            }
        }*/





        return rootView;
    }


}
