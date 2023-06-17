package com.example.mywebview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btHome;
    private Button RickRoll;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btHome = findViewById(R.id.btHome);
        webView = findViewById(R.id.webView);
        RickRoll = findViewById(R.id.btRickRoll);
        btHome.setOnClickListener(e->toHome());
        RickRoll.setOnClickListener(e->toVideo());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        webView.loadUrl("https://www.unoeste.br");
    }

    private void toHome() {
        webView.loadUrl("https://www.unoeste.br");
    }
    private void toVideo(){
        int random = (int) (Math.random() * 100);
        Toast.makeText(this, String.valueOf(random),
                Toast.LENGTH_SHORT).show();
        webView.reload();
        if(random > 90)
            webView.loadUrl("https://media.tenor.com/SSY2V0RrU3IAAAAd/rick-roll-rick-rolled.gif");
        else{
            webView.loadUrl("https://www.unoeste.br/fipp/estagio/relatorio/login.asp");
        }

    }

}