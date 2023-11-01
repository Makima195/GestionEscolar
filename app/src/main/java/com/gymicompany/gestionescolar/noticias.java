package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class noticias extends AppCompatActivity {

    private TextView dataTextView;
    private DocumentReference documentRef;
    private ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        dataTextView = findViewById(R.id.info);

        // Inicializa Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        documentRef = db.collection("noticias").document("test");

        // Configura un oyente para recuperar datos
        listenerRegistration = documentRef.addSnapshotListener((documentSnapshot, e) -> {
            if (e != null) {
                // Maneja errores si es necesario
                return;
            }

            if (documentSnapshot != null && documentSnapshot.exists()) {
                // El documento existe, accede a sus datos
                String data = documentSnapshot.getString("info");
                dataTextView.setText(data);
            } else {
                // El documento no existe o está vacío
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detén el oyente cuando la actividad se destruye para evitar fugas de memoria
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }
}