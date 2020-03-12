package com.example.sportnews.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sportnews.BuildConfig;
import com.example.sportnews.apihelper.ApiService;
import com.example.sportnews.apihelper.UtilsApi;
import com.example.sportnews.model.NewsRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    private static final String API_TOKEN = BuildConfig.API_KEY;
    private MutableLiveData<NewsRequest> liveDataNews = new MutableLiveData<>();

    public void setNews(String country, String category) {
        ApiService mApiService = UtilsApi.getApiService();
        Call<NewsRequest> call = mApiService.getNewsList(country, category, API_TOKEN);
        call.enqueue(new Callback<NewsRequest>() {

            public void onResponse(Call<NewsRequest> call, Response<NewsRequest> response) {
                Log.d("onResponse", response.body().getStatus());
                liveDataNews.setValue(response.body());

            }

            public void onFailure(Call<NewsRequest> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    public LiveData<NewsRequest> getNews(){
        return liveDataNews;
    }
}
