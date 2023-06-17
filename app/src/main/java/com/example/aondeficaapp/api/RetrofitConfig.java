package com.example.aondeficaapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit IBGEretrofit, ViaCEPretrofit;

    public RetrofitConfig() {
        Gson gson = new GsonBuilder().setLenient().create();
        IBGEretrofit = new Retrofit.Builder().baseUrl("https://servicodados.ibge.gov.br/api/v1/").addConverterFactory(GsonConverterFactory.create()).build();
        ViaCEPretrofit = new Retrofit.Builder().baseUrl("https://viacep.com.br/ws/").addConverterFactory(GsonConverterFactory.create()).build();
    }
    public IBGEService getIBGEService(){
        return this.IBGEretrofit.create(IBGEService.class);
    }

    public ViaCEPService getViaCEPSErvice(){
        return this.ViaCEPretrofit.create(ViaCEPService.class);
    }
}
