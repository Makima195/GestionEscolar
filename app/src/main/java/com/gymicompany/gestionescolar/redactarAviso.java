package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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


public class redactarAviso extends AppCompatActivity {
    Button btn_subir_aviso;
    EditText titulo, aviso;
    private FirebaseFirestore mFireStore;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redactar_aviso);

        this.setTitle("Crear avisos");
        mFireStore = FirebaseFirestore.getInstance();


        btn_subir_aviso = findViewById(R.id.btn_subir_aviso);
        titulo = findViewById(R.id.titulo);
        aviso = findViewById(R.id.aviso);

        btn_subir_aviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tituloAviso = titulo.getText().toString().trim();
                String textoAviso = aviso.getText().toString().trim();

                if (tituloAviso.isEmpty() || textoAviso.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese correctamente los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postAviso(tituloAviso, textoAviso);
                }

            }
        });


    }

    private void postAviso(String tituloAviso, String textoAviso) {
        mFireStore = FirebaseFirestore.getInstance();
        Map<String, Object> map = new HashMap<>();
        map.put("Titulo", tituloAviso);
        map.put("Aviso", textoAviso);
        mFireStore.collection("avisos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al crear aviso", Toast.LENGTH_SHORT).show();
            }
        });
    }

}