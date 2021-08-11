package com.rahul.covid19;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Countries extends AppCompatActivity {
    EditText Search_Country;
    ListView Country_List;
    public static List<Countries_Model> countries_modelList = new ArrayList<>();
    Countries_Model countries_model;
    Country_Adapter country_adapter;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        (Objects.requireNonNull(getSupportActionBar())).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Search_Country = findViewById(R.id.Search_Country);
        Country_List = findViewById(R.id.country_list);

         fetchdata(); // countries !

        Country_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),detail_country.class).putExtra("position",position));
            }
        });



         Search_Country.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 country_adapter.getFilter().filter(s);
                 country_adapter.notifyDataSetChanged();
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });
    }
    private void fetchdata()
    {

        // using Volley Library
        String url = "https://corona.lmao.ninja/v2/countries/";
        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {    // Handle the JSON object and
                        // handle it inside try and catch
                        try {
                            // Creating object of JSONObject
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String country_name = jsonObject.getString("country");
                                String cases = jsonObject.getString("cases");
                                String today_cases = jsonObject.getString("todayCases");
                                String deaths = jsonObject.getString("deaths");
                                String today_deaths = jsonObject.getString("todayDeaths");
                                String recovered = jsonObject.getString("recovered");

                                JSONObject Object = jsonObject.getJSONObject("countryInfo");
                                String flagUrl = Object.getString("flag");
                               countries_model = new Countries_Model(flagUrl,country_name,cases,today_cases,deaths,today_deaths,recovered);
                               countries_modelList.add(countries_model);

                            }
                            country_adapter = new Country_Adapter(Countries.this,countries_modelList);
                            Country_List.setAdapter(country_adapter);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(Countries.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



}
