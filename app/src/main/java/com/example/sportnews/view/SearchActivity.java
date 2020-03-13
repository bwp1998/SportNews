package com.example.sportnews.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sportnews.R;
import com.example.sportnews.adapter.NewsAdapter;
import com.example.sportnews.model.NewsResult;
import com.example.sportnews.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsViewModel newsViewModel;
    int progress = 0;
    private Activity activity;
    Menu menu;
    private static final String COUNTRY = "id";
    private static final String CATEGORY = "sports";
    private ArrayList<NewsResult> results = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.rv_piggy);
        progressBar = findViewById(R.id.progress_bar);
        setProgressValue(progress);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

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
        inflater.inflate(R.menu.menu_search2, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search2);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                newsViewModel.searchNews(COUNTRY, CATEGORY, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
            adapter = new NewsAdapter(SearchActivity.this, results);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);

        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
