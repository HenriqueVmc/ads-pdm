package br.edu.ifsc.ddm.projmydot;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TelaInicialActivity extends AppCompatActivity {

    private SQLiteDatabase _db;
    private ContentValues values;
    private long registro;
    private RegistroPonto ponto;

    private TextView mTextMessage;
    private EditText txtEditar;

    private Calendar _horaAtual;
    private Calendar _horasDiarias;
    private Calendar _horaEntrada;
    private Calendar _horaSaidaAlmoco;
    private Calendar _horaRetornoAlmoco;
    private Calendar _horaSaidaPrevista;

    private String horaSaidaAlmocoFormatada;
    private String horaEntradaFormatada;
    private String horaRetornoAlmocoFormatada;

    private EditText txtEntrada;
    private EditText txtSaidaAlmoco;
    private EditText txtRetornoAlmoco;

    private SimpleDateFormat formatHM = new SimpleDateFormat("HH:mm");

    private TimePickerDialog timeDialog;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        _db = openOrCreateDatabase("dbmydot", MODE_PRIVATE, null);
        ponto = new RegistroPonto();
        ponto.createTable(_db);

        values = new ContentValues();

        values.put("DataHoraEntrada", "-");
        values.put("DataHoraSaidaAlmoco", "-");
        values.put("DataHoraRetornoAlmoco", "-");
        values.put("DataHoraSaida", "-");

        registro = _db.insert("registros", null, values);

        mTextMessage = findViewById(R.id.message);

        _horaAtual = Calendar.getInstance();

        Intent intent = getIntent();
        Date horasTrabalhadas = (Date) intent.getSerializableExtra(("_horasTrabalhadas"));

        if (horasTrabalhadas != null) {
            _horasDiarias.setTime(horasTrabalhadas);
        }

        txtEntrada = findViewById(R.id.txtHorasEntrada);
        txtSaidaAlmoco = findViewById(R.id.txtHoraSaidaAlmoco);
        txtRetornoAlmoco = findViewById(R.id.txtHorasRetornoAlmoco);

        txtEntrada.setOnClickListener(showTimeDialog());
        txtSaidaAlmoco.setOnClickListener(showTimeDialog());
        txtRetornoAlmoco.setOnClickListener(showTimeDialog());

        atuallizarTela();

        myCalendar = Calendar.getInstance();
    }

    public TelaInicialActivity(){
        try {
            if(_horasDiarias == null) {
                Date horasDiarias = formatHM.parse("08:48");
                _horasDiarias = Calendar.getInstance();
                _horasDiarias.setTime(horasDiarias);
            }
        } catch (ParseException e) {
                e.printStackTrace();
        }
    }

    private View.OnClickListener showTimeDialog() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View txt) {

                txtEditar = (EditText)txt;

                TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        myCalendar.set(Calendar.MINUTE, minute);
                        updateLabel(txtEditar);
                    }
                };

                timeDialog = new TimePickerDialog(TelaInicialActivity.this,
                        time,
                        0,
                        0,
                        false);

                timeDialog.show();
            }
        };
    }

    private void atualizarHorasTrabalhadas() {

        atuallizarTela();

        Calendar cHorasTrabalhadas = Calendar.getInstance();

        cHorasTrabalhadas.add(Calendar.HOUR_OF_DAY, -_horaEntrada.get(Calendar.HOUR_OF_DAY));
        cHorasTrabalhadas.add(Calendar.MINUTE, -_horaEntrada.get(Calendar.MINUTE));

        TextView lblHorasTrabalhadas = findViewById(R.id.lblHorasTrabalhadas);
        lblHorasTrabalhadas.setText(formatHM.format(cHorasTrabalhadas.getTime()));
    }

    private EditText buscarPonto() {

        if (txtEntrada.getText().toString().isEmpty() || txtEntrada.getText().toString().equals("00:00"))
            return txtEntrada;

        else if (txtSaidaAlmoco.getText().toString().isEmpty() || txtSaidaAlmoco.getText().toString().equals("00:00"))
            return txtSaidaAlmoco;

        else if (txtRetornoAlmoco.getText().toString().isEmpty() || txtRetornoAlmoco.getText().toString().equals("00:00"))
            return txtRetornoAlmoco;

        return null;
    }

    public void marcarPonto(View view) {

        atuallizarTela();

        EditText txtPonto = buscarPonto();

        if (txtPonto != null) {

            _horaAtual = Calendar.getInstance();
            txtPonto.setText(formatHM.format(_horaAtual.getTime()));
            atualizarPrevisaoSaida();
            atualizarHorasTrabalhadas();
        }
    }

    private void atuallizarTela() {

        try{

            horaEntradaFormatada = txtEntrada.getText().toString().trim();

            if(!horaEntradaFormatada.isEmpty()){
                _horaEntrada = Calendar.getInstance();
                _horaEntrada.setTime(formatHM.parse(horaEntradaFormatada));

                values.put("DataHoraEntrada", horaEntradaFormatada);
                _db.update("registros", values, "Id = "+registro, null);
            }

            horaSaidaAlmocoFormatada = txtSaidaAlmoco.getText().toString().trim();

            if(!horaSaidaAlmocoFormatada.isEmpty()){
                _horaSaidaAlmoco = Calendar.getInstance();
                _horaSaidaAlmoco.setTime(formatHM.parse(horaSaidaAlmocoFormatada));

                values.put("DataHoraSaidaAlmoco", horaSaidaAlmocoFormatada);
                _db.update("registros", values, "Id = "+registro, null);
            }

            horaRetornoAlmocoFormatada = txtRetornoAlmoco.getText().toString().trim();

            if(!horaRetornoAlmocoFormatada.isEmpty()){
                _horaRetornoAlmoco = Calendar.getInstance();
                _horaRetornoAlmoco.setTime(formatHM.parse(horaRetornoAlmocoFormatada));

                values.put("DataHoraRetornoAlmoco", horaRetornoAlmocoFormatada);
                _db.update("registros", values, "Id = "+registro, null);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void atualizarPrevisaoSaida() {

        atuallizarTela();

        TextView lblHoraSaida = findViewById(R.id.lblHoraSaida);
        TextView lblHorasIntervalo = findViewById(R.id.lblHorasIntervalo);

        _horaSaidaPrevista = Calendar.getInstance();
        _horaSaidaPrevista.add(Calendar.HOUR_OF_DAY, 8);
        _horaSaidaPrevista.add(Calendar.MINUTE, 48);

        if (!txtRetornoAlmoco.getText().toString().isEmpty() && !txtSaidaAlmoco.getText().toString().isEmpty()) {

            Calendar intervalo = calcularIntervalo();
            _horaSaidaPrevista.add(Calendar.HOUR_OF_DAY, intervalo.get(Calendar.HOUR_OF_DAY));
            _horaSaidaPrevista.add(Calendar.MINUTE, intervalo.get(Calendar.MINUTE));

            lblHorasIntervalo.setText(formatHM.format(intervalo.getTime()));

        } else if (!txtSaidaAlmoco.getText().toString().isEmpty()) {

            _horaSaidaPrevista.add(Calendar.HOUR_OF_DAY, 1);
            lblHorasIntervalo.setText("01:00");
        }

        values.put("DataHoraSaida", formatHM.format(_horaSaidaPrevista.getTime()));
        _db.update("registros", values, "Id = "+registro, null);

        lblHoraSaida.setText("Horário de Saída: " + formatHM.format(_horaSaidaPrevista.getTime()));
    }

    private Calendar calcularIntervalo() {

        atuallizarTela();

        try {

            Date saidaAlmoco = formatHM.parse(horaSaidaAlmocoFormatada);
            Date retornoAlmoco = formatHM.parse(horaRetornoAlmocoFormatada);

            _horaSaidaAlmoco = Calendar.getInstance();
            _horaRetornoAlmoco = Calendar.getInstance();

            _horaSaidaAlmoco.setTime(saidaAlmoco);
            _horaRetornoAlmoco.setTime(retornoAlmoco);

            //_horaRetornoAlmoco.add(Calendar.HOUR_OF_DAY, -_horaRetornoAlmoco.get(Calendar.HOUR_OF_DAY));
            //_horaRetornoAlmoco.add(Calendar.MINUTE, -_horaRetornoAlmoco.get(Calendar.MINUTE));

            Calendar retorno = Calendar.getInstance();
            retorno.setTime(_horaRetornoAlmoco.getTime());
            retorno.add(Calendar.HOUR_OF_DAY, -_horaSaidaAlmoco.get(Calendar.HOUR_OF_DAY));
            retorno.add(Calendar.MINUTE, -_horaSaidaAlmoco.get(Calendar.MINUTE));

            return retorno;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void configuracoes(View view) {

        Intent intent = new Intent(this, ConfiguracoesActivity.class);
        intent.putExtra("horasDiarias", _horasDiarias.getTime());
        startActivity(intent);
    }

    public void showTimePicker(View btn) {

        txtEntrada.setEnabled(true);
        txtSaidaAlmoco.setEnabled(true);
        txtRetornoAlmoco.setEnabled(true);

        Button btnEditar = (Button)btn;
        btnEditar.setVisibility(View.GONE);

        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setVisibility(View.VISIBLE);
    }

    private void updateLabel(View txt) {

        EditText txtAlterado = (EditText)txt;
        txtAlterado.setText(formatHM.format(myCalendar.getTime()));
    }

    public void relatorio(View view) {
        Intent intent = new Intent(this, RelatorioActivity.class);
        startActivity(intent);
    }
}
