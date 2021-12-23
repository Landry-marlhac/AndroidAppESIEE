package com.example.androidappesiee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Classe pour gerer la base de données en SQLite
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

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    //Création base de données
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + TACHES_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TACHE_NAME + " TEXT, " + COLUMN_TACHE_DESCRIPTION + " TEXT, " + COLUMN_TACHE_TYPEDETACHE + " TEXT, " + COLUMN_TACHE_PRIORITE + " INTEGER, " + COLUMN_TACHE_EFFECTUEE + " BOOL )";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TACHES_TABLE);
        onCreate(db);
    }

    //fonction pour ajouter une tache a la base de donnée
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
            return false;
        } else {
            return true;
        }
    }

    //Fonction pour lire les données
    Cursor readAllData() {
        String query = "SELECT * FROM " + TACHES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //mise a jour de la base de donnée
    void updateData(String tache_name, String tache_descr, String tache_type,
                    int tache_prio, Boolean tache_isdone, String row_id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TACHE_NAME, tache_name);
        cv.put(COLUMN_TACHE_DESCRIPTION, tache_descr);
        cv.put(COLUMN_TACHE_TYPEDETACHE, tache_type);
        cv.put(COLUMN_TACHE_PRIORITE, tache_prio);
        cv.put(COLUMN_TACHE_EFFECTUEE, tache_isdone);

        int result = db.update(TACHES_TABLE, cv,"id=?",new String[]{row_id});
        if (result == -1){
        }
    }

    //Supprimer une tache de la base de donnée
    public void deleteOne(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TACHES_TABLE, "id=?", new String[]{row_id});
        if(result == -1){
            //not good
        }else{
            //good
        }
    }

    //Supprimer l'entièretée des données
    public void deleteAllData(){
        /*How to use it ?
          DataBaseHelper db = new DataBaseHelper(YourActicity.this);
          db.deleteAllData();
         */

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TACHES_TABLE);
    }
}
