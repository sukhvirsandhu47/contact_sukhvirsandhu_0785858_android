package com.example.contact_sukhvirsandhu_0785858_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME= "Contact.db";

    public DBHelper(Context context ) {
        super(context, "Contact.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(Fname TEXT,Lname TEXT, Email VARCHAR, Email1 Varchar, Number varchar primary key, Number1 varchar,Address varchar )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }
    public Boolean insertData(String fname, String lname, String email,String email1, String number,String number1, String add)
    {
        SQLiteDatabase MYDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Fname",fname);
        contentValues.put("Lname",lname);
        contentValues.put("Email",email);
        contentValues.put("Number",number);
        contentValues.put("Email1",email1);
        contentValues.put("Number1",number1);
        contentValues.put("Address", add);
        long result = MYDB.insert("users",null, contentValues);
        if(result==-1) return false;
        else return  true;
    }
    public Cursor viewData()
    {

        SQLiteDatabase MYDB= this.getReadableDatabase();
        String query = "Select * from users" ;
        Cursor cursor = MYDB.rawQuery(query,null);
        return cursor;

    }
    public int updateData(String fname,String email, String number) {

        SQLiteDatabase MYDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", email);
        contentValues.put("Number", number);
        String whereArgs[]={""+fname};
        int count= MYDB.update("user",contentValues,"Email=?",whereArgs);
       // Cursor cursor = MYDB.rawQuery("Select * from users where Fname=?", new String[]{fname});
        //if (cursor.getCount() > 0) {
          //  long result = MYDB.update("users", contentValues, "Email?", new String[]{""+email});
           // if (result == -1) return false;
            //else return true;
        //}
        //else
        //{
          //  return true;
        //}
        return count;
    }
    public Cursor deleteData(String fname, String number) {

        SQLiteDatabase MYDB = this.getWritableDatabase();;
        Cursor cursor = MYDB.rawQuery("Select * from users where Fname=?", new String[]{""+fname});
        if (cursor.getCount() > 0) {
            String query = "delete from user where Fname="+fname;
            Cursor cursor1= MYDB.rawQuery(query,null);
            return cursor1;
        }
        else
        {
            return null;
        }
    }

}
