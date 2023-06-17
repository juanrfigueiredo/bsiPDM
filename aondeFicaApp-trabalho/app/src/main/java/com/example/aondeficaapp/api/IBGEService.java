package com.example.aondeficaapp.api;

import com.example.aondeficaapp.objetos.Estado;
import com.example.aondeficaapp.objetos.Municipio;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IBGEService {
    @GET("localidades/estados/")
    Call<List<Estado>> buscarEstados();

    @GET("localidades/estados/{uf}/municipios")
    Call<List<Municipio>> buscarMunicipios(@Path("uf") String uf);
}
