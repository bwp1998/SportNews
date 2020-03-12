package com.example.sportnews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsRequest {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("articles")
    @Expose
    private List<NewsResult> result;

    public NewsRequest(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NewsResult> getResult() {
        return result;
    }

    public void setResult(List<NewsResult> result) {
        this.result = result;
    }
}
