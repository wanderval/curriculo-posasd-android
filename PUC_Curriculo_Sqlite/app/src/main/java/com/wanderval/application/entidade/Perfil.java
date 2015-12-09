package com.wanderval.application.entidade;

import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;

import com.wanderval.application.R;
import com.wanderval.application.database.DBAdapter;

/**
 * Created by monkey_001 on 26/10/15.
 */
public class Perfil {

    private long _id;
    private String nome;
    private String email;
    private String telefone;
    private String habilidade;

    DBAdapter datasource;
    Perfil perfil;

    public Perfil(String nome, String email, String telefone, String habilidade){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.habilidade = habilidade;
    }

    public Perfil(long id, String nome, String email, String telefone){
        this._id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }


    public long getId(){
        return this._id;
    }
    public void setId(long id){
        this._id = id;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getTelefone(){
        return this.telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getHabilidade(){
        return this.habilidade;
    }
    public void setHabilidade(String habilidade){this.habilidade = habilidade;}

}
