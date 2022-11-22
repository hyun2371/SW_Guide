package com.sungshindev.sw_guide.data.Blog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface BlogInterface {
    @GET("/v1/search/blog")
    Call<Blogs> getBlogData(@Header("X-Naver-Client-Id") String clientId, @Header("X-Naver-Client-Secret") String clientSecret,
                                       @Query("query") String query);
//    fun getSearchBlog(@Header("X-Naver-Client-Id")clientId:String, @Header("X-Naver-Client-Secret")clientSecret:String,
//                      @Query("query")query:String?): Call<SearchBlogResponse>

}
