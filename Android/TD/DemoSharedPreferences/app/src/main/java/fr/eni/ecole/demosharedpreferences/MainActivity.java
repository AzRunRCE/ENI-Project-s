package fr.eni.ecole.demosharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String texte = sp.getString(IdentProvider.CLE_ACTIVITY, "");
        TextView edt = findViewById(R.id.txtPreferences);
        edt.setText(texte);
    }

    public void onSaveGlobal(View view) {
        EditText txtGlobal = findViewById(R.id.editText2);
        SharedPreferences sp = getSharedPreferences(IdentProvider.FICHIER,
                                                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(IdentProvider.CLE_GLOBAL,
                        txtGlobal.getText().toString());
        editor.apply();
    }

    public void onSaveLocal(View view) {

        EditText editText = findViewById(R.id.editText);
        //Gestion des préférences pour l'activité
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(IdentProvider.CLE_ACTIVITY,
                editText.getText().toString());
        //Renvoi un boolean
        //editor.commit();
        //Utilise un thread : tâche asynchrone
        editor.apply();

    }

    public void onSecondClick(View view) {

        Intent i = new Intent(MainActivity.this, CibleActivity.class);

        startActivity(i);

    }
}
