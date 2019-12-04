package fr.eni.ecole.monagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fr.eni.ecole.monagenda.adapters.PersonneAdapter;
import fr.eni.ecole.monagenda.bll.PersonneManager;
import fr.eni.ecole.monagenda.bo.Personne;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private List<Personne> liste;
    private PersonneAdapter adapter;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liste = PersonneManager.getPersonnes();

        adapter = new PersonneAdapter(MainActivity.this,
                                        R.layout.item_personne,
                                        liste);
        //Récupère la ListView
        lstView = findViewById(R.id.list_personnes);

        //Associe l'adapter avec la ListView
        lstView.setAdapter(adapter);

        //Gestion du click sur la liste
        lstView.setOnItemClickListener(MainActivity.this);

    }

    //Méthode de l'interface AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {
        //On récupère l'objet depuis l'adapteur en fonction de sa position
        Personne p = (Personne) parent.getItemAtPosition(position);

        Toast.makeText(MainActivity.this,
                p.toString()
                , Toast.LENGTH_LONG).show();
    }

}
