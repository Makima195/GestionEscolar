package com.gymicompany.gestionescolar;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class avisosDocentes extends AppCompatActivity {
    Button btn_crear_aviso;
    TextView ver_avisos;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos_docentes);

        btn_crear_aviso = findViewById(R.id.btn_crear_aviso);

        // Inicializa Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Cambia "mi_coleccion" al nombre de tu colección
        CollectionReference collection = db.collection("avisos");
        ver_avisos = findViewById(R.id.ver_avisos);


        btn_crear_aviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(avisosDocentes.this, redactarAviso.class));
            }
        });
    }
}


