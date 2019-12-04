package fr.eni.ecole.androkado.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.ecole.androkado.R;
import fr.eni.ecole.androkado.bo.Article;

public class ArticleAdapter extends
        RecyclerView.Adapter<ArticleAdapter.ViewHolder>{

    private ArticleItemClickListener listener;
    private List<Article> articles;
    private int resView;
    private Context context;

    public ArticleAdapter(Context context,
                          List<Article> data,
                          int resource,
                          ArticleItemClickListener listener){
        this.articles = data;
        this.resView = resource;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext())
                        .inflate(this.resView, parent, false);

        final ViewHolder viewHolder = new ViewHolder(mView);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onArticleItemClick(v, viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.nom.setText(article.getNom());
        holder.prix.setText(context.getString(R.string.valeur_prix,String.valueOf(article.getPrix())));
        //animation

        setScaleAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public static class ViewHolder
            extends RecyclerView.ViewHolder{

        public TextView nom;
        public TextView prix;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.nom = itemView.findViewById(R.id.info_nom_article);
            this.prix = itemView.findViewById(R.id.info_prix);

        }
    }

    public interface ArticleItemClickListener{
        public void onArticleItemClick(View v, int position);
    }


    private void setScaleAnimation(View itemView) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f,0,1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(1500);
        itemView.startAnimation(anim);

    }
}
