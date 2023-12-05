package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class hub_docentes extends AppCompatActivity {

    public void Menuhub (View view){
        Intent i = new Intent(this, AjustesActivity.class);
        startActivity(i);
    }

    ImageButton buttonajustes, buttonAnuncioGeneral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_docentes);

        buttonajustes = findViewById(R.id.ajustes);

        buttonAnuncioGeneral = findViewById(R.id.noticias);


        buttonajustes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(hub_docentes.this, AjustesProfe.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        // Puedes dejar este método vacío o agregar un mensaje de advertencia
        // o realizar cualquier otra acción que desees.
        // Esto evitará que el usuario navegue hacia atrás.
    }
}