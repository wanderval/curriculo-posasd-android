package com.wanderval.application.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.wanderval.application.database.DBAdapter;
import com.wanderval.application.puc_curriculo_sqlite.ContactosActivity;

import com.wanderval.application.R;

public class MainActivity extends AppCompatActivity {
    DBAdapter datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setTitle("Curriculo");
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        setSharedDataPerfil();


        //ACTIONS BUTTONS
        Button button = (Button) findViewById(R.id.btn_academic);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AcademicActivity.class);
                startActivity(intent);
            }
        });

        Button btn_professional = (Button) findViewById(R.id.btn_professional);
        btn_professional.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EmpresaActivity.class);
                startActivity(intent);
            }
        });
        Button btn_map = (Button) findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapActivity.class);
                startActivity(intent);
            }
        });

    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent novo = new Intent("com.wanderval.application.activitys.PerfilActivity");
            startActivity(novo);
        }

        return super.onOptionsItemSelected(item);
    }


    public void setSharedDataPerfil(){

        datasource = new DBAdapter(this);
        datasource.open();

        Cursor cursor = datasource.getPerfil();

        cursor.moveToFirst();
        if(cursor != null){

            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String graduacao = cursor.getString(cursor.getColumnIndex("graduado"));
            String habilidade = cursor.getString(cursor.getColumnIndex("habilidade"));

            SharedPreferences settings = getSharedPreferences("PREFS_NOME_PERFIL", 0);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString("pref_perfil_nome", nome);
            editor.putString("pref_perfil_email", email);
            editor.putString("pref_perfil_graduado", graduacao);
            editor.putString("pref_perfil_habilidade", habilidade);

            editor.commit();

            TextView tv_nome = (TextView)findViewById(R.id.perfil_nome);
            tv_nome.setText(nome);

            TextView tv_email = (TextView)findViewById(R.id.perfil_email);
            tv_email.setText(email);

            TextView tv_graduado = (TextView)findViewById(R.id.perfil_graduacao);
            tv_graduado.setText(graduacao);

        }else{

        }

        datasource.close();

    }


}

