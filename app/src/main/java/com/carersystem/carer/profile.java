package com.carersystem.carer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.carersystem.carer.Utility.Prefs;
import com.carersystem.carer.Utility.utility;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;


public class profile extends AppCompatActivity {

    TextView first,personalcare,medicalcare;
    ImageButton carer,userorder,feedbackuser;
    LinearLayout usersignput;
    Prefs pref;
    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //loading the default fragment
        //loadFragment(new carer());
        first= findViewById(R.id.one);
        personalcare= findViewById(R.id.personal);
        medicalcare= findViewById(R.id.medical);
        carer= findViewById(R.id.etUsername);
        userorder= findViewById(R.id.order);
        feedbackuser= findViewById(R.id.filledback);
        usersignput= findViewById(R.id.Logout);


        //getting bottom navigation view and attaching the listener


        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profile.this,homecare.class);
                intent.putExtra("category", "Home Care");
                startActivity(intent);
            }
        });
        personalcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profile.this,homecare.class);
                intent.putExtra("category", "Personal Care");
                startActivity(intent);
            }
        });
        medicalcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profile.this,homecare.class);
                intent.putExtra("category", "Medical Care");
                startActivity(intent);
            }
        });

        carer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profile.this,Carerview.class);

                startActivity(intent);
            }
        });

        userorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profile.this,Orderplan.class);

                startActivity(intent);
            }
        });

        feedbackuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profile.this,Feedback.class);

                startActivity(intent);
            }
        });

        pref = new Prefs(getApplicationContext());
        usersignput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.clearSession();
                Intent intent = new Intent(profile.this, home.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                finish();
            }
        });
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        utility.SetMessage(profile.this,"Please click BACK again to exit");

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
                finish();
            }
        }, 2000);
    }


}
