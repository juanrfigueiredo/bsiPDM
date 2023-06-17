package com.example.projetoprovab2.api;

import com.example.projetoprovab2.objetos.Ano;
import com.example.projetoprovab2.objetos.Marca;
import com.example.projetoprovab2.objetos.Modelo;
import com.example.projetoprovab2.objetos.ModelosAnos;
import com.example.projetoprovab2.objetos.Valor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FIPEService {
    @GET("{veiculo}/marcas/")
    Call<List<Marca>> getMarcas(@Path("veiculo") String veiculo);

    @GET("{veiculo}/marcas/{cod_marca}/modelos")
    Call<ModelosAnos> getModelos(@Path("veiculo") String veiculo, @Path("cod_marca") String cod_marca);

    @GET("{veiculo}/marcas/{cod_marca}/modelos/{cod_modelo}/anos/")
    Call<List<Ano>> getAnos(@Path("veiculo") String veiculo, @Path("cod_marca") String cod_marca, @Path("cod_modelo") String cod_modelo);

    @GET("{veiculo}/marcas/{cod_marca}/modelos/{cod_modelo}/anos/{cod_ano}/")
    Call<Valor> getValor(@Path("veiculo") String veiculo, @Path("cod_marca") String cod_marca, @Path("cod_modelo") String cod_modelo, @Path("cod_ano") String cod_ano);
}
