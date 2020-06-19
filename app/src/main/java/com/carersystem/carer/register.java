package com.carersystem.carer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.carersystem.carer.Utility.utility;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    ProgressBar progressBar;
    RequestQueue requestQueue;
    String scode, msg;
    String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String PassPattern = "((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{5,20})";
    //String userPattern = "^[a-z]{3,15}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        final EditText email = (EditText) findViewById(R.id.etEmail);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final EditText user = (EditText) findViewById(R.id.etUsername);
        final EditText mobile = (EditText) findViewById(R.id.etnumber);
        final EditText address = (EditText) findViewById(R.id.etaddress);

        final Button submitbutton=findViewById(R.id.btnregister);

        progressBar=findViewById(R.id.progressBar);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (utility.isInternetAvailable(register.this)) {

                    if (user.getText().toString().equals("")) {
                        utility.SetMessage(register.this, "Enter Valid Username");
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        if (!email.getText().toString().trim().matches(emailPattern)) {
                            utility.SetMessage(register.this, "Enter Valid Email Id");
                            progressBar.setVisibility(View.INVISIBLE);

                        } else {
                            if (password.getText().toString().equals("")) {

                                utility.SetMessage(register.this, "Enter Valid Password");
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                            else {

                                if (mobile.length() != 10) {
                                    utility.SetMessage(register.this, "Enter 10 digit mobile number");
                                    progressBar.setVisibility(View.INVISIBLE);
                                } else {

                                    if (address.getText().toString().equals("")) {
                                        utility.SetMessage(register.this, "Enter Valid Address");
                                        progressBar.setVisibility(View.INVISIBLE);
                                    } else {


                                        registerUser(user.getText().toString(), email.getText().toString(), password.getText().toString(), mobile.getText().toString(), address.getText().toString());

                                    }

                                }
                            }
                        }
                    }
                }
            }
        });


    }

    /*startActivity(new Intent(Login.this, MainActivity.class));*/
    public void registerUser(final String client_name, final String client_email,final String password,final String client_phone,final String address){
        //Toast.makeText(Register.this,email+password,Toast.LENGTH_LONG).show();
        String url = getResources().getString(R.string.url)+"action=SignUp&client_name="+client_name+"&client_email="+client_email+"&password="+password+"&client_phone="+client_phone+"&address="+address;
        url=url.replaceAll(" ","%20");
        Toast.makeText(register.this,url,Toast.LENGTH_LONG).show();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.v("Response", response.toString());

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    scode = jsonResponse.getString("code");
                    Log.v("responseCode", scode + "");
                    if (scode.equals("200")) {
                        Log.v("responseCode","in");

                        msg = jsonResponse.getString("msg");

                        //Log.v("responseCode",""+id);
                        progressBar.setVisibility(View.INVISIBLE);
                        utility.SetMessage(register.this,msg+" ");
                        startActivity(new Intent(register.this,MainActivity.class));
                    } else {
                        msg = jsonResponse.getString("msg");
                        utility.SetMessage(register.this,"" + msg);
                    }
                    Log.v("Response code", scode );
                    progressBar.setVisibility(View.INVISIBLE);
                } catch (JSONException e) {
                    utility.SetMessage(register.this,"" + e.getMessage());
                    Log.v("responseCode", e.getMessage());
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Error: " + error.getMessage());
                utility.SetMessage(register.this,"" + error.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "SignUp");
                params.put("client_name", client_name);
                params.put("client_email", client_email);
                params.put("password", password);
                params.put("client_phone", client_phone);
                params.put("address", address);

                Log.v("PARAM", "Posting params: " + params.toString());
                Log.v("strreq", ""+params.toString());
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);
        /*int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);
        strReq.setShouldCache(false);
        requestQueue.add(strReq);

        Log.v("strreq", ""+strReq);*/
        /////////////////Volley
    }

}
