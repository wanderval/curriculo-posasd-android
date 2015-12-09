package com.wanderval.application.activitys;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.wanderval.application.R;
import com.wanderval.application.database.DBAdapter;


public class EmpresaActivity extends ListActivity {

    ListAdapter adapter;
    DBAdapter datasource;
    Button btNovaEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("Profissional");
        setContentView(R.layout.content_professional);

        setSharedDataPerfil();
        makeListEmpregos();
    }

    private void makeListEmpregos(){

        datasource = new DBAdapter(this);
        datasource.open();
        Cursor cursor = datasource.getEmpresas();

        String[] columns = new String[] { "nome","cargo" };
        int[] to = new int[] { R.id.profissional_nome, R.id.profissional_cargo};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_profissional_item,
                cursor,
                columns,
                to);
        this.setListAdapter(adapter);
        datasource.close();

        btNovaEmpresa = (Button) findViewById(R.id.btNovaEmpresa);

        btNovaEmpresa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent novo = new Intent("com.wanderval.application.activitys.NovaEmpresaActivity");
                startActivity(novo);
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        datasource.open();
        Cursor cursor = datasource.getEmpresas();
        String[] columns = new String[] { "nome","cargo","data_entrada","data_saida" };
        int[] to = new int[] { R.id.profissional_nome, R.id.profissional_cargo, R.id.profissional_dataentrada, R.id.profissional_datasaida};
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_profissional_item,
                cursor,
                columns,
                to);
        this.setListAdapter(adapter);
        datasource.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent("com.wanderval.application.activitys.EmpresaDetalhes");
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("idEmpresa", cursor.getInt(cursor.getColumnIndex("_id")));
        startActivity(intent);
    }

    public void setSharedDataPerfil(){

        SharedPreferences settings = getSharedPreferences("PREFS_NOME_PERFIL", 0);
        String nome = settings.getString("pref_perfil_nome", "");
        String email = settings.getString("pref_perfil_email", "");
        String graduacao = settings.getString("pref_perfil_graduado", "");
        String habilidade = settings.getString("pref_perfil_habilidade", "");

        TextView tv_nome = (TextView)findViewById(R.id.perfil_nome);
        tv_nome.setText(nome);

        TextView tv_email = (TextView)findViewById(R.id.perfil_email);
        tv_email.setText(email);

        TextView tv_graduacao = (TextView)findViewById(R.id.perfil_graduacao);
        tv_graduacao.setText(graduacao);

        TextView tv_habilidade = (TextView)findViewById(R.id.professional_habilidade);
        tv_habilidade.setText(habilidade);

    }

}
