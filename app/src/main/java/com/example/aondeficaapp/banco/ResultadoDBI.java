package com.example.aondeficaapp.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aondeficaapp.objetos.Resultado;

import java.util.ArrayList;
import java.util.List;

public class ResultadoDBI {
    //lida da comunicação entre o objeto Resultado e o Banco
    private Conexao con;
    private final String TABLE="resultados";

    public ResultadoDBI (Context context) {
        con = new Conexao(context);
        con.conectar();
    }

    public boolean salvar(Resultado r) {
        ContentValues dados = new ContentValues();
        dados.put("res_cep", r.getCep());
        dados.put("res_logradouro", r.getLogradouro());
        dados.put("res_complemento", r.getComplemento());
        dados.put("res_bairro", r.getBairro());
        dados.put("res_localidade", r.getLocalidade());
        dados.put("res_uf", r.getUf());
        dados.put("res_ibge", r.getIbge());
        dados.put("res_gia", r.getGia());
        dados.put("res_ddd", r.getDdd());
        dados.put("res_siafi", r.getSiafi());
        return con.inserir(TABLE,dados)>0;
    }
    public boolean alterar(Resultado r) {
        ContentValues dados=new ContentValues();
        dados.put("res_id",r.getId());
        dados.put("res_ddd", r.getDdd());
        dados.put("res_siafi", r.getSiafi());
        dados.put("res_cep", r.getCep());
        dados.put("res_logradouro", r.getLogradouro());
        dados.put("res_complemento", r.getComplemento());
        dados.put("res_bairro", r.getBairro());
        dados.put("res_localidade", r.getLocalidade());
        dados.put("res_uf", r.getUf());
        dados.put("res_ibge", r.getIbge());
        dados.put("res_gia", r.getGia());
        return con.alterar(TABLE,dados,"res_id="+r.getId())>0;
    }
    public boolean apagar(long chave) {
        return con.apagar(TABLE,"res_id="+chave)>0;
    }
    public boolean apagarTodos() {
        return con.apagarTodos(TABLE)>0;
    }
    public Resultado getResultado(int id) {
        Resultado data = null;

        Cursor cursor=con.consultar("select * from "+TABLE+"where res_id="+id);
        if(cursor.moveToFirst())
            data = new Resultado(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9), cursor.getString(10));
        cursor.close();;
        return data;
    }
    public List<Resultado> getAll(){
        Resultado r = null;
        List<Resultado> listResultados = new ArrayList<Resultado>();
        Cursor cursor = con.consultar("select * from "+TABLE);
        while(cursor.moveToNext()){
            r = new Resultado(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9), cursor.getString(10));

            listResultados.add(r);
            System.out.println(r.getBairro()+r.getCep()+r.getComplemento()+r.getLogradouro()+r.getCep()+r.getGia()+r.getIbge()+r.getUf());
        }
        cursor.close();
        return listResultados;
    }
    public ArrayList<Resultado> get(String filtro)
    {   ArrayList <Resultado> objs = new ArrayList();
        String sql="select * from "+TABLE+ "where"+filtro;
        if (!filtro.equals(""))
            sql+=" where "+filtro;

        Cursor cursor=con.consultar(sql);

        if(cursor.moveToFirst())
            while (!cursor.isAfterLast()) {
                objs.add(new Resultado(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getString(9), cursor.getString(10)));
                cursor.moveToNext();
            }
        cursor.close();;
        return objs;
    }
}
