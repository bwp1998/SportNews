package com.example.sportnews.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsResult implements Parcelable {
    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("urlToImage")
    @Expose
    private String image;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("publishedAt")
    @Expose
    private String date;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected NewsResult(Parcel in) {
        author = in.readString();
        image = in.readString();
        content = in.readString();
        title = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(image);
        dest.writeString(content);
        dest.writeString(title);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsResult> CREATOR = new Creator<NewsResult>() {
        @Override
        public NewsResult createFromParcel(Parcel in) {
            return new NewsResult(in);
        }

        @Override
        public NewsResult[] newArray(int size) {
            return new NewsResult[size];
        }
    };
}
