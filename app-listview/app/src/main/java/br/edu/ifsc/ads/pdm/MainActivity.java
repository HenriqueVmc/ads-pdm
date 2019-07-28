package br.edu.ifsc.ads.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.list);

        list.add("Cupcake");
        list.add("Donut");
        list.add("Eclair");
        list.add("Froyo");
        list.add("Gingerbread");
        list.add("Honeycomb");
        list.add("Ice Cream");
        list.add("Jelly Bean");
        list.add("KitKat");
        list.add("Lollipop");
        list.add("Marshmallow");
        list.add("Nougat");
        list.add("Oreo");
        list.add("Pie");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valor = list.get(position);
                Toast.makeText(getBaseContext(), valor, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
