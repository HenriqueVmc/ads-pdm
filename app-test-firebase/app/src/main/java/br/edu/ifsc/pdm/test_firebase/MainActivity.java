package br.edu.ifsc.pdm.test_firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase db;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Armazenando Referencia do DB
        db = FirebaseDatabase.getInstance();
        dbReference = db.getReference();

        dbReference.child("test").setValue("100");

        dbReference.child("Usuarios")
                        .child("04")
                            .child("Id")
                                .setValue("1");

        dbReference.child("Usuarios")
                        .child("04")
                            .child("Nome")
                                .setValue("Henrique Venâncio");

//        dbReference.child("Usuarios")
//                        .child("05")
//                            .child("Id")
//                                .setValue("2");

        dbReference.child("Usuarios")
                        .child("05")
                            .child("Nome")
                                .setValue("Emanuelington");

        dbReference.child("Usuarios")
                   .push()
                        .child("Nome")
                            .setValue("Emanuelington");

        Usuario user = new Usuario();
        user.setNome("Henrique");
        user.setApelido("Nenhum");
        user.setAltura("1.98");

        dbReference.child("Usuarios")
                   .push()
                        .setValue(user);
    }
}
