package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;



public class ReceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
    }

    //metodo de botones


    public void Iniciar_sesion (View view){
        Intent i = new Intent(this, IniciarSesionActivity.class);
        startActivity(i);
        //overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    public void Register (View view){
        Intent i = new Intent(this, register.class);
        startActivity(i);
        //overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }


}