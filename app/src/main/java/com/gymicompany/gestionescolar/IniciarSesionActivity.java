package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class IniciarSesionActivity extends AppCompatActivity {

    public void Volver_recepcion (View view){
        Intent i = new Intent(this, HubAlumnos.class);
        startActivity(i);
        finish();
    }

    Button buttonIniciarSesion,buttonvolver;
    EditText contraseñaeditText, correoeditText;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    //filtro de usuarios docentes
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = db.collection("user");
    CollectionReference usersCollection2 = db.collection("docentes");



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        // Encuentra las referencias a los elementos de la interfaz de usuario
        correoeditText = findViewById(R.id.correoeditText);
        contraseñaeditText = findViewById(R.id.contraseñaeditText);
        buttonIniciarSesion = findViewById(R.id.buttonIniciarSesion);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        buttonvolver = findViewById(R.id.button_atras);

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

            buttonvolver.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v){
                    startActivity(new Intent(IniciarSesionActivity.this, ReceptionActivity.class));
                }
            });
    }



    private void loginUser(String username, String password){
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(IniciarSesionActivity.this, HubAlumnos.class));
                    Toast.makeText(IniciarSesionActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(IniciarSesionActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e){
                Toast.makeText(IniciarSesionActivity.this, "error de sesion", Toast.LENGTH_SHORT).show();
            }
        });

    }








}