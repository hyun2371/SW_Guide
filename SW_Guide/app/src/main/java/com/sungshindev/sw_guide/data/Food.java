package com.sungshindev.sw_guide.data;

public class Food {
    String title;
    String category;
    String time;
    int image_path;

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

    public int getImage_path(){
        return image_path;
    }

    public void setImage_path(int image_path){
        this.image_path = image_path;
    }

    public Food(String title, String category, String time, int image_path){
        this.title = title;
        this.category = category;
        this.time = time;
        this.image_path = image_path;
    }
}
