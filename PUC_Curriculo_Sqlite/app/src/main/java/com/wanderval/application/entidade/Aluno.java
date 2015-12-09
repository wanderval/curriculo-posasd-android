package com.wanderval.application.entidade;

/**
 * Created by monkey_001 on 25/10/15.
 */
public class Aluno {

    private long _id;
    private String tipo;
    private String curso;
    private String instituicao;
    private String dataConclusao;

    public Aluno(long id, String tipo, String curso, String instituicao, String dataConclusao){
        this._id = id;
        this.tipo =tipo;
        this.curso = curso;
        this.instituicao = instituicao;
        this.dataConclusao=dataConclusao;
    }

    //getter/setter
    public long getId(){
        return this._id;
    }
    public void setId(long id){
        this._id = id;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public String getCurso(){
        return this.curso;
    }
    public void setCurso(String curso){
        this.curso = curso;
    }
    public String getInstituicao(){
        return this.instituicao;
    }
    public void setInstituicao(String instituicao){
        this.instituicao = instituicao;
    }
    public String getDataConclusao(){
        return this.dataConclusao;
    }
    public void setDataConclusao(String dataConclusao){
        this.dataConclusao = dataConclusao;
    }

    //method
    public String getSimpleName() {
        return "Aluno";
    }
}
