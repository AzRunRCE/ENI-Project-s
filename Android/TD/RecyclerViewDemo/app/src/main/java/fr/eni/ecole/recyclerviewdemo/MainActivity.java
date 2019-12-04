package fr.eni.ecole.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import fr.eni.ecole.recyclerviewdemo.adapters.CandyAdapter;
import fr.eni.ecole.recyclerviewdemo.bll.CandyManager;
import fr.eni.ecole.recyclerviewdemo.bo.Candy;

public class MainActivity extends AppCompatActivity {

    private List<Candy> candies;
    private CandyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);

        candies = CandyManager.getList();
        //Adapter
        adapter = new CandyAdapter(MainActivity.this,
                        R.layout.item_card_list,
                        candies,
                        new CandyAdapter.CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, Candy candy) {
                                Toast.makeText(MainActivity.this,
                                        "Candy : " + candy.getNom(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });

        recyclerView.setAdapter(adapter);

        //Permet de gérer le type d'affichage
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);

    }
}
