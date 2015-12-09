package com.wanderval.application.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.wanderval.application.R;
import com.wanderval.application.database.DBAdapter;
import com.wanderval.application.entidade.Aluno;
import com.wanderval.application.entidade.Profissional;

/**
 * Created by monkey_001 on 25/10/15.
 */
public class NovaEmpresaActivity extends AppCompatActivity {

    Button btadicionar;
    EditText edtNome;
    EditText edtCargo;
    EditText edtDataEntrada;
    EditText edtDataSaida;


    private DBAdapter datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cadastro");
        setContentView(R.layout.novaempresa);


        datasource      = new DBAdapter(this);
        edtNome         = (EditText) findViewById(R.id.novaempresa_nome);
        edtCargo        = (EditText) findViewById(R.id.novaempresa_cargo);
        edtDataEntrada  = (EditText) findViewById(R.id.novaempresa_dataentrada);
        edtDataSaida    = (EditText) findViewById(R.id.novaempresa_datasaida);
        btadicionar     =  (Button) findViewById(R.id.novaempresa_btadicionar);

        btadicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                datasource.open();
                Profissional p = datasource.createEmpresa(edtNome.getText().toString(), edtCargo.getText().toString(), edtDataEntrada.getText().toString(), edtDataSaida.getText().toString());
                datasource.close();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(NovaEmpresaActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Curso:" + p.getNomeEmpresa());
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
                dialogo.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
