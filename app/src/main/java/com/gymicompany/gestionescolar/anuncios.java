package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class anuncios extends AppCompatActivity {
    Button btn_ir_crear_notas;
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        btn_ir_crear_notas = findViewById(R.id.btn_ir_crear_anuncios);


    }

}