package com.example.projetoprovab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btAutomovel, btMoto, btCaminhao;
    private int  AUTOMOVEL = 1, MOTO = 2, CAMINHAO = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAutomovel = findViewById(R.id.btAutomovel);
        btMoto = findViewById(R.id.btMoto);
        btCaminhao = findViewById(R.id.btCaminhao);

        btAutomovel.setOnClickListener(view -> {openFIPE(AUTOMOVEL);});
        btMoto.setOnClickListener(view -> {openFIPE(MOTO);});
        btCaminhao.setOnClickListener(view -> {openFIPE(CAMINHAO);});
    }

    private void openFIPE(int escolha) {
        Intent fipeActivity = new Intent(this, FipeActivity.class);

        switch (escolha){
            case 1:
                fipeActivity.putExtra("parameter","carros");
                break;
            case 2:
                fipeActivity.putExtra("parameter","motos");
                break;
            case 3:
                fipeActivity.putExtra("parameter","caminhoes");
                break;
            default:
                fipeActivity.putExtra("parameter","");
                break;
        }
        startActivity(fipeActivity);
    }
}