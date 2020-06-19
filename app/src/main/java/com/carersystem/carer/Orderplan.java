package com.carersystem.carer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.carersystem.carer.Adapter.CardAdapterClientorder;
import com.carersystem.carer.Model.Modalorderview;
import com.carersystem.carer.Utility.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.carersystem.carer.Carerview.CARER_NUM;

public class Orderplan extends AppCompatActivity {

    //Creating a List of superheroes
    private List<Modalorderview> listHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    // public static final String TAG_MESS = "mess_name";
    public static final String O_ID = "id";
    public static final String O_NAME = "name";
    public static final String O_EMAIL = "email";
    public static final String O_CATEGORY= "category";

    public static final String O_SERVICES = "services";
    public static final String O_PRICE = "price";
    public static final String O_DTL = "description";
    public static final String O_DATE = "date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderplan);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //editId = (EditText) findViewById(R.id.editTextId);
        //btn = (Button) findViewById(R.id.buttonget);




        //Initializing our superheroes list
        listHeroes = new ArrayList<>();

        getData();
    }

    private void getData() {

        String client_email= Prefs.with(Orderplan.this).getEmail();

        Toast.makeText(Orderplan.this, "" + client_email, Toast.LENGTH_LONG).show();

        //Toast.makeText(getApplicationContext(), category, Toast.LENGTH_LONG).show();
        String url = getResources().getString(R.string.url) + "action=orderfetch&client_email="+client_email;
        url = url.replaceAll(" ", "%20");
        Log.d("VURL", url + "");
        //Creating a json array request
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.v("Response", response.toString());
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String scode = jsonResponse.optString("code");
                    Log.wtf("responseCode", scode + " fff " + response);

                    //JSONArray classj=jsonResponse.getJSONArray("classes");
                    JSONArray Data = jsonResponse.getJSONArray("classes_cart");
                    parseData(Data);


                } catch (JSONException e) {
                    Toast.makeText(Orderplan.this,"No Data Received",
                            Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("USER", "Error: " + error.getMessage());
                Toast.makeText(Orderplan.this,"No Data Received", Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.GONE);
                //dialog.dismiss();
            }
        });


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
            Modalorderview superHero = new Modalorderview();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                superHero.setId(json.getString(O_ID));
                superHero.setName(json.getString(O_NAME));
                superHero.setEmail(json.getString(O_EMAIL));
                superHero.setCategory(json.getString(O_CATEGORY));
                superHero.setServices(json.getString(O_SERVICES));
                superHero.setPrice(json.getString(O_PRICE));
                superHero.setDescription(json.getString(O_DTL));
                superHero.setDate(json.getString(O_DATE));



            } catch (JSONException e) {
                e.printStackTrace();
            }
            listHeroes.add(superHero);
        }


        adapter = new CardAdapterClientorder(listHeroes, Orderplan.this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder back = new AlertDialog.Builder(Orderplan.this);
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
