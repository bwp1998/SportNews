package com.example.sportnews.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportnews.R;
import com.example.sportnews.SearchActivity;
import com.example.sportnews.adapter.NewsAdapter;
import com.example.sportnews.model.NewsResult;
import com.example.sportnews.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    int progress = 0;
    private Activity activity;
    Menu menu;


    private static final String COUNTRY = "id";
    private static final String CATEGORY = "sports";
    private ArrayList<NewsResult> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_piggy);
        progressBar = findViewById(R.id.progress_bar);
        setProgressValue(progress);
        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.setNews(COUNTRY,CATEGORY);
        newsViewModel.getNews().observe(this, newsRequest -> {
            List<NewsResult> list = newsRequest.getResult();
            results.addAll(list);
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        });

        setupRecyclerview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent goToSearch = new Intent(MainActivity.this,SearchActivity.class);
        startActivity(goToSearch);
        return true;
    }

    private void setProgressValue(final int progress) {

        // set the progress
        progressBar.setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 10);
            }
        });
        thread.start();
    }

    private void setupRecyclerview() {
        if (adapter == null) {
            adapter = new NewsAdapter(MainActivity.this, results);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);

        } else {
            adapter.notifyDataSetChanged();
        }

    }


}
