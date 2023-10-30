package com.gymicompany.gestionescolar.model;

public class AnuncioModel {
    private String anuncio;
    private String titulo;

    public AnuncioModel(String anuncio, String titulo) {
        this.anuncio = anuncio;
        this.titulo = titulo;
    }

    public String getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(String anuncio) {
        this.anuncio = anuncio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
