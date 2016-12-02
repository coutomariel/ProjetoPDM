package com.coutomariel.accesscontrol.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coutomariel.accesscontrol.util.Constantes;

/**
 * Created by Mariel on 02/12/2016.
 */

public class PeopleRepository extends SQLiteOpenHelper {


    public PeopleRepository(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constantes.DB_NOME, factory, Constantes.DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
