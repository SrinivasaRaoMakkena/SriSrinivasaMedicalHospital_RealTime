package com.example.srinivas.srisrinivasamedicalhospital.alerts;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Srinivas on 7/4/2017.
 */

 public final  class LandT {

    public static void toast(String msg,Context context){

        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

   public  static  void log(String msg){
        Log.i("Srinivas",msg);

    }

    public static void snackBarDialog(View view, String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }

}
