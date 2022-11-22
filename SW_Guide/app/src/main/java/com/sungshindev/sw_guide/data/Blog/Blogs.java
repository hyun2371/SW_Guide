package com.sungshindev.sw_guide.data.Blog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class Blogs {
    @SerializedName("items")
    @Expose
    ArrayList<BlogItems> blogItems;

    public ArrayList<BlogItems> getBlogItems(){
        return blogItems;
    }


}
