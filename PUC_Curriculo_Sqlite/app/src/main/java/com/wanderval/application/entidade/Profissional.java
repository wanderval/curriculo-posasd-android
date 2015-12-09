package com.wanderval.application.entidade;

/**
 * Created by monkey_001 on 25/10/15.
 */
public class Profissional {

    private long _id;
    private String nomeEmpresa;
    private String cargo;
    private String dataEntrada;
    private String dataSaida;

    public Profissional(long id, String nomeEmpresa, String cargo, String dataEntrada, String dataSaida){
        this._id            = id;
        this.nomeEmpresa    = nomeEmpresa;
        this.cargo          = cargo;
        this.dataEntrada    = dataEntrada;
        this.dataSaida      = dataSaida;
    }

    //getter/setter
    public long getId(){
        return this._id;
    }
    public void setId(long id){
        this._id = id;
    }
    public String getNomeEmpresa(){
        return this.nomeEmpresa;
    }
    public void setNomeEmpresa(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
    }
    public String getCargo(){
        return this.cargo;
    }
    public void setCargo(String cargo){
        this.cargo = cargo;
    }
    public String getDataEntrada(){
        return this.dataEntrada;
    }
    public void setDataEntrada(String dataEntrada){
        this.dataEntrada = dataEntrada;
    }
    public String getDataSaida(){
        return this.dataSaida;
    }
    public void setDataSaida(String dataSaida){
        this.dataSaida = dataSaida;
    }

    //method
    public String getSimpleName() {
        return "Profissional";
    }
}
