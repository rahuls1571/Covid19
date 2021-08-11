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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class State_Affected extends AppCompatActivity {
    EditText Search_state;
    ListView state_List;
    State_Model state_model;
    public static List<State_Model> state_modelList = new ArrayList<>();
    State_adapter state_adapter;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state__affected);

        Search_state = findViewById(R.id.Search_state);
        state_List = findViewById(R.id.state_list);

        (Objects.requireNonNull(getSupportActionBar())).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fetchStatedata();



        Search_state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              state_adapter.getFilter().filter(s);
              state_adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void fetchStatedata() {
         String url = "https://api.covid19india.org/data.json";
         final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 try {
                     // Creating object of JSONObject
                     JSONArray jsonArray = (response.getJSONArray("statewise"));
                     for(int i=0; i<jsonArray.length(); i++){
                         JSONObject jsonObject = jsonArray.getJSONObject(i);
                         String active = jsonObject.getString("active");
                         String deaths = jsonObject.getString("deaths");
                         String recovered = jsonObject.getString("recovered");
                         String state = jsonObject.getString("state");
                         String confirmed = jsonObject.getString("confirmed");

                         state_model = new State_Model(active,deaths,recovered,state,confirmed);
                         state_modelList.add(state_model);

                     }

                     state_adapter = new State_adapter(State_Affected.this,state_modelList);
                     state_List.setAdapter(state_adapter);


                 }
                 catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(State_Affected.this,error.getMessage(),Toast.LENGTH_SHORT).show();
             }
         });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}