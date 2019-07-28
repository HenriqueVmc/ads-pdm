package br.edu.ifsc.ddm.pdm_app_imc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcularIMC(View view) {

        double peso, altura, imc;

        EditText txtPeso = findViewById(R.id.txtPeso);
        EditText txtAltura = findViewById(R.id.txtAltura);

        peso = Double.valueOf(txtPeso.getText().toString());
        altura = Double.valueOf(txtAltura.getText().toString());

        imc = (altura > 0) ? peso / (altura*altura) : 0;

        TextView lblIMC = findViewById(R.id.lblIMC);
        BigDecimal resultFormat = new BigDecimal(imc).setScale(2, RoundingMode.HALF_EVEN);

        lblIMC.setText("IMC: " + resultFormat.toString());
    }
}