package com.example.shashidemoebix.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shashidemoebix.R;
import com.example.shashidemoebix.adapter.NewsArticleAdapter;
import com.example.shashidemoebix.model.Article;
import com.example.shashidemoebix.view_model.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.shashidemoebix.constants.AppConstant.API_KEY;
import static com.example.shashidemoebix.constants.AppConstant.Category_QUERY;
import static com.example.shashidemoebix.constants.AppConstant.Country_QUERY;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular_article;
    private LinearLayoutManager layoutManager;
    private NewsArticleAdapter adapter;
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        getMovieArticles();
    }
    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        progress_circular_article = (ProgressBar) findViewById(R.id.progress_circular_article);
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new NewsArticleAdapter(MainActivity.this, articleArrayList);
        my_recycler_view.setAdapter(adapter);

        // View Model
        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

    }

    /**
     * get movies articles from news api
     *
     * @param @null
     */
    private void getMovieArticles() {
        articleViewModel.getArticleResponseLiveData(Country_QUERY,Category_QUERY, API_KEY).observe(this, articleResponse -> {
            if (articleResponse != null) {
                progress_circular_article.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticles();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
