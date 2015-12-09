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


/**
 * Created by monkey_001 on 25/10/15.
 */
public class AcademicDetalhes extends AppCompatActivity {

    int idCurso;
    DBAdapter datasource;
    Aluno aluno;
    Spinner edtTipo;
    TextView edtCurso;
    TextView edtInstituicao;
    TextView dataConclusao;
    Button btVoltar;
    Button btEliminar;
    Button btAtualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTitle("Detalhe");
        setContentView(R.layout.detalheacademia);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);*/



        edtTipo         = (Spinner) findViewById(R.id.detalhecurso_tipo_spinner);
        edtCurso        = (EditText) findViewById(R.id.detalhecurso_curso);
        edtInstituicao  = (EditText) findViewById(R.id.detalhecurso_instituicao);
        dataConclusao   = (EditText) findViewById(R.id.detalhecurso_dataconclusao);
        btEliminar      =  (Button) findViewById(R.id.btEliminar);
        btAtualizar     =  (Button) findViewById(R.id.btAtualizar);

        carregaDetalhesAcademicos();

        /*btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });*/
        btEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder dialogo = new AlertDialog.Builder(AcademicDetalhes.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Eliminar item?");
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                dialogo.setPositiveButton("Eliminar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        datasource.open();
                        datasource.EliminaCurso(idCurso);
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
                AlertDialog.Builder dialogo = new AlertDialog.Builder(AcademicDetalhes.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Salvar alterações?");
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                dialogo.setPositiveButton("Atualizar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        datasource.open();
                        datasource.AtualizarCurso(String.valueOf(idCurso),
                                                    edtTipo.getSelectedItem().toString(),
                                                    edtCurso.getText().toString(), edtInstituicao.getText().toString(),
                                                    dataConclusao.getText().toString());
                        datasource.close();
                        finish();
                    }
                });
                dialogo.show();
            }
        });

    }

    private void carregaDetalhesAcademicos(){
        idCurso = getIntent().getIntExtra("idCurso", 0);

        datasource = new DBAdapter(this);
        datasource.open();
        aluno = datasource.getCursoTo(idCurso);
        datasource.close();


        //edtTipo.setSelected();//Text(aluno.getTipo());
        edtTipo.setSelection(getIndex(edtTipo, aluno.getTipo()));
        edtCurso.setText(aluno.getCurso());
        edtInstituicao.setText(aluno.getInstituicao());
        dataConclusao.setText(aluno.getDataConclusao());
    }
    private int getIndex (Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
}
