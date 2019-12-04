package com.eni.tp_correction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eni.tp_correction.bo.AjoutEditionActivity;
import com.eni.tp_correction.bo.Article;

import java.util.ArrayList;

public class ListeArticlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerViewArticles = findViewById(R.id.recyclerViewListeArticles);

        ArrayList<Article> arrayList = new ArrayList<>();
        arrayList.add(new Article("Pain au chocolat","viennoiserie au chocolat","https://wikipedia.org/Pain_au_chocolat", 1.0f,5.0f,false));
        arrayList.add(new Article("Vélo","Véhicule avec deux roues et des pédales","https://wikipedia.org/Vélo", 500.0f,3.0f,false));
        arrayList.add(new Article("Tesla Cybertruck","Véhicule de science fiction","https://wikipedia.org/Tesla", 39990.0f,5.0f,false));



        ArticleDao dao = new ArticleDao(this);
        dao.insertAll(arrayList);

        SharedPreferences sp = this.getSharedPreferences(ConfigurationActivity.SHARED_PREF_CONF, Context.MODE_PRIVATE);
        boolean triprix = sp.getBoolean(ConfigurationActivity.KEY_TRI_PRIX,false);

        ArticleAdapter articleAdapter = new ArticleAdapter(dao.getAll(triprix),this);
        recyclerViewArticles.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArticles.setAdapter(articleAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.toolbar_item_liste_config){
            startActivity(new Intent(this,ConfigurationActivity.class));
            return true;
        }else if(item.getItemId() == R.id.toolbat_item_liste_add){

            startActivity(new Intent(this, AjoutEditionActivity.class));

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_liste_article,menu);
        return true;
    }
}
