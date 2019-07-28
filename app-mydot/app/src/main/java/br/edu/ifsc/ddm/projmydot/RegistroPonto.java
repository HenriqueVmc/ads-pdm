package br.edu.ifsc.ddm.projmydot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RegistroPonto {

    private SimpleDateFormat formatHM, formatDM;

    /*
    Calendar horaEntrada;
    Calendar horaSaidaAlmoco;
    Calendar horaRetornoAlmoco;
    Calendar horaSaida;
    */

    String horaEntradaFormatada;
    String horaSaidaAlmocoFormatada;
    String horaRetornoAlmocoFormatada;
    String horaSaidaFormatada;

    public RegistroPonto(){
        formatHM = new SimpleDateFormat("HH:mm");
        formatDM = new SimpleDateFormat("dd/MM");
    }

    public void createTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS registros(Id INTEGER PRIMARY KEY AUTOINCREMENT, DataHoraEntrada TEXT, DataHoraSaidaAlmoco TEXT, DataHoraRetornoAlmoco TEXT, DataHoraSaida TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS configuracoes(Id INTEGER PRIMARY KEY AUTOINCREMENT, HorasDiarias TEXT)");
    }

//    public String getHoraEntradaFormatada() {
//        return formatHM.format(horaEntrada.getTime());
//    }
//
//    public String getHoraSaidaFormatada() {
//        return formatHM.format(horaEntrada.getTime());
//    }

    public ArrayList<RegistroPonto> getRelatorioRegistros(SQLiteDatabase db) {

        ArrayList<RegistroPonto> registros = new ArrayList<>();

        Cursor result = db.query("registros", null,
                null, null, null, null, null);

        while(result.moveToNext()) {

            RegistroPonto ponto = new RegistroPonto();
            ponto.horaEntradaFormatada = result.getString(result.getColumnIndex("DataHoraEntrada"));
            ponto.horaSaidaAlmocoFormatada = result.getString(result.getColumnIndex("DataHoraSaidaAlmoco"));
            ponto.horaRetornoAlmocoFormatada = result.getString(result.getColumnIndex("DataHoraRetornoAlmoco"));
            ponto.horaSaidaFormatada = result.getString(result.getColumnIndex("DataHoraSaida"));

            registros.add(ponto);
        }

        return registros;
    }

    public String getHoraEntradaFormatada() {
        return horaEntradaFormatada;
    }

    public String getDataAtualFormatada() {
        return formatDM.format(Calendar.getInstance().getTime());
    }


}
