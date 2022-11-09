package com.sungshindev.sw_guide.data;

public class Building {
    int building_id;
    String title;
    String image_path;

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getImage_path(){
        return image_path;
    }

    public void setImage_path(String image_path){
        this.image_path = image_path;
    }

    public Building(int building_id, String title, String image_path){
        this.building_id = building_id;
        this.title = title;
        this.image_path = image_path;
    }

    public Building(){
        building_id = 0;
        title="default";
        image_path="default";
    }
}
