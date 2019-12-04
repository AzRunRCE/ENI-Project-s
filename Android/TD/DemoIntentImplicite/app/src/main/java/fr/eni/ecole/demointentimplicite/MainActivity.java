package fr.eni.ecole.demointentimplicite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void envoiSmsClick(View view) {
        //Appel d'une application qui sait envoyer des sms
        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.google.fr"));
                                //Uri.parse("smsto:0612042587"));
        if(intent.resolveActivity(getPackageManager())
            != null){
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this,
                    "Aucune activité disponible",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void intentFilterClick(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setType("application/pdf");
        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }


    
}
