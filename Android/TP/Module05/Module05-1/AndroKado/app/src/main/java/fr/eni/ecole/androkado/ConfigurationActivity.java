package fr.eni.ecole.androkado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import fr.eni.ecole.androkado.Contracts.ConstLibrary;


public class ConfigurationActivity extends AppCompatActivity {

    private EditText etPrixDefaut;
    private Switch tri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        setTitle("Configuration");

        SharedPreferences spIntra = getSharedPreferences(ConstLibrary.PREF_CONFIGURATION,MODE_PRIVATE);
        String valeurPrixDefaut = spIntra.getString(ConstLibrary.CLE_PRIX_DEFAUT,"");
        boolean valeurTri = spIntra.getBoolean(ConstLibrary.CLE_TRI,false);

        etPrixDefaut = findViewById(R.id.et_prix_defaut);
        tri =  findViewById(R.id.s_tri_prix);

        etPrixDefaut.setText(valeurPrixDefaut);
        tri.setChecked(valeurTri);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        SharedPreferences sp = getSharedPreferences(ConstLibrary.PREF_CONFIGURATION,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        /*
        Suppression d'une clé des préférences
        editor.remove(ConstLibrary.CLE_PRIX_DEFAUT);
         */
        /*
        Supprime toutes les clés
        editor.clear();
         */

        editor.putString(ConstLibrary.CLE_PRIX_DEFAUT,etPrixDefaut.getText().toString());
        editor.putBoolean(ConstLibrary.CLE_TRI,tri.isChecked());
        editor.apply();
    }
}
