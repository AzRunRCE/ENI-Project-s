package com.eni.tp_correction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by quentin for TP_Correction on 2019-12-01.
 */
public class ConfigurationActivity extends AppCompatActivity {
    public static final String SHARED_PREF_CONF = "shared_pref_conf";
    public static final String KEY_PRIX_DEF = "key_prix_def";
    public static final String KEY_TRI_PRIX = "key_tri_prix";
    EditText editTextPrixDef;
    Switch switchTriPrix;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        sharedPreferences = getSharedPreferences(SHARED_PREF_CONF,MODE_PRIVATE);
        editTextPrixDef = findViewById(R.id.editTextPrixDef);
        switchTriPrix = findViewById(R.id.switchTriPrix);

        //Set values of SharedPref
        editTextPrixDef.setText(String.valueOf(sharedPreferences.getInt(KEY_PRIX_DEF,50)));
        switchTriPrix.setChecked(sharedPreferences.getBoolean(KEY_TRI_PRIX,false));
    }

    @Override
    protected void onStop() {
        //Save
        boolean triPrix = switchTriPrix.isChecked();
        int prixDefaut = Integer.valueOf(editTextPrixDef.getText().toString());
        sharedPreferences.edit().putBoolean(KEY_TRI_PRIX,triPrix).apply();
        sharedPreferences.edit().putInt(KEY_PRIX_DEF,prixDefaut).apply();
        super.onStop();
    }
}
