package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ReceptionActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    public void Volver_recepcion (View view){
        Intent i = new Intent(this, HubAlumnos.class);
        startActivity(i);
        finish();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);

        // Inicializa Firebase Authentication
        auth = FirebaseAuth.getInstance();

        // Verifica si el usuario está autenticado
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            // El usuario está autenticado, redirige a la actividad específica
            startActivity(new Intent(this, HubAlumnos.class));
             // Cierra la actividad actual para que no pueda volver atrás con el botón "Atrás"
        }

        // Aquí puedes colocar el resto del código de tu actividad principal
        // ...
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