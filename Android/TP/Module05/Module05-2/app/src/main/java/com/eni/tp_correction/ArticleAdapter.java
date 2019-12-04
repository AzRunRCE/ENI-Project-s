package com.eni.tp_correction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eni.tp_correction.bo.Article;

import java.util.ArrayList;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by quentin for TP_Correction on 2019-11-30.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private ArrayList<Article> arrayListArticle;

    public ArticleAdapter(ArrayList<Article> arrayListArticle,Context context) {


        this.arrayListArticle = arrayListArticle;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        TextView textViewArticle;
        MaterialRatingBar materialRatingBar;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewArticle = itemView.findViewById(R.id.textViewTitreArticle);
            materialRatingBar = itemView.findViewById(R.id.materialRatingBar);
        }
    }
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_holder, parent, false);
        return new ArticleViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ArticleViewHolder holder, int position) {
        final Article articleAffiche = arrayListArticle.get(position);
        final Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToDetail = new Intent(context,DetailActivity.class);
                intentToDetail.putExtra(DetailActivity.TAG_ARTICLE,articleAffiche);
                context.startActivity(intentToDetail);
            }
        });
        holder.textViewArticle.setText(articleAffiche.getTitre());
        holder.materialRatingBar.setRating(articleAffiche.getRating());
    }

    @Override
    public int getItemCount() {
        return arrayListArticle.size();
    }
}
