package com.example.adivinhanumero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private final CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String criarUsuario(String nome, String idade, String email) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME_USUARIO, nome);
        valores.put(CriaBanco.IDADE_USUARIO, idade);
        valores.put(CriaBanco.EMAIL_USUARIO, email);

        resultado = db.insert(CriaBanco.TABELA_USUARIO, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro.";
        else
            return "Registro inserido com sucesso.";
    }

    public void atualizarUsuario(int id, String nome, String idade, String email) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID_USUARIO + "=" + id;

        valores = new ContentValues();
        valores.put(CriaBanco.NOME_USUARIO, nome);
        valores.put(CriaBanco.IDADE_USUARIO, idade);
        valores.put(CriaBanco.EMAIL_USUARIO, email);

        db.update(CriaBanco.TABELA_USUARIO, valores, where, null);
        db.close();
    }

//    public Cursor pegarUsuarioPorId(int id) {
//        Cursor cursor;
//        String[] campos = {CriaBanco.ID_USUARIO, CriaBanco.NOME_USUARIO, CriaBanco.IDADE_USUARIO, CriaBanco.EMAIL_USUARIO};
//        String where = CriaBanco.ID_USUARIO + "=" + id;
//        db = banco.getReadableDatabase();
//        cursor = db.query(CriaBanco.TABELA_USUARIO, campos, where, null, null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//        db.close();
//        return cursor;
//    }

    public Cursor listarJogos() {
        Cursor cursor;
        String[] campos = {CriaBanco.ID_JOGO, CriaBanco.DATA_JOGO, CriaBanco.TEMPO_JOGO, CriaBanco.TENTATIVAS_JOGO, CriaBanco.STATUS_JOGO};
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA_JOGO, campos, null, null, null, null, "tempo", null);

        if (cursor != null)
            cursor.moveToFirst();
        db.close();
        return cursor;
    }

    public Cursor listarUsuarios() {
        Cursor cursor;
        String[] campos = {CriaBanco.ID_USUARIO, CriaBanco.NOME_USUARIO, CriaBanco.IDADE_USUARIO, CriaBanco.EMAIL_USUARIO};
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA_USUARIO, campos, null, null, null, null, null, "1");

        if (cursor != null)
            cursor.moveToFirst();
//        db.close();
        System.out.println(cursor.getCount());
        return cursor;
    }

    public String criarJogo(String data, String tentativas, int id_usuario, String tempo, String status) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.DATA_JOGO, data);
        valores.put(CriaBanco.TEMPO_JOGO, tempo);
        valores.put(CriaBanco.TENTATIVAS_JOGO, tentativas);
        valores.put(CriaBanco.ID_USUARIO_JOGO, id_usuario);
        valores.put(CriaBanco.STATUS_JOGO, status);


        resultado = db.insert(CriaBanco.TABELA_JOGO, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro.";
        else
            return "Registro inserido com sucesso.";
    }
}
