package com.sungshindev.sw_guide.data;

public class Food {
    String title;
    String category;
    String time;
    String image_path;
    String num;
    String recommend;
    float lon;
    float lat;
    int fid;

    public int getFid(){
        return fid;
    }

    public String getNum(){return num;}

    public String getTitle(){
        return title;
    }

    public String getCategory(){
        return category;
    }


    public String getTime(){
        return time;
    }

    public String getImage_path(){
        return image_path;
    }

    public String getRecommend(){return recommend;}


    public Food(){
        fid = 0;
        lat=0;
        lon=0;
        title="default";
        category ="default";
        time = "default";
        image_path="default";
        num = "default";
        recommend="default";
    }

    public Food(int fid, String title, String category, String time, String image_path, String num,String recommend){
        this.fid = fid;
        this.title = title;
        this.category = category;
        this.time = time;
        this.image_path = image_path;
        this.num = num;
        this.recommend=recommend;
    }
}
