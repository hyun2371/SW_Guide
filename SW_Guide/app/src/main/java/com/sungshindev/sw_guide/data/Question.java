package com.sungshindev.sw_guide.data;

public class Question {
    String a;
    String q;
    int qid;
    String image_path;


    public String getA() {
        return a;
    }

    public String getQ(){
        return q;
    }

    public int getQid() {
        return qid;
    }

    public String getImage_path(){return image_path;}

    public Question(String a, String q, int qid, String image_path) {
        this.a = a;
        this.q = q;
        this.qid = qid;
        this.image_path = image_path;
    }

    public Question(){
        a = "default";
        q = "default";
        qid = 0;
        image_path="";
    }
}