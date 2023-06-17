package com.example.buscacep;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CEP {
    public String cep;
    public String logradouro;
    public String complemento;
    public String bairro;
    public String localidade;
    public String uf;
    public String ibge;
    public String gia;
    public String ddd;
    public String siafi;

    @Override
    public String toString(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.toJson(this);

    }
}
