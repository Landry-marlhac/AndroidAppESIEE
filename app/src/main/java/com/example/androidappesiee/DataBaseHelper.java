package com.example.androidappesiee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DatabaseTache.db";
    private static final int DATBASE_VERSION = 1;
    private static final String TACHES_TABLE = "TACHES_TABLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TACHE_NAME = "TACHE_NAME";
    private static final String COLUMN_TACHE_DESCRIPTION = "TACHE_DESCRIPTION";
    private static final String COLUMN_TACHE_TYPEDETACHE = "TACHE_TYPEDETACHE";
    private static final String COLUMN_TACHE_PRIORITE = "TACHE_PRIORITE";
    private static final String COLUMN_TACHE_EFFECTUEE = "TACHE_EFFECTUEE";

    private Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + TACHES_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TACHE_NAME + " TEXT, " + COLUMN_TACHE_DESCRIPTION + " TEXT, " + COLUMN_TACHE_TYPEDETACHE + " TEXT, " + COLUMN_TACHE_PRIORITE + " INTEGER, " + COLUMN_TACHE_EFFECTUEE + " BOOL)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TACHES_TABLE);
        onCreate(db);
    }

    public boolean addTask(String nomtache, String description, String typedetache, int priorite) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean iseffectuee = new Boolean(false);

        cv.put(COLUMN_TACHE_NAME, nomtache);
        cv.put(COLUMN_TACHE_DESCRIPTION, description);
        cv.put(COLUMN_TACHE_TYPEDETACHE, typedetache);
        cv.put(COLUMN_TACHE_PRIORITE, priorite);
        cv.put(COLUMN_TACHE_EFFECTUEE, iseffectuee);

        long insert = db.insert(TACHES_TABLE, null, cv);

        if (insert == -1) {
            Toast.makeText(context, "Failed to add task", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "succeed to add task", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TACHES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Boolean deleteOne(ToDoTacheModel tacheModel) {
        //Find customer model in the database. if in database then remove it and return true
        //if not find, return false
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TACHES_TABLE + " WHERE " + COLUMN_ID + " = " + tacheModel.getID();


        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}
