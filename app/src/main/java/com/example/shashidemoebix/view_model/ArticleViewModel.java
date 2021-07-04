package com.example.shashidemoebix.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shashidemoebix.repository.NewsRepository;
import com.example.shashidemoebix.response.NewsModel;


public class ArticleViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;
    private LiveData<NewsModel> articleResponseLiveData;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository();
    }
    public LiveData<NewsModel> getArticleResponseLiveData(String query,String cQuery, String key) {
        return articleResponseLiveData= newsRepository.getMovieArticles(query,cQuery, key);
    }
}
