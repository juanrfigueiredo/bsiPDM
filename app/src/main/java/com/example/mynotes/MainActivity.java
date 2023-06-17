package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btConfirmar,btCancelar;
    private TextView txTexto, txTitulo;
    private RatingBar ratingBar;

    private Notepad notepad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btConfirmar = findViewById(R.id.btConfirmar);
        btCancelar = findViewById(R.id.btCancelar);
        ratingBar = findViewById(R.id.ratingBar);
        txTexto = findViewById(R.id.txTexto);
        txTitulo = findViewById(R.id.txTitulo);
        btCancelar.setOnClickListener(e->{cancelar();});
        btConfirmar.setOnClickListener(e->{confirmar();});
        notepad = new Notepad();
    }

    private void cancelar(){
        txTexto.setText("");
        txTitulo.setText("");
        ratingBar.setRating(1);
    }

    private void confirmar(){
        Note note = new Note(
                txTexto.getText().toString(),
                txTitulo.getText().toString(),
                (int) ratingBar.getRating());

        notepad.add(note);
        cancelar();
    }
}