package com.example.sportnews.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sportnews.R;
import com.example.sportnews.model.NewsResult;

public class DetailArticleActivity extends AppCompatActivity {

    ImageView ivImage;
    TextView tvTitle, tvDate, tvContent;
    public static final String EXTRA_ARTICLE = "extra article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);
        initContent();

        NewsResult result = getIntent().getParcelableExtra(EXTRA_ARTICLE);
        if (result != null) {
            String image = result.getImage();
            String title = result.getTitle();
            String date = result.getDate();
            String content = result.getContent();
            Glide.with(this).load(image).into(ivImage);
            tvTitle.setText(title);
            tvDate.setText(date);
            tvContent.setText(content);
        }

    }

    private void initContent() {
        ivImage = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tv_piggy);
        tvDate = findViewById(R.id.tv_relaseDate);
        tvContent = findViewById(R.id.tv_content);
    }
}
