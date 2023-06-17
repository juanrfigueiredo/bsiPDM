package com.example.aondeficaapp.api;

import com.example.aondeficaapp.objetos.Estado;
import com.example.aondeficaapp.objetos.Municipio;
import com.example.aondeficaapp.objetos.Resultado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCEPService {
    @GET("{uf}/{municipio}/{rua}/json")
    Call<List<Resultado>> buscarResultados(@Path("uf") String uf,
                                           @Path("municipio") String municipio,
                                           @Path("rua") String rua);
}
