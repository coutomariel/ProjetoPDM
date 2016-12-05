package com.coutomariel.accesscontrol.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coutomariel.accesscontrol.util.Constantes;

/**
 * Created by Mariel on 02/12/2016.
 */

public class PeopleRepository extends SQLiteOpenHelper {


    public PeopleRepository(Context context) {
        super(context, Constantes.DB_NOME, null, Constantes.DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        StringBuilder query = new StringBuilder();
//        query.append("CREATE TABLE TB_PEOPLE( ");
//        query.append("ID_PEOPLE INT PRIMARY KEY AUTOINCREMENT, ");
//        query.append("NOME TEXT(25)NOT NULL");
//        query.append(");");
//
//        db.execSQL(query.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
