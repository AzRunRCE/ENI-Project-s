package fr.eni.ecole.androkado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import fr.eni.ecole.androkado.bll.ArticleManager;
import fr.eni.ecole.androkado.bo.Article;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG_ANDROKADO";
    private Article article = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        article = ArticleManager.getArticle(1);

        TextView tvNom =  findViewById(R.id.tv_article);
        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvPrix = findViewById(R.id.tv_prix);
        RatingBar rating = findViewById(R.id.rating_article);
        ToggleButton toggle = findViewById(R.id.btn_achete);
        CheckBox ckActive = findViewById(R.id.ck_active);

        tvNom.setText(article.getNom());
        tvDescription.setText(article.getDescription());
        tvPrix.setText(String.valueOf(article.getPrix()));
        rating.setRating(article.getNote());
        toggle.setChecked(article.isAchete());
        ckActive.setChecked(article.isActive());
    }

    public void onClickUrl(View view)
    {
        Toast.makeText(this, article.getUrl(), Toast.LENGTH_LONG).show();
    }

    public void onClickAchat(View view)
    {
        article.setAchete(!article.isAchete());
        Log.i(TAG,"L'article est il acheté : "
                + ((ToggleButton)view).isChecked());
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Quitter")
                .setMessage("Voulez-vous quitter l'application?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
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
