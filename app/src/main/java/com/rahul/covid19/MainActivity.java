package com.rahul.covid19;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rahulsuthar_covid19india_api.Covid19;
import com.example.rahulsuthar_covid19india_api.Covid19_Interface;
import com.example.rahulsuthar_covid19india_api.State;
import com.jsevy.adxf.DXFDocument;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     RecyclerView symptoms_Recycler_view,precaution_Recycler_view;
     ArrayList<symptoms_list> symptoms_lists;
     ArrayList<precaution_list> precaution_lists;
     TextView infected,deceased,recoverd,viewAll_Symtoms,viewAll_Precaution,set_countries_name,state_situation,district_situation;
     Button More_info;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (Objects.requireNonNull(getSupportActionBar())).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

      //  Covid19 c  = new Covid19(this,  this);
//
        symptoms_Recycler_view   = findViewById(R.id.RecyclerView);
        precaution_Recycler_view = findViewById(R.id.RecyclerView2);
        infected = findViewById(R.id.infected);
        recoverd = findViewById(R.id.recoverd);
        viewAll_Symtoms = findViewById(R.id.viewAllSymtoms);
        viewAll_Precaution = findViewById(R.id.viewAllPrecaution);
        deceased = findViewById(R.id.deceased);
        More_info = findViewById(R.id.More_info);
        set_countries_name = findViewById(R.id.set_countries_name);
        state_situation = findViewById(R.id.state_situation);
        district_situation = findViewById(R.id.district_situation);

        viewAll_Symtoms.setOnClickListener(this);
        viewAll_Precaution.setOnClickListener(this);
        More_info.setOnClickListener(this);
        set_countries_name.setOnClickListener(this);
        state_situation.setOnClickListener(this);
        district_situation.setOnClickListener(this);

        symptoms_lists = new ArrayList<>();
        precaution_lists = new ArrayList<>();

        symptoms_lists.add(new symptoms_list("cough","Coughing can have causes that aren't due to underlying disease",R.drawable.cough));
        symptoms_lists.add(new symptoms_list("fever","A temporary increase in average body temperature of 98.6°F (37°C)",R.drawable.fever));
        symptoms_lists.add(new symptoms_list("sore_throat","Pain or irritation in the throat that can occur with or without swallowing, often accompanies infections, such as a cold or flu.",R.drawable.sore_throat));

        precaution_lists.add(new precaution_list("Clear","Clean your hands often. Use soap and water.",R.drawable.clean));
        precaution_lists.add(new precaution_list("Cover","Cover your nose and mouth with your bent elbow or a tissue when you cough or sneeze.",R.drawable.cover));
        precaution_lists.add(new precaution_list("Distance","Maintain a safe distance from anyone who is coughing or sneezing.",R.drawable.distance));

        symptoms_Recycler_view(symptoms_lists);
        precaution_Recycler_view(precaution_lists);
        India_Situation_Data();


    }

    private void India_Situation_Data()
    {
        // using Volley Library
        String url = "https://corona.lmao.ninja/v2/countries/india";
       // String url = "api.covid19india.org/data.json";
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
                            JSONObject jsonObject = new JSONObject(response);

                            // Set the data in text view
                            // which are available in JSON format
                            // Note that the parameter inside
                            // the getString() must match
                            // with the name given in JSON format
                            infected.setText(jsonObject.getString("cases"));
                            recoverd.setText(jsonObject.getString("recovered"));
                            deceased.setText(jsonObject.getString("deaths"));
                         /* tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));  */
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
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void symptoms_Recycler_view(ArrayList<symptoms_list> symptoms_lists)
    {
        symptoms_AdapterList symptoms_List = new symptoms_AdapterList(symptoms_lists,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        symptoms_Recycler_view.setLayoutManager(layoutManager);
        symptoms_Recycler_view.setAdapter(symptoms_List);
        symptoms_List.notifyDataSetChanged();
    }

    public void precaution_Recycler_view(ArrayList<precaution_list> precaution_lists)
    {
        precaution_AdapterList precaution_List = new precaution_AdapterList(precaution_lists);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        precaution_Recycler_view.setLayoutManager(layoutManager1);
        precaution_Recycler_view.setAdapter(precaution_List);
        precaution_List.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.viewAllSymtoms:
                Intent intent = new Intent(MainActivity.this,symptoms_viewAll.class);
                startActivity(intent);
                break;

            case R.id.viewAllPrecaution:
                Intent precaution_viewAll = new Intent(MainActivity.this,precaution_viewAll.class);
                startActivity(precaution_viewAll);
                break;

            case R.id.More_info:
                Intent More_info = new Intent(MainActivity.this,More_info.class);
                startActivity(More_info);
                break;

            case R.id.set_countries_name:
                Intent Countries = new Intent(MainActivity.this,Countries.class);
                startActivity(Countries);
                break;

            case R.id.state_situation:
                Intent State_situation = new Intent(MainActivity.this,State_Affected.class);
                startActivity(State_situation);
                break;

            case R.id.district_situation:
                Intent District_situation = new Intent(MainActivity.this,District_Affected.class);
                startActivity(District_situation);
                break;


        }
    }


}

