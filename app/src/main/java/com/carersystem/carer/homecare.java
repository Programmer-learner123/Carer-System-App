package com.carersystem.carer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.carersystem.carer.Adapter.CardAdapterhomecare;
import com.carersystem.carer.Model.Modalhomecare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AlertDialog;
public class homecare extends AppCompatActivity {

    private List<Modalhomecare> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CardAdapterhomecare adapter;
    // public static final String TAG_MESS = "mess_name";
    public static final String TAG_ID = "id";
    public static final String TAG_CAT = "category";
    public static final String TAG_ITEM_NAME = "name";

    public static final String TAG_PRICE = "price";


    public static final String TAG_D = "detail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homecare);
//context = Home.this;
        // pref = new PrefManager(this);
        // pref.checkLogin();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();


        getData();




    }
    //This method will get data from the web api
    private void getData() {
        //  progressBar.setVisibility(View.VISIBLE);
        Bundle extras1 = getIntent().getExtras();
        String category = extras1.getString("category");

        String url = getResources().getString(R.string.url)+"action=itemfetch&category="+category;
        url=url.replaceAll(" ","%20");
        Log.d("VURL",url+"");
        //Creating a json array request
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.v("Response", response.toString());
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String    scode = jsonResponse.optString("code");

                    //String status = jsonResponse.getString("status");


                    Log.wtf("responseCode", scode + " fff "+response);

                    //JSONArray classj=jsonResponse.getJSONArray("classes");
                    JSONArray Data = jsonResponse.getJSONArray("classes_cart");
                    parseData(Data);


                } catch (JSONException e) {
                    Toast.makeText(homecare.this,"No data Received",
                            Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("USER", "Error: " + error.getMessage());
                Toast.makeText(homecare.this, "No data recieved", Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.GONE);
                //dialog.dismiss();
            }
        });

        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);
        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(strReq);
        // Adding request to request queue
        // MyApplication.getInstance().addToRequestQueue(strReq);

    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            Modalhomecare superHerom = new Modalhomecare();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);



                superHerom.setCategory(json.getString(TAG_CAT));

                superHerom.setName(json.getString(TAG_ITEM_NAME));
                superHerom.setPrice(json.getString(TAG_PRICE));
                superHerom.setDetail(json.getString(TAG_D));




            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSuperHeroes.add(superHerom);
        }

        //Finally initializing our adapter
        adapter = new CardAdapterhomecare(listSuperHeroes, homecare.this, new CardAdapterhomecare.OnItemClickListener() {

            @Override
            public void onItemClick(Modalhomecare item) {
                String category = item.getCategory();
                String name = item.getName();
                String price = item.getPrice();
                Intent intent = new Intent(homecare.this, book_package.class);
                intent.putExtra("Category", category);
                intent.putExtra("Name", name);
                intent.putExtra("Price", price);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();


            }
        });



        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(homecare.this);
        back.setTitle("Are you sure want to leave now?");
        back.setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                endActivity();
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialogback = back.create();
        dialogback.show();
    }

    public void endActivity() {
        this.finish();
    }


}



