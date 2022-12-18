package com.example.adivinhanumero;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DadosDoUsuarios extends AppCompatActivity {
    private BancoController crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_do_usuarios);

        Intent i = getIntent();
        Bundle parametros = i.getExtras();

        String metodo = parametros.getString("metodo");
        trataFormularioDeAcordoComOMetodo(metodo);
    }


    public void trataFormularioDeAcordoComOMetodo(String metodo) {
        TextView titulo = findViewById(R.id.tvTitulo);
        Button btnSalvar = findViewById(R.id.btnSalvarUsuario);
        View btnVoltar = findViewById(R.id.btnUsuarioVoltar);
        switch (metodo) {
            case "cadastro":
                titulo.setText("Cadastro de jogador");
                btnSalvar.setText("Criar usuário");

                btnVoltar.setVisibility(View.GONE);

                btnSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CriarUsuario();
                    }
                });
                break;
            case "edicao":
                titulo.setText("Edição de usuário");
                btnSalvar.setText("Editar usuário");

                crud = new BancoController(getBaseContext());

                Cursor usuarios = crud.listarUsuarios();
                usuarios.moveToFirst();

                EditText nomeET = findViewById(R.id.ptNome);
                EditText idadeET = findViewById(R.id.ptIdade);
                EditText emailET = findViewById(R.id.ptEmail);

                nomeET.setText(usuarios.getString(1));
                idadeET.setText(usuarios.getString(2));
                emailET.setText(usuarios.getString(3));

                int usuario_id = Integer.parseInt(usuarios.getString(0));

                btnSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditarUsuario(usuario_id);
                    }
                });
                break;
        }
    }

    public void CriarUsuario() {
        EditText nomeET = findViewById(R.id.ptNome);
        EditText idadeET = findViewById(R.id.ptIdade);
        EditText emailET = findViewById(R.id.ptEmail);

        String nome = nomeET.getText().toString();
        String idade = idadeET.getText().toString();
        String email = emailET.getText().toString();

        String resultado;

        if (nome.isEmpty() || idade.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            crud = new BancoController(getBaseContext());
            resultado = crud.criarUsuario(nome, idade, email);
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            Cursor usuarios = crud.listarUsuarios();
            usuarios.moveToFirst();

            Intent i = new Intent(DadosDoUsuarios.this, MainActivity.class);
            Bundle parametros = new Bundle();
            parametros.putString("usuario_id", usuarios.getString(0));
            i.putExtras(parametros);
            startActivity(i);
        }
    }

    public void EditarUsuario(int usuario_id) {
        EditText nomeET = findViewById(R.id.ptNome);
        EditText idadeET = findViewById(R.id.ptIdade);
        EditText emailET = findViewById(R.id.ptEmail);

        String nome = nomeET.getText().toString();
        String idade = idadeET.getText().toString();
        String email = emailET.getText().toString();

        if (nome.isEmpty() || idade.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            crud.atualizarUsuario(usuario_id, nome, idade, email);
            Toast.makeText(getApplicationContext(), "Usuário atualizado com sucesso!", Toast.LENGTH_LONG).show();

            Intent i = new Intent(DadosDoUsuarios.this, MainActivity.class);
            Bundle parametros = new Bundle();
            parametros.putString("usuario_id", String.valueOf(usuario_id));
            i.putExtras(parametros);
            startActivity(i);
        }
    }

    public void Voltar(View v) {
        Intent i = new Intent(DadosDoUsuarios.this, MainActivity.class);
        Bundle parametros = new Bundle();
        i.putExtras(parametros);
        startActivity(i);
    }
}