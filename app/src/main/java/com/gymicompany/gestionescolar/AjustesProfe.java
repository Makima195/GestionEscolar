package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AjustesProfe extends AppCompatActivity {

    public void Menuhub (View view){
        Intent i = new Intent(this, AjustesActivity.class);
        startActivity(i);
    }
    Button buttoncerrarsesion;
    ImageButton buttonback;
    private ImageView imagen1, imagen2;
    private Button cambiarButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        buttoncerrarsesion = findViewById(R.id.buttoncerrarsesion);
        buttonback = findViewById(R.id.buttonback);
        imagen1 = findViewById(R.id.fondoapp1);
        imagen2 = findViewById(R.id.fondoapp2);
        cambiarButton = findViewById(R.id.buttoncambiarfondo);

        buttoncerrarsesion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(AjustesProfe.this, ReceptionActivity.class));
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(AjustesProfe.this, hub_docentes.class));
            }
        });

        cambiarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar la visibilidad de las ImageView
                if (imagen1.getVisibility() == View.VISIBLE) {
                    imagen1.setVisibility(View.INVISIBLE);
                    imagen2.setVisibility(View.VISIBLE);
                } else {
                    imagen1.setVisibility(View.VISIBLE);
                    imagen2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Guarda el estado de visibilidad en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("imagen1_visible", imagen1.getVisibility() == View.VISIBLE);
        editor.apply();

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restaura el estado de visibilidad desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        boolean imagen1Visible = preferences.getBoolean("imagen1_visible", true);
        imagen1.setVisibility(imagen1Visible ? View.VISIBLE : View.INVISIBLE);
        imagen2.setVisibility(imagen1Visible ? View.INVISIBLE : View.VISIBLE);
    }
}