package com.wanderval.application.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.wanderval.application.R;
import com.wanderval.application.database.DBAdapter;
import com.wanderval.application.entidade.Aluno;
import com.wanderval.application.puc_curriculo_sqlite.Contacto;

public class NovoAcademicActivity extends AppCompatActivity {

    Button btadicionar;
    //EditText edtTipo;
    EditText edtCurso;
    EditText edtInstituicao;
    EditText dataConclusao;
    Spinner edtTipo;

    private DBAdapter datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cadastro");
        setContentView(R.layout.novoacademic);


        datasource      = new DBAdapter(this);
        edtTipo         = (Spinner) findViewById(R.id.novocurso_tipo_spinner);
        edtCurso        = (EditText) findViewById(R.id.novocurso_curso);
        edtInstituicao  = (EditText) findViewById(R.id.novocurso_instituicao);
        dataConclusao   = (EditText) findViewById(R.id.novocurso_dataconclusao);
        btadicionar     =  (Button) findViewById(R.id.novocurso_btadicionar);

        btadicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                datasource.open();
                Aluno c = datasource.createAluno(edtTipo.getSelectedItem().toString(), edtCurso.getText().toString(), edtInstituicao.getText().toString(), dataConclusao.getText().toString());
                datasource.close();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(NovoAcademicActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Curso:" + c.getCurso());
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
