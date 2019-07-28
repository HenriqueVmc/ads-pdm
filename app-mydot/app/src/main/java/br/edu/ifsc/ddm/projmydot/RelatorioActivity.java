package br.edu.ifsc.ddm.projmydot;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RelatorioActivity extends AppCompatActivity {

    private SQLiteDatabase _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        ListView listView = findViewById(R.id.list);

        RegistroPonto ponto = new RegistroPonto();
        ArrayList<RegistroPonto> registros = new ArrayList<>();

        _db = openOrCreateDatabase("dbmydot", MODE_PRIVATE, null);
        registros = ponto.getRelatorioRegistros(_db);

        RegistroPontoListAdapter adapter = new RegistroPontoListAdapter(getApplicationContext(), R.layout.my_listview_relatorio, registros);
        listView.setAdapter(adapter);
    }
}
