package br.edu.ifsc.ads.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewPersonalizada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);

        Frutas frutas = new Frutas();

        ArrayList<Fruta> listaFrutas = new ArrayList<Fruta>();

        for (Fruta f: frutas.FRUTAS) {
            listaFrutas.add(f);
        }

        FrutaListAdapter adapter = new FrutaListAdapter(getApplicationContext(), R.layout.my_layout_list_view_frutas, listaFrutas);

        listView.setAdapter(adapter);
    }
}
