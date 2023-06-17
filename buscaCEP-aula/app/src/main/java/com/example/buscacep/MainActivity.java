package com.example.buscacep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etCep,etResult;
    private Button btConsultar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCep=findViewById(R.id.etCep);
        btConsultar=findViewById(R.id.btConsultar);
        etResult=findViewById((R.id.etResult));
        btConsultar.setOnClickListener(e->{buscarCep();});
    }

    private void buscarCep() {

        Call<CEP> call = new RetrofitConfig().getCEPService().buscarCEP(etCep.getText().toString());
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                CEP cep=response.body();
                etResult.setText(cep.toString());
                System.out.println(cep);
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                etResult.setText(t.getMessage());
            }
        });
    }

}