package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class notas extends AppCompatActivity {

    Button btn_subir_notas;
    EditText nota, nombre_alumno, materia;

    private FirebaseFirestore mFireStore;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        this.setTitle("Subir notas");
        mFireStore = FirebaseFirestore.getInstance();

        btn_subir_notas = findViewById(R.id.btn_subir_nota);
        nota = findViewById(R.id.nota);
        nombre_alumno = findViewById(R.id.nombre_alumno);
        materia = findViewById(R.id.materia);



        btn_subir_notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoNota = nota.getText().toString().trim();
                String textNombre = nombre_alumno.getText().toString().trim();
                String nombreMateria = materia.getText().toString().trim();

                if (textoNota.isEmpty() || textNombre.isEmpty() || nombreMateria.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese correctamente los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postNotas(textoNota,textNombre,nombreMateria);
                }

            }
        });

    }
    private void postNotas(String textoNota, String textNombre, String nombreMateria){
        mFireStore = FirebaseFirestore.getInstance();
        Map<String, Object> map = new HashMap<>();
        map.put("Calificacion", textoNota);
        map.put("Nombre", textNombre);
        map.put("Materia", nombreMateria);
        mFireStore.collection("notas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al subir nota", Toast.LENGTH_SHORT).show();
            }
        });
    }
}