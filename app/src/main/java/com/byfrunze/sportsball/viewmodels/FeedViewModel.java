package com.byfrunze.sportsball.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.byfrunze.sportsball.models.JSONNews;
import com.byfrunze.sportsball.network.RepositoryCallBack;

public class FeedViewModel extends ViewModel {
    private MutableLiveData<JSONNews> jsonNewsMutableLiveData;
    private RepositoryCallBack repositoryCallBack;

    public void init(){

        String q = "sport";
        repositoryCallBack = RepositoryCallBack.getInstance();
        jsonNewsMutableLiveData = repositoryCallBack.getNews(q);
    }

    public MutableLiveData<JSONNews> getJsonNewsMutableLiveData() {
        return jsonNewsMutableLiveData;
    }

    public void setJsonNewsMutableLiveData(String q) {
        jsonNewsMutableLiveData = repositoryCallBack.getNews(q);
    }
}
