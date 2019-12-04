package fr.eni.ecole.androkado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

import fr.eni.ecole.androkado.bll.ArticleManager;
import fr.eni.ecole.androkado.bo.Article;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "TAG_ANDROKADO";
    private Article article = null;

    private View main_context;

    private TextView tvNom ;
    private TextView tvDescription;
    private TextView tvPrix;
    private RatingBar rating;
    private ToggleButton toggle;
    private CheckBox ckActive;

    private final static int PERMISSION_READ_CONTATS = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        if(intent == null){
            //TODO : Ajouter un toast
            finish();
        }

        article = intent.getParcelableExtra("article");

        main_context = findViewById(R.id.main_context);

        tvNom =  findViewById(R.id.tv_article);
        tvDescription = findViewById(R.id.tv_description);
        tvPrix = findViewById(R.id.tv_prix);
        rating = findViewById(R.id.rating_article);
        toggle = findViewById(R.id.btn_achete);
        ckActive = findViewById(R.id.ck_active);

        getPermissions();
    }

    public void onClickUrl(View view)
    {
        //Toast.makeText(this, article.getUrl(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DetailActivity.this
                                ,InfoUrlActivity.class);

        //Transférer l'article à la seconde activité
        intent.putExtra("art", article);

        startActivity(intent);
    }

    public void onClickAchat(View view)
    {
        article.setAchete(!article.isAchete());
        Log.i(TAG,"L'article est il acheté : "
                + ((ToggleButton)view).isChecked());
    }


    /**
     * Gestion affichage
     */
    private void afficheArticle(){
        tvNom.setText(article.getNom());
        tvDescription.setText(article.getDescription());
        tvPrix.setText(getString(R.string.valeur_prix, String.valueOf(article.getPrix())));
        rating.setRating(article.getNote());
        toggle.setChecked(article.isAchete());
        ckActive.setChecked(article.isActive());
    }

    //Demande des permissions
    protected void getPermissions(){
        //Savoir si on ne possède pas la permission
        if(ContextCompat.checkSelfPermission(DetailActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){

            //Je regarde s'il y a eu une première demande
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    DetailActivity.this
                        ,Manifest.permission.READ_CONTACTS)){
                Snackbar.make(main_context,
                        R.string.msg_snackbar_perm,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.label_ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(DetailActivity.this,
                                        new String[] {Manifest.permission.READ_CONTACTS},
                                        PERMISSION_READ_CONTATS);
                            }
                        }).show();
            }
            else{
                //Première demande de permission
                ActivityCompat.requestPermissions(DetailActivity.this,
                        new String[] {Manifest.permission.READ_CONTACTS},
                        PERMISSION_READ_CONTATS);
            }

        }
        else{
            afficheArticle();
        }

    }

    //Traitement de la réponse de l'utilisateur pour les permissions
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {


        switch (requestCode){
            case PERMISSION_READ_CONTATS :
                if(grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    afficheArticle();
                }
                else{
                    Toast.makeText(DetailActivity.this,
                            "La permission est nécessaire pour le fonctionnement de l'application!!",
                            Toast.LENGTH_LONG).show();

                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    //region Gestion du menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_details, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //endregion
}
