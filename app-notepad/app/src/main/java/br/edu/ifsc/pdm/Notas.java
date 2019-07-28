package br.edu.ifsc.pdm;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Notas {

    private String texto;
    SharedPreferences notas;
    SharedPreferences.Editor editNotas;

    private final String FILE_SHARE = "Notes";

    Context context;

    public Notas(Context c){
        this.context = c;
        try {

            notas = this.context.getSharedPreferences(FILE_SHARE, Context.MODE_PRIVATE);
            editNotas = notas.edit();

        }catch (Exception e){};
    }

    // Salvar notas
    public void salvarNota(String nota){

        editNotas.putString("nota",nota);
        editNotas.commit();
    }

    // Recuperar notas
    public String recuperarNota(){

        if(notas.contains("nota")){
            //String s = "";
            Toast.makeText(context, "Notas salvas!", Toast.LENGTH_SHORT).show();
            return this.notas.getString("nota","");
        }

        return "";
    }
}
