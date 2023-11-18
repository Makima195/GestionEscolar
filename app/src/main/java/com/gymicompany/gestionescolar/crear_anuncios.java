package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class crear_anuncios extends AppCompatActivity {
    Button btn_crear;
    EditText titulo_general, anuncio_crear_general;
    private FirebaseFirestore mFireStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anuncios);

        this.setTitle("Crear anuncios");
        mFireStore = FirebaseFirestore.getInstance();


        btn_crear = findViewById(R.id.btn_subir_aviso);
        titulo_general = findViewById(R.id.titulo_general);
        anuncio_crear_general = findViewById(R.id.anuncio_crear_general);

        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tituloAnuncio = titulo_general.getText().toString().trim();
                String textoAnuncio = anuncio_crear_general.getText().toString().trim();

                if (tituloAnuncio.isEmpty() || textoAnuncio.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese correctamente los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postAnuncio(tituloAnuncio, textoAnuncio);
                }

            }
        });


    }

    private void postAnuncio(String tituloAnuncio, String textoAnuncio) {
        mFireStore = FirebaseFirestore.getInstance();
        Map<String, Object> map = new HashMap<>();
        map.put("Titulo", tituloAnuncio);
        map.put("Anuncio", textoAnuncio);
        mFireStore.collection("anuncios").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al crear anuncio", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
