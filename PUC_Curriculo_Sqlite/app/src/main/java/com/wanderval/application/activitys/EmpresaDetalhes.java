package com.wanderval.application.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wanderval.application.R;
import com.wanderval.application.database.DBAdapter;
import com.wanderval.application.entidade.Aluno;
import com.wanderval.application.entidade.Profissional;


/**
 * Created by monkey_001 on 25/10/15.
 */
public class EmpresaDetalhes extends AppCompatActivity {

    int idEmpresa;
    DBAdapter datasource;
    Profissional profissional;
    TextView edtNome;
    TextView edtCargo;
    TextView edtDataEntrada;
    TextView edtDataSaida;
    Button btVoltar;
    Button btEliminar;
    Button btAtualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTitle("Detalhe");
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);*/
        setContentView(R.layout.detalheempresa);


        edtNome         = (EditText) findViewById(R.id.detalheempresa_nomeempresa);
        edtCargo        = (EditText) findViewById(R.id.detalheempresa_cargo);
        edtDataEntrada  = (EditText) findViewById(R.id.detalheempresa_dataentrada);
        edtDataSaida    = (EditText) findViewById(R.id.detalheempresa_datasaida);
        btEliminar      =  (Button) findViewById(R.id.btEliminar);
        btAtualizar     =  (Button) findViewById(R.id.btAtualizar);

        carregaDetalhesEmpresa();

        /*btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });*/
        btEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder dialogo = new AlertDialog.Builder(EmpresaDetalhes.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Eliminar item?");
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                dialogo.setPositiveButton("Eliminar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        datasource.open();
                        datasource.EliminaEmpresa(idEmpresa);
                        datasource.close();
                        finish();
                    }
                });
                dialogo.show();
            }
        });

        btAtualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder dialogo = new AlertDialog.Builder(EmpresaDetalhes.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Salvar alterações?");
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                dialogo.setPositiveButton("Atualizar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        datasource.open();
                        datasource.AtualizarEmpresa(String.valueOf(idEmpresa),
                                edtNome.getText().toString(),
                                edtCargo.getText().toString(),
                                edtDataEntrada.getText().toString(),
                                edtDataSaida.getText().toString());
                        datasource.close();
                        finish();
                    }
                });
                dialogo.show();
            }
        });

    }

    private void carregaDetalhesEmpresa(){
        idEmpresa = getIntent().getIntExtra("idEmpresa", 0);

        datasource = new DBAdapter(this);
        datasource.open();
        profissional = datasource.getEmpresaTo(idEmpresa);
        datasource.close();

        edtNome.setText(profissional.getNomeEmpresa());
        edtCargo.setText(profissional.getCargo());
        edtDataEntrada.setText(profissional.getDataEntrada());
        edtDataSaida.setText(profissional.getDataSaida());
    }

}
