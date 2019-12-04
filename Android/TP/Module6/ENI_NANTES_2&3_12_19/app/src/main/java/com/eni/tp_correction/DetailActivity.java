package com.eni.tp_correction;

import android.Manifest;
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
import androidx.core.app.ActivityCompat;

import com.eni.tp_correction.bo.Article;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG_ARTICLE = "TAG_ARTICLE";
    public static final String ARTICLE_EXTRA = "article_extra";
    public static final int REQUEST_CODE = 42;
    private  boolean sendSMSAndContactPermission = false;
    Article inArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final Article inArticle = new Article("Pain au chocolat","Viennoiserie au chocolat", "https://wikipedia.org/Pain_au_Chocolat",0.9f,5.0f,true);

        inArticle = getIntent().getParcelableExtra(DetailActivity.TAG_ARTICLE);

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

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.SEND_SMS}, REQUEST_CODE);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.toolbar_item_edit){
            return true;
        }else if(item.getItemId() == R.id.toolbar_item_send && sendSMSAndContactPermission){
            Intent intentToSelectionContact = new Intent(this, SelectionContactActivity.class);
            intentToSelectionContact.putExtra(ARTICLE_EXTRA,inArticle);
            startActivity(intentToSelectionContact);
            return true;

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_detail,menu);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE
                && grantResults.length > 0
                && grantResults[0] == PERMISSION_GRANTED
                && grantResults[1] == PERMISSION_GRANTED){
            sendSMSAndContactPermission = true;
        }
    }
}
