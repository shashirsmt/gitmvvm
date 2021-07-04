package com.example.shashidemoebix.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.shashidemoebix.response.NewsModel;
import com.example.shashidemoebix.retrofit.ApiRequest;
import com.example.shashidemoebix.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static final String TAG = NewsRepository.class.getSimpleName();
    private ApiRequest apiRequest;


    public NewsRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<NewsModel> getMovieArticles(String query,String cat, String key) {
        final MutableLiveData<NewsModel> data = new MutableLiveData<>();
        apiRequest.getMovieArticles(query,cat, key)
                .enqueue(new Callback<NewsModel>() {
                    @Override
                    public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                            Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
