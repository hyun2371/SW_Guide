package com.sungshindev.sw_guide.data;

public class Building {
    int building_id;
    String title;
    String image_path;
    String explain;

    public int getBuilding_id() {
        return building_id;
    }

    public String getTitle(){
        return title;
    }

    public String getImage_path(){
        return image_path;
    }

    public String getExplain(){
        return explain;
    }
    public Building(int building_id, String title, String image_path, String explain){
        this.building_id = building_id;
        this.title = title;
        this.image_path = image_path;
        this.explain = explain;
    }

    public Building(){
        building_id = 0;
        title="default";
        image_path="default";
        explain = "default";
    }
}
