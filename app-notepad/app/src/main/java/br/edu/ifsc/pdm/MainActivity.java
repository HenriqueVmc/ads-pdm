package br.edu.ifsc.pdm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Notas notas;
    TextInputEditText txtInput;
    String textNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput = findViewById(R.id.txtInput);
        textNota = txtInput.getText().toString();

        notas = new Notas(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // SALVAR CONTEÚDO
                notas.salvarNota(textNota);
            }
        });

        notas.recuperarNota();
    }

    @Override
    protected void onStart() {
        super.onStart();
        txtInput.setText(notas.recuperarNota());
    }

    @Override
    protected void onPause() {
        super.onPause();
        notas.salvarNota(txtInput.getText().toString());
    }
}
