package com.example.srinivas.srisrinivasamedicalhospital;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.srinivas.srisrinivasamedicalhospital.alerts.LandT;
import com.example.srinivas.srisrinivasamedicalhospital.alerts.SignUpValidations;
import com.example.srinivas.srisrinivasamedicalhospital.database.Constants;
import com.example.srinivas.srisrinivasamedicalhospital.database.DBHelper;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @NonNull
    @BindView(R.id.fullName)
    EditText fullName;

    @NonNull
    @BindView(R.id.uName)
    EditText userName;

    @NonNull
    @BindView(R.id.pWord)
    EditText password;

    @NonNull
    @BindView(R.id.CpassWord)
    EditText confirmPassword;

    @NonNull
    @BindView(R.id.eMail)
    EditText email;

    @NonNull
    @BindView(R.id.phoneNumber)
    EditText phoneNum;

    @NonNull
    @BindView(R.id.genderSpinner)
    Spinner gender;

    @NonNull
    @BindView(R.id.dateOfBirth)
    TextView DOB;
    @BindView(R.id.agreeCheckBox)
    CheckBox agreeBox;
    @BindView(R.id.registrationBTN)
    Button register;
    @BindView(R.id.signInReg)
    Button login;


    String fname, uname, pwd, cpwd, mail, phno, genderStr, dateOfBirthStr;
    int isCheckedBox;

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);


        //textViewsAsStrings();

        dbHelper = new DBHelper(this);

        db = dbHelper.getWritableDatabase();
        // db.execSQL();
        // yourEditText = (EditText) findViewById(R.id.yourEditTextID);
        DOB.setOnClickListener(this);
        // To show current date in the datepicker


//DOB.setOnTouchListener(new View.OnTouchListener() {
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        HideSoftKeyBoard.hideSoftKeyboard(SignUpActivity.this);
//        return true;
//    }
//});
        login.setOnClickListener(this);
        register.setOnClickListener(this);


    }

    public void textViewsAsStrings() {
        fname = fullName.getText().toString();
        uname = userName.getText().toString();
        pwd = password.getText().toString();
        cpwd = confirmPassword.getText().toString();
        mail = email.getText().toString();
        phno = phoneNum.getText().toString();
        genderStr = gender.getSelectedItem().toString();
        dateOfBirthStr = DOB.getText().toString();


    }

    public void insertValues() {

        textViewsAsStrings();

        if (agreeBox.isChecked()) {
            isCheckedBox = 1;
        } else {
            isCheckedBox = 0;
        }


//        if (fname.isEmpty()) {
//            fullName.setError("Please enter your full name");
//        } else if (uname.isEmpty()) {
//            userName.setError("Please enter your user name");
//        } else if (mail.isEmpty()) {
//            email.setError("Please enter your email");
//        } else if (phno.isEmpty()) {
//            phoneNum.setError("Please enter your mobile num");
//        } else if (pwd.isEmpty()) {
//            password.setError("Please enter your password");
//        } else if (dateOfBirthStr.isEmpty()) {
//            DOB.setError("Please enter your DOB");
//        } else {


        //full name validations
        if (fname.isEmpty()) {
            fullName.setError("Please enter your full name");
            return;
        } else if (!SignUpValidations.nameValidations(fname)) {
            fullName.setError("Only alphabets and spaces, start with alphabet");
            return;
        }
        //user name validations
        if (uname.isEmpty()) {
            userName.setError("Please enter your user name");
            return;

        } else if (!SignUpValidations.userNameValidations(uname)) {
            userName.setError("only alphabets&numbers, start with alphabet");
            return;
        }
        //email validations
        if (mail.isEmpty()) {
            email.setError("Please enter your email");
            return;
        } else if (!SignUpValidations.emailRestrictions(mail)) {
            email.setError("Email format is invalid");
            return;
        }


        //phone number validations
        if (phno.isEmpty()) {
            phoneNum.setError("Please enter your mobile num");
            return;
        } else if (!SignUpValidations.TenDigitPhoneNum(phno)) {
            phoneNum.setError("Please enter valid mobile no");
            return;
        }
        //password validations
        if (pwd.isEmpty()) {
            password.setError("Please enter your password");
            return;
        } else if (!SignUpValidations.isAcceptablePassword(pwd)) {
            password.setError("Please check below for password restrictions");
            Snackbar.make(findViewById(R.id.relativeSignUp), "8 <= length of password <= 20,\n At least one Capital(A), small(a),special(@), digit(1)", Snackbar.LENGTH_LONG).show();
            return;
        }
        //confirm password validations
        if (!pwd.equals(cpwd)) {
            confirmPassword.setError("Password doesn't match");
            return;
        }
        //DOB validations

        if (dateOfBirthStr.isEmpty()) {
            DOB.setError("Please enter your DOB");
            return;
        }

        Log.i(Constants.TABLE_NAME, "inserted");

        ContentValues values = new ContentValues();
        //   values.put(Constants.COLUMN_ID, BaseColumns._ID);
        values.put(Constants.COLUMN_NAME, fname);
        values.put(Constants.COLUMN_USERNAME, uname);
        values.put(Constants.COLUMN_PASSWORD, pwd);
        values.put(Constants.COLUMN_CONFIRMPASSWORD, cpwd);
        values.put(Constants.COLUMN_EMAIL, mail);
        values.put(Constants.COLUMN_PHONENUM, phno);
        values.put(Constants.COLUMN_GENDER, genderStr);
        values.put(Constants.COLUMN_DOB, dateOfBirthStr);
        values.put(Constants.COLUMN_ISAGREE, isCheckedBox);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Constants.TABLE_NAME, null, values);
        LandT.snackBarDialog(findViewById(R.id.relativeSignUp), "Registration Successfull!!!");

    }

    // }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registrationBTN:
                textViewsAsStrings();
                db = dbHelper.getWritableDatabase();
                if (uniqueEmail(mail)) {
                    insertValues();

                } else {
                    email.setError("Already registered with this email");
                }

                break;
            case R.id.signInReg:
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                break;
            case R.id.dateOfBirth:
                // HideSoftKeyBoard.hideSoftKeyboard(SignUpActivity.this);
                Calendar mcurrentDate = null;

                mcurrentDate = Calendar.getInstance();

                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "MM/dd/yy"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        DOB.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);

                mDatePicker.show();


                break;
        }
    }


    public boolean uniqueEmail(String gmail) {

        //dbHelper = new DBHelper();


        textViewsAsStrings();

        db = dbHelper.getReadableDatabase();
        // array of columns to fetch
        String[] columns = {
                Constants.COLUMN_EMAIL
        };
        // selection criteria
        String selection = Constants.COLUMN_EMAIL + " = ? ";

        // selection argument
        String[] selectionArgs = {gmail};


        Cursor cursor = db.query(Constants.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();


        LandT.log("" + cursorCount);

        if (cursor.moveToFirst()) {

            if (cursorCount > 0 && cursor.getString(cursor.getColumnIndex(Constants.COLUMN_EMAIL)).equals(gmail)) {
                LandT.log(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_EMAIL)));
                Snackbar.make(findViewById(R.id.relativeSignUp), "Email already registered", Snackbar.LENGTH_LONG).show();
                return false;
            }

        }


        cursor.close();


        return true;

    }


//    private  boolean validEmail(String gmail){
//        if (gmail.isEmpty()){
//           // email.setError("Please enter email");
//            return false;
//        }else if(!SignUpValidations.emailRestrictions(gmail)){
//            //email.setError("Email format is invalid");
//            return false;
//        }else{
//            return true;
//        }
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //cursor.close();
        db.close();
        dbHelper.close();
    }
}
