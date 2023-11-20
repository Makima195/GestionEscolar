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

    TextView lunesbloque1, lunesbloque2, lunesbloque3, lunesbloque4, lunesbloque5, lunesbloque6;
    TextView martesbloque1, martesbloque2, martesbloque3, martesbloque4, martesbloque5, martesbloque6;
    TextView miercolesbloque1, miercolesbloque2, miercolesbloque3, miercolesbloque4, miercolesbloque5, miercolesbloque6;
    TextView juevesbloque1, juevesbloque2, juevesbloque3, juevesbloque4, juevesbloque5, juevesbloque6;
    TextView viernesbloque1, viernesbloque2, viernesbloque3, viernesbloque4, viernesbloque5, viernesbloque6;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);


        buttonback = findViewById(R.id.buttonback);
        user = FirebaseAuth.getInstance().getCurrentUser();
        lunesbloque1 = findViewById(R.id.lunesbloque1);
        lunesbloque2 = findViewById(R.id.lunesbloque2);
        lunesbloque3 = findViewById(R.id.lunesbloque3);

        lunesbloque4 = findViewById(R.id.lunesbloque4);
        lunesbloque5 = findViewById(R.id.lunesbloque5);
        lunesbloque6 = findViewById(R.id.lunesbloque6);

        martesbloque1 = findViewById(R.id.martesbloque1);
        martesbloque2 = findViewById(R.id.martesbloque2);
        martesbloque3 = findViewById(R.id.martesbloque3);
        martesbloque4 = findViewById(R.id.martesbloque4);
        martesbloque5 = findViewById(R.id.martesbloque5);
        martesbloque6 = findViewById(R.id.martesbloque6);

        miercolesbloque1 = findViewById(R.id.miercolesbloque1);
        miercolesbloque2 = findViewById(R.id.miercolesbloque2);
        miercolesbloque3 = findViewById(R.id.miercolesbloque3);
        miercolesbloque4 = findViewById(R.id.miercolesbloque4);
        miercolesbloque5 = findViewById(R.id.miercolesbloque5);
        miercolesbloque6 = findViewById(R.id.miercolesbloque6);

        juevesbloque1 = findViewById(R.id.juevesbloque1);
        juevesbloque2 = findViewById(R.id.juevesbloque2);
        juevesbloque3 = findViewById(R.id.juevesbloque3);
        juevesbloque4 = findViewById(R.id.juevesbloque4);
        juevesbloque5 = findViewById(R.id.juevesbloque5);
        juevesbloque6 = findViewById(R.id.juevesbloque6);

        viernesbloque1 = findViewById(R.id.viernesbloque1);
        viernesbloque2 = findViewById(R.id.viernesbloque2);
        viernesbloque3 = findViewById(R.id.viernesbloque3);
        viernesbloque4 = findViewById(R.id.viernesbloque4);
        viernesbloque5 = findViewById(R.id.viernesbloque5);
        viernesbloque6 = findViewById(R.id.viernesbloque6);


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

                                        lunesbloque1.setText(documentSnapshotHorario.getString("lunesbloque1"));
                                        lunesbloque2.setText(documentSnapshotHorario.getString("lunesbloque2"));
                                        lunesbloque3.setText(documentSnapshotHorario.getString("lunesbloque3"));
                                        lunesbloque4.setText(documentSnapshotHorario.getString("lunesbloque4"));
                                        lunesbloque5.setText(documentSnapshotHorario.getString("lunesbloque5"));
                                        lunesbloque6.setText(documentSnapshotHorario.getString("lunesbloque6"));

                                        martesbloque1.setText(documentSnapshotHorario.getString("martesbloque1"));
                                        martesbloque2.setText(documentSnapshotHorario.getString("martesbloque2"));
                                        martesbloque3.setText(documentSnapshotHorario.getString("martesbloque3"));
                                        martesbloque4.setText(documentSnapshotHorario.getString("martesbloque4"));
                                        martesbloque5.setText(documentSnapshotHorario.getString("martesbloque5"));
                                        martesbloque6.setText(documentSnapshotHorario.getString("martesbloque6"));

                                        miercolesbloque1.setText(documentSnapshotHorario.getString("miercolesbloque1"));
                                        miercolesbloque2.setText(documentSnapshotHorario.getString("miercolesbloque2"));
                                        miercolesbloque3.setText(documentSnapshotHorario.getString("miercolesbloque3"));
                                        miercolesbloque4.setText(documentSnapshotHorario.getString("miercolesbloque4"));
                                        miercolesbloque5.setText(documentSnapshotHorario.getString("miercolesbloque5"));
                                        miercolesbloque6.setText(documentSnapshotHorario.getString("miercolesbloque6"));

                                        juevesbloque1.setText(documentSnapshotHorario.getString("juevesbloque1"));
                                        juevesbloque2.setText(documentSnapshotHorario.getString("juevesbloque2"));
                                        juevesbloque3.setText(documentSnapshotHorario.getString("juevesbloque3"));
                                        juevesbloque4.setText(documentSnapshotHorario.getString("juevesbloque4"));
                                        juevesbloque5.setText(documentSnapshotHorario.getString("juevesbloque5"));
                                        juevesbloque6.setText(documentSnapshotHorario.getString("juevesbloque6"));

                                        viernesbloque1.setText(documentSnapshotHorario.getString("viernesbloque1"));
                                        viernesbloque2.setText(documentSnapshotHorario.getString("viernesbloque2"));
                                        viernesbloque3.setText(documentSnapshotHorario.getString("viernesbloque3"));
                                        viernesbloque4.setText(documentSnapshotHorario.getString("viernesbloque4"));
                                        viernesbloque5.setText(documentSnapshotHorario.getString("viernesbloque5"));
                                        viernesbloque6.setText(documentSnapshotHorario.getString("viernesbloque6"));

                                      
                                  }
                                });
                    }
                });
    }




}