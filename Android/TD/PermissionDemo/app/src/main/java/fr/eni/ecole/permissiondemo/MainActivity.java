package fr.eni.ecole.permissiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static private int PERMISSION_READ_EXTERNAL = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ne pas oublier de faire la demande dans le manifest.xml
        //Vérifier que l'application n'a pas le droit
        if(ContextCompat.checkSelfPermission(
                MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE
                )
            != PackageManager.PERMISSION_GRANTED){

            //Tester si on déjà demandé le droit
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            )){
                //Je peux poser la question à l'utilisateur
                //AlertDialog ou SnackBar
                //Si oui on redemande la permission

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Permission ?")
                        .setMessage("Accepter la demande permission ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(
                                        MainActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                        PERMISSION_READ_EXTERNAL
                                );
                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(
                                        MainActivity.this,
                                        "Impossible de continuer l'application",
                                        Toast.LENGTH_SHORT
                                ).show();
                                //Je détruis l'activité
                                finish();
                            }
                        }).show();


            }
            else{
                //première demande du ou des droits
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_READ_EXTERNAL
                );
            }

        }
        else{
            //Continue mon application
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Vérifie la réponse de l'utilisateur
        switch (requestCode){
            case PERMISSION_READ_EXTERNAL :
                if(grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //continuer mon application
                }
                else{
                    Toast.makeText(
                            MainActivity.this,
                            "Impossible de continuer l'application",
                            Toast.LENGTH_SHORT
                    ).show();
                    //Je détruis l'activité
                    finish();
                }
                break;
        }

    }
}
