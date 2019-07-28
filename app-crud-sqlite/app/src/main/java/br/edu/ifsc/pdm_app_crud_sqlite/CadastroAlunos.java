package br.edu.ifsc.pdm_app_crud_sqlite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

public class CadastroAlunos extends AppCompatActivity {

    private EditText _txtCodigo;
    private EditText _txtNome;
    private EditText _txtNota;

    private SQLiteDatabase _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        _txtCodigo = findViewById(R.id.txtCodigo);
        _txtNome = findViewById(R.id.txtNome);
        _txtNota = findViewById(R.id.txtNota);

        // Abrir base de dados
        _db = openOrCreateDatabase("dbalunos", MODE_PRIVATE, null);
        _db.execSQL("CREATE TABLE IF NOT EXISTS notas(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                     "Nome VARCHAR NOT NULL," +
                                                     "Nota DECIMAL NOT NULL)");
    }

    public void bntCadastrar(View v){

        // Pilha de registros com {chave:valor}
        ContentValues registros = new ContentValues();
        registros.put("Nome", this._txtNome.getText().toString());
        registros.put("Nota", Double.parseDouble(this._txtNota.getText().toString()));

        // insert de registros, faz uma laço de SET "Coluna" = "Valor"
        _db.insert("notas", null, registros);

        limparCampos();

        Toast.makeText(getApplicationContext(),
                  "Aluno inserido com sucesso!",
                       Toast.LENGTH_LONG).show();
    }

    public void bntAlterar(View v){

        ContentValues registros = new ContentValues();
        registros.put("Nome", this._txtNome.getText().toString());
        registros.put("Nota", Double.parseDouble(this._txtNota.getText().toString()));

        String where = "Id = "+_txtCodigo.getText().toString();

        _db.update("notas", registros, where, null);

        limparCampos();

        Toast.makeText(getApplicationContext(),
                  "Aluno atualizado com sucesso!",
                       Toast.LENGTH_LONG).show();
    }

    public void bntExcluir(View v){

        String where = "Id = "+_txtCodigo.getText().toString();

        _db.delete("notas", where, null);

        limparCampos();

        Toast.makeText(getApplicationContext(),
                  "Aluno removido com sucesso!",
                       Toast.LENGTH_LONG).show();
    }

    public void bntPesquisar(View v){

        final EditText txtCodPesquisaDialog = new EditText(this);

        AlertDialog.Builder telaPesquisa = new AlertDialog.Builder(this);

        DialogInterface.OnClickListener o = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realizarPesquisa(Integer.parseInt(_txtCodigo.getText().toString()));
            }
        };

        telaPesquisa.setIcon(android.R.drawable.ic_menu_search);
        telaPesquisa.setTitle("PESQUISAR:");
        telaPesquisa.setPositiveButton("OK", o);
        telaPesquisa.setNegativeButton("CANCELAR", null);
        telaPesquisa.setView(txtCodPesquisaDialog);

        telaPesquisa.show();
    }

    private void realizarPesquisa(int codigo) {
        // 2° parametro: new String[]{"Id", "Nome", "Nota"} ou * == null
        Cursor result = _db.query("notas", null,
        "id=" + codigo, null, null, null, null);

        if(result.moveToNext()){
            String nome = result.getString(result.getColumnIndex("Nome"));
            Double nota = result.getDouble(result.getColumnIndex("Nota"));

            _txtCodigo.setText(Integer.toString(codigo));
            _txtNome.setText(nome);
            _txtNota.setText(nota.toString());
        }
    }

    public void bntListar(View v){
        Cursor result = _db.query("notas", null,
                null, null, null, null, null);

        final EditText txtLista = new EditText(this);

        String texto = "";

        while(result.moveToNext()) {
            int cod = result.getInt(result.getColumnIndex("Id"));
            String nome = result.getString(result.getColumnIndex("Nome"));
            Double nota = result.getDouble(result.getColumnIndex("Nota"));

            texto += "Código: " + cod +
                     "\nNome: " + nome +
                     "\nNota: " + nota + "\n---\n";
        }

        txtLista.setText(texto);
        txtLista.setScroller(new Scroller(this));
        txtLista.setVerticalScrollBarEnabled(true);
        txtLista.setMovementMethod(new ScrollingMovementMethod());
        txtLista.isScrollContainer();

        AlertDialog.Builder telaLista = new AlertDialog.Builder(this);

        telaLista.setTitle("LISTAGEM DE REGISTROS:");
        telaLista.setPositiveButton("OK", null);
        telaLista.setView(txtLista);

        telaLista.show();

    }

    private void limparCampos(){
        this._txtCodigo.setText("");
        this._txtNome.setText("");
        this._txtNota.setText("");
    }
}

