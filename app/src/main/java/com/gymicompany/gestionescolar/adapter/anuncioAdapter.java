package com.gymicompany.gestionescolar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.gymicompany.gestionescolar.R;
import com.gymicompany.gestionescolar.model.AnuncioModel;
import com.gymicompany.gestionescolar.model.anuncioModel;

public class anuncioAdapter extends FirestoreRecyclerAdapter<anuncioModel, anuncioAdapter.ViewHolder> {

    public anuncioAdapter(@NonNull FirestoreRecyclerOptions<AnuncioModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull AnuncioModel anuncio2) {
        viewHolder.TituloAnuncio.setText(anuncio2.getTitulo());
        viewHolder.AnuncioAnuncio.setText(anuncio2.getAnuncio());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_anuncio_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView AnuncioAnuncio, TituloAnuncio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AnuncioAnuncio = itemView.findViewById(R.id.anuncio);
            TituloAnuncio = itemView.findViewById(R.id.titulo);
        }
    }
}
