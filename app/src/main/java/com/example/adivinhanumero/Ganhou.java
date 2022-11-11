package com.example.adivinhanumero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ganhou extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Intent i = getIntent();
        Bundle parametros = i.getExtras();

        int tentativas = parametros.getInt("tentativas");

        TextView text_resultado = findViewById(R.id.textResultado);
        text_resultado.setText("PARABÉMS! VOCÊ ACERTOU COM " + ((3 - tentativas) + 1) + " TENTATIVAS");
    }

    public void Voltar(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}