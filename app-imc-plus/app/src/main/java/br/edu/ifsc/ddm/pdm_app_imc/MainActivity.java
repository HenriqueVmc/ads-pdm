package br.edu.ifsc.ddm.pdm_app_imc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

        if(altura > 0 && peso > 0) {

            imc =  peso / (altura * altura);

            BigDecimal imcFormatado = new BigDecimal(imc).setScale(2, RoundingMode.HALF_EVEN);

            setIMC((classificaIMC(imcFormatado.doubleValue())));
        }
    }

    private void setIMC(Imc imc) {

        TextView lblIMC = findViewById(R.id.lblIMC);
        ImageView imgIMC = findViewById(R.id.imgIMC);

        imgIMC.setImageDrawable(imc.getImg());
        lblIMC.setText(imc.getMensagem());
    }

    private Imc classificaIMC(double imc) {

        Imc objImc = null;

        //Drawable imgPerfil = getResources().getDrawable(R.drawable.perfil);
        Drawable imgAbaixoPeso = getResources().getDrawable(R.drawable.abaixopeso);
        Drawable imgNormal = getResources().getDrawable(R.drawable.normal);
        Drawable imgSobrepeso = getResources().getDrawable(R.drawable.sobrepeso);
        Drawable imgObesidade1 = getResources().getDrawable(R.drawable.obesidade1);
        Drawable imgObesidade2 = getResources().getDrawable(R.drawable.obesidade2);
        Drawable imgObesidade3 = getResources().getDrawable(R.drawable.obesidade3);

        if(imc < 18.5)
            objImc = new Imc(imgAbaixoPeso, "IMC: "+ imc +" - Abaixo do peso");

        else if(imc > 18.5 && imc <= 29.9)
            objImc = new Imc(imgNormal, "IMC: "+ imc +" - Peso ideal (parabéns)");

        else if(imc > 29.9 && imc <= 34.9)
            objImc = new Imc(imgSobrepeso, "IMC: "+ imc +" - Levemente acima do peso");

        else if(imc > 34.9 && imc <= 39.9)
            objImc = new Imc(imgObesidade1, "IMC: "+ imc +" - Obesidade grau I");

        else if(imc > 34.9 && imc <= 39.9)
            objImc = new Imc(imgObesidade2, "IMC: "+ imc +" - Obesidade grau II (severa)");

        else if (imc > 39.9)
            objImc = new Imc(imgObesidade3, "IMC: "+ imc +" - Obesidade grau III (mórbida)");

        return objImc;
    }
}