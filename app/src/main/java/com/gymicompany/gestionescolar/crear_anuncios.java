package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class crear_anuncios extends AppCompatActivity {
    Button btn_crear;
    EditText titulo, anuncio;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anuncios);

        this.setTitle("Crear anuncios");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btn_crear = findViewById(R.id.btn_crear);
        titulo = findViewById(R.id.titulo);
        anuncio = findViewById(R.id.anuncio);

        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tituloAnuncio = titulo.getText().toString().trim();
                String textoAnuncio = anuncio.getText().toString().trim();

                if (tituloAnuncio.isEmpty() || textoAnuncio.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingrese correctamente los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postAnuncio(tituloAnuncio, textoAnuncio);
                }
            }
        });
    }

    private void postAnuncio(String tituloAnuncio, String textoAnuncio) {
        // Crear una referencia a un nodo específico donde se almacenarán los anuncios
        DatabaseReference anunciosRef = mDatabase.child("anuncios").push();

        // Crea un objeto Anuncio para almacenar los datos
        anuncios nuevoAnuncio = new anuncios();

        // Agrega el nuevo anuncio a la base de datos
        anunciosRef.setValue(nuevoAnuncio).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Anuncio creado exitosamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al crear el anuncio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}