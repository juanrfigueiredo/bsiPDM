package com.example.aondeficaapp.atividades;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.aondeficaapp.R;
import com.example.aondeficaapp.banco.ResultadoDBI;
import com.example.aondeficaapp.fragmentos.buscaFragment;
import com.example.aondeficaapp.fragmentos.mapaFragment;
import com.example.aondeficaapp.fragmentos.resultados.resultadoFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Button btSair;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_menu,R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.getNavigationViewReady();



        ResultadoDBI rdbi = new ResultadoDBI(this);
        rdbi.apagarTodos();
        addEventToButton();


    }

    private void addEventToButton() {
        btSair = findViewById(R.id.btFechar);
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getNavigationViewReady() {
        navigationView.setNavigationItemSelectedListener(item -> {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft;
            switch (item.getItemId()) {
                case R.id.menuItemBuscar:
                    Toast.makeText(this, "Buscar foi clicado", Toast.LENGTH_SHORT).show();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.frameLayout, new buscaFragment());
                    ft.commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.menuItemResultados:
                    Toast.makeText(this, "Resultados foi clicado", Toast.LENGTH_SHORT).show();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.frameLayout, new resultadoFragment());
                    ft.commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.menuItemMapa:
                    Toast.makeText(this, "Mapa foi clicado", Toast.LENGTH_SHORT).show();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.frameLayout, new mapaFragment());
                    ft.commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return true;
        });
    }


}