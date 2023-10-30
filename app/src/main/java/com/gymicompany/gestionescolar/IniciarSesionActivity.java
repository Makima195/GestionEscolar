package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class IniciarSesionActivity extends AppCompatActivity {

    public void Volver_recepcion (View view){
        Intent i = new Intent(this, ReceptionActivity.class);
        startActivity(i);
    }

    Button buttonIniciarSesion;
    EditText contraseñaeditText, correoeditText;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        // Encuentra las referencias a los elementos de la interfaz de usuario
        correoeditText = findViewById(R.id.correoeditText);
        contraseñaeditText = findViewById(R.id.contraseñaeditText);
        buttonIniciarSesion = findViewById(R.id.buttonIniciarSesion);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String username = correoeditText.getText().toString();
                String password = contraseñaeditText.getText().toString();

                if (username.isEmpty() && password.isEmpty()) {
                    // Aquí puedes agregar la lógica de autenticación
                    Toast.makeText(IniciarSesionActivity.this, "Ingresa los datos", Toast.LENGTH_SHORT).show();

                } else {
                    // Manejo de errores si los campos están vacíos
                    loginUser(username, password);
                }
            }
        });
    }

    private void loginUser(String username, String password){
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(IniciarSesionActivity.this, Hubprueba.class));
                    Toast.makeText(IniciarSesionActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(IniciarSesionActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e){
                Toast.makeText(IniciarSesionActivity.this, "erro de sesion", Toast.LENGTH_SHORT).show();
            }
        });

    }



}