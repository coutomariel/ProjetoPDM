package com.coutomariel.accesscontrol.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.coutomariel.accesscontrol.model.Pessoa;
import com.coutomariel.accesscontrol.model.TipoPessoa;
import com.coutomariel.accesscontrol.util.Constantes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mariel on 02/12/2016.
 */

public class Pessoas extends SQLiteOpenHelper {


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
        query.append("PRESENTE INTEGER DEFAULT 0)");

        db.execSQL(query.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void salvarPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = getContentValuesPessoa(pessoa);

        db.insert("TB_PEOPLE", null, contentValues);
    }

    @NonNull
    private ContentValues getContentValuesPessoa(Pessoa pessoa) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("NOME", pessoa.getNome());
        contentValues.put("CPF", pessoa.getCpf());

        contentValues.put("SEXO", pessoa.getSexo().toString());
        contentValues.put("SETOR", pessoa.getSetor().ordinal());
        contentValues.put("TIPO_PESSOA", pessoa.getTipoPessoa().ordinal());

        contentValues.put("DT_ADMISSAO", pessoa.getDataAdmissao().getTime());


        return contentValues;
    }


    public List<Pessoa> listarPessoas() {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_PEOPLE", null, null, null, null, null, "NOME");

        while (cursor.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(cursor.getColumnIndex("ID_PEOPLE")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            pessoa.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));

            int time = cursor.getInt(cursor.getColumnIndex("DT_ADMISSAO"));
            Date dtAdmissao = new Date();
            dtAdmissao.setTime(time);
            pessoa.setDataAdmissao(dtAdmissao);

            lista.add(pessoa);

            
        }


        return lista;
    }

}
