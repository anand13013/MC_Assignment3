package com.example.golukumar.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

/**
 * Created by Anand kumar on 9/30/2016.
 */
public class DatabaseProvider extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "NAME";
    public static final String COLUMN_3 = "ROLLNO";
    public static final String COLUMN_4 = "EMAIL";

    public DatabaseProvider(Context context) {
        super(context, DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ROLLNO TEXT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String rollno, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_2, name);
        cv.put(COLUMN_3, rollno);
        cv.put(COLUMN_4, email);
        long result =  db.insert(TABLE_NAME, null, cv);
        if(result == -1)
        {
            return false;
        }
        else{
            return true;
        }
    }

    public Integer deleteData(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{ id });
    }
    public boolean updateData(String id, String name, String rollno, String email)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_1, id);
        cv.put(COLUMN_2, name);
        cv.put(COLUMN_3, rollno);
        cv.put(COLUMN_4, email);
        db.update(TABLE_NAME, cv, "ID = ?", new String[]{ id });
        return true;
    }


    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME , null);
        return res;
    }
}
