package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AjustesActivity extends AppCompatActivity {

    public void Menuhub (View view){
        Intent i = new Intent(this, AjustesActivity.class);
        startActivity(i);
    }
    Button buttoncerrarsesion, buttondeath;
    ImageButton buttonback;
    private ImageView imagen1, imagen2;
    private Button cambiarButton;

    private FirebaseAuth auth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        buttoncerrarsesion = findViewById(R.id.buttoncerrarsesion);
        buttonback = findViewById(R.id.buttonback);
        imagen1 = findViewById(R.id.fondoapp1);
        imagen2 = findViewById(R.id.fondoapp2);
        cambiarButton = findViewById(R.id.buttoncambiarfondo);
        buttondeath = findViewById(R.id.buttondeath);
        // Inicializa Firebase Authentication
        auth = FirebaseAuth.getInstance();

        // Verifica si el usuario está autenticado
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            // El usuario está autenticado
            // ...
        }

        buttoncerrarsesion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(AjustesActivity.this, ReceptionActivity.class));
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(AjustesActivity.this, HubAlumnos.class));
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

        buttondeath.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // El usuario se eliminó correctamente
                                        // Puedes agregar más acciones después de eliminar el usuario si es necesario
                                        Toast.makeText(getApplicationContext(), "Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(AjustesActivity.this, ReceptionActivity.class));
                                        // Aquí puedes realizar acciones adicionales después de eliminar el usuario, si es necesario
                                        // Por ejemplo, cerrar la sesión y redirigir al usuario a la pantalla de inicio de sesión.
                                    } else {
                                        // Error al eliminar el usuario
                                        Toast.makeText(getApplicationContext(), "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
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
