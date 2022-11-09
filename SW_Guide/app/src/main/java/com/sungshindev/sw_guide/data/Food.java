package com.sungshindev.sw_guide.data;

public class Food {
    String title;
    String category;
    String time;
    String image_path;
    int fid;

    public int getFid(){
        return fid;
    }

    public void setFid(){
        this.fid = fid;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }

    public String getImage_path(){
        return image_path;
    }

    public void setImage_path(String image_path){
        this.image_path = image_path;
    }

    public Food(){
        fid = 0;
        title="default";
        category ="default";
        time = "default";
        image_path="default";
    }

    public Food(int fid, String title, String category, String time, String image_path){
        this.fid = fid;
        this.title = title;
        this.category = category;
        this.time = time;
        this.image_path = image_path;
    }
}
