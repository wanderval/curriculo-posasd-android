package com.wanderval.application.database;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.wanderval.application.entidade.Aluno;
import com.wanderval.application.entidade.Perfil;
import com.wanderval.application.entidade.Profissional;
import com.wanderval.application.puc_curriculo_sqlite.Contacto;


public class DBAdapter {
	private SQLiteDatabase database;
	private com.wanderval.application.database.DbHelper dbHelper;
	private String[] allColumns = { DbHelper.ID, DbHelper.NOME, DbHelper.EMAIL, DbHelper.TELEFONE, DbHelper.FOTO};
	private String[] allColumns_academic = { DbHelper.ID_A, DbHelper.TIPO_A, DbHelper.CURSO_A, DbHelper.INSTITUICAO_A, DbHelper.DATA_A};
	private String[] allColumns_empresa = { DbHelper.ID_E, DbHelper.NOME_E, DbHelper.CARGO_E, DbHelper.DATA_ENTRADA_E, DbHelper.DATA_SAIDA_E};
	private String[] allColumns_perfil = { DbHelper.ID_P, DbHelper.NOME_P, DbHelper.EMAIL_P, DbHelper.TELEFONE_P, DbHelper.GRADUADO_P, DbHelper.HABILIDADE_P};

	public DBAdapter(Context context) {
		dbHelper = new DbHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}


	//METHOD CONTATO
	public Contacto createContacto(String nome, String email, String telefone, Bitmap foto) {
		ContentValues values = new ContentValues();
		values.put(dbHelper.NOME, nome);
		values.put(dbHelper.EMAIL,email);
		values.put(dbHelper.TELEFONE,telefone);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		foto.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] photo = baos.toByteArray();

		values.put(dbHelper.FOTO, photo);

		long insertId = database.insert(dbHelper.TABLE_NAME, null, values);
		// To show how to query
		Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + insertId, null,null, null, null);
		cursor.moveToFirst();
		return cursorToContacto(cursor);
	}

	public void EliminaContacto (int idContacto){
		//database.delete(DB.TABLE_NAME, "id=?", new String [] {Integer.toString(idContacto)});
		database.delete(DbHelper.TABLE_NAME, DbHelper.ID + " = " + idContacto, null);
		}
	private Contacto cursorToContacto(Cursor cursor) {
		byte[] blob = cursor.getBlob(cursor.getColumnIndex(dbHelper.FOTO));
	      	Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
	Contacto contacto = new Contacto(cursor.getLong(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),bmp);
		return contacto;
	}

	public Cursor getContactos(){
		Cursor cursor = database.rawQuery("select _id, nome,telefone,foto from contactos2", null);
			return cursor;
		}
	public Contacto getContacto (int idContacto){
		Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID + " = " + idContacto, null,null, null, null);
			cursor.moveToFirst();
		return cursorToContacto(cursor);
		}

	//METHOD ALUNO

	public Aluno createAluno(String tipo, String curso, String instituicao, String data) {
		ContentValues values = new ContentValues();
		values.put(dbHelper.TIPO_A, tipo);
		values.put(dbHelper.CURSO_A, curso);
		values.put(dbHelper.INSTITUICAO_A, instituicao);
		values.put(dbHelper.DATA_A,data);


		long insertId = database.insert(dbHelper.TABLE_ACADEMIA, null, values);
		// To show how to query
		Cursor cursor = database.query(dbHelper.TABLE_ACADEMIA, allColumns_academic, dbHelper.ID_A + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		return cursorToCurso(cursor);//cursorToContacto(cursor);
	}

	private Aluno cursorToCurso(Cursor cursor) {

		Aluno aluno = new Aluno(cursor.getLong(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4));
		return aluno;
	}
	public Cursor getCursos(){
		Cursor cursor = database.rawQuery("select _id, tipo, curso, instituicao, data from academia", null);
		return cursor;
	}
	public Aluno getCursoTo (int idCurso){
		Cursor cursor = database.query(dbHelper.TABLE_ACADEMIA, allColumns_academic, dbHelper.ID_A + " = " + idCurso, null, null, null, null);
		cursor.moveToFirst();
		return cursorToCurso(cursor);
	}

	public void EliminaCurso (int idCurso){
		//database.delete(DB.TABLE_NAME, "id=?", new String [] {Integer.toString(idContacto)});
		database.delete(DbHelper.TABLE_ACADEMIA, DbHelper.ID_A + " = " + idCurso, null);
	}
	public void AtualizarCurso (String id, String tipo, String curso, String instituicao, String data){
		ContentValues values = new ContentValues();

		values.put(dbHelper.ID_A, id);
		values.put(dbHelper.TIPO_A, tipo);
		values.put(dbHelper.CURSO_A, curso);
		values.put(dbHelper.INSTITUICAO_A, instituicao);
		values.put(dbHelper.DATA_A,data);

		database.update(DbHelper.TABLE_ACADEMIA, values,"_id = ?", new String[]{id});
	}


	//METHOD EMPRESA

	public Profissional createEmpresa(String nome, String cargo, String dataEntrada, String dataSaida) {
		ContentValues values = new ContentValues();
		values.put(dbHelper.NOME_E, nome);
		values.put(dbHelper.CARGO_E, cargo);
		values.put(dbHelper.DATA_ENTRADA_E, dataEntrada);
		values.put(dbHelper.DATA_SAIDA_E,dataSaida);


		long insertId = database.insert(dbHelper.TABLE_EMPRESA, null, values);
		// To show how to query
		Cursor cursor = database.query(dbHelper.TABLE_EMPRESA, allColumns_empresa, dbHelper.ID_E + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		return cursorToEmpresa(cursor);//cursorToContacto(cursor);
	}

	private Profissional cursorToEmpresa(Cursor cursor) {

		Profissional profissional = new Profissional(cursor.getLong(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4));
		return profissional;
	}
	public Cursor getEmpresas(){
		Cursor cursor = database.rawQuery("select _id, nome, cargo, data_entrada, data_saida from empresa", null);
		return cursor;
	}
	public Profissional getEmpresaTo (int idEmpresa){
		Cursor cursor = database.query(dbHelper.TABLE_EMPRESA, allColumns_empresa, dbHelper.ID_E + " = " + idEmpresa, null, null, null, null);
		cursor.moveToFirst();
		return cursorToEmpresa(cursor);
	}

	public void EliminaEmpresa (int idEmpresa){
		//database.delete(DB.TABLE_NAME, "id=?", new String [] {Integer.toString(idContacto)});
		database.delete(DbHelper.TABLE_EMPRESA, DbHelper.ID_E + " = " + idEmpresa, null);
	}

	public void AtualizarEmpresa (String id, String nome, String cargo, String dataEntrada, String dataSaida){
		ContentValues values = new ContentValues();

		values.put(dbHelper.ID_E, id);
		values.put(dbHelper.NOME_E, nome);
		values.put(dbHelper.CARGO_E, cargo);
		values.put(dbHelper.DATA_ENTRADA_E, dataEntrada);
		values.put(dbHelper.DATA_SAIDA_E,dataSaida);

		database.update(DbHelper.TABLE_EMPRESA, values, "_id = ?", new String[]{id});
	}

	//METHOD PERFIL

	public Perfil createPerfil(String nome, String email, String telefone) {
		ContentValues values = new ContentValues();
		values.put(dbHelper.NOME_P, nome);
		values.put(dbHelper.EMAIL_P, email);
		values.put(dbHelper.TELEFONE_P, telefone);



		long insertId = database.insert(dbHelper.TABLE_PERFIL, null, values);
		// To show how to query
		Cursor cursor = database.query(dbHelper.TABLE_PERFIL, allColumns_perfil, dbHelper.ID_P + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		return cursorToPerfil(cursor);//cursorToContacto(cursor);
	}

	private Perfil cursorToPerfil(Cursor cursor) {

		Perfil perfil = new Perfil(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
		return perfil;
	}

	public Cursor getPerfil(){
		Cursor cursor = database.rawQuery("select _id, nome, email, telefone, graduado, habilidade from perfil", null);
		return cursor;
	}

	public Perfil getPerfilTo(int idPerfil){
		//Cursor cursor = database.query(dbHelper.TABLE_PERFIL, allColumns_perfil, dbHelper.ID_P + " = " + idPerfil, null, null, null, null);
		Cursor cursor = database.rawQuery("select _id, nome, email, telefone, habilidade from perfil where _id = '1'",null);
		cursor.moveToFirst();
		return cursorToPerfil(cursor);
	}

	public void AtualizarPerfil (String id, String nome, String email, String telefone, String habilidade){
		ContentValues values = new ContentValues();

		values.put(dbHelper.ID_P, id);
		values.put(dbHelper.NOME_P, nome);
		values.put(dbHelper.EMAIL_P, email);
		values.put(dbHelper.TELEFONE_P, telefone);
		values.put(dbHelper.HABILIDADE_P,habilidade);

		database.update(DbHelper.TABLE_PERFIL, values, "_id = ?", new String[]{id});
	}
}
