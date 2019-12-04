package fr.eni.ecole.demofichier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSaveFile(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("monFichier.txt", Context.MODE_PRIVATE);
                    fos.write("Mon texte a enregistré".getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    protected void afficher(String txt){
        Toast.makeText(MainActivity.this,
                txt,
                Toast.LENGTH_LONG).show();
    }

    public void onReadFile(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileInputStream fis = null;
                try {
                    fis = openFileInput("monFichier.txt");
                    byte[] buffer = new byte[1024];
                    StringBuffer texte = new StringBuffer();
                    while (fis.read(buffer) != -1){
                        texte.append(new String(buffer));
                    }

                    Log.v("TAG", texte.toString());

                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
