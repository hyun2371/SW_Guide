package com.sungshindev.sw_guide.data.Blog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlogItems {
    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("link")
    @Expose
    String link;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("postdate")
    @Expose
    String postdate;

    public String getTitle(){
        return title;
    }

    public String getLink(){
        return link;
    }

    public String getDescription(){
        return description;
    }

    public String getPostdate(){
        return postdate;
    }
}
