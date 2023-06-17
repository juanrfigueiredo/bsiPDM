package com.example.projetoprovab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.projetoprovab2.api.FIPEService;
import com.example.projetoprovab2.api.RetrofitConfig;
import com.example.projetoprovab2.objetos.Ano;
import com.example.projetoprovab2.objetos.Marca;
import com.example.projetoprovab2.objetos.Modelo;
import com.example.projetoprovab2.objetos.ModelosAnos;
import com.example.projetoprovab2.objetos.Valor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FipeActivity extends AppCompatActivity {

    private Button btVoltar, btConsultar;
    private Spinner spinnerMarca, spinnerModelo, spinnerAno;
    private RetrofitConfig retrofitConfig;
    private FIPEService fipeService;
    private TextView tvReferencia, tvValor;
    private List<Marca> marcas;
    private ArrayAdapter<Marca> marcasArrayAdapter;
    private List<Modelo> modelos;
    private ArrayAdapter<Modelo> modelosArrayAdapter;
    private List<Ano> anos;
    private ArrayAdapter<Ano> anosArrayAdapter;
    private List<Valor> valores;
    private String tipo;
    private String marcaSelected, modeloSelected;
    private ModelosAnos modelosAnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fipe);

        btVoltar = findViewById(R.id.btVoltar);
        btConsultar = findViewById(R.id.btConsultar);
        tvReferencia = findViewById(R.id.tvReferencia);
        tvValor = findViewById(R.id.tvValor);
        btVoltar.setOnClickListener(view -> {
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
        });

        retrofitConfig = new RetrofitConfig();
        fipeService = retrofitConfig.getFIPEService();
        spinnerAno = findViewById(R.id.spinnerAno);
        spinnerMarca = findViewById(R.id.spinnerMarca);
        spinnerModelo = findViewById(R.id.spinnerModelo);

        Intent intent = getIntent();
        tipo = intent.getStringExtra("parameter");
        populaMarca(tipo);

        addEventMarcaItemSelected();
        addEventModeloItemSelected();

        btConsultar.setOnClickListener(view -> {
            Ano a = (Ano) spinnerAno.getSelectedItem();
            if(marcaSelected != "-1" && modeloSelected != "-1" && a.getCodigo() != "-1"){
                fipeService.getValor(tipo,marcaSelected,modeloSelected, a.getCodigo()).enqueue(new Callback<Valor>() {
                    @Override
                    public void onResponse(Call<Valor> call, Response<Valor> response) {
                        System.out.println("success "+call.request().url());
                        tvReferencia.setText(response.body().MesReferencia);
                        tvValor.setText(response.body().Valor);
                    }

                    @Override
                    public void onFailure(Call<Valor> call, Throwable t) {

                    }
                });
            }
        });

    }



    private void addEventModeloItemSelected() {
        spinnerModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                anos = new ArrayList<>();
                anosArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, anos);
                spinnerAno.setAdapter(anosArrayAdapter);
                Modelo selected = (Modelo) adapterView.getItemAtPosition(i);
                if(selected.getCodigo() != "-1") {
                    modeloSelected = selected.getCodigo();
                    fipeService.getAnos(tipo, marcaSelected, selected.getCodigo()).enqueue(new Callback<List<Ano>>() {
                        @Override
                        public void onResponse(Call<List<Ano>> call, Response<List<Ano>> response) {
                            System.out.println("success "+call.request().url());
                            anos = response.body();
                            anosArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, anos);
                            spinnerAno.setAdapter(anosArrayAdapter);
                        }

                        @Override
                        public void onFailure(Call<List<Ano>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                modeloSelected = "-1";
                anos = new ArrayList<>();
                anosArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, anos);
                spinnerAno.setAdapter(anosArrayAdapter);
            }
        });
    }

    private void addEventMarcaItemSelected() {
        spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                modelos = new ArrayList<>();
                modelosArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, modelos);
                spinnerModelo.setAdapter(modelosArrayAdapter);
                Marca selected = (Marca) adapterView.getItemAtPosition(i);
                if(selected.getCodigo() != "-1"){
                    marcaSelected = selected.getCodigo();
                    fipeService.getModelos(tipo, selected.getCodigo())
                            .enqueue(new Callback<ModelosAnos>() {
                                @Override
                                public void onResponse(Call<ModelosAnos> call, Response<ModelosAnos> response) {
                                    System.out.println("success "+call.request().url());
                                    modelosAnos = response.body();
                                    modelos = modelosAnos.getModelos();
                                    modelosArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, modelos);
                                    spinnerModelo.setAdapter(modelosArrayAdapter);
                                }

                                @Override
                                public void onFailure(Call<ModelosAnos> call, Throwable t) {
                                    System.out.println("error "+call.request().url());
                                }
                            });
                }else {
                    modelos = new ArrayList<>();
                    modelosArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, modelos);
                    spinnerModelo.setAdapter(modelosArrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void populaMarca(String parameter) {
        System.out.println("parameter = "+parameter);
        fipeService.getMarcas(parameter)
                .enqueue(new Callback<List<Marca>>() {
                    @Override
                    public void onResponse(Call<List<Marca>> call, Response<List<Marca>> response) {
                        System.out.println("success "+call.request().url());
                        marcas = response.body();
                        marcas.add(new Marca("-1","Escolha uma marca"));
                        marcas.sort(Comparator.comparing(Marca::getCodigo));
                        marcasArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, marcas);
                        spinnerMarca.setAdapter(marcasArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Marca>> call, Throwable t) {
                        System.out.println("error "+call.request().url());
                    }
                });


    }
}