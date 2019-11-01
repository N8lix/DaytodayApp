package com.example.daytodayapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TASK.DB";
    public static final String TABLE_NAME = "TASK";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "POINT";
    public static final String COL_3 = "DONE";
    public static final String COL_4 = "TIMESTAMP";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
         SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("CREATE TABLE " + TABLE_NAME + "("+COL_1+")");

       // db.execSQL("CREATE TABLE "+ TABLE_NAME+" (NAME TEXT, POINT INTEGER, DONE INTEGER, TIMESTAMP TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//    db.execSQL("DROP TABLE " + TABLE_NAME);
//    onCreate(db);
    }

    public boolean insertData (String name, int point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,point);
        contentValues.put(COL_3,0);
        long result = db.insert(TABLE_NAME, null,contentValues);

        if (result == -1) return false;
        else return true;

    }
//
//
//   public void eraserhead(){
 //       SQLiteDatabase db = this.getWritableDatabase();
  //      db.delete(TABLE_NAME, null, null);
//
//    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from "+ TABLE_NAME + " WHERE DONE = 0 ", null);

        return res;
    }
//
//    public Cursor reset(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("UPDATE "+ TABLE_NAME + " SET DONE = \"ready \" WHERE DONE = null ", null);
//        return res;
//    }

    public void adddatabase(){
        insertData("Bagun Pagi",5);
        insertData("Get Dressed",5);
        insertData("Breakfast",5);
        insertData("Go to scool and write diary",5);
        insertData("Homework",5);
        insertData("Study",10);
        insertData("Dinner",5);
        insertData("Go to bed",10);


    }

    public void done(String pos, int point ){
        int done = 1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,pos);
        contentValues.put(COL_2,point);
        contentValues.put(COL_3,done);
        db.update(TABLE_NAME,contentValues,"NAME = ?", new String[]{pos});
//        return true;

    }


}
