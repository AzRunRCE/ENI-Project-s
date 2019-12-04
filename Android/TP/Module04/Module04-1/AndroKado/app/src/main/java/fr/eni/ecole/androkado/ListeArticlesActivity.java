package fr.eni.ecole.androkado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.util.List;

import fr.eni.ecole.androkado.adapters.ArticleAdapter;
import fr.eni.ecole.androkado.bll.ArticleManager;
import fr.eni.ecole.androkado.bo.Article;

public class ListeArticlesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Article> articles;
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);

        recyclerView = findViewById(R.id.rv_articles);
        articles = ArticleManager.getArticles();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);

        adapter = new ArticleAdapter(ListeArticlesActivity.this,
                    articles,
                    R.layout.my_article_view,
                    new ArticleAdapter.ArticleItemClickListener() {
            @Override
            public void onArticleItemClick(View v, int position) {
                Article article = articles.get(position);

                Intent intent = new Intent(ListeArticlesActivity.this, DetailActivity.class);
                intent.putExtra("article", article);
                startActivity(intent);

            }
        });

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        final AlertDialog.Builder builder = new AlertDialog.Builder(ListeArticlesActivity.this);
        builder.setTitle("Quitter")
                .setMessage("Voulez-vous quitter l'application?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListeArticlesActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();

    }
}
