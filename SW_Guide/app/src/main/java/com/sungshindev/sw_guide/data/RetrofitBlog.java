package com.sungshindev.sw_guide.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sungshindev.sw_guide.data.Blog.BlogInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBlog {
    private static final String BASE_URL = "https://openapi.naver.com";


    public static BlogInterface getApiService(){return getInstance().create(BlogInterface.class);}

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
