package br.edu.ifsc.pdm.appconversortemperatura;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ConverterTemperatura(View view) {

        RadioButton rdbF = findViewById(R.id.rdbF);
        RadioButton rdbK = findViewById(R.id.rdbK);
        EditText txtInput = findViewById(R.id.txtTemp);
        double temp = Double.valueOf(txtInput.getText().toString());

        String result = "", escala = "";

        if(rdbF.isChecked()){
            result = Double.toString(ConverterParaFahrenheit(temp));
            escala = " F°";
        }else if(rdbK.isChecked()){
            result = Double.toString(ConverterParaKelvin(temp));
            escala = " K°";
        }

        BigDecimal resultFormat = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
        setTemperatura(resultFormat.toString(), temp, escala);

        rdbK.setChecked(false);
        rdbF.setChecked(false);
    }

    private void setTemperatura(String result, double temp, String escala) {

        TextView lblResult = findViewById(R.id.lblResult);
        lblResult.setText(result + escala);
    }

    private double ConverterParaFahrenheit(double temp) {

        return (temp * 9/5) + 32;
    }

    private double ConverterParaKelvin(double temp) {

        return temp + 273.15;
    }

    public void onCheckedK(View view) {

        RadioButton rdbF = findViewById(R.id.rdbF);

        if(rdbF.isChecked()){
            rdbF.setChecked(false);
        }
    }

    public void onCheckedF(View view) {

        RadioButton rdbK = findViewById(R.id.rdbK);

        if(rdbK.isChecked()){
            rdbK.setChecked(false);
        }
    }
}
