package com.example.srinivas.srisrinivasamedicalhospital.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Srinivas on 7/1/2017.
 */

public class DBHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Constants.TABLE_NAME;

    private static final String DATABASE_CREATE = "create table "
            + Constants.TABLE_NAME + "( "
          //  + Constants.COLUMN_ID + " integer primary key autoincrement, "
            + Constants.COLUMN_NAME + " text , "
            + Constants.COLUMN_USERNAME + " text , "
            + Constants.COLUMN_PASSWORD + " text, "
            + Constants.COLUMN_CONFIRMPASSWORD + " text , "
            + Constants.COLUMN_EMAIL + " text primary key, "
            + Constants.COLUMN_PHONENUM + " text , "
            + Constants.COLUMN_ISAGREE + " integer, "
            + Constants.COLUMN_GENDER + " text,"
            + Constants.COLUMN_DOB  + " text );";

    public DBHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

        Log.i(Constants.DATABASE_NAME, " Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
