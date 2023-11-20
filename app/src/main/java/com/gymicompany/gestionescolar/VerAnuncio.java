package com.gymicompany.gestionescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class VerAnuncio extends AppCompatActivity {
    TextView Anuncio;
    TextView Titulo;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference collectionRef = db.collection("anuncios");


    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_anuncio);

        Anuncio = findViewById(R.id.Anuncio_ver);
        Titulo = findViewById(R.id.Titulo_ver);

        user = FirebaseAuth.getInstance().getCurrentUser();

        //CargarAnuncio();
        Prueba();

    }

        public void Prueba(){

            collectionRef.orderBy("anuncios", Query.Direction.DESCENDING)
                    .limit(1)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                                // Accede a los campos que deseas mostrar en los TextViews
                                String campo1 = document.getString("Anuncio");
                                String campo2 = document.getString("Titulo");

                                // Actualiza los TextViews con los valores de los campos
                                Anuncio.setText(campo1);
                                Titulo.setText(campo2);
                            } else {
                                // La colección está vacía, no hay documentos
                                Log.d("TAG", "La colección está vacía");
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Manejar errores de lectura
                            Log.e("TAG", "Error al leer el documento", e);
                        }
                    });
        }


     /* private void CargarAnuncio() {
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
                                .addOnSuccessListener(documentSnapshotanuncios -> {
                                    if (documentSnapshotanuncios.exists()) {
                                        Titulo.setText(documentSnapshotanuncios.getString("Titulo"));
                                        Anuncio.setText(documentSnapshotanuncios.getString("Anuncio"));
                                        //EJEMPLO DE OTROS DIAS TODO PUEDE ESTAR JUNTO
                                        //martesbloque2.setText(documentSnapshotHorario.getString("bloque2"));
                                        // Puedes continuar con el resto de los bloques o datos aquí.
                                    }
                                });
                    }
                });
    }
    */

}