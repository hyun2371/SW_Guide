package com.sungshindev.sw_guide.data;

public class HomeTip {
    int tip_id;
    String img_path;
    String explain;

    public int getTip_id(){ return tip_id;}
    public String getImg_path(){
        return img_path;
    }

    public String getExplain(){
        return explain;
    }
    public HomeTip(int tip_id, String img_path, String explain){
        this.tip_id = tip_id;
        this.img_path = img_path;
        this.explain = explain;
    }

    public HomeTip(){
        tip_id=0;
        img_path="default";
        explain = "default";
    }
}
