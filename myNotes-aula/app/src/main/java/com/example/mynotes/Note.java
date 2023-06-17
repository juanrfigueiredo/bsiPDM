package com.example.mynotes;

import androidx.annotation.NonNull;

public class Note {
    private String titulo,texto;
    private int rating;

    public Note(String titulo, String texto, int rating) {
        this.titulo = titulo;
        this.texto = texto;
        this.rating = rating;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return this.titulo;
    }
}
