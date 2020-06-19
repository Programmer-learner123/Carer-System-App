package com.carersystem.carer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class home extends AppCompatActivity {

    TextView usinin,usinu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usinin =findViewById(R.id.signin);
        usinu=findViewById(R.id.signup);

        usinin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(home.this,MainActivity.class);
                startActivity(intent);

            }
        });
        usinu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(home.this,register.class);
                startActivity(intent);

            }
        });
    }
}
