package com.example.projetoprovab2.objetos;

public class Valor {

    public int TipoVeiculo;
    public String Valor;

    public String Marca;

    public String Modelo;

    public int AnoModelo;

    public String Combustivel;

    public String CodigoFipe;

    public String MesReferencia;

    public String SiglaCombustivel;

    public Valor(int tipoVeiculo, String valor, String marca, String modelo, int anoModelo, String combustivel, String codigoFipe, String mesReferencia, String siglaCombustivel) {
        TipoVeiculo = tipoVeiculo;
        Valor = valor;
        Marca = marca;
        Modelo = modelo;
        AnoModelo = anoModelo;
        Combustivel = combustivel;
        CodigoFipe = codigoFipe;
        MesReferencia = mesReferencia;
        SiglaCombustivel = siglaCombustivel;
    }

    public int getTipoVeiculo() {
        return TipoVeiculo;
    }

    public void setTipoVeiculo(int tipoVeiculo) {
        TipoVeiculo = tipoVeiculo;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public int getAnoModelo() {
        return AnoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        AnoModelo = anoModelo;
    }

    public String getCombustivel() {
        return Combustivel;
    }

    public void setCombustivel(String combustivel) {
        Combustivel = combustivel;
    }

    public String getCodigoFipe() {
        return CodigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        CodigoFipe = codigoFipe;
    }

    public String getMesReferencia() {
        return MesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        MesReferencia = mesReferencia;
    }

    public String getSiglaCombustivel() {
        return SiglaCombustivel;
    }

    public void setSiglaCombustivel(String siglaCombustivel) {
        SiglaCombustivel = siglaCombustivel;
    }
}