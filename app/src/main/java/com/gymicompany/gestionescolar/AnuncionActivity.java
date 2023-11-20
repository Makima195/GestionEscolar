package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnuncionActivity extends AppCompatActivity {

    Button btn_ir_crear_notas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncion);

        btn_ir_crear_notas = findViewById(R.id.btn_ir_crear_anuncios);

        btn_ir_crear_notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnuncionActivity.this, CrearAnuncio.class));
            }
        });
    }

}