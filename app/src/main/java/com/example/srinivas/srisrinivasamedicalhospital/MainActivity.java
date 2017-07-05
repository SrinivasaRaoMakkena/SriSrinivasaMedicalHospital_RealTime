package com.example.srinivas.srisrinivasamedicalhospital;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srinivas.srisrinivasamedicalhospital.database.Constants;
import com.example.srinivas.srisrinivasamedicalhospital.database.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.passWord)
    TextView password;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.signUp)
    Button signUp;


    @BindView(R.id.forgetPassword)
    Button forgotPWD;

    String EMail,passWord;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        dbHelper = new DBHelper(this);
        db=dbHelper.getWritableDatabase();


//retriveData();

    }

    @OnClick(R.id.signUp)
    public void signUp() {
        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.login)
    public void login() {

        EMail = email.getText().toString();
        passWord = password.getText().toString();

       System.out.println( checkUser(EMail));

        if(EMail.isEmpty()){
            email.setError("Please enter username!");
        }else if(passWord.isEmpty()){
            password.setError("Please enter password!");
        }else {
            checkUser(EMail);
        }

    }


    public boolean checkUser(String userName) {
        db=dbHelper.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {
                Constants.COLUMN_PASSWORD
        };


        // selection criteria
        String selection = Constants.COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {EMail};


        Cursor cursor = db.query(Constants.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
       Toast.makeText(getApplicationContext(), "" + cursorCount, Toast.LENGTH_SHORT).show();

       // Toast.makeText(getApplicationContext(),cursorCount+"",Toast.LENGTH_SHORT).show();

if (cursor.moveToFirst()) {
    if (cursor.getCount() == 1 && cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PASSWORD)).equals(passWord)) {
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
        return true;
    }
}

        cursor.close();
        db.close();

        Snackbar.make(findViewById(R.id.relativeLayout), "Please check your credentials", Snackbar.LENGTH_LONG).show();
        return false;



    }




    public void dropTable(View view) {
        db=dbHelper.getWritableDatabase();
        //db.execSQL("DELETE FROM "+Constants.TABLE_NAME);
      //db.execSQL(  "DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
//        Log.i(Constants.TABLE_NAME,"Dropped");

retriveData();


    }



    public void retriveData(){
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_NAME;

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
              //  Log.i(Constants.TABLE_NAME,""+cursor.getCount());
               //Toast.makeText(getApplicationContext(),cursor.getString(cursor.getColumnIndex(Constants.COLUMN_EMAIL))+"",Toast.LENGTH_SHORT).show();
                //Log.i(Constants.TABLE_NAME,""+cursor.getColumnName(cursor.getPosition()));
            } while (cursor.moveToNext());
        }

        Log.i(Constants.TABLE_NAME,""+cursor.getCount());

cursor.close();
    }


    //public void

}
