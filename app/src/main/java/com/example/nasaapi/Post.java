package com.example.nasaapi;

import android.text.format.DateFormat;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("date")
    private String date;

    @SerializedName("media_type")
    private String media_type;

    @SerializedName("url")
    private String url;

    public String getDate() {
        return date;
    }

    public String getMediaType() {
        return media_type;
    }

    public String getUrl() {
        return url;
    }
}
