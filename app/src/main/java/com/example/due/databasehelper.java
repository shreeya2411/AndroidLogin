package com.example.due;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;


public class databasehelper extends SQLiteOpenHelper {
    public static final String database_name = "due";
    public static final String table_name = "users";
    public static final String col_1 = "u_id";
    public static final String col_2 = "u_name";
    public static final String col_3 = "u_pass";
    public static final String col_4 = "u_fine";
    public  SQLiteDatabase db;
    //static final String DATABASE_CREATE = "create table " + "LOGIN" + "( " + "ID" + " integer primary key autoincrement," + "USERNAME text,PASSWORD text); ";

    public databasehelper(@Nullable Context context)
    {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (u_id integer primary key autoincrement," + "u_name text," + "u_pass text," + "u_fine integer);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + table_name);
        onCreate(db);

    }
    public void addusers(SQLiteDatabase db,String name,String pass)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,pass);
        contentValues.put(col_4,"0");
        db.insert("users",null,contentValues);

    }

    public static int  validatelogin(SQLiteDatabase db, String name, String pass)
    {
        //Cursor cursor = db.query("users",new String[]{"u_name","u_password"},"u_name = ? and u_pass = ?",null,null,null,null);
        Cursor cursor = db.rawQuery("SELECT *FROM " + table_name + " WHERE " + col_2 + "=? AND " + col_3 + "=?", new String[]{name,pass});
        if (cursor != null)
        {
            int count = cursor.getCount();
            cursor.close();
            db.close();
            if(count >0)
                return count;
        }
        return 10;
    }


}

