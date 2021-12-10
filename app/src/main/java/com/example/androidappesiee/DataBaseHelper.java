package com.example.androidappesiee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String TACHES_TABLE = "TACHES_TABLE";
    public static final String COLUMN_TACHE_NAME = "TACHE_NAME";
    public static final String COLUMN_TACHE_DESCRIPTION = "TACHE_DESCRIPTION";
    public static final String COLUMN_TACHE_TYPEDETACHE = "TACHE_TYPEDETACHE";
    public static final String COLUMN_TACHE_PRIORITE = "TACHE_PRIORITE";
    public static final String COLUMN_TACHE_EFFECTUEE = "TACHE_EFFECTUEE";
    public static final String COLUMN_ID = "ID";
    private Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "tachesToDo.db", null, 1);
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

    public boolean addOne (String nomtache, String description, String typedetache, int priorite){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        boolean iseffectuee = new Boolean(false);

        cv.put(COLUMN_TACHE_NAME, nomtache);
        cv.put(COLUMN_TACHE_DESCRIPTION, description);
        cv.put(COLUMN_TACHE_TYPEDETACHE, typedetache);
        cv.put(COLUMN_TACHE_PRIORITE, priorite);
        cv.put(COLUMN_TACHE_EFFECTUEE, iseffectuee);

        long insert = db.insert(TACHES_TABLE, null, cv);

        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean deleteOne (ToDoTacheModel tacheModel){
        //Find customer model in the database. if in database then remove it and return true
        //if not find, return false
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+TACHES_TABLE+ " WHERE "+COLUMN_ID+ " = "+tacheModel.getID();


        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public List<ToDoTacheModel> getEveryone(){

        List<ToDoTacheModel> returnList = new ArrayList<>();
        //Get data from database

        String queryString = "SELECT * FROM "+TACHES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //Loop throught the cursor (result set) and create new customer objevt. put them in return list
            do{
                int tacheID = cursor.getInt(0);
                String tacheName = cursor.getString(1);
                String tacheDescription = cursor.getString(2);
                String typeDeTache = cursor.getString(3);
                int tachePriorite = cursor.getInt(4);
                boolean tacheEffectuee = cursor.getInt(5) == 1 ? true : false ;

                ToDoTacheModel newTache = new ToDoTacheModel(tacheID,tacheName,tacheDescription,typeDeTache,tachePriorite,tacheEffectuee);
                returnList.add(newTache);
            }while(cursor.moveToNext());

        }else{
            //Failure, do not add anything to list
        }

        //Close cursor and database
        cursor.close();
        db.close();

        return returnList;

    }













}
