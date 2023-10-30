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
    ImageButton buttonajustes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_alumnos);

        buttonajustes = findViewById(R.id.buttonajustes);

        buttonajustes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(HubAlumnos.this, AjustesActivity.class));
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
