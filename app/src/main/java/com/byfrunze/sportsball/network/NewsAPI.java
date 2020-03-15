package com.byfrunze.sportsball.network;

import com.byfrunze.sportsball.models.JSONNews;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {

    @GET("/v2/everything")
    Call<JSONNews> getInfo(
            @Query("apiKey") String apiKey,
            @Query("language") String language,
            @Query("q") String q
            );
}
