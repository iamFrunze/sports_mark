package com.byfrunze.sportsball.activities.ui.feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.models.Article;
import com.byfrunze.sportsball.models.JSONNews;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public interface OnClickListener{
        void onClick(Article article, int pos);
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private List<Article> articles;


    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articles.get(position);
        Picasso.get()
                .load(article.getUrlToImage())
                .placeholder(R.drawable.back_header)
                .into(holder.imageViewFeed);
        holder.textViewFeedTitle.setText(article.getTitle());
        holder.textViewAuthorFeed.setText(article.getAuthor());
        holder.textViewDateFeed.setText(String.valueOf(article.getPublishedAt()));
        holder.textViewDescriptionFeed.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textViewFeedTitle)
        TextView textViewFeedTitle;
        @BindView(R.id.textViewAuthorFeed)
        TextView textViewAuthorFeed;
        @BindView(R.id.textViewDateFeed)
        TextView textViewDateFeed;
        @BindView(R.id.textViewDescriptionFeed)
        TextView textViewDescriptionFeed;
        @BindView(R.id.imageViewFeed)
        ImageView imageViewFeed;
        @BindView(R.id.cardItemFeed)
        MaterialCardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(v->{
                if(onClickListener != null) onClickListener.onClick(articles.get(getAdapterPosition()), getAdapterPosition());
            });

        }
    }
}
