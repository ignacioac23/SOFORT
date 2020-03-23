package com.example.proyectomenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        DisplayMetrics medidasVentana=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho=medidasVentana.widthPixels;
        int alto=medidasVentana.heightPixels;
        getWindow().setLayout((int)(ancho*1),(int)(alto*1));






    }

    public void botonexist (View view){
        Intent botonexist = new Intent(this, Mapa.class);
        startActivity(botonexist);
    }


}
