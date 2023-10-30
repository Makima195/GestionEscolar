package com.gymicompany.gestionescolar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HubDocente extends AppCompatActivity {
    public void Menuhub (View view){
        Intent i = new Intent(this, anuncios.class);
        startActivity(i);
    }
    ImageButton bt_horario, bt_notas, bt_noticias, bt_avisos, bt_ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_docente);

        bt_horario = findViewById(R.id.horario);
        bt_notas = findViewById(R.id.notas);
        bt_noticias = findViewById(R.id.noticias);
        bt_avisos = findViewById(R.id.avisos);
        bt_ajustes = findViewById(R.id.ajustes);

        bt_avisos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HubDocente.this, anuncios.class));
            }
        });
    }
}

        /*bt_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar otra vista
                Intent intent = new Intent(HubDocente.this, "destino.jpg".class);
                startActivity(intent);
            }
        });*/
        //despues mas botones