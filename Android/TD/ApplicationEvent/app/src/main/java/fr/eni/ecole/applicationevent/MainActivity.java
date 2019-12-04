package fr.eni.ecole.applicationevent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import fr.eni.ecole.applicationevent.bo.Personne;


public class MainActivity extends AppCompatActivity {

    private static String TAG_APP = "TAG_LOG";
    private EditText monText;
    private Button monButton;
    private View maView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monText = (EditText) findViewById(R.id.editText);
        monButton = findViewById(R.id.buttonEdt);
        maView = findViewById(R.id.context_view);
        /*
        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG_APP, "SetOnclickListener");
            }
        });
        */

        monButton.setOnClickListener(new EcouteurClic());

        Log.v(TAG_APP, "OnCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG_APP, "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.v(TAG_APP, "OnResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG_APP, "OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG_APP, "OnRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG_APP, "OnPause");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG_APP, "OnDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("TEXTEINPUT", "Laurent Bouvet");
        Log.v(TAG_APP, "SaveInstance" );
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){
            String recup = savedInstanceState.getString("TEXTEINPUT");
            Log.v(TAG_APP, "Donnée" + recup);
        }

    }

    public void onClickName(View view) {

        Log.v(TAG_APP, "Input " + monText.getText().toString());

    }

    public void onToastClick(View view) {

        Toast.makeText(MainActivity.this,
                "Message Toast",
                Toast.LENGTH_LONG)
                .show();

    }

    public void onAlertDialogClick(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Message AlertDialog")
                .setMessage("Question alerte ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,
                                "Action oui",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Non
                    }
                }).create().show();

    }

    public void onSnackBarClick(View view) {
        //Ne pas oublier d'importer la librairie
        Snackbar.make(maView, "Mon message"
                , Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Action désirée
                    }
                }).show();
    }

    public void onActiviteClick(View view) {
        Intent intent = new Intent(MainActivity.this,
                PersonneActivity.class);

        Personne p = new Personne();
        p.setNom("Dupond");
        p.setPrenom("Alain");

        //Envoyer des informations à l'autre activité
        intent.putExtra("texte", "Mon texte");
        intent.putExtra("personne", p);

        startActivity(intent);

    }

    private class EcouteurClic implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.v(TAG_APP, "Class Interne");
        }
    }
}



