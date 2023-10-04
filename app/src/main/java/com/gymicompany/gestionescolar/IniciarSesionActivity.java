package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class IniciarSesionActivity extends AppCompatActivity {

    public void Volver_recepcion (View view){
        Intent i = new Intent(this, ReceptionActivity.class);
        startActivity(i);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        // Encuentra las referencias a los elementos de la interfaz de usuario
        EditText usernameEditText = findViewById(R.id.correoeditText);
        EditText contraseñaEditText = findViewById(R.id.contraseñaeditText);
        Button buttonIniciarSesion = findViewById(R.id.buttonIniciarSesion);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = contraseñaEditText.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    // Aquí puedes agregar la lógica de autenticación



                    
                } else {
                    // Manejo de errores si los campos están vacíos
                }
            }
        });
    }



}