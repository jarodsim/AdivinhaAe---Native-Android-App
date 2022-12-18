package com.example.adivinhanumero;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class Jogos extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);

        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.listarJogos();

        String[] nomeCampos = new String[]{CriaBanco.DATA_JOGO, CriaBanco.STATUS_JOGO, CriaBanco.TENTATIVAS_JOGO, CriaBanco.TEMPO_JOGO};
        int[] idViews = new int[]{R.id.data, R.id.status, R.id.tentativas, R.id.tempo};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_jogos, cursor, nomeCampos, idViews, 0);
        lista = findViewById(R.id.lvJogos);
        lista.setAdapter(adaptador);
    }


    public void Voltar(View v) {
        Intent i = new Intent(Jogos.this, MainActivity.class);
        startActivity(i);
    }
}