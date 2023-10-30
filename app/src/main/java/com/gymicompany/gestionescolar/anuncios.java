package com.gymicompany.gestionescolar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.gymicompany.gestionescolar.adapter.anuncioAdapter;
import com.gymicompany.gestionescolar.model.AnuncioModel;

public class anuncios extends AppCompatActivity {
    Button btn_ir_crear_notas;
    RecyclerView mRecycler;
    anuncioAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("anuncios");

        FirestoreRecyclerOptions<AnuncioModel> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<AnuncioModel>()
                        .setQuery(query, AnuncioModel.class)
                        .build();

        mAdapter = new anuncioAdapter(firestoreRecyclerOptions);
        mRecycler.setAdapter(mAdapter);

        btn_ir_crear_notas = findViewById(R.id.btn_ir_crear_anuncios);
        btn_ir_crear_notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(anuncios.this, crear_anuncios.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
