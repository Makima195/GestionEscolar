package com.gymicompany.gestionescolar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;

import java.net.URI;

public class Certificados extends AppCompatActivity {
    private ImageButton bt_descargar, bt_volver;
    String urlPDF = "https://firebasestorage.googleapis.com/v0/b/gestionescolar-fa82c.appspot.com/o/Certificado.pdf?alt=media&token=14d9676f-467f-4c68-a329-1f26a34888d9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificados);

        bt_descargar = findViewById(R.id.bt_descargar);
        bt_volver = findViewById(R.id.bt_volver);

        bt_descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(urlPDF);
                Intent i = new Intent(Intent.ACTION_VIEW,_link);
                startActivity(i);
                //Log.d("clik", "ok");
            }
        });

        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Certificados.this, HubAlumnos.class);
                startActivity(i);
            }
        });
    }



}
