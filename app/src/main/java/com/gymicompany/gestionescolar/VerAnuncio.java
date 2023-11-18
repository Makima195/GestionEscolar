package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerAnuncio extends AppCompatActivity {
    TextView Anuncio;
    TextView Titulo;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_anuncio);

        Anuncio = findViewById(R.id.Anuncio_ver);
        Titulo = findViewById(R.id.Titulo_ver);

        user = FirebaseAuth.getInstance().getCurrentUser();

        CargarAnuncio();


    }

    private void CargarAnuncio() {
        db.collection("user")
                .document(user.getUid())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // RECUERDA LA PUTA MAYUSCULA DE "C"URSO >:DDDDDDD
                        String pruebacurso = documentSnapshot.getString("anuncio_general");
                        db.collection("anuncios")
                                .document(pruebacurso)
                                .get()
                                .addOnSuccessListener(documentSnapshotanuncio -> {
                                    if (documentSnapshotanuncio.exists()) {
                                        Titulo.setText(documentSnapshotanuncio.getString("Titulo"));
                                        Anuncio.setText(documentSnapshotanuncio.getString("Anuncio"));
                                        //EJEMPLO DE OTROS DIAS TODO PUEDE ESTAR JUNTO
                                        //martesbloque2.setText(documentSnapshotHorario.getString("bloque2"));
                                        // Puedes continuar con el resto de los bloques o datos aquí.
                                    }
                                });
                    }
                });
    }
}