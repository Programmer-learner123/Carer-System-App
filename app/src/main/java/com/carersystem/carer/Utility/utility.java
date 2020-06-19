package com.carersystem.carer.Utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;


import com.carersystem.carer.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class utility {
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS =200;

    public static boolean isInternetAvailable (Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public static void SetMessage(Context context, String msg){
        Toast.makeText(context,msg+"",Toast.LENGTH_LONG).show();
        /*Snackbar.make(view, msg+"",
                Snackbar.LENGTH_LONG)
                .show();*/
    }

   /* public static void Signout(Context context){
       Prefs.with(context).setProfile("","","",false,"","");
        context.startActivity(new Intent(context, MainActivity.class));
    }*/

    public static String TodayDate()
    {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        String month = "";
        if ((mMonth + 1) < 10)
            month = "0" + (mMonth + 1);
        else
            month = String.valueOf((mMonth + 1));

        String day = "";
        if (mDay < 10)
            day = "0" + mDay;
        else
            day = String.valueOf(mDay);

        return day + "-"+ month + "-" + mYear;

    }

    public static String Today_Date()
    {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        String month = "";
        if ((mMonth + 1) < 10)
            month = "0" + (mMonth + 1);
        else
            month = String.valueOf((mMonth + 1));

        String day = "";
        if (mDay < 10)
            day = "0" + mDay;
        else
            day = String.valueOf(mDay);

        return day + "/"+ month + "/" + mYear;

    }

    public static String CurrentTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a");
        Log.v("TIME",df.format(Calendar.getInstance().getTime())+"");
        return df.format(Calendar.getInstance().getTime());

    }

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    public static boolean CheckPermission(Context context) {
        int readmsgpermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS);
        int locationPermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int locationPermission1 = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        int callphone = ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        int readestorage = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeestorage = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readphonestate = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED && locationPermission1 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (readmsgpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (callphone != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (readphonestate != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (readestorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (writeestorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
