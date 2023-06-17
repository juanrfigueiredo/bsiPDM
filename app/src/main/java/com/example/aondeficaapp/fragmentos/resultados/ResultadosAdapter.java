package com.example.aondeficaapp.fragmentos.resultados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aondeficaapp.R;
import com.example.aondeficaapp.objetos.Estado;
import com.example.aondeficaapp.objetos.Resultado;

import java.util.ArrayList;
import java.util.List;

public class ResultadosAdapter extends ArrayAdapter<Resultado> {
    private LayoutInflater inflater;
    private ArrayList<Resultado> resultados;
    private TextView tvCep, tvRua, tvBairro;

    private Resultado resultado;
    public ResultadosAdapter(@NonNull Context context, int resource, List<Resultado> resultados) {
        super(context, 0, resultados);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = inflater.inflate(R.layout.list_resultados, parent, false);
        }

        resultado = getItem(position);

        tvRua = itemView.findViewById(R.id.tvRua);
        tvBairro = itemView.findViewById(R.id.tvBairro);
        tvCep = itemView.findViewById(R.id.tvCep);

        tvRua.setText(resultado.getLogradouro());
        tvBairro.setText(resultado.getBairro()+"");
        tvCep.setText(resultado.getCep());

        return itemView;
    }
}
