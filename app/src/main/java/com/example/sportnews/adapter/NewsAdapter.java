package com.example.sportnews.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportnews.R;
import com.example.sportnews.model.NewsResult;
import com.example.sportnews.view.CustomOnItemListener;
import com.example.sportnews.view.DetailArticleActivity;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private NewsAdapter adapter;
    private Activity activity;
    private ArrayList<NewsResult> arrayList;

    public NewsAdapter(Activity activity, ArrayList<NewsResult> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_list_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsResult result = arrayList.get(position);

        String url_poster = result.getImage();
        Glide.with(activity).load(url_poster).into(holder.ivPoster);

        holder.tvTitle.setText(result.getTitle());
        holder.tvAuthor.setText(result.getAuthor());
        holder.tvDate.setText(result.getDate());
        holder.cvNews.setOnClickListener(new CustomOnItemListener(position, (view, position1) -> {
            Intent intent = new Intent(activity, DetailArticleActivity.class);
            intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE, result);
            activity.startActivity(intent);
        }));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        CardView cvNews;
        TextView tvTitle, tvDate, tvAuthor;
        ImageView ivPoster;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            cvNews = itemView.findViewById(R.id.cv_new);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvTitle = itemView.findViewById(R.id.tv_piggy);
            tvDate = itemView.findViewById(R.id.tv_relaseDate);
            ivPoster = itemView.findViewById(R.id.iv_piggy);
        }
    }
}
