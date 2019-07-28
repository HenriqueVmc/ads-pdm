package br.edu.ifsc.pdm_app_crud_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ExemploScriptsSQL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        SQLiteDatabase db;

        //MODE_PRIVATE : Não permitir compartilhamento com apps externos
        db = openOrCreateDatabase("banco", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS aluno(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR," +
                "Matricula LONG)");

        //db.execSQL("INSERT INTO aluno VALUES(1, 'Henrique', 000001);");
        //db.execSQL("INSERT INTO aluno VALUES(2, 'Rodrigo', 000002);");

        // Cursor está apontando para ultimo item da lista retornada:
        Cursor dataRows = db.rawQuery("SELECT * FROM aluno", null);// "consulta", argumentos('where', 'orderby', 'group' e etc)

        // Apontando para inicio da lista retornada:
        dataRows.moveToFirst();

        do {

            Log.i("Registros: ", "ID: " + Integer.toString(dataRows.getInt(dataRows.getColumnIndex("Id")))+
                                           " | Nome: " + dataRows.getString(dataRows.getColumnIndex("Name")) +
                                           " | Matricula: " + Long.toString(dataRows.getLong(dataRows.getColumnIndex("Matricula"))));

        }while (dataRows.moveToNext());

        dataRows.close();

    }
}

