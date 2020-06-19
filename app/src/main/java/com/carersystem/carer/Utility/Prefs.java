package com.carersystem.carer.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import static java.lang.Boolean.TRUE;

public class Prefs {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "user";

    // All Shared Preferences Keys
    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String LOGIN = "login";
    private static final String ID = "id";
    private static final String NAME = "user";

    private static final String PHONE = "mobile";
    private static final String STATUS = "status";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";

    private static final String PREFS_NAME = "USER";
    private static Prefs instance;
    private static SharedPreferences sharedPreferences;


    public Prefs(Context context) {

        this._context= context;
        pref = _context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static Prefs with(Context context) {
        if (instance == null) {
            instance = new Prefs(context);
        }
        return instance;
    }

    // Constructor



    public void setProfile(String id, String user,String email,String mobile,String address) {


                editor.putString(ID, id)
                .putString(NAME, user)
                .putString(PHONE, mobile)
                .putString(EMAIL, email)

                .putString(ADDRESS, address)

                .putBoolean(KEY_IS_LOGGED_IN,true)
                .apply();
    }




    public void clearSession() {
        editor.clear();
        editor.commit();
    }




    public String getEmail()
    {
        return pref.getString(EMAIL,"");
    }

    public String getMobile()
    {
        return pref.getString(PHONE,"");
    }
    public String getName()
    {
        return pref.getString(NAME,"");
    }

    public String getAddress()
    {
        return pref.getString(ADDRESS,"");
    }
    public boolean isLogin() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

}
