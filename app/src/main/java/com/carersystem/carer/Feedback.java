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
import com.carersystem.carer.Utility.Prefs;
import com.carersystem.carer.Utility.utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
    ProgressBar progressBar;
    RequestQueue requestQueue;
    String scode, msg;
    String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback);
        final EditText caeremail = (EditText) findViewById(R.id.etEmail);

        final EditText carername = (EditText) findViewById(R.id.etUsername);

        final EditText opinion = (EditText) findViewById(R.id.etaddress);

        final Button submitbutton=findViewById(R.id.btnregister);



        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (utility.isInternetAvailable(Feedback.this)) {

                    if (carername.getText().toString().equals("")) {
                        utility.SetMessage(Feedback.this, "Enter Valid Username");

                    } else {
                        if (!caeremail.getText().toString().trim().matches(emailPattern)) {
                            utility.SetMessage(Feedback.this, "Enter Valid Email Id");


                        } else {
                            if (opinion.getText().toString().equals("")) {

                                utility.SetMessage(Feedback.this, "Enter One diit one uppercase");

                            } else {


                                        registerUser(carername.getText().toString(), caeremail.getText().toString(), opinion.getText().toString());

                                    }

                                }
                            }
                        }
                    }

        });


    }

    /*startActivity(new Intent(Login.this, MainActivity.class));*/
    public void registerUser(final String carer_name, final String carer_email,final String opinion){
        //Toast.makeText(Register.this,email+password,Toast.LENGTH_LONG).show();
       final String client_email= Prefs.with(Feedback.this).getEmail();

        Toast.makeText(Feedback.this, "" + client_email, Toast.LENGTH_LONG).show();
        String url = getResources().getString(R.string.url)+"action=Feedback&carer_name="+carer_name+"&carer_email="+carer_email+"&opinion="+opinion+"&client_email="+client_email;
        url=url.replaceAll(" ","%20");
        Toast.makeText(Feedback.this,url,Toast.LENGTH_LONG).show();
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

                        utility.SetMessage(Feedback.this,msg+" ");
                        startActivity(new Intent(Feedback.this,profile.class));
                    } else {
                        msg = jsonResponse.getString("msg");
                        utility.SetMessage(Feedback.this,"" + msg);
                    }
                    Log.v("Response code", scode );

                } catch (JSONException e) {
                    utility.SetMessage(Feedback.this,"" + e.getMessage());
                    Log.v("responseCode", e.getMessage());

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Error: " + error.getMessage());
                utility.SetMessage(Feedback.this,"" + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "Feedback");
                params.put("carer_name", carer_name);
                params.put("carer_email", carer_email);
                params.put("opinion", opinion);
                params.put("client_email", client_email);

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
