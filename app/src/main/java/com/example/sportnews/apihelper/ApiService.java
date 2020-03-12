package com.example.sportnews.apihelper;

import com.example.sportnews.model.NewsRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("top-headlines")
    Call<NewsRequest> getNewsList(@Query("country") String country,
                                  @Query("category") String source,
                                  @Query("apiKey") String apiKey);
}
