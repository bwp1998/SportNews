package com.example.sportnews.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.sportnews.R;
import com.example.sportnews.model.NewsResult;

public class DetailArticleActivity extends AppCompatActivity {

    ImageView ivImage;
    TextView tvTitle, tvDate, tvContent;
    public static final String EXTRA_ARTICLE = "extra article";
    private ProgressBar progressBar;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail_article);
        progressBar = findViewById(R.id.progress_detail);
        setProgressValue(progress);
        initContent();
        NewsResult result = getIntent().getParcelableExtra(EXTRA_ARTICLE);

        if (result != null) {
            String image = result.getImage();
            String title = result.getTitle();
            String date = result.getDate();
            String content = result.getContent();
            Glide.with(this).load(image).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return true;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(ivImage);

            tvTitle.setText(title);
            tvDate.setText(date);
            tvContent.setText(content);
        }

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

    private void initContent() {
        ivImage = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tv_piggy);
        tvDate = findViewById(R.id.tv_relaseDate);
        tvContent = findViewById(R.id.tv_content);
    }
}
