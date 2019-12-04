package fr.eni.ecole.androkado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class InfoUrlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(InfoUrlActivity.this,
                "Affichage de la nouvelle activité",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home :
                    //Test si un ParentActivityName est présent dans le manifest
                    if(getParentActivityIntent() == null){
                        onBackPressed();
                    }
                    else{
                        NavUtils.navigateUpFromSameTask(this);
                    }
                    return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
