package com.example.alqur_anapp.apihelper;

import com.example.alqur_anapp.model.NewsRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("top-headlines")
    Call<NewsRequest> getNewsList(@Query("sources")String source,
                                  @Query("apiKey")String apiKey);
}
