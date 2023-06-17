package com.example.projetoprovab2.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.ref.Cleaner;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit FIPEretrofit;
    private FIPEService FIPEService;

    public RetrofitConfig() {
        Gson gson = new GsonBuilder().setLenient().create();
        FIPEretrofit = new Retrofit.Builder().baseUrl("https://parallelum.com.br/fipe/api/v1/").addConverterFactory(GsonConverterFactory.create()).build();
    }

    public FIPEService getFIPEService(){
        return this.FIPEretrofit.create(FIPEService.class);
    }
}