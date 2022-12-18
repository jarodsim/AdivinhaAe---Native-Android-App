package com.example.adivinhanumero;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ganhou extends AppCompatActivity {
    private BancoController crud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Intent i = getIntent();
        Bundle parametros = i.getExtras();

        crud = new BancoController(getBaseContext());
        Cursor usuarios = crud.listarUsuarios();
        usuarios.moveToFirst();

        int usuario_id = Integer.parseInt(usuarios.getString(0));
        int tentativas = parametros.getInt("tentativas");
        Long tempo = parametros.getLong("tempo");

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String agora = formatador.format(new Date());

        crud.criarJogo(agora, String.valueOf(tentativas), usuario_id, String.valueOf(tempo), "ganhou");

        TextView text_resultado = findViewById(R.id.textResultado);
        text_resultado.setText("PARABÉNS! VOCÊ ACERTOU COM " + ((3 - tentativas) + 1) + " TENTATIVAS");
    }

    public void Voltar(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}