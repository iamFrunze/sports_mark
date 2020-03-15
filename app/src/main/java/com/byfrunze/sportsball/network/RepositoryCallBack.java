package com.byfrunze.sportsball.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.byfrunze.sportsball.models.JSONNews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryCallBack {
    public static RepositoryCallBack repositoryCallBack;

    public static RepositoryCallBack getInstance() {
        if(repositoryCallBack == null){
            repositoryCallBack = new RepositoryCallBack();
        }
        return repositoryCallBack;
    }

    public MutableLiveData<JSONNews> getNews(String q){
        MutableLiveData<JSONNews> news = new MutableLiveData<>();
        String apiKey = "e1bb478f4fa840648e24222424db0cdc";
        NetworkService.getInstance().getApi().getInfo(apiKey, "ru", q)
                .enqueue(new Callback<JSONNews>() {
            @Override
            public void onResponse(Call<JSONNews> call, Response<JSONNews> response) {
                news.setValue(response.body());
            }

            @Override
            public void onFailure(Call<JSONNews> call, Throwable t) {
            }
        });
        return news;
    }
}
