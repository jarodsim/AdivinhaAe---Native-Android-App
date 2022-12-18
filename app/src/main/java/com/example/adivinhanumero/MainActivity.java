package com.example.adivinhanumero;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Cursor cursor;
    private BancoController crud;


    final int random_number = new Random().nextInt(16);
    private int tentativas_restantes = 3;
    Long tempoInicial = new Date().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crud = new BancoController(getBaseContext());

        cursor = crud.listarUsuarios();

        if (cursor.getCount() <= 0) {
            Intent i = new Intent(MainActivity.this, DadosDoUsuarios.class);
            Bundle parametros = new Bundle();
            parametros.putString("metodo", "cadastro");
            i.putExtras(parametros);
            startActivity(i);
        }
    }

    public void Verificar(View v) {
        try {
            EditText numero_digitado = findViewById(R.id.editTextNumero);

            if (numero_digitado.getText().toString().isEmpty()) {
                Toast.makeText(this, "Ops! Digite algum número.", Toast.LENGTH_SHORT).show();
            } else {
                int numero = Integer.parseInt(numero_digitado.getText().toString());

                Long tempoFinal = new Date().getTime();
                Long tempo = tempoFinal - tempoInicial;
                if (numero == random_number) {
                    Intent i = new Intent(this, Ganhou.class);
                    Bundle parametros = new Bundle();
                    parametros.putInt("tentativas", tentativas_restantes);
                    parametros.putLong("tempo", tempo);
                    i.putExtras(parametros);
                    startActivity(i);
                } else {
                    SetaTentativasRestantes(TimeUnit.MILLISECONDS.toSeconds(tempo));
                    TextView subtituloTextView = findViewById(R.id.textSubTitulo);
                    TextView textotentativasRestantes = findViewById(R.id.textTentativasRestantes);

                    subtituloTextView.setTextColor(Color.parseColor("#e74c3c"));
                    subtituloTextView.setTextSize(36);
                    subtituloTextView.setText(VerificarProximidade(random_number, numero));
                    textotentativasRestantes.setText("Tentativas restantes: " + tentativas_restantes);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Toast.makeText(this, "Sinto muito, algo de errado ocorreu com a aplicação.", Toast.LENGTH_SHORT).show();
        }
    }

    private String VerificarProximidade(int random_number, int numero_selecionado) {
        int distancia = Math.abs(random_number - numero_selecionado);

        switch (distancia) {
            case 1:
                return "Bem perto!";
            case 2:
                return "Está perto";
            case 3:
                return "Um pouco distante";
            case 4:
                return "Distante";
            case 5:
                return "Longe demais, pelo amor de Deus";
            default:
                return "Mais perdido que cego em tiroteio";
        }
    }

    public void SetaTentativasRestantes(Long tempo) {
        if (tentativas_restantes == 1) {
            crud = new BancoController(getBaseContext());
            Cursor usuarios = crud.listarUsuarios();
            usuarios.moveToFirst();

            int usuario_id = Integer.parseInt(usuarios.getString(0));
            String tentativas = "3";

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String agora = formatador.format(new Date());

            crud.criarJogo(agora, tentativas, usuario_id, tempo, "perdeu");

            Intent i = new Intent(this, Perdeu.class);
            startActivity(i);
        } else {
            tentativas_restantes -= 1;
        }
    }

    public void Reiniciar(View v) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void IrParaEdicaoDeUsuario(View v) {
        Intent i = new Intent(MainActivity.this, DadosDoUsuarios.class);
        Bundle parametros = new Bundle();
        parametros.putString("metodo", "edicao");
        i.putExtras(parametros);
        startActivity(i);
    }

    public void IrParaListagemDeJogos(View v) {
        Intent i = new Intent(MainActivity.this, Jogos.class);
        startActivity(i);
    }
}