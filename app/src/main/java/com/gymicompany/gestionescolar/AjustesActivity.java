package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AjustesActivity extends AppCompatActivity {

    public void Menuhub (View view){
        Intent i = new Intent(this, AjustesActivity.class);
        startActivity(i);
    }
    Button buttoncerrarsesion;
    ImageButton buttonback;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        buttoncerrarsesion = findViewById(R.id.buttoncerrarsesion);
        buttonback = findViewById(R.id.buttonback);

        buttoncerrarsesion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(AjustesActivity.this, ReceptionActivity.class));
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(AjustesActivity.this, HubAlumnos.class));
            }
        });
    }
}