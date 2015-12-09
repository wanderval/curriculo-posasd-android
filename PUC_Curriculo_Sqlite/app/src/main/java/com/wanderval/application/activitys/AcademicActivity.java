package com.wanderval.application.activitys;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.wanderval.application.R;
import com.wanderval.application.database.DBAdapter;


public class AcademicActivity extends ListActivity {

    ListAdapter adapter;
    DBAdapter datasource;
    Button btNovoCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       setTitle("Academico");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //super.setActionBar(toolbar);//setSupportActionBar(toolbar);
        setContentView(R.layout.content_academic);

        setSharedDataPerfil();
        makeListCurso();

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void makeListCurso(){

        datasource = new DBAdapter(this);
        datasource.open();
        Cursor cursor = datasource.getCursos();

        String[] columns = new String[] { "tipo","instituicao" };
        int[] to = new int[] { R.id.academic_tipo, R.id.academic_instituicao};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_academic_item,
                cursor,
                columns,
                to);
        this.setListAdapter(adapter);
        datasource.close();

        btNovoCurso = (Button) findViewById(R.id.btNovoCurso);

        btNovoCurso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent novo = new Intent("com.wanderval.application.activitys.NovoAcademicActivity");
                startActivity(novo);
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        datasource.open();
        Cursor cursor = datasource.getCursos();
        String[] columns = new String[] { "tipo","curso","instituicao", "data" };
        int[] to = new int[] { R.id.academic_tipo, R.id.academic_curso, R.id.academic_instituicao, R.id.academic_data};
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_academic_item,
                cursor,
                columns,
                to);
        this.setListAdapter(adapter);
        datasource.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent("com.wanderval.application.activitys.AcademicDetalhes");
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("idCurso", cursor.getInt(cursor.getColumnIndex("_id")));
        startActivity(intent);
    }

    public void setSharedDataPerfil(){

        SharedPreferences settings = getSharedPreferences("PREFS_NOME_PERFIL", 0);
        String nome = settings.getString("pref_perfil_nome", "");
        String email = settings.getString("pref_perfil_email", "");
        String graduacao = settings.getString("pref_perfil_graduado", "");




        TextView tv_nome = (TextView)findViewById(R.id.perfil_nome);
        tv_nome.setText(nome);

        TextView tv_email = (TextView)findViewById(R.id.perfil_email);
        tv_email.setText(email);

        TextView tv_graduacao = (TextView)findViewById(R.id.perfil_graduacao);
        tv_graduacao.setText(graduacao);



    }

}
