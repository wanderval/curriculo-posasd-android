package com.wanderval.application.activitys;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wanderval.application.R;
import com.wanderval.application.database.DBAdapter;
import com.wanderval.application.entidade.Aluno;
import com.wanderval.application.entidade.Perfil;


public class PerfilActivity extends AppCompatActivity {

    int idPerfil;
    DBAdapter datasource;
    Perfil perfil;
    TextView edtNome;
    TextView edtEmail;
    TextView edtTelefone;
    TextView edtGraduado;
    TextView edtHabilidade;
    Button btEliminar;
    Button btAtualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTitle("Perfil");
        setContentView(R.layout.detalheperfil);



        edtHabilidade   = (EditText) findViewById(R.id.detalheperfil_habilidade);
        edtNome         = (EditText) findViewById(R.id.detalheperfil_nome);
        edtEmail        = (EditText) findViewById(R.id.detalheperfil_email);
        edtTelefone     = (EditText) findViewById(R.id.detalheperfil_telefone);
        btEliminar      =  (Button) findViewById(R.id.btEliminar);
        btAtualizar     =  (Button) findViewById(R.id.btAtualizar);

        carregaDetalhesPerfil();

        /*btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });*/
        btEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder dialogo = new AlertDialog.Builder(PerfilActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Eliminar item?");
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                dialogo.setPositiveButton("Eliminar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        datasource.open();
                        datasource.EliminaCurso(idPerfil);
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
                AlertDialog.Builder dialogo = new AlertDialog.Builder(PerfilActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Salvar alterações?");
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                dialogo.setPositiveButton("Atualizar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setDadosPerfil();
                        datasource.open();
                        datasource.AtualizarPerfil(String.valueOf(idPerfil),
                                edtNome.getText().toString(),
                                edtEmail.getText().toString(),
                                edtTelefone.getText().toString(),
                                edtHabilidade.getText().toString());
                        datasource.close();


                        finish();
                    }
                });
                dialogo.show();
            }
        });

    }

    private void setDadosPerfil(){
        String nome         = edtNome.getText().toString();
        String email        = edtEmail.getText().toString();
        String telefone     = edtTelefone.getText().toString();
        String habilidade   = edtHabilidade.getText().toString();

        /*SharedPreferences settings = getSharedPreferences("PREFS_NOME_PERFIL", 0);
        settings.getString("pref_perfil_nome", nome);
        settings.getString("pref_perfil_email", email);*/

        /*SharedPreferences mypref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mypref.edit();*/

        SharedPreferences settings = getSharedPreferences("PREFS_NOME_PERFIL", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("pref_perfil_nome", nome);
        editor.putString("pref_perfil_email", email);
        editor.putString("pref_perfil_habilidade", habilidade);
        //settings.getString("pref_perfil_graduado", "");

        editor.commit();

        /*SharedPreferences settings = getSharedPreferences("PREFS_NOME_PERFIL", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("pref_perfil_nome", nome);
        editor.putString("pref_perfil_email", email);
        //editor.putString("pref_perfil_telefone", telefone);
        editor.putString("pref_perfil_habilidade", habilidade);
        editor.commit();*/
    }
    private void carregaDetalhesPerfil(){

        datasource = new DBAdapter(this);
        datasource.open();

        Cursor cursor = datasource.getPerfil();

        cursor.moveToFirst();
        if(cursor != null){

            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String habilidade = cursor.getString(cursor.getColumnIndex("habilidade"));

            perfil = new Perfil(nome, email, telefone, habilidade);

            //Toast.makeText(this, "deu certo:"+nome+" email:"+email+" telefone:"+telefone+"", Toast.LENGTH_LONG).show();


        }else{
            Toast.makeText(this, "FALHOU", Toast.LENGTH_LONG).show();
        }

        datasource.close();



        edtHabilidade.setText(perfil.getHabilidade());
        edtNome.setText(perfil.getNome());
        edtEmail.setText(perfil.getEmail());
        edtTelefone.setText(perfil.getTelefone());
    }



}
