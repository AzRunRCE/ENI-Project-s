package com.eni.tp_correction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eni.tp_correction.bo.Article;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG_ARTICLE = "TAG_ARTICLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final Article inArticle = new Article("Pain au chocolat","Viennoiserie au chocolat", "https://wikipedia.org/Pain_au_Chocolat",0.9f,5.0f,true);

        final Article inArticle = getIntent().getParcelableExtra(DetailActivity.TAG_ARTICLE);

        TextView textViewTitre = findViewById(R.id.textViewTitre);
        TextView textViewDescription = findViewById(R.id.textViewDescription);;
        TextView textViewPrix= findViewById(R.id.textViewPrix);
        RatingBar ratingBarDegre = findViewById(R.id.materialRatingBar);
        ToggleButton toggleButtonIsBought= findViewById(R.id.toggleButton);
        final ImageButton imageButtonWeb= findViewById(R.id.imageButton);

        textViewTitre.setText(inArticle.getTitre());
        textViewDescription.setText(inArticle.getDescription());
        textViewPrix.setText(String.valueOf(inArticle.getPrix()));
        ratingBarDegre.setRating(inArticle.getRating());
        toggleButtonIsBought.setChecked(inArticle.isBought());
        imageButtonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToInfoUrlAct = new Intent(DetailActivity.this,InfoUrlActivity.class);
                intentToInfoUrlAct.putExtra(InfoUrlActivity.TAG_ARTICLE,inArticle);
                startActivity(intentToInfoUrlAct);
            }
        });

        toggleButtonIsBought.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                inArticle.setBought(b);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.toolbar_item_edit){
            return true;
        }else if(item.getItemId() == R.id.toolbar_item_send){
            return true;

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_detail,menu);
        return true;
    }
}
