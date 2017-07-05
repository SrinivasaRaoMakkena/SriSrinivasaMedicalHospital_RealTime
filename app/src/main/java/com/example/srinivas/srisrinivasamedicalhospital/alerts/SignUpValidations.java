package com.example.srinivas.srisrinivasamedicalhospital.alerts;

import android.content.Context;



import java.util.regex.*;


/**
 * Created by Srinivas on 7/4/2017.
 */

public final class SignUpValidations {

    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 20;



Context c;
    private static  int countUpper;
    private static  int countLower;
    private static  int countSpecialChar;
    private static  int countDigit;

    public static boolean nameValidations(String name){

        String regex = "^[A-Za-z][^@#$%^&!,./';0-9]{3,20}"; //only alphabets and spaces , should start with alphabet
        //if(name.contains())

       // Matcher matcher = regex .matcher(name);
        return Pattern.matches(regex,name);

    }
    public static boolean userNameValidations(String name){
        String regex = "^[A-Za-z][^@#$%^&!,./'; ]{3,20}"; //only alphabets and numbers , should start with alphabet
        //if(name.contains())

        // Matcher matcher = regex .matcher(name);
        return Pattern.matches(regex,name);

    }


    public static boolean TenDigitPhoneNum(String mobileNum){

        Pattern regex = Pattern.compile("^[2-9]\\d{2}\\d{3}\\d{4}$");
        Matcher matcher = regex .matcher(mobileNum);

        return matcher.find();

    }


        static  boolean success;
        public static boolean isAcceptablePassword (String password){
            if (password.isEmpty()) {
                //System.out.println("empty string.");
                success = false;
            }
            password = password.trim();
            int len = password.length();
            if (len < MIN_PASSWORD_LENGTH || len > MAX_PASSWORD_LENGTH) {
                // toast("wrong size, it must have at least 8 characters and less than 20.");
                success = false;
            } else {
                char[] aC = password.toCharArray();
                for (char c : aC) {
                    if (Character.isUpperCase(c)) {
                       // System.out.println(c + " is uppercase.");
                        countUpper++;
                    } else if (Character.isLowerCase(c)) {
                        //System.out.println(c + " is lowercase.");
                        countLower++;
                    } else if (Character.isDigit(c)) {
                        //System.out.println(c + " is digit.");
                        countDigit++;
                    } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                        //System.out.println(c + " is valid symbol.");
                        countSpecialChar++;
                    } else {
                        //LandT.toast(c + "  is an invalid character in the password.");
                        success = false;
                        //return success;
                    }
                }
                if (countUpper < 1 || countDigit < 1 || countLower < 1 || countSpecialChar < 1) {
                    //toast = "password doesn't match requirements";
                    //toast(toast);
                    success = false;
                } else {
                    //  toast("password success");
                    success = true;
                }

            }
            return success;
        }






    public static boolean emailRestrictions(String mail){

        Pattern regex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,20}$");
        Matcher matcher = regex .matcher(mail);
        return matcher.find();

    }


}
