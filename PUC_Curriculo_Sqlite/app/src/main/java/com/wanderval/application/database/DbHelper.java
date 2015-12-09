package com.wanderval.application.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.wanderval.application.entidade.Aluno;
import com.wanderval.application.entidade.Profissional;

/**
 * Created by monkey_001 on 25/10/15.
 */
public class DbHelper extends SQLiteOpenHelper {

    DBAdapter da;

    //DATABASE
    private static final String DATABASE_NAME = "contactos5.db";
    private static final int DATABASE_VERSION = 12;

    //TABLES
    //tabela CONTATO
    public static final String TABLE_NAME = "contactos2";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String TELEFONE = "telefone";
    public static final String FOTO = "foto";

    //tabela Academia
    public static final String TABLE_ACADEMIA = "academia";
    public static final String ID_A = "_id";
    public static final String TIPO_A = "tipo";
    public static final String CURSO_A = "curso";
    public static final String INSTITUICAO_A = "instituicao";
    public static final String DATA_A = "data";

    //tabela Empresa
    public static final String TABLE_EMPRESA = "empresa";
    public static final String ID_E = "_id";
    public static final String NOME_E = "nome";
    public static final String CARGO_E = "cargo";
    public static final String DATA_ENTRADA_E = "data_entrada";
    public static final String DATA_SAIDA_E = "data_saida";

    //tabela perfil
    public static final String TABLE_PERFIL = "perfil";
    public static final String ID_P = "_id";
    public static final String NOME_P = "nome";
    public static final String EMAIL_P = "email";
    public static final String TELEFONE_P = "telefone";
    public static final String GRADUADO_P = "graduado";
    public static final String HABILIDADE_P = "habilidade";

    //CREATE TABLE
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( " + ID
            + " integer primary key autoincrement, " + NOME
            + " text not null, " + EMAIL + " text not null, "
            + TELEFONE+" text not null" +
            ", "+ FOTO +" BLOB);";
    private static final String DATABASE_CREATE2 = "create table "
            + TABLE_ACADEMIA + "( " + ID_A
            + " integer primary key autoincrement, " + TIPO_A
            + " text not null, " + CURSO_A + " text not null, "
            + INSTITUICAO_A+" text not null," + DATA_A+" text not null);";

    private static final String DATABASE_CREATE3 = "create table "
            + TABLE_EMPRESA + "( " + ID_E
            + " integer primary key autoincrement, " + NOME_E
            + " text not null, " + CARGO_E + " text not null, "
            + DATA_ENTRADA_E+" text not null," + DATA_SAIDA_E+" text not null);";

    private static final String DATABASE_CREATE4 = "create table "
            + TABLE_PERFIL + "( " + ID_P
            + " integer primary key autoincrement, " + NOME_P
            + " text not null, " + EMAIL_P + " text not null, "
            + TELEFONE_P+" text not null," + GRADUADO_P+" text not null," + HABILIDADE_P+" text not null);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE2);
        db.execSQL(DATABASE_CREATE3);
        db.execSQL(DATABASE_CREATE4);



        //DATAS DEFAULT
        final String sql    =  "INSERT INTO perfil (nome, email, telefone,graduado, habilidade) VALUES('wanderval carvalho','wanderval_carvalho@hotmail.com','998591206','Sistema de Informação'," +
                                "'Javascript, Java, JSF, PHP, NodeJS, Hibernate, MongoDB, SQLServer 2012, MYSQL')";
        db.execSQL(sql);

        final String sql1   =  "INSERT INTO academia (tipo, curso, instituicao, data) VALUES('Graduação','Sistema de Informação','Univás','22/11/2013')";
        db.execSQL(sql1);

        final String sql2   =  "INSERT INTO academia (tipo, curso, instituicao, data) VALUES('Pós-Graduação','Arquitetura de Software Distribuído','Puc Minas','22/11/2016')";
        db.execSQL(sql2);

        final String sql3   =  "INSERT INTO empresa (nome, cargo, data_entrada, data_saida) VALUES('Blumonkey Soluções Tecnológicas','Analista Desenvolvedor','22/02/2013','22/06/2015')";
        db.execSQL(sql3);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACADEMIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPRESA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERFIL);
        onCreate(db);
    }
}