package com.example.aondeficaapp.objetos;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Estado {
    private int id;
    private String sigla,nome;

    public Estado(int id, String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getSigla();
    }
}
