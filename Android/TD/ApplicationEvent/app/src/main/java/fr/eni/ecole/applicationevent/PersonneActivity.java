package fr.eni.ecole.applicationevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.eni.ecole.applicationevent.bo.Personne;

public class PersonneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personne);

        Intent i = getIntent();

        if(i != null){
            String texte = i.getStringExtra("texte");
            Personne p = i.getParcelableExtra("personne");

            Log.v("TAG", p.getNom());

        }

    }

    public void onClickHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
