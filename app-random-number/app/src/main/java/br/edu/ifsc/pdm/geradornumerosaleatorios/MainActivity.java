package br.edu.ifsc.pdm.geradornumerosaleatorios;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //public void alteraTexto(View view) {
        // o obj que recebe a intenção trata-se de uma View, ou seja, um Componente Visual
        // Button btn = (Button)view;
        // btn.setText("Bora testar essa bagaça");

        // Classe R, gerada em tmp de compilação, detém tds os recursos da app
        // para cada, gera uma prop pública com identificador, Ex.:
        // EditText txt = (EditText) findViewById(R.id.txt);
        // TextView lbl = (TextView) findViewById(R.id.lbl);

        // lbl.setText(txt.getText().toString());
    //}

    public void sortearValor(View view) {
        TextView lblResultado = (TextView)findViewById(R.id.lblResultado);
        EditText txtN1 = (EditText)findViewById(R.id.txtN1);
        EditText txtN2 = (EditText)findViewById(R.id.txtN2);

        if(!txtN1.getText().toString().isEmpty() && !txtN2.getText().toString().isEmpty()) {
            int maior = 0;
            int n1 = Integer.parseInt(txtN1.getText().toString());
            int n2 = Integer.parseInt(txtN2.getText().toString());

            if(n1 >= n2){
                maior = n1;
                n1 = n2;
                n2 = maior;
            }

            int resultado = new Random().nextInt((n2 - n1) + 1) + n1;

            lblResultado.setText(Integer.toString(resultado));
            lblResultado.setTextSize(100);
            lblResultado.setTextColor(Color.rgb(32, 96, 64));

        }else{
            Toast.makeText(getApplicationContext(), "Informe o intervalo!", Toast.LENGTH_SHORT).show();
        }
    }
}