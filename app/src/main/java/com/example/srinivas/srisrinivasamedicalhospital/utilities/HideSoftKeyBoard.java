package com.example.srinivas.srisrinivasamedicalhospital.utilities;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Srinivas on 7/4/2017.
 */

public class HideSoftKeyBoard {

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
