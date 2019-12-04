package fr.eni.ecole.demotoolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.R.drawable.star_on);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    //Permet de définir le fichier xml de menu utilisé
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Permet de définir l'action en fonction du menu cliqué
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_un :

                //TODO : appel d'une autre activité

                return true;

            case R.id.menu_deux :

                Toast.makeText(this, "Menu deux", Toast.LENGTH_LONG)
                        .show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
