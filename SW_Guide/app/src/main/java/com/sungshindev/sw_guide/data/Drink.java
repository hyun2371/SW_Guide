package com.sungshindev.sw_guide.data;

public class Drink {

    int drink_id;
    String title;
    String time;
    String image_path;
    int lon;
    int lat;

    public int getDrink_id() {
        return drink_id;
    }

    public String getTitle(){
        return title;
    }

    public String getTime(){
        return time;
    }

    public String getImage_path(){
        return image_path;
    }

    public int getLon(){return lon;}
    public int getLat(){return lat;}

    public Drink(int drink_id, String title,  String time, String image_path){
        this.drink_id = drink_id;
        this.title = title;
        this.time = time;
        this.image_path = image_path;
    }

    public Drink(){
        drink_id=0;
        lon = 0;
        lat = 0;
        title = "default";
        time = "default";
        image_path = "default";
    }
}
