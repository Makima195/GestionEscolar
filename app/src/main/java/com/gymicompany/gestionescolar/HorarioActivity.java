package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import androidx.annotation.NonNull;



public class HorarioActivity extends AppCompatActivity {

    public void Horario (View view){
        Intent i = new Intent(this, HorarioActivity.class);
        startActivity(i);
    }

    ImageButton buttonback;

    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();

    TextView lunesbloque1, lunesbloque2, lunesbloque3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);


        buttonback = findViewById(R.id.buttonback);
        user = FirebaseAuth.getInstance().getCurrentUser();
        lunesbloque1 = findViewById(R.id.lunesbloque1);
        lunesbloque2 = findViewById(R.id.lunesbloque2);
        lunesbloque3 = findViewById(R.id.lunesbloque3);

        buttonback.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(HorarioActivity.this, HubAlumnos.class));
            }
        });

        cargarHorario();

    }
    private void cargarHorario() {
        db.collection("user")
                .document(user.getUid())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // RECUERDA LA PUTA MAYUSCULA DE "C"URSO >:DDDDDDD
                        String pruebacurso = documentSnapshot.getString("Curso");
                        db.collection("Horarios")
                                .document(pruebacurso)
                                .get()
                                .addOnSuccessListener(documentSnapshotHorario -> {
                                    if (documentSnapshotHorario.exists()) {
                                        lunesbloque1.setText(documentSnapshotHorario.getString("bloque1"));
                                        lunesbloque2.setText(documentSnapshotHorario.getString("bloque2"));
                                        lunesbloque3.setText(documentSnapshotHorario.getString("bloque3"));
                                        //EJEMPLO DE OTROS DIAS TODO PUEDE ESTAR JUNTO
                                        //martesbloque2.setText(documentSnapshotHorario.getString("bloque2"));
                                        // Puedes continuar con el resto de los bloques o datos aquí.
                                    }
                                });
                    }
                });
    }




}