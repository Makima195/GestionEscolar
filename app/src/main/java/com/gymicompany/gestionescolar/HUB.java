package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.TextView;

public class HUB extends AppCompatActivity {

    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    private TextView dateTimeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        btn1 = findViewById(R.id.avisos);
        btn2 = findViewById(R.id.ajustes);
        btn3 = findViewById(R.id.horarios);
        btn4 = findViewById(R.id.noticias);
        btn5 = findViewById(R.id.docentes);
        btn6 = findViewById(R.id.documentos);
        btn7 = findViewById(R.id.notas);

        dateTimeTextView = findViewById(R.id.fechaYhora);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateTime = dateFormat.format(new Date());


        dateTimeTextView.setText("Fecha y hora: " + dateTime);

        ImageButton btn2 = findViewById(R.id.ajustes);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para navegar a la nueva vista
                Intent intent = new Intent(HUB.this, ajustes.class);
                startActivity(intent);
            }
        });


    }
}