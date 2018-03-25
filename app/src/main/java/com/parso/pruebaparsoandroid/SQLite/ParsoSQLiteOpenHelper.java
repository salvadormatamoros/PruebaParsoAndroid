package com.parso.pruebaparsoandroid.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luis.Matamoros on 23/03/2018.
 */

public class ParsoSQLiteOpenHelper extends SQLiteOpenHelper {
    public ParsoSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usersTbl(name text, email text, picture blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {
        db.execSQL("drop table if exists usersTbl");
        db.execSQL("create table usersTbl(name text, email text, picture blob)");
    }
}