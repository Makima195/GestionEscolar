package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class IniciarSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
    }

    public void Volver_recepcion (View view){
        Intent i = new Intent(this, ReceptionActivity.class);
        startActivity(i);
    }
}