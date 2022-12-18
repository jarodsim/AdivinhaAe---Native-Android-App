package com.example.adivinhanumero;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "adivinha.db";
    protected static final int VERSAO = 1;

    protected static final String TABELA_USUARIO = "Usuarios";
    protected static final String ID_USUARIO = "_id";
    protected static final String NOME_USUARIO = "nome";
    protected static final String IDADE_USUARIO = "idade";
    protected static final String EMAIL_USUARIO = "email";

    protected static final String TABELA_JOGO = "Jogo";
    protected static final String ID_JOGO = "_id";
    protected static final String DATA_JOGO = "data";
    protected static final String TENTATIVAS_JOGO = "tentativas";
    protected static final String TEMPO_JOGO = "tempo";
    protected static final String STATUS_JOGO = "status";
    protected static final String ID_USUARIO_JOGO = "id_usuario";

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_usuario = "CREATE TABLE " + TABELA_USUARIO + "("
                + ID_USUARIO + " integer primary key autoincrement,"
                + NOME_USUARIO + " text,"
                + IDADE_USUARIO + " integer,"
                + EMAIL_USUARIO + " text"
                + ")";
        db.execSQL(sql_usuario);


        String sql_jogo = "CREATE TABLE " + TABELA_JOGO + "("
                + ID_JOGO + " integer primary key autoincrement,"
                + DATA_JOGO + " text,"
                + TENTATIVAS_JOGO + " text,"
                + TEMPO_JOGO + " integer,"
                + STATUS_JOGO + " text,"
                + ID_USUARIO_JOGO + " integer"
                + ")";
        db.execSQL(sql_jogo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABELA_JOGO + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABELA_USUARIO + "'");
        onCreate(db);
    }
}
