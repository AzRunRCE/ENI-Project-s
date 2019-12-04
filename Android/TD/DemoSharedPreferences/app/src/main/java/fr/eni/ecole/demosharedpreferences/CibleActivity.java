package fr.eni.ecole.demosharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class CibleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cible);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getSharedPreferences(IdentProvider.FICHIER,
                                    MODE_PRIVATE);

        String info = sp.getString(IdentProvider.CLE_GLOBAL, "Default");

        TextView txt = findViewById(R.id.textView);

        txt.setText(info);

    }
}
