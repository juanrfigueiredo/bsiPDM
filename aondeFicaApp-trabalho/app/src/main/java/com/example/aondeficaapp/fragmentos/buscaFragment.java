package com.example.aondeficaapp.fragmentos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aondeficaapp.api.IBGEService;
import com.example.aondeficaapp.api.RetrofitConfig;
import com.example.aondeficaapp.api.ViaCEPService;
import com.example.aondeficaapp.banco.ResultadoDBI;
import com.example.aondeficaapp.objetos.Estado;
import com.example.aondeficaapp.R;
import com.example.aondeficaapp.objetos.Municipio;
import com.example.aondeficaapp.objetos.Resultado;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link buscaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class buscaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1, mParam2;
    private Spinner spinnerEstado, spinnerMunicipio;
    private RetrofitConfig retrofitConfig;
    private IBGEService ibgeService;
    private ViaCEPService viaCEPService;
    private Estado estadoSelecionado;
    private Municipio municipioSelecionado;
    private List<Estado> estados;
    private List<Municipio> municipios;
    private ArrayAdapter<Estado> estadoArrayAdapter;
    private ArrayAdapter<Municipio> municipioArrayAdapter;
    private Button btBuscar;

    private TextView etRua;
    public buscaFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment buscaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static buscaFragment newInstance(String param1, String param2) {
        buscaFragment fragment = new buscaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busca, container, false);
        spinnerEstado = view.findViewById(R.id.spinnerEstado);
        spinnerMunicipio = view.findViewById(R.id.spinnerCidade);
        retrofitConfig = new RetrofitConfig();
        ibgeService = retrofitConfig.getIBGEService();
        viaCEPService = retrofitConfig.getViaCEPSErvice();
        btBuscar = view.findViewById(R.id.btBuscar);
        etRua = view.findViewById(R.id.etRua);
        populaEstado();
        addEventEstadoItemSelected();
        addEventBtBuscarClick();
       return view;

    }

    private void addEventBtBuscarClick() {
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("fui clicado");
                if(spinnerEstado.getSelectedItemId() > 0 && etRua.getText().toString().length() > 0){
                    //tenta achar o cep usando viaCep, caso não conseguir um alert dialog avisa do erro
                    buscaResultados(estadoSelecionado, municipioSelecionado, etRua.getText().toString());
                }
            }
        });
    }

    private void buscaResultados(Estado estado, Municipio municipio, String rua){
        Log.i("buscarResultados_resquest",estado.getSigla()+"/"+municipio.getNome()+"/"+rua);
        Call<List<Resultado>> call = new RetrofitConfig()
                .getViaCEPSErvice()
                .buscarResultados(estado.getSigla(),municipio.getNome(),rua);

        call.enqueue(new Callback<List<Resultado>>() {
            @Override
            public void onResponse(Call<List<Resultado>> call, Response<List<Resultado>> response) {
                Log.i("buscarResultados_result","sucess");
                List<Resultado> resultados = response.body();
                if(resultados != null && resultados.size() > 0){
                    Resultado r = resultados.get(0);
                    if(r.getLogradouro() == ""){
                        alertarResultadosEncontrados();
                    } else {
                        Gson gson = new Gson();
                        ResultadoDBI rdbi = new ResultadoDBI(requireContext());
                        for (Resultado r2d2 : resultados) {
                            rdbi.salvar(r2d2);
                        }
                        alertarResultadosEncontrados(resultados.size());
                    }
                } else {
                    alertarResultadosEncontrados();
                }
            }
            @Override
            public void onFailure(Call<List<Resultado>> call, Throwable t) {
                Log.i("buscarResultados_result","error");
            }
        });
    }
    private void alertarResultadosEncontrados(int size) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("CEPs encontrados!");
        builder.setMessage(size + " CEPs foram encontrados e registrados no fragmento resultados!");
        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),
                        "Elemento adcionado a lista de resultados", Toast.LENGTH_LONG).show();
                municipios = new ArrayList<>();
                municipioArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, municipios);
                spinnerMunicipio.setAdapter(municipioArrayAdapter);
                etRua.setText("");
                spinnerEstado.setSelection(0,true);
            }
        });

        builder.create().show();
    }

    private void alertarResultadosEncontrados() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Nenhum CEP encontrado!");
        builder.setMessage("Nenhum cep foi encontrado com essas informações!");
        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void addEventEstadoItemSelected() {
        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                municipios = new ArrayList<>();
                municipioArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, municipios);
                spinnerMunicipio.setAdapter(municipioArrayAdapter);
                Estado selected = (Estado)adapterView.getItemAtPosition(i);
                estadoSelecionado = selected;
                if(selected.getId() > 0){
                    Log.i("buscarMunicipios_resquest","localidades/estados/"+selected.getSigla()+"municipios");
                    ibgeService.buscarMunicipios(selected.getSigla())
                            .enqueue(new Callback<List<Municipio>>() {
                                @Override
                                public void onResponse(Call<List<Municipio>> call, Response<List<Municipio>> response) {
                                    Log.i("buscarMunicipios_result","sucess");
                                    municipios = response.body();
                                    municipioArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, municipios);
                                    spinnerMunicipio.setAdapter(municipioArrayAdapter);
                                }

                                @Override
                                public void onFailure(Call<List<Municipio>> call, Throwable t) {
                                    Log.e("buscarMunicipios_result","error");
                                    Toast.makeText(getContext(),
                                            "Erro ao carregar lista de cidades", Toast.LENGTH_SHORT);
                                }
                            });
                } else {
                    municipios = new ArrayList<>();
                    municipioArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, municipios);
                    spinnerMunicipio.setAdapter(municipioArrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                municipioSelecionado = (Municipio) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populaEstado() {
        //faz o request e devolve os dados para o spinner
        Log.i("buscarEstados_resquest","localidades/estados/");
        ibgeService.buscarEstados()
                .enqueue(new Callback<List<Estado>>() {
                    @Override
                    public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                        Log.i("buscarEstados_result","sucess");
                        estados = response.body();
                        estados.add(new Estado(0," Selecione um estado","Selecione um estado"));
                        estados.sort(Comparator.comparing(Estado::getSigla));
                        estadoArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, estados);
                        spinnerEstado.setAdapter(estadoArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Estado>> call, Throwable t) {
                        Log.e("buscarEstados_result","error");
                        Toast.makeText(getContext(),
                                "Erro ao carregar lista de estados", Toast.LENGTH_SHORT);
                    }
                });
    }
}