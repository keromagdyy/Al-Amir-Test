package com.example.alamirgrouptest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.alamirgrouptest.data.contract.*;

public class helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userDB.dp";
    private static final int DATABASE_VERSION = 1;
    Context context;

    public helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_USER_TABLE = "CREATE TABLE " +usertable.TABLE_NAME + " (" +
                usertable.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                usertable.COLUMN_USER_USERNAME + " TEXT UNIQUE NOT NULL, "+
                usertable.COLUMN_USER_PHONE + " TEXT UNIQUE NOT NULL, "+
                usertable.COLUMN_USER_GENDER+ " TEXT);";

        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + usertable.TABLE_NAME);
        onCreate(db);
    }

    public void addPerson(String name, String phone, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(usertable.COLUMN_USER_USERNAME,name);
        cv.put(usertable.COLUMN_USER_PHONE,phone);
        cv.put(usertable.COLUMN_USER_GENDER,gender);
        long result = db.insert(usertable.TABLE_NAME, null , cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor showData(){
        String query = "SELECT * FROM " + usertable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public void updateData(String raw_id, String name, String phone, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(usertable.COLUMN_USER_USERNAME,name);
        cv.put(usertable.COLUMN_USER_PHONE,phone);
        cv.put(usertable.COLUMN_USER_GENDER,gender);

        long result = db.update(usertable.TABLE_NAME,cv,usertable.COLUMN_USER_ID +"=?", new String[]{raw_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, raw_id, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(String raw_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(usertable.TABLE_NAME, usertable.COLUMN_USER_ID +"=?",new String[]{raw_id});
        if(result == -1){
            Toast.makeText(context, "Fialed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

}
