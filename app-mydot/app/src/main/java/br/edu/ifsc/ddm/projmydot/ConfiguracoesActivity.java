package br.edu.ifsc.ddm.projmydot;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConfiguracoesActivity extends AppCompatActivity {

    private Date _horasTrabalhadas;
    SimpleDateFormat formatHM;
    EditText txtHorasTrabalhadas;
    //TelaInicialModel _myTela = null;
    Date _horasDiarias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        txtHorasTrabalhadas = findViewById(R.id.txtHorasTrabalhadas);
        formatHM = new SimpleDateFormat("HH:mm");
        Intent intent = getIntent();
        _horasDiarias = (Date)intent.getSerializableExtra("horasDiarias");

        if(_horasDiarias != null)
            txtHorasTrabalhadas.setText(formatHM.format(_horasDiarias));
    }


    public void salvarHorasDiarias(View view) {
        try {

            if(!txtHorasTrabalhadas.getText().toString().isEmpty() && !txtHorasTrabalhadas.getText().toString().equals("08:48")) {
                    _horasTrabalhadas = formatHM.parse(txtHorasTrabalhadas.getText().toString().trim()+" AM");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, TelaInicialActivity.class);
        intent.putExtra("_horasTrabalhadas", _horasTrabalhadas);

        /*ArrayList<TelaInicialModel> retorno = new ArrayList<TelaInicialModel>();
        retorno.add(_myTela);
        intent.putExtra("myTela", retorno);*/

        startActivity(intent);
    }

    //@Override
    //public int describeContents() {
    //    return 0;
    //}

    //@Override
    //public void writeToParcel(Parcel dest, int flags) {
    //    dest.writeString(_horasTrabalhadas);
    //}
}
