package com.example.sportnews.apihelper;

public class UtilsApi {

    static final String BASE_URL = "https://newsapi.org/v2/";

    public static ApiService getApiService(){
        return ApiClient.getClient().create(ApiService.class);
    }
}
