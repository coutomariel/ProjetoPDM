package com.coutomariel.accesscontrol.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.coutomariel.accesscontrol.model.Pessoa;
import com.coutomariel.accesscontrol.model.Setor;
import com.coutomariel.accesscontrol.model.Sexo;
import com.coutomariel.accesscontrol.model.TipoPessoa;
import com.coutomariel.accesscontrol.util.Constantes;
import com.coutomariel.accesscontrol.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mariel on 02/12/2016.
 */

public class Pessoas extends SQLiteOpenHelper {

    private String sql;

    public Pessoas(Context context) {
        super(context, Constantes.DB_NOME, null, Constantes.DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_PEOPLE( ");
        query.append("ID_PEOPLE INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append("NOME TEXT(25) NOT NULL,");
        query.append("CPF TEXT(25) NOT NULL,");
        query.append("SEXO TEXT(25) NOT NULL,");
        query.append("TIPO_PESSOA TEXT(25) NOT NULL,");
        query.append("SETOR TEXT(25) NOT NULL,");
        query.append("DT_ADMISSAO INTEGER NOT NULL,");
        query.append("PRESENTE INTEGER DEFAULT 0),");
        query.append("CAMINHO_FOTO TEXT");

        db.execSQL(query.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                sql = "ALTER TABLE TB_PEOPLE ADD COLUMN CAMINHO_FOTO TEXT";
                db.execSQL(sql);
        }
    }

    public void salvarPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = getContentValuesPessoa(pessoa);

        db.insert("TB_PEOPLE", null, contentValues);
    }

    public Pessoa buscarPorId(int id){
        Pessoa pessoa = new Pessoa();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_PEOPLE", null, "ID_PEOPLE = ?", new String[]{String.valueOf(id)}, null, null, "NOME");
        if(cursor.moveToNext()){
            setPessoaFromCursor(cursor, pessoa);
        }
        return pessoa;
    };

    public void removerPorId(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("TB_PEOPLE", "ID_PEOPLE = ?", new String[]{String.valueOf(id)});
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_PEOPLE", null, null, null, null, null, "NOME");

        while (cursor.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            setPessoaFromCursor(cursor, pessoa);
            lista.add(pessoa);
        }
        return lista;
    }

    @NonNull
    private ContentValues getContentValuesPessoa(Pessoa pessoa) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("NOME", pessoa.getNome());
        contentValues.put("CPF", pessoa.getCpf());

        contentValues.put("SEXO", pessoa.getSexo().toString());

        contentValues.put("SETOR", pessoa.getSetor().ordinal());
        contentValues.put("TIPO_PESSOA", pessoa.getTipoPessoa().ordinal());
        contentValues.put("CAMINHO_FOTO", pessoa.getCaminhoFoto().toString());

        contentValues.put("DT_ADMISSAO", pessoa.getDataAdmissao().getTime());


        return contentValues;
    }

    private void setPessoaFromCursor(Cursor cursor, Pessoa pessoa) {
        pessoa.setId(cursor.getInt(cursor.getColumnIndex("ID_PEOPLE")));
        pessoa.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
        pessoa.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));

        int sexo = cursor.getInt(cursor.getColumnIndex("SEXO"));
        pessoa.setSexo(Sexo.getSexo(sexo));

        int setor = cursor.getInt(cursor.getColumnIndex("SETOR"));
        pessoa.setSetor(Setor.getSetor(setor));

        int tipoPessoa = cursor.getInt(cursor.getColumnIndex("TIPO_PESSOA"));
        pessoa.setTipoPessoa(TipoPessoa.getTipoPessoa(tipoPessoa));

        pessoa.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("CAMINHO_FOTO")));

        long time = cursor.getLong(cursor.getColumnIndex("DT_ADMISSAO"));
        Date dtAdmissao = new Date();
        dtAdmissao.setTime(time);
        pessoa.setDataAdmissao(dtAdmissao);
    }

}
