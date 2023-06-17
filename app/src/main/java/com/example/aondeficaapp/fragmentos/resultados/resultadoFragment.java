package com.example.aondeficaapp.fragmentos.resultados;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aondeficaapp.R;
import com.example.aondeficaapp.banco.ResultadoDBI;
import com.example.aondeficaapp.objetos.Resultado;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link resultadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class resultadoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1, mParam2;

    private ListView listView;
    private ResultadoDBI resultadoDBI;
    private List<Resultado> resultados;


    public resultadoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment resultadoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static resultadoFragment newInstance(String param1, String param2) {
        resultadoFragment fragment = new resultadoFragment();
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
        View view = inflater.inflate(R.layout.fragment_resultado, container,false);
        listView = view.findViewById(R.id.lvResultados);

        resultadoDBI = new ResultadoDBI(requireContext());
        resultados = resultadoDBI.getAll();
        ArrayAdapter<Resultado> aar = new ResultadosAdapter(requireContext(), android.R.layout.activity_list_item, resultados);
        listView.setAdapter(aar);






        return view;
    }
}