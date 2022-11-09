package com.sungshindev.sw_guide.data;

public class Drink {

    String title;
    String time;
    int image_path;

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
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

    public Drink(String title,  String time, int image_path){
        this.title = title;
        this.time = time;
        this.image_path = image_path;
    }
}
