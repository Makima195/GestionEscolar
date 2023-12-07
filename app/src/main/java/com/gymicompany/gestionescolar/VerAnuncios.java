package com.gymicompany.gestionescolar;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class VerAnuncios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_anuncios);
        // Inicializar Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtener referencia al ListView en tu diseño
        ListView listView = findViewById(R.id.lista_anuncios);

        // Crear una lista para almacenar los datos de Firestore
        final ArrayList<String> dataList = new ArrayList<>();

        // Obtener datos de Firestore y actualizar el ListView
        db.collection("anuncios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Aquí puedes acceder a los datos de cada documento
                                String titulo = document.getString("Titulo");
                                String anuncio = document.getString("Anuncio");
                                // Agregar ambos datos a la lista
                                dataList.add(titulo + ": " + anuncio);
                            }

                            // Actualizar el ListView con los datos
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(VerAnuncios.this,
                                    android.R.layout.simple_list_item_1, dataList);
                            listView.setAdapter(adapter);
                        } else {
                            Log.e(TAG, "Error obteniendo documentos: ", task.getException());
                        }
                    }
                });
    }
}