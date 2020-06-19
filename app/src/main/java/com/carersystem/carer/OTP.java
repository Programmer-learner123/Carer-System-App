package com.carersystem.carer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import static java.lang.Boolean.TRUE;

public class OTP extends AppCompatActivity {
    Button bLog;
    EditText etLogin;
    TextView otview;
    //String url_req = "http://192.168.181.2/service_main/request_otp.php";

    String otp = "";
    ProgressDialog dialog;

    String scode, msg;
    Context context;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        bLog = (Button) findViewById(R.id.bLogin);
        etLogin = (EditText) findViewById(R.id.etLogin);
        otview =  findViewById(R.id.viewot);


        context = OTP.this;
        // pref = new PrefManager(this);

        bLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  dialog = ProgressDialog.show(OTP.this, "", "Please wait...", true);
                //dialog.show();

                final String client_phone = etLogin.getText().toString();
                if (client_phone.length() == 10) {
                    String url = getResources().getString(R.string.url) +"action=OTP&client_phone=" +client_phone ;
                    url = url.replaceAll(" ", "%20");
                    // pref.setMobileNumber(mobile);

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
                                    Log.v("responseCode", "in");
                                    msg = jsonResponse.getString("msg");
                                    //And then read attributes like
                                    String otp = jsonResponse.getString("otp");

                                       otview.setText(otp);



                                    Log.v("responseCode", "" + otp);

                                    utility.SetMessage(OTP.this, msg + " ");
                                    //startActivity(new Intent(OTP.this, profile.class));
                                } else {
                                    msg = jsonResponse.getString("msg");
                                    utility.SetMessage(OTP.this, "" + msg);
                                }
                                Log.v("Response code", scode);

                            } catch (JSONException e) {
                                utility.SetMessage(OTP.this, "" + e.getMessage());
                                Log.v("responseCode", e.getMessage());

                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Response", "Error: " + error.getMessage());
                            utility.SetMessage(OTP.this, "" + error.getMessage());

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("action", "SignIn");
                            params.put("client_phone", client_phone);

                            Log.v("PARAM", "Posting params: " + params.toString());
                            Log.v("strreq", "" + params.toString());
                            return params;
                        }

                    };
                    requestQueue = Volley.newRequestQueue(OTP.this);
                    requestQueue.add(strReq);

                }

            }
        });
    }
}