package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class HubAlumnos extends AppCompatActivity {

    public void Menuhub (View view){
        Intent i = new Intent(this, AjustesActivity.class);
        startActivity(i);
    }

    ImageButton buttonajustes, buttonhorario, buttonanuncio_general, buttondescargas;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_alumnos);

        //crea la existencia de los botones para usarse

        buttonajustes = findViewById(R.id.buttonajustes);
        buttonhorario = findViewById(R.id.buttonhorario);
        buttonanuncio_general = findViewById(R.id.button_ver_anuncio_general);
        buttondescargas = findViewById(R.id.buttondescargas);


        //hacia donde te redirigen los botones "ajustes"
        buttonajustes.setOnClickListener(v -> startActivity(new Intent(HubAlumnos.this, AjustesActivity.class)));
        //hacia donde te redirigen los botones "horarios"
        buttonhorario.setOnClickListener(v -> startActivity(new Intent(HubAlumnos.this, HorarioActivity.class)));
        //hacia donde te redirigen los botones "anuncios"
        buttonanuncio_general.setOnClickListener(v -> startActivity(new Intent(HubAlumnos.this, VerAnuncios.class)));
        //hacia donde te redirigen los botones "descargas"
        buttondescargas.setOnClickListener(v -> startActivity(new Intent(HubAlumnos.this, Certificados.class)));



    }

    @Override
    public void onBackPressed() {
        // Puedes dejar este método vacío o agregar un mensaje de advertencia
        // o realizar cualquier otra acción que desees.
        // Esto evitará que el usuario navegue hacia atrás.

    }

}
