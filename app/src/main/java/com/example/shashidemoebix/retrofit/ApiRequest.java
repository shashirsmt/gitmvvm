package com.example.shashidemoebix.retrofit;


import com.example.shashidemoebix.response.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("v2/top-headlines")
    Call<NewsModel> getMovieArticles(
            @Query("country") String query,
            @Query("category") String cat_query,
            @Query("apikey") String apiKey
    );
}
