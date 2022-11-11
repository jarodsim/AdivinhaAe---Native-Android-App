package com.example.adivinhanumero;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final int random_number = new Random().nextInt(16);
    private int tentativas_restantes = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println(random_number);
    }

    public void Verificar(View v) {
        try {
            EditText numero_digitado = findViewById(R.id.editTextNumero);

            if (numero_digitado.getText().toString().isEmpty()) {
                Toast.makeText(this, "Ops! Digite algum número.", Toast.LENGTH_SHORT).show();
            } else {
                int numero = Integer.parseInt(numero_digitado.getText().toString());

                if (numero == random_number) {
                    Intent i = new Intent(this, Ganhou.class);
                    Bundle parametros = new Bundle();
                    parametros.putInt("tentativas", tentativas_restantes);
                    i.putExtras(parametros);
                    startActivity(i);
                } else {
                    SetaTentativasRestantes();
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

    public void SetaTentativasRestantes() {
        if (tentativas_restantes == 1) {
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
}