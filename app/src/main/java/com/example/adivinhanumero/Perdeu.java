package com.example.adivinhanumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Perdeu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdeu);
    }

    public void Voltar(View r){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}