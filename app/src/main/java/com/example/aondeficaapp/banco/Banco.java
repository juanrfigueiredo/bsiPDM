package com.example.aondeficaapp.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    public Banco(Context context) {
        super(context, "resutados.db", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE resultados " +
                "(res_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "res_ddd INTEGER, " +
                "res_siafi INTEGER, " +
                "res_cep VARCHAR (15), " +
                "res_logradouro VARCHAR (30), " +
                "res_complemento VARCHAR(30), " +
                "res_bairro VARCHAR(30), " +
                "res_localidade VARCHAR(30), " +
                "res_uf VARCHAR(2), " +
                "res_ibge VARCHAR(20), " +
                "res_gia VARCHAR (10));"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS resultados");
        onCreate(sqLiteDatabase);
    }
}
