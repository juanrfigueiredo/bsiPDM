package com.example.projetoprovab2.objetos;

import java.util.List;

public class ModelosAnos {
    public List<Ano> anos;
    public List<Modelo> modelos;

    public ModelosAnos(List<Ano> anos, List<Modelo> modelos) {
        this.anos = anos;
        this.modelos = modelos;
    }

    public List<Ano> getAnos() {
        return anos;
    }

    public void setAnos(List<Ano> anos) {
        this.anos = anos;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }
}
