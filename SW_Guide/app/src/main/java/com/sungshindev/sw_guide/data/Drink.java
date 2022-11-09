package com.sungshindev.sw_guide.data;

public class Drink {

    int drink_id;
    String title;
    String time;
    String image_path;

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

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

    public String getImage_path(){
        return image_path;
    }
    public void setImage_path(String image_path){
        this.image_path = image_path;
    }

    public Drink(int drink_id, String title,  String time, String image_path){
        this.drink_id = drink_id;
        this.title = title;
        this.time = time;
        this.image_path = image_path;
    }

    public Drink(){
        drink_id=0;
        title = "default";
        time = "default";
        image_path = "default";
    }
}
