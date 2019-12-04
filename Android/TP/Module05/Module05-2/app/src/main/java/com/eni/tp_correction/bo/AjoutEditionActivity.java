package com.eni.tp_correction.bo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.eni.tp_correction.ArticleDao;
import com.eni.tp_correction.ConfigurationActivity;
import com.eni.tp_correction.R;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class AjoutEditionActivity extends AppCompatActivity {

    private EditText editTextTitre,editTextDescription,editTextUrl,editTextPrix;
    private Switch switchIsBought;
    private MaterialRatingBar materialRatingBarEnvie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_edition);

        editTextTitre = findViewById(R.id.editTextTitre);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextUrl = findViewById(R.id.editTextUrl);
        editTextPrix = findViewById(R.id.editTextPrix);
        switchIsBought = findViewById(R.id.switchIsBought);
        materialRatingBarEnvie = findViewById(R.id.materialRatingBarEnvie);

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigurationActivity.SHARED_PREF_CONF,MODE_PRIVATE);
        int prixdef = sharedPreferences.getInt(ConfigurationActivity.KEY_PRIX_DEF,50);
        editTextPrix.setText(String.valueOf(prixdef));

    }

    @Override
    protected void onStop() {
        super.onStop();
        ArticleDao dao = new ArticleDao(this);
        if( ! editTextTitre.getText().toString().isEmpty()&&
        !editTextDescription.getText().toString().isEmpty()&&
                !editTextUrl.getText().toString().isEmpty()&&
                !editTextPrix.getText().toString().isEmpty())
            dao.insert(new Article(
                editTextTitre.getText().toString(),
                editTextDescription.getText().toString(),
                editTextUrl.getText().toString(),
                Float.valueOf(editTextPrix.getText().toString()),
                materialRatingBarEnvie.getRating(),
                switchIsBought.isChecked()
        ));
    }
}
