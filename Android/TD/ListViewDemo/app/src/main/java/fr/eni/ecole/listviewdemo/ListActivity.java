package fr.eni.ecole.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import fr.eni.ecole.listviewdemo.adapters.CandyAdapter;
import fr.eni.ecole.listviewdemo.bll.CandyManager;
import fr.eni.ecole.listviewdemo.bo.Candy;

public class ListActivity extends AppCompatActivity {

    private CandyAdapter adapter;
    private List<Candy> candys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        candys = CandyManager.getList();

        adapter = new CandyAdapter(ListActivity.this,
                R.layout.item_list,
                candys);

        ListView liste = findViewById(R.id.lstv_candy);

        //Associe les deux
        liste.setAdapter(adapter);

    }

    public void ajouterCandyClick(View view) {

        candys.add(new Candy("Bonbon", "Rouge", 0.50F));

        adapter.notifyDataSetChanged();

    }
}
