package com.carersystem.carer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class book_package extends AppCompatActivity {

    RequestQueue requestQueue;
    String scode, msg;
    String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    EditText name,email,category,services,price,description;
    Prefs pref;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_package);
        pref = new Prefs(getApplicationContext());


        name = (EditText) findViewById(R.id.name);
        String Car=pref.getName();
        name.setText(Car);

       email = (EditText) findViewById(R.id.email);
        String Car2=pref.getEmail();
        email.setText(Car2);

       category = (EditText) findViewById(R.id.category);

        Bundle extras = getIntent().getExtras();
        String Test = extras.getString("Category");
        category.setText(Test);
        services = (EditText) findViewById(R.id.package_name);

        Bundle extras1 = getIntent().getExtras();
        String Test1 = extras1.getString("Name");
        services.setText(Test1);
        price = (EditText) findViewById(R.id.price);

        Bundle extras2 = getIntent().getExtras();
        String Test2 = extras2.getString("Price");
        price.setText(Test2);
        description = (EditText) findViewById(R.id.detail);

        final Button submitbutton=findViewById(R.id.btnregister);



        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (utility.isInternetAvailable(book_package.this)) {

                    if (name.getText().toString().equals("")) {
                        utility.SetMessage(book_package.this, "Enter Valid Username");

                    } else {
                        if (!email.getText().toString().trim().matches(emailPattern)) {
                            utility.SetMessage(book_package.this, "Enter Valid Email Id");


                        } else {
                            if (category.getText().toString().equals("")) {

                                utility.SetMessage(book_package.this, "Enter valid Category");

                            } else {
                                if (services.getText().toString().equals("")) {

                                    utility.SetMessage(book_package.this, "Enter proper Service");

                                } else {
                                    if (price.getText().toString().equals("")) {

                                        utility.SetMessage(book_package.this, "Enter One digit one uppercase");

                                    } else {
                                        if (description.getText().toString().equals("")) {

                                            utility.SetMessage(book_package.this, "Enter One digit one uppercase");

                                        } else {


                                            registerUser(name.getText().toString(), email.getText().toString(), category.getText().toString(), services.getText().toString(), price.getText().toString(), description.getText().toString());

                                        }

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
    public void registerUser(final String name, final String email,final String category,final String services,final String price,final String description){
        //Toast.makeText(Register.this,email+password,Toast.LENGTH_LONG).show();
        //final String client_email= Prefs.with(Feedback.this).getEmail();

       // Toast.makeText(Feedback.this, "" + client_email, Toast.LENGTH_LONG).show();
        String url = getResources().getString(R.string.url)+"action=booking&name="+name+"&email="+email+"&category="+category+"&price="+price+"&services="+services+"&description="+description;
        url=url.replaceAll(" ","%20");
        Toast.makeText(book_package.this,url,Toast.LENGTH_LONG).show();
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

                        String category = jsonResponse.getString("category");
                        String services = jsonResponse.getString("services");
                        String price = jsonResponse.getString("price");
                        utility.SetMessage(book_package.this,msg+" ");
                        // startActivity(new Intent(booking.this,parking_slot.class));
                        Intent intent=new Intent(book_package.this,Purchase.class);
                        //intent.putExtra("Item_id",Item_id.getText().toString().trim());

                        // intent.putExtra("amount",slot.getText().toString().trim());

                        intent.putExtra("Category",category);
                        intent.putExtra("Services",services);
                        intent.putExtra("Price",price);

                        startActivity(intent);
                    } else {
                        msg = jsonResponse.getString("msg");
                        utility.SetMessage(book_package.this,"" + msg);
                    }
                    Log.v("Response code", scode );

                } catch (JSONException e) {
                    utility.SetMessage(book_package.this,"" + e.getMessage());
                    Log.v("responseCode", e.getMessage());

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Error: " + error.getMessage());
                utility.SetMessage(book_package.this,"" + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "booking");
                params.put("name", name);
                params.put("email", email);
                params.put("category", category);
                params.put("services", services);
                params.put("description", description);
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
