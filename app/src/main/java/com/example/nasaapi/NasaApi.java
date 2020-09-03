package com.example.nasaapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaApi {
    @GET("apod/?api_key=wWYANs1tL8T8WKqKTL05jgMvD4SMjpnftQUF08pS")
    Call<Post> getPosts(@Query("date") String date);
}
