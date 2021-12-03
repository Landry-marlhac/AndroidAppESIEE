package com.example.androidappesiee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TACHES_TABLE = "TACHES_TABLE";
    public static final String COLUMN_TACHE_ID = "TACHE_ID";
    public static final String COLUMN_TACHE_NOM = "TACHE_NOM";
    public static final String COLUMN_TACHE_DESCRIPTION = "TACHE_DESCRIPTION";
    public static final String COLUMN_TACHE_TYPEDETACHE = "TACHE_TYPEDETACHE";
    public static final String COLUMN_TACHE_DEADLINE = "TACHE_DEADLINE";
    public static final String COLUMN_TACHE_REPETITION = "TACHE_REPETITION" ;
    public static final String COLUMN_TACHE_PRIORITE = "TACHE_PRIORITE" ;
    public static final String COLUMN_TACHE_EFFECTUEE = "TACHE_EFFECTUEE" ;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "tachesToDo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE TACHES_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT,)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
