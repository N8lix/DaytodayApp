package com.example.daytodayapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Struct;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TASK.DB";
    private static final String TABLE_NAME = "TASK";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "POINT";
    private static final String COL_4 = "DONE";
    private static final String COL_5 = "WHOSE";
    private static final String COL_6 = "TIMESTAMP";

    private static final String TABLE_NAME2 = "PERSON";
    private static final String COL_11 = "ID";
    private static final String COL_22 = "NAME";
    private static final String COL_33 = "POINTS";
    private static final String COL_44 = "DETL";
    private static final String COL_55 = "TIMESTAMP";

    private static final String CREATE_TABLE_TASK = "CREATE TABLE "
            + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY," + COL_2
            + " TEXT," + COL_3 + " INTEGER,"+ COL_4 + " INTEGER,"+ COL_5 + " INTEGER," + COL_6
            + " DATETIME" + ")";

    private static final String CREATE_TABLE_PERSON = "CREATE TABLE "
            + TABLE_NAME2 + "(" + COL_11 + " INTEGER PRIMARY KEY," + COL_22
            + " TEXT, " + COL_33 + " INTEGER," + COL_44 + " TEXT," + COL_55
            + " DATETIME" + ")";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
      //   SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("CREATE TABLE " + TABLE_NAME + "("+COL_1+")");

       //db.execSQL("CREATE TABLE "+ TABLE_NAME+" (NAME TEXT, POINT INTEGER, DONE INTEGER, TIMESTAMP default current_timestamp)");

        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        db.execSQL("DROP TABLE " + TABLE_NAME2);

        onCreate(db);
    }

    public boolean insertData (String name, int point,String TABLE_NAME, int whose){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        contentValues.put(COL_2,name);
        contentValues.put(COL_3,point);
        contentValues.put(COL_4,0);
        contentValues.put(COL_5,whose);
        contentValues.put(COL_6,sqlDate.toString());
        long result = db.insert(TABLE_NAME, null,contentValues);



        if (result == -1) return false;
        else return true;

    }


   public void eraserhead(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);

    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * from "+ TABLE_NAME +" WHERE "+COL_4+" = 0 ";
        Cursor res = db.rawQuery(query , null);

        Log.d("masuk", "masyj getAlldata");

        if (res.moveToFirst()) {
            Log.d("masuk  ", "getAllData:  "+DatabaseUtils.dumpCursorToString(res));
        }

        //Log.d("error", DatabaseUtils.dumpCursorToString(res));
        return res;
    }
//
//    public Cursor reset(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("UPDATE "+ TABLE_NAME + " SET DONE = \"ready \" WHERE DONE = null ", null);
//        return res;
//    }




    public void adddatabase(){


        int x =0;
        while(x<3){
        insertData("Bagun Pagi",5,"TASK", x);
        insertData("Get Dressed",5,"TASK", x);
        insertData("Breakfast",5,"TASK", x);
        insertData("Go to scool and write diary",5,"TASK", x);
        insertData("Homework",5,"TASK", x);
        insertData("Study",10,"TASK", x);
        insertData("Dinner",5,"TASK", x);
        insertData("Go to bed",10,"TASK", x);
        x++;}

        Log.d("masuk","adddatabase");



    }


    public void done(String pos, int point ){
        int done = 1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,pos);
        contentValues.put(COL_3,point);
        contentValues.put(COL_4,done);
        db.update(TABLE_NAME,contentValues,"NAME = ?", new String[]{pos});
//        return true;

    }


}
