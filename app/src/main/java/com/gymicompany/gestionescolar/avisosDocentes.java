package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class avisosDocentes extends AppCompatActivity {
    Button btn_crear_aviso;
    RecyclerView recyclerViewSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos_docentes);

        btn_crear_aviso = findViewById(R.id.btn_crear_aviso);

        btn_crear_aviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(avisosDocentes.this, redactarAviso.class));
            }
        });
    }

}