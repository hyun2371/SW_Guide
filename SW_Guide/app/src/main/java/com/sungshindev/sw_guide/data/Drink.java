package com.sungshindev.sw_guide.data;

public class Drink {

    String category;
    int drink_id;
    String image_path;
    float lat;
    float lon;
    String num;
    String recommend;
    String time;
    String title;
    String address;

    public int getDrink_id() {
        return drink_id;
    }

    public String getTitle(){
        return title;
    }

    public String getTime(){
        return time;
    }

    public String getAddress(){return address;}

    public String getImage_path(){
        return image_path;
    }

    public float getLon(){return lon;}
    public float getLat(){return lat;}

    public String getNum(){return num;}
    public String getRecommend(){return recommend;}

    public String getCategory(){return category;}

    public Drink(int drink_id, String title,  String time, String num, String category,
                 String image_path,float lon, float lat, String recommend){
        this.drink_id = drink_id;
        this.title = title;
        this.time = time;
        this.num =num;
        this.category = category;
        this.image_path = image_path;
        this.lon = lon;
        this.lat = lat;
        this.recommend = recommend;
    }

    public Drink(){
        drink_id=0;
        lon = 0;
        lat = 0;
        title = "default";
        time = "default";
        image_path = "default";
        recommend = "default";
        num="default";
        category="default";
        address="default";
    }
}
